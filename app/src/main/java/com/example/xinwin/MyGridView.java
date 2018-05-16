package com.example.xinwin;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by 张翔宇 on 2018/4/16.
 */

public class MyGridView extends GridView {
    public int longmDragResponse=1000;//响应时间
    private boolean isDrag = false;//是否拖动
    //private Vibrator mVibrator;
    private int mDragPosition;
    //拖拽的View
    private View mDragView;
    private WindowManager mWindowManager=(WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);;
    private WindowManager.LayoutParams layoutParams;
    private ImageView mDragMirrorView;
    private Bitmap bitmap;
    //按下的点到Item自身的距离；
    private int mPoint2ItemLeft;
    private int mPoint2ItemTop;
    //距离边界的距离
    private int mOffset2Top;
    private int mOffset2Left;
    private int downx;
    private int downy;
    private int mMoveX;
    private int mMoveY;
    private OnItemChangeListener listener;
    private OnItemChangeListener changeListener;
    Handler handler=new Handler();
    Runnable runnable1=new Runnable() {
        @Override
        public void run() {
            isDrag=true;
            mDragView.setVisibility(INVISIBLE);
            createBitmap(bitmap,downx,downy);
        }
    };
    public MyGridView(Context context) {
        super(context);
    }

    public MyGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void createBitmap(Bitmap bitmap,int downx,int downy){
        layoutParams=new WindowManager.LayoutParams();
        layoutParams.format = PixelFormat.TRANSLUCENT;
        layoutParams.gravity = Gravity.TOP|Gravity.LEFT;
        layoutParams.x=mOffset2Left+(downx-mPoint2ItemLeft);
        layoutParams.y=mOffset2Top+(downy-mPoint2ItemTop);
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        mDragMirrorView=new ImageView(getContext());
        mDragMirrorView.setImageBitmap(bitmap);
        mWindowManager.addView(mDragMirrorView,layoutParams);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                downx= (int) ev.getX();
                downy= (int) ev.getY();
                mDragPosition=pointToPosition(downx,downy);
                if(mDragPosition==INVALID_POSITION){
                    return super.dispatchTouchEvent(ev);
                }//无效图标，返回
                handler.postDelayed(runnable1,longmDragResponse);
                mDragView=getChildAt(mDragPosition-getFirstVisiblePosition());
                if (mDragView == null){
                    return super.dispatchTouchEvent(ev);
                }
                mOffset2Left= (int) ev.getRawX()-downx;
                mOffset2Top= (int) ev.getRawY()-downy;
                mPoint2ItemLeft=downx-mDragView.getLeft();
                mPoint2ItemTop=downy-mDragView.getTop();
                mDragView.setDrawingCacheEnabled(true);
                bitmap=Bitmap.createBitmap(mDragView.getDrawingCache());
                mDragView.destroyDrawingCache();
                break;
            case MotionEvent.ACTION_MOVE:
                mMoveX= (int) ev.getX();
                mMoveY= (int) ev.getY();
                if(!isTouchInItem(mDragView,mMoveX,mMoveY)){
                    handler.removeCallbacks(runnable1);
                }
                break;
            case MotionEvent.ACTION_UP:
                handler.removeCallbacks(runnable1);
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isTouchInItem(View v,int x,int y){
        if(v==null){
            return false;
        }
        else if(v.getLeft()<x&&x<v.getRight()&&v.getTop()<y&&y<v.getBottom()){
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(isDrag&&mDragMirrorView!=null){
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                return super.onTouchEvent(ev);
            case MotionEvent.ACTION_MOVE:
                mMoveX= (int) ev.getX();
                mMoveY= (int) ev.getY();
                initDragview(mMoveX,mMoveY);
                break;
            case MotionEvent.ACTION_UP:
                onStopDrag();
                isDrag=false;
                break;
         }
        }
        return super.onTouchEvent(ev);

    }
    public void initDragview(int mMoveX,int mMoveY){
        layoutParams.x=mMoveX+mOffset2Left-mPoint2ItemLeft;
        layoutParams.y=mMoveY+mOffset2Top-mPoint2ItemTop;
        mWindowManager.updateViewLayout(mDragMirrorView,layoutParams);
        onSwapitem(mMoveX,mMoveY);
    }
    private void onSwapitem(int mMoveX, int mMoveY) {
        int temposition=pointToPosition(mMoveX,mMoveY);
        if(temposition!=INVALID_POSITION&&temposition!=mDragPosition){
            if(changeListener!=null){
                changeListener.onchange(mDragPosition,temposition);
            }
            getChildAt(temposition-getFirstVisiblePosition()).setVisibility(INVISIBLE);
            getChildAt(mDragPosition-getFirstVisiblePosition()).setVisibility(VISIBLE);
            mDragPosition=temposition;
        }
    }
    public void onStopDrag(){
        View view=getChildAt(mDragPosition-getFirstVisiblePosition());
        if(view!=null)
            view.setVisibility(VISIBLE);
        removedramImage();
    }

    private void removedramImage() {
        if(mDragMirrorView!=null){
            mWindowManager.removeView(mDragMirrorView);
            mDragView=null;
        }
    }
    public void setItemchangerlistener(OnItemChangeListener listener){
        this.listener=listener;
    }
    public interface OnItemChangeListener{
        void onchange(int x,int y);
    }
}
