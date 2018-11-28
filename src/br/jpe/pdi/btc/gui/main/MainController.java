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
package br.jpe.pdi.btc.gui.main;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author joaovperin
 */
public final class MainController extends MainBase implements Initializable {

    /** The Canvas graphic context */
    private GraphicsContext graphicsContext;
    private Image image;

    private final Stage st;

    public MainController(Stage st) {
        this.st = st;
        this.initialize(null, null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("*** initialize called");
        this.graphicsContext = canvas.getGraphicsContext2D();
        configureCanvas();
        textField.setText(System.getProperty("user.home"));
    }

    @Override
    protected void openFileBrowser(MouseEvent mouseEvent) {
        File f = showFileChooserAndGetResult();
        if (f != null && f.exists()) {
            textField.setText(f.getAbsolutePath());
        }
        System.out.println(f);
    }

    @Override
    protected void reloadImage(MouseEvent mouseEvent) {
        /* TODO: SOOLVE THE NPE */
        double canvasWidth = graphicsContext.getCanvas().getWidth();
        double canvasHeight = graphicsContext.getCanvas().getHeight();
        image = getImage(textField.getText());
        graphicsContext.drawImage(image, 0, 0, canvasWidth, canvasHeight);
    }

    private File showFileChooserAndGetResult() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter
                = new FileChooser.ExtensionFilter("IMG files (*.(png || jpg))", "*.png", "*.jpg");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(st);
    }

    private Image getImage(String filename) {
        if (filename == null || filename.trim().isEmpty()) {
            System.out.println("*** Invalid filename");
            return null;
        }
        if (new File(filename).exists()) {
            final String file = "file://";
            if (filename.startsWith(file)) {
                return new Image(filename);
            }
            return new Image(file.concat(filename));
        }
        System.out.println("*** Image don't exist :/");
        return null;
    }

    private void configureCanvas() {
        double canvasWidth = graphicsContext.getCanvas().getWidth();
        double canvasHeight = graphicsContext.getCanvas().getHeight();

        graphicsContext.setFill(Color.LIGHTGRAY);
        graphicsContext.setStroke(Color.BLACK);
        graphicsContext.setLineWidth(5);

        graphicsContext.fill();
        graphicsContext.strokeRect(
                0, //x of the upper left corner
                0, //y of the upper left corner
                canvasWidth, //width of the rectangle
                canvasHeight);  //height of the rectangle

        graphicsContext.setLineWidth(1);
    }

}
