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

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.swing.filechooser.FileFilter;

/**
 * Image filter for JFileChooser
 */
public class ImageFilter extends FileFilter {

    private static final List<String> VALID_EXTS = Arrays.asList(Utils.PNG, Utils.JPG, Utils.JPEG);

    //Accept all directories and all gif, jpg, tiff, or png files.
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = Utils.getExtension(f);
        if (extension != null) {
            return VALID_EXTS.contains(extension);
        }
        return false;
    }

    //The description of this filter
    @Override
    public String getDescription() {
        return "Images";
    }

}
