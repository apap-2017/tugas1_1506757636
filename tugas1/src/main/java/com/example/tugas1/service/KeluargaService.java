package com.example.tugas1.service;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;

public interface KeluargaService {
    KeluargaModel pilihKeluargaById (int id_keluarga);

    KeluargaModel pilihKeluarga (String nkk);

    void tambahKeluarga (KeluargaModel keluarga);

    void updateStatusBerlaku (int id);

    void ubahKeluarga (KeluargaModel keluarga, int id);
}
