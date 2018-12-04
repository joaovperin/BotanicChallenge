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

import static br.jpe.ipl.core.ImageInfoConstants.PIXEL_COUNT;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the only class that really mathers :D
 */
public class ImageGatherRunnableTest {

    @Test
    public void testRunTaskLeaf1() throws Exception {
        ImageGatherRunnable instance = new ImageGatherRunnable(get("leaf_1.png"));
        assertEquals(3, instance.gatherInfo().getInt(PIXEL_COUNT));
    }

    @Test
    public void testRunTaskLeaf2() throws Exception {
        ImageGatherRunnable instance = new ImageGatherRunnable(get("leaf_2.png"));
        assertEquals(2, instance.gatherInfo().getInt(PIXEL_COUNT));
    }

    @Test
    public void testRunTaskLeaf3() throws Exception {
        ImageGatherRunnable instance = new ImageGatherRunnable(get("leaf_3.png"));
        assertEquals(1, instance.gatherInfo().getInt(PIXEL_COUNT));
    }

    @Test
    public void testRunTaskLeaf4() throws Exception {
        ImageGatherRunnable instance = new ImageGatherRunnable(get("leaf_4.png"));
        assertEquals(4, instance.gatherInfo().getInt(PIXEL_COUNT));
    }

    private static String get(String img) {
        return new File("src/test/resources/".concat(img)).getAbsolutePath();
    }

}
