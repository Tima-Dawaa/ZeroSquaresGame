import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ZerosSquarePlayers {
    int i ;
    int j;
    String color;

    boolean isGoalAchieved;
   String goalName;


    public ZerosSquarePlayers(int i, int j, String color,String goalName) {
        this.i = i;
        this.j = j;
        this.color = color;
        this.isGoalAchieved = false;
        this.goalName=goalName;

    }
    public ZerosSquarePlayers(ZerosSquarePlayers newPlayersCopy) {
        this.i = newPlayersCopy.i;
        this.j = newPlayersCopy.j;
        this.color = new String(newPlayersCopy.color);
        this.isGoalAchieved = newPlayersCopy.isGoalAchieved;
        this.goalName = new String(newPlayersCopy.goalName);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZerosSquarePlayers that)) return false;
        return i == that.i && j == that.j && isGoalAchieved == that.isGoalAchieved && Objects.equals(color, that.color) && Objects.equals(goalName, that.goalName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j, color, isGoalAchieved, goalName);
    }
}
