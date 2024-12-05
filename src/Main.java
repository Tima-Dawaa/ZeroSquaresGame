import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    static Map<Integer, Integer> levelNum = new HashMap<>();
    static {
        levelNum.put(6, 10);
        levelNum.put(7, 20);
        levelNum.put(8, 30);
    }
    private static void createLogsFiles() throws IOException {
        PrintLog mylogger = new PrintLog();
        for (int i = 0; i < 3; i++) {
        ZeroSquaresGame start = new ZeroSquaresGame();
        Cell[][] initialGrid = start.levels.get(6+i);
        List<ZerosSquarePlayers> initialPlayers = start.levelPlayers.get(6+i);

        ZerosSquareState initialState = new ZerosSquareState(initialGrid, initialPlayers);

            Map<String, Map<String, Object>> results = start.runAllSearchAlgorithms(initialState);
            int currentLevel = levelNum.get(6+i);
            mylogger.saveToLog(results.get("Uniform Cost Search (UCS)"), currentLevel);
            mylogger.saveToLog(results.get("Breadth-First Search (BFS)"), currentLevel);
            mylogger.saveToLog(results.get("Depth-First Search (DFS)"), currentLevel);
            mylogger.saveToLog(results.get("Steepest Hill Climbing"), currentLevel);
            mylogger.saveToLog(results.get("Simple Hill Climbing"), currentLevel);
            mylogger.saveToLog(results.get("A*"), currentLevel);
            mylogger.saveToLog(results.get("Recursive DFS"), currentLevel);
        }
    }

    public static void main(String[] args) throws IOException {
//        ZeroSquaresGame start=new ZeroSquaresGame();
//        start.start();
        //______________________________________________________First Evalution___________________________________


        createLogsFiles();
//        ZeroSquaresGame start = new ZeroSquaresGame();
//        Cell[][] initialGrid = start.levels.get(start.currentLevel);
//        List<ZerosSquarePlayers> initialPlayers = start.levelPlayers.get(start.currentLevel);
//
//        ZerosSquareState initialState = new ZerosSquareState(initialGrid, initialPlayers);
//
//        start.runAllSearchAlgorithms(initialState);

//        Map<String, Object> result = start.searchAlgUcs(initialState);
//
//        if (result != null) {
//            List<ZerosSquareState> path = (List<ZerosSquareState>) result.get("path");
//            for (ZerosSquareState state : path) {
//                state.print();
//                System.out.println();
//            }
//            System.out.println("Length of Success Path : " + ((List<ZerosSquareState>) result.get("path")).size());
//            System.out.println("Visited Nodes Count: " + result.get("visitedCount"));
//
//        }
//    }
    }
}