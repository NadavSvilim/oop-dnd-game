package utils.Callbacks;

import BusinessLayer.Tile;
import utils.Position;

public interface AdjacentTilesCallback {
    public Tile getAdjTile(Position p, char direction);
}
