package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {
    ImageView imgQuocKy;
    TextView tvCommon, tvOfficial, tvPopulation, tvCapital, tvLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        addControl();
        addEvent();
    }
    private void addControl(){
        imgQuocKy = (ImageView)findViewById(R.id.imgQuocKi);
        tvCommon = (TextView)findViewById(R.id.tvCommon);
        tvOfficial = (TextView)findViewById(R.id.tvOfficial);
        tvPopulation = (TextView)findViewById(R.id.tvPopulation);
        tvCapital = (TextView)findViewById(R.id.tvCapital);
        tvLanguage = (TextView)findViewById(R.id.tvLanguage);
    }
    private void addEvent(){
        Intent intent = getIntent();
        String nameCommon = intent.getStringExtra("nameCommon");
        String nameOfficial = intent.getStringExtra("nameOfficial");
        int population = intent.getIntExtra("population",0);
        String capital = intent.getStringExtra("capital");
        String language = intent.getStringExtra("language");
        String img = intent.getStringExtra("quocky");
        Picasso.with(this).load(img).into(imgQuocKy);
        tvCommon.setText(nameCommon);
        tvOfficial.setText(nameOfficial);
        tvPopulation.setText(String.valueOf(population));
        tvCapital.setText(capital);
        tvLanguage.setText(language);
    }
}