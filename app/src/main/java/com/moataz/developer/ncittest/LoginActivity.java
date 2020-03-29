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

public class LoginActivity extends AppCompatActivity {

    private EditText userName,userPass;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.name_text);
        userPass = findViewById(R.id.pass_text);

        login = findViewById(R.id.bu_login);
        login.setOnClickListener(v ->
                Login()
        );

    }

    public void Login(){

        String name = userName.getText().toString().trim();
        String pass = userPass.getText().toString().trim();

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

        Call<User> call = RetrofitClient.getInstance().getApi().Login(name,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body().getErrorId()== null){
                    Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                    startActivity(intent);
                    finish();
//                    Toast.makeText(LoginActivity.this,"sucsses",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Invalid username or password",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this,t.getMessage(),Toast.LENGTH_LONG).show();
                Log.d("error",t.getMessage());

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

}
