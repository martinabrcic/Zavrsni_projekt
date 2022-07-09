package pckg_zavrsni_projekt_mmbrcic;

import java.util.EventListener;

public interface PatientInfoListener extends EventListener {

    void patientInfoPanelEventOccurred(PatientInfoPanelEvent patientInfoPanelEvent);
}
