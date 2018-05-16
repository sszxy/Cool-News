package com.example.xinwin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xinwin.db.citydb;
import com.example.xinwin.db.countrydb;
import com.example.xinwin.db.provincesdb;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class weatheractivity extends AppCompatActivity {
    ListView listView;
    ImageView imageView;
    ArrayAdapter<String> arrayAdapter;
    List<String> datalist=new ArrayList<>();
    List<provincesdb> provincelist=new ArrayList<>();
    List<citydb> citylist=new ArrayList<>();
    List<countrydb> countrylist=new ArrayList<>();
    TextView textView;
    int currentlevel;
    public static int PROVINCE_LEVEL=0;
    public static int CITY_LEVEL=1;
    public static int COUNTRY_LEVEL=2;
    ProgressDialog progressDialog;
    provincesdb selectprovinces;
    citydb selectcity;
    countrydb selectcountry;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_weatheractivity);
        listView= (ListView) findViewById(R.id.listview);
        imageView= (ImageView) findViewById(R.id.backimg);
        textView= (TextView) findViewById(R.id.name);
        arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datalist);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(currentlevel==PROVINCE_LEVEL){
                     selectprovinces=provincelist.get(position);
                     initcity();
                }else if (currentlevel==CITY_LEVEL) {
                    selectcity = citylist.get(position);
                    initcountry();
                }else if (currentlevel==COUNTRY_LEVEL){
                    String weathrrid=countrylist.get(position).getWeatherID();
                    String countryname=countrylist.get(position).getCountryname();
                    Intent intent=new Intent();
                    intent.putExtra("weatherid",weathrrid);
                    intent.putExtra("countryname",countryname);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }
        });
        initprovince();

    }
    public void initprovince(){
        textView.setText("中国");
        provincelist= DataSupport.findAll(provincesdb.class);
        if(provincelist.size()>0){
            datalist.clear();
            for(int i=0;i<provincelist.size();i++){
                datalist.add(provincelist.get(i).getProvinceName());
            }
            arrayAdapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentlevel=PROVINCE_LEVEL;
        }else {
            String address="http://guolin.tech/api/china";
            quertFromServer(address,"province");
        }



       /* HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(weatheractivity.this,"加载失败",Toast.LENGTH_SHORT);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String datastring=response.body().string();
                Requestcontent.handleProvinceResponse(datastring);
            }
        });*/
    }
    public void initcity(){
        textView.setText(selectprovinces.getProvinceName());
        citylist= DataSupport.where("provinceID=?",String.valueOf(selectprovinces.getId())).find(citydb.class);
        if(citylist.size()>0){
            datalist.clear();
            for(int i=0;i<citylist.size();i++){
                datalist.add(citylist.get(i).getCityname());
            }
            arrayAdapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentlevel=CITY_LEVEL;
        }else {
            int provinceCode=selectprovinces.getProvinceCode();
            String address="http://guolin.tech/api/china/"+provinceCode;
            quertFromServer(address,"city");
        }
    }
    public void initcountry(){
        textView.setText(selectcity.getCityname());
        countrylist=DataSupport.where("cityID=?",String.valueOf(selectcity.getId())).find(countrydb.class);
        if(countrylist.size()>0){
            datalist.clear();
            for(countrydb country:countrylist){
                datalist.add(country.getCountryname());
            }
            arrayAdapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentlevel=COUNTRY_LEVEL;
        }
        else {
            int provinceCode=selectprovinces.getProvinceCode();
            int cityCode=selectcity.getCitycode();
            String address="http://guolin.tech/api/china/"+provinceCode+"/"+cityCode;
            quertFromServer(address,"country");
        }
    }
    public void quertFromServer(String address, final String type){
        showprogressdialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closedialog();
                        Toast.makeText(weatheractivity.this,"加载失败",Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsestring=response.body().string();
                boolean result=false;
                switch (type){
                    case "province":result=Requestcontent.handleProvinceResponse(responsestring);
                        break;
                    case "city":result=Requestcontent.handleCityResponse(responsestring,selectprovinces.getId());
                        break;
                    case "country":result=Requestcontent.handleCountryResponse(responsestring,selectcity.getId());
                        break;
                }
                if (result){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closedialog();
                            if("province".equals(type)){
                                initprovince();
                            }else if ("city".equals(type)){
                                initcity();
                            }else {
                                initcountry();
                            }
                        }
                    });
                }
            }
        });
    }
    public void showprogressdialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    public void closedialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
}
