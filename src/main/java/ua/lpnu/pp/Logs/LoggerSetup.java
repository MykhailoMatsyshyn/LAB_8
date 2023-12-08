package ua.lpnu.pp.Logs;

import java.io.IOException;
import java.util.logging.*;

public class LoggerSetup {
    public static void setupLogger() {

        Logger globalLogger = Logger.getLogger("");

        Handler[] handlers = globalLogger.getHandlers();
        for (Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }

        try {
            FileHandler fileHandler = new FileHandler("D:\\Java_\\LabsNULP\\LAB_7\\src\\main\\java\\ua\\lpnu\\pp\\Logs\\log.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            EmailHandler emailHandler = new EmailHandler("mykhailo.matsyshyn.oi.2022@lpnu.ua", "gcxbrkpzsnkyzbzi", "mykhailo.matsyshyn.oi.2022@lpnu.ua", "Severe Log Alert");
            globalLogger.addHandler(fileHandler);
            globalLogger.addHandler(emailHandler);


            globalLogger.setLevel(Level.INFO);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}