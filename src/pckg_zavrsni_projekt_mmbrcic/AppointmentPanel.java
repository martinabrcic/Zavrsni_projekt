package pckg_zavrsni_projekt_mmbrcic;


import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

public class AppointmentPanel extends JPanel {

    /**
     * Na ovom panelu nalaze se JButtons, TextArea, ScrollPane, ComboBox,
     * Jlist
     * U String nizu su spremljeni termini koje pacijent moze rezervirati
     */
    private JButton confirmButton;
    private JButton exitButton;
    private JTextArea infoTextArea;
    private JScrollPane scrollPane;
    private JSpinner dateSpinner;
    private JComboBox<String> workingDays;
    private JList<String> timeListMonday;
    private JList<String> timeListWednesday;
    private JList<String> timeListFriday;
    String[] timetableMonday = {"10:00", "11:00", "12:00", "13:00"};
    String[] timetableWednesday = {"14:30", "15:00", "17:00", "18:30"};
    String[] timetableFriday = {"11:30", "13:30", "14:00", "15:00"};
    private PatientInfoPanelEvent patientInfoPanelEvent;


    public AppointmentPanel() {
        Dimension dimension = getPreferredSize();
        dimension.height = 450;
        setPreferredSize(dimension);
        initializeComponents();
        layoutComponents();
        setBorders();
        activateAppointmentPanel();
    }

    private void initializeComponents() {
        confirmButton = new JButton("Confirm");
        exitButton = new JButton("Exit");
        infoTextArea = new JTextArea();
        infoTextArea.setPreferredSize(new Dimension(300, 300));
        infoTextArea.setVisible(true);
        infoTextArea.setEditable(true);
        scrollPane = new JScrollPane(infoTextArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        add(scrollPane);

        Date today = new Date();
        dateSpinner = new JSpinner(new SpinnerDateModel(today, null, null, Calendar.DAY_OF_WEEK_IN_MONTH));
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(dateSpinner, "HH:mm dd/MM/yyyy");
        dateSpinner.setEditor(dateEditor);
        add(dateSpinner);

        dateSpinner.setVisible(true);

        workingDays = new JComboBox<String>();
        DefaultComboBoxModel<String> workingComboModel = new DefaultComboBoxModel<>();
        workingComboModel.addElement("Monday");
        workingComboModel.addElement("Wednesday");
        workingComboModel.addElement("Friday");
        workingDays.setModel(workingComboModel);


        timeListMonday = new JList<>(timetableMonday);
        timeListMonday.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        timeListWednesday = new JList<>(timetableWednesday);
        timeListWednesday.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        timeListFriday = new JList<>(timetableFriday);
        timeListFriday.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
        gridBagConstraints.insets = new Insets(10, 5, 5, 5);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        add(dateSpinner, gridBagConstraints);

        gridBagConstraints.gridy++;
        add(workingDays, gridBagConstraints);

        gridBagConstraints.gridy++;
        add(timeListMonday, gridBagConstraints);
        add(timeListWednesday, gridBagConstraints);
        add(timeListFriday, gridBagConstraints);

        gridBagConstraints.gridheight = 3;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx++;
        gridBagConstraints.gridy = 0;
        add(scrollPane, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        add(confirmButton, gridBagConstraints);

        gridBagConstraints.gridx++;
        add(exitButton, gridBagConstraints);


    }

    private void setBorders() {
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 5);     //specijaliziran da kreira nove bordere bez new, baziran na Factory pattern
        Border inner = BorderFactory.createTitledBorder("Book a meeting");

        Border border = BorderFactory.createCompoundBorder(outer, inner);
        setBorder(border);
    }

    public void activateAppointmentPanel() {
        /**
         * Action Listener pokrece se na neku akciju, npr klikn na JButton
         * u pozadini se nesto odvija, a moze se i prikazati u Framu, tj na Panelu
         * u prvom slucaju prikazuje timetable ovisno o kojem se danu radi
         * u drugom slucaju, ako je termin odabran, druge sakrije od korisnika i odabrani prikaze
         * u JTextArea
         */
        workingDays.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (workingDays.getModel().getSelectedItem().equals(workingDays.getItemAt(0))) {
                    timeListMonday.setVisible(true);
                    timeListWednesday.setVisible(false);
                    timeListFriday.setVisible(false);
                } else if (workingDays.getModel().getSelectedItem().equals(workingDays.getItemAt(1))) {
                    timeListWednesday.setVisible(true);
                    timeListMonday.setVisible(false);
                    timeListFriday.setVisible(false);
                } else if (workingDays.getModel().getSelectedItem().equals(workingDays.getItemAt(2))) {
                    timeListFriday.setVisible(true);
                    timeListMonday.setVisible(false);
                    timeListWednesday.setVisible(false);
                } else {
                    timeListFriday.setVisible(false);
                    timeListWednesday.setVisible(false);
                    timeListMonday.setVisible(false);
                }

            }
        });

        timeListMonday.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!timeListMonday.getValueIsAdjusting()) {
                    timeSelection(timeListMonday, timetableMonday);
                    timeListMonday.setEnabled(false);
                }

            }
        });
        timeListWednesday.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!timeListWednesday.getValueIsAdjusting()) {
                    timeSelection(timeListWednesday, timetableWednesday);
                    timeListWednesday.setEnabled(false);
                }

            }
        });
        timeListFriday.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!timeListFriday.getValueIsAdjusting()) {
                    timeSelection(timeListFriday, timetableFriday);
                    timeListFriday.setEnabled(false);
                }
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AppointmentPanel.this, "Scheduled appointment on " + workingDays.getModel().getSelectedItem() + " for the " +
                        "patient");

            }

        });

    }

    /**
     *
     * @param stringJList predstavlja Listu u kojoj se nalaze dani
     * @param arr predstavlja niz termina
     * Uz pomoc ove metode se prikazuje na TextArea
     */
    private void timeSelection(JList<String> stringJList, String[] arr) {
        if (stringJList.getSelectedIndex() != -1) {
            System.out.println("Current selection " + arr[stringJList.getSelectedIndex()]);
            setInfoArea("You booked this termin: " + stringJList.getSelectedValue());
        } else {
            System.out.println("Please choose time!");
        }
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JTextArea getInfoTextArea() {
        return infoTextArea;
    }


    public void setInfoArea(String item) {
        infoTextArea.append(item);
        infoTextArea.append("\n");
    }


}
