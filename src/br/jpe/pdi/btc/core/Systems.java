/*
 * BotanicChallenge
 * CopyRight Rech Informática Ltda. Todos os direitos reservados.
 */
package br.jpe.pdi.btc.core;

import java.security.AccessController;
import sun.awt.OSInfo;

/**
 * Descrição da classe.
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
