package com.moataz.developer.ncittest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moataz.developer.ncittest.model.User;
import com.moataz.developer.ncittest.netWork.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignActivity extends AppCompatActivity {
    private EditText userName,userPass,re_pass;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        userName = findViewById(R.id.name_text_sign);
        userPass = findViewById(R.id.pass_text_sign);
        re_pass = findViewById(R.id.pass_text2_sign);

        login = findViewById(R.id.bu_sign);
        login.setOnClickListener(v ->
                Sign()
        );

    }

    public void Sign(){

        String name = userName.getText().toString().trim();
        String pass = userPass.getText().toString().trim();
        String rePass = re_pass.getText().toString().trim();

        if (name.isEmpty()){
            userName.setError("Enter your name");
            userName.requestFocus();
            return;
        }

        if (pass.isEmpty()){
            userPass.setError("Enter your mail");
            userPass.requestFocus();
            return;
        }

        if (pass.isEmpty()&&name.isEmpty()){
            userName.setError("Enter your mail");
            userName.requestFocus();
            userPass.setError("Enter your mail");
            userPass.requestFocus();
            return;
        }

        if (!pass.equals(rePass)){
            re_pass.setError("this pass is not matching!");
            re_pass.requestFocus();
            return;
        }

        Call<User> call = RetrofitClient.getInstance().getApi().sign(name,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    Intent intent = new Intent(SignActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(SignActivity.this,"Could not insert user, maybe user not unique",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SignActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
