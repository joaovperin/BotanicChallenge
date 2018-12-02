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

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

/**
 * Loading bar
 */
public class LoadingBar {

    private static LoadingBar instance;

    private LoadingBar() {
    }

    private static final int BWIDTH = 400;
    private static final int BHEIGHT = 120;
    private static final String LOADING = "Loading...";

    // Bounding box
    private Rectangle bb = null;

    public void show(Canvas canvas) {
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        if (bb != null) {
            return;
        }

        int cW = canvas.getWidth();
        int cH = canvas.getHeight();
        bb = new Rectangle((cW - BWIDTH) / 2, (cH - BHEIGHT) / 2, BWIDTH, BHEIGHT);

        g.setColor(Color.BLACK);
        g.fillRect(bb.x, bb.y, bb.width, bb.height);

        Font font = new Font("Serif", Font.PLAIN, 96);

        float dx = BWIDTH / 2 - (LOADING.length() * font.getSize()) / 5;
        float dy = (bb.height + font.getSize() / 2) / 2;

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(LOADING, bb.x + dx, bb.y + dy);
    }

    public void hide(Canvas canvas) {
        Graphics2D g = (Graphics2D) canvas.getGraphics();
        if (bb == null) {
            return;
        }
        g.clearRect(bb.x, bb.y, bb.width, bb.height);
        bb = null;
    }

    public static final LoadingBar get() {
        if (instance == null) {
            instantiate();
        }
        return instance;
    }

    private static synchronized void instantiate() {
        if (instance == null) {
            instance = new LoadingBar();
        }
    }

}
