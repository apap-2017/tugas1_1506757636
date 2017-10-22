package com.example.tugas1.DAO;

import com.example.tugas1.model.KeluargaModel;
import com.example.tugas1.model.PendudukModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DAOpenduduk {
    @Select("select * from penduduk where nik = #{nik}")
    PendudukModel pilihPenduduk (String nik);

    @Select("select * from penduduk where id_keluarga = #{id_keluarga}")
    List<PendudukModel> pilihKeluargaId (int id_keluarga);

    @Insert("INSERT INTO penduduk (nik, nama, tempat_lahir, tanggal_lahir, jenis_kelamin, is_wni, id_keluarga, agama, pekerjaan, status_perkawinan, status_dalam_keluarga, golongan_darah, is_wafat)"
            + "VALUES ('${nik}', '${nama}', '${tempat_lahir}', '${tanggal_lahir}', ${jenis_kelamin}, ${is_wni}, '${id_keluarga}', '${agama}', "
            + "'${pekerjaan}', '${status_perkawinan}', '${status_dalam_keluarga}', '${golongan_darah}', '${is_wafat}')")
    void tambahPenduduk (PendudukModel penduduk);

    @Update("UPDATE penduduk SET  is_wafat = '1' WHERE nik = #{nik}")
    void ubahStatusKematian(@Param("nik") String nik);

    @Update("UPDATE penduduk SET nik = '${penduduk.nik}', nama = '${penduduk.nama}', tempat_lahir = '${penduduk.tempat_lahir}', tanggal_lahir = '${penduduk.tanggal_lahir}', "
            + "jenis_kelamin = '${penduduk.jenis_kelamin}', is_wni = '${penduduk.is_wni}', id_keluarga = '${penduduk.id_keluarga}', agama = '${penduduk.agama}', "
            + "pekerjaan = '${penduduk.pekerjaan}', status_perkawinan = '${penduduk.status_perkawinan}', status_dalam_keluarga = '${penduduk.status_dalam_keluarga}', "
            + "golongan_darah = '${penduduk.golongan_darah}' "
            + "WHERE id = #{id}")
    void ubahPenduduk (@Param("penduduk") PendudukModel penduduk, @Param("id") int id);

    @Select("select * from penduduk JOIN "
            + "(select id from keluarga where id_kelurahan = #{id_kelurahan}) AS keluarga "
            + "ON keluarga.id = penduduk.id_keluarga")
    List<PendudukModel> pilihPendudukByIdKelurahan(int id_kelurahan);
}
