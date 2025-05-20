package BusinessLayer;

import BusinessLayer.Units.UnitVisitor;
import utils.Position;

public abstract class Tile
implements TileVisited {
    private char Tile;
    protected Position position;

    public Tile(char tile) {
        this.Tile = tile;
    }

    public void SwapPosition(Tile tile) {
        Position temp = this.position;
        this.position = tile.position;
        tile.position = temp;
    }

    public Position GetPosition() {
        return this.position;
    }

    public String toString() {
        return "" + this.Tile;
    }

    public Tile initialize(Position p) {
        this.position = p;
        return this;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public abstract void accept(UnitVisitor var1);

    public void setTile(char tile) {
        this.Tile = tile;
    }
}

