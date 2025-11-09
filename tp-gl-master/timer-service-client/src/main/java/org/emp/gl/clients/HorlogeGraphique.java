package org.emp.gl.clients;

import java.awt.*;
import javax.swing.*;
import java.beans.PropertyChangeEvent;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private final JLabel labelHeure = new JLabel("", SwingConstants.CENTER);
    private final TimerService timerService;

    private int heures, minutes, secondes;

    public HorlogeGraphique(TimerService timerService) {
        super("Horloge Graphique");

        this.timerService = timerService;
        this.timerService.addTimeChangeListener(this);

        // Configuration de la fenêtre
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        labelHeure.setFont(new Font("Arial", Font.BOLD, 28));
        labelHeure.setText(getHeureFormatee());
        add(labelHeure, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();
        int newValue = (int) evt.getNewValue();

        switch (prop) {
            case TimerChangeListener.HEURE_PROP:
                heures = newValue;
                break;
            case TimerChangeListener.MINUTE_PROP:
                minutes = newValue;
                break;
            case TimerChangeListener.SECONDE_PROP:
                secondes = newValue;
                break;
        }

        // Mettre à jour l'affichage sur l'interface graphique
        SwingUtilities.invokeLater(() -> labelHeure.setText(getHeureFormatee()));
    }

    private String getHeureFormatee() {
        return String.format("%02d:%02d:%02d", heures, minutes, secondes);
    }
}
