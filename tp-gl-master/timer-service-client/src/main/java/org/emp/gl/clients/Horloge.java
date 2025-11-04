package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class Horloge implements TimerChangeListener {

    private final String nom;
    private final TimerService timerService;

    public Horloge(String nom, TimerService timerService) {
        this.nom = nom;
        this.timerService = timerService;

        // inscription en tant qu'observateur du TimerService
        this.timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        // réagir uniquement aux changements de secondes
        if (SECONDE_PROP.equals(prop)) {
            System.out.printf("[%s] Heure : %02d:%02d:%02d%n",
                    nom,
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
        }
    }
}
