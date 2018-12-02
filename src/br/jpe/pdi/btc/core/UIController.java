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

/**
 * UI Controller
 */
public class UIController {

    private static UIController instance;

    private String imageName;

    private UIController() {
        this.imageName = "";
    }

    public final void onClickOpenFileBrowser() {
        System.out.println("*** Openning browser for image: " + imageName);
    }

    public final void onClickGo() {
        System.out.println("*** Processing image: " + imageName);
    }

    public void onTextChanged(String newText) {
        this.imageName = newText;
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
