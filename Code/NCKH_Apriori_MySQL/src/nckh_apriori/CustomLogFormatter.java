package nckh_apriori;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

class CustomLogFormatter extends Formatter {
    public String format(LogRecord rec) {
        return formatMessage(rec)+"\n";
    }

    public String getHead(Handler h) {
        return "";
    }

    public String getTail(Handler h) {
        return "";
    }
}