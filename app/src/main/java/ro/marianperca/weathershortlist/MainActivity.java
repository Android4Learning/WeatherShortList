package ro.marianperca.weathershortlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinearLayout forecastContainer = findViewById(R.id.forecast_container);

        ArrayList<WeatherDay> forecast = populateForecast(3);

        SimpleDateFormat formatDate = new SimpleDateFormat("EEEE", Locale.ENGLISH);

        for (WeatherDay weatherDay : forecast) {
            View weatherView = LayoutInflater.from(this).inflate(R.layout.weather_day, forecastContainer, false);

            TextView day = weatherView.findViewById(R.id.day);
            TextView tempLow = weatherView.findViewById(R.id.temp_low);
            TextView tempHigh = weatherView.findViewById(R.id.temp_high);
            ImageView icon = weatherView.findViewById(R.id.icon);

            day.setText(formatDate.format(weatherDay.date));
            tempLow.setText(weatherDay.min + "\u00B0"); // concatentat cu simbolul pentru grade °
            tempHigh.setText(weatherDay.max + "\u00B0");

            Glide.with(this).load(weatherDay.icon).into(icon);

            forecastContainer.addView(weatherView);
        }
    }

    private ArrayList<WeatherDay> populateForecast(int noOfDays) {
        Calendar c = Calendar.getInstance();
        Random rand = new Random();

        ArrayList<String> weatherIcons = new ArrayList<String>() {
            {
                add("https://www.weatherbit.io/static/img/icons/t04d.png");
                add("https://www.weatherbit.io/static/img/icons/r03d.png");
                add("https://www.weatherbit.io/static/img/icons/r05d.png");
                add("https://www.weatherbit.io/static/img/icons/c01d.png");
                add("https://www.weatherbit.io/static/img/icons/c02d.png");
                add("https://www.weatherbit.io/static/img/icons/c03d.png");
            }
        };

        int lowMin = 1;
        int lowMax = 10;

        int highMin = 12;
        int highMax = 30;

        ArrayList<WeatherDay> entries = new ArrayList<>();

        for (int i = 0; i < noOfDays; i++) {
            c.add(Calendar.DATE, 1);
            entries.add(new WeatherDay(
                    c.getTime(),
                    rand.nextInt(lowMax - lowMin + 1) + lowMin,
                    rand.nextInt(highMax - highMin + 1) + highMin,
                    weatherIcons.get(rand.nextInt(weatherIcons.size()))
            ));
        }

        return entries;
    }
}
