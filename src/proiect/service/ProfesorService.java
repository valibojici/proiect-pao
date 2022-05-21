package proiect.service;

import proiect.entity.ProfesorEntity;
import proiect.model.Profesor;
import proiect.repository.ProfesorRepository;

import java.util.List;

public class ProfesorService {
    public static Profesor getProfesor(int id) {
        ProfesorEntity p = ProfesorRepository.find(id);
        if(p == null)return null;
        return new Profesor(p.getId(), p.getNume(), p.getPrenume(), p.getTelefon(), p.getEmail());
    }

    public static void addProfesor(Profesor profesor){
        ProfesorEntity prof = new ProfesorEntity(-1, profesor.getNume(), profesor.getPrenume(), profesor.getTelefon(), profesor.getEmail());
        ProfesorRepository.insert(prof);
    }

    public static List<Profesor> getAll() {
        return ProfesorRepository
                .getAll()
                .stream()
                .map(x -> new Profesor(x.getId(), x.getNume(), x.getPrenume(), x.getTelefon(), x.getEmail()))
                .toList();
    }
}
