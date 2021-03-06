package com.ft.first_interview.PictureSelector;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.ft.first_interview.R;

import java.util.ArrayList;

public class PlusImageActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener, View.OnClickListener{

    private ViewPager viewPager; //展示图片的ViewPager
    private TextView positionTv; //图片的位置，第几张图片
    private ArrayList<String> imgList; //图片的数据源
    private int mPosition; //第几张图片
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_image);
        getSupportActionBar().hide();

        imgList = getIntent().getStringArrayListExtra(PictureSelectorConstant.IMG_LIST);
        mPosition = getIntent().getIntExtra(PictureSelectorConstant.POSITION, 0);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        positionTv = (TextView) findViewById(R.id.position_tv);
        findViewById(R.id.back_iv).setOnClickListener(this);
        findViewById(R.id.delete_iv).setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);

        mAdapter = new ViewPagerAdapter(this, imgList);
        viewPager.setAdapter(mAdapter);
        positionTv.setText(mPosition + 1 + "/" + imgList.size());
        viewPager.setCurrentItem(mPosition);
    }

    //删除图片
//    private void deletePic() {
//        CancelOrOkDialog dialog = new CancelOrOkDialog(this, "要删除这张图片吗?") {
//            @Override
//            public void ok() {
//                super.ok();
//                imgList.remove(mPosition); //从数据源移除删除的图片
//                setPosition();
//                dismiss();
//            }
//        };
//        dialog.show();
//    }

    private void deletePic(){
        //先new出一个监听器，设置好监听
        DialogInterface.OnClickListener dialogOnclicListener=new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which){
                    case Dialog.BUTTON_POSITIVE:
                        imgList.remove(mPosition); //从数据源移除删除的图片
                        setPosition();
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        break;
                }
            }
        };
        //dialog参数设置
        AlertDialog.Builder builder=new AlertDialog.Builder(this);  //先得到构造器
        builder.setTitle(null); //设置标题
        builder.setMessage("是否确认删除?"); //设置内容
        builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确认",dialogOnclicListener);
        builder.setNegativeButton("取消", dialogOnclicListener);
        builder.setCancelable(false);
        builder.create().show();
    }

    //设置当前位置
    private void setPosition() {
        if(imgList.size() == 0) {
            back();
        }
        else {
            positionTv.setText(mPosition + 1 + "/" + imgList.size());
            viewPager.setCurrentItem(mPosition);
            mAdapter.notifyDataSetChanged();
        }
    }

    //返回上一个页面
    private void back() {
        Intent intent = getIntent();
        intent.putStringArrayListExtra(PictureSelectorConstant.IMG_LIST, imgList);
        setResult(PictureSelectorConstant.RESULT_CODE_VIEW_IMG, intent);
        finish();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mPosition = position;
        positionTv.setText(position + 1 + "/" + imgList.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_iv:
                //返回
                back();
                break;
            case R.id.delete_iv:
                //删除图片
                deletePic();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //按下了返回键
            back();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
