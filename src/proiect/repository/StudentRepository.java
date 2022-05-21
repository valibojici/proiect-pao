package proiect.repository;
import proiect.entity.StudentEntity;
import proiect.mapper.ProfesorEntityMapper;
import proiect.mapper.StudentEntityMapper;
import proiect.model.Student;

import java.util.Arrays;
import java.util.List;

public class StudentRepository extends Repository{
    public static List<StudentEntity> findAll(){
        return readQuery("select * from persoana join student using(id)", new StudentEntityMapper());
    }

    public static StudentEntity find(int id){
        try {
            return readQuery("select * from persoana join student using(id) where student.id="+id, new StudentEntityMapper()).get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void insert(StudentEntity student) {
        String sql = "insert into persoana(nume,prenume,telefon) values(" +
                addQuotMark(student.getNume()) +
                ", " + addQuotMark(student.getPrenume()) +
                "," + addQuotMark(student.getTelefon()) + ")";
        int id = executeQuery(sql);
        sql = "insert into student values(" + id + ", " + addQuotMark(student.getNrMatricol()) + ")";
        executeQuery(sql);
    }

    public static List<StudentEntity> getAll() {
        String sql = "select * from persoana join student using(id)";
        return readQuery(sql, new StudentEntityMapper());
    }

    public static void delete(int id) {
        String sql = "delete from persoana where id="+id;
        executeQuery(sql);
    }
}
