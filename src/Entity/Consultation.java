package Entity;

import java.sql.Time;

public class Consultation {
    private int id;

    private int doctors_id;
    private int patients_id;
    private int specialties_id;
    private Time date;
    private Time start_at;
    private Time end_at;
    private boolean paid;
    private float amount_paid;
    private String payment_method;

    public Consultation() {
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

    public int getPatients_id() {
        return patients_id;
    }

    public void setPatients_id(int patients_id) {
        this.patients_id = patients_id;
    }

    public int getSpecialties_id() {
        return specialties_id;
    }

    public void setSpecialties_id(int specialties_id) {
        this.specialties_id = specialties_id;
    }

    public Time getDate() {
        return date;
    }

    public void setDate(Time date) {
        this.date = date;
    }

    public Time getStart_at() {
        return start_at;
    }

    public void setStart_at(Time start_at) {
        this.start_at = start_at;
    }

    public Time getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Time end_at) {
        this.end_at = end_at;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public float getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(float amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
