package Entity;

import java.sql.Time;

public class Agenda {
    private int id;

    private int doctors_id;
    private String days_of_week;
    private Time time_start;
    private Time time_end;

    public Agenda() {
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

    public String getDays_of_week() {
        return days_of_week;
    }

    public void setDays_of_week(String days_of_week) {
        this.days_of_week = days_of_week;
    }

    public Time getTime_start() {
        return time_start;
    }

    public void setTime_start(Time time_start) {
        this.time_start = time_start;
    }

    public Time getTime_end() {
        return time_end;
    }

    public void setTime_end(Time time_end) {
        this.time_end = time_end;
    }
}
