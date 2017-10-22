package com.example.tugas1.service;

import com.example.tugas1.DAO.DAOpenduduk;
import com.example.tugas1.model.PendudukModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService{
    @Autowired
    private DAOpenduduk DAOpenduduk;

    public PendudukModel pilihPenduduk (String nik) {
        return DAOpenduduk.pilihPenduduk(nik);
    }
    public List<PendudukModel> pilihKeluargaId (int id_keluarga) {
        return DAOpenduduk.pilihKeluargaId(id_keluarga);
    }

    public void tambahPenduduk (PendudukModel penduduk) {
        DAOpenduduk.tambahPenduduk(penduduk);
    }

    public void ubahStatusKematian (String nik) {
        DAOpenduduk.ubahStatusKematian(nik);
    }

    public void ubahPenduduk (PendudukModel penduduk, int id) {
        DAOpenduduk.ubahPenduduk(penduduk, id);
    }

    public List<PendudukModel> pilihPendudukByIdKelurahan(int id_kelurahan) {
        return DAOpenduduk.pilihPendudukByIdKelurahan(id_kelurahan);
    }
}
