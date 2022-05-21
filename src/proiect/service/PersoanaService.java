package proiect.service;

import proiect.entity.PersonEntity;
import proiect.model.Persoana;
import proiect.repository.PersoanaRepository;

public class PersoanaService {
    public static void delete(int id){
        PersoanaRepository.delete(id);
    }

    public static Persoana getPersoana(int id){
        PersonEntity pers = PersoanaRepository.find(id);
        if (pers == null) {
            return null;
        }
        return new Persoana(pers.getId(), pers.getNume(), pers.getPrenume(), pers.getTelefon());
    }
}
