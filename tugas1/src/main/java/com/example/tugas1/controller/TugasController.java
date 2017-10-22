package com.example.tugas1.controller;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.tugas1.model.PendudukModel;
import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.KelurahanModel;
import com.example.tugas1.model.KecamatanModel;
import com.example.tugas1.model.KotaModel;
import com.example.tugas1.model.LainLainModel;
import com.example.tugas1.service.KotaService;
import com.example.tugas1.service.KecamatanService;
import com.example.tugas1.service.KeluargaService;
import com.example.tugas1.service.KelurahanService;
import com.example.tugas1.service.PendudukService;
import com.example.tugas1.service.LainLainService;


@Controller
public class TugasController {
    @Autowired
    PendudukService pendudukDAO;
    @Autowired
    KeluargaService keluargaDAO;
    @Autowired
    LainLainService LainLainDAO;
    @Autowired
    KelurahanService kelurahanDAO;
    @Autowired
    KotaService kotaDao;
    @Autowired
    KecamatanService kecamatanDAO;

    @RequestMapping("/")
    public String index (){
        return "index";
    }

    @RequestMapping("/penduduk")
    public String lihatPenduduk (Model model, @RequestParam(value = "nik") String nik) {
        PendudukModel penduduk = pendudukDAO.pilihPenduduk(nik);
        KeluargaModel keluarga = keluargaDAO.pilihKeluargaById(penduduk.getId_keluarga());
        LainLainModel lainlain = LainLainDAO.alamatByIdKelurahan(keluarga.getId_kelurahan());

        model.addAttribute("penduduk", penduduk);
        model.addAttribute("keluarga", keluarga);
        model.addAttribute("lainlain", lainlain);
        return "penduduk";
    }

    @RequestMapping("/keluarga")
    public String lihatKeluarga (Model model, @RequestParam(value = "nkk") String nkk) {
        KeluargaModel keluarga = keluargaDAO.pilihKeluarga(nkk);
        List<PendudukModel> anggotaKeluarga = pendudukDAO.pilihKeluargaId(keluarga.getId());
        LainLainModel lainlain = LainLainDAO.alamatByIdKelurahan(keluarga.getId_kelurahan());

        model.addAttribute ("keluarga", keluarga);
        model.addAttribute("anggota", anggotaKeluarga);
        model.addAttribute("lainlain", lainlain);
        return "keluarga";
    }

    @RequestMapping("/penduduk/tambah")
    public String tambahPendudukForm (Model model) {
        model.addAttribute("penduduk", new PendudukModel());
        return "tambah-penduduk";
    }

    @RequestMapping(value = "/penduduk/tambah", method = RequestMethod.POST)
    public String tambahPenduduk (Model model, @ModelAttribute PendudukModel penduduk) {
        KeluargaModel keluarga = keluargaDAO.pilihKeluargaById(penduduk.getId_keluarga());
        String tanggal = "";
        int tglNIK = 0;
        if (keluarga != null) {
            LainLainModel lainlain = LainLainDAO.kodeAlamatByIdKelurahan(keluarga.getId_kelurahan());
            String tanggalLahir = penduduk.getTanggal_lahir();
            tanggal = tanggalLahir.substring(8,10);
            if (penduduk.getJenis_kelamin() == 1) {
                tglNIK = Integer.parseInt(tanggal);
                tglNIK = tglNIK + 40;
                tanggal = Integer.toString(tglNIK);
            }
            String lahir = tanggal + tanggalLahir.substring(5,7) + tanggalLahir.substring(2,4);
            String kodeKecamatan = lainlain.getKode_kecamatan();
            String nikTemp = kodeKecamatan.substring(0,kodeKecamatan.length()-1) + lahir;

            String nikSebelum = LainLainDAO.nikSebelum(nikTemp);
            Long nik = Long.parseLong(nikTemp + "0001");
            if (nikSebelum != null) {
                nik = Long.parseLong(nikSebelum) + 1;
            }
            String nikPenduduk = Long.toString(nik);
            penduduk.setNik(nikPenduduk);

            pendudukDAO.tambahPenduduk(penduduk);
            model.addAttribute("nikTambah", nik);
            return "success";
        } else {
            model.addAttribute("id_keluarga", penduduk.getId_keluarga());
            return "not-found";
        }
    }

    @RequestMapping("/keluarga/tambah")
    public String tambahKeluargaForm (Model model) {
        model.addAttribute("keluarga", new KeluargaModel());
        return "tambah-keluarga";
    }

