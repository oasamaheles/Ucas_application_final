package com.example.ucasproject.Add_user_admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

import com.example.ucasproject.InvoiceActivity;
import com.example.ucasproject.Models.User;
import com.example.ucasproject.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Calendar;

public class AddNewUserActivity extends AppCompatActivity {
    EditText editAddUserFirstName, editAddUserAddress, editAddUserSubNumber, editAddUserPhone;
    Button btnAddNewUser;
    ProgressBar pbNewUser;
    ImageView imgNewUser;

    FirebaseFirestore db;
    private FirebaseStorage storage;

    private Uri imgUri;
    String textUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        editAddUserFirstName = findViewById(R.id.editAddUserFirstName);
        editAddUserAddress = findViewById(R.id.editAddUserAddress);
        editAddUserSubNumber = findViewById(R.id.editAddUserSubNumber);
        editAddUserPhone = findViewById(R.id.editAddUserPhoneNumber);
        imgNewUser = findViewById(R.id.addNewUserImage);

        btnAddNewUser = findViewById(R.id.btnAddNewUser);

        pbNewUser = findViewById(R.id.pbAddNewUser);


        ActivityResultLauncher<String> arl = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imgUri = result;
                if (imgUri != null) {
                    imgNewUser.setImageURI(imgUri);
                }
            }
        });

        imgNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arl.launch("image/*");
            }
        });

        btnAddNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editAddUserFirstName.getText().toString()) ||
                        TextUtils.isEmpty(editAddUserAddress.getText().toString()) || TextUtils.isEmpty(editAddUserSubNumber.getText().toString()) ||
                        TextUtils.isEmpty(editAddUserPhone.getText().toString())) {
                    Toast.makeText(AddNewUserActivity.this, R.string.pleaseCompleteAllVales, Toast.LENGTH_SHORT).show();
                    return;
                }

                pbNewUser.setVisibility(View.VISIBLE);
                btnAddNewUser.setVisibility(View.GONE);

                String firstName = editAddUserFirstName.getText().toString();
                String address = editAddUserAddress.getText().toString();
                String subNumber = editAddUserSubNumber.getText().toString();
                String phoneNumber = editAddUserPhone.getText().toString();

                User user = new User(firstName, address, subNumber, phoneNumber);

                db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        uploadPhoto();

                        if (documentReference.getId() != null) {
                            pbNewUser.setVisibility(View.GONE);
                            btnAddNewUser.setVisibility(View.VISIBLE);

                            Toast.makeText(AddNewUserActivity.this, R.string.finshSuccessfull, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddNewUserActivity.this, R.string.checkInternet, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void uploadPhoto() {
        Calendar calendar = Calendar.getInstance();
        storage.getReference().child(editAddUserFirstName.getText().toString() + "//"
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