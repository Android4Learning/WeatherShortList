package ro.marianperca.weathershortlist;

import java.util.Date;

public class WeatherDay {
    Date date;
    double min;
    double max;
    String icon;

    public WeatherDay(Date date, double min, double max, String icon) {
        this.date = date;
        this.min = min;
        this.max = max;
        this.icon = icon;
    }
}
