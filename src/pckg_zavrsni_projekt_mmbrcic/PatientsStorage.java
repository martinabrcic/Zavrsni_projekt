package pckg_zavrsni_projekt_mmbrcic;

import java.io.*;
import java.util.TreeMap;

public class PatientsStorage implements Serializable {
    /**
     * Ova klasa predstavlja bazu podataka
     */
    private Patient patient;
    private String patientName;
    private String patientSurname;
    private String patientDateOfBirth;
    private String patientEmailAddress;
    private String patientOIB;
    private String patientGender;
    private String patientDisease;
    TreeMap<String, Patient> patientsTreeMap;

    /**
     * prikom kreiranja objekta pokrece se baza
     */
    public PatientsStorage() {

        loadDatabase();

    }

    /**
     * metoda kojom se dodaje pacijent u bazu
     * provjera postoji li vec pacijent s Key vrijednoscu OIB-a
     * @param OIB
     * @param patient
     */
    public void addPatientToTheBase(String OIB, Patient patient) {

        if (patientsTreeMap.containsKey(OIB)) {
            System.out.println("This patient is already in the base: " + patient.getPatientOIB());
            String firstName = patient.getPatientName();
            String lastName = patient.getPatientSurname();
            System.out.println("This patient is already in the base " + firstName + lastName);

        } else if(OIB.equals(null)) {
            return;

        } else {
            patientsTreeMap.put(OIB, patient);
            System.out.println("Added new patient");
            System.out.println("Patient: " + patient.getPatientName() + patient.getPatientSurname() + " with OIB " + OIB);
            savePatientToTheBinFile();
            System.out.println(patientsTreeMap);
        }

    }

    public void listAllPatients() {
        for (String patientOIB : patientsTreeMap.keySet()) {
            System.out.println("Patient OIB: " + patientOIB + "-" + patientsTreeMap.get(patientOIB));
        }
    }

    /**
     * Spremanje pacijenta u bin file
     */
    private void savePatientToTheBinFile() {

        File file = new File("registeredPatients.bin");
        System.out.println("Saving to the file " + file.getPath());
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(patientsTreeMap);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * ucitvanja iz bin filea
     * @return vraca TreeMapu
     */
    public TreeMap<String, Patient> loadDatabase() {
        File file = new File("registeredPatients.bin");
        System.out.println("Loading details from " + file.getPath());
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                patientsTreeMap = (TreeMap<String, Patient>) objectInputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            patientsTreeMap = new TreeMap<>();
        }
        return patientsTreeMap;
    }

    public TreeMap<String, Patient> getPatientsTreeMap() {
        return patientsTreeMap;
    }

    @Override
    public String toString() {
        return "PatientsStorage{" +
                "patient=" + patient +
                ", patientName='" + patientName + '\'' +
                ", patientSurname='" + patientSurname + '\'' +
                ", patientDateOfBirth='" + patientDateOfBirth + '\'' +
                ", patientEmailAddress='" + patientEmailAddress + '\'' +
                ", patientOIB=" + patientOIB +
                ", patientGender='" + patientGender + '\'' +
                ", patientDisease='" + patientDisease + '\'' +
                ", patientsTreeMap=" + patientsTreeMap +
                '}';
    }

}
