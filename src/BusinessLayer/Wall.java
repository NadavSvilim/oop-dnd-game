package BusinessLayer;

import BusinessLayer.Units.UnitVisitor;

public class Wall
extends Tile {
    public static final char WALL_TILE = '#';

    public Wall() {
        super('#');
    }

    @Override
    public void accept(UnitVisitor v) {
        v.visit(this);
    }
}

