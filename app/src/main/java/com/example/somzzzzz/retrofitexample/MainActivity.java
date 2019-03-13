package com.example.somzzzzz.retrofitexample;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.zip.Checksum;

import Model.CheckSum;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "RAJ";

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=findViewById(R.id.result);

        final Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);


        api.getCheckSum().enqueue(new Callback<CheckSum>() {
            @Override
            public void onResponse(Call<CheckSum> call, Response<CheckSum> response) {

                mTextView.setText(response.body().toString());
                CheckSum checksum=response.body();
                Log.d(Tag, "onResponse: "+checksum.getCHECKSUMHASH());

            }

            @Override
            public void onFailure(Call<CheckSum> call, Throwable t) {

                mTextView.setText(t.getMessage());

            }
        });

    }
}
