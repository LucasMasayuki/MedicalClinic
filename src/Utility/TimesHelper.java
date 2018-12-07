package Utility;

import java.sql.Time;

public class TimesHelper {
    private Time start;

    private Time end;

    public TimesHelper(String start, String end) {
        this.start = Time.valueOf(start);
        this.end = Time.valueOf(end);
    }

    public Time getEnd() {
        return end;
    }

    public Time getStart() {
        return start;
    }
}
