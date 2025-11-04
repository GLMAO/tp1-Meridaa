package org.emp.gl.clients;

import java.beans.PropertyChangeEvent;
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
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        int newValue = (int) evt.getNewValue();

        if(prop.equals(TimerChangeListener.SECONDE_PROP)) {
            // mettre à jour l'affichage de la seconde ou décrémenter le compteur
        }
    }
}
