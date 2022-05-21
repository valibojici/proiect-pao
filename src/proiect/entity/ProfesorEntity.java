package proiect.entity;

public class ProfesorEntity extends PersonEntity {
    private String email;

    public ProfesorEntity(int id, String nume, String prenume, String telefon, String email) {
        super(id, nume, prenume, telefon);
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ProfesorEntity{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", telefon='" + telefon + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
