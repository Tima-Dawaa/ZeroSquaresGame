import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
public class PrintLog {
    public static Logger logger = Logger.getLogger(ZeroSquaresGame.class.getName());

    public void saveToLog (Map<String, Object> result, int currentLevel) throws IOException {

        FileHandler fileHandler = new FileHandler("./logs/" +result.get("algorithmName")+"/" + currentLevel +".log");
        fileHandler.setFormatter(new LogFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(Level.ALL);
        logger.log(Level.FINE, "Visited States: " + result.get("visitedCount"));
        logger.log(Level.FINE, "Path States: " +((List<ZerosSquareState>) result.get("path")).size());
        logger.log(Level.FINE, "Time: " + result.get("executionTime") + " milliseconds");
        logger.log(Level.FINE, "Memory Used: " +  result.get("memoryUsage") + " KB");

        fileHandler.close();
    }
}
