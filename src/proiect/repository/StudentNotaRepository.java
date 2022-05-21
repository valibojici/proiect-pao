package proiect.repository;

import proiect.entity.StudentNotaEntity;
import proiect.mapper.StudentNotaEntityMapper;

import java.util.List;

public class StudentNotaRepository extends Repository{
    public static List<StudentNotaEntity> findAll(){
        return readQuery("select * from student_nota", new StudentNotaEntityMapper());
    }

    public static List<StudentNotaEntity> findAllByMaterie(int idMaterie){
        return readQuery("select * from student_nota where idMaterie="+idMaterie, new StudentNotaEntityMapper());
    }

    public static void insert(StudentNotaEntity sn) {
        String note = String.join(",", sn.getNote().stream().map(String::valueOf).toList());
        String sql = "insert into student_nota values(" + sn.getIdStudent() + ", " + sn.getIdMaterie() + ", array[" + note+"])";
        executeQuery(sql);
    }

    public static void delete(int idMaterie, int idStudent) {
        String sql = "delete from student_nota where idStudent=" + idStudent + " and idMaterie=" + idMaterie;
        executeQuery(sql);
    }

    public static StudentNotaEntity find(int idMaterie, int idStudent) {
        String sql = "select * from student_nota where idMaterie="+idMaterie+" and idStudent="+idStudent;
        try {
            return readQuery(sql, new StudentNotaEntityMapper()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public static void update(int idMaterie, int idStudent, StudentNotaEntity sn) {
        String note = String.join(",", sn.getNote().stream().map(String::valueOf).toList());
        String sql = "update student_nota set" +
                " idMaterie=" + sn.getIdMaterie() +
                ", idStudent=" + sn.getIdStudent() +
                ", note=array[" + note + "] where idMaterie=" + idMaterie + " and idStudent=" + idStudent;
        executeQuery(sql);
    }
}
