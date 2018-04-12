package com.example.android.cdi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PaypalActivity extends BaseActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    private String email, contraseña;
    private EditText emailP;
    private EditText contraseñaP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paypal);

        emailP = (EditText)findViewById(R.id.mPaypalEmailField);
        contraseñaP = (EditText)findViewById(R.id.mPaypalPasswordField);

        mAuth = FirebaseAuth.getInstance();

        Bundle extras = getIntent().getExtras();

        if(extras != null){
            email = extras.getString("email");
            contraseña = extras.getString("contraseña");
            emailP.setText(email);
            contraseñaP.setText(contraseña);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        volver();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }
    // [END on_start_check_user]

    public void volver (){
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = emailP.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailP.setError("Campo obligatorio.");
            valid = false;
        } else {
           emailP.setError(null);
        }

        String password = contraseñaP.getText().toString();
        if (TextUtils.isEmpty(password)) {
            contraseñaP.setError("Campo obligatorio.");
            valid = false;
        } else {
            contraseñaP.setError(null);
        }

        return valid;
    }

    private void createAccount(String email, String password) {
        Log.d(TAG, "createAccount:" + email);


        showProgressDialog();

        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressDialog();
                        // [END_EXCLUDE]
                    }
                });
        // [END create_user_with_email]
    }

    public void registrarse (View v){
        if(validateForm()){
            createAccount(email, contraseña);
        }
    }
}
