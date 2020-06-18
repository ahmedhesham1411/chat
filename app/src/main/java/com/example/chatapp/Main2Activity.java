package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Main2Activity extends AppCompatActivity {

    MaterialEditText mail,password;
    Button login_btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        auth = FirebaseAuth.getInstance();

        mail = findViewById(R.id.login_mail);
        password = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.btn_login);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_mail = mail.getText().toString();
                String txt_password = password.getText().toString();
                if (TextUtils.isEmpty(txt_mail) || TextUtils.isEmpty(txt_password)){
                    Toast.makeText(Main2Activity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
                else {
                    auth.signInWithEmailAndPassword(txt_mail,txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Main2Activity.this,hello.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(Main2Activity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }
}
