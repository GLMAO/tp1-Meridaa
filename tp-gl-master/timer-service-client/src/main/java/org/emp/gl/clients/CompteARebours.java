package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import java.util.Random;

public class CompteARebours implements TimerChangeListener {

    private final TimerService timerService;
    private int compteur;
    private final String nom;

    public CompteARebours(String nom, int compteur, TimerService timerService) {
        this.nom = nom;
        this.compteur = compteur;
        this.timerService = timerService;

        // s'inscrire comme observateur
        this.timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
        if (TimerChangeListener.SECONDE_PROP.equals(prop)) {
            if (compteur > 0) {
                System.out.printf("[%s] Compte à rebours : %d%n", nom, compteur);
                compteur--;
            }
            if (compteur == 0) {
                // se désinscrire automatiquement
                timerService.removeTimeChangeListener(this);
                System.out.printf("[%s] Terminé !\n", nom);
            }
        }
    }
}
