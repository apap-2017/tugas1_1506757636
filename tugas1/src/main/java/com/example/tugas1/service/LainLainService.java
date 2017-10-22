package com.example.tugas1.service;

import com.example.tugas1.model.LainLainModel;

public interface LainLainService {
    LainLainModel alamatByIdKelurahan (int id_kelurahan);

    String nikSebelum (String nikPenduduk);

    String nkkSebelum (String nkkKeluarga);

    LainLainModel kodeAlamatByNama (String namaKelurahan, String namaKecamatan, String namaKota);

    LainLainModel kodeAlamatByIdKelurahan (int id_kelurahan);
}
