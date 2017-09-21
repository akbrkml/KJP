package com.example.kjp.view.fragment;


import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kjp.MainActivity;
import com.akbar.dev.kjp.R;
import com.example.kjp.model.Siswa;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private static final String TAG = RegisterFragment.class.getSimpleName();

    private DatabaseReference mDatabase;

    @BindView(R.id.nisn)EditText textNisn;
    @BindView(R.id.nokk)EditText textNoKk;
    @BindView(R.id.nama)EditText textNamaLengkap;
    @BindView(R.id.jk)EditText textJk;
    @BindView(R.id.tempat_lahir)EditText textTempatLahir;
    @BindView(R.id.tanggal_lahir)EditText textTanggalLahir;
    @BindView(R.id.alamat)EditText textAlamat;
    @BindView(R.id.provinsi)EditText textProvinsi;
    @BindView(R.id.kota)EditText textKota;
    @BindView(R.id.kecamatan)EditText textKecamatan;
    @BindView(R.id.kelurahan)EditText textKelurahan;
    @BindView(R.id.pendidikan)EditText textPendidikan;
    @BindView(R.id.kelas)EditText textKelas;
    @BindView(R.id.agama)EditText textAgama;
    @BindView(R.id.register_button)Button mButton;
    @BindView(R.id.tv_message_display)TextView textViewMessage;
    @BindView(R.id.form)TextView textViewForm;
    @BindView(R.id.data)TextView textViewData;

    private FragmentManager fm;
    private ProgressDialog progressDialog;

    public String nisn, noKk, namaLengkap,
            jenisKelamin, tempatLahir, tanggalLahir,
            alamat, provinsi, kota,
            kecamatan, kelurahan, pendidikan,
            kelas, agama, status;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.bind(this, view);

        getActivity().setTitle("Daftar KJP");

        initComponents();

        doCheck();

        return view;
    }

    private void initComponents(){
        mDatabase = FirebaseDatabase.getInstance().getReference();

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Saving data...");
    }

    private void hideProgress() {
        if (progressDialog != null){
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    private void showProgress() {
        progressDialog.show();
    }

    private void doCheck(){
        mDatabase.child("users").child(MainActivity.userId).child("siswa").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
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
                    mButton.setVisibility(View.GONE);
                    textViewData.setVisibility(View.GONE);
                    textViewForm.setVisibility(View.GONE);
                    textViewMessage.setVisibility(View.VISIBLE);
                    textViewMessage.setText("Anda sudah mendaftar KJP");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @OnClick(R.id.register_button)
    public void doRegister(){
        if (!validate()){
            return;
        }

        getData();

        showProgress();

        registerKjp(MainActivity.userId, Long.parseLong(nisn), Long.parseLong(noKk), namaLengkap, jenisKelamin, tempatLahir, tanggalLahir, alamat, provinsi, kota, kecamatan, kelurahan, pendidikan, kelas, agama, "Belum diverifikasi");

        fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new RegisterParentFragment()).commit();
    }

    private void getData(){

        nisn = textNisn.getText().toString();
        noKk = textNoKk.getText().toString();
        namaLengkap = textNamaLengkap.getText().toString().trim();
        jenisKelamin = textJk.getText().toString().trim();
        tempatLahir = textTempatLahir.getText().toString().trim();
        tanggalLahir = textTanggalLahir.getText().toString().trim();
        alamat = textAlamat.getText().toString().trim();
        provinsi = textProvinsi.getText().toString().trim();
        kota = textKota.getText().toString().trim();
        kecamatan = textKecamatan.getText().toString().trim();
        kelurahan = textKelurahan.getText().toString().trim();
        pendidikan = textPendidikan.getText().toString().trim();
        kelas = textKelas.getText().toString().trim();
        agama = textAgama.getText().toString().trim();
    }

    private boolean validate(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(String.valueOf(nisn))) {
            textNisn.setError("Enter NISN!");
            result = false;
        } else {
            textNisn.setError(null);
        }

        if (TextUtils.isEmpty(String.valueOf(noKk))) {
            textNoKk.setError("Enter No KK!");
            result = false;
        } else {
            textNoKk.setError(null);
        }

        if (TextUtils.isEmpty(namaLengkap)) {
            textNamaLengkap.setError("Enter name!");
            result = false;
        } else {
            textNamaLengkap.setError(null);
        }

        if (TextUtils.isEmpty(jenisKelamin)) {
            textJk.setError("Enter gender!");
            result = false;
        } else {
            textJk.setError(null);
        }

        if (TextUtils.isEmpty(tempatLahir)) {
            textTempatLahir.setError("Enter place of birth!");
            result = false;
        } else {
            textTempatLahir.setError(null);
        }

        if (TextUtils.isEmpty(tanggalLahir)) {
            textTanggalLahir.setError("Enter date of birth!");
            result = false;
        } else {
            textTanggalLahir.setError(null);
        }

        if (TextUtils.isEmpty(alamat)) {
            textAlamat.setError("Enter address!");
            result = false;
        } else {
            textAlamat.setError(null);
        }

        if (TextUtils.isEmpty(provinsi)) {
            textProvinsi.setError("Enter province!");
            result = false;
        } else {
            textProvinsi.setError(null);
        }

        if (TextUtils.isEmpty(kota)) {
            textKota.setError("Enter city!");
            result = false;
        } else {
            textKota.setError(null);
        }

        if (TextUtils.isEmpty(kecamatan)) {
            textKecamatan.setError("Enter kecamatan!");
            result = false;
        } else {
            textKecamatan.setError(null);
        }

        if (TextUtils.isEmpty(kelurahan)) {
            textKelurahan.setError("Enter kelurahan!");
            result = false;
        } else {
            textKelurahan.setError(null);
        }

        if (TextUtils.isEmpty(pendidikan)) {
            textPendidikan.setError("Enter pendidikan!");
            result = false;
        } else {
            textPendidikan.setError(null);
        }

        if (TextUtils.isEmpty(kelas)) {
            textKelas.setError("Enter class!");
            result = false;
        } else {
            textKelas.setError(null);
        }

        if (TextUtils.isEmpty(agama)) {
            textAgama.setError("Enter religion!");
            result = false;
        } else {
            textAgama.setError(null);
        }

        return result;
    }

    private void registerKjp(String userId, Long nisn, Long noKk, String namaLengkap, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, String pendidikan, String kelas, String agama, String status){

        Siswa siswa = new Siswa(nisn, noKk, namaLengkap,
                jenisKelamin, tempatLahir, tanggalLahir,
                alamat, provinsi, kota,
                kecamatan, kelurahan, pendidikan,
                kelas, agama, status);

        mDatabase.child("users").child(userId).child("siswa").setValue(siswa).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                hideProgress();
                if (!task.isSuccessful()) {
                    showMessageSnackbar("Failed to inserting your data");
                } else {
                    cleanFields();
                }
            }
        });
    }

    private void cleanFields(){
        textNisn.setText("");
        textNoKk.setText("");
        textNamaLengkap.setText("");
        textJk.setText("");
        textTempatLahir.setText("");
        textTanggalLahir.setText("");
        textAlamat.setText("");
        textProvinsi.setText("");
        textKota.setText("");
        textKecamatan.setText("");
        textKelurahan.setText("");
        textPendidikan.setText("");
        textKelas.setText("");
        textAgama.setText("");
    }

    private void showMessageSnackbar(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }
}