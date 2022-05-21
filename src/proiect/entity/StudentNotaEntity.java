package proiect.entity;

import java.util.List;

public class StudentNotaEntity {
    private int idMaterie;
    private int idStudent;
    private List<Integer> note;

    public StudentNotaEntity(int idMaterie, int idStudent, List<Integer> note) {
        this.idMaterie = idMaterie;
        this.idStudent = idStudent;
        this.note = note;
    }

    public int getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(int idMaterie) {
        this.idMaterie = idMaterie;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public List<Integer> getNote() {
        return note;
    }

    public void setNote(List<Integer> note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StudentNotaEntity{" +
                "idMaterie=" + idMaterie +
                ", idStudent=" + idStudent +
                ", note=" + note +
                '}';
    }
}
