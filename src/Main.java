import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
//        ZeroSquaresGame start=new ZeroSquaresGame();
//        start.start();
        //______________________________________________________First Evalution___________________________________


        ZeroSquaresGame start = new ZeroSquaresGame();
        Cell[][] initialGrid = start.levels.get(start.currentLevel);
        List<ZerosSquarePlayers> initialPlayers = start.levelPlayers.get(start.currentLevel);

        ZerosSquareState initialState = new ZerosSquareState(initialGrid, initialPlayers);

        Map<String, Object> bfsResult = start.SearchAlgBfs(initialState);

        if (bfsResult != null) {


            List<ZerosSquareState> path = (List<ZerosSquareState>) bfsResult.get("path");
            for (ZerosSquareState state : path) {
                state.print();
                System.out.println();
            }
            System.out.println("Length of Success Path : "+ ((List<ZerosSquareState>) bfsResult.get("path")).size());
            System.out.println("Visited Nodes Count: " + bfsResult.get("visitedCount"));
        }
    }
}