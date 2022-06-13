package com.example.ucasproject.LoginRegister;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucasproject.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class RegisterActivity extends AppCompatActivity {
    EditText editSignupName, editSignupSubscription;
    Button btnSignupSign;
    TextView textAlreadyHave;
    ProgressBar progressBarSign;
    ImageView photo_user;
    ActivityResultLauncher<String> ar1;
    String img_save ;
    View parentLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editSignupName = findViewById(R.id.edit_signup_name);
        final EditText editSignupPhone = findViewById(R.id.edit_signup_phone);
        editSignupSubscription = findViewById(R.id.edit_signup_Subscription);
        btnSignupSign = findViewById(R.id.btn_signup_sign);
        textAlreadyHave = findViewById(R.id.text_signup_alreadyHaveAccount);
        progressBarSign = findViewById(R.id.pbSign);
        photo_user= findViewById(R.id.userImgSign);
        parentLayout = findViewById(android.R.id.content);





        // عشان المستخدم بضغط على الصورة وراح يختار صورة من المعرض وبترجع الصورة ع التطبيق فبنستخدم اكتيفيتي ريزلت لانشر
        ar1 = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            //GetContent: اختيار صورة من المعرض
            @Override
            public void onActivityResult(Uri result) {
                photo_user.setImageURI(result);
                img_save = result.toString();
            }
        });
        ActivityResultLauncher<String> permission =
                registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
                    @Override
                    public void onActivityResult(Boolean result) {
                        if(result){
                            photo_user.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    ar1.launch("image/*");
                                }
                            });
                        }
                        else {
                            Snackbar.make(parentLayout,"Gallery will not be accessed",Snackbar.LENGTH_LONG).setActionTextColor(getResources().getColor(android.R.color.holo_red_light)).show();
                        }
                    }
                });
        permission.launch(Manifest.permission.READ_EXTERNAL_STORAGE);


        btnSignupSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editSignupName.getText().toString().trim().isEmpty() || editSignupPhone.getText().toString().trim().isEmpty() ||
                        editSignupSubscription.getText().toString().trim().isEmpty()) {
                    Toast.makeText(RegisterActivity.this, R.string.pleaseCompleteAllVales, Toast.LENGTH_SHORT).show();
                }

                progressBarSign.setVisibility(View.VISIBLE);
                btnSignupSign.setVisibility(View.GONE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+97" + editSignupPhone.getText().toString(),
                        60,
                        TimeUnit.SECONDS,
                 RegisterActivity.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBarSign.setVisibility(View.GONE);
                                btnSignupSign.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBarSign.setVisibility(View.GONE);
                                btnSignupSign.setVisibility(View.VISIBLE);
                                Log.d("error", e.getMessage());
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBarSign.setVisibility(View.GONE);
                                btnSignupSign.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                intent.putExtra("number",editSignupPhone.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
                                finish();

                            }
                        });
            }
        });
        textAlreadyHave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}