package com.ft.first_interview.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ft.first_interview.PictureSelector.GridViewAdapter;
import com.ft.first_interview.PictureSelector.PictureSelectorConfig;
import com.ft.first_interview.PictureSelector.PictureSelectorConstant;
import com.ft.first_interview.PictureSelector.PlusImageActivity;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Company;
import com.ft.first_interview.bean.P_C_D;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

public class FirmCompanyActivity extends AppCompatActivity {

    private Company company = new Company();

    private EditText et_firm_name;
    private EditText et_firm_shortname;
    private EditText et_firm_address;
    private EditText et_firm_tel;
    private EditText et_firm_legalperson;
    private EditText et_firm_licensenum;
    private RelativeLayout rl_firm_type;
    private TextView pv_firm_type;
    private RelativeLayout rl_firm_scale;
    private TextView pv_firm_scale;
    private RelativeLayout rl_firm_business;
    private TextView pv_firm_business;
    private EditText et_firm_introduction;
    private EditText et_firm_headname;
    private EditText et_firm_headphone;
    private Button btn_firm_submit;

    private GridView firm_license_gridView;
    private GridView firm_pictures_gridView;
    private ArrayList<String> al;
    private ArrayList<String> licenseImgList = new ArrayList<>();
    private ArrayList<String> picturesImgList = new ArrayList<>();
    private GridViewAdapter mGridViewLicenseImgAdapter; //展示上传的图片的适配器
    private GridViewAdapter mGridViewPicturesImgAdapter;


