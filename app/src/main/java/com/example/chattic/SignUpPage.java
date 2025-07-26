package com.example.chattic;

import static android.view.View.INVISIBLE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.LoginActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class SignUpPage extends AppCompatActivity {

    EditText edtUname, edtEmail, edtPass, edtConfirmPass;
    String strUname, strEmail, strPass, strConfirmPass;
    Button signUpBtn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        edtUname = findViewById(R.id.uname);
        edtEmail = findViewById(R.id.emailid);
        edtPass = findViewById(R.id.password);
        edtConfirmPass = findViewById(R.id.confirmPass);

        signUpBtn = findViewById(R.id.button);
        mAuth = FirebaseAuth.getInstance();


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strUname = edtUname.getText().toString();
                strEmail = edtEmail.getText().toString().trim();
                strPass = edtPass.getText().toString().trim();
                strConfirmPass = edtConfirmPass.getText().toString().trim();

                if (!TextUtils.isEmpty(strUname)) {
                    if (!TextUtils.isEmpty(strEmail)) {
                        if (strEmail.matches(emailPattern)) {
                            if (!TextUtils.isEmpty(strPass)) {
                                if (!TextUtils.isEmpty(strPass)) {
                                    if (strPass.equals(strConfirmPass)) {
                                        SignUpUser();
                                    } else {
                                        edtPass.setError("Confirm Password Should be Same As Password");
                                    }
                                } else {
                                    edtPass.setError("Confirm Password Field Can't Be Empty");
                                }
                            } else {
                                edtPass.setError("Password Field Can't Be Empty");
                            }
                        } else {
                            edtEmail.setError("Enter Valid Email Address");
                        }
                    } else {
                        edtEmail.setError("Email Field Can't Be Empty");
                    }
                } else {
                    edtUname.setError("Username Field Can't Be Empty");
                }
            }
        });


    }

    private void SignUpUser() {

        mAuth.createUserWithEmailAndPassword(strEmail, strPass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                Intent intent = new Intent(SignUpPage.this, LoginActivity.class);
                startActivity(intent);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpPage.this, "Invalid Credentials " , Toast.LENGTH_SHORT).show();
            }
        });
    }


}