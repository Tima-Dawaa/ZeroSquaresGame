//class ZeroSquareState it represent the processing of game (logic cases) =>> the logic of game (like player win || check if game done(all the players win)|| the implementation of weak white frame or weak black frame
// the move function its return new state after every new movement from the player


import java.util.*;

public class ZerosSquareState {
    Cell[][] grid;
    List<ZerosSquarePlayers> players;
    Boolean isLoss = false;
    List<ZerosSquarePlayers> sortPlayers = new ArrayList<ZerosSquarePlayers>();
    List<ZerosSquareState> nextStates = new ArrayList<ZerosSquareState>();
    List<Character> direction = new ArrayList<>(Arrays.asList('w', 's', 'a', 'd'));

    int cost = 1;
    int costOfMove = 0;

    public ZerosSquareState(Cell[][] initialGrid, List<ZerosSquarePlayers> players) {
        this.grid = initialGrid;
        this.players = new ArrayList<>(players);
        this.isLoss = false;
    }


    int getCostWeights() {
        return cost;
    }

    void print() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                boolean playerFound = false;


                if (grid[i][j].type == CellType.Complement) {
                    System.out.print("  ");
                    continue;
                }


                for (ZerosSquarePlayers player : players) {
                    if (player.i == i && player.j == j && !player.isGoalAchieved) {
                        String colorCode = getPlayerColor(player.color);
                        System.out.print(colorCode + player.color + ConsoleColors.RESET + " ");
                        playerFound = true;
                        break;
                    }
                }


