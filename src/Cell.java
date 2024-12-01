//Cell class : each cell has an  id that matches its type

import java.util.Objects;

public class Cell {
    CellType type;
    String id;
    public Cell(CellType type ,String id){
      this.type =type;
      this.id=id;
    }

    public Cell(Cell newCellCopy) {
        this.type = newCellCopy.type;
        this.id = new String(newCellCopy.id);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return type == cell.type && Objects.equals(id, cell.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, id);
    }
}
