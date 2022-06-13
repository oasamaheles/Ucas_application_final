package com.example.ucasproject.Add_user_admin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ucasproject.InvoiceActivity;
import com.example.ucasproject.Models.Admin;
import com.example.ucasproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class AddNewAdminActivity extends AppCompatActivity {
    ImageView imgNewAdmin;
    EditText editAdminFirstName, editAdminAddress, editAdminPhoneNumber;
    Button btnAddNewAdmin;
    ProgressBar pbNewAdmin;
    String textUrl;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_admin);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        imgNewAdmin = findViewById(R.id.addNewAdminImage);

        editAdminFirstName = findViewById(R.id.editAddAdminUserName);
        editAdminAddress = findViewById(R.id.editAddAdminAddress);
        editAdminPhoneNumber = findViewById(R.id.editAddAdminPhoneNumber);

        btnAddNewAdmin = findViewById(R.id.btnAddNewAdmin);

        pbNewAdmin = findViewById(R.id.pbAddNewAdmin);

        ActivityResultLauncher<String> arl = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imgUri = result;
                if (imgUri != null) {
                    imgNewAdmin.setImageURI(imgUri);
                }
            }
        });

        imgNewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch("image/*");
            }
        });

        btnAddNewAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editAdminFirstName.getText().toString()) ||
                        TextUtils.isEmpty(editAdminAddress.getText().toString()) || TextUtils.isEmpty(editAdminPhoneNumber.getText().toString())) {
                    Toast.makeText(AddNewAdminActivity.this, R.string.pleaseCompleteAllVales, Toast.LENGTH_SHORT).show();
                    return;
                }

                pbNewAdmin.setVisibility(View.VISIBLE);
                btnAddNewAdmin.setVisibility(View.GONE);

                String adminFirstName = editAdminFirstName.getText().toString();
                String adminAddress = editAdminAddress.getText().toString();
                String adminPhoneNumber = editAdminPhoneNumber.getText().toString();

                Admin admin = new Admin(adminFirstName, adminAddress, adminPhoneNumber);

                Glide.with(getApplicationContext()).load(admin.getAdminImgUrl()).circleCrop().into(imgNewAdmin);

                db.collection("admins").add(admin).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        uploadPhoto();

                        if (documentReference.getId() != null) {
                            pbNewAdmin.setVisibility(View.GONE);
                            btnAddNewAdmin.setVisibility(View.VISIBLE);

                            Toast.makeText(AddNewAdminActivity.this, R.string.finshSuccessfull, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewAdminActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    private void uploadPhoto() {
        Calendar calendar = Calendar.getInstance();
        storage.getReference().child(editAdminFirstName.getText().toString() + "//"
                + calendar.getTimeInMillis()).putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        textUrl = uri.toString();
                    }
                });
            }
        });
    }
}