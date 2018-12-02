/*
 * Copyright (C) 2018 BotanicChallenge
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.jpe.pdi.btc.core;

import br.jpe.ipl.core.Image;
import br.jpe.ipl.core.ImageBuilder;
import br.jpe.ipl.core.ImageColor;
import br.jpe.ipl.core.ImageInfo;
import br.jpe.ipl.core.ImageInfoConstants;
import br.jpe.ipl.core.ImageInfoExtractor;
import br.jpe.ipl.core.ImageLoader;
import br.jpe.ipl.core.ImagePoint;
import br.jpe.ipl.core.scripts.image.BinaryLabelingScript;
import br.jpe.ipl.core.scripts.image.extraction.PixelCountExtractionScript;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An image info-gathering runnable
 */
public class ImageGatherRunnable implements Runnable, ImageInfoConstants {

    private final String filename;

    public ImageGatherRunnable(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        System.out.println(gatherInfo().toString());
    }

    private ImageInfo gatherInfo() {
        // Load image
        Image imgOriginal = null;
        try {
            imgOriginal = loadImage(filename);
        } catch (IOException ex) {
            Logger.getLogger(ImageGatherRunnable.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Minimum area to be considered a block
        final int MIN_AREA = 400;

        // Process
        BinaryLabelingScript binaryLabeler = new BinaryLabelingScript(ImageColor.black(), MIN_AREA, true);
        Image newImage = ImageBuilder.create(imgOriginal).
                applyScript((mtz, c, i, j) -> {
                    int r = c.getRed();
                    int g = c.getGreen();
                    int b = c.getBlue();

                    int v = 0; // defaults black
                    // Wine or Copper red
                    if (isBetween(r, 114, 72) && isBetween(g, 36, 43) && isBetween(b, 32, 45)
                            || isBetween(r, 216, 71) && isBetween(g, 109, 43) && isBetween(b, 93, 45)) {
                        v = 255;
                    }
                    // LightBrown
                    if (isBetween(r, 142, 30) && isBetween(g, 132, 22) && isBetween(b, 96, 22)) {
                        v = 0;
                    }
                    // DarkBrown
                    if (isBetween(r, 115, 30) && isBetween(g, 89, 22) && isBetween(b, 76, 22)) {
                        v = 0;
                    }

                    for (int k = 0; k < 3; k++) {
                        mtz[i][j][k] = v;
                    }
                }).
                applyScript(binaryLabeler).
                build();

        // Gather INFO
        ImageInfo info = ImageInfo.create();
        Map<ImageColor, ImagePoint> colors = binaryLabeler.getColors();
        colors.forEach((ImageColor k, ImagePoint v) -> {
            final int pCnt = ImageInfoExtractor.create(newImage.getMatrix()).
                    applyScript(new PixelCountExtractionScript(k)).
                    extract().getInt(PIXEL_COUNT);
            info.put(PIXEL_COUNT + "_" + k, pCnt);
        });
        return info;
    }

    private static boolean isBetween(int pValue, int target, int tolerance) {
        int tValue = (int) ((double) target * (double) tolerance) / 100;
        int min = target - tValue;
        int max = target + tValue;
        return (pValue >= min && pValue <= max);
    }

    private Image loadImage(String filename) throws IOException {
        // Checks file name
        if (filename == null || filename.trim().isEmpty()) {
            throw new FileNotFoundException("Image not found!");
        }
        // Checks file exists
        File file = new File(filename);
        if (file.exists()) {
            return ImageLoader.fromFile(file).asOriginal();
        }
        // Get the image as a resource
        InputStream resourceImg = getClass().getClassLoader().getResourceAsStream(filename);
        if (resourceImg != null) {
            return ImageLoader.fromResources(filename).asOriginal();
        }
        throw new FileNotFoundException("Image not found!");
    }

}
