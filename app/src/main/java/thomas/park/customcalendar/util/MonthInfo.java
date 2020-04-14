package thomas.park.customcalendar.util;


import androidx.annotation.Nullable;

import java.util.Vector;

class MonthInfo {

    private String today;
    @Nullable private Vector<Schedule> schedules = null;

    public MonthInfo() {
    }

    public MonthInfo(String today, @Nullable Vector<Schedule> schedules) {
        this.today = today;
        this.schedules = schedules;
    }

    public String getToday() {
        return today;
    }

    public void setToday(String today) {
        this.today = today;
    }

    @Nullable
    public Vector<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(@Nullable Vector<Schedule> schedules) {
        this.schedules = schedules;
    }
}