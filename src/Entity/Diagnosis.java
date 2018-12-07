package Entity;

public class Diagnosis {
    private int id;
    private String treatment;
    private String remedies;
    private String observation;
    private int diseasesId;
    private int consultationId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getRemedies() {
        return remedies;
    }

    public void setRemedies(String remedies) {
        this.remedies = remedies;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public int getDiseasesId() {
        return diseasesId;
    }

    public void setDiseasesId(int diseasesId) {
        this.diseasesId = diseasesId;
    }

    public int getConsultationId() {
        return consultationId;
    }

    public void setConsultationId(int consultationId) {
        this.consultationId = consultationId;
    }
}
