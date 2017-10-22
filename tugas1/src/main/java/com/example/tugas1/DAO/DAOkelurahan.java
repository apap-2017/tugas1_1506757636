package com.example.tugas1.DAO;

import com.example.tugas1.model.KelurahanModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DAOkelurahan {
    @Select("select * from kelurahan where id = #{id_kelurahan}")
    KelurahanModel pilihKelurahanById(int id_kelurahan);

    @Select("select id from kelurahan where kode_kelurahan = #{kode_kelurahan}")
    int pilihIdByKodeKelurahan(String kodeKelurahan);

    @Select("select * from kelurahan JOIN "
            + "(select id from kecamatan where nama_kecamatan = #{nama_kecamatan}) AS kecamatan "
            + "ON kecamatan.id = kelurahan.id_kecamatan")
    List<KelurahanModel> pilihKelurahan (@Param("nama_kecamatan")String nama_kecamatan);
}
