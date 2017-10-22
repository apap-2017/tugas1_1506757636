package com.example.tugas1.service;


import com.example.tugas1.DAO.DAOkeluarga;
import com.example.tugas1.model.KeluargaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
    @Autowired
    private DAOkeluarga DAOkeluarga;

    public KeluargaModel pilihKeluargaById (int id_keluarga) {
        return DAOkeluarga.pilihKeluargaById(id_keluarga);
    }

    public KeluargaModel pilihKeluarga (String nkk) {
        return DAOkeluarga.pilihKeluarga(nkk);
    }

    public void tambahKeluarga (KeluargaModel keluarga) {
        DAOkeluarga.tambahKeluarga(keluarga);
    }

    public void updateStatusBerlaku (int id) {
        DAOkeluarga.updateStatusBerlaku(id);
    }

    public void ubahKeluarga (KeluargaModel keluarga, int id) {
        DAOkeluarga.ubahKeluarga(keluarga, id);
    }
}
