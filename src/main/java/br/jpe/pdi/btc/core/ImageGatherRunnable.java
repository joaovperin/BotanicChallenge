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

import br.jpe.pdi.btc.utils.Files;
import br.jpe.ipl.core.Image;
import br.jpe.ipl.core.ImageBuilder;
import br.jpe.ipl.core.ImageColor;
import br.jpe.ipl.core.ImageInfo;
import br.jpe.ipl.core.ImageInfoConstants;
import br.jpe.ipl.core.scripts.image.BinaryLabelingScript;
import br.jpe.ipl.core.scripts.image.convolution.DilationMorphScript;
import java.io.IOException;

/**
 * An image info-gathering runnable
 */
public class ImageGatherRunnable extends AsyncTask implements ImageInfoConstants {

    private static final int MIN_AREA = 20;
    private final String filename;

    public ImageGatherRunnable(String filename) {
        this.filename = filename;
    }

    @Override
    public String runTask() throws Exception {
        ImageInfo info = gatherInfo();
        return getReturnMessage(info);
    }

    public final ImageInfo gatherInfo() throws IOException {
        Image imgOriginal = Files.loadImage(filename);

        // Checks if  32 > RED < 196, 21 > Green < 51 AND 18 > Blue < 46
        BinaryLabelingScript binaryLabeler = new BinaryLabelingScript(ImageColor.black(), MIN_AREA, true);
        ImageBuilder.create(imgOriginal).transform((r, g, b)
                -> ((isBetween(r, 114, 72) && isBetween(g, 36, 43) && isBetween(b, 32, 45)) ? 255 : 0))
                .applyScript(2, new DilationMorphScript()).
                build(binaryLabeler);
        return ImageInfo.create().put(PIXEL_COUNT, binaryLabeler.getColors().size());
    }

    private static boolean isBetween(int pValue, int target, int tolerance) {
        int tValue = (int) ((double) target * (double) tolerance) / 100;
        int min = target - tValue;
        int max = target + tValue;
        return (pValue >= min && pValue <= max);
    }

    private String getReturnMessage(ImageInfo info) {
        StringBuilder sb = new StringBuilder();
        int numManchas = info.getInt(PIXEL_COUNT);
        sb.append("Numero de manchas: ").append(numManchas).append('\n');
        sb.append("Tratamento: ").append(getTratamento(numManchas)).append('\n');
        return sb.toString();
    }

    private String getTratamento(int numManchas) {
        if (numManchas == 1) {
            return "Mancha INICIAL.\nTratamento: Chá de alho";
        }
        if (numManchas == 2 || numManchas == 3) {
            return "Mancha MÉDIA.\nTratamento: Pó de calcário dissolvido em 1L de água";
        }
        if (numManchas > 3) {
            return "Mancha GRAVE.\nTratamento: Não possui. Você deve arrancar a folha e pulverizar a planta com Aerosol anti-ferrugem botânica";
        }
        return "Parabéns! A folha analisada não possui problemas de ferrugem";
    }

}
