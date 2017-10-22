package com.example.tugas1.service;

import com.example.tugas1.model.PendudukModel;

import java.util.List;

public interface PendudukService {
    PendudukModel pilihPenduduk (String nik);

    List<PendudukModel> pilihKeluargaId (int id_keluarga);

    void tambahPenduduk (PendudukModel penduduk);

    void ubahStatusKematian (String nik);

    void ubahPenduduk (PendudukModel penduduk, int id);

    List<PendudukModel> pilihPendudukByIdKelurahan(int id_kelurahan);
}
