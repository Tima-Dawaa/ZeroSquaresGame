import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ZerosSquareState {
    Cell[][] grid;
    List<ZerosSquarePlayers> players;
    Boolean isLoss = false;
    List<ZerosSquarePlayers> sortPlayers = new ArrayList<ZerosSquarePlayers>();
    List<ZerosSquareState> nextStates = new ArrayList<ZerosSquareState>();
    List<Character> direction = new ArrayList<>(Arrays.asList('w', 's', 'a', 'd'));


    public ZerosSquareState(Cell[][] initialGrid, List<ZerosSquarePlayers> players) {
        this.grid = initialGrid;
        this.players = new ArrayList<>(players);
        this.isLoss = false;
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
        switch (color.toLowerCase()) {
            case "r":
                return ConsoleColors.RED;
            case "g":
                return ConsoleColors.GREEN;
            case "b":
                return ConsoleColors.BLUE;
            case "y":
                return ConsoleColors.YELLOW;
            case "p":
                return ConsoleColors.PURPLE;

            default:
                return ConsoleColors.WHITE;
        }
    }

    String getCellColor(CellType type) {
        switch (type) {
            case BLOCK:
                return ConsoleColors.BLACK_BOLD;
            case FREE:
                return ConsoleColors.WHITE_BOLD;
            case GOAL:
                return ConsoleColors.GREEN_BRIGHT;
            case WEAK_FRAME:
                return ConsoleColors.WHITE_BACKGROUND_BRIGHT;
            case WEAK_BLOCK:
                return ConsoleColors.BLUE_BACKGROUND_BRIGHT;
            default:
                return ConsoleColors.WHITE;
        }
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
        for (ZerosSquarePlayers player : sortedPlayers) {
            System.out.println(player.color);
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


            if (reachedWeakBlock) {
                newState.lossGame();
                return newState;
            }
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
            System.out.println("Player reached WEAK_BLOCK at: (" + newI + ", " + newJ + ")");
            player.color = "#";
            player.i = newI;
            player.j = newJ;
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
                System.out.println("Player " + player.color + " has reached their goal!");
                grid[player.i][player.j].id = "f";
                grid[player.i][player.j].type = CellType.FREE;

                player.isGoalAchieved = true;

            }
        }
    }

    boolean lossGame() {
        for (ZerosSquarePlayers player : players) {
            if (grid[player.i][player.j].type == CellType.WEAK_BLOCK) {
                System.out.println(player.color + " has exited the grid because WEAKBLOCK");
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

    List<ZerosSquareState> nextStates() {
        List<ZerosSquareState> nextStates = new ArrayList<ZerosSquareState>();

        for (int i = 0; i < direction.size(); i++) {
            ZerosSquareState newState = this.move(direction.get(i));
            if (!newState.equals(this)) {
                    nextStates.add(newState);
                }

        }
        System.out.println(nextStates.size());
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
        int result = Objects.hash(players, isLoss, sortPlayers, nextStates, direction);
        result = 31 * result + Arrays.hashCode(grid);
        return result;
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

}
