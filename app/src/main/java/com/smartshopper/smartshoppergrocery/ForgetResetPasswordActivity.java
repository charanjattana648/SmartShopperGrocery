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
import com.google.firebase.auth.FirebaseUser;

public class ForgetResetPasswordActivity extends AppCompatActivity implements View.OnClickListener {


    private Button UpdateEmail,UpdatePassword,DeleteUser,SignOut;
    private EditText TxtInput;
    private String EnteredData;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_reset_password);


        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        firebaseAuth=FirebaseAuth.getInstance();

        TxtInput=findViewById(R.id.emailInput);
        UpdateEmail=findViewById(R.id.ResetEmail);
        UpdatePassword=findViewById(R.id.ResetPassword);
        DeleteUser=findViewById(R.id.DeleteUser);
        SignOut=findViewById(R.id.SignOutBtn);

        UpdateEmail.setOnClickListener(this);
        UpdatePassword.setOnClickListener(this);
        DeleteUser.setOnClickListener(this);
        SignOut.setOnClickListener(this);

        EnteredData=TxtInput.getText().toString().trim();
        user=firebaseAuth.getCurrentUser();

    }


    @Override
    public void onClick(View v) {
        if(v==UpdateEmail)
        {
   /*         UpdatePassword.setVisibility(View.INVISIBLE);
            DeleteUser.setVisibility(View.INVISIBLE);
            UpdateEmail.setVisibility(View.VISIBLE);*/
            UpdateEmailUser();
        }else if(v==UpdatePassword)
        {
            UpdatePasswordUser();

        }else if(v==DeleteUser)
        {
            DeleteUserFunction();
        }
        else if(v==SignOut)
        {
            userSignOut();
        }

    }

    public void UpdateEmailUser()
    {
        if(EnteredData.isEmpty())
        {
            Toast toast=Toast.makeText(this,"Enter the Email",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            progressDialog.show();
            user.updateEmail(EnteredData).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Email is updated!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Failed to update Email!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });

        }
    }
    public void   UpdatePasswordUser()
    {
        if(EnteredData.isEmpty())
        {
            Toast toast=Toast.makeText(this,"Enter the Email",Toast.LENGTH_SHORT);
            toast.show();
        }else{
            progressDialog.show();
            user.updatePassword(EnteredData).addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Password is updated!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();

                }
            });

        }

    }
    public void DeleteUserFunction()
    {
        if(user!=null)
        {
            progressDialog.show();
            user.delete().addOnCompleteListener(this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Your profile is deleted:( Create a account now!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ForgetResetPasswordActivity.this, "Failed to delete your account!", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });
        }

    }

    public void userSignOut()
    {
        progressDialog.show();
        firebaseAuth.signOut();
        FirebaseAuth.AuthStateListener stateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if(user==null)
                {
                    startActivity(new Intent(ForgetResetPasswordActivity.this,LoginActivity.class));
                    finish();
                }else {

                }
                progressDialog.dismiss();
            }
        };

    }

    @Override
    protected void onResume() {
        if(firebaseAuth.getCurrentUser()==null)
        {
            startActivity(new Intent(ForgetResetPasswordActivity.this,LoginActivity.class));
            finish();
        }
        super.onResume();
    }

}
