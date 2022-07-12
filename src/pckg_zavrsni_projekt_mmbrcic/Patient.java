package pckg_zavrsni_projekt_mmbrcic;

import java.io.*;

public class Patient implements Serializable {
    /**
     * klasa Pacijent
     */
    private String patientName;
    private String patientSurname;
    private String patientDateOfBirth;
    private String patientEmailAddress;
    private String patientOIB;
    private String patientGender;
    private String patientDisease;

    /**
     *
     * @param name String ime pacijenta
     * @param surname String prezime pacijenta
     * @param birthDate String datum rodenja
     * @param email String email
     * @param OIB String OIB
     * @param gender String spol
     * @param disease String bolest
     */
    public Patient(String name, String surname, String birthDate, String email, String OIB, String gender, String disease) {
        this.patientName = name;
        this.patientSurname = surname;
        this.patientDateOfBirth = birthDate;
        this.patientEmailAddress = email;
        this.patientOIB = OIB;
        this.patientGender = gender;
        this.patientDisease = disease;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public String getPatientOIB() {
        return patientOIB;
    }

    @Override
    public String toString() {
        return "Info for the patient{" +
                "patientName: '" + patientName + '\'' + "\n" +
                ", patientSurname: '" + patientSurname + '\'' +  "\n" +
                ", patientDateOfBirth: '" + patientDateOfBirth + '\'' + "\n" +
                ", patientEmailAddress: '" + patientEmailAddress + '\'' + "\n" +
                ", patientOIB: " + patientOIB + "\n" +
                ", patientGender: '" + patientGender + '\'' + "\n" +
                ", patientDisease: '" + patientDisease + '\'' + "\n" +
                '}';
    }
}
