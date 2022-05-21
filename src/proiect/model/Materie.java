package proiect.model;

import java.util.ArrayList;
import java.util.List;

public class Materie implements Comparable<Materie> {
    private int id;
    private String denumire;
    private List<StudentNota> studentiNote;
    private Profesor profesor;

    public Materie(int id, String denumire, List<StudentNota> studentiNote, Profesor profesor) {
        this.id = id;
        this.denumire = denumire;
        this.studentiNote = studentiNote;
        this.profesor = profesor;
    }

    public Materie() {
        this.studentiNote = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDenumire() {
        return this.denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public List<StudentNota> getStudentiNote() {
        return this.studentiNote;
    }

    public void setStudentiNote(List<StudentNota> studentiNote) {
        this.studentiNote = studentiNote;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Profesor getProfesor() {
        return this.profesor;
    }

    @Override
    public String toString() {
        String profesor = "";
        if(this.profesor != null){
            profesor = "ID:" + this.profesor.getId() + " " + this.profesor.getNumePrenume();
        }
        return "Denumire: " + this.denumire + "\n" + "Profesor: " + profesor + "\nStudenti:" + this.getStudenti();
    }

    private String getStudenti() {
        StringBuilder s = new StringBuilder();
        for (StudentNota studNota : this.studentiNote) {
            s.append('\n');
            s.append(studNota.toString());
        }
        return s.toString();
    }

    @Override
    public int compareTo(Materie o) {
        return Integer.compare(this.id, o.id);
    }
}
