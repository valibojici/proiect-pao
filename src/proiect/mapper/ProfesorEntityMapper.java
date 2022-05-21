package proiect.mapper;

import proiect.entity.ProfesorEntity;
import proiect.entity.StudentEntity;

import java.sql.ResultSet;

public class ProfesorEntityMapper implements RowMapper<ProfesorEntity> {
    public ProfesorEntity mapRow(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String telefon = resultSet.getString("telefon");
        String email = resultSet.getString("email");
        return new ProfesorEntity(id, nume, prenume, telefon, email);
    }
}
