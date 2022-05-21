package proiect.repository;

import proiect.entity.PersonEntity;
import proiect.mapper.PersonEntityMapper;

public class PersoanaRepository extends Repository {
    public static void delete(int id){
        executeQuery("delete from persoana where id=" + id);
    }

    public static PersonEntity find(int id){
        try {
            return readQuery("select * from persoana where id=" + id, new PersonEntityMapper()).get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
