package com.baidu.k12edu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baidu.k12edu.R;

import java.util.List;

/**
 * Created by dengshengjin on 16/9/11.
 */
public class UserListAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mData;
    private LayoutInflater mInflater = null;

    public UserListAdapter(Context context) {
        mContext = context;
        //根据context上下文加载布局，这里的是activity本身，即this
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        if (mData == null) {
            return 0;
        }
        return mData.size();
    }

    @Override
    public String getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //如果item为空，则new holder对象
        if (convertView == null) {
            holder = new ViewHolder();
            //使用inflate加载布局，第一个参数为要加载的布局id，第二个为空
            convertView = mInflater.inflate(R.layout.user_list_item, null);
            holder.title = (TextView) convertView.findViewById(R.id.title);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //通过getItem方法获取滑到的当前位置的数据，赋值给data
        String data = getItem(position);
        //holder通过setText方法获取数据给title，title为TextView
        holder.title.setText(data);
        //返回View内容
        return convertView;
    }

    //通过字符数组获取数据
    public void setData(List<String> mData) {
        this.mData = mData;
        notifyDataSetChanged();
    }
    //声明一个静态类ViewHolder，有一个TextView属性，为title
    private static class ViewHolder {
        private TextView title;
    }

}
