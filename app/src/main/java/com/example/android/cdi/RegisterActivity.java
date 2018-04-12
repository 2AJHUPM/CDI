package com.example.android.cdi;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText field_nombre;
    private EditText field_apellidos;
    private EditText field_email;
    private EditText field_telefono;
    private EditText field_contraseña;
    private EditText field_confirmarconstraseña;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        field_nombre = (EditText)findViewById(R.id.field_nombre);
        field_apellidos = (EditText)findViewById(R.id.field_apellidos);
        field_email = (EditText) findViewById(R.id.field_email);
        field_telefono = (EditText)findViewById(R.id.field_telefono);
        field_contraseña = (EditText)findViewById(R.id.field_contraseña);
        field_confirmarconstraseña = (EditText) findViewById(R.id.field_confirmarcontraseña);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        volver();
    }

    public void volver (){
        Intent intent = new Intent();
        setResult(2, intent);
        finish();
    }



    public void toPaypal (View v){
        if(validateForm(v)){
            Intent intent = new Intent(this, PaypalActivity.class);
            intent.putExtra("email", field_email.getText().toString());
            intent.putExtra("contraseña", field_contraseña.getText().toString());
            startActivityForResult(intent, 5);
        }
    }

    private boolean validateForm(View v) {
        boolean valid = true;
        String nombre = field_nombre.getText().toString();
        if (TextUtils.isEmpty(nombre)) {
            field_nombre.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_nombre.setError(null);
        }

        String apellidos = field_apellidos.getText().toString();
        if (TextUtils.isEmpty(apellidos)) {
            field_apellidos.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_apellidos.setError(null);
        }

        //TODO quitar is empty y comprobar formato email
        String email = field_email.getText().toString();
        if (TextUtils.isEmpty(email)) {
            field_email.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_email.setError(null);
        }

        String telefono = field_telefono.getText().toString();
        if (TextUtils.isEmpty(telefono)) {
            field_telefono.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_telefono.setError(null);
        }

        String contrasena = field_contraseña.getText().toString();
        if (TextUtils.isEmpty(contrasena)) {
            field_contraseña.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_contraseña.setError(null);
        }

        String confirmarContrasena = field_confirmarconstraseña.getText().toString();
        if (TextUtils.isEmpty(confirmarContrasena)) {
            field_confirmarconstraseña.setError("Campo obligatorio.");
            valid = false;
        } else {
            field_confirmarconstraseña.setError(null);
        }

        if(contrasena.compareTo(confirmarContrasena) != 0){
            valid = false;
            Snackbar.make(v, "Las contraseñas no coinciden", Snackbar.LENGTH_LONG).setAction("Retry", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    field_contraseña.setText("");
                    field_confirmarconstraseña.setText("");
                    field_contraseña.requestFocus();
                }
            }).show();

        }

        return valid;
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 2){
            Toast.makeText(getApplicationContext(), "Todo correcto", Toast.LENGTH_SHORT).show();
        }
    }
}
