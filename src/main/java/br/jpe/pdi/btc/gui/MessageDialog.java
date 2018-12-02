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
package br.jpe.pdi.btc.gui;

import javax.swing.JOptionPane;

/**
 * Helper to show message dialogs
 */
public class MessageDialog {

    private static MessageDialog instance;

    private static final String INFO_TITLE = "Information";
    private static final String ERROR_TITLE = "Error";

    private MessageDialog() {
    }

    public final void showInfo(String info) {
        new Thread(() -> JOptionPane.showMessageDialog(null, info, INFO_TITLE, JOptionPane.INFORMATION_MESSAGE)).start();
    }

    public final void showError(String err) {
        new Thread(() -> JOptionPane.showMessageDialog(null, err, ERROR_TITLE, JOptionPane.ERROR_MESSAGE)).start();
    }

    public static final MessageDialog get() {
        if (instance == null) {
            instantiate();
        }
        return instance;
    }

    private static synchronized void instantiate() {
        if (instance == null) {
            instance = new MessageDialog();
        }
    }

}
