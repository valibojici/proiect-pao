package proiect.entity;

public class StudentEntity extends PersonEntity {
    private String nrMatricol;

    public StudentEntity(int id, String nume, String prenume, String telefon, String nrMatricol) {
        super(id, nume, prenume, telefon);
        this.nrMatricol = nrMatricol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(String nrMatricol) {
        this.nrMatricol = nrMatricol;
    }
}
