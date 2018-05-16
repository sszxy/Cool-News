package com.example.xinwin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    RecyclerView recyclerView;
    View view1,view2,view3,view4,view5,view6;
    List<news.ResultBean.ListBean> newslists1=new ArrayList<>();
    String address;
    List<View> viewList=new ArrayList<>();
    List<String> tablist=new ArrayList<>();
    SwipeRefreshLayout swipeRefreshLayout;
    Handler handler=new Handler();
    DrawerLayout drawerLayout;
    int selection;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Toolbar toolbar;
    RelativeLayout relativeLayoutcolor;
    ImageView addimageView;
    LayoutInflater inflater;
    TextView placenametv,placetem;
    LinearLayout linearLayout;
    NavigationView navigationView;
    View headerlayout;
    ImageView imageViewweather;
    ImageView myhome;
    ImageView addimg;




    @RequiresApi( api = Build.VERSION_CODES.M)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);
        inflater=getLayoutInflater();
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        editor=sharedPreferences.edit();
        selection=sharedPreferences.getInt("selection",0);
        toolbar= (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        initview();
        initviewpager();
        initdrawerlayout();
        switch (selection){
            case 0:initcolor(R.color.Appthemecolor_green);
                break;
            case 1:initcolor(R.color.Appthemecolor_red);
                break;
            case 2:initcolor(R.color.colorAccent);
                break;
            case 3:initcolor(R.color.Appthemecolor_purple);
                break;
            case 4:initcolor(R.color.Appthemecolor_blue);
                break;
            case 5:initcolor(R.color.Appthemecolor_yellow);
                break;
        }
        Myviewpageradapter myviewpageradapter=new Myviewpageradapter(viewList,tablist);
        viewPager.setAdapter(myviewpageradapter);
        tabLayout.setupWithViewPager(viewPager);
    }
    public void initview(){
        inflater=getLayoutInflater();//、新闻、财经、体育、娱乐、军事、教育
        tablist.add("头条");
        tablist.add("新闻");
        tablist.add("财经");
        tablist.add("体育");
        tablist.add("娱乐");
        tablist.add("军事");
        viewPager= (ViewPager) findViewById(R.id.myviewpager);
        view1=inflater.inflate(R.layout.tab1,null);
        view2=inflater.inflate(R.layout.tab2,null);
        view3=inflater.inflate(R.layout.tab3,null);
        view4=inflater.inflate(R.layout.tab4,null);
        view5=inflater.inflate(R.layout.tab5,null);
        view6=inflater.inflate(R.layout.tab6,null);
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);
        viewList.add(view5);
        viewList.add(view6);
        tabLayout= (TabLayout) findViewById(R.id.mytablayout);
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());
        relativeLayoutcolor= (RelativeLayout) findViewById(R.id.colorrelativelayout);
        addimageView= (ImageView) findViewById(R.id.addimg);
       // placenametv=headerview.findViewById(R.id.placename);
        //placetem=headerview.findViewById(R.id.placetem);
        linearLayout= (LinearLayout) findViewById(R.id.colorlinearlayout);
        navigationView= (NavigationView) findViewById(R.id.nav_view);
        headerlayout=navigationView.inflateHeaderView(R.layout.headerlayout);
        placenametv=headerlayout.findViewById(R.id.placename);
        placetem=headerlayout.findViewById(R.id.placetem);
        imageViewweather=headerlayout.findViewById(R.id.weatherimg);
        myhome= (ImageView) findViewById(R.id.myhomeimg);
        myhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        addimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SelectActivity.class);
                intent.putStringArrayListExtra("category", (ArrayList<String>) tablist);
                startActivityForResult(intent,3);
            }
        });

        //tabLayout.setupWithViewPager(viewPager);
    }
    public void initviewpager(){
        for(int i=0;i<viewList.size();i++){
            address="http://api.jisuapi.com/news/get?channel="+tablist.get(i)+"&start=0&num=40&appkey=14b9de4ec2f81bd8";
            initrecyclerview(viewList.get(i),recyclerView,i,swipeRefreshLayout);
        }
    }
    public void initrecyclerview(View view, RecyclerView recyclerViewss, final int i,SwipeRefreshLayout refreshLayout){
        recyclerViewss=view.findViewById(R.id.myrecycleview);
        refreshLayout=view.findViewById(R.id.refreshlayout);
        refreshLayout.setColorSchemeResources(R.color.colorAccent);
        getresponse(address,recyclerViewss,i,refreshLayout);

    }
    public void getresponse(final String address, final RecyclerView recycler, final int i, final SwipeRefreshLayout refreshLayout){
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responsestring=response.body().string();
                final Gson gson=new Gson();
                final int[] k = new int[1];
                news news1=gson.fromJson(responsestring,news.class);
                newslists1=news1.getResult().getList();
                final List<news.ResultBean.ListBean>[] listBeen = new List[]{new ArrayList<news.ResultBean.ListBean>()};
                for(k[0] =0; k[0] <10; k[0]++)
                {
                    listBeen[0].add(newslists1.get(k[0]));
                }

                final int[] count = {k[0] + 10};
                final int[] finalK = {k[0]};
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        final Myrecycleadapter myrecycleadapter=new Myrecycleadapter(listBeen[0],MainActivity.this);
                        myrecycleadapter.setOnItemClickListener(new Myrecycleadapter.onItemClickListener() {
                            @Override
                            public void onItemclick(View v, int position) {
                                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                                String url=newslists1.get(position).getUrl();
                                String picurl=newslists1.get(position).getPic();
                                String title=tablist.get(i);
                                intent.putExtra("url",url);
                                intent.putExtra("picurl",picurl);
                                intent.putExtra("title",title);
                                startActivity(intent);
                            }
                        });
                        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                HttpUtil.sendOkHttpRequest(address, new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Toast.makeText(MainActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        String strng=response.body().string();
                                        Gson gsonnow=new Gson();
                                        news newsnow=gson.fromJson(strng,news.class);
                                        newslists1=newsnow.getResult().getList();
                                        listBeen[0].clear();
                                        for(k[0] =0; k[0] <10; k[0]++)
                                        {
                                            listBeen[0].add(newslists1.get(k[0]));
                                        }
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                myrecycleadapter.notifyDataSetChanged();
                                                refreshLayout.setRefreshing(false);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                        LinearLayoutManager manager=new LinearLayoutManager(MainActivity.this);
                        recycler.setLayoutManager(manager);
                        recycler.setAdapter(myrecycleadapter);
                        recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            boolean isloading=false;
                            int nowlastitem;
                            @Override
                            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if(newState==RecyclerView.SCROLL_STATE_IDLE&&nowlastitem+1==myrecycleadapter.getItemCount()){
                                    if(!isloading){
                                        isloading=true;

                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                if(count[0] ==50){
                                                    myrecycleadapter.sethasmore(false);
                                                    //myrecycleadapter.notifyDataSetChanged();
                                                    myrecycleadapter.notifyItemRemoved(myrecycleadapter.getItemCount());
                                                    //myrecycleadapter.notifyItemChanged(myrecycleadapter.getItemCount(),myrecycleadapter.getItemCount());
                                                   /* try {
                                                        Thread.sleep(200);

                                                    } catch (InterruptedException e) {
                                                        e.printStackTrace();
                                                    }*/

                                                }else {
                                                for(; finalK[0] <count[0]; finalK[0]++){
                                                    listBeen[0].add(newslists1.get(finalK[0]));
                                                }
                                                   myrecycleadapter.notifyDataSetChanged();
                                                    myrecycleadapter.notifyItemRemoved(myrecycleadapter.getItemCount());
                                                    count[0] +=10;
                                                }

                                                isloading=false;
                                            }
                                        },1000);

                                    }
                                }
                            }

                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);
                                LinearLayoutManager layoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();
                                nowlastitem=layoutManager.findLastVisibleItemPosition();
                            }
                        });
                    }
                });
            }
        });

    }
    public void initdrawerlayout(){
        drawerLayout= (DrawerLayout) findViewById(R.id.mydrawerlayout);
        NavigationView navigationView= (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.color:
                        Intent intent= new Intent(MainActivity.this,colorActivity.class);
                        intent.putExtra("selection",selection);
                        startActivityForResult(intent,1);
                        break;
                    case R.id.weather:
                        Intent intent1=new Intent(MainActivity.this,weatheractivity.class);
                        startActivityForResult(intent1,2);
                }
                return true;
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:if(resultCode==RESULT_OK){
                selection=data.getIntExtra("selection",0);
                switch (selection){
                    case 0:initcolor(R.color.Appthemecolor_green);
                        break;
                    case 1:initcolor(R.color.Appthemecolor_red);
                        break;
                    case 2:initcolor(R.color.colorAccent);
                        break;
                    case 3:initcolor(R.color.Appthemecolor_purple);
                        break;
                    case 4:initcolor(R.color.Appthemecolor_blue);
                        break;
                    case 5:initcolor(R.color.Appthemecolor_yellow);
                        break;
                }
                editor.putInt("selection",selection);
                editor.apply();
                break;
            }
            case 2:if(resultCode==RESULT_OK){
                Log.d("tag","hh");
                String weatherID=data.getStringExtra("weatherid");
                Log.d("tag",weatherID);
                final String countryname=data.getStringExtra("countryname");
                Log.d("tag",countryname);
                String weatheraddress="http://guolin.tech/api/weather?cityid="+weatherID+"&key=f635f4ea488e49b98965b08d1968ca04";
                HttpUtil.sendOkHttpRequest(weatheraddress, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(final Call call, Response response) throws IOException {
                        String responseweather=response.body().string();
                        Gson gson=new Gson();
                        weather weatherobject=gson.fromJson(responseweather, com.example.xinwin.weather.class);
                        List<weather.HeWeatherBean> list=weatherobject.getHeWeather();
                        final weather.HeWeatherBean weatherBean=list.get(0);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                placenametv.setText(countryname);
                                placetem.setText(weatherBean.getNow().getTmp());
                                String cond=weatherBean.getNow().getCond_txt();
                                switch (cond){
                                    case "晴":imageViewweather.setImageResource(R.drawable.qing);
                                        break;
                                    case "多云":imageViewweather.setImageResource(R.drawable.duoyun);
                                        break;
                                    case "晴间多云":imageViewweather.setImageResource(R.drawable.duoyun);
                                        break;
                                    case "小雨":imageViewweather.setImageResource(R.drawable.yutian);
                                        break;
                                    case "中雨":imageViewweather.setImageResource(R.drawable.yutian);
                                        break;
                                    case "大雨":imageViewweather.setImageResource(R.drawable.yutian);
                                        break;
                                    case "雷阵雨":imageViewweather.setImageResource(R.drawable.yutian);
                                        break;
                                    case "阴":imageViewweather.setImageResource(R.drawable.yin);
                                        break;
                                }
                            }
                        });
                    }
                });
            }
            case 3: if (resultCode==RESULT_OK){
                tablist=data.getStringArrayListExtra("itemlist");
                initviewpager();
            }
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initcolor(int colorid){
                toolbar.setBackgroundColor(getColor(colorid));
                relativeLayoutcolor.setBackgroundColor(getColor(colorid));
                tabLayout.setBackgroundColor(getColor(colorid));
                addimageView.setBackgroundColor(getColor(colorid));
                linearLayout.setBackgroundColor(getColor(colorid));
    }
}
