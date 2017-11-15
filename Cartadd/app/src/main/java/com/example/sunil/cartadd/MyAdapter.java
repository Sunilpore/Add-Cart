package com.example.sunil.cartadd;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sunil on 11/13/2017.
 */

class MyAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<ProductModel> alist;
    LayoutInflater inflater;

    public MyAdapter(Context mContext, ArrayList<ProductModel> alist) {
        this.mContext=mContext;
        this.alist = alist;
        inflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return alist.size();
    }

    @Override
    public Object getItem(int i) {
        return alist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup vg) {
        ViewHolder vh;
        if(view==null) {
            vh=new ViewHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.lay, vg, false);
            vh.prodname=view.findViewById(R.id.tv_product);
            vh.click=view.findViewById(R.id.bt_click);
            view.setTag(vh);
        }
        else{
            vh= (ViewHolder) view.getTag();
        }

        ProductModel current= (ProductModel) getItem(i);

        vh.prodname.setText(current.prodcat);
        vh.click.setTag(current);

        vh.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Button bt= (Button) view;

                ProductModel tmp= (ProductModel) bt.getTag();

                tmp.setClickbutton(bt.isSelected());


            }
        });

        return view;
    }

    private class ViewHolder{
        TextView prodname;
        Button click;

    }
}
