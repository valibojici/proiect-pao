package proiect.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class StudentNota {
    Student student;
    List<Integer> note;

    public StudentNota(Student student, List<Integer> note) {
        this.student = student;
        this.note = note;
    }

    @Override
    public String toString() {
        Float medie = null;
        try{
            medie = this.getMedie();
        } catch (Exception ignored){}

        return "ID:" +
                this.student.getId() + " " +
                this.student.getNrMatricol() + " " +
                this.student.getNumePrenume() + " " +
                this.note + " (" + medie + ")";
    }

    public Student getStudent() {
        return student;
    }

    public List<Integer> getNote() {
        return this.note;
    }

    public void adaugaNota(int nota) throws Exception {
        if(nota <= 0 || nota > 10){
            throw new Exception("Nota trebuie sa fie intre 1 si 10");
        }
        this.note.add(nota);
    }

    public float getMedie() throws Exception{
        if(this.note.size() == 0){
            throw new Exception("Nu exista note pentru a face media");
        }
        int suma = this.note.stream().reduce(0, Integer::sum);
        float medie = (float)suma / this.note.size();
        return (float)Math.round(medie * 100) / 100;
    }

    public static Comparator<StudentNota> dupaNume = (sn1, sn2) -> sn1.getStudent().getNumePrenume().compareTo(sn2.getStudent().getNumePrenume());
    public static Comparator<StudentNota> dupaMedie = (sn1, sn2) -> {
        Float m1 = null;
        Float m2 = null;
        try {
            m1 = sn1.getMedie();
        }catch (Exception ignored){}
        try {
            m2 = sn2.getMedie();
        }catch (Exception ignored){}
        if(m1 != null && m2 != null) return Float.compare(m2, m1); // desc
        if(m1 == null && m2 == null) return 0; // daca ambele sunt null nu cont
        if(m1 == null) return 1; // daca primul e null sn1 e dupa sn2
        return -1;
    };
}
