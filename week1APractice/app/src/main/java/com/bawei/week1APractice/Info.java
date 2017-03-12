package com.bawei.week1APractice;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Info {
    public Result result;
    public class Result{
        public ArrayList<Data> data;

    }
    public class Data{
        public String author_name;
        public String title;
        public String url;
        public String thumbnail_pic_s;

    }
}
