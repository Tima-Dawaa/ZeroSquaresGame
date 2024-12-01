//this class have the 6 grids for playing and intelligence search algorithms

import java.util.*;

public class ZeroSquaresGame {
    List<Cell[][]> levels = new ArrayList<>();
    List<List<ZerosSquarePlayers>> levelPlayers = new ArrayList<>();
    int currentLevel = 0;
    private Map<String, Object> result;

    public ZeroSquaresGame() {
        char[][] level2 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'f', 'f', 'x', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'x', 'x', 'x', 'R', 'x', 'x', 'x'},
                {'_', '_', '_', 'x', 'x', 'x', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level2Players = new ArrayList<>();
        level2Players.add(new ZerosSquarePlayers(2, 1, "r", "R"));
        char[][] level6 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', '_', '_', '_'},
                {'x', 'R', 'f', 'f', 'f', 'x', 'x', '_', '_'},
                {'x', 'f', 'f', 'B', 'f', 'f', 'x', 'x', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level6Players = new ArrayList<>();
        level6Players.add(new ZerosSquarePlayers(3, 7, "r", "R"));
        level6Players.add(new ZerosSquarePlayers(3, 6, "b", "B"));
        char[][] level18 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_', '_'},
                {'x', 'f', 'f', 'f', 'x', 'R', 'f', 'x', '_', '_'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'x', '_', '_'},
                {'x', 'f', 'f', 'f', 'x', 'P', 'f', 'x', '_', '_'},
                {'x', 'Y', 'x', 'f', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'x', 'f', 'f', 'x', 'f', 'B', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level18Players = new ArrayList<>();
        level18Players.add(new ZerosSquarePlayers(6, 8, "p", "P"));
        level18Players.add(new ZerosSquarePlayers(6, 7, "r", "R"));
        level18Players.add(new ZerosSquarePlayers(5, 8, "y", "Y"));
        level18Players.add(new ZerosSquarePlayers(5, 7, "b", "B"));
        char[][] level21 = new char[][]{
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', '#', '_'},
                {'x', 'f', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_'},
                {'x', 'f', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'R', 'x', '_'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level21Players = new ArrayList<>();
        level21Players.add(new ZerosSquarePlayers(1, 1, "r", "R"));
        char[][] level12 = new char[][]{
                {'_', '_', 'x', 'x', 'x', 'x', 'x', '_', '_'},
                {'x', 'x', 'x', 'f', 'Y', 'f', 'x', '_', '_'},
                {'x', 'R', 'f', 'f', 'f', 'f', 'x', '_', '_'},
                {'x', 'f', 'f', 'f', 'x', 'f', 'x', '_', '_'},
                {'x', 'f', 'f', 'f', 'x', 'f', 'x', '_', '_'},
                {'x', 'x', 'B', 'f', 'x', 'f', 'x', '_', '_'},
                {'_', 'x', 'x', 'f', 'x', 'x', 'x', '_', '_'},
                {'_', '_', 'x', 'x', 'x', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level12Players = new ArrayList<>();
        level12Players.add(new ZerosSquarePlayers(3, 5, "r", "R"));
        level12Players.add(new ZerosSquarePlayers(5, 5, "b", "B"));
        level12Players.add(new ZerosSquarePlayers(4, 5, "y", "Y"));
        char[][] level26 = new char[][]{
                {'_', '_', '_', '_', '_', '_', '_', '_', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'x', 'x', 'x', '_', '_', 'x', 'x', 'f', 'P', 'x'},
                {'x', '@', 'C', 'Y', 'x', 'x', 'x', 'x', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
                {'_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_', '_'},
        };
        ArrayList<ZerosSquarePlayers> level26Players = new ArrayList<>();
        level26Players.add(new ZerosSquarePlayers(3, 4, "c", "C"));
        level26Players.add(new ZerosSquarePlayers(3, 5, "p", "P"));
        level26Players.add(new ZerosSquarePlayers(3, 6, "y", "Y"));
        level26Players.add(new ZerosSquarePlayers(3, 7, "r", "R"));
        levels.add(convert(level2));
        levels.add(convert(level6));
        levels.add(convert(level12));
        levels.add(convert(level18));
        levels.add(convert(level21));
        levels.add(convert(level26));
        levelPlayers.add(level2Players);
        levelPlayers.add(level6Players);
        levelPlayers.add(level12Players);
        levelPlayers.add(level18Players);
        levelPlayers.add(level21Players);
        levelPlayers.add(level26Players);

    }

    static Cell[][] convert(char[][] level) {
        Cell[][] cells = new Cell[level.length][level[0].length];
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[i].length; j++) {
                if (level[i][j] == 'f') cells[i][j] = new Cell(CellType.FREE, "f");
                else if (level[i][j] == 'x') cells[i][j] = new Cell(CellType.BLOCK, "x");
                else if (level[i][j] == '@') cells[i][j] = new Cell(CellType.WEAK_FRAME, "@");
                else if (level[i][j] == '#') cells[i][j] = new Cell(CellType.WEAK_BLOCK, "#");
                else if (Character.isUpperCase(level[i][j]))
                    cells[i][j] = new Cell(CellType.GOAL, Character.toString(level[i][j]));
                else if (level[i][j] == '_') cells[i][j] = new Cell(CellType.Complement, "_");
            }
        }
        System.out.println(cells[4][1].id + cells[4][1].type);
        return cells;
    }

    void runAllSearchAlgorithms(ZerosSquareState initialState) {
        Map<String, Map<String, Object>> results = new LinkedHashMap<>();

        results.put("Uniform Cost Search (UCS)", searchAlgUcs(initialState));
        results.put("Breadth-First Search (BFS)", searchAlgBfs(initialState));
        results.put("Depth-First Search (DFS)", searchAlgDfs(initialState));
        results.put("A*",searchAlgAStar(initialState));

        System.out.println("========================================");
        System.out.println("           Search Algorithm Results      ");
        System.out.println("========================================");

        for (Map.Entry<String, Map<String, Object>> entry : results.entrySet()) {
            String algorithmName = entry.getKey();
            Map<String, Object> result = entry.getValue();

            System.out.println("Algorithm: " + algorithmName);
            if (result != null) {
                System.out.println("    Path: " +((List<ZerosSquareState>) result.get("path")).size());
                System.out.println("    Visited Nodes: " + result.get("visitedCount"));
            } else {
                System.out.println(" No solution found.");
            }
            System.out.println("----------------------------------------");
        }
    }



    Map<String, Object> searchAlgBfs(ZerosSquareState initialState) {
        Queue<ZerosSquareState> queue = new LinkedList<ZerosSquareState>();
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        queue.add(initialState);
        visited.add(initialState);
        parent.put(initialState, null);

        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();

            if (current.gameDone()) {

                Map<String, Object> result = new HashMap<>();
                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", visited.size());
                return result;


            }

            for (ZerosSquareState next : current.nextStates()) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                    parent.put(next, current);
                }
            }
        }
        return null;
    }

    Map<String, Object> searchAlgDfs(ZerosSquareState initialState) {
        Stack<ZerosSquareState> stack = new Stack<ZerosSquareState>();
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        stack.add(initialState);
        visited.add(initialState);
        parent.put(initialState, null);

        while (!stack.isEmpty()) {
            ZerosSquareState current = stack.pop();


            if (current.gameDone()) {


                Map<String, Object> result = new HashMap<>();
                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", visited.size());
                return result;


            }

            for (ZerosSquareState next : current.nextStates()) {
                if (!visited.contains(next) && !next.lossGame()) {
                    visited.add(next);
                    stack.push(next);
                    parent.put(next, current);
                }
            }
        }
        return null;
    }

    Map<String, Object> searchAlgoDfsRecursive(ZerosSquareState initialState) {
        Set<ZerosSquareState> visited = new HashSet<>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();
        parent.put(initialState, null);
        return dfsRecursive(initialState, visited, parent);
    }

    private Map<String, Object> dfsRecursive(ZerosSquareState currentState, Set<ZerosSquareState> visited, Map<ZerosSquareState, ZerosSquareState> parent) {
        if (currentState.lossGame()) {
            return null;
        }
        visited.add(currentState);

        if (currentState.gameDone()) {
            Map<String, Object> result = new HashMap<>();
            result.put("path", getSuccessPath(currentState, parent));
            result.put("visitedCount", visited.size());
            return result;
        }

        for (ZerosSquareState nextState : currentState.nextStates()) {
            if (!visited.contains(nextState)) {
                parent.put(nextState, currentState);

                Map<String, Object> result = dfsRecursive(nextState, visited, parent);

                if (result != null) {
                    return result;
                }
            }
        }
        return null;
    }


    Map<String, Object> searchAlgUcs(ZerosSquareState initialState) {

        Queue<ZerosSquareState> queue = new PriorityQueue<ZerosSquareState>(Comparator.comparingInt(ZerosSquareState::getCostWeights));
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();
        initialState.cost = 0;
        queue.add(initialState);
        parent.put(initialState, null);

        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();

            visited.add(current);
            if (current.gameDone()) {


                Map<String, Object> result = new HashMap<>();
                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", visited.size());
                return result;


            }

            for (ZerosSquareState next : current.nextStates()) {

                int newCost = current.cost + 1;

                if (!visited.contains(next) || newCost < next.cost) {

                    next.cost = newCost;

                    queue.add(next);
                    parent.put(next, current);
                }
            }
        }
        return null;
    }




    Map<String, Object> searchAlgAStar(ZerosSquareState initialState) {

        Queue<ZerosSquareState> queue = new PriorityQueue<ZerosSquareState>(Comparator.comparingInt(ZerosSquareState::getCostStar));
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();


        initialState.cost = 0;
        queue.add(initialState);
        parent.put(initialState, null);


        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();
            visited.add(current);
            if (current.gameDone()) {

                Map<String, Object> result = new HashMap<>();
                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", visited.size());
                return result;


            }

            for (ZerosSquareState next : current.nextStates()) {
                int newCost = current.cost + next.cost;

                if (!visited.contains(next) || newCost < next.cost) {
                    next.cost = newCost;
                    queue.add(next);
                    parent.put(next, current);
                }
            }
        }
        return null;
    }



    List<ZerosSquareState> getSuccessPath(ZerosSquareState gameDoneState, Map<ZerosSquareState, ZerosSquareState> parents) {
        List<ZerosSquareState> pathWin = new ArrayList<ZerosSquareState>();
        ZerosSquareState current = gameDoneState;

        while (current != null) {
            pathWin.add(0, current);
            current = parents.get(current);
        }
        return pathWin;
    }


//    void start() {
//        Scanner scanner = new Scanner(System.in);
//
//        while (currentLevel < levels.size()) {
//            Cell[][] grid = levels.get(currentLevel);
//            List<ZerosSquarePlayers> players = levelPlayers.get(currentLevel);
//            ZerosSquareState state = new ZerosSquareState(grid, players);
//

//            while (!state.gameDone()) {
//                state.print();
//                System.out.println("Enter move direction (w = up, s = down, a = left, d = right): ");
//                char direction = scanner.next().charAt(0);
//
//                ZerosSquareState newState = state.move(direction);
//                newState.nextStates();
//                if (state.isLoss) {
//                    System.out.println("Game Over!");
//                    return;
//                }
//                newState.playerWin();
//                state = newState;
//            }
//
//            System.out.println("Level " + (currentLevel + 1) + " Complete!");
//            currentLevel++;
//        }
//
//        System.out.println("All levels completed!");
//        scanner.close();
//    }
}
