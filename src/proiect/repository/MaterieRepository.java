package proiect.repository;

import proiect.entity.MaterieEntity;
import proiect.mapper.MaterieEntityMapper;

import java.util.List;

public class MaterieRepository extends Repository{
    public static List<MaterieEntity> findAll(){
        return readQuery("select * from materie", new MaterieEntityMapper());
    }

    public static MaterieEntity find(int id){
        try {
            return readQuery("select * from materie where id="+id, new MaterieEntityMapper()).get(0);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public static void insert(MaterieEntity materie) {
        executeQuery("insert into materie(idProfesor,denumire) values (" +
                materie.getIdProfesor() + ", " +
                addQuotMark(materie.getDenumire())+ ")") ;
    }

    public static void updateProfesor(int idMaterie, Integer idProf) {
        if(idProf == null){
            executeQuery("update materie set idprofesor = null where id=" + idMaterie);
        } else {
            executeQuery("update materie set idprofesor =" + idProf + " where id=" + idMaterie);
        }
    }

    public static void delete(int idMaterie) {
        executeQuery("delete from materie where id="+idMaterie);
    }
}
