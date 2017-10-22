package com.example.tugas1.service;

import com.example.tugas1.DAO.DAOkelurahan;
import com.example.tugas1.model.KelurahanModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
    @Autowired
    private DAOkelurahan DAOkelurahan;

    public KelurahanModel pilihKelurahanById (int id_kelurahan) {
        return DAOkelurahan.pilihKelurahanById(id_kelurahan);
    }

    public int pilihIdByKodeKelurahan (String kodeKelurahan) {
        return DAOkelurahan.pilihIdByKodeKelurahan(kodeKelurahan);
    }

    public List<KelurahanModel> pilihKelurahan (String namaKecamatan) {
        return DAOkelurahan.pilihKelurahan(namaKecamatan);
    }
}
