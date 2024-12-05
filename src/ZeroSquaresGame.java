//this class have the 6 grids for playing and intelligence search algorithms

import java.util.*;

public class ZeroSquaresGame {
    List<Cell[][]> levels = new ArrayList<>();
    List<List<ZerosSquarePlayers>> levelPlayers = new ArrayList<>();
    int currentLevel = 8;
    Map<String, Object> result;

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

//        char[][] level0= new char[][]{
//                {'_', 'x', 'x', 'x', 'x', 'x', '_', '_', '_', '_', '_'},
//                {'x', 'x', 'f', 'f', 'f', 'x', 'x', 'x', 'x', 'x', '_'},
//                {'x', 'f', 'f', 'f', 'f', 'x', 'x', 'B', 'f', 'x', '_'},
//                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x', 'x'},
//                {'x', 'f', 'f', 'f', 'x', 'x', 'x', 'f', 'f', 'R', 'x'},
//                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x', 'x'},
//                {'x', 'x', 'f', 'f', 'x', 'x', 'x', 'x', 'x', 'x', '_'},
//                {'_', 'x', 'x', 'x', 'x', '_', '_', '_', '_', '_', '_'},
//
//        };
//        ArrayList<ZerosSquarePlayers> level0Players = new ArrayList<>();
//        level0Players.add(new ZerosSquarePlayers(1, 2, "r", "R"));
//        level0Players.add(new ZerosSquarePlayers(6, 2, "b", "B"));


