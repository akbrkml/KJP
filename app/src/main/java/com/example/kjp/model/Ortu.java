package com.example.kjp.model;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by akbar on 08/07/17.
 */
@IgnoreExtraProperties
public class Ortu {
    public Long noKtp, noKk, kodePos;
    public String nama, jenisKelamin, tempatLahir, tanggalLahir, alamat, provinsi, kota, kecamatan, kelurahan, pendidikan, agama, statusTempat, noHp, tipeAlamat, pekerjaan;

    public Ortu(){
        // Default constructor required for calls to DataSnapshot.getValue(Ortu.class)
    }

    public Ortu(String nama, Long noKtp, Long noKk, String tempatLahir, String tanggalLahir, String jenisKelamin, String agama, String alamat, String provinsi, String kota, String kecamatan, String kelurahan, Long kodePos, String statusTempat, String noHp, String tipeAlamat, String pekerjaan, String pendidikan){
        this.nama = nama;
        this.noKtp = noKtp;
        this.noKk = noKk;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.agama = agama;
        this.alamat = alamat;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.kodePos = kodePos;
        this.statusTempat = statusTempat;
        this.noHp = noHp;
        this.tipeAlamat = tipeAlamat;
        this.pekerjaan = pekerjaan;
        this.pendidikan = pendidikan;
    }

    public Long getNoKtp() {
        return noKtp;
    }

    public void setNoKtp(Long noKtp) {
        this.noKtp = noKtp;
    }

    public Long getNoKk() {
        return noKk;
    }

    public void setNoKk(Long noKk) {
        this.noKk = noKk;
    }

    public Long getKodePos() {
        return kodePos;
    }

    public void setKodePos(Long kodePos) {
        this.kodePos = kodePos;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(String tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getPendidikan() {
        return pendidikan;
    }

    public void setPendidikan(String pendidikan) {
        this.pendidikan = pendidikan;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStatusTempat() {
        return statusTempat;
    }

    public void setStatusTempat(String statusTempat) {
        this.statusTempat = statusTempat;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getTipeAlamat() {
        return tipeAlamat;
    }

    public void setTipeAlamat(String tipeAlamat) {
        this.tipeAlamat = tipeAlamat;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }
}
