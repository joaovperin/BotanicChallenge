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

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * UI Controller
 */
public class UIController {

    private static UIController instance;

    private String imageName;
    private Canvas canvas;

    private UIController() {
        this.imageName = "";
    }

    public final void onClickOpenFileBrowser() {
        JFileChooser jFileChooser = new javax.swing.JFileChooser();
        jFileChooser.setFileFilter(new ImageFilter());
        jFileChooser.setVisible(true);
        jFileChooser.setCurrentDirectory(new File(imageName));

        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            this.imageName = jFileChooser.getSelectedFile().toString();
            if (this.canvas != null) {
                drawImageOnCanvas();
            }
        }
    }

    public final void onClickGo() {
        new Thread(new ImageGatherRunnable(imageName).onSucessCallback((String msg) -> {
            JOptionPane.showMessageDialog(null, fmtMessage(msg), "Information!", JOptionPane.INFORMATION_MESSAGE);
        }).onErrorCallback((Exception err) -> {
            JOptionPane.showMessageDialog(null, fmtError(err), "Error!", JOptionPane.ERROR_MESSAGE);
            err.printStackTrace(System.out);
        })).start();
    }

    public void onTextChanged(String newText) {
        this.imageName = newText;
    }

    public String fmtMessage(String msg) {
        return String.format("Information:\n\n%s\n", msg);
    }

    private String fmtError(Exception err) {
        return String.format("Error:\n\n%s\n", err.getMessage());
    }

    public void setCanvasRef(Canvas canvas) {
        this.canvas = canvas;
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

    private void drawImageOnCanvas() {
        try {
            java.awt.Image img = ImageIO.read(new File(imageName));
            Graphics2D g = (Graphics2D) this.canvas.getGraphics();
            g.drawImage(img, 0, 0, canvas);
        } catch (IOException ex) {
            Logger.getLogger(UIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
