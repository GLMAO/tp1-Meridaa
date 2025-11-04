package org.emp.gl.core.launcher;

import org.emp.gl.clients.Horloge;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;

public class App {

    public static void main(String[] args) {
        // 1️⃣ Création du TimerService (le sujet)
        TimerService timerService = new DummyTimeServiceImpl();

        // 2️⃣ Création de plusieurs horloges observant le même service
        new Horloge("Horloge 1", timerService);
        new Horloge("Horloge 2", timerService);
        new Horloge("Horloge 3", timerService);

        // Le DummyTimerService s'occupe de notifier les observateurs automatiquement
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
