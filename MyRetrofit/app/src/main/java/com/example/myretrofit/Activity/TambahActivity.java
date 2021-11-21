package com.example.myretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myretrofit.API.APIRequestData;
import com.example.myretrofit.API.RetroServer;
import com.example.myretrofit.Model.ResponseModel;
import com.example.myretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahActivity extends AppCompatActivity {

    private EditText etNama, etEmail, etPassword;
    private Button btnSimpan;
    private String nm,eml,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah);

        etNama = findViewById(R.id.edt_fullname);
        etEmail = findViewById(R.id.edt_email);
        etPassword = findViewById(R.id.edt_password);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nm = etNama.getText().toString();
                eml = etEmail.getText().toString();
                pass = etPassword.getText().toString();

                if (nm.trim().equals("")){
                    etNama.setError("Nama di isi");
                }else if (eml.trim().equals("")){
                    etEmail.setError("Email di isi");
                }else if(pass.trim().equals("")){
                    etPassword.setError("Password di isi");
                }else{
                    createData();
                }
            }
        });


    }
    private void createData(){
        APIRequestData ard = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> simpanData = ard.ardCreateData(nm, eml, pass);

        simpanData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                Toast.makeText(TambahActivity.this, "Kode : " + kode + " || Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(TambahActivity.this, "Gagal Menambahkan Data" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}