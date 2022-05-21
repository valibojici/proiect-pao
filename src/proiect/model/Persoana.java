package proiect.model;

public class Persoana implements Comparable<Persoana> {
    protected int id;
    protected String nume;
    protected String prenume;
    protected String telefon;

    public Persoana(int id, String nume, String prenume, String telefon) {
        this.id = id;
        this.nume = nume;
        this.prenume = prenume;
        this.telefon = telefon;
    }

    public Persoana() {

    }

    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getNumePrenume() {
        return this.nume + " " + this.prenume;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setTelefon(String telefon) throws Exception {
        if (!telefon.matches("^\\d{10}$")) {
            throw new Exception("Telefon invalid");
        }
        this.telefon = telefon;
    }

    @Override
    public String toString() {
        return id + ". " + this.nume + " " + this.prenume + " " + this.telefon;
    }

    @Override
    public int compareTo(Persoana o) {
        return Integer.compare(this.id, o.id);
    }
}
