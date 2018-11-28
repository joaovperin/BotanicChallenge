/*
 * BotanicChallenge
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.jpe.pdi.btc;

import br.jpe.pdi.btc.core.Systems;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.UIManager;

/**
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

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction((ActionEvent event) -> {
            System.out.println("Hello World!");
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