    private List<String> typeItems = new ArrayList<>();
    private List<String> scaleItems = new ArrayList<>();
    private List<String> businessItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_company);

        initList();

        et_firm_name = (EditText) findViewById(R.id.et_firm_name);
        et_firm_shortname = (EditText) findViewById(R.id.et_firm_shortname);
        et_firm_address = (EditText) findViewById(R.id.et_firm_address);
        et_firm_tel = (EditText) findViewById(R.id.et_firm_tel);
        et_firm_legalperson = (EditText) findViewById(R.id.et_firm_legalperson);
        et_firm_licensenum = (EditText) findViewById(R.id.et_firm_licensenum);
        rl_firm_type = (RelativeLayout) findViewById(R.id.rl_firm_type);
        pv_firm_type = (TextView) findViewById(R.id.pv_firm_type);
        rl_firm_scale = (RelativeLayout) findViewById(R.id.rl_firm_scale);
        pv_firm_scale = (TextView) findViewById(R.id.pv_firm_scale);
        rl_firm_business = (RelativeLayout) findViewById(R.id.rl_firm_business);
        pv_firm_business = (TextView) findViewById(R.id.pv_firm_business);

        et_firm_introduction = (EditText) findViewById(R.id.et_firm_introduction);
        et_firm_headname = (EditText) findViewById(R.id.et_firm_headname);
        et_firm_headphone = (EditText) findViewById(R.id.et_firm_headphone);
        btn_firm_submit = (Button) findViewById(R.id.btn_firm_submit);

        rl_firm_type.setOnClickListener(new CompanyOnClickListener());
        rl_firm_scale.setOnClickListener(new CompanyOnClickListener());
        rl_firm_business.setOnClickListener(new CompanyOnClickListener());
        btn_firm_submit.setOnClickListener(new CompanyOnClickListener());

        firm_license_gridView = (GridView) findViewById(R.id.firm_license_gridView);
        firm_pictures_gridView = (GridView) findViewById(R.id.firm_pictures_gridView);
        initGridView();
    }

    private class CompanyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_firm_type:
                    OptionsPickerView type_pvOptions = new OptionsPickerBuilder(
                            FirmCompanyActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = typeItems.get(options1);
                            pv_firm_type.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("公司性质")
                            .build();
                    type_pvOptions.setPicker(typeItems);
                    type_pvOptions.show();
                    break;
                case R.id.rl_firm_scale:
                    OptionsPickerView scale_pvOptions = new OptionsPickerBuilder(
                            FirmCompanyActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = scaleItems.get(options1);
                            pv_firm_scale.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("公司规模")
                            .build();
                    scale_pvOptions.setPicker(scaleItems);
                    scale_pvOptions.show();
                    break;
                case R.id.rl_firm_business:
                    OptionsPickerView business_pvOptions = new OptionsPickerBuilder(
                            FirmCompanyActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = businessItems.get(options1);
                            pv_firm_business.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("所在行业")
                            .build();
                    business_pvOptions.setPicker(businessItems);
                    business_pvOptions.show();
                    break;
                case R.id.btn_firm_submit:
                    if(et_firm_name.getText().toString().trim().equals("") || et_firm_address.getText().toString().trim().equals("") ||
                            et_firm_tel.getText().toString().trim().equals("") || et_firm_legalperson.getText().toString().trim().equals("") ||
                            et_firm_licensenum.getText().toString().trim().equals("") || licenseImgList.size() == 0 ||
                            pv_firm_type.getText().toString().trim().equals("") || pv_firm_scale.getText().toString().trim().equals("") ||
                            pv_firm_business.getText().toString().trim().equals("") || et_firm_headname.getText().toString().trim().equals("") ||
                            et_firm_headphone.getText().toString().trim().equals("")) {
                        Toast.makeText(getApplicationContext(),"请填写所有必填项！",Toast.LENGTH_SHORT).show();
                    } else {
                        setCompany();
                        Toast.makeText(getApplicationContext(),"提交成功！",Toast.LENGTH_SHORT).show();
                        PictureFileUtils.deleteCacheDirFile(FirmCompanyActivity.this);
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initList() {
        //性质
        typeItems.add("国有");
        typeItems.add("中外合作");
        typeItems.add("中外合资");
        typeItems.add("外商独资");
        typeItems.add("集体");
        typeItems.add("私营");
        typeItems.add("其他");
        //规模
        scaleItems.add("少于50人");
        scaleItems.add("50-150人");
        scaleItems.add("150-500人");
        scaleItems.add("500-1000人");
        scaleItems.add("1000人以上");
        //行业
        businessItems.add("制造业");
        businessItems.add("商业/贸易");
        businessItems.add("金融");
        businessItems.add("科技");
        businessItems.add("通信/电子");
        businessItems.add("地产/建筑");
        businessItems.add("医药/美容");
        businessItems.add("教育/法律");
        businessItems.add("广告/艺术");
        businessItems.add("物流/交通");
        businessItems.add("能源/化工");
        businessItems.add("餐饮/食品");
        businessItems.add("酒店/娱乐");
        businessItems.add("专业/服务业");
        businessItems.add("农/林/渔");
        businessItems.add("政府/科研");
        businessItems.add("汽车/设备");
        businessItems.add("家居/家电/服装");
        businessItems.add("跨领域经营");
        businessItems.add("其他");
    }

    private void initGridView() {
        mGridViewLicenseImgAdapter = new GridViewAdapter(this, licenseImgList,PictureSelectorConstant.MAX_SELECT_PIC_NUM);
        firm_license_gridView.setAdapter(mGridViewLicenseImgAdapter);
        firm_license_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    //如果“增加按钮形状的”图片的位置是最后一张，且添加了的图片的数量不超过6张，才能点击
                    if (licenseImgList.size() == PictureSelectorConstant.MAX_SELECT_PIC_NUM) {
                        //最多添加6张图片
                        al = licenseImgList;
                        viewPluImg(position);
                    } else {
                        //添加凭证图片
                        selectPic(PictureSelectorConstant.MAX_SELECT_PIC_NUM - licenseImgList.size());
                    }
                } else {
                    al = licenseImgList;
                    viewPluImg(position);
                }
            }
        });

        mGridViewPicturesImgAdapter = new GridViewAdapter(this,picturesImgList,PictureSelectorConstant.MAX_SELECT_PIC_NUM);
        firm_pictures_gridView.setAdapter(mGridViewPicturesImgAdapter);
        firm_pictures_gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == parent.getChildCount() - 1) {
                    if (picturesImgList.size() == 1) {
                        al = picturesImgList;
                        viewPluImg(position);
                    } else {
                        selectPic(PictureSelectorConstant.MAX_SELECT_PIC_NUM - picturesImgList.size());
                    }
                } else {
                    al = picturesImgList;
                    viewPluImg(position);
                }
            }
        });
    }

    //查看大图
    private void viewPluImg(int position) {
        Intent intent = new Intent(this, PlusImageActivity.class);
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
    }

    // 处理选择的照片的地址
    private void refreshAdapter(List<LocalMedia> picList) {
        for (LocalMedia localMedia : picList) {
            //被压缩后的图片路径
            if (localMedia.isCompressed()) {
                String compressPath = localMedia.getCompressPath(); //压缩后的图片路径
                System.out.println("compressPath:"+compressPath);
                if(al == licenseImgList) {
                    licenseImgList.add(compressPath); //把图片添加到将要上传的图片数组中
                    mGridViewLicenseImgAdapter.notifyDataSetChanged();
                }
                else if(al == picturesImgList) {
                    picturesImgList.add(compressPath);
                    mGridViewPicturesImgAdapter.notifyDataSetChanged();
                }
            }
            else{
                System.out.println("Path:"+localMedia.getPath());
                if(al == licenseImgList) {
                    licenseImgList.add(localMedia.getPath()); //把图片添加到将要上传的图片数组中
                    mGridViewLicenseImgAdapter.notifyDataSetChanged();
                }
                else if(al == picturesImgList) {
                    picturesImgList.add(localMedia.getPath());
                    mGridViewPicturesImgAdapter.notifyDataSetChanged();
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
        if (requestCode == PictureSelectorConstant.REQUEST_CODE_MAIN
                && resultCode == PictureSelectorConstant.RESULT_CODE_VIEW_IMG) {
            //查看大图页面删除了图片
            ArrayList<String> toDeletePicList = data.getStringArrayListExtra(PictureSelectorConstant.IMG_LIST); //要删除的图片的集合
            if(al == licenseImgList) {
                licenseImgList.clear();
                licenseImgList.addAll(toDeletePicList);
                mGridViewLicenseImgAdapter.notifyDataSetChanged();
            }
            else {
                picturesImgList.clear();
                picturesImgList.addAll(toDeletePicList);
                mGridViewPicturesImgAdapter.notifyDataSetChanged();
            }
        }
    }

    private void setCompany() {
        company.setCompanyname(et_firm_name.getText().toString().trim());
        company.setShortname(et_firm_shortname.getText().toString().trim());
        company.setAddress(et_firm_address.getText().toString().trim());
        company.setTelphone(et_firm_tel.getText().toString().trim());
        company.setLegalperson(et_firm_legalperson.getText().toString().trim());
        company.setLiensenum(et_firm_licensenum.getText().toString().trim());
        company.setLicenseImg(licenseImgList);
        company.setFirmtype(pv_firm_type.getText().toString().trim());
        company.setScale(pv_firm_scale.getText().toString().trim());
        company.setBusiness(pv_firm_business.getText().toString().trim());
        company.setIntroduction(et_firm_introduction.getText().toString().trim());
        company.setPicturesImgList(picturesImgList);
        company.setHeadname(et_firm_headname.getText().toString().trim());
        company.setHeadphone(et_firm_headphone.getText().toString().trim());
    }
}
