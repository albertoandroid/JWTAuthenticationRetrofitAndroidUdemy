package com.androiddesdecero.jwtudemy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.androiddesdecero.jwtudemy.api.WebServiceApi;
import com.androiddesdecero.jwtudemy.api.WebServiceJWT;
import com.androiddesdecero.jwtudemy.model.Login;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etUserName;
    private EditText etPassword;
    private Button btToken;
    private Button btObtenerRecurso;
    private Button btSolicitudTokenErroneo;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUpView();
    }

    private void setUpView(){
        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
        btToken = findViewById(R.id.btToken);
        btToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                obtenerToken();
            }
        });
    }

    private void obtenerToken(){
        Login login = new Login();
        login.setUser(etUserName.getText().toString());
        login.setPassword(etPassword.getText().toString());
        Call<List<String>> call = WebServiceJWT
                .getInstance()
                .createService(WebServiceApi.class)
                .obtenerToken(login);

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code()==200){
                    token = response.body().get(0).toString();
                    Log.d("TAG1", "El token es: " + token);
                }else if(response.code()==401){
                    Log.d("TAG1", "No Autorizado");
                }else{
                    Log.d("TAG1", "No obtenido token");
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });

    }
}



















