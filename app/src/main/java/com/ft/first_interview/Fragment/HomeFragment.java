package com.ft.first_interview.Fragment;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ft.first_interview.Activity.JobDetailActivity;
import com.ft.first_interview.Activity.SearchActivity;
import com.ft.first_interview.R;
import com.ft.first_interview.bean.Job;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    final String[] name = new String[] { "张国荣", "张学友", "谭咏麟" };
    final String[] message = new String[] {
            "张国荣，1956年9月12日生于香港，歌手、演员、音乐人；影视歌多栖发展的代表之一。1977年正式出道。1983年以《风继续吹》成名。1984年演唱的《Monica》是香港歌坛第一支同获十大中文金曲、十大劲歌金曲的舞曲 。 1986年、1987年获劲歌金曲金奖",
            "张学友，歌手、演员，1961年7月10日出生于香港，1984年获得香港首届十八区业余歌唱大赛冠军，正式出道，1993年发行的国语唱片《吻别》年度销量超过400万张，1995年、1996年连续两年获得世界音乐大奖全球销量最高亚洲流行乐歌手奖",
            "谭咏麟，1950年8月23日出生于香港，籍贯广东新会，中国香港男歌手、音乐人、演员。[1]20世纪60年代末为Loosers乐队成员。1973年任温拿乐队主音歌手。1975年参演首部电影《大家乐》。1978年温拿乐队宣布解散，谭咏麟以个人身份发展。1979年赴台湾发展事业，推出首张个人专辑《反斗星》" };

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
                Bundle bundle = new Bundle();
                bundle.putInt("photo", R.mipmap.ic_launcher_round);
                bundle.putString("message", message[arg2]);
                intent.putExtras(bundle);
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
class JobItemAdapter extends ArrayAdapter<Job> {

    private int layoutId;

    public JobItemAdapter(Context context, int layoutId, List<Job> list) {
        super(context, layoutId, list);
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Job item = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(layoutId, parent, false);

        TextView title = (TextView) view.findViewById(R.id.joblistitem_title);
        title.setText(item.getJob_title());
        TextView company = (TextView) view.findViewById(R.id.joblistitem_company);
        company.setText(item.getCompany());
        TextView salary = (TextView) view.findViewById(R.id.joblistitem_salary);
        salary.setText(item.getJob_salary());
        TextView place = (TextView) view.findViewById(R.id.joblistitem_place);
        place.setText(item.getJob_place());
        TextView tag = (TextView) view.findViewById(R.id.joblistitem_tag);
        ArrayList<String> s;
        s = item.getJob_tag();
        String ts = s.get(0);
        for(int i = 1;i < s.size(); i ++){
            ts = ts +" | "+ s.get(i);
        }
        tag.setText(ts);
        TextView posttime = (TextView) view.findViewById(R.id.joblistitem_time);
        posttime.setText(item.getPosttime());

        return view;
    }
}
