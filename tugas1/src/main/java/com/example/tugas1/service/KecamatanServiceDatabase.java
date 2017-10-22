package com.example.tugas1.service;

import com.example.tugas1.DAO.DAOkecamatan;
import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KotaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService{

    @Autowired
    private DAOkecamatan DAOkecamatan;

    public KecamatanModel pilihKecamatanById (int id_kecamatan) {
        return DAOkecamatan.pilihKecamatanById(id_kecamatan);
    }

    public List<KecamatanModel> pilihKecamatan (String namaKota) {
        return DAOkecamatan.pilihKecamatan(namaKota);
    }
}
