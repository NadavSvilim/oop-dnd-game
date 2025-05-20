package BusinessLayer;

import BusinessLayer.Units.UnitVisitor;

public class Empty
extends Tile {
    public static final char EMPTY_TILE = '.';

    public Empty() {
        super('.');
    }

    @Override
    public void accept(UnitVisitor v) {
        v.visit(this);
    }
}

