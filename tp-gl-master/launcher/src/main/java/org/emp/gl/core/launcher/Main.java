package org.emp.gl;

import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.clients.HorlogeGraphique;

public class Main {
    public static void main(String[] args) {
        TimerService timerService = new DummyTimeServiceImpl();
        new HorlogeGraphique(timerService);
    }
}