        char[][] level10 = new char[][]{
                {'_', '_', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', '_', '_'},
                {'_', 'x', 'x', 'f', 'f', 'f', 'f', 'f', 'f', 'x', 'x', '_'},
                {'x', 'x', 'f', 'f', 'x', 'f', 'B', 'x', 'f', 'f', 'x', 'x'},
                {'x', 'f', 'f', 'x', 'f', 'R', 'f', 'f', 'x', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'x', 'f', 'f', 'x', 'x', 'f', '_'},
                {'x', 'f', 'x', 'f', 'x', 'x', 'f', 'f', 'f', 'x', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'x', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
        };
        ArrayList<ZerosSquarePlayers> level10Players = new ArrayList<>();
        level10Players.add(new ZerosSquarePlayers(5, 1, "r", "R"));
        level10Players.add(new ZerosSquarePlayers(5, 10, "b", "B"));

        char[][] level20 = new char[][]{
                {'_', '_', '_', 'x', 'x', 'x', 'x', '_', '_'},
                {'x', 'x', 'x', 'x', 'f', 'Y', 'x', 'x', 'x'},
                {'x', 'B', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'R', 'x', 'G', 'f', 'x', 'C', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},

        };
        ArrayList<ZerosSquarePlayers> level20Players = new ArrayList<>();
        level20Players.add(new ZerosSquarePlayers(1, 4, "g", "G"));
        level20Players.add(new ZerosSquarePlayers(2, 2, "r", "R"));
        level20Players.add(new ZerosSquarePlayers(2, 3, "b", "B"));
        level20Players.add(new ZerosSquarePlayers(3, 2, "c", "C"));
        level20Players.add(new ZerosSquarePlayers(3, 7, "y", "Y"));


        char[][] level30 = new char[][]{
                {'_', '_', '_', 'x', 'x', 'x', 'x', 'x', 'x', '_', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'f', 'f', 'R', 'x', 'x', 'x', 'Y', 'x', 'x', 'C', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x'},
                {'x', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'f', 'x', 'f', 'f', 'x'},
                {'x', 'x', 'x', 'x', 'x', '@', 'x', 'x', 'B', 'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'_', '_', '_', '_', 'x', 'x', 'x', 'x', '#', 'x', '_', '_', '_', '_', '_', '_'},
        };

        ArrayList<ZerosSquarePlayers> level30Players = new ArrayList<>();
        level30Players.add(new ZerosSquarePlayers(2, 1, "g", "G"));
        level30Players.add(new ZerosSquarePlayers(3, 2, "r", "R"));
        level30Players.add(new ZerosSquarePlayers(2, 2, "b", "B"));
        level30Players.add(new ZerosSquarePlayers(2, 3, "c", "C"));
        level30Players.add(new ZerosSquarePlayers(3, 1, "y", "Y"));
//        levels.add(convert(level0));
        levels.add(convert(level2));
        levels.add(convert(level6));
        levels.add(convert(level12));
        levels.add(convert(level18));
        levels.add(convert(level21));
        levels.add(convert(level26));
        levels.add(convert(level10));
        levels.add(convert(level20));
        levels.add(convert(level30));


//        levelPlayers.add(level0Players);
        levelPlayers.add(level2Players);
        levelPlayers.add(level6Players);
        levelPlayers.add(level12Players);
        levelPlayers.add(level18Players);
        levelPlayers.add(level21Players);
        levelPlayers.add(level26Players);
        levelPlayers.add(level10Players);
        levelPlayers.add(level20Players);
        levelPlayers.add(level30Players);

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
        return cells;
    }

    Map<String, Map<String, Object>> runAllSearchAlgorithms(ZerosSquareState initialState) {
        Map<String, Map<String, Object>> results = new LinkedHashMap<>();

        results.put("Uniform Cost Search (UCS)", searchAlgUcs(initialState));
        results.put("Breadth-First Search (BFS)", searchAlgBfs(initialState));
        results.put("Depth-First Search (DFS)", searchAlgDfs(initialState));
        results.put("Steepest Hill Climbing", searchAlgHill_climbing_steepest(initialState));
        results.put("Simple Hill Climbing", searchAlgHill_Climbing_simple(initialState));
        results.put("A*", searchAlgAStar(initialState));
        results.put("Recursive DFS", searchAlgoDfsRecursive(initialState));

        System.out.println("========================================");
        System.out.println("           Search Algorithm Results      ");
        System.out.println("========================================");

        for (Map.Entry<String, Map<String, Object>> entry : results.entrySet()) {
            String algorithmName = entry.getKey();
            Map<String, Object> result = entry.getValue();

            System.out.println("Algorithm: " + algorithmName);
            if (result != null) {
                System.out.println("    Path: " + ((List<ZerosSquareState>) result.get("path")).size());
                System.out.println("    Visited Nodes: " + result.get("visitedCount"));
            } else {
                System.out.println(" No solution found.");
            }
            System.out.println("----------------------------------------");
        }
        return results;
    }

    private Map<String, Object> constructResult(ZerosSquareState finalState, Map<ZerosSquareState, ZerosSquareState> parent,
                                                int visitedCount, long startTime, long initialMemory, Runtime runtime, String nameAlgorithm) {
        long endTime = System.nanoTime();
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();

        Map<String, Object> result = new HashMap<>();
        result.put("path", getSuccessPath(finalState, parent));
        result.put("visitedCount", visitedCount);
        result.put("executionTime", (endTime - startTime) / 1_000_000.0);
        result.put("memoryUsage",Math.abs((finalMemory - initialMemory)/ 1000));
        result.put("algorithmName", nameAlgorithm);
        return result;
    }


    Map<String, Object> searchAlgBfs(ZerosSquareState initialState) {
        System.gc();
        Queue<ZerosSquareState> queue = new LinkedList<ZerosSquareState>();
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        queue.add(initialState);
        visited.add(initialState);
        parent.put(initialState, null);

        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();


            if (current.gameDone()) {
                return constructResult(current, parent, visited.size(), startTime, initialMemory, runtime,"BFS");

            }
            if (current.cleanResult()) {

                continue;

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
        System.gc();
        Stack<ZerosSquareState> stack = new Stack<ZerosSquareState>();
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        stack.add(initialState);
        visited.add(initialState);
        parent.put(initialState, null);

        while (!stack.isEmpty()) {
            ZerosSquareState current = stack.pop();

            if (current.cleanResult()) {
                continue;

            }


            if (current.gameDone()) {
                return constructResult(current, parent, visited.size(), startTime, initialMemory, runtime,"DFS");



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
        System.gc();
        Set<ZerosSquareState> visited = new HashSet<>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        parent.put(initialState, null);
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();
        Map<String, Object>result =dfsRecursive(initialState, visited, parent);
        long endTime = System.nanoTime();
        long finalMemory = runtime.totalMemory() - runtime.freeMemory();

        assert result != null;
        result.put("executionTime", (endTime - startTime) / 1_000_000.0);
        result.put("memoryUsage", Math.abs((finalMemory - initialMemory)/ 1000));
        result.put("algorithmName", "RDFS");

        return  result;

    }

    private Map<String, Object> dfsRecursive(ZerosSquareState currentState, Set<ZerosSquareState> visited, Map<ZerosSquareState, ZerosSquareState> parent) {
        if (currentState.cleanResult()) {
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
        System.gc();
        Queue<ZerosSquareState> queue = new PriorityQueue<ZerosSquareState>(Comparator.comparingInt(ZerosSquareState::getCostWeights));
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();

        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        initialState.cost = 0;
        queue.add(initialState);
        parent.put(initialState, null);

        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();
            if (current.cleanResult()) {
                continue;
            }

            visited.add(current);
            if (current.gameDone()) {
                return constructResult(current, parent, visited.size(), startTime, initialMemory, runtime,"UCS");

            }

            for (ZerosSquareState next : current.nextStates()) {
                int newCost = current.cost + next.costOfMove;
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
        System.gc();
        Queue<ZerosSquareState> queue = new PriorityQueue<ZerosSquareState>(Comparator.comparingInt(ZerosSquareState::getCostStar));
        Set<ZerosSquareState> visited = new HashSet<ZerosSquareState>();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        initialState.cost = 0;
        queue.add(initialState);
        parent.put(initialState, null);


        while (!queue.isEmpty()) {
            ZerosSquareState current = queue.poll();
            if (current.cleanResult()) {
                continue;

            }
            visited.add(current);
            if (current.gameDone()) {
                return constructResult(current, parent, visited.size(), startTime, initialMemory, runtime,"AStar");



            }

            for (ZerosSquareState next : current.nextStates()) {
                int newCost = current.cost + next.costOfMove;

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

    Map<String, Object> searchAlgHill_climbing_steepest(ZerosSquareState initialState) {
        System.gc();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();
        ArrayList<ZerosSquareState> path = new ArrayList<>();

        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        parent.put(initialState, null);
        ZerosSquareState current = initialState;
        path.add(initialState);

        while (true) {
            int currentHeuristic = current.get_heuristic();
            if (current.cleanResult()) {
                continue;

            }
            ZerosSquareState nextState = null;
            int minHeuristic = currentHeuristic;


            for (ZerosSquareState next : current.nextStates()) {
                int nextHeuristic = next.get_heuristic();


                if (nextHeuristic < minHeuristic) {
                    minHeuristic = nextHeuristic;
                    nextState = next;
                }


            }

            if (minHeuristic == currentHeuristic || nextState == null) {
                long endTime = System.nanoTime();
                long finalMemory = runtime.totalMemory() - runtime.freeMemory();

                Map<String, Object> result = new HashMap<>();

                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", path.size());
                result.put("executionTime", (endTime - startTime) / 1_000_000.0);
                result.put("memoryUsage", Math.abs((finalMemory - initialMemory)/ 1000));
                result.put("algorithmName", "SteepestHillClimbing");
                return result;
            }
            parent.put(nextState, current);
            path.add(nextState);
            current = nextState;
        }
    }

    Map<String, Object> searchAlgHill_Climbing_simple(ZerosSquareState initialState) {
        System.gc();
        Map<ZerosSquareState, ZerosSquareState> parent = new HashMap<>();
        ArrayList<ZerosSquareState> path = new ArrayList<>();
        Set<ZerosSquareState> visited = new HashSet<>();
        long startTime = System.nanoTime();
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory() - runtime.freeMemory();

        ZerosSquareState current = initialState;
        parent.put(current, null);
        path.add(current);
        visited.add(current);


        while (true) {
            int currentHeuristic = current.get_heuristic();

            if (current.cleanResult()) {
                continue;

            }
            boolean canImprove = false;
            System.out.println("Current State Heuristic: " + currentHeuristic); ///for debugggg


            for (ZerosSquareState nextState : current.nextStates()) {
                if (visited.contains(nextState)) {
                    continue;
                }

                int nextHeuristic = nextState.get_heuristic();


                if (nextHeuristic < currentHeuristic) {
                    parent.put(nextState, current);
                    path.add(nextState);
                    visited.add(nextState);
                    current = nextState;
                    canImprove = true;
                    break;
                }
            }
            if (!canImprove) {
                long endTime = System.nanoTime();
                long finalMemory = runtime.totalMemory() - runtime.freeMemory();
                Map<String, Object> result = new HashMap<>();

                result.put("path", getSuccessPath(current, parent));
                result.put("visitedCount", path.size());
                result.put("executionTime", (endTime - startTime) / 1_000_000.0);
                result.put("memoryUsage", Math.abs((finalMemory - initialMemory)/ 1000));
                result.put("algorithmName", "SimpleHillClimbing");
                return result;
            }


        }

    }


}



