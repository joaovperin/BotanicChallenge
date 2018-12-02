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
package br.jpe.pdi.btc;

import br.jpe.pdi.btc.utils.Systems;
import br.jpe.pdi.btc.gui.MainFrame;
import javax.swing.UIManager;

/**
 * Main program EntryPoint
 *
 * @author joaovperin
 */
public class BotanicChallenge {

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
    private static void startGraphicalUserInterfaceApplication() throws Exception {
        final String lookAndFeel = Systems.isWindows()
                ? "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
                : "javax.swing.plaf.nimbus.NimbusLookAndFeel";
        /*
        -> Available LookAndFeels:
        javax.swing.plaf.metal.MetalLookAndFeel
        javax.swing.plaf.nimbus.NimbusLookAndFeel
        com.sun.java.swing.plaf.motif.MotifLookAndFeel
        com.sun.java.swing.plaf.windows.WindowsLookAndFeel
        com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel
         */
        UIManager.setLookAndFeel(lookAndFeel);
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }

}
