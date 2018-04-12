package com.example.android.cdi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity
        implements View.OnClickListener {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.email_sign_in_button).setOnClickListener(this);
        findViewById(R.id.email_create_account_button).setOnClickListener(this);

    }

    // [START on_start_check_user]

    /*private void updateUI(FirebaseUser user) {
        hideProgressDialog();
        if (user != null) {
            mStatusTextView.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mDetailTextView.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            findViewById(R.id.email_password_buttons).setVisibility(View.GONE);
            findViewById(R.id.email_password_fields).setVisibility(View.GONE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.VISIBLE);

            //findViewById(R.id.verify_email_button).setEnabled(!user.isEmailVerified());
        } else {
            mStatusTextView.setText(R.string.signed_out);
            mDetailTextView.setText(null);

            findViewById(R.id.email_password_buttons).setVisibility(View.VISIBLE);
            findViewById(R.id.email_password_fields).setVisibility(View.VISIBLE);
            findViewById(R.id.signed_in_buttons).setVisibility(View.GONE);
        }
    }*/

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch (i){
            case R.id.email_create_account_button:
                //createAccount(mEmailField.getText().toString(), mPasswordField.getText().toString());
                Intent intent = new Intent(this, RegisterActivity.class);
                //TODO definir RequestCodes
                startActivityForResult(intent, 1);
                break;
            case R.id.email_sign_in_button:
                Intent intent1 = new Intent(this, LoginActivity.class);
                startActivityForResult(intent1, 3);
                break;


            /*case R.id.verify_email_button:
                sendEmailVerification();
                break;


            case R.id.sign_out_button:
                signOut();
                break;*/
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2){
            Toast.makeText(getApplicationContext(), "Todo correcto", Toast.LENGTH_SHORT).show();
        }
    }
}
