package com.bawei.week1APractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/11.
 */
public class Fragment1 extends Fragment {
    private ArrayList<Info.Data> list;

    public Fragment1(ArrayList<Info.Data> list) {
        this.list = list;
    }

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, null);
        initView();

        return view;
    }

    private void initView() {
        ListView listview= (ListView) view.findViewById(R.id.listview);
        MyAdapter myAdapter = new MyAdapter();
        listview.setAdapter(myAdapter);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView==null){
                convertView=convertView.inflate(getContext(),R.layout.item,null);
                holder=new ViewHolder();
                holder.author= (TextView) convertView.findViewById(R.id.author);
                holder.title= (TextView) convertView.findViewById(R.id.title);
                holder.img= (ImageView) convertView.findViewById(R.id.img);

                convertView.setTag(holder);

            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            holder.author.setText(list.get(position).author_name);
            holder.title.setText(list.get(position).title);
            //设置缓存
            DisplayImageOptions options=new DisplayImageOptions.Builder()
                    .showImageForEmptyUri(R.mipmap.ic_launcher)
                    .showImageOnFail(R.mipmap.ic_launcher)
                    .cacheInMemory(true)                                               //缓存用
                    .cacheOnDisc(true)                                                    //缓存用
                    .displayer(new RoundedBitmapDisplayer(5))       //图片圆角显示，值为整数
                    .build();
           // ImageLoader.getInstance().displayImage(list.get(position).thumbnail_pic_s,holder.img);
            Picasso.with(getContext()).load(list.get(position).thumbnail_pic_s).error(R.mipmap.ic_launcher).into(holder.img);
            return convertView;
        }
    }
    class ViewHolder{
        ImageView img;
        TextView title;
        TextView author;

    }
}
