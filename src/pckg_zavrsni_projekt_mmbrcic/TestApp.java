package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;

public class TestApp {
    /**
     * main program
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                RegistrationAndLoginFrame loginFrame = new RegistrationAndLoginFrame();
            }
        });
    }
}
