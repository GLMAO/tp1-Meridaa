package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        TimerService timerService = new DummyTimeServiceImpl();
        SwingUtilities.invokeLater(() -> new HorlogeGUI(timerService));
    }
}
