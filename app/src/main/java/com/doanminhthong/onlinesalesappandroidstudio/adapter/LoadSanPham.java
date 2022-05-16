package com.doanminhthong.onlinesalesappandroidstudio.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doanminhthong.onlinesalesappandroidstudio.R;
import com.doanminhthong.onlinesalesappandroidstudio.model.LoaiSP;

import java.util.List;

public class LoadSanPham extends BaseAdapter {
    List<LoaiSP> array;
    Context context;

    public LoadSanPham( Context context, List<LoaiSP> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null){
            viewHolder = new ViewHolder();
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
            viewHolder.textTenSanPham = view.findViewById(R.id.textView_tenSp);
            viewHolder.imgHinhAnh = view.findViewById(R.id.imageView_sanpham);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.textTenSanPham.setText(array.get(i).getTensanpham());
            Glide.with(context).load(array.get(i).getHinhanh()).into(viewHolder.imgHinhAnh);
        }
        return view;
    }

    public class ViewHolder{

        TextView textTenSanPham;
        ImageView imgHinhAnh;
    }
}

