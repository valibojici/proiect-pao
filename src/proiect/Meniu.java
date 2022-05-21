package proiect;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Meniu {
    private List<String> optiuni;

    public Meniu(List<String> optiuni) {
        this.optiuni = optiuni;
    }

    public String show() {
        System.out.println();
        // afis optiuni cu indecsi
        for (int i = 0; i < this.optiuni.size(); ++i) {
            System.out.println((i + 1) + ") " + this.optiuni.get(i));
        }
        int index = -1;
        Scanner scanner = new Scanner(System.in);

        // astept input
        while (index == -1) {
            try {
                System.out.print("Introdu alegere: ");
                index = scanner.nextInt();
                if (index < 1 || index > this.optiuni.size()) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("Index invalid");
                scanner.nextLine();
                index = -1;
            }

        }
        // de ex. din Afis materie in afis_materie
        return this.optiuni.get(index - 1).toLowerCase().replace(' ', '_');
    }
}
