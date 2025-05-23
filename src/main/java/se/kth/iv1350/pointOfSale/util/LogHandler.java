package se.kth.iv1350.pointOfSale.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for handling the log.
 */
public class LogHandler {
    private static final String logFileProcessSale = "log-file.txt";

    private PrintWriter logFile;

    public LogHandler() throws IOException {
        logFile = new PrintWriter(
            new FileWriter(logFileProcessSale), true);
    }

    /**
     * Writes a log entry description of an exception
     * @param exception The exception that shall be logged
     */
    public void logException(Exception exception){
        StringBuilder logMsgBuilder= new StringBuilder();
        logMsgBuilder.append(createTime());
        logMsgBuilder.append("Exception was thrown: ");
        logMsgBuilder.append(exception.getMessage());
        logFile.println(logMsgBuilder);
        exception.printStackTrace(logFile);
    }

    private String createTime(){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter= DateTimeFormatter.
        ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}
