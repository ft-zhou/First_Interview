package com.ft.first_interview.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ft.first_interview.Activity.JobDetailActivity;
import com.ft.first_interview.Activity.SearchActivity;
import com.ft.first_interview.Adapter.JobItemAdapter;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private ImageView topImage;
    private RelativeLayout top_rl;
    private EditText etSearch;
    private ListView joblist;
    private List<Job> data = new ArrayList<>();

    private int mTouchShop;//最小滑动距离
    protected float mFirstY;//触摸下去的位置
    protected float mCurrentY;//滑动时Y的位置
    protected int direction;//判断是否上滑或者下滑的标志
    protected boolean mShow;//判断是否执行了上滑动画
    private Animator mAnimator;//动画属性

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container,false);
        topImage = (ImageView) view.findViewById(R.id.topImage);
        etSearch= (EditText) view.findViewById(R.id.etSearch) ;
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(),SearchActivity.class));
            }
        });



        initList();

        JobItemAdapter itemAdapter = new JobItemAdapter(getActivity(), R.layout.job_list_item, data);
        joblist = (ListView)view.findViewById(R.id.job_list);
        joblist.setAdapter(itemAdapter);

        joblist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent intent=new Intent(getContext(), JobDetailActivity.class);
                startActivity(intent);
            }
        });

        View header = View.inflate(getActivity(), R.layout.headview, null);//自定义一个头布局和顶部执行动画的布局等高就行
        joblist.addHeaderView(header);//加载头布局
//
//        //获得一个最小滑动距离
//        mTouchShop = ViewConfiguration.get(getActivity()).getScaledTouchSlop();//系统级别的一个属性,判断用户的最小滑动距离的,可查看源码为16
//
//        joblist.setOnTouchListener(new View.OnTouchListener() {//listview的触摸事件
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                switch (event.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        mFirstY = event.getY();//按下时获取位置
//                        break;
//
//                    case MotionEvent.ACTION_MOVE:
//                        mCurrentY = event.getY();//得到滑动的位置
//                        if(mCurrentY - mFirstY > mTouchShop){//滑动的位置减去按下的位置大于最小滑动距离  则表示向下滑动
//                            direction = 0;//down
//                        }else if(mFirstY - mCurrentY > mTouchShop){//反之向上滑动
//                            direction = 1;//up
//                        }
//
//                        if(direction == 1){//判断如果是向上滑动 则执行向上滑动的动画
//                            if(mShow){//判断动画是否执行了  执行了则改变状态
//                                //执行往上滑动的动画
//                                tolbarAnim(1);
//                                mShow = !mShow;
//                            }
//                        }else if(direction == 0){//判断如果是向下滑动 则执行向下滑动的动画
//                            if(!mShow){//判断动画是否执行了  执行了则改变状态
//                                //执行往下滑动的动画
//                                tolbarAnim(0);
//                                mShow = !mShow;
//                            }
//                        }
//
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        break;
//
//                }
//                return false;
//            }
//        });

//        joblist.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                switch (motionEvent.getAction()) {
//                    case MotionEvent.ACTION_DOWN:
//                        lastY = (int) motionEvent.getY();
//                        break;
//                    case MotionEvent.ACTION_MOVE:
//                        int currentY = (int) motionEvent.getY();
//                        int destY = currentY - lastY;
//                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) joblist.getLayoutParams();
//                        ViewGroup.MarginLayoutParams paramsTop = (ViewGroup.MarginLayoutParams) topImage.getLayoutParams();
//
//                        if (currentY - lastY < 0) {
//                            //上滑隐藏顶部
//                            if(paramsTop.topMargin > (-paramsTop.height)) {
//                                destY = (paramsTop.topMargin + destY < (-paramsTop.height) ? (-paramsTop.height - paramsTop.topMargin) : destY);
//                                paramsTop.topMargin += destY;
//                                topImage.requestLayout();
//                            }
//                        }else {
//                            //下滑显示头部
//                            if(paramsTop.topMargin <= 0) {
//                                destY = (paramsTop.topMargin + destY > 0 ? 0 - paramsTop.topMargin : destY);
//                                paramsTop.topMargin += destY;
//                                topImage.requestLayout();
//                            }
//                        }
//                }
//                return false;
//            }
//        });

        return view;
    }

    private void initList() {
        for (int i = 0; i < 10; i++) {
            Job item = new Job();
            item.setCompany("一面科技");
            item.setJob_title("Android开发工程师");
            item.setJob_place("上海-闵行区");
            item.setJob_salary("8000～10000/月");
            item.setPosttime("5分钟前");
            ArrayList<String> tag = new ArrayList<>();
            tag.add("五险一金");
            tag.add("应届毕业生");
            item.setJob_tag(tag);
            data.add(item);
        }
    }

    private void tolbarAnim(int flag){
        if(mAnimator != null && mAnimator.isRunning()){//判断动画存在  如果启动了,则先关闭
            mAnimator.cancel();
        }
        if(flag == 0){
            mAnimator = ObjectAnimator.ofFloat(top_rl, "translationY", top_rl.getTranslationY(),0);//从当前位置位移到0位置
        }else{
            mAnimator = ObjectAnimator.ofFloat(top_rl, "translationY", top_rl.getTranslationY(),-top_rl.getHeight());//从当前位置移动到布局负高度的wiz
        }
        mAnimator.start();//执行动画

    }

}
