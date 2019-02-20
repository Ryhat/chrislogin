package com.chrisfeb.app.chrislogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private TextInputEditText username;
    private TextInputEditText password;
    private TextInputEditText password2;
    private TextInputEditText email;

    private TextInputLayout layoutUsername;
    private TextInputLayout layoutPassword;
    private TextInputLayout layoutPassword2;
    private TextInputLayout layoutEmail;

    private ProgressBar mProgressBar;

    private MaterialButton mBtRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.ed_username);
        password = findViewById(R.id.ed_password);
        password2 = findViewById(R.id.ed_password2);
        email = findViewById(R.id.ed_email);

        layoutUsername = findViewById(R.id.tv_username);
        layoutPassword = findViewById(R.id.tv_password);
        layoutPassword2 = findViewById(R.id.tv_password2);
        layoutEmail = findViewById(R.id.tv_email);

        mProgressBar = findViewById(R.id.progress);

        mBtRegister = findViewById(R.id.bt_register);
    }

    public void showError(TextInputLayout textInputLayout, String error){
        textInputLayout.setErrorEnabled(true);
        textInputLayout.setError(error);
        textInputLayout.getEditText().setFocusable(true);
        textInputLayout.getEditText().setFocusableInTouchMode(true);
        textInputLayout.getEditText().requestFocus();
    }

    public void register(View view) {

        mProgressBar.setVisibility(View.VISIBLE);
        mBtRegister.setText("");

        String strUsername = username.getText().toString().trim();
        String strPassword = password.getText().toString().trim();
        String strPassword2 = password2.getText().toString().trim();
        String strEmail = email.getText().toString().trim();

        if (strUsername.equals("")){
            showError(layoutUsername, "请输入用户名");
            return;
        }
        if (strPassword.equals("")){
            showError(layoutPassword, "请输入密码");
            return;
        }
        if (strPassword2.equals("")){
            showError(layoutPassword2, "请输入密码");
            return;
        }
        if (!strPassword.equals(strPassword2)){
            showError(layoutPassword2, "两次密码不一致，请检查输入");
            return;
        }
        if (strEmail.isEmpty()){
            showError(layoutEmail, "请输入邮箱");
        } 
        if (isMobileEM(strEmail)){

        } else {
            showError(layoutEmail, "邮箱格式错误，请检查输入");
        }
    }

    public static boolean isMobileEM(String email) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean insertNewUser(String username, String password, String email){


        return true;
    }

    public void toLogin(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

