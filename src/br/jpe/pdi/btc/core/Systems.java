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

import java.security.AccessController;
import sun.awt.OSInfo;

/**
 * Utilities to work with the SO
 *
 * @author joaovperin
 */
public class Systems {

    public static boolean isWindows() {
        return getOs().equals(OSInfo.OSType.WINDOWS);
    }

    private static OSInfo.OSType getOs() {
        return AccessController.doPrivileged(OSInfo.getOSTypeAction());
    }

}
