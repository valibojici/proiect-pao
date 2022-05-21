package proiect.model;

public class Profesor extends Persoana {
    private String email;

    public Profesor(int id, String nume, String prenume, String telefon, String email) {
        super(id, nume, prenume, telefon);
        this.email = email;
    }

    public Profesor() {
    }

    public void setEmail(String email) throws Exception {
        if (!email.matches("^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[A-Za-z]{3}$")) {
            throw new Exception("Email invalid");
        }
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return super.toString() + " " + this.email;
    }
}
