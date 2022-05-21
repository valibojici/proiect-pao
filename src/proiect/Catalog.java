package proiect;

import proiect.model.*;
import proiect.repository.PersoanaRepository;
import proiect.service.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class Catalog {
    private final Meniu meniuPrincipal;

    public Catalog() {
        this.meniuPrincipal = new Meniu(Arrays.asList(
                "Adauga persoana",
                "Sterge persoana",
                "Afis persoane",
                "Adauga materie",
                "Sterge materie",
                "Afis materie",
                "Afis materii",
                "Modifica profesor materie",
                "Adauga student materie",
                "Elimina student materie",
                "Adauga nota student",
                "Sterge nota student",
                "Modifica nota student",
                "Exit"
        ));
    }

    public void meniu() {
        String opt = this.meniuPrincipal.show();
        while (!(opt.equals("exit"))) {
            this.logAction(opt);
            switch (opt) {
                case "adauga_persoana" -> this.adaugaPersoana();
                case "sterge_persoana" -> this.stergePersoana();
                case "afis_persoane" -> this.afisPersoane();
                case "adauga_materie" -> this.adaugaMaterie();
                case "sterge_materie" -> this.stergeMaterie();
                case "afis_materie" -> this.afisMaterie();
                case "afis_materii" -> this.afisMaterii();
                case "modifica_profesor_materie" -> this.modificaProfesorMaterie();
                case "adauga_student_materie" -> this.adaugaStudentMaterie();
                case "elimina_student_materie" -> this.eliminaStudentMaterie();
                case "adauga_nota_student" -> this.adaugaNotaStudent();
                case "sterge_nota_student" -> this.stergeNotaStudent();
                case "modifica_nota_student" -> this.modificaNotaStudent();
            }
            opt = this.meniuPrincipal.show();
        }
    }

    private void afisPersoane() {
        Meniu meniuPersoane = new Meniu(Arrays.asList(
                "Afis profesori",
                "Afis studenti"
        ));
        String opt = meniuPersoane.show();
        boolean exista = false;

        System.out.println("----------------");
        if (opt.equals("afis_profesori")) {
            for (Profesor p : ProfesorService.getAll()) {
                System.out.println(p);
                exista = true;
            }
        } else {
            for (Student s : StudentService.getAll()) {
                System.out.println(s);
                exista = true;
            }
        }

        if (!exista) {
            System.out.println("Nu exista");
        }
        System.out.println("----------------");
    }

    private void citirePersoana(Persoana persoana, Scanner scanner) throws Exception {
        System.out.print("Nume: ");
        persoana.setNume(scanner.nextLine());
        System.out.print("Prenume: ");
        persoana.setPrenume(scanner.nextLine());
        System.out.print("Telefon: ");
        persoana.setTelefon(scanner.nextLine());
    }

    private void adaugaPersoana() {
        Meniu meniuPersoana = new Meniu(Arrays.asList(
                "Adauga profesor",
                "Adauga student"
        ));
        String opt = meniuPersoana.show();
        if (opt.equals("adauga_profesor")) {
            this.adaugaProfesor();
        } else {
            this.adaugaStudent();
        }
    }

    private void adaugaProfesor() {
        Profesor profesor = new Profesor();
        Scanner scanner = new Scanner(System.in);
        try {
            citirePersoana(profesor, scanner);
            System.out.print("Email: ");
            profesor.setEmail(scanner.nextLine());
            ProfesorService.addProfesor(profesor);
            System.out.println("\nOK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugaStudent() {
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);
        try {
            citirePersoana(student, scanner);
            System.out.print("Nr. matricol: ");
            student.setNrMatricol(scanner.nextLine());
            StudentService.addStudent(student);
            System.out.println("\nOK");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugaMaterie() {
        Scanner scanner = new Scanner(System.in);
        Profesor profesor = null;
        Materie materie = new Materie();
        try {
            System.out.print("Denumire: ");
            materie.setDenumire(scanner.nextLine());

            System.out.print("ID profesor (-1 daca nu exista profesor): ");

            int profesorId = scanner.nextInt();
            if (profesorId != -1) {
                profesor = ProfesorService.getProfesor(profesorId);
            }
            if (profesor == null) {
                throw new Exception("Profesorul nu exista");
            }
            materie.setProfesor(profesor);
            MaterieService.adaugaMaterie(materie);
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void afisMaterie() {
        Meniu meniuMaterie = new Meniu(Arrays.asList(
                "Sortare studenti dupa nume",
                "Sortare studenti dupa medie"
        ));

        String alegere = meniuMaterie.show();
        System.out.println("---------");
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            if (alegere.equals("sortare_studenti_dupa_nume")) {
                materie.getStudentiNote().sort(StudentNota.dupaNume);
            } else if (alegere.equals("sortare_studenti_dupa_medie")) {
                materie.getStudentiNote().sort(StudentNota.dupaMedie);
            }
            System.out.println(materie);
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("---------");
    }

    private void afisMaterii() {
        System.out.println("---------");
        for (Materie m : MaterieService.getMaterii()) {
            String profesor = m.getProfesor() == null ? "nu exista" : m.getProfesor().getNumePrenume();
            System.out.println(m.getId() + ". Denumire: " + m.getDenumire() + " | Profesor: " + profesor);
        }
        System.out.println("---------");
    }


    private void modificaProfesorMaterie() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            int idMaterie = scanner.nextInt();
            Materie materie = MaterieService.getMaterie(idMaterie);
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            System.out.print("ID profesor nou: (-1 daca nu exista profesor): ");
            int idProfNou = scanner.nextInt();
            if (idProfNou == -1) {
                MaterieService.modificaProfesor(materie, null);
            } else {
                Profesor prof = ProfesorService.getProfesor(idProfNou);
                if (prof == null) {
                    throw new Exception("Profesorul nu exista");
                }
                materie.setProfesor(prof);
                MaterieService.modificaProfesor(materie, prof);
            }
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugaStudentMaterie() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            System.out.print("ID student: ");
            Student student = StudentService.getStudent(scanner.nextInt());
            if (student == null) {
                throw new Exception("Studentul nu exista");
            }

            StudentNota studentNota = StudentNotaService.getStudentNota(materie.getId(), student.getId());
            if (studentNota != null) {
                throw new Exception("Studentul este deja inregistrat");
            }

            MaterieService.adaugaStudentMaterie(materie.getId(), student);
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void stergePersoana() {
        try {
            System.out.print("ID persoana: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            Persoana p = PersoanaService.getPersoana(id);
            if (p == null) {
                throw new Exception("Persoana nu exista");
            }
            PersoanaService.delete(id);
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void stergeMaterie() {
        try {
            System.out.print("ID materie: ");
            Scanner scanner = new Scanner(System.in);
            int id = scanner.nextInt();
            Materie m = MaterieService.getMaterie(id);
            if (m == null) {
                throw new Exception("Materia nu exista");
            }
            MaterieService.stergeMaterie(id);
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("ID invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void stergeNotaStudent() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            System.out.print("ID student: ");
            Student student = StudentService.getStudent(scanner.nextInt());
            if (student == null) {
                throw new Exception("Studentul nu exista");
            }
            StudentNota studentNota = StudentNotaService.getStudentNota(materie.getId(), student.getId());
            if (studentNota == null) {
                throw new Exception("Studentul nu este inregistrat la materie");
            }

            if (studentNota.getNote().size() == 0) {
                throw new Exception("Studentul nu are note");
            }

            System.out.println("Studentul " + student.getNumePrenume() + " are notele " + studentNota.getNote());
            System.out.println("Index nota de sters (indexat de la 1)");
            int index = scanner.nextInt();
            studentNota.getNote().remove(index - 1);
            StudentNotaService.updateStudentNota(materie.getId(), student.getId(), studentNota);
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("Input invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void adaugaNotaStudent() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            System.out.print("ID student: ");
            Student student = StudentService.getStudent(scanner.nextInt());
            if (student == null) {
                throw new Exception("Studentul nu exista");
            }

            StudentNota studentNota = StudentNotaService.getStudentNota(materie.getId(), student.getId());
            if (studentNota == null) {
                throw new Exception("Studentul nu este inregistrat la materie");
            }

            System.out.println("Studentul " + student.getNumePrenume() + " are notele " + studentNota.getNote());
            System.out.println("Nota de adaugat: ");
            int nota = scanner.nextInt();
            if (nota <= 0 || nota > 10) {
                throw new Exception("Nota nu este intre 1 si 10");
            }
            studentNota.getNote().add(nota);
            StudentNotaService.updateStudentNota(materie.getId(), student.getId(), studentNota);

            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("Input invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void modificaNotaStudent() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }
            System.out.print("ID student: ");
            Student student = StudentService.getStudent(scanner.nextInt());
            if (student == null) {
                throw new Exception("Studentul nu exista");
            }
            StudentNota studentNota = StudentNotaService.getStudentNota(materie.getId(), student.getId());
            if (studentNota == null) {
                throw new Exception("Studentul nu este inregistrat la materie");
            }

            if (studentNota.getNote().size() == 0) {
                throw new Exception("Studentul nu are note");
            }

            System.out.println("Studentul " + student.getNumePrenume() + " are notele " + studentNota.getNote());

            System.out.println("Index nota de modificat (indexat de la 1)");
            int idxNota = scanner.nextInt() - 1;
            System.out.println("Nota actuala: " + studentNota.getNote().get(idxNota));
            System.out.print("Nota noua: ");
            int nota = scanner.nextInt();
            if (nota <= 0 || nota > 10) {
                throw new Exception("Nota nu este intre 1 si 10");
            }
            studentNota.getNote().set(idxNota, nota);
            StudentNotaService.updateStudentNota(materie.getId(), student.getId(), studentNota);

            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("Input invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminaStudentMaterie() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("ID materie: ");
            Materie materie = MaterieService.getMaterie(scanner.nextInt());
            if (materie == null) {
                throw new Exception("Materia nu exista");
            }

            System.out.print("ID student: ");
            Student student = StudentService.getStudent(scanner.nextInt());
            if (student == null) {
                throw new Exception("Studentul nu exista");
            }

            StudentNota studentNota = StudentNotaService.getStudentNota(materie.getId(), student.getId());
            if (studentNota == null) {
                throw new Exception("Studentul nu este inregistrat la materie");
            }

            MaterieService.eliminaStudentMaterie(materie.getId(), student.getId());
            System.out.println("\nOK");
        } catch (InputMismatchException e) {
            System.out.println("Input invalid");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void logAction(String action) {
        String timp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
        CsvWriter f = CsvWriter.getInstance();
        try {
            f.appendCsvFile(new String[]{action, timp}, "audit/audit.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
