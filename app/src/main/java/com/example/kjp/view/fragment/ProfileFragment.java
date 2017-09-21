package com.example.kjp.view.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kjp.MainActivity;
import com.akbar.dev.kjp.R;
import com.example.kjp.model.Siswa;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    @BindView(R.id.nisn)TextView textNisn;
    @BindView(R.id.nokk)TextView textNoKk;
    @BindView(R.id.nama)TextView textNamaLengkap;
    @BindView(R.id.jk)TextView textJk;
    @BindView(R.id.tempat_lahir)TextView textTempatLahir;
    @BindView(R.id.tanggal_lahir)TextView textTanggalLahir;
    @BindView(R.id.alamat)TextView textAlamat;
    @BindView(R.id.provinsi)TextView textProvinsi;
    @BindView(R.id.kota)TextView textKota;
    @BindView(R.id.kecamatan)TextView textKecamatan;
    @BindView(R.id.kelurahan)TextView textKelurahan;
    @BindView(R.id.pendidikan)TextView textPendidikan;
    @BindView(R.id.kelas)TextView textKelas;
    @BindView(R.id.agama)TextView textAgama;
    @BindView(R.id.status)TextView textStatus;

    @BindView(R.id.tv_message_display)TextView textMessage;

    @BindView(R.id.title_nisn)TextView textTitleNisn;
    @BindView(R.id.title_nokk)TextView textTitleNoKk;
    @BindView(R.id.title_nama)TextView textTitleNamaLengkap;
    @BindView(R.id.title_jk)TextView textTitleJk;
    @BindView(R.id.title_tempat_lahir)TextView textTitleTempatLahir;
    @BindView(R.id.title_tanggal_lahir)TextView textTitleTanggalLahir;
    @BindView(R.id.title_alamat)TextView textTitleAlamat;
    @BindView(R.id.title_provinsi)TextView textTitleProvinsi;
    @BindView(R.id.title_kota)TextView textTitleKota;
    @BindView(R.id.title_kecamatan)TextView textTitleKecamatan;
    @BindView(R.id.title_kelurahan)TextView textTitleKelurahan;
    @BindView(R.id.title_pendidikan)TextView textTitlePendidikan;
    @BindView(R.id.title_kelas)TextView textTitleKelas;
    @BindView(R.id.title_agama)TextView textTitleAgama;

    private DatabaseReference mDatabase;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ButterKnife.bind(this, view);

        getDataProfile();

        getActivity().setTitle("Profil");

        return view;
    }

    private void getDataProfile(){
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(MainActivity.userId).child("siswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Siswa siswa = dataSnapshot.getValue(Siswa.class);
                if (dataSnapshot.getChildrenCount() > 0){
                    textNisn.setText(String.valueOf(siswa.nisn));
                    textNoKk.setText(String.valueOf(siswa.noKk));
                    textNamaLengkap.setText(siswa.namaLengkap);
                    textJk.setText(siswa.jenisKelamin);
                    textTempatLahir.setText(siswa.tempatLahir);
                    textTanggalLahir.setText(siswa.tanggalLahir);
                    textAlamat.setText(siswa.alamat);
                    textProvinsi.setText(siswa.provinsi);
                    textKota.setText(siswa.kota);
                    textKecamatan.setText(siswa.kecamatan);
                    textKelurahan.setText(siswa.kelurahan);
                    textPendidikan.setText(siswa.pendidikan);
                    textKelas.setText(siswa.kelas);
                    textAgama.setText(siswa.agama);
                    textStatus.setText("Status " + siswa.status);
                }else if (dataSnapshot.getChildrenCount() < 1){
                    showErrorMessage();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void showErrorMessage() {
        // COMPLETED (44) Hide mRecyclerView, not mWeatherTextView
        /* First, hide the currently visible data */
        textNisn.setVisibility(View.GONE);
        textNoKk.setVisibility(View.GONE);
        textNamaLengkap.setVisibility(View.GONE);
        textJk.setVisibility(View.GONE);
        textTempatLahir.setVisibility(View.GONE);
        textTanggalLahir.setVisibility(View.GONE);
        textAlamat.setVisibility(View.GONE);
        textProvinsi.setVisibility(View.GONE);
        textKota.setVisibility(View.GONE);
        textKecamatan.setVisibility(View.GONE);
        textKelurahan.setVisibility(View.GONE);
        textPendidikan.setVisibility(View.GONE);
        textKelas.setVisibility(View.GONE);
        textAgama.setVisibility(View.GONE);
        textStatus.setVisibility(View.GONE);

        textTitleNisn.setVisibility(View.GONE);
        textTitleNoKk.setVisibility(View.GONE);
        textTitleNamaLengkap.setVisibility(View.GONE);
        textTitleJk.setVisibility(View.GONE);
        textTitleTempatLahir.setVisibility(View.GONE);
        textTitleTanggalLahir.setVisibility(View.GONE);
        textTitleAlamat.setVisibility(View.GONE);
        textTitleProvinsi.setVisibility(View.GONE);
        textTitleKota.setVisibility(View.GONE);
        textTitleKecamatan.setVisibility(View.GONE);
        textTitleKelurahan.setVisibility(View.GONE);
        textTitlePendidikan.setVisibility(View.GONE);
        textTitleKelas.setVisibility(View.GONE);
        textTitleAgama.setVisibility(View.GONE);
        /* Then, show the error */
        textMessage.setText("Anda belum mendaftar KJP");
        textMessage.setGravity(View.TEXT_ALIGNMENT_CENTER);
        textMessage.setTextSize(20);
        textMessage.setPadding(16, 16, 16, 16);
    }

}