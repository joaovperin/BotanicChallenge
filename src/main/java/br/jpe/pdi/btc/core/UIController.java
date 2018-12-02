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

import br.jpe.pdi.btc.gui.MessageDialog;
import br.jpe.pdi.btc.gui.LoadingBar;
import br.jpe.pdi.btc.utils.Files;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

/**
 * UI Controller
 */
public class UIController {

    private static UIController instance;

    private Canvas canvas;

    private UIController() {
    }

    public final void onClickOpenFileBrowser(JTextField textField) {
        // Create Filechooser
        JFileChooser jFileChooser = new javax.swing.JFileChooser();
        jFileChooser.setFileFilter(new ImageFilter());
        jFileChooser.setVisible(true);
        jFileChooser.setCurrentDirectory(new File(textField.getText()));
        // Open file chooser
        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            textField.setText(jFileChooser.getSelectedFile().toString());
            renderImageOnCanvas(textField);
        }
    }

    public final void onClickGo(JTextField textField) {
        LoadingBar.get().show(canvas);
        new Thread(new ImageGatherRunnable(textField.getText()).onSucessCallback((SucessCallback<String>) (String data) -> {
            MessageDialog.get().showInfo(fmtMessage(data));
        }).onErrorCallback((Exception err) -> {
            MessageDialog.get().showError(fmtError(err));
            err.printStackTrace(System.out);
        }).onFinallyCallback(() -> {
            LoadingBar.get().hide(canvas);
            renderImageOnCanvas(textField);
        })).start();
    }

    public final void renderImageOnCanvas(JTextField textField) {
        // Render in another Thread
        String filename = textField.getText();
        File file = new File(filename);
        if (this.canvas != null && file.exists() && file.isFile()) {
            new Thread(() -> drawImageOnCanvas(filename)).start();
        }
    }

    private String fmtMessage(String msg) {
        return String.format("Information:\n\n%s\n", msg);
    }

    private String fmtError(Exception err) {
        return String.format("Error:\n\n%s\n", err.getMessage());
    }

    private void drawImageOnCanvas(String imageName) {
        try {
            Graphics2D g = (Graphics2D) this.canvas.getGraphics();
            java.awt.Image img = ImageIO.read(new File(imageName));
            g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            g.drawImage(img, 0, 0, canvas);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public final UIController setCanvasRef(Canvas canvas) {
        this.canvas = canvas;
        return this;
    }

    private static class ImageFilter extends FileFilter {

        @Override
        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            return Files.isImage(f);
        }

        @Override
        public String getDescription() {
            return "Images (jpg, jpeg, png)";
        }
    }

    public static final UIController get() {
        if (instance == null) {
            instantiate();
        }
        return instance;
    }

    private static synchronized void instantiate() {
        if (instance == null) {
            instance = new UIController();
        }
    }

}
