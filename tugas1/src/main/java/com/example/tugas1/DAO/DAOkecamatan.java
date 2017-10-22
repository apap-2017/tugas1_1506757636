package com.example.tugas1.DAO;


import com.example.tugas1.model.KecamatanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DAOkecamatan {
    @Select("select * from kecamatan JOIN "
            + "(select id from kota where nama_kota = #{nama_kota}) AS kota "
            + "ON kota.id = kecamatan.id_kota")
    List<KecamatanModel> pilihKecamatan (@Param("nama_kota")String namaKota);

    @Select("select * from kecamatan where id = #{id_kecamatan}")
    KecamatanModel pilihKecamatanById(int id_kecamatan);
}
