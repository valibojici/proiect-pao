package proiect.service;

import proiect.entity.StudentEntity;
import proiect.model.Profesor;
import proiect.model.Student;
import proiect.repository.ProfesorRepository;
import proiect.repository.StudentRepository;

import java.util.List;

public class StudentService {
    public static Student getStudent(int id) {
        StudentEntity s = StudentRepository.find(id);
        if(s == null) return null;
        return new Student(s.getId(), s.getNume(), s.getPrenume(), s.getTelefon(), s.getNrMatricol());
    }

    public static void addStudent(Student student){
        StudentEntity s = new StudentEntity(student.getId(), student.getNume(), student.getPrenume(), student.getTelefon(), student.getNrMatricol());
        StudentRepository.insert(s);
    }

    public static List<Student> getAll() {
        return StudentRepository
                .getAll()
                .stream()
                .map(x -> new Student(x.getId(), x.getNume(), x.getPrenume(), x.getTelefon(), x.getNrMatricol()))
                .toList();
    }

    public static void delete(Student student) {
        if(student == null){
            return;
        }
        StudentRepository.delete(student.getId());
    }
}
