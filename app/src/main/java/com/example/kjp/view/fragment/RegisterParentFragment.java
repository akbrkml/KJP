package com.example.kjp.view.fragment;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.kjp.MainActivity;
import com.akbar.dev.kjp.R;
import com.example.kjp.model.Ortu;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterParentFragment extends Fragment {

    private static final String TAG = RegisterParentFragment.class.getSimpleName();

    private DatabaseReference mDatabase;

    @BindView(R.id.noktp)EditText textNoKtp;
    @BindView(R.id.nokk)EditText textNoKk;
    @BindView(R.id.nama)EditText textNama;
    @BindView(R.id.jk)EditText textJk;
    @BindView(R.id.tempat_lahir)EditText textTempatLahir;
    @BindView(R.id.tanggal_lahir)EditText textTanggalLahir;
    @BindView(R.id.alamat)EditText textAlamat;
    @BindView(R.id.provinsi)EditText textProvinsi;
    @BindView(R.id.kota)EditText textKota;
    @BindView(R.id.kecamatan)EditText textKecamatan;
    @BindView(R.id.kelurahan)EditText textKelurahan;
    @BindView(R.id.pendidikan)EditText textPendidikan;
    @BindView(R.id.agama)EditText textAgama;
    @BindView(R.id.tipe_alamat)EditText textTipeAlamat;
    @BindView(R.id.nohp)EditText textNoHp;
    @BindView(R.id.pekerjaan)EditText textPekerjaan;
    @BindView(R.id.kodepos)EditText textKodePos;
    @BindView(R.id.status)EditText textStatusTempat;

    private FragmentManager fm;
    private ProgressDialog progressDialog;

    public String nama, noktp, noKk,
            tempatLahir, tanggalLahir, jenisKelamin,
            agama, alamat, provinsi,
            kota, kecamatan, kelurahan,
            kodepos, statusTempat, noHp,
            tipeAlamat, pekerjaan, pendidikan;

    public RegisterParentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_parent, container, false);

        ButterKnife.bind(this, view);

        getActivity().setTitle("Daftar KJP");

        initComponents();

        return view;
    }

    private void getData(){

        noktp = textNoKtp.getText().toString();
        noKk = textNoKk.getText().toString();
        nama = textNama.getText().toString().trim();
        jenisKelamin = textJk.getText().toString().trim();
        tempatLahir = textTempatLahir.getText().toString().trim();
        tanggalLahir = textTanggalLahir.getText().toString().trim();
        alamat = textAlamat.getText().toString().trim();
        provinsi = textProvinsi.getText().toString().trim();
        kota = textKota.getText().toString().trim();
        kecamatan = textKecamatan.getText().toString().trim();
        kelurahan = textKelurahan.getText().toString().trim();
        pendidikan = textPendidikan.getText().toString().trim();
        tipeAlamat = textTipeAlamat.getText().toString().trim();
        agama = textAgama.getText().toString().trim();
        statusTempat = textStatusTempat.getText().toString().trim();
        noHp = textNoHp.getText().toString().trim();
        pekerjaan = textPekerjaan.getText().toString().trim();
        kodepos = textKodePos.getText().toString().trim();
    }

    @OnClick(R.id.register_button)
    public void doRegister(){
        if (!validate()){
            return;
        }

        getData();

        showProgress();

        registerParent(MainActivity.userId, nama, Long.parseLong(noktp), Long.parseLong(noKk), tempatLahir, tanggalLahir, jenisKelamin, agama, alamat, provinsi, kota, kecamatan, kelurahan, Long.parseLong(kodepos), statusTempat, noHp, tipeAlamat, pekerjaan, pendidikan);
    }

    private boolean validate(){
        boolean result = true;

        getData();

        if (TextUtils.isEmpty(String.valueOf(noKk))) {
            textNoKtp.setError("Enter No. KTP!");
            result = false;
        } else {
            textNoKtp.setError(null);
        }

        if (TextUtils.isEmpty(String.valueOf(noKk))) {
            textNoKk.setError("Enter No KK!");
            result = false;
        } else {
            textNoKk.setError(null);
        }

        if (TextUtils.isEmpty(nama)) {
            textNama.setError("Enter name!");
            result = false;
        } else {
            textNama.setError(null);
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

        if (TextUtils.isEmpty(kodepos)) {
            textKodePos.setError("Enter kode pos!");
            result = false;
        } else {
            textKodePos.setError(null);
        }

        if (TextUtils.isEmpty(agama)) {
            textAgama.setError("Enter religion!");
            result = false;
        } else {
            textAgama.setError(null);
        }

        if (TextUtils.isEmpty(pekerjaan)) {
            textPekerjaan.setError("Enter the job!");
            result = false;
        } else {
            textPekerjaan.setError(null);
        }

        if (TextUtils.isEmpty(statusTempat)) {
            textStatusTempat.setError("Enter status place!");
            result = false;
        } else {
            textStatusTempat.setError(null);
        }

        if (TextUtils.isEmpty(noHp)) {
            textNoHp.setError("Enter phone number!");
            result = false;
        } else {
            textNoHp.setError(null);
        }

        if (TextUtils.isEmpty(tipeAlamat)) {
            textTipeAlamat.setError("Enter address type!");
            result = false;
        } else {
            textTipeAlamat.setError(null);
        }

        return result;
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

    private void registerParent(String userId, String nama, Long noKtp, Long noKk, String tempatLahir, String tanggalLahir, String jenisKelamin, String agama, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, Long kodePos, String statusTempat, String noHp, String tipeAlamat, String pekerjaan, String pendidikan){

        Ortu ortu = new Ortu(nama, noKtp, noKk,
                tempatLahir, tanggalLahir, jenisKelamin,
                agama, alamat, provinsi,
                kota, kecamatan, kelurahan,
                kodePos, statusTempat, noHp,
                tipeAlamat, pekerjaan, pendidikan);

        mDatabase.child("users").child(userId).child("ortu").setValue(ortu).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                hideProgress();
                if (!task.isSuccessful()) {
                    showMessageSnackbar("Failed to inserting your data");
                } else {
                    cleanText();
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle("Registration");
                    alertDialogBuilder.setMessage("Your biodata has been saved.");
                    alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            fm = getFragmentManager();
                            fm.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
                        }
                    });
                    alertDialogBuilder.show();
                }
            }
        });
    }

    private void showMessageSnackbar(String message) {
        Snackbar.make(getActivity().findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG)
                .show();
    }

    private void cleanText(){
        textNoKtp.setText("");
        textNoKk.setText("");
        textNama.setText("");
        textJk.setText("");
        textTempatLahir.setText("");
        textTanggalLahir.setText("");
        textAlamat.setText("");
        textProvinsi.setText("");
        textKota.setText("");
        textKecamatan.setText("");
        textKelurahan.setText("");
        textPendidikan.setText("");
        textPekerjaan.setText("");
        textAgama.setText("");
        textTipeAlamat.setText("");
        textStatusTempat.setText("");
        textKodePos.setText("");
        textNoHp.setText("");
    }
}