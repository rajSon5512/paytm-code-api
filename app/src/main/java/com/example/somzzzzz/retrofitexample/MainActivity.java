package com.example.somzzzzz.retrofitexample;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;
import java.util.zip.Checksum;

import Model.CheckSum;
import okio.BufferedSink;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String Tag = "RAJ";

    private TextView mTextView;
    private String mId;

    private Button checkSumbutton;
    private CheckSum mCheckSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView=findViewById(R.id.result);
        checkSumbutton=findViewById(R.id.checkSum);

         Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=retrofit.create(Api.class);

        api.getCheckSum().enqueue(new Callback<CheckSum>() {
            @Override
            public void onResponse(Call<CheckSum> call, Response<CheckSum> response) {

                mTextView.setText(response.body().toString());
                mCheckSum=response.body();
                Log.d(Tag, "onResponse: "+mCheckSum.getCHECKSUMHASH());
                mId=mCheckSum.getMID();
                Log.d(Tag, "onResponse: "+mCheckSum.getMID());

            }

            @Override
            public void onFailure(Call<CheckSum> call, Throwable t) {

                mTextView.setText(t.getMessage());

            }
        });



        checkSumbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit=new Retrofit.Builder()
                        .baseUrl(Api.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api=retrofit.create(Api.class);

                api.getResponse(mCheckSum).enqueue(new Callback<Model.Response>() {
                    @Override
                    public void onResponse(Call<Model.Response> call, Response<Model.Response> response) {

                        if(call.isExecuted()){

                            int rcode=response.code();

                            if(rcode==200){

                                mTextView.setText("Verify CheckSum");

                            }else{

                                mTextView.setText("Invalid CheckSum");
                            }


                        }


                    }

                    @Override
                    public void onFailure(Call<Model.Response> call, Throwable t) {

                        Log.d(Tag, "onFailure: "+t.getMessage());
                    }
                });


            }
        });
    }
}