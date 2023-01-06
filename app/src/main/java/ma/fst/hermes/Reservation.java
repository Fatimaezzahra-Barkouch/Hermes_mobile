package ma.fst.hermes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ma.fst.hermes.Matche;


public class Reservation extends AppCompatActivity {

    Button r;
    final Calendar myCalendar = Calendar.getInstance();
    EditText datePicker, timePicker;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        r = (Button) findViewById(R.id.reserver);

        datePicker = findViewById(R.id.date_picker);
        timePicker = findViewById(R.id.time_picker);
        selectDate();

        timePicker.setOnClickListener(v -> selectTime());
        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Matche.class);
                startActivity(intent);
            }
        });
    }

    public void fetchApi() {

    }

    private void selectDate() {
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, month);
                myCalendar.set(Calendar.DAY_OF_MONTH, day);

                datePicker.setText(updateDate());
            }
        };

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(Reservation.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });
    }

    private String updateDate() {
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        return dateFormat.format(myCalendar.getTime());
    }

    private String s;

    private void selectTime() {
        Calendar currentTime = Calendar.getInstance();
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int minute = currentTime.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog;
        timePickerDialog = new TimePickerDialog(Reservation.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                currentTime.set(Calendar.HOUR_OF_DAY, hour);
                currentTime.set(Calendar.MINUTE, minute);

                String myFormat = "HH:mm:ss";
                SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
                s = dateFormat.format(currentTime.getTime());


            }
        }, hour, minute, true);
        timePicker.setText(s);
        timePickerDialog.setTitle("select Time");
        timePickerDialog.show();


    }
}