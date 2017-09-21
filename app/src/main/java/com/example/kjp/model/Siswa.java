package com.example.kjp.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by akbar on 14/06/17.
 */

@IgnoreExtraProperties
public class Siswa {

    public Long nisn, noKk;
    public String namaLengkap, jenisKelamin, tempatLahir, tanggalLahir, alamat, provinsi, kota, kecamatan, kelurahan, pendidikan, kelas, agama, status;

    public Siswa(){
        // Default constructor required for calls to DataSnapshot.getValue(Siswa.class)
    }

    public Siswa(Long nisn, Long noKk, String namaLengkap, String jenisKelamin, String tempatLahir, String tanggalLahir, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, String pendidikan, String kelas, String agama, String status){
        this.nisn = nisn;
        this.noKk = noKk;
        this.namaLengkap = namaLengkap;
        this.jenisKelamin = jenisKelamin;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.pendidikan = pendidikan;
        this.kelas = kelas;
        this.agama = agama;
        this.status = status;
    }
}
