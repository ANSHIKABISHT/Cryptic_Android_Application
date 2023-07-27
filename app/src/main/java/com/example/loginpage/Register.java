package com.example.loginpage;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.provider.ContactsContract;
        import android.text.TextUtils;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ProgressBar;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.OnFailureListener;
        import com.google.android.gms.tasks.OnSuccessListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.FirebaseUser;
        import com.google.firebase.firestore.DocumentReference;
        import com.google.firebase.firestore.FirebaseFirestore;
        import com.google.firestore.v1.FirestoreGrpc;

        import java.util.HashMap;
        import java.util.Map;

        import javax.crypto.Mac;
        import javax.crypto.spec.SecretKeySpec;
        import java.security.InvalidKeyException;
        import java.security.NoSuchAlgorithmException;
        import java.util.Base64;

public class Register extends AppCompatActivity {

    //creating string
    public static final String TAG= "TAG";

    //declaring the variables
    EditText mFullName ,mEmail,mPassword,mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    ProgressBar mProgressBar;


    FirebaseFirestore fstore;
    String userID;
    FirebaseAuth fAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // defining the variables along with their id and location
        mFullName = findViewById(R.id.fullname);
        mEmail= findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mPhone= findViewById(R.id.phone);
        mRegisterBtn = findViewById(R.id.RegisterBtn);
        mLoginBtn= findViewById(R.id.createtext);
        mProgressBar=findViewById(R.id.progressBar);

        fAuth=FirebaseAuth.getInstance();
        fstore=FirebaseFirestore.getInstance();


        //authentication successfull move to mainactivity
        if(fAuth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }





        //where login button connects to on clicking it in register page
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //take info from user
                final String fullname= mFullName.getText().toString();
                final String email= mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String phone= mPhone.getText().toString();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email Is Required");
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password Is Required");
                    return;
                }

                if(password.length() < 6)
                {mPassword.setError("Password must be more than 6 characters");
                    return;
                }

                // Progress bar is visible after registering
                mProgressBar.setVisibility(View.VISIBLE);


                //data of email and password will be saved in firebase

                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        //on successful authentication
                        if(task.isSuccessful()){
                            FirebaseUser fuser = fAuth.getCurrentUser();
                            fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext() , "Registration Successful", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Log.d(TAG, "On Failure: Email not Sent" + e.getMessage());
                                }
                            });

                            Toast.makeText(getApplicationContext(), "User Created", Toast.LENGTH_SHORT).show();
                            userID=fAuth.getCurrentUser().getUid();
                            DocumentReference documentreference =fstore.collection("user").document(userID);
                            Map<String,Object> user =new HashMap<>();

                            user.put("fName", fullname);
                            user.put("email", email);
                            user.put("phone" , phone);
                            documentreference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG , "On Sucess : Use Profile is Created" +userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Log.d(TAG , "On Failure :" + e.toString());
                                }
                            });

                            //on clicking registed redired to main activity

                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }
                        else {
                            Toast.makeText(Register.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            mProgressBar.setVisibility(View.GONE);

                        }
                    }
                });

            }
        });



    }
}