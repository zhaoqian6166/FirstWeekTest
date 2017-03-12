package com.bawei.week1APractice;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends FragmentActivity {
    private ArrayList<Fragment> list;
    private ArrayList<String> list_string;
    private ViewPager viewpager;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
        getData();
    }

    private void intiView() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);

        list_string = new ArrayList<String>();
        list_string.add("推荐");
        list_string.add("最新");
        tab.addTab(tab.newTab().setText(list_string.get(0)));
        tab.addTab(tab.newTab().setText(list_string.get(1)));


    }

    public void getData() {
        AsyncHttpClient client = new AsyncHttpClient();
        String url="http://mock.eolinker.com/success/C9kimTQ9XQTqvLMNlXNLdyTWAkChKHh2";
client.get(MainActivity.this, url, new TextHttpResponseHandler() {

    @Override
    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
        Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
        Log.i("测试","失败");
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, String responseString) {
      //  responseString//请求得到的json
     //   Log.i("测试","成功"+responseString);
        Gson gson = new Gson();
        Info info = gson.fromJson(responseString, Info.class);
        Log.i("测试",info.result.data.size()+"--");
        ArrayList<Info.Data> list1=new ArrayList<Info.Data>();
        ArrayList<Info.Data> list2=new ArrayList<Info.Data>();
        for (int i=0;i<15;i++){
           list1.add( info.result.data.get(i));
        }
        for (int i=15;i<30;i++){
            list2.add( info.result.data.get(i));
        }
        Fragment1 fragment1 = new Fragment1(list1);
        Fragment2 fragment2 = new Fragment2(list2);
        list = new ArrayList<Fragment>();
        list.add(fragment1);
        list.add(fragment2);
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        //关联
        tab.setupWithViewPager(viewpager);
        tab.setTabsFromPagerAdapter(adapter);
    }
});


    }
    class MyPagerAdapter extends FragmentPagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_string.get(position);
        }
    }
}
