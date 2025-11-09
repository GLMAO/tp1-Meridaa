package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class CompteARebours implements TimerChangeListener {

    private final String nom;
    private int valeur;
    private final TimerService timerService;

    public CompteARebours(String nom, int valeur, TimerService timerService) {
        this.nom = nom;
        this.valeur = valeur;
        this.timerService = timerService;

        // inscription comme observateur
        this.timerService.addTimeChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();

        if (prop.equals(TimerChangeListener.SECONDE_PROP)) {
            valeur--;
            if (valeur >= 0) {
                System.out.println("[" + nom + "] Compte à rebours : " + valeur);
            }
            if (valeur == 0) {
                System.out.println("[" + nom + "] Terminé !");
                timerService.removeTimeChangeListener(this); // se désinscrire
            }
        }
    }
}