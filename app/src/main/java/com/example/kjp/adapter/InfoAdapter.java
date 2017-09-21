package com.example.kjp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.akbar.dev.kjp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 18/06/17.
 */

public class InfoAdapter extends BaseAdapter {

    private String[] judul = {"Tentang KJP", "Persyaratan KJP", "Pendataan KJP", "Regulasi Terkait", "Penggunaan Data", "Download"};
    private String[] detail = {"Lihat", "Lihat", "Lihat", "Lihat", "Lihat", "Lihat"};
    private Context mContext;

    public InfoAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return judul.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_info, parent, false);
        holder = new ViewHolder(view);

        holder.textJudul.setText(judul[position]);
        holder.textDetail.setText(detail[position]);

        return view;
    }

    static class ViewHolder{

        @BindView(R.id.textViewJudul)
        TextView textJudul;
        @BindView(R.id.textViewDetail)TextView textDetail;

        public ViewHolder(View view){
            ButterKnife.bind(this, view);
        }
    }
}
