package proiect.entity;

public class MaterieEntity {
    private int id;
    private Integer idProfesor;
    private String denumire;

    public MaterieEntity(int id, Integer idProfesor, String denumire) {
        this.id = id;
        this.idProfesor = idProfesor;
        this.denumire = denumire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Integer idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }
}
