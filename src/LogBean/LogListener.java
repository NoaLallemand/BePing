package LogBean;

import java.util.EventListener;

public interface LogListener extends EventListener
{
    public void logDetected(LogEvent e);
}
