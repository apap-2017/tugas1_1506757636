package com.example.tugas1.DAO;

import com.example.tugas1.model.KeluargaModel;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DAOkeluarga {
    @Select("select * from keluarga where id = #{id_keluarga}")
    KeluargaModel pilihKeluargaById(int id_keluarga);

    @Select("select * from keluarga where nomor_kk = #{nkk}")
    KeluargaModel pilihKeluarga (String nkk);

    @Insert("insert into keluarga (nomor_kk, alamat, RT, RW, id_kelurahan, is_tidak_berlaku) "
            + "values ('${nomor_kk}', '${alamat}', '${rt}', '${rw}', '${id_kelurahan}', '0')")
    void tambahKeluarga(KeluargaModel keluarga);

    @Update("update keluarga SET is_tidak_berlaku = '1' WHERE id = #{id}")
    void updateStatusBerlaku (@Param("id") int id);

    @Update("update keluarga SET nomor_kk = '${keluarga.nomor_kk}', alamat ='${keluarga.alamat}', RT = '${keluarga.rt}', "
            + "RW = '${keluarga.rw}', id_kelurahan = '${keluarga.id_kelurahan}', is_tidak_berlaku = '0' "
            + "WHERE id = #{id}")
    void ubahKeluarga(@Param("keluarga") KeluargaModel keluarga, @Param("id") int id);
}
