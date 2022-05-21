package proiect.mapper;

import proiect.entity.MaterieEntity;

import java.sql.ResultSet;

public class MaterieEntityMapper implements RowMapper<MaterieEntity> {
    public MaterieEntity mapRow(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String denumire = resultSet.getString("denumire");
        int idProfesor = resultSet.getInt("idProfesor");
        return new MaterieEntity(id, idProfesor, denumire);
    }
}
