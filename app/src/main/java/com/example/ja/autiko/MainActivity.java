/*
Aplikacja, która będzie pomagać autystycznym dziecom w porozumiewaniu się. Kafelki odpowiadające różnym przedmiotom, zwierzętom, czynnościom itp. z życia codziennego.
Po wciśnieciu kafelka odtwarzana jest nazwa danego przemiotu zwierzęcia czynności itp.
 */

package com.example.ja.autiko;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Locale;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {


    private TextToSpeech tts;
    private EditText txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main_screen();
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void main_screen() {
        setContentView(R.layout.activity_main);
        ImageButton btnSpeak = (ImageButton) findViewById(R.id.playButton);
        txtText = (EditText) findViewById(R.id.speakEditText);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                speak(txtText.getText().toString());
            }
        });

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Toast toast = Toast.makeText(getApplicationContext(), "This Language is not supported", LENGTH_SHORT);
                        toast.show();
                    }
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Initilization Failed!", LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }

    public void speak(String text) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        } else {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    public void speak_apple(View view) {
        speak("jabłko");
    }
    public void speak_carrot(View view) {
        speak("marchewka");
    }
    public void speak_water(View view) {
        speak("woda");
    }
    public void speak_vegetables(View view) {
        speak("warzywa");
    }
    public void speak_drinks(View view) {
        speak("napoje");
    }
    public void speak_fruits(View view) {
        speak("owoce");
    }

    public void set_fruits_view(View view) {
        setContentView(R.layout.fruits);
        speak_fruits(view);
    }
    public void set_vegetables_view(View view) {
        setContentView(R.layout.vegetables);
        speak_vegetables(view);
    }
    public void set_drinks_view(View view) {
        setContentView(R.layout.drinks);
        speak_drinks(view);
    }
    public void set_categories_view(View view) {
        setContentView(R.layout.categories);
    }
    public void set_main_view(View view) {
        main_screen();
    }
}
