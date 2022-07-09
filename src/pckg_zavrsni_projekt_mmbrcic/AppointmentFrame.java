package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/** Klasa koja prosiruje JFrame i predstavlja Frame iz Swinga koji je povezan s istoimenim
 * panelom
 * Preko ovog framea korisnik moze zakazati sastanak i spremiti potvrdu u neki file.
 */
public class AppointmentFrame extends JFrame {

    /**
     * Definira se izgled framea, aktivira se appointment panel
     */
    private AppointmentPanel appointmentPanel;
    private JFileChooser chooser;
    private PatientInfoPanelEvent patientInfoPanelEvent;


    public AppointmentFrame() {

        super("Appointment Frame");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        initializeComponents();
        layoutAll();
        activateAppointmentPanel();
    }

    private void initializeComponents() {
        appointmentPanel = new AppointmentPanel();
        setJMenuBar(createMenu());
        chooser = new JFileChooser();
    }
    private void layoutAll() {
        setLayout(new BorderLayout());
        add(appointmentPanel, BorderLayout.CENTER);
    }

    private void activateAppointmentPanel() {
        appointmentPanel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        appointmentPanel.getConfirmButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public AppointmentPanel getAppointmentPanel() {
        return appointmentPanel;
    }

    public PatientInfoPanelEvent getPatientInfoPanelEvent() {
        return patientInfoPanelEvent;
    }

    public void setPatientInfoPanelEvent(PatientInfoPanelEvent patientInfoPanelEvent) {
        this.patientInfoPanelEvent = patientInfoPanelEvent;
    }

    private MenuBar createMenu() {
        return new MenuBar();
    }

    /**
     * Unutarnja klasa koja sadrzi Menu za spremanje ili otvaranje neke datoteke
     */
    class MenuBar extends JMenuBar {

        JMenu menu;
        JMenuItem openFile;
        JMenuItem save2File;
        JMenuItem printConfirmation;

        MenuBar() {
            initializeComponents();
            addComponentsToMenuBar();
            activateMenuBar();
        }

        void initializeComponents() {
            menu = new JMenu("Menu");
            openFile = new JMenuItem("Open file");
            save2File = new JMenuItem("Save to file");
            printConfirmation = new JMenuItem("Print confirmation");
        }
        void addComponentsToMenuBar() {
            menu.add(openFile);
            menu.add(save2File);
            add(menu);
        }

        /**
         * Metoda za spremanje texta iz text area u datoteku i za otvaranje
         * datoteke
         * @throws FileNotFoundException
         * @throws IOException
         */
        void activateMenuBar() {
            save2File.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int val = chooser.showSaveDialog(null);
                    if(val == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();
                        System.out.println(file.getPath());
                        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file))) {
                            bufferedWriter.write(appointmentPanel.getInfoTextArea().getText());
                            bufferedWriter.flush();
                            bufferedWriter.close();
                            JOptionPane.showConfirmDialog(null, "Savin customers data...", "Saving status", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                        } catch (FileNotFoundException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex){
                            ex.printStackTrace();
                        }
                    }
                }
            });
            openFile.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String filePath = "src/Zavrsni_projekt";
                    File userDirectory = new File(filePath);
                    chooser = new JFileChooser(userDirectory);
                    int result = chooser.showOpenDialog(AppointmentFrame.this);
                    if(result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = chooser.getSelectedFile();
                        System.out.println("Selected file " + selectedFile.getAbsoluteFile());
                        try {
                            FileReader fileReader = new FileReader(selectedFile);
                            appointmentPanel.getInfoTextArea().read(fileReader, selectedFile);
                            fileReader.close();
                        } catch (FileNotFoundException ex) {
                            throw new RuntimeException(ex);
                        } catch (IOException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                }
            });
        }
    }
}
