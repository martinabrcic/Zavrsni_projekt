package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistrationAndLoginFrame extends JFrame {
    /**
     * Glavni frame koji sadrzi Login i PatientInfo (Registration) panel
     */
    private LoginPanel loginPanel;

    private PatientInfoPanel patientInfoPanel;
    PatientsStorage patientsStorage;

    public RegistrationAndLoginFrame() {
        super("Login window");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        patientsStorage = new PatientsStorage();
        initializeComponents();
        layoutAll();
        activatePatientInfoFrame();

    }

    private void initializeComponents() {
        patientInfoPanel = new PatientInfoPanel(patientsStorage);
        loginPanel = new LoginPanel();
    }

    private void layoutAll() {
        setLayout(new BorderLayout());
        add(patientInfoPanel, BorderLayout.EAST);
        add(loginPanel, BorderLayout.WEST);
    }

    /**
     * Glavna metoda koja se aktivira kada se ispune sva registracijska
     * polja
     * odabire se nastavak nakon provjere i registracije
     * sto korisnik iduce zeli: naruciti lijekove ili rezervirati termin
     * kod doktora
     */
    private void activatePatientInfoFrame() {
        patientInfoPanel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        patientInfoPanel.setPatientInfoListener(new PatientInfoListener() {
            @Override
            public void patientInfoPanelEventOccurred(PatientInfoPanelEvent patientInfoPanelEvent) {
                System.out.println(patientInfoPanelEvent);
                String name = patientInfoPanelEvent.getPatientName();
                String surname = patientInfoPanelEvent.getPatientSurname();
                String OIB = patientInfoPanelEvent.getPatientOIB();
                String gender = patientInfoPanelEvent.getPatientGender();
                String birthDate = patientInfoPanelEvent.getPatientDateOfBirth();
                String disease = patientInfoPanelEvent.getPatientDisease();
                String email = patientInfoPanelEvent.getPatientEmailAddress();

                Patient patient = new Patient(name, surname, birthDate, email, OIB, gender, disease);
//
                if (patient.getPatientName().equals(null)) {
                    return;
                } else if (patientsStorage.getPatientsTreeMap().containsKey(patientInfoPanel.getOIBField().getText())) {

                    System.out.println(patientInfoPanel.getOIBField().getText());
                    JOptionPane.showMessageDialog(null, "Patient with entered OIB already exists in the system!", "Existing patient", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "Is entered data correct?", "Saving status", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    if (result == JOptionPane.NO_OPTION) {
                        return;
                    } else {
                        patientsStorage.addPatientToTheBase(OIB, patient);
                    }
                }

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(name);
                stringBuilder.append(surname);
                stringBuilder.append(OIB);
                stringBuilder.append(gender);
                stringBuilder.append(birthDate);
                stringBuilder.append(disease);
                stringBuilder.append(email);

                String[] choices = {"Visit doctor", "Order medication"};
                Object defaultChoice = choices[1];
                int option = JOptionPane.showOptionDialog(RegistrationAndLoginFrame.this, "Please choose what do you want to do", "Option window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);

                if (option == JOptionPane.OK_OPTION) {
                    AppointmentFrame appointmentFrame = new AppointmentFrame();
                    appointmentFrame.setVisible(true);
                    appointmentFrame.getAppointmentPanel().getInfoTextArea().setText(patientInfoPanelEvent.toString());
                    dispose();

                } else {
                    OrderMedicationFrame orderMedicationFrame = new OrderMedicationFrame();
                    orderMedicationFrame.setVisible(true);
                    orderMedicationFrame.orderMedicationPanel.getPatientInfoDetails().setText(patientInfoPanelEvent.toString());
                    dispose();
                }
            }
        });
        /**
         * metoda za aktivaciju loginpanela
         * provjera polja
         * odluka o nastavku u app
         */

        loginPanel.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Patients Map: " + patientsStorage);

                if (!loginPanel.checkOIB(loginPanel.getOIBField().getText()) || !loginPanel.checkPassword(loginPanel.getPasswordField().getPassword())) {
                    System.out.println(loginPanel.getOIBField().getText());
                    return;
                } else if (loginPanel.getOIBField().getText().equals("") || loginPanel.getPasswordField().getPassword().length <= 0) {
                    System.out.println("Some of the fields are empty!");
                    JOptionPane.showMessageDialog(RegistrationAndLoginFrame.this, "Please fill OIB and password field to continue!", "WARNING", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (patientsStorage.patientsTreeMap.containsKey(loginPanel.getOIBField().getText())) {
                    System.out.println("This patient is already in the base");
                    String[] choices = {"Visit doctor", "Order medication"};
                    Object defaultChoice = choices[1];
                    int option = JOptionPane.showOptionDialog(null, "Please choose what do you want to do", "Option window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, defaultChoice);
                    Patient patient = patientsStorage.patientsTreeMap.get(loginPanel.getOIBField().getText());
                    if (option == JOptionPane.OK_OPTION) {
                        AppointmentFrame appointmentFrame = new AppointmentFrame();
                        appointmentFrame.setVisible(true);
                        appointmentFrame.getAppointmentPanel().getInfoTextArea().setText(patient.toString());

                    } else {
                        OrderMedicationFrame orderMedicationFrame = new OrderMedicationFrame();
                        orderMedicationFrame.setVisible(true);
                        orderMedicationFrame.orderMedicationPanel.getPatientInfoDetails().setText(patient.toString());
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, " Patient with entered OIB doesn't exist in the system! Please fill the registration form!", "WARNING", JOptionPane.QUESTION_MESSAGE);
                    System.out.println("Please fill the registration form!");
                    return;
                }
            }
        });
    }

}
