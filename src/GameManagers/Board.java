package GameManagers;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import utils.Position;
import BusinessLayer.Tile;
import BusinessLayer.Units.Enemies.Enemy;
import initializers.TileInitializer;

public class Board{
    private TreeMap<Position ,Tile> map;
    private List<Enemy> enemies;
    private final int width;

    public Board(List<Tile> tiles, List<Enemy> enemies, int width){
        this.enemies = enemies;
        this.width = width;
        this.map = new TreeMap<Position ,Tile>();
        for(Tile tile : tiles){
            map.put(tile.getPosition(), tile);
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<Position, Tile> entry : map.entrySet()){
            sb.append(entry.getValue().toString());
            if(entry.getKey().getX() == width-1){
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Tile getAdjTile(Position p, char direction) {
        int x = p.getX();
        int y = p.getY();
        switch(direction){
            case 'd':
                return map.get(new Position(x, y+1));
            case 'l':
                return map.get(new Position(x-1, y));
            case 'u':
                return map.get(new Position(x, y-1));
            case 'r':
                return map.get(new Position(x+1, y));
            case 's':
                return map.get(new Position(x, y));
            default:
                return null;
        }
    }

    public void removeEnemy(Enemy e) {
        enemies.remove(e);
        map.remove(e.getPosition());
        map.put(e.GetPosition(), TileInitializer.getInstance().produceEmpty(e.GetPosition()));
    }

    public void swap(Tile tile1, Tile tile2) {
        map.remove(tile1.getPosition());
        map.remove(tile2.getPosition());
        tile1.SwapPosition(tile2);
        map.put(tile1.getPosition(), tile1);
        map.put(tile2.getPosition(), tile2);
    }
}
