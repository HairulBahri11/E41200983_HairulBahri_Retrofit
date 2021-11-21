package com.example.myretrofit.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myretrofit.API.APIRequestData;
import com.example.myretrofit.API.RetroServer;
import com.example.myretrofit.Adapter.AdapterData;
import com.example.myretrofit.Model.DataModel;
import com.example.myretrofit.Model.ResponseModel;
import com.example.myretrofit.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //menarik data dari database ke android

    private RecyclerView rvData;
    private RecyclerView.Adapter adData;
    private RecyclerView.LayoutManager lmData;
    private List<DataModel> listData = new ArrayList<>();
    private SwipeRefreshLayout swl;
    private ProgressBar pb;

    private FloatingActionButton fab_tambah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvData = findViewById(R.id.rv_data);
        swl = findViewById(R.id.swl_data);
        pb = findViewById(R.id.pb_data);
        fab_tambah = findViewById(R.id.fab_add);

        lmData = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvData.setLayoutManager(lmData);
        retrieveData();

        swl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swl.setRefreshing(true);
                retrieveData();
                swl.setRefreshing(false);
            }
        });

        fab_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TambahActivity.class));
            }
        });
    }

    public void retrieveData(){
        pb.setVisibility(View.VISIBLE);
        APIRequestData ard = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModel> tampilData = ard.ardRetriveData();
//        antrikan perintah data
        tampilData.enqueue(new Callback<ResponseModel>() {
            @Override
            //menangkap data jika berhasil
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
//                Toast.makeText(MainActivity.this, "Kode : " + kode + " || Pesan : " + pesan, Toast.LENGTH_SHORT).show();
                listData = response.body().getData();
                adData = new AdapterData(MainActivity.this, listData);
                rvData.setAdapter(adData);
                adData.notifyDataSetChanged();

                pb.setVisibility(View.INVISIBLE);
            }
            //menangkap jika ada gagal
            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Koneksi Gagal" +t.getMessage(), Toast.LENGTH_SHORT).show();
                pb.setVisibility(View.INVISIBLE);
            }
        });

    }
}