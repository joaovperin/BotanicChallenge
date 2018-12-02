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
import br.jpe.ipl.core.ImageLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * File utils
 */
public class Utils {

    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String TIFF = "tiff";
    public final static String TIF = "tif";
    public final static String PNG = "png";

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    public static Image loadImage(String filename) throws IOException {
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
        InputStream resourceImg = Utils.class.getClassLoader().getResourceAsStream(filename);
        if (resourceImg != null) {
            return ImageLoader.fromResources(filename).asOriginal();
        }
        throw new FileNotFoundException("Image not found!");
    }

}
