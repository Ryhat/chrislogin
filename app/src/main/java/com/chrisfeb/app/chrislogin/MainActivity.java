package com.chrisfeb.app.chrislogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static String URL_LOGIN = "http://120.78.137.216/mytaskapp/login.php";
    private MaterialButton mBtToRegister;
    private MaterialButton mBtLogin;
    private TextInputEditText mEtUsername;
    private TextInputEditText mEtPassword;

    private ProgressBar mProgressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtToRegister = findViewById(R.id.bt_to_register);
        mBtLogin = findViewById(R.id.bt_login);

        mEtUsername = findViewById(R.id.ed_username);
        mEtPassword = findViewById(R.id.ed_password);

        mProgressBar = findViewById(R.id.progress);
    }

    public void logIn(View view) {

        final String username = mEtUsername.getText().toString().trim();
        final String password = mEtPassword.getText().toString().trim();

        if (username.equals("")){
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_LONG).show();
        }else if (password.equals("")){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_LONG).show();
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            mBtLogin.setText("");
            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                mProgressBar.setVisibility(View.INVISIBLE);
                                mBtLogin.setText(R.string.login);

                                JSONObject jsonObject = new JSONObject(response);

                                String success = jsonObject.getString("success");
                                String message = jsonObject.getString("message");

                                if (success.equals("1")){
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                                } else {
                                    mProgressBar.setVisibility(View.INVISIBLE);
                                    mBtLogin.setText(R.string.login);
                                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                mProgressBar.setVisibility(View.INVISIBLE);
                                mBtLogin.setText(R.string.login);
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            mProgressBar.setVisibility(View.INVISIBLE);
                            mBtLogin.setText(R.string.login);
                            Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String, String> params = new HashMap<>();
                    params.put("username", username);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    public void register(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

    }
}
