package com.ft.first_interview.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;

import com.ft.first_interview.PictureSelector.GridViewAdapter;
import com.ft.first_interview.PictureSelector.PictureSelectorConfig;
import com.ft.first_interview.PictureSelector.PictureSelectorConstant;
import com.ft.first_interview.PictureSelector.PlusImageActivity;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.P_C_D;
import com.ft.first_interview.bean.Resume;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

public class ResumeActivity extends AppCompatActivity {

    private Resume resume;

    private Switch show_or_hide;
    private EditText et_name;
    private TextView pv_sex;
    private TextView pv_age;
    private TextView pv_native;
    private TextView pv_address;
    private EditText et_phone;
    private EditText et_school;
    private EditText et_major;
    private TextView pv_record;
    private TextView pv_graduation;
    private TextView pv_place;
    private TextView pv_position;
    private TextView pv_salary;
    private EditText et_certification;
    private EditText et_self;

    private Button btn_submit;
    private Context mContext;
    private GridView gridView;
    private GridView gridView_video;
    private ArrayList<String> al;
    private ArrayList<String> mPicList = new ArrayList<>(); //上传的图片凭证的数据源
    private ArrayList<String> mVideoList = new ArrayList<>();   //上传的视频的数据源
    private GridViewAdapter mGridViewAddImgAdapter; //展示上传的图片的适配器
    private GridViewAdapter mGridViewVideoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        show_or_hide = (Switch) findViewById(R.id.show_or_hide);
        et_name = (EditText) findViewById(R.id.et_name);
        pv_sex = (TextView) findViewById(R.id.pv_sex);
        pv_age = (TextView) findViewById(R.id.pv_age);
        pv_native = (TextView) findViewById(R.id.pv_native);
        pv_address = (TextView) findViewById(R.id.pv_address);
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_school = (EditText) findViewById(R.id.et_school);
        et_major = (EditText) findViewById(R.id.et_major);
        pv_record = (TextView) findViewById(R.id.pv_record);
        pv_graduation = (TextView) findViewById(R.id.pv_graduation);
        pv_place = (TextView) findViewById(R.id.pv_place);
        pv_position = (TextView) findViewById(R.id.pv_position);
        pv_salary = (TextView) findViewById(R.id.pv_salary);
        et_certification = (EditText) findViewById(R.id.et_certification);
        et_self = (EditText) findViewById(R.id.et_self);

