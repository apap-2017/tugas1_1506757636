package com.example.tugas1.service;

import java.util.List;

import com.example.tugas1.DAO.DAOkota;
import com.example.tugas1.model.KotaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaServiceDatabase implements KotaService{
    @Autowired
    private DAOkota DAOkota;

    public KotaModel pilihKotaById(int id_kota) {
        return DAOkota.pilihKotaById(id_kota);
    }

    public List<KotaModel> selectAllKota() {
        return DAOkota.selectAllKota();
    }
}
