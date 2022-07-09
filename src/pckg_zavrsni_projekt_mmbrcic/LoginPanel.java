package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPanel extends JPanel {
    /**
     * Login panel pomocu kojeg se ulogira vec postojeci korisnik
     * Ako nije registriran, prikazuje se poruka
     */
    private JButton loginButton;
    private JTextField OIBField;
    private JPasswordField passwordField;
    private PatientInfoPanelEvent patientInfoPanelEvent;
    private PatientInfoListener patientInfoListener;
    public LoginPanel() {
        Dimension dimension = getPreferredSize();
        dimension.width = 300;
        setPreferredSize(dimension);


        initializeComponents();
        layoutComponents();
        setBorders();
        //activateLoginPanel();
    }

    private void initializeComponents() {
        loginButton = new JButton("Login");
        OIBField = new JTextField(10);
        passwordField = new JPasswordField(10);
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 15, 3, 10);
        add(new JLabel("OIB: "));

        gridBagConstraints.gridx++;
        add(OIBField, gridBagConstraints);

        gridBagConstraints.gridy++;
        gridBagConstraints.gridx = 0;
        add(new JLabel("Password: "), gridBagConstraints);

        gridBagConstraints.gridx++;
        add(passwordField, gridBagConstraints);

        gridBagConstraints.gridy++;

        add(loginButton, gridBagConstraints);

    }

    private void setBorders() {
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("Login form");

        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);

    }

    /**
     * Boolean metoda kojem se provjerava valjanost unesenog OIB-a
     * Odgovara li duljina 11 znamenki i jel unesen broj
     * @param OIB
     * @return
     */
    public boolean checkOIB(String OIB) {
        if (OIBField.getText().length() != 11) {
            JOptionPane.showMessageDialog(LoginPanel.this, "OIB length should be 11 num values!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("OIB length should be 11 num values!");
            return false;
        }
        try{
            Long longValue = Long.parseLong(OIBField.getText());
            return true;
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(LoginPanel.this, "OIB length should be num value!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("OIB length should be num value!");
            return false;
        }
    }

    /**
     * metoda kojom se provjerava duljina lozinke
     * @param password prikazana u nizu charactera
     * @return
     */

    public boolean checkPassword(char[] password) {
        if (password.length < 8) {
            JOptionPane.showMessageDialog(LoginPanel.this, "Password should contain minimum 8 characters!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("Password should contain minimum 8 characters!");
            return false;
        }
        return true;
    }

    public JButton getLoginButton() {
        return loginButton;
    }


    public JTextField getOIBField() {
        return OIBField;
    }


    public JPasswordField getPasswordField() {
        return passwordField;
    }


}
