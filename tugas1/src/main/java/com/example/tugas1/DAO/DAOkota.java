package com.example.tugas1.DAO;


import com.example.tugas1.model.KotaModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DAOkota {

    @Select("select * from kota")
    List<KotaModel> selectAllKota();

    @Select("select * from kota where id = #{id_kota}")
    KotaModel pilihKotaById(int id_kota);
}
