package com.example.tugas1.service;

import com.example.tugas1.model.KotaModel;

import java.util.List;

public interface KotaService {

    List<KotaModel> selectAllKota ();

    KotaModel pilihKotaById(int id_kota);
}
