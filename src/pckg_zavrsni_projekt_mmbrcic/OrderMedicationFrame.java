package pckg_zavrsni_projekt_mmbrcic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
public class OrderMedicationFrame extends JFrame {

    OrderMedicationPanel orderMedicationPanel;

    public OrderMedicationFrame() {
        super("Order medication");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        initializeComponents();
        layoutAll();
        activateOrderMedicationPanel();
        createMenu();

    }

    private void initializeComponents() {
        orderMedicationPanel = new OrderMedicationPanel();
        setJMenuBar(createMenu());

    }

    private void layoutAll() {
        setLayout(new BorderLayout());
        add(orderMedicationPanel, BorderLayout.CENTER);
    }

    /**
     * aktivacija panela, odnosno pomocu get Metode se dodaje ActionListener
     * za Exit i Confirm Button
     * Na Exit se frame zatvara
     * na Confirm ispisuje potvrdu o narudzbi
     */
    private void activateOrderMedicationPanel() {
        orderMedicationPanel.getExitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        orderMedicationPanel.getOrderButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Medicine successfully ordered", "CONFIRMATION", JOptionPane.PLAIN_MESSAGE);
                dispose();
            }
        });
    }

    private MenuBar createMenu() {
        return new MenuBar();
    }

    class MenuBar extends JMenuBar {

        JMenu menu;
        JMenuItem exportAsPdf;

        MenuBar() {
            initializeComponents();
            addComponentsToMenuBar();
            activateMenuBar();
        }

        void initializeComponents() {
            menu = new JMenu("Menu");
            exportAsPdf = new JMenuItem("Export as PDF");
        }

        void addComponentsToMenuBar() {
            menu.add(exportAsPdf);
            add(menu);
        }

        /**
         * aktivacija Menu Bara
         * Ima opciju za exportati text s JTextArea u PDF-u
         */
        void activateMenuBar() {
            exportAsPdf.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Document document = new Document();
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream("patientRecipe.pdf"));
                        document.open();
                        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
                        document.addTitle("PATIENT RECIPE");
                        document.addCreationDate();

                        Chunk chunk = new Chunk(orderMedicationPanel.getPatientInfoDetails().getText(), font);
                        document.add(new Paragraph(" "));
                        document.add(chunk);

                        document.close();
                        JOptionPane.showConfirmDialog(null, "Saving recipe...", "Saving status", JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE);
                    } catch (DocumentException ex) {
                        throw new RuntimeException(ex);
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }
            });
        }
    }
}
