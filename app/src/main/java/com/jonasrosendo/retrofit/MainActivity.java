package com.jonasrosendo.retrofit;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jonasrosendo.retrofit.api.ICEPService;
import com.jonasrosendo.retrofit.model.CEP;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_retrieve;
    private TextView txv_result;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_retrieve = findViewById(R.id.btn_retrieve);
        txv_result   = findViewById(R.id.txv_result);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveCEPRetrofit();
            }
        });
    }

    private void retrieveCEPRetrofit(){

        ICEPService icepService = retrofit.create(ICEPService.class);
        Call<CEP> cepCall = icepService.retrieveCEP("01310100");

        cepCall.enqueue(new Callback<CEP>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<CEP> call, Response<CEP> response) {
                if(response.isSuccessful()){
                    CEP cep = response.body();
                    if (cep != null) {
                        txv_result.setText(cep.getCep() + ", " + cep.getBairro());
                    }
                }
            }

            @Override
            public void onFailure(Call<CEP> call, Throwable t) {

            }
        });
    }
}
