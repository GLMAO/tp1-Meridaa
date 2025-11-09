package org.emp.gl.clients;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

import javax.swing.*;

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

    public static class HorlogeGUI extends JFrame {

        private final JLabel labelHeure;
        private final TimerService timerService;

        public HorlogeGUI(TimerService timerService) {
            super("Horloge Graphique");
            this.timerService = timerService;

            // Configurer la fenêtre
            setSize(300, 100);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            labelHeure = new JLabel("", SwingConstants.CENTER);
            labelHeure.setFont(new Font("Arial", Font.BOLD, 30));
            add(labelHeure, BorderLayout.CENTER);

            // Inscription comme observateur
            timerService.addTimeChangeListener(evt -> updateHeure(evt));

            setVisible(true);
        }

        private void updateHeure(PropertyChangeEvent evt) {
            // On ne s'intéresse qu'aux secondes pour rafraîchir l'affichage
            if(evt.getPropertyName().equals("SECONDE_PROP")) {
                int h = timerService.getHeures();
                int m = timerService.getMinutes();
                int s = timerService.getSecondes();
                // Mettre à jour le JLabel sur le thread EDT
                SwingUtilities.invokeLater(() -> labelHeure.setText(String.format("%02d:%02d:%02d", h, m, s)));
            }
        }
    }
}
