package com.example.tugas1.service;

import com.example.tugas1.DAO.DAOLainLain;
import com.example.tugas1.model.LainLainModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LainLainDatabase implements LainLainService {
    @Autowired
    private DAOLainLain DAOLainLain;

    public LainLainModel alamatByIdKelurahan (int id_kelurahan) {
        return DAOLainLain.alamatByIdKelurahan(id_kelurahan);
    }

    public String nikSebelum (String nikPenduduk) {
        return DAOLainLain.nikSebelum(nikPenduduk);
    }

    public String nkkSebelum (String nkkKeluarga) {
        return DAOLainLain.nkkSebelum(nkkKeluarga);
    }

    public LainLainModel kodeAlamatByNama (String nama_kelurahan, String nama_kecamatan, String nama_kota) {
        return DAOLainLain.kodeAlamatByNama(nama_kelurahan, nama_kecamatan, nama_kota);
    }

    public LainLainModel kodeAlamatByIdKelurahan (int id_kelurahan) {
        return DAOLainLain.kodeAlamatByIdKelurahan(id_kelurahan);
    }
}