        pv_sex.setOnClickListener(new pvListener());
        pv_age.setOnClickListener(new pvListener());
        pv_native.setOnClickListener(new pvListener());
        pv_address.setOnClickListener(new pvListener());
        pv_record.setOnClickListener(new pvListener());
        pv_graduation.setOnClickListener(new pvListener());
        pv_place.setOnClickListener(new pvListener());
        pv_position.setOnClickListener(new pvListener());
        pv_salary.setOnClickListener(new pvListener());

        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResume();
                PictureFileUtils.deleteCacheDirFile(ResumeActivity.this);
            }
        });

        mContext = this;
        gridView = (GridView) findViewById(R.id.gridView);
        gridView_video = (GridView) findViewById(R.id.gridView_video);
        initGridView();
    }

    private class pvListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.pv_sex:

                    break;
                case R.id.pv_age:

                default:
                    break;
            }
        }
    }

    //初始化展示上传图片的GridView
    private void initGridView() {
        mGridViewAddImgAdapter = new GridViewAdapter(mContext, mPicList,PictureSelectorConstant.MAX_SELECT_PIC_NUM);
        gridView.setAdapter(mGridViewAddImgAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
            if (position == parent.getChildCount() - 1) {
                //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过6张，才能点击
                if (mPicList.size() == PictureSelectorConstant.MAX_SELECT_PIC_NUM) {
                    //最多添加6张图片
                    al = mPicList;
                    viewPluImg(position);
                } else {
                    //添加凭证图片
                    selectPic(PictureSelectorConstant.MAX_SELECT_PIC_NUM - mPicList.size());
                    System.out.println("selectPic:"+mPicList+"//"+mVideoList);
                }
            } else {
                al = mPicList;
                viewPluImg(position);
            }
            }
        });

        mGridViewVideoAdapter = new GridViewAdapter(mContext,mVideoList,1);
        gridView_video.setAdapter(mGridViewVideoAdapter);
        gridView_video.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    if (mVideoList.size() == 1) {
                        al = mVideoList;
                        viewPluImg(position);
                    } else {
                        selectVideo();
                        System.out.println("selectVideo:"+mVideoList+"//"+mPicList);
                    }
                } else {
                    al = mVideoList;
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(mContext, PlusImageActivity.class);
        intent.putStringArrayListExtra(PictureSelectorConstant.IMG_LIST, al);
        intent.putExtra(PictureSelectorConstant.POSITION, position);
        startActivityForResult(intent, PictureSelectorConstant.REQUEST_CODE_MAIN);
    }

    /**
     * 打开相册或者照相机选择凭证图片，最多6张
     *
     * @param maxTotal 最多选择的图片的数量
     */
    private void selectPic(int maxTotal) {
        PictureSelectorConfig.initMultiConfig(this, maxTotal);
        al = mPicList;
    }
    private void selectVideo() {
        PictureSelectorConfig.initSingleConfig(this);
        al = mVideoList;
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                System.out.println("compressPath:"+compressPath);
                if(al == mPicList) {
                    mPicList.add(compressPath); //把图片添加到将要上传的图片数组中
                    mGridViewAddImgAdapter.notifyDataSetChanged();
                }
                else if(al == mVideoList) {
                    mVideoList.add(compressPath);
                    mGridViewVideoAdapter.notifyDataSetChanged();
                }
            }
            else{
                System.out.println("Path:"+localMedia.getPath());
                if(al == mPicList) {
                    mPicList.add(localMedia.getPath()); //把图片添加到将要上传的图片数组中
                    mGridViewAddImgAdapter.notifyDataSetChanged();
                }
                else if(al == mVideoList) {
                    mVideoList.add(localMedia.getPath());
                    mGridViewVideoAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    refreshAdapter(PictureSelector.obtainMultipleResult(data));
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的
                    break;
            }
        }
        if (requestCode == PictureSelectorConstant.REQUEST_CODE_MAIN && resultCode == PictureSelectorConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(PictureSelectorConstant.IMG_LIST); //要删除的图片的集合
            if(al == mPicList) {
                mPicList.clear();
                mPicList.addAll(toDeletePicList);
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
            else {
                mVideoList.clear();
                mVideoList.addAll(toDeletePicList);
                mGridViewVideoAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setResume() {
        resume.setShow_or_hide(show_or_hide.isChecked());
        resume.setName(et_name.getText().toString());
        resume.setSex(pv_sex.getText().toString());
        resume.setAge(pv_age.getText().toString());
        String sadd = pv_native.getText().toString();
        String[] saddlist = null;
        saddlist = sadd.split("-");
        P_C_D nativeadd = new P_C_D();
        nativeadd.setProvince(saddlist[0]);
        nativeadd.setCity(saddlist[1]);
        nativeadd.setDistrict(saddlist[2]);
        resume.setNativeadd(nativeadd);
        sadd = pv_address.getText().toString();
        saddlist = sadd.split("-");
        nativeadd.setProvince(saddlist[0]);
        nativeadd.setCity(saddlist[1]);
        nativeadd.setDistrict(saddlist[2]);
        resume.setAddress(nativeadd);
        resume.setPhone(et_phone.getText().toString());
        resume.setSchool(et_school.getText().toString());
        resume.setMajor(et_major.getText().toString());
        resume.setRecord(pv_record.getText().toString());
        resume.setGraduation(pv_graduation.getText().toString());
        sadd = pv_place.getText().toString();
        saddlist = sadd.split("-");
        nativeadd.setProvince(saddlist[0]);
        nativeadd.setCity(saddlist[1]);
        nativeadd.setDistrict(saddlist[2]);
        resume.setPlace(nativeadd);
        resume.setPosition(pv_position.getText().toString());
        resume.setSalary(pv_salary.getText().toString());
        resume.setCertification(et_certification.getText().toString());
        resume.setSelf(et_self.getText().toString());
        resume.setImgUrlList(mPicList);
        resume.setVidelUrl(mVideoList.get(0));
    }

}
