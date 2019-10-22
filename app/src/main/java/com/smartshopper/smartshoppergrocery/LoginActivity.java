package com.smartshopper.smartshoppergrocery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button LoginBtn,RegisterBtn;
    private EditText InptEmail,InptPassword;
    private String email,password;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private TextView ForgetPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Logging...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth=FirebaseAuth.getInstance();

        InptEmail=findViewById(R.id.emailInput);
        InptPassword=findViewById(R.id.passwordInput);
        LoginBtn=findViewById(R.id.sign_in_button);
        RegisterBtn=findViewById(R.id.sign_up_button);
        ForgetPassword=findViewById(R.id.forget_password_button);
        ForgetPassword.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);
        RegisterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==LoginBtn)
        {
            progressDialog.show();
            LoginUser();
        }if(v==RegisterBtn)
        {
            startActivity(new Intent(this,RegisterActivity.class));
        }
        if(v==ForgetPassword)
        {
            startActivity(new Intent(LoginActivity.this,ForgetResetPasswordActivity.class));
        }
    }
    public void LoginUser()
    {
        email=InptEmail.getText().toString().trim();
        password=InptPassword.getText().toString().trim();

        try {
            if (email.isEmpty()) {
                Toast toast = Toast.makeText(LoginActivity.this, "UserName cannot be Empty", Toast.LENGTH_SHORT);
                toast.show();
            } else if (password.isEmpty()) {
                Toast toast = Toast.makeText(LoginActivity.this, "Password cannot be Empty", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(LoginActivity.this, HomeScreenMain.class));
                        } else {
                            Toast toast = Toast.makeText(LoginActivity.this, "Email or Password is Wrong", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    }
                });
            }
            progressDialog.dismiss();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