    @RequestMapping(value = "/keluarga/tambah", method = RequestMethod.POST)
    public String tambahKeluarga (Model model, @ModelAttribute KeluargaModel keluarga) {
        String nama_kelurahan = keluarga.getNama_kelurahan();
        String nama_kecamatan = keluarga.getNama_kecamatan();
        String nama_kota = keluarga.getNama_kota();
        LainLainModel kodeAlamat = LainLainDAO.kodeAlamatByNama(nama_kelurahan, nama_kecamatan, nama_kota);
        String kodeKecamatan = kodeAlamat.getKode_kecamatan();
        String kodeKelurahan = kodeAlamat.getKode_kelurahan();

        String tanggal = "";
        String rilisTanggal = "";
        String nkkTemp = "";
        String nkkSebelum = "";
        String nkkKeluarga = "";

        if (kodeAlamat != null) {
            LocalDate localDate = LocalDate.now();
            tanggal = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
            rilisTanggal = tanggal.substring(8,10) + tanggal.substring(5,7) + tanggal.substring(2,4);
            nkkTemp = kodeKecamatan.substring(0,kodeKecamatan.length()-1) + rilisTanggal;
            nkkSebelum = LainLainDAO.nkkSebelum(nkkTemp);
            Long nkk = Long.parseLong(nkkTemp + "0001");
            if(nkkSebelum != null) {
                nkk = Long.parseLong(nkkSebelum) + 1;
            }
            nkkKeluarga = Long.toString(nkk);
            keluarga.setNomor_kk(nkkKeluarga);
            keluarga.setId_kelurahan(kelurahanDAO.pilihIdByKodeKelurahan(kodeKelurahan));

            model.addAttribute("nkkTambah", nkkKeluarga);
            keluargaDAO.tambahKeluarga(keluarga);
            return "succsess";
        } else {
            model.addAttribute("alamat", keluarga.getAlamat());
            return "not-found";
        }
    }

    @RequestMapping(value = "/penduduk/mati", method = RequestMethod.POST)
    public String ubahStatusKematian (Model model, @RequestParam(value = "nik") String nik) {
        PendudukModel penduduk = pendudukDAO.pilihPenduduk(nik);
        pendudukDAO.ubahStatusKematian(nik);

        int id_keluarga = penduduk.getId_keluarga();
        List<PendudukModel> anggotaKeluarga = pendudukDAO.pilihKeluargaId(id_keluarga);
        int wafat = 0;
        for (PendudukModel t : anggotaKeluarga) {
            if (t.getIs_wafat() == 1) {
                wafat += 1;
            }
        }

        if (wafat == anggotaKeluarga.size()) {
            keluargaDAO.updateStatusBerlaku(id_keluarga);
        }
        model.addAttribute("nikKematian", nik);
        return "success";
    }

    @RequestMapping("/penduduk/ubah/{nik}")
    public String ubahPendudukForm (Model model, @PathVariable(value = "nik") String nik) {
        PendudukModel penduduk = pendudukDAO.pilihPenduduk(nik);
        if(penduduk != null) {
            model.addAttribute("penduduk", penduduk);
            return "ubah-penduduk";
        } else {
            model.addAttribute("nik", nik);
            return "not-found";
        }
    }

    @RequestMapping(value = "/penduduk/ubah/{nik}", method = RequestMethod.POST)
    public String ubahPenduduk (Model model, @ModelAttribute PendudukModel penduduk) {
        String nik = penduduk.getNik();
        PendudukModel pendudukNow = pendudukDAO.pilihPenduduk(nik);
        KeluargaModel keluarga = keluargaDAO.pilihKeluargaById(penduduk.getId_keluarga());
        int idPenduduk = pendudukNow.getId();
        String nikNow = nik.substring(0,12);

        if (keluarga != null) {
            LainLainModel lainlain = LainLainDAO.kodeAlamatByIdKelurahan(keluarga.getId_kelurahan());
            String tanggalLahir = penduduk.getTanggal_lahir();
            String tanggal = tanggalLahir.substring(8,10);
            if (penduduk.getJenis_kelamin() == 1) {
                int tgl = Integer.parseInt(tanggal);
                tgl += 40;
                tanggal = Integer.toString(tgl);
            }
            String lahir = tanggal + tanggalLahir.substring(5,7) + tanggalLahir.substring(2,4);
            String kodeKecamatan = lainlain.getKode_kecamatan();
            String nikTemp = kodeKecamatan.substring(0,kodeKecamatan.length()-1) + lahir;
            String nikSebelum = LainLainDAO.nikSebelum(nikTemp);

            if (!nik.equals(nikNow)) {
                Long nikNew = Long.parseLong(nikTemp + "0001");
                if (nikSebelum != null) {
                    nikNew = Long.parseLong(nikSebelum) + 1;
                }
                String nikPenduduk = Long.toString(nikNew);
                penduduk.setNik(nikPenduduk);
            }
            if (penduduk.getGolongan_darah() == null) {
                penduduk.setGolongan_darah(penduduk.getGolongan_darah());
            } if (penduduk.getStatus_perkawinan() == null) {
                penduduk.setStatus_perkawinan(penduduk.getStatus_perkawinan());
            } if (penduduk.getStatus_dalam_keluarga() == null) {
                penduduk.setStatus_dalam_keluarga(penduduk.getStatus_dalam_keluarga());
            }

            pendudukDAO.ubahPenduduk(penduduk, idPenduduk);
            model.addAttribute("nikUbah", nik);
            return "success";
        } else {
            model.addAttribute ("idKeluarga", penduduk.getId_keluarga());
            return "not-found";
        }

    }

