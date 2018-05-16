package com.example.xinwin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SelectActivity extends AppCompatActivity {
    List<HashMap<String,Object>> datalist=new ArrayList<>();
    List<String> itemlist=new ArrayList<>();
    String gridlist[]={"头条","新闻","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"};
    MyGridView gridView;
    SimpleAdapter adapter;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        Intent intent=getIntent();
        itemlist=intent.getStringArrayListExtra("category");
        gridView= (MyGridView) findViewById(R.id.mygridview);
        initdata();
        gridView.setAdapter(adapter);
        gridView.setItemchangerlistener(new MyGridView.OnItemChangeListener() {
            @Override
            public void onchange(int from, int to) {
                HashMap<String, Object> temp = datalist.get(from);
                datalist.set(from,datalist.get(to));
                datalist.set(to,temp);
                adapter.notifyDataSetChanged();
            }
        });
        textView= (TextView) findViewById(R.id.select_queding);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent();
                intent1.putStringArrayListExtra("itemlist", (ArrayList<String>) itemlist);
                setResult(RESULT_OK,intent1);
                finish();
            }
        });

    }
    public void initdata(){
        for(int i=0;i<gridlist.length;i++){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("cancel",gridlist[i]);
            datalist.add(hashMap);
        }
         adapter=new SimpleAdapter(this,datalist,R.layout.itemgridlayout,new String[]{"cancel"},new int[]{R.id.cancel_tv});
    }

}
