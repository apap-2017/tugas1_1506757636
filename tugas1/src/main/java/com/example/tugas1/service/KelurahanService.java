package com.example.tugas1.service;

import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.PendudukModel;

import java.util.List;

public interface KelurahanService {
    KelurahanModel pilihKelurahanById (int id_kelurahan);

    int pilihIdByKodeKelurahan (String kodeKelurahan);

    List<KelurahanModel> pilihKelurahan (String namaKecamatan);

}
