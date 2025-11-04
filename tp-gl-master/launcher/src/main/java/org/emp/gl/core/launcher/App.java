package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.CompteARebours;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

import java.util.Random;

public class App {

    public static void main(String[] args) {
        // 1️⃣ Création du TimerService
        TimerService timerService = new DummyTimeServiceImpl();

        // 2️⃣ Création de plusieurs horloges observant le même service
        new Horloge("Horloge 1", timerService);
        new Horloge("Horloge 2", timerService);
        new Horloge("Horloge 3", timerService);

        // 3️⃣ Création d'un compte à rebours de 5 secondes
        new CompteARebours("Compteur 5 sec", 5, timerService);

        // 4️⃣ Création de 10 compteurs aléatoires entre 10 et 20
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            int valeurInit = 10 + random.nextInt(11); // valeurs entre 10 et 20
            new CompteARebours("Compteur " + i, valeurInit, timerService);
        }

        // 5️⃣ Le DummyTimeService s'occupe de notifier les observateurs automatiquement
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
