package com.ft.first_interview.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;
import com.ft.first_interview.bean.P_C_D;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

public class FirmPublishActivity extends AppCompatActivity {

    private Job job = new Job();

    private EditText et_recruit_detailposition;
    private RelativeLayout rl_recruit_position;
    private TextView pv_recruit_position;
    private RelativeLayout rl_recruit_place;
    private TextView pv_recruit_place;
    private RelativeLayout rl_recruit_salary;
    private TextView pv_recruit_salary;
    private EditText et_recruit_description;
    private EditText et_recruit_tag1;
    private EditText et_recruit_tag2;
    private EditText et_recruit_tag3;
    private Button btn_recruit_publish;

    private List<String> provinceItems = new ArrayList<String>();
    private List<String> cityItems1 = new ArrayList<String>();
    private List<String> cityItems2 = new ArrayList<String>();
    private List<String> cityItems3 = new ArrayList<String>();
    private List<List<String>> pcItems = new ArrayList<>();
    private List<String> positionItems = new ArrayList<String>();
    private List<String> salaryItems = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firm_publish);

        initList();

        et_recruit_detailposition = (EditText) findViewById(R.id.et_recruit_detailposition);
        rl_recruit_position = (RelativeLayout) findViewById(R.id.rl_recruit_position);
        pv_recruit_position = (TextView) findViewById(R.id.pv_recruit_position);
        rl_recruit_place = (RelativeLayout) findViewById(R.id.rl_recruit_place);
        pv_recruit_place = (TextView) findViewById(R.id.pv_recruit_place);
        rl_recruit_salary = (RelativeLayout) findViewById(R.id.rl_recruit_salary);
        pv_recruit_salary = (TextView) findViewById(R.id.pv_recruit_salary);
        et_recruit_description = (EditText) findViewById(R.id.et_recruit_description);
        et_recruit_tag1 = (EditText) findViewById(R.id.et_recruit_tag1);
        et_recruit_tag2 = (EditText) findViewById(R.id.et_recruit_tag2);
        et_recruit_tag3 = (EditText) findViewById(R.id.et_recruit_tag3);
        btn_recruit_publish = (Button) findViewById(R.id.btn_recruit_publish);

        rl_recruit_position.setOnClickListener(new PublishOnClickListener());
        rl_recruit_place.setOnClickListener(new PublishOnClickListener());
        rl_recruit_salary.setOnClickListener(new PublishOnClickListener());
        btn_recruit_publish.setOnClickListener(new PublishOnClickListener());
    }

    private class PublishOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_recruit_position:
                    OptionsPickerView position_pvOptions = new OptionsPickerBuilder(FirmPublishActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = positionItems.get(options1);
                            pv_recruit_position.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("工作岗位")
                            .build();
                    position_pvOptions.setPicker(positionItems);
                    position_pvOptions.show();
                    break;
                case R.id.rl_recruit_place:
                    OptionsPickerView place_pvOptions = new OptionsPickerBuilder(FirmPublishActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int options2, int options3, View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = provinceItems.get(options1) + "-" + pcItems.get(options1).get(options2);
                            pv_recruit_place.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setTitleText("工作地点")
                            .build();
                    place_pvOptions.setPicker(provinceItems, pcItems);
                    place_pvOptions.show();
                    break;
                case R.id.rl_recruit_salary:
                    OptionsPickerView salary_pvOptions = new OptionsPickerBuilder(
                            FirmPublishActivity.this, new OnOptionsSelectListener() {
                        @Override
                        public void onOptionsSelect(int options1, int option2, int options3 ,View v) {
                            //返回的分别是三个级别的选中位置
                            String tx = salaryItems.get(options1);
                            pv_recruit_salary.setText(tx);
                        }
                    }).setSubmitText("确定")//确定按钮文字
                            .setCancelText("取消")//取消按钮文字
                            .setSelectOptions(0)
                            .setTitleText("薪资")
                            .build();
                    salary_pvOptions.setPicker(salaryItems);
                    salary_pvOptions.show();
                    break;
                case R.id.btn_recruit_publish:
                    if(et_recruit_detailposition.getText().toString().trim().equals("") || pv_recruit_position.getText().toString().trim().equals("") ||
                            pv_recruit_place.getText().toString().trim().equals("") || pv_recruit_salary.getText().toString().trim().equals("") ||
                            et_recruit_description.getText().toString().trim().equals("")) {
                        Toast.makeText(getApplicationContext(),"请填写所有必填项！",Toast.LENGTH_SHORT).show();
                    } else {
                        setJob();
                        Toast.makeText(getApplicationContext(),"发布成功！",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    private void initList() {
        int i;
        //省市
        provinceItems.add("北京");
        provinceItems.add("上海");
        provinceItems.add("黑龙江");
        cityItems1.add("北京市");
        cityItems2.add("上海市");
        cityItems3.add("哈尔滨市");
        cityItems3.add("齐齐哈尔市");
        cityItems3.add("牡丹江市");
        cityItems3.add("佳木斯市");
        cityItems3.add("七台河市");
        cityItems3.add("大庆市");
        cityItems3.add("黑河市");
        cityItems3.add("绥化市");
        cityItems3.add("伊春市");
        cityItems3.add("鹤岗市");
        cityItems3.add("双鸭山市");
        cityItems3.add("鸡西市");
        cityItems3.add("大兴安岭地区");
        pcItems.add(cityItems1);
        pcItems.add(cityItems2);
        pcItems.add(cityItems3);
        //岗位
        positionItems.add("销售类");
        positionItems.add("客服类");
        positionItems.add("市场类");
        positionItems.add("人力类");
        positionItems.add("行政类");
        positionItems.add("后勤类");
        positionItems.add("餐饮类");
        positionItems.add("酒店类");
        positionItems.add("旅游类");
        positionItems.add("服务类");
        positionItems.add("健身类");
        positionItems.add("婚庆类");
        positionItems.add("娱乐类");
        positionItems.add("医疗类");
        positionItems.add("医药类");
        positionItems.add("美容类");
        positionItems.add("策划类");
        positionItems.add("设计类");
        positionItems.add("地产类");
        positionItems.add("建筑类");
        positionItems.add("工程师类");
        positionItems.add("物业类");
        positionItems.add("安保类");
        positionItems.add("家政类");
        positionItems.add("财务类");
        positionItems.add("金融类");
        positionItems.add("银行类");
        positionItems.add("保险类");
        positionItems.add("教育类");
        positionItems.add("咨询类");
        positionItems.add("法律类");
        positionItems.add("科研类");
        positionItems.add("公务员类");
        positionItems.add("物流类");
        positionItems.add("仓储类");
        positionItems.add("交通类");
        positionItems.add("农林渔类");
        positionItems.add("环保类");
        positionItems.add("化工类");
        positionItems.add("文艺类");
        positionItems.add("传媒类");
        positionItems.add("制造类");
        positionItems.add("采购类");
        positionItems.add("质检类");
        positionItems.add("技工类");
        positionItems.add("普工类");
        positionItems.add("IT软件类");
        positionItems.add("硬件类");
        positionItems.add("通信类");
        positionItems.add("高级管理类");
        positionItems.add("运营类");
        //薪资
        salaryItems.add("1500/月 以下");
        salaryItems.add("1500-2000/月");
        salaryItems.add("2000-3000/月");
        salaryItems.add("3000-4500/月");
        salaryItems.add("4500-6000/月");
        salaryItems.add("6000-8000/月");
        salaryItems.add("8000-10000/月");
        salaryItems.add("10000-15000/月");
        salaryItems.add("15000-20000/月");
        salaryItems.add("20000/月 及以上");
    }

    private void setJob() {
        job.setJob_title(et_recruit_detailposition.getText().toString().trim());
        job.setJob_position(pv_recruit_position.getText().toString().trim());
        job.setJob_place(pv_recruit_place.getText().toString().trim());
        job.setJob_salary(pv_recruit_salary.getText().toString().trim());
        job.setJob_describe(et_recruit_description.getText().toString().trim());
        ArrayList<String> tag = null;
        tag.add(et_recruit_tag1.getText().toString().trim());
        tag.add(et_recruit_tag2.getText().toString().trim());
        tag.add(et_recruit_tag3.getText().toString().trim());
        job.setJob_tag(tag);
    }
}