                if (!playerFound) {
                    String colorCode = getCellColor(grid[i][j].type);
                    System.out.print(colorCode + grid[i][j].id.charAt(0) + ConsoleColors.RESET + " ");
                }
            }
            System.out.println();
        }
    }


    String getPlayerColor(String color) {
        return switch (color.toLowerCase()) {
            case "r" -> ConsoleColors.RED;
            case "g" -> ConsoleColors.GREEN;
            case "b" -> ConsoleColors.BLUE;
            case "y" -> ConsoleColors.YELLOW;
            case "p" -> ConsoleColors.PURPLE;
            default -> ConsoleColors.WHITE;
        };
    }

    String getCellColor(CellType type) {
        return switch (type) {
            case BLOCK -> ConsoleColors.BLACK_BOLD;
            case FREE -> ConsoleColors.WHITE_BOLD;
            case GOAL -> ConsoleColors.GREEN_BRIGHT;
            case WEAK_FRAME -> ConsoleColors.WHITE_BACKGROUND_BRIGHT;
            case WEAK_BLOCK -> ConsoleColors.BLUE_BACKGROUND_BRIGHT;
            default -> ConsoleColors.WHITE;
        };
    }


    List<ZerosSquarePlayers> sortByDirection(char direction, List<ZerosSquarePlayers> movablePlayers) {
        List<ZerosSquarePlayers> sortedPlayers = new ArrayList<>(movablePlayers);

        switch (direction) {
            case 'w':
                sortedPlayers.sort((ZerosSquarePlayers p1, ZerosSquarePlayers p2) -> Integer.compare(p1.i, p2.i));

                break;

            case 's':
                sortedPlayers.sort((ZerosSquarePlayers p1, ZerosSquarePlayers p2) -> Integer.compare(p2.i, p1.i));
                break;

            case 'a':
                sortedPlayers.sort((ZerosSquarePlayers p1, ZerosSquarePlayers p2) -> Integer.compare(p1.j, p2.j));

                break;

            case 'd':
                sortedPlayers.sort((ZerosSquarePlayers p1, ZerosSquarePlayers p2) -> Integer.compare(p2.j, p1.j));


                break;


        }

        return sortedPlayers;
    }


    List<ZerosSquarePlayers> getMovablePlayers(List<ZerosSquarePlayers> players, char direction, List<ZerosSquarePlayers> getOutWinners) {
        List<ZerosSquarePlayers> playersCanMove = new ArrayList<>();

        for (ZerosSquarePlayers player : players) {
            int targetI = player.i;
            int targetJ = player.j;
            switch (direction) {
                case 'w':
                    targetI -= 1;
                    break;
                case 's':
                    targetI += 1;
                    break;
                case 'a':
                    targetJ -= 1;
                    break;
                case 'd':
                    targetJ += 1;
                    break;

            }

            if (isPathClear(player, targetI, targetJ, getOutWinners)) {
                playersCanMove.add(player);
            }
        }

        return playersCanMove;
    }


    private boolean isPathClear(ZerosSquarePlayers player, int targetI, int targetJ, List<ZerosSquarePlayers> getOutWinners) {

        if (targetI < 0 || targetI >= grid.length || targetJ < 0 || targetJ >= grid[0].length) {

            return false;
        }

        Cell targetCell = grid[targetI][targetJ];


        for (ZerosSquarePlayers otherPlayer : getOutWinners) {
            if (otherPlayer.i == targetI && otherPlayer.j == targetJ && otherPlayer != player) {

                return false;
            }
        }


        return (targetCell.type == CellType.FREE || targetCell.type == CellType.GOAL ||
                targetCell.type == CellType.WEAK_FRAME || targetCell.type == CellType.WEAK_BLOCK);
    }


    public ZerosSquareState move(char direction) {
        ZerosSquareState newState = deepCopy();

        List<ZerosSquarePlayers> getOutWinners = new ArrayList<>();
        for (ZerosSquarePlayers player : newState.players) {
            if (!player.isGoalAchieved) {
                getOutWinners.add(player);
            }

        }
        for (ZerosSquarePlayers player : newState.sortByDirection(direction, getMovablePlayers(getOutWinners, direction, getOutWinners))) {
            int newI = player.i;
            int newJ = player.j;
            boolean reachedWeakBlock = false;


            switch (direction) {
                case 'w':
                    while (newI > 0 && isPathClear(player, newI - 1, newJ, getOutWinners)) {
                        newI--;
                        reachedWeakBlock = handlePlayerMovement(newState, player, newI, newJ);
                        if (reachedWeakBlock) break;
                    }
                    break;
                case 's':
                    while (newI < newState.grid.length - 1 && isPathClear(player, newI + 1, newJ, getOutWinners)) {
                        newI++;
                        reachedWeakBlock = handlePlayerMovement(newState, player, newI, newJ);
                        if (reachedWeakBlock) break;
                    }
                    break;
                case 'a':
                    while (newJ > 0 && isPathClear(player, newI, newJ - 1, getOutWinners)) {
                        newJ--;
                        reachedWeakBlock = handlePlayerMovement(newState, player, newI, newJ);
                        if (reachedWeakBlock) break;
                    }
                    break;
                case 'd':
                    while (newJ < newState.grid[0].length - 1 && isPathClear(player, newI, newJ + 1, getOutWinners)) {
                        newJ++;
                        reachedWeakBlock = handlePlayerMovement(newState, player, newI, newJ);
                        if (reachedWeakBlock) break;
                    }
                    break;
                default:
                    System.out.println("Invalid move");
                    return this;
            }


//            if (reachedWeakBlock) {
//                newState.lossGame();
//                return newState;
//            }
        }

        return newState;
    }


    private boolean handlePlayerMovement(ZerosSquareState newState, ZerosSquarePlayers player, int newI, int newJ) {
        Cell currentCell = newState.grid[player.i][player.j];
        Cell targetCell = newState.grid[newI][newJ];

        if (targetCell.type == CellType.WEAK_FRAME) {
            targetCell.type = CellType.GOAL;
            targetCell.id = player.color;
        }

        if (currentCell.type == CellType.GOAL && currentCell.id.equals(player.color)) {
            currentCell.id = player.goalName;
        } else if (currentCell.type == CellType.GOAL && Character.isUpperCase(currentCell.id.charAt(0))) {
            newState.playerWin();
            if (player.isGoalAchieved) {
                return true;

            }
        } else {
            currentCell.id = "f";
            currentCell.type = CellType.FREE;
        }

        if (targetCell.type == CellType.WEAK_BLOCK) {

            player.color = "#";
            player.i = newI;
            player.j = newJ;
            newState.isLoss = true;
            newState.lossGame();
            return true;
        }

        player.i = newI;
        player.j = newJ;

        return false;
    }


    void playerWin() {
        for (ZerosSquarePlayers player : players) {

            if (grid[player.i][player.j].type == CellType.GOAL &&
                    grid[player.i][player.j].id.equals(player.goalName)) {
                grid[player.i][player.j].id = "f";
                grid[player.i][player.j].type = CellType.FREE;

                player.isGoalAchieved = true;

            }
        }
    }

    boolean lossGame() {
        for (ZerosSquarePlayers player : players) {
            if (grid[player.i][player.j].type == CellType.WEAK_BLOCK) {
                isLoss = true;
                return isLoss;
            }

        }
        return isLoss;
    }

    boolean gameDone() {
        for (ZerosSquarePlayers player : players) {

            if (!player.isGoalAchieved) {
                return false;
            }
        }
        return true;
    }

    int costOneMove(char direction) {
        List<ZerosSquarePlayers> getOutWinners = new ArrayList<>();
        for (ZerosSquarePlayers player1 : players) {
            if (!player1.isGoalAchieved) {
                getOutWinners.add(player1);
            }
        }
        int count = 0;
        for (ZerosSquarePlayers player : players) {
            int targetI = player.i;
            int targetJ = player.j;
            while (isPathClear(player, targetI, targetJ, getOutWinners)) {
                switch (direction) {
                    case 'w' -> targetI -= 1;
                    case 's' -> targetI += 1;
                    case 'a' -> targetJ -= 1;
                    case 'd' -> targetJ += 1;
                }
                count += 1;
            }
        }
        return count;
    }


    Set<ZerosSquareState> nextStates() {
        Set<ZerosSquareState> nextStates = new HashSet<ZerosSquareState>();

        for (int i = 0; i < direction.size(); i++) {
            ZerosSquareState newState = this.move(direction.get(i));
            if (!newState.equals(this)) {
                nextStates.add(newState);
                newState.costOfMove = costOneMove(direction.get(i));
            }

        }
        return nextStates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZerosSquareState that)) return false;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!grid[i][j].equals(((ZerosSquareState) o).grid[i][j])) {
                    return false;
                }
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (!players.get(i).equals(((ZerosSquareState) o).players.get(i))) {
                return false;
            }
        }

        return isLoss == ((ZerosSquareState) o).isLoss;
    }


    @Override
    public int hashCode() {
        return Objects.hash(Arrays.deepHashCode(grid), players, isLoss);
    }

    ZerosSquareState deepCopy() {
        Cell[][] newGrid = new Cell[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                newGrid[i][j] = new Cell(grid[i][j]);
            }
        }

        List<ZerosSquarePlayers> newPlayers = new ArrayList<>();
        for (ZerosSquarePlayers player : players) {
            newPlayers.add(new ZerosSquarePlayers(player));
        }

        ZerosSquareState newState = new ZerosSquareState(newGrid, newPlayers);

        newState.sortPlayers = new ArrayList<>();
        for (ZerosSquarePlayers player : this.sortPlayers) {
            newState.sortPlayers.add(new ZerosSquarePlayers(player));
        }

        return newState;
    }

    int get_heuristic() {
        int cost = 0;
        for (ZerosSquarePlayers player : players) {
            if (player.isGoalAchieved||cleanResult()) {
                continue;
            }
            int playerGoalRow = playerGoalRow(player.goalName);
            int playerGoalColumn = playerGoalColumn(player.goalName);
            if (playerGoalRow != -1 && playerGoalColumn != -1) {
                cost = cost + Math.abs(player.i - playerGoalRow) + Math.abs(player.j - playerGoalColumn);
            }
        }

        return cost;
    }

    int playerGoalRow(String goalName) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].type == CellType.GOAL && grid[i][j].id.equals(goalName)) {
                    return i;
                }
            }

        }

        return -1;
    }

    int playerGoalColumn(String goalName) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j].type == CellType.GOAL && grid[i][j].id.equals(goalName)) {
                    return j;
                }
            }

        }
        return -1;
    }

    int getCostStar() {
        return get_heuristic() + getCostWeights();
    }

    boolean cleanResult() {
        if (lossGame()) return true;


        for (ZerosSquarePlayers player : players) {
            int countGoals = 0;
            String color = player.goalName;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j].id.equals(color)) {
                        countGoals++;

                    }
                }
            }
            if (countGoals == 2) return true;
        }
        return false;
    }
}







