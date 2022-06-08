package com.example.ucasproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ucasproject.Models.Issuing;
import com.example.ucasproject.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class IssuingActivity extends AppCompatActivity {
    TextView textName, textId;
    ImageView imgCurrent;
    EditText editIssuingCurrentReading, editIssuingPrice;
    Button btnIssuingSave;
    ProgressBar pbIssuing;

    FirebaseFirestore db;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issuing);

        db = FirebaseFirestore.getInstance();
        user = new User();
        user = (User) getIntent().getSerializableExtra("");

        textName = findViewById(R.id.textIssuingUserName);
        textId = findViewById(R.id.textIssuingUserId);
        imgCurrent = findViewById(R.id.imgCurrent);
        editIssuingCurrentReading = findViewById(R.id.editIssuingCurrentReading);
        editIssuingPrice = findViewById(R.id.editIssuingBillPrice);
        btnIssuingSave = findViewById(R.id.btnIssuingSave);
        pbIssuing = findViewById(R.id.pbAddPrice);

//        textName.setText(user.getUserFirstName() + " " + user.getUserLastName());
//        textId.setText(user.getUserId());

        btnIssuingSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editIssuingCurrentReading.getText().toString()) || TextUtils.isEmpty(editIssuingPrice.getText().toString())) {
                    Toast.makeText(IssuingActivity.this, R.string.pleaseCompleteAllVales, Toast.LENGTH_SHORT).show();
                    return;
                }

                pbIssuing.setVisibility(View.VISIBLE);
                btnIssuingSave.setVisibility(View.GONE);

                String name = textName.getText().toString();
                String id = textId.getText().toString();
                String currentReading = editIssuingCurrentReading.getText().toString();
                String price = editIssuingPrice.getText().toString();
                Issuing issuing = new Issuing(name, id, currentReading, price);

                db.collection("bills").add(issuing).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        if (documentReference.getId() != null) {
                            pbIssuing.setVisibility(View.GONE);
                            btnIssuingSave.setVisibility(View.VISIBLE);
                            Toast.makeText(IssuingActivity.this, R.string.finshSuccessfull, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(IssuingActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }
}