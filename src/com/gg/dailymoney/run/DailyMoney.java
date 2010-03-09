package com.gg.dailymoney.run;

import com.gg.dailymoney.gui.ActivateJDialog;
import com.gg.dailymoney.gui.StartJDialog;
import com.gg.dailymoney.gui.MainJFrame;
import com.gg.dailymoney.gui.LoginJDialog;
import com.gg.dailymoney.init.DataManager;
import com.gg.dailymoney.init.Hardware;
import java.awt.EventQueue;
import javax.swing.JOptionPane;

/**
 *
 * @author German Garcia
 */
public class DailyMoney {

    public static void main(String args[]) {
        if (!compatible()) {
            System.exit(1);
        }

        final StartJDialog splash = new StartJDialog(null, true);
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                splash.setVisible(true);
            }
        });

        verifyLicenseAuthorization();
        String messages[] = {
            "Cargando aplicacion...", "Conectando la base de datos...", "Preparando las librerias moviles...", "Iniciando..."
        };

        for (int time = 0; time < 100; time++) {
            splash.setProgress(time);
            splash.setMessage(messages[time / 25]);
            try {
                Thread.sleep(50L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                splash.dispose();
                LoginJDialog dialog = new LoginJDialog(null, true);
                dialog.setUndecorated(true);
                dialog.setVisible(true);
                if (dialog.isOk()) {
                    MainJFrame window = new MainJFrame();
                    window.setVisible(true);
                    window.setExtendedState(6);
                }
            }
        });
    }

    private static boolean compatible() {
        String os_arch = System.getProperty("os.arch");
        String os_name = System.getProperty("os.name");
        String user_name = System.getProperty("user.name");
        String hw_id = Hardware.getHwId();
        String hd_id = Hardware.getHdId();
        if (os_arch == null || os_name == null || user_name == null || hw_id == null || hd_id == null) {
            JOptionPane.showMessageDialog(null, "Esta aplicaci\363n no se puede ejecutar en este equipo, debido a que no cumple los requisitos m\355nimos de hardware.", "Error de inicializaci\363n", 0);
            return false;
        } else {
            return true;
        }
    }

    private static boolean registered() {
        DataManager data = new DataManager();
        return data.existLicense();
    }

    private static boolean validLicense() {
        DataManager data = new DataManager();
        return data.isLicenseCorrect();
    }

    private static void verifyLicenseAuthorization() {
        if (!registered()) {
            JOptionPane.showMessageDialog(null, "Es necesario activar su software para poder usarlo.");
            ActivateJDialog dialog = new ActivateJDialog(null, true);
            dialog.setVisible(true);
            if (!registered()) {
                JOptionPane.showMessageDialog(null, "Su software no ha sido activado.", "Error de activaci\363n", 0);
                System.exit(1);
            }
        } else if (!validLicense()) {
            int ans = JOptionPane.showConfirmDialog(null, "Usted no esta autorizado para utilizar este software, la licencia no es v\341lida\no esta es una copia ilegal. \277Desea activar su software para poder usarlo?", "Licencia no v\341lida.", 0);
            if (ans == 0) {
                ActivateJDialog dialog = new ActivateJDialog(null, true);
                dialog.setVisible(true);
            }
            if (!validLicense()) {
                JOptionPane.showMessageDialog(null, "Su software no ha sido activado.", "Error de activaci\363n", 0);
                System.exit(1);
            }
        }
    }
}
