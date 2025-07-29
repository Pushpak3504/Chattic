package com.example.chattic;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button findBtn;
    ProgressBar progressBar;
    EditText query;
    TextView outputText;

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


        findBtn = findViewById(R.id.findBtn);
        progressBar = findViewById(R.id.progressBar);
        query = findViewById(R.id.edtQuestion);
        outputText = findViewById(R.id.outputTxt);

        findBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GeminiPro geminiPro = new GeminiPro();
                String q = query.getText().toString();
                query.setText("");
                outputText.setText("");
                progressBar.setVisibility(VISIBLE);

                geminiPro.getResponse(q, new ResponseCallback() {
                    @Override
                    public void onResponse(String response) {
                        outputText.setText(response);
                        progressBar.setVisibility(GONE);
                    }
                });
            }
        });

    }





}