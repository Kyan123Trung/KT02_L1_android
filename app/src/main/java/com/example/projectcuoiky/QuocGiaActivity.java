package com.example.projectcuoiky;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class QuocGiaActivity extends AppCompatActivity {
    Spinner spinner_chauluc;
    ArrayList<String> dsChauLuc = new ArrayList<>();
    ArrayList<String> lstQGChauPhi = new ArrayList<>();
    ArrayList<String> lstQGChauA = new ArrayList<>();
    ArrayList<String> lstQGChauAu = new ArrayList<>();
    ArrayList<String> lstQGChauMy = new ArrayList<>();
    ArrayList<String> lstQGChauUc = new ArrayList<>();
    ArrayList<QuocGia> lstquocgia = new ArrayList<>();
    String url = "http://10.7.132.187/data.json";
    ListView lvDSQuocGia;
    String chauluc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quoc_gia);
        addControls();
        addEvents();
    }
    public void requestAPI(String url){
        RequestQueue queue= Volley.newRequestQueue(QuocGiaActivity.this);
        StringRequest stringRequest =new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                    parseJsonData(response);
                }catch(JSONException e){
                    throw new RuntimeException(e);
                }
            }
            }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
    private void parseJsonData(String response) throws JSONException{
        JSONArray quocGiaArray = new JSONArray(response);
        for (int i = 0; i < quocGiaArray.length(); i++){
            JSONObject qgObject = quocGiaArray.getJSONObject(i);
            QuocGia quocgia = new QuocGia();
            if(qgObject.has("flags")){
                JSONObject qgSubObject = qgObject.getJSONObject("flags");
                quocgia.quocky = qgSubObject.getString("png");
            }
            if(qgObject.has("name")){
                JSONObject qgSubObject = qgObject.getJSONObject("name");
                quocgia.nameCommon = qgSubObject.getString("common");
                quocgia.nameOfficial = qgSubObject.getString("official");
            }
            if(qgObject.has("capital")){
                JSONArray qgSubArray = qgObject.getJSONArray("capital");
                quocgia.capital = qgSubArray.getString(0);
            }
            quocgia.population = qgObject.getInt("population");
            if(qgObject.has("languages")){
                String languages = "";
                String[] qgSubArray = qgObject.getString("languages").substring(1,qgObject.getString("languages").length()-1).split(",");
                for (int j = 0; j < qgSubArray.length; j++){
                    String[] qgSubEndArray = qgSubArray[j].split(":");
                    languages += ' ' + qgSubEndArray[1].substring(1,qgSubEndArray[1].length()-1);
                    quocgia.languages = languages;
                }
            }
            if(qgObject.has("continents")){
                JSONArray qgSubArray = qgObject.getJSONArray("continents");
                quocgia.chauluc = qgSubArray.getString(0);
                if (Objects.equals(qgSubArray.getString(0), "Asia")){
                    lstQGChauA.add(quocgia.nameCommon);
                }
                if (Objects.equals(qgSubArray.getString(0), "Europe")){
                    lstQGChauAu.add(quocgia.nameCommon);
                }
                if (Objects.equals(qgSubArray.getString(0), "Africa")){
                    lstQGChauPhi.add(quocgia.nameCommon);
                }
                if (Objects.equals(qgSubArray.getString(0), "Oceania")){
                    lstQGChauUc.add(quocgia.nameCommon);
                }
                if (qgSubArray.getString(0).contains("America")){
                    lstQGChauMy.add(quocgia.nameCommon);
                }
            }
            lstquocgia.add(quocgia);
        }
    }
    private void addControls(){
        spinner_chauluc = (Spinner)findViewById(R.id.spinner);
        lvDSQuocGia = (ListView)findViewById(R.id.lvCountry);
    }
    private void addEvents(){
        requestAPI(url);
        initData();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,dsChauLuc);
        spinner_chauluc.setAdapter(adapter);
        spinner_chauluc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chauluc = dsChauLuc.get(i);
                if (chauluc == "Asia"){
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQGChauA);
                    lvDSQuocGia.setAdapter(adapter);
                }
                if (chauluc == "Europe"){
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQGChauAu);
                    lvDSQuocGia.setAdapter(adapter);
                }
                if (chauluc == "Africa"){
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQGChauPhi);
                    lvDSQuocGia.setAdapter(adapter);
                }
                if (chauluc == "Oceania"){
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQGChauUc);
                    lvDSQuocGia.setAdapter(adapter);
                }
                if (chauluc == "America"){
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,lstQGChauMy);
                    lvDSQuocGia.setAdapter(adapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        lvDSQuocGia.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String nameQG = null;
                if (chauluc == "Asia"){
                    nameQG = lstQGChauA.get(i);
                }
                if (chauluc == "Europe"){
                    nameQG= lstQGChauAu.get(i);
                }
                if (chauluc == "Africa"){
                    nameQG = lstQGChauPhi.get(i);
                }
                if (chauluc == "Oceania"){
                    nameQG = lstQGChauUc.get(i);
                }
                if (chauluc == "America"){
                    nameQG = lstQGChauMy.get(i);
                }
                for (QuocGia qg: lstquocgia){
                    if (qg.nameCommon == nameQG){
                        Intent intent = new Intent(QuocGiaActivity.this, DetailActivity.class);
                        intent.putExtra("nameCommon",qg.nameCommon);
                        intent.putExtra("nameOfficial",qg.nameOfficial);
                        intent.putExtra("quocky",qg.quocky);
                        intent.putExtra("language",qg.languages);
                        intent.putExtra("capital",qg.capital);
                        intent.putExtra("population",qg.population);
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private void initData(){
        dsChauLuc.add("Asia");
        dsChauLuc.add("Africa");
        dsChauLuc.add("Europe");
        dsChauLuc.add("Oceania");
        dsChauLuc.add("America");
    }
}