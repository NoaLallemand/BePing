package LogBean;

import java.util.EventObject;

public class LogEvent extends EventObject
{
    private String log;
    public String getLog() { return log; }

    public LogEvent(Object source)
    {
        super(source);
    }

    public LogEvent(Object source, String log)
    {
        super(source);
        this.log = log;
    }
}
