package com.example.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KecamatanModel {
    private int id;
    private String kode_kecamatan;
    private int id_kota;
    private String nama_kecamatan;
    private List<KelurahanModel> kelurahan_list;
}
