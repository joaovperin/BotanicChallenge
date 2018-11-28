/*
 * Copyright (C) 2018 Perin
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
package br.jpe.pdi.btc;

import br.jpe.pdi.btc.core.Systems;
import br.jpe.pdi.btc.gui.main.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.UIManager;

/**
 * Main program EntryPoint
 *
 * @author joaovperin
 */
public class BotanicChallenge extends Application {

    /**
     * Main program entry point
     *
     * @param args
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        // Command line or window based
        if (args == null || args.length == 0) {
            startGraphicalUserInterfaceApplication();
        } else {
            System.out.println("*** Execução por linha de comando não suportada!");
        }
    }

    /**
     * Starts the GUI application
     *
     * @param args
     * @throws Exception
     */
    private static void startGraphicalUserInterfaceApplication(String... args) throws Exception {
        final String lookAndFeel = Systems.isWindows() ?
                "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel" :
                "javax.swing.plaf.metal.MetalLookAndFeel";
        UIManager.setLookAndFeel(lookAndFeel);
        Application.launch(BotanicChallenge.class, args);
    }

    /**
     * Start the application
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        Parent root = new MainController(primaryStage);
        Scene scene = new Scene(root);
        primaryStage.setTitle("Desafio do botânico");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
