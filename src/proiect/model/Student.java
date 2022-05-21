package proiect.model;

public class Student extends Persoana {
    private String nrMatricol;

    public Student(int id, String nume, String prenume, String telefon, String nrMatricol) {
        super(id, nume, prenume, telefon);
        this.nrMatricol = nrMatricol;
    }

    public Student() {
    }

    public String getNrMatricol() {
        return nrMatricol;
    }

    public void setNrMatricol(String nrMatricol) throws Exception {
        if (!nrMatricol.matches("^\\d+/\\d{4}$")) {
            throw new Exception("Nr. matricol invalid");
        }
        this.nrMatricol = nrMatricol;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.nrMatricol;
    }
}
