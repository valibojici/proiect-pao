package proiect.service;

import proiect.entity.MaterieEntity;
import proiect.entity.StudentNotaEntity;
import proiect.model.Materie;
import proiect.model.Profesor;
import proiect.model.Student;
import proiect.model.StudentNota;
import proiect.repository.MaterieRepository;
import proiect.repository.StudentNotaRepository;

import java.util.ArrayList;
import java.util.List;

public class MaterieService {
    public static List<Materie> getMaterii(){
        List<Materie> materii = new ArrayList<>();
        for(MaterieEntity m : MaterieRepository.findAll()) {
            List<StudentNota> studNote = new ArrayList<>();
            try {
                studNote = StudentNotaService.getStudentNote(m.getId());
            } catch (Exception ignored) {}

            Profesor prof = null;
            try {
                prof = ProfesorService.getProfesor(m.getIdProfesor());
            } catch (Exception ignored) {}
            Materie materie = new Materie(m.getId(), m.getDenumire(), studNote, prof);
            materii.add(materie);
        }
        return materii;
    }

    public static Materie getMaterie(int id) {
        MaterieEntity m = MaterieRepository.find(id);
        if(m == null) return null;
        return new Materie(
                m.getId(),
                m.getDenumire(),
                StudentNotaService.getStudentNote(m.getId()),
                ProfesorService.getProfesor(m.getIdProfesor())
        );
    }

    public static void adaugaMaterie(Materie materie){
        Profesor p = materie.getProfesor();
        if(p == null){
            MaterieRepository.insert(new MaterieEntity(materie.getId(), null, materie.getDenumire()));
            return;
        }
        MaterieEntity m = new MaterieEntity(materie.getId(), p.getId(), materie.getDenumire());

    }

    public static void adaugaStudentMaterie(int idMaterie, Student student){
        StudentNotaEntity sn = new StudentNotaEntity(idMaterie, student.getId(), new ArrayList<>());
        StudentNotaRepository.insert(sn);
    }

    public static void eliminaStudentMaterie(int idMaterie, int idStudent) {
        StudentNotaRepository.delete(idMaterie, idStudent);
    }

    public static void eliminaProfesorMaterie(int idMaterie) {
        MaterieRepository.updateProfesor(idMaterie,null);
    }

    public static void stergeMaterie(int idMaterie){
        MaterieRepository.delete(idMaterie);
    }

    public static void modificaProfesor(Materie materie, Profesor p){
        MaterieRepository.updateProfesor(materie.getId(),p == null ? null : p.getId());
    }
}
