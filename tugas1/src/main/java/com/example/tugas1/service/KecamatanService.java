package com.example.tugas1.service;

import com.example.tugas1.model.KecamatanModel;

import java.util.List;

public interface KecamatanService {

    KecamatanModel pilihKecamatanById (int id_kelurahan);

    List<KecamatanModel> pilihKecamatan (String namaKota);
}
