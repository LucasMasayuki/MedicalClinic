package Entity;

public class Exerts {
    private int id;
    private int specialties_id;
    private int doctors_id;

    public Exerts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctors_id() {
        return doctors_id;
    }

    public void setDoctors_id(int doctors_id) {
        this.doctors_id = doctors_id;
    }

    public int getSpecialties_id() {
        return specialties_id;
    }

    public void setSpecialties_id(int specialties_id) {
        this.specialties_id = specialties_id;
    }
}
