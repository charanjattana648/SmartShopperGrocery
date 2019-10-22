package com.smartshopper.smartshoppergrocery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button RegisterBtn,LoginBtn;
    private EditText emailInpt,PasswordInpt,CPasswordInpt;
    private String Email,Password,CPassword;
    private Boolean hasUpperCase=false,hasLowerCase=false,hasNumber=false;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth=FirebaseAuth.getInstance();

        emailInpt=findViewById(R.id.RemailInput);
        PasswordInpt=findViewById(R.id.RpasswordInput);
        CPasswordInpt=findViewById(R.id.ConfirmPasswordInput);

        RegisterBtn=findViewById(R.id.sign_up_buttonR);
        LoginBtn=findViewById(R.id.sign_in_buttonR);
        RegisterBtn.setOnClickListener(this);
        LoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==RegisterBtn)
        {
            RegisterUser();
        }if(v==LoginBtn)
        {
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

    public void RegisterUser()
    {
        Email=emailInpt.getText().toString().trim();
        Password=PasswordInpt.getText().toString().trim();
        CPassword=CPasswordInpt.getText().toString().trim();
        hasUpperCase=checkUpperCase(Password);
        hasLowerCase=checkLowerCase(Password);
        hasNumber=checkHasNumber(Password);

        try {
            progressDialog.show();

            if (Email.isEmpty()) {
                Toast toast = Toast.makeText(RegisterActivity.this, "UserName cannot be Empty", Toast.LENGTH_SHORT);
                toast.show();
            } else if (Password.isEmpty()) {
                Toast toast = Toast.makeText(RegisterActivity.this, "Password cannot be Empty", Toast.LENGTH_SHORT);
                toast.show();
            } else if (!Password.equals(CPassword)) {
                Toast toast = Toast.makeText(RegisterActivity.this, "Password does not match with Confirm Password", Toast.LENGTH_SHORT);
                toast.show();
            }else if (Password.length()<8) {
                Toast toast = Toast.makeText(RegisterActivity.this, "Password should be of minimum 8 characters", Toast.LENGTH_SHORT);
                toast.show();
            }else if(hasNumber==false || hasLowerCase==false || hasUpperCase==false){
                Toast toast = Toast.makeText(RegisterActivity.this, "Password should contain One UpperCase,One LowerCase and One Numeric ", Toast.LENGTH_SHORT);
                toast.show();

            }else {
                firebaseAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                            finish();
                        }else {
                            Toast toast = Toast.makeText(RegisterActivity.this, "Try with different Email ", Toast.LENGTH_SHORT);
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

    public boolean checkUpperCase(String Password)
    {
        hasUpperCase=false;
        for(int i=0;i<Password.length();i++)
        {
            if(Character.isUpperCase(Password.charAt(i)))
            {
                hasUpperCase=true;
                return hasUpperCase;
            }
        }
        return hasUpperCase;
    }
    public boolean checkLowerCase(String Password)
    {
        hasLowerCase=false;
        for(int i=0;i<Password.length();i++)
        {
            if(Character.isLowerCase(Password.charAt(i)))
            {
                hasLowerCase=true;
                return hasLowerCase;
            }
        }
        return hasLowerCase;
    }
    public boolean checkHasNumber(String Password)
    {
        hasNumber=false;
        for(int i=0;i<Password.length();i++)
        {
            if(Character.isDigit(Password.charAt(i)))
            {
                hasNumber=true;
                return hasNumber;
            }
        }
        return hasNumber;
    }

}
