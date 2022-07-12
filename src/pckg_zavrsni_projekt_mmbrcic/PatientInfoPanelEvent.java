package pckg_zavrsni_projekt_mmbrcic;

import java.util.EventObject;

public class PatientInfoPanelEvent extends EventObject {

    private String patientName;
    private String patientSurname;
    private String patientDateOfBirth;
    private String patientEmailAddress;
    private String patientOIB;
    private String patientGender;
    private String patientDisease;
    private char[] password;

    public PatientInfoPanelEvent(Object source) {
        super(source);
    }


    public PatientInfoPanelEvent(Object source, String name, String surname, String birthDate, String email, String OIB, String gender, String disease, char[] password) {
        super(source);
        this.patientName = name;
        this.patientSurname = surname;
        this.patientDateOfBirth = birthDate;
        this.patientEmailAddress = email;
        this.patientOIB = OIB;
        this.patientGender = gender;
        this.patientDisease = disease;
        this.password = password;
    }

    public String getPatientName() {
        return patientName;
    }
    public String getPatientSurname() {
        return patientSurname;
    }

    public String getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public String getPatientEmailAddress() {
        return patientEmailAddress;
    }

    public String getPatientOIB() {
        return patientOIB;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public String getPatientDisease() {
        return patientDisease;
    }


    @Override
    public String toString() {
        return "Info for the patient{" +
                "patientName: '" + patientName + '\'' + "\n" +
                ", patientSurname: '" + patientSurname + '\'' + "\n" +
                ", patientDateOfBirth: '" + patientDateOfBirth + '\'' + "\n" +
                ", patientEmailAddress: '" + patientEmailAddress + '\'' + "\n" +
                ", patientOIB: " + patientOIB + "\n" +
                ", patientGender: '" + patientGender + '\'' + "\n" +
                ", patientDisease: '" + patientDisease + '\'' + "\n" +
                '}';
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
}
