package com.ft.first_interview.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.ft.first_interview.PictureSelector.GridViewAdapter;
import com.ft.first_interview.PictureSelector.PictureSelectorConfig;
import com.ft.first_interview.PictureSelector.PictureSelectorConstant;
import com.ft.first_interview.PictureSelector.PlusImageActivity;
import com.ft.first_interview.R;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    private EditText et_feedback;
    private GridView feedback_gridView;
    private ArrayList<String> feedbackImg = new ArrayList<>();
    private GridViewAdapter mFeedbackImgAdapter;
    private Button btn_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        et_feedback = (EditText) findViewById(R.id.et_feedback);
        feedback_gridView = (GridView) findViewById(R.id.feedback_gridView);
        initGridView();
        btn_feedback = (Button) findViewById(R.id.btn_feedback);
        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_feedback.getText().toString().trim().equals("")) {
                    Toast.makeText(getApplicationContext(),"请填写反馈意见",Toast.LENGTH_SHORT).show();
                }
                else {
                    //提交
                    Toast.makeText(getApplicationContext(),"提交成功！",Toast.LENGTH_SHORT).show();
                    FeedbackActivity.this.finish();
                }
            }
        });
    }

    //初始化展示上传图片的GridView
    private void initGridView() {
        mFeedbackImgAdapter = new GridViewAdapter(this, feedbackImg,PictureSelectorConstant.MAX_SELECT_PIC_NUM);
        feedback_gridView.setAdapter(mFeedbackImgAdapter);
        feedback_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过6张，才能点击
                    if (feedbackImg.size() == PictureSelectorConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加6张图片
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(PictureSelectorConstant.MAX_SELECT_PIC_NUM - feedbackImg.size());
                    }
                } else {
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(this, PlusImageActivity.class);
        intent.putStringArrayListExtra(PictureSelectorConstant.IMG_LIST, feedbackImg);
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
    }
    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                feedbackImg.add(compressPath); //把图片添加到将要上传的图片数组中
                mFeedbackImgAdapter.notifyDataSetChanged();
            }
            else{
                feedbackImg.add(localMedia.getPath()); //把图片添加到将要上传的图片数组中
                mFeedbackImgAdapter.notifyDataSetChanged();
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
            feedbackImg.clear();
            feedbackImg.addAll(toDeletePicList);
            mFeedbackImgAdapter.notifyDataSetChanged();
        }
    }
}
