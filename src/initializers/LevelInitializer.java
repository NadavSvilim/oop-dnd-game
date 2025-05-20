package initializers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import BusinessLayer.Tile;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import GameManagers.Board;
import utils.Position;

public class LevelInitializer {
    private TileInitializer TI;
    private int PlayerID;
    private static LevelInitializer instance = null;

    public static LevelInitializer getInstance(){
        if(instance == null){
            instance = new LevelInitializer();
        }
        return instance;
    }

    public Player initPlayer(int ID){
        PlayerID = ID;
        return TileInitializer.getInstance().producePlayer(ID, null);
    }

    private LevelInitializer(){
        TI = TileInitializer.getInstance();
    }

    public Board initBoard(String levelPath){
        List<String> lines;
        try{
            lines = Files.readAllLines(Paths.get(levelPath));
        }
        catch (IOException e){
            throw new RuntimeException("Could not read level file: " + e.getMessage());
        }
        TreeMap<Position ,Tile> map = new TreeMap<Position ,Tile>();
        List<Enemy> enemies = new ArrayList<Enemy>();
        int y = 0;
        for(String line : lines){
            int x = 0;
            for(char c : line.toCharArray()){
                Position p = new Position(x, y);
                switch(c){
                    case '@':
                        map.put(p, TI.producePlayer(PlayerID, p));
                        break;
                    case '#':
                        map.put(p, TI.produceWall(p));
                        break;
                    case '.':
                        map.put(p, TI.produceEmpty(p));
                        break;
                    default:
                        int id = TI.getIdByChar(c);
                        Enemy enemy = TI.produceEnemy(id, p);
                        map.put(p, enemy);
                        enemies.add(enemy);
                        break;    
                }
                x++;
            }
            y++;
        }
        return new Board(new ArrayList<Tile>(map.values()), enemies, lines.get(0).length());
    }
}
