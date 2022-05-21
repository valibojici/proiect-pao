package proiect.service;

import proiect.entity.StudentNotaEntity;
import proiect.model.Materie;
import proiect.model.Student;
import proiect.model.StudentNota;
import proiect.repository.StudentNotaRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentNotaService {
    public static List<StudentNota> getStudentNote(int idMaterie) {
        List<StudentNota> list = new ArrayList<>();
        for (StudentNotaEntity x : StudentNotaRepository.findAllByMaterie(idMaterie)) {
            StudentNota studentNota = new StudentNota(StudentService.getStudent(x.getIdStudent()), x.getNote());
            list.add(studentNota);
        }
        return list;
    }

    public static StudentNota getStudentNota(int idMaterie, int idStudent) {
        StudentNotaEntity sn = StudentNotaRepository.find(idMaterie, idStudent);
        if(sn == null){
            return null;
        }
        return new StudentNota(StudentService.getStudent(sn.getIdStudent()), sn.getNote());
    }

    public static void updateStudentNota(int idMaterie, int idStudent, StudentNota sn){
        StudentNotaEntity s = new StudentNotaEntity(idMaterie, sn.getStudent().getId(), sn.getNote());
        StudentNotaRepository.update(idMaterie, idStudent, s);
    }
}