    @RequestMapping("/keluarga/ubah/{nkk}")
    public String ubahKeluargaForm (Model model, @PathVariable(value = "nkk") String nkk) {
        KeluargaModel keluarga = keluargaDAO.pilihKeluarga(nkk);
        if (keluarga != null) {
            LainLainModel lainlain = LainLainDAO.alamatByIdKelurahan(keluarga.getId_kelurahan());
            keluarga.setNama_kelurahan(lainlain.getNama_kelurahan());
            keluarga.setNama_kecamatan(lainlain.getNama_kecamatan());
            keluarga.setNama_kota(lainlain.getNama_kota());
            model.addAttribute("keluarga", keluarga);
            return "ubah-keluarga";
        } else {
            model.addAttribute("nkk", nkk);
            return "not-found";
        }

    }

    @RequestMapping(value = "/keluarga/ubah/{nkk}", method = RequestMethod.POST)
    public String ubahKeluarga (Model model, @ModelAttribute KeluargaModel keluarga, @PathVariable(value = "nkk") String nkk) {
        String namaKelurahan = keluarga.getNama_kelurahan();
        String namaKecamatan = keluarga.getNama_kecamatan();
        String namaKota = keluarga.getNama_kota();
        LainLainModel lainlain = LainLainDAO.kodeAlamatByNama(namaKelurahan, namaKecamatan, namaKota);
        String kodeKecamatan = lainlain.getKode_kecamatan();
        String kodeKelurahan = lainlain.getKode_kelurahan();

        int id = keluargaDAO.pilihKeluarga(nkk).getId();
        String nkkNow = nkk.substring(0,6);
        if(lainlain != null) {
            if(!kodeKecamatan.equals(nkkNow)) {
                LocalDate localDate = LocalDate.now();
                String tanggal = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(localDate);
                String tanggalRilis = tanggal.substring(8,10) + tanggal.substring(5,7) + tanggal.substring(2,4);
                String nkkTemp = kodeKecamatan.substring(0,kodeKecamatan.length()-1) + tanggalRilis;
                String nkkSebelum = LainLainDAO.nkkSebelum(nkkTemp);
                Long nkkBaru = Long.parseLong(nkkTemp + "0001");
                if (nkkSebelum != null) {
                    nkkBaru = Long.parseLong(nkkSebelum) + 1;
                }
                keluarga.setNomor_kk(Long.toString(nkkBaru));
            }
            keluarga.setId_kelurahan(kelurahanDAO.pilihIdByKodeKelurahan(kodeKelurahan));
            model.addAttribute("nkkUbah", nkk);
            keluargaDAO.ubahKeluarga(keluarga, id);
            return "success";
        } else {
            model.addAttribute("alamat", keluarga.getAlamat());
            return "not-found";
        }
    }
    @RequestMapping(value = "/penduduk/cari")
    public String cariPenduduk (Model model, @RequestParam(value = "kt", required = false) String nama_kota, @RequestParam(value = "kc", required = false) String nama_kecamatan, @RequestParam(value = "kl", required = false) String nama_kelurahan) {
        List<KotaModel> kota_list = kotaDao.selectAllKota();
        model.addAttribute("kota_list", kota_list);
        if (nama_kelurahan != null) {
            List<KelurahanModel> kelurahan_list = kelurahanDAO.pilihKelurahan(nama_kecamatan);
            int idKelurahan = 0;
            for (KelurahanModel kelurahan : kelurahan_list) {
                if (kelurahan.getNama_kelurahan().equals(nama_kelurahan)) {
                    idKelurahan = kelurahan.getId();
                }
            }
            List<PendudukModel> penduduk_list = pendudukDAO.pilihPendudukByIdKelurahan(idKelurahan);
            model.addAttribute("nama_kota", nama_kota);
            model.addAttribute("nama_kecamatan", nama_kecamatan);
            model.addAttribute("nama_kelurahan", nama_kelurahan);
            model.addAttribute("view", "view");
            model.addAttribute("penduduk_list", penduduk_list);
        } else if (nama_kecamatan != null) {
            List<KecamatanModel> kecamatan_list = kecamatanDAO.pilihKecamatan(nama_kota);
            List<KelurahanModel> kelurahan_list = kelurahanDAO.pilihKelurahan(nama_kecamatan);
            model.addAttribute("nama_kota", nama_kota);
            model.addAttribute("nama_kecamatan", nama_kecamatan);
            model.addAttribute("kecamatan_list", kecamatan_list);
            model.addAttribute("kelurahan_list", kelurahan_list);
        } else if (nama_kota != null) {
            List<KecamatanModel> kecamatan_list = kecamatanDAO.pilihKecamatan(nama_kota);
            model.addAttribute("nama_kota", nama_kota);
            model.addAttribute("kecamatan_list", kecamatan_list);
        }
        return "cari-penduduk";

    }

}
