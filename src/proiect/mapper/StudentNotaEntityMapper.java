package proiect.mapper;

import proiect.entity.StudentNotaEntity;

import java.sql.Array;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentNotaEntityMapper implements RowMapper<StudentNotaEntity> {
    public StudentNotaEntity mapRow(ResultSet resultSet) throws Exception{
        int idStudent = resultSet.getInt("idStudent");
        int idMaterie = resultSet.getInt("idMaterie");
        Array array = resultSet.getArray("note");
        List<Integer> note = new ArrayList<>();
        for (Object x : (Object[]) array.getArray()) {
            note.add((Integer) x);
        }
        return new StudentNotaEntity(idMaterie, idStudent, note);
    }
}
