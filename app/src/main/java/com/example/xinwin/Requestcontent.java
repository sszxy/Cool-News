package com.example.xinwin;

import android.text.TextUtils;

import com.example.xinwin.db.citydb;
import com.example.xinwin.db.countrydb;
import com.example.xinwin.db.provincesdb;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 张翔宇 on 2018/4/15.
 */

public class Requestcontent  {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
        Gson gson=new Gson();
        List<provinces> provincesList=new ArrayList<>();
        provincesList=gson.fromJson(response,new TypeToken<List<provinces>>(){}.getType());
        for(int i=0;i<provincesList.size();i++){
            provincesdb p=new provincesdb();
            p.setProvinceName(provincesList.get(i).getName());
            p.setProvinceCode(provincesList.get(i).getId());
            p.save();
        }
            return true;
      }
        return false;
    }
    public static boolean handleCityResponse(String response,int PreovinceID){
        if(!TextUtils.isEmpty(response)){
            Gson gson=new Gson();
            List<city> citylist=new ArrayList<>();
            citylist=gson.fromJson(response,new TypeToken<List<city>>(){}.getType());
            for(city cityobject:citylist){
                citydb c=new citydb();
                c.setCityname(cityobject.getName());
                c.setCitycode(cityobject.getId());
                c.setProvinceID(PreovinceID);
                c.save();
            }
            return true;
        }
        return false;
    }
    public static boolean handleCountryResponse(String response,int cityID){
        if(!TextUtils.isEmpty(response)){
            Gson gson=new Gson();
            List<country> countrylist=new ArrayList<>();
            countrylist=gson.fromJson(response,new TypeToken<List<country>>(){}.getType());
            for(country countryobject:countrylist){
                countrydb c=new countrydb();
                c.setCountryname(countryobject.getName());
                c.setCountrycode(countryobject.getId());
                c.setCityID(cityID);
                c.setWeatherID(countryobject.getWeather_id());
                c.save();
            }
            return true;
        }
        return false;
    }
}
