package proiect.repository;

import proiect.entity.ProfesorEntity;
import proiect.mapper.ProfesorEntityMapper;
import proiect.service.DBQueryExecutorService;

import java.util.List;

public class ProfesorRepository extends Repository{
    public static List<ProfesorEntity> findAll(){
        return readQuery("select * from persoana join profesor using(id)", new ProfesorEntityMapper());
    }

    public static ProfesorEntity find(int id){
        try {
            return readQuery("select * from persoana join profesor using(id) where profesor.id=" + id, new ProfesorEntityMapper()).get(0);
        } catch (ArrayIndexOutOfBoundsException e){
            return null;
        }
    }

    public static void insert(ProfesorEntity prof) {
        String sql = "insert into persoana(nume,prenume,telefon) values(" +
                addQuotMark(prof.getNume()) +
                ", " + addQuotMark(prof.getPrenume()) +
                "," + addQuotMark(prof.getTelefon()) + ")";
        int id = executeQuery(sql);
        sql = "insert into profesor values(" + id + ", " + addQuotMark(prof.getEmail()) + ")";
        executeQuery(sql);
    }

    public static List<ProfesorEntity> getAll() {
        String sql = "select * from persoana join profesor using(id)";
        return readQuery(sql, new ProfesorEntityMapper());
    }
}
