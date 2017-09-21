package com.example.kjp.adapter;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akbar.dev.kjp.R;
import com.example.kjp.view.fragment.InfoFragment;
import com.example.kjp.view.fragment.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by akbar on 18/06/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {

    private String[] judul = {"Informasi umum KJP", "Daftar KJP"};
    private String[] isi = {"Menu ini berisi informasi umum tentang Kartu Jakarta Pintar", "Daftarkan akun anda"};
    private String[] detail = {"Lihat selengkapnya", "Daftar"};

    private Context mContext;
    private FragmentManager fm;

    public HomeAdapter(Context context){
        this.mContext = context;
    }

    public class HomeHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textViewJudul)TextView textJudul;
        @BindView(R.id.textViewIsi)TextView textIsi;
        @BindView(R.id.textViewDetail)TextView textDetail;

        public HomeHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public HomeAdapter.HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_home, parent, false);
        HomeHolder homeHolder = new HomeHolder(view);
        return homeHolder;
    }

    @Override
    public void onBindViewHolder(final HomeAdapter.HomeHolder holder, int position) {
        holder.textJudul.setText(judul[position]);
        holder.textIsi.setText(isi[position]);
        holder.textDetail.setText(detail[position]);

        holder.textDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fm = ((Activity) mContext).getFragmentManager();
                if (holder.textDetail.getText().toString() == "Lihat selengkapnya"){
                    fm.beginTransaction().replace(R.id.content_frame, new InfoFragment()).addToBackStack(null).commit();
                } else if (holder.textDetail.getText().toString() == "Daftar"){
                    fm.beginTransaction().replace(R.id.content_frame, new RegisterFragment()).addToBackStack(null).commit();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.length;
    }
}