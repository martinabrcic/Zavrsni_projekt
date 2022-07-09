package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderMedicationPanel extends JPanel {

    private JButton orderButton;
    private JButton exitButton;
    private JTextArea patientInfoDetails;
    private JComboBox medicationBox;
    private JScrollPane scrollPane;

    PatientInfoPanelEvent patientInfoPanelEvent;


    public OrderMedicationPanel(){
        Dimension dimension = getPreferredSize();
        dimension.width = 500;
        dimension.height = 400;
        setPreferredSize(dimension);
        initializeComponents();
        layoutComponents();
        setBorders();
        activateMedicationPanel();

    }
    private void initializeComponents() {
        orderButton = new JButton("Order");
        exitButton = new JButton("Exit");
        patientInfoDetails = new JTextArea(15, 20);
        scrollPane = new JScrollPane(patientInfoDetails, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        patientInfoDetails.setVisible(true);
        patientInfoDetails.setEditable(false);
        medicationBox = new JComboBox();
        DefaultComboBoxModel<String> medicationComboModel = new DefaultComboBoxModel<>();
        medicationComboModel.addElement("Gastal");
        medicationComboModel.addElement("Normabel");
        medicationComboModel.addElement("Voltaren");
        medicationComboModel.addElement("Andol");
        medicationComboModel.addElement("Klavocin");
        medicationComboModel.addElement("Lekadol");
        medicationComboModel.addElement("Ibuprofen");
        medicationComboModel.addElement("Lupocet");
        medicationBox.setModel(medicationComboModel);
    }
    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(10, 5, 5, 5);
        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        add(medicationBox, gridBagConstraints);

        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx++;
        add(scrollPane, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy++;
        gridBagConstraints.insets = new Insets(0,20,0,20);
        add(orderButton, gridBagConstraints);

        gridBagConstraints.gridx++;
        add(exitButton,gridBagConstraints);

    }
    private void setBorders() {
        Border outer = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        Border inner = BorderFactory.createTitledBorder("Order medication");

        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);

    }

    /**
     * metoda kojom se odabere lijek, kada se jedan odabere, drugi se disable
     */
    public void activateMedicationPanel() {

        medicationBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setInfoArea("Ordered medicine: "+ medicationBox.getSelectedItem());
                medicationBox.setEnabled(false);
            }
        });
    }

    public JButton getOrderButton() {
        return orderButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTextArea getPatientInfoDetails() {
        return patientInfoDetails;
    }

    public void setInfoArea(String item) {
        patientInfoDetails.append(item);
        patientInfoDetails.append("\n");
    }


}
