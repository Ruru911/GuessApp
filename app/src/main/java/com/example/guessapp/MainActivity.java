package com.example.guessapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editText = findViewById(R.id.eText);
        button = findViewById(R.id.btn);
        textView = findViewById(R.id.tResult);

        Random random = new Random();
        int secretKey = random.nextInt(20) + 1;
        Log.i("Result", secretKey + "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();

                try {
                    int intValue = Integer.parseInt(value);

                    // Range check
                    if (intValue < 1 || intValue > 20) {
                        Toast.makeText(getApplicationContext(), "Enter number in 1-20", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Result check
                    if (intValue == secretKey) {
                        textView.setText("You won!");
                    } else {
                        textView.setText("Incorrect! Try again.");

                        // Hint
                        if (intValue < secretKey) {
                            Toast.makeText(getApplicationContext(), "The guessed number is higher", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "The guessed number is lower", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (NumberFormatException e) {
                    // Handling an exception if a non-number is entered
                    Toast.makeText(getApplicationContext(), "Please, enter an integer", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
