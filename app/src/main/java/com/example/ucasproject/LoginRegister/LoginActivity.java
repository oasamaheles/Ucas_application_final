package com.example.ucasproject.LoginRegister;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ucasproject.InvoiceActivity;
import com.example.ucasproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends AppCompatActivity {
    EditText textId;
    Button btnLoginlogin;
    TextView textLoginDontHaveAccount, textResend;

    private String verificationId;

    ProgressBar progLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textId = findViewById(R.id.textLoginId);
        btnLoginlogin = findViewById(R.id.btn_login_login);
        textLoginDontHaveAccount = findViewById(R.id.text_login_dontHaveAccount);
        textResend = findViewById(R.id.txtResend);
        progLogin = findViewById(R.id.pbLogin);

        verificationId = getIntent().getStringExtra("verificationId");
        textId.setText(verificationId);

        btnLoginlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (textId.getText().toString().trim().isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.pleaseCompleteAllVales, Toast.LENGTH_SHORT).show();
                    return;
                }

                String code = textId.getText().toString();

                if (verificationId != null) {

                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationId, code);

                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                                Intent intent = new Intent(LoginActivity.this, InvoiceActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("error",e.getMessage());
                        }
                    });
                }
            }
        });
        textLoginDontHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        textResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+97" + getIntent().getStringExtra("number"),
                        60,
                        TimeUnit.SECONDS,
                        LoginActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Log.d("error", e.getMessage());
                            }

                            @Override
                            public void onCodeSent(@NonNull String newVerification, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                verificationId = newVerification;
                            }
                        });
            }
        });
    }

}
