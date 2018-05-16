package com.example.xinwin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class colorActivity extends AppCompatActivity implements View.OnClickListener{
    Toolbar toolbar;
    int selection;
    TextView textView;
    ImageView view1,view2;
    RelativeLayout relativeLayout1,relativeLayout2,relativeLayout3,relativeLayout4,relativeLayout5,relativeLayout6;
    int idsrc[]=new int[]{R.id.green,R.id.red,R.id.Accent,R.id.purple,R.id.blue,R.id.yellow};
    @RequiresApi(api =  Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decorView=getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_color);
        Intent intent=getIntent();
        selection=intent.getIntExtra("selection",0);
        init();
        initcolor();
    }
    public void init(){
        toolbar=(Toolbar) findViewById(R.id.toolbarcolor);
        setSupportActionBar(toolbar);
        ActionBar bar=getSupportActionBar();
        if(bar!=null){
            bar.setDisplayHomeAsUpEnabled(true);
        }
        relativeLayout1= (RelativeLayout) findViewById(R.id.greenparent);
        relativeLayout2= (RelativeLayout) findViewById(R.id.redparent);
        relativeLayout3= (RelativeLayout) findViewById(R.id.Accentparent);
        relativeLayout4= (RelativeLayout) findViewById(R.id.purpleparent);
        relativeLayout5= (RelativeLayout) findViewById(R.id.blueparent);
        relativeLayout6= (RelativeLayout) findViewById(R.id.yelloparent);
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        relativeLayout4.setOnClickListener(this);
        relativeLayout5.setOnClickListener(this);
        relativeLayout6.setOnClickListener(this);
        for(int i=0;i<6;i++){
            ImageView imageView= (ImageView) findViewById(idsrc[i]);
            if(i==selection){
                imageView.setVisibility(View.VISIBLE);
            }
        }
        textView= (TextView) findViewById(R.id.queding);
        textView.setOnClickListener(this);
        view1= (ImageView) findViewById(R.id.imagecircle1);
        view2= (ImageView) findViewById(R.id.imagecircle2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.greenparent:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_green));
                selection=0;
                for(int i=0;i<6;i++){
                ImageView imageView= (ImageView) findViewById(idsrc[i]);
                if(i==0){
                    imageView.setVisibility(View.VISIBLE);
                }else {
                    imageView.setVisibility(View.GONE);
                }
            }
                break;
            case R.id.redparent:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_red));
                selection=1;
                for(int i=0;i<6;i++){
                    ImageView imageView= (ImageView) findViewById(idsrc[i]);
                    if(i==1){
                        imageView.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.Accentparent:
                toolbar.setBackgroundColor(getColor(R.color.colorAccent));
                selection=2;
                for(int i=0;i<6;i++){
                    ImageView imageView= (ImageView) findViewById(idsrc[i]);
                    if(i==2){
                        imageView.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.purpleparent:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_purple));
                selection=3;
                for(int i=0;i<6;i++){
                    ImageView imageView= (ImageView) findViewById(idsrc[i]);
                    if(i==3){
                        imageView.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.blueparent:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_blue));
                selection=4;
                for(int i=0;i<6;i++){
                    ImageView imageView= (ImageView) findViewById(idsrc[i]);
                    if(i==4){
                        imageView.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.yelloparent:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_yellow));
                selection=5;
                for(int i=0;i<6;i++){
                    ImageView imageView= (ImageView) findViewById(idsrc[i]);
                    if(i==5){
                        imageView.setVisibility(View.VISIBLE);
                    }else {
                        imageView.setVisibility(View.GONE);
                    }
                }
                break;
            case R.id.queding:
                Intent intent=new Intent();
                intent.putExtra("selection",selection);
                setResult(RESULT_OK,intent);
                finish();
                break;
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void initcolor(){
        switch (selection){
            case 0:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_green));
                view1.setImageResource(R.color.Appthemecolor_green);
                view2.setImageResource(R.color.Appthemecolor_green);
            case 1:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_red));
                view1.setImageResource(R.color.Appthemecolor_red);
                view2.setImageResource(R.color.Appthemecolor_red);
            case 2:
                toolbar.setBackgroundColor(getColor(R.color.colorAccent));
                view1.setImageResource(R.color.colorAccent);
                view2.setImageResource(R.color.colorAccent);
            case 3:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_purple));
                view1.setImageResource(R.color.Appthemecolor_purple);
                view2.setImageResource(R.color.Appthemecolor_purple);
            case 4:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_blue));
                view1.setImageResource(R.color.Appthemecolor_blue);
                view2.setImageResource(R.color.Appthemecolor_blue);
            case 5:
                toolbar.setBackgroundColor(getColor(R.color.Appthemecolor_yellow));
                view1.setImageResource(R.color.Appthemecolor_yellow);
                view2.setImageResource(R.color.Appthemecolor_yellow);
     }
    }
}
