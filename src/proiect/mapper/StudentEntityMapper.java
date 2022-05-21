package proiect.mapper;

import proiect.entity.StudentEntity;

import java.sql.ResultSet;

public class StudentEntityMapper implements RowMapper<StudentEntity> {
    public StudentEntity mapRow(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String telefon = resultSet.getString("telefon");
        String nrMatricol = resultSet.getString("nrMatricol");
        return new StudentEntity(id, nume, prenume, telefon, nrMatricol);
    }
}
