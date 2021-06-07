package com.example.m1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    ListView mListView;
    MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadData();
    }

    private void LoadData() {
        Request request = new Request.Builder()
                .url("https://cloud.culture.tw/frontsite/trans/SearchPerformPlaceAction.do?method=doFindPerformPlaceTypeJ")
                .build();
        OkHttpClient client = new OkHttpClient();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //請求失敗時執行的程式
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                String responseString = response.body().string();
                Item[] myData = gson.fromJson(responseString, Item[].class);
                ArrayList<Item> items = new ArrayList<Item>(Arrays.asList(myData));
                mListView = findViewById(R.id.listview);
                mAdapter = new MyAdapter(getBaseContext(), items);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListView.setAdapter(mAdapter); //傳入Adapter的Instance
                    }
                });

                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Item item = mAdapter.getItem(position);
                        Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                        intent.putExtra("item", item);
                        startActivity(intent);
                    }
                });

            }
        });

    }
}