package initializers;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

import BusinessLayer.Empty;
import BusinessLayer.Tile;
import BusinessLayer.Wall;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Enemies.Monster;
import BusinessLayer.Units.Enemies.Trap;
import BusinessLayer.Units.PlayerClasses.Hunter;
import BusinessLayer.Units.PlayerClasses.Mage;
import BusinessLayer.Units.PlayerClasses.Player;
import BusinessLayer.Units.PlayerClasses.Rouge;
import BusinessLayer.Units.PlayerClasses.Warrior;
import GameManagers.Game;
import utils.Position;
import utils.Callbacks.MassageCallback;

public class TileInitializer {
    private Player p;
    private MassageCallback massageCallback;

    private static final List<Supplier<Player>> playerTypes = Arrays.asList(
        () -> new Warrior("Jon Snow", 300, 30, 4, 3),
        () -> new Warrior("The Hound",400 , 20 , 6, 5),
        () -> new Mage("Melisandre", 100, 5, 1, 300, 15, 5, 6, 30),
        () -> new Mage("Thoros of Myr", 250, 25, 4, 150, 20, 3, 4, 20),
        () -> new Rouge("Arya Stark", 150, 40, 2, 20),
        () -> new Rouge("Bronn", 250, 35, 3, 50),
        () -> new Hunter("Ygritte", 220, 30, 2, 6)
    );
    private static final List<Supplier<Enemy>> enemyTypes = Arrays.asList(
        () -> new Monster("Lannister Solider", 's', 80, 8, 3, 3, 25),
        () -> new Monster("Lannister Commander", 'k', 200, 14, 8, 4, 50),
        () -> new Monster("Queen's Guard", 'q', 400, 20, 15, 5, 100),
        () -> new Monster("Wight", 'z', 600, 30, 15, 3, 100),
        () -> new Monster("Bear-Wright", 'b', 1000, 75, 30, 4, 250),
        () -> new Monster("Giant-Wight", 'g', 1500, 100, 40, 5, 500),
        () -> new Monster("White Walker", 'w', 2000, 150, 50, 6, 1000),
        () -> new Monster("The Mountain", 'M', 1000, 60, 25, 6, 500),
        () -> new Monster("Queen Cersei", 'C', 100, 10, 10, 1, 1000),
        () -> new Monster("Nights King", 'K', 5000, 300, 150, 8, 5000),
        () -> new Trap("Bonus Trap", 'B', 1, 1, 1, 250, 1, 5),
        () -> new Trap("Queens Trap", 'Q', 250, 50, 10, 100, 3, 7),
        () -> new Trap("Death Trap", 'D', 500, 100, 20, 250, 1, 10)
    );

    private static TileInitializer instance = null;

    private TileInitializer(){
        this.massageCallback = Game.getInstance(null).getMassageCallback();
    }

    public static TileInitializer getInstance(){
        if(instance == null){
            instance = new TileInitializer();
        }
        return instance;
    }


    public Player producePlayer(int ID, Position position){
        if(this.p == null){
            Supplier<Player> supplier = playerTypes.get(ID);
            this.p = supplier.get();
            p.SetDeathCallback(Game.getInstance(null));
            p.SetEnemiesCallback(Game.getInstance(null));
            p.setAdjacentTilesCallback(Game.getInstance(null));
            p.setBoardSwapCallback(Game.getInstance(null));
            p.setMassageCallback(massageCallback);
        }
        this.p.initialize(position);
        return this.p;
    }

    public Enemy produceEnemy(int ID, Position position){
        Supplier<Enemy> supplier = enemyTypes.get(ID);
        Enemy enemy = supplier.get();
        enemy.initialize(position);
        enemy.SetDeathCallback(Game.getInstance(null));
        enemy.SetPlayerPositionCallback(Game.getInstance(null));
        enemy.setAdjacentTilesCallback(Game.getInstance(null));
        enemy.setBoardSwapCallback(Game.getInstance(null));
        enemy.setMassageCallback(massageCallback);
        return enemy;
    }

    public Tile produceEmpty(Position position){
        return new Empty().initialize(position);
    }

    public Tile produceWall(Position position){
        return new Wall().initialize(position);
    }

    public int getIdByChar(char c) {
        switch (c) {
            case 's':
                return 0;
            case 'k':
                return 1;
            case 'q':
                return 2;
            case 'z':
                return 3;
            case 'b':   
                return 4;
            case 'g':   
                return 5;
            case 'w':   
                return 6;
            case 'M':
                return 7;
            case 'C':
                return 8;
            case 'K':   
                return 9;
            case 'B':
                return 10;
            case 'Q':
                return 11;
            case 'D':   
                return 12;
            default:
                return -1;
        }
    }


    
}