package com.example.tugas1.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendudukModel {
    private int id;
    private String nik;
    private String nama;
    private String tempat_lahir;
    private String tanggal_lahir;
    private int is_wni;
    private int id_keluarga;
    private String agama;
    private String pekerjaan;
    private String status_perkawinan;
    private String status_dalam_keluarga;
    private String golongan_darah;
    private int is_wafat;
    private int jenis_kelamin;
    private LainLainModel lainlain;
}
