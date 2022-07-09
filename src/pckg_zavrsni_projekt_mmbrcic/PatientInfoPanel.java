package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientInfoPanel extends JPanel {
    /**
     * Glavni panel preko kojeg se osoba registrira
     * sadrzi atribute povezane s pacijentom
     */
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField dateOfBirthField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JTextField OIBField;
    private JRadioButton radioMale;
    private JRadioButton radioFemale;
    private ButtonGroup genderGroup;
    private JComboBox diseaseBox;
    private JButton confirmButton;
    private JButton cancelButton;
    private JButton exitButton;
    private PatientInfoListener patientInfoListener;

    public PatientInfoPanel(PatientsStorage patientsStorage) {

        Dimension dimension = getPreferredSize();
        dimension.width = 400;
        setPreferredSize(dimension);

        initializeComponents();
        layoutComponents();
        setBorders();
        activatePatientInfoPanel();
    }

    private void initializeComponents() {
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        dateOfBirthField = new JTextField(10);
        emailField = new JTextField(10);
        OIBField = new JTextField(11);
        passwordField = new JPasswordField(10);

        radioMale = new JRadioButton("Male");
        radioFemale = new JRadioButton("Female");
        radioMale.setSelected(true);
        genderGroup = new ButtonGroup();
        genderGroup.add(radioMale);
        genderGroup.add(radioFemale);

        diseaseBox = new JComboBox();
        DefaultComboBoxModel<String> diseaseComboModel = new DefaultComboBoxModel<>();
        diseaseComboModel.addElement("Headache");
        diseaseComboModel.addElement("Flu");
        diseaseComboModel.addElement("Allergy");
        diseaseComboModel.addElement("Arrhythmia");
        diseaseComboModel.addElement("Insomnia");
        diseaseComboModel.addElement("Burns");
        diseaseComboModel.addElement("Vertigo");
        diseaseComboModel.addElement("Sore throat");
        diseaseBox.setModel(diseaseComboModel);

        confirmButton = new JButton("Confirm");
        cancelButton = new JButton("Cancel");
        exitButton = new JButton("Exit");

    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 15, 10, 0);
        add(new JLabel("Name: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(nameField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Surname: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(surnameField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Date of birth: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(dateOfBirthField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("OIB: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(OIBField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Email address: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(emailField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Password: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(passwordField, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Gender: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(radioMale, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(radioFemale, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(new JLabel("Disease: "), gridBagConstraints);
        gridBagConstraints.gridx++;
        add(diseaseBox, gridBagConstraints);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        add(confirmButton, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(cancelButton, gridBagConstraints);
        gridBagConstraints.gridx++;
        add(exitButton, gridBagConstraints);
    }

    private void setBorders() {
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("Registration form");

        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);

    }

    /**
     * Metoda kojom se na klik Confirm buttona provjeravaju registracijska polja
     *
     */
    private void activatePatientInfoPanel() {

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String surname = surnameField.getText();
                String email = emailField.getText();
                String birthDate = dateOfBirthField.getText();
                char[] password = passwordField.getPassword();
                String OIB = OIBField.getText();


                if (!checkValues(name) || !checkValues(surname) || !checkOIB(OIB) || !checkEmailAddress(email) || !checkPassword(String.valueOf(password))){
                    return;
                }

                if (nameField.getText().isEmpty() || surnameField.getText().isEmpty() || emailField.getText().isEmpty() || dateOfBirthField.getText().isEmpty() || OIBField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                    System.out.println("User should fill all the details!");
                    JOptionPane.showMessageDialog(PatientInfoPanel.this, "Please fill all the fields to continue!", "WARNING", JOptionPane.WARNING_MESSAGE);
                }

                String disease = (String) diseaseBox.getSelectedItem();
                String gender = null;
                if (radioMale.isSelected()) {
                    gender = "Male";
                } else if (radioFemale.isSelected()) {
                    gender = "Female";
                }

                PatientInfoPanelEvent patientInfoPanelEvent = new PatientInfoPanelEvent(PatientInfoPanel.this, name, surname, birthDate, email, OIB, gender, disease);


                patientInfoListener.patientInfoPanelEventOccurred(patientInfoPanelEvent);
                nameField.setText("");
                surnameField.setText("");
                emailField.setText("");
                dateOfBirthField.setText("");
                OIBField.setText("");
                passwordField.setText("");

            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameField.setText("");
                surnameField.setText("");
                dateOfBirthField.setText("");
                emailField.setText("");
                OIBField.setText("");
            }
        });
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public void setExitButton(JButton exitButton) {
        this.exitButton = exitButton;
    }

    public void setPatientInfoListener(PatientInfoListener patientInfoListener) {
        this.patientInfoListener = patientInfoListener;
    }

    public JTextField getOIBField() {
        return OIBField;
    }

    /**
     * Provjera sadrze li uneseno ime i prezime broj
     * @param word String
     * @return
     */
    private boolean checkValues(String word) {
        char[] wordChar = word.toCharArray();
        for (char ch : wordChar) {
            if (Character.isDigit(ch)) {
                JOptionPane.showMessageDialog(PatientInfoPanel.this, "Please enter String value", "WARNING", JOptionPane.WARNING_MESSAGE);
                System.out.println("Please enter String value");
                return false;
            }
        }
        return true;
    }

    /**
     * Validacija email adrese, sadrzi li @
     * @param emailAddress String
     * @return
     */
    private boolean checkEmailAddress(String emailAddress) {
        if (!emailAddress.contains("@")) {
            JOptionPane.showMessageDialog(PatientInfoPanel.this, "Email address should contain @!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("Email address should contain @!");
            return false;
        }
        return true;
    }

    /**
     * Validacija OIB-a
     * @param OIB numericka reprezentacija Stringa
     * @return
     */
    public boolean checkOIB(String OIB) {
        if (OIBField.getText().length() != 11) {
            JOptionPane.showMessageDialog(PatientInfoPanel.this, "OIB length should be 11 num values!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("OIB length should be 11 num values!");
            return false;
        }
       try{
           Long longValue = Long.parseLong(OIBField.getText());
           return true;
       } catch (NumberFormatException numberFormatException) {
           JOptionPane.showMessageDialog(PatientInfoPanel.this, "OIB length should be num value!", "WARNING", JOptionPane.WARNING_MESSAGE);
           System.out.println("OIB length should be num value!");
           return false;
       }
    }

    public boolean checkPassword(String password) {
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(PatientInfoPanel.this, "Password should contain minimum 8 characters!", "WARNING", JOptionPane.WARNING_MESSAGE);
            System.out.println("Password should contain minimum 8 characters!");
            return false;
        }
        return true;
    }

}