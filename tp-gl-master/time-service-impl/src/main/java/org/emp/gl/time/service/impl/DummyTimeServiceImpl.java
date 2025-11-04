package org.emp.gl.time.service.impl;

import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeEvent;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class DummyTimeServiceImpl implements TimerService {

    private int dixiemeDeSeconde;
    private int minutes;
    private int secondes;
    private int heures;

    private PropertyChangeSupport support = new PropertyChangeSupport(this);

    public DummyTimeServiceImpl() {
        setTimeValues();

        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();
        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100_000_000);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }

    private void notifyChange(String propName, int oldValue, int newValue) {
        support.firePropertyChange(propName, oldValue, newValue);
    }

    public void setDixiemeDeSeconde(int newDixiemeDeSeconde) {
        if (dixiemeDeSeconde == newDixiemeDeSeconde) return;
        int oldValue = dixiemeDeSeconde;
        dixiemeDeSeconde = newDixiemeDeSeconde;
        notifyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldValue, dixiemeDeSeconde);
    }

    public void setSecondes(int newSecondes) {
        if (secondes == newSecondes) return;
        int oldValue = secondes;
        secondes = newSecondes;
        notifyChange(TimerChangeListener.SECONDE_PROP, oldValue, secondes);
    }

    public void setMinutes(int newMinutes) {
        if (minutes == newMinutes) return;
        int oldValue = minutes;
        minutes = newMinutes;
        notifyChange(TimerChangeListener.MINUTE_PROP, oldValue, minutes);
    }

    public void setHeures(int newHeures) {
        if (heures == newHeures) return;
        int oldValue = heures;
        heures = newHeures;
        notifyChange(TimerChangeListener.HEURE_PROP, oldValue, heures);
    }

    private void timeChanged() {
        setTimeValues();
    }

    @Override
    public int getDixiemeDeSeconde() { return dixiemeDeSeconde; }

    @Override
    public int getHeures() { return heures; }

    @Override
    public int getMinutes() { return minutes; }

    @Override
    public int getSecondes() { return secondes; }
}
