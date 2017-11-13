
/*
Aplikacja, która będzie pomagać autystycznym dziecom w porozumiewaniu się. Kafelki odpowiadające różnym przedmiotom, zwierzętom, czynnościom itp. z życia codziennego.
Po wciśnieciu kafelka odtwarzana jest nazwa danego przemiotu zwierzęcia czynności itp.
 */

package com.example.ja.autiko;

import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private TextToSpeech tts;
    private EditText txtText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btnSpeak = (ImageButton) findViewById(R.id.playButton);
        txtText = (EditText) findViewById(R.id.speakEditText);

        // button on click event
        btnSpeak.setOnClickListener(new View.OnClickListener() {

                                        @Override
                                        public void onClick(View arg0) {
                                            speak(txtText.getText().toString());
                                        }
                                    });



        //SPEAKING
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.getDefault());
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "This Language is not supported");
                    }
                    speak("Dzień dobry");

                } else {
                    Log.e("TTS", "Initilization Failed!");
                }
            }
        });
    }




    private void speak(String text){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }else{
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void next_button(View view){
        setContentView(R.layout.categories);
    }


    public void back_button(View view) {
        setContentView(R.layout.activity_main);
    }
}
