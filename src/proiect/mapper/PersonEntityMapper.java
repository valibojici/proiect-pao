package proiect.mapper;

import proiect.entity.PersonEntity;

import java.sql.ResultSet;

public class PersonEntityMapper implements RowMapper<PersonEntity> {
    public PersonEntity mapRow(ResultSet resultSet) throws Exception{
        int id = resultSet.getInt("id");
        String nume = resultSet.getString("nume");
        String prenume = resultSet.getString("prenume");
        String telefon = resultSet.getString("telefon");

        return new PersonEntity(id, nume, prenume, telefon);
    }
}
