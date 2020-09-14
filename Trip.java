package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.*;

/*
Input:
Driver Dan
Driver Lauren
Driver Kumi
Trip Dan 07:15 07:45 17.3 -> 34.6/H * 0.6(30/(30 + 20)) +
Trip Dan 06:12 06:32 21.8 -> 65.4/H * 0.4  = 47
Trip Lauren 12:01 13:16 42.0

Output:
Lauren: 42 miles @ 34 mph
Dan: 39 miles @ 47 mph
Kumi: 0 miles
*/

public class Trip {
    //for each trip:
    //it must has a name, startTime and endTime, and the average speed;
    private double distance;
    //the int[] time take four parameters: startTime in Hour, startTime in Mins, endTime in Hour, endTime in Mins
    private int[] time;
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getDistance() {
        return this.distance;
    }
    public void setTime(int[] time) {
        this.time = time;
    }
    public int[] getTime() {
        return this.time;
    }
    public Trip(int[] time, double distance) {
        this.time = time;
        this.distance = distance;
    }
    //Write a function which utilize the Calendar class to parse the string "time" into following parameters
    //double "time" in hours which served for the future calculation of average speed;
    public double getTime (String str1, String str2) throws ParseException {
        double curTime;
        SimpleDateFormat start = new SimpleDateFormat("HH:mm");
        SimpleDateFormat end = new SimpleDateFormat("HH:mm");
        Date startT = start.parse(str1);
        Date endT = end.parse(str2);
        Calendar calOfStart = Calendar.getInstance();
        calOfStart.setTime(startT);
        Calendar calOfEnd = Calendar.getInstance();
        calOfEnd.setTime(endT);
        int startHour = calOfStart.get(Calendar.HOUR_OF_DAY);
        int endHour = calOfEnd.get(Calendar.HOUR_OF_DAY);
        int startMins = calOfStart.get(Calendar.MINUTE);
        int endMins = calOfEnd.get(Calendar.MINUTE);
        curTime = (((endHour - startHour + 0.0) * 60 + (endMins - startMins)))/ 60;
        return curTime;
    }

    public double getCurSpeed(double curTime, double distance) {
        double speed = distance / curTime;
        //According to the constraints, we would discard the data which is out of the boundary;
        return speed >= 5 && speed <= 100 ? speed : 0;
    }
}

