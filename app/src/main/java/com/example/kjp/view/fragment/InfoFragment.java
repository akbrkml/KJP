package com.example.kjp.view.fragment;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.akbar.dev.kjp.R;
import com.example.kjp.adapter.InfoAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class InfoFragment extends Fragment{

    @BindView(R.id.listInfo)ListView listView;

    private FragmentManager fm;

    public InfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info, container, false);

        ButterKnife.bind(this, view);

        getActivity().setTitle("Informasi Umum KJP");

        initComponents();

        return view;
    }

    private void initComponents(){
        listView.setAdapter(new InfoAdapter(getActivity()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fm = getFragmentManager();
                switch (position){
                    case 0:
                        fm.beginTransaction().replace(R.id.content_frame, new AboutFragment()).addToBackStack(null).commit();
                        break;
                    case 1:
                        fm.beginTransaction().replace(R.id.content_frame, new SyaratFragment()).addToBackStack(null).commit();
                        break;
                    case 2:
                        fm.beginTransaction().replace(R.id.content_frame, new DataFragment()).addToBackStack(null).commit();
                        break;
                    case 3:
                        fm.beginTransaction().replace(R.id.content_frame, new RegulasiFragment()).addToBackStack(null).commit();
                        break;
                    case 4:
                        fm.beginTransaction().replace(R.id.content_frame, new DanaFragment()).addToBackStack(null).commit();
                        break;
                    case 5:
                        fm.beginTransaction().replace(R.id.content_frame, new DownloadFragment()).addToBackStack(null).commit();
                        break;
                }
            }
        });
    }
}