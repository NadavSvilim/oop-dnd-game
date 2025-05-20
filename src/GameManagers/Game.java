package GameManagers;

import java.util.ArrayList;
import java.util.List;

import BusinessLayer.Observable;
import BusinessLayer.Observer;
import BusinessLayer.Tile;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import View.View;
import initializers.LevelInitializer;
import utils.Position;
import utils.Callbacks.AdjacentTilesCallback;
import utils.Callbacks.BoardSwapCallback;
import utils.Callbacks.EnemiesCallback;
import utils.Callbacks.EnemyDeathCallback;
import utils.Callbacks.MassageCallback;
import utils.Callbacks.PlayerDeathCallback;
import utils.Callbacks.PlayerPositionCallback;

public class Game implements EnemyDeathCallback, PlayerDeathCallback, EnemiesCallback, PlayerPositionCallback, AdjacentTilesCallback, BoardSwapCallback, Observable {
    public enum GameStatus {RUNNING, WON, LOST};
    private Level curLevel;
    private GameStatus status;
    private static Game instance;
    private Player player;
    private List<Observer> observers;
    private MassageCallback messageCallback;

    private Game(View view){
        observers = new ArrayList<Observer>();
        status = GameStatus.RUNNING;
        messageCallback = view.getCallback();
    }

    public static Game getInstance(View view){
        if(instance == null){
            instance = new Game(view);
        }
        return instance;
    }

    public void choosePlayer(int playerType){
        player = LevelInitializer.getInstance().initPlayer(playerType);
        addObserver(player);
    }

    public void onEnemyDeath(Enemy e){
        player.gainExp(e.getExp());
        curLevel.getBoard().removeEnemy(e);
        observers.remove(e);
    }

    public void onPlayerDeath(){
        player.setTile('X');
        stopGame();
    }

    private void stopGame() {
        messageCallback.send(getBoard().toString());
        status = GameStatus.LOST;
        // needs to put and X on the Tile of the player
    }

    public Position getPlayerPosition(){
        return player.getPosition();
    }

    public List<Enemy> getEnemies(){
        return curLevel.getBoard().getEnemies();
    }

    public Tile getAdjTile(Position p, char direction){
        return curLevel.getBoard().getAdjTile(p, direction);
    }    
    public void addObserver(Observer o){
        observers.add(o);
    }
    public void removeObserver(Observer o){
        observers.remove(o);
    }
    public void tickObservers(){

        for(Observer o : observers){
            o.Tick();
        }
    }
    public boolean isGameRunning(){
        return status == GameStatus.RUNNING;
    }

    public void start() {
        curLevel = new Level(1);
        for(Enemy e : curLevel.getBoard().getEnemies()){
            addObserver(e);
        }
    }

    public boolean isLevelFinished(){
        return curLevel.isFinished();
    }

    public GameStatus getStatus(){
        return status;
    }

    public Object getBoard() {
        return curLevel.getBoard();
    }

    public String PlayerDescription() {
        return player.description();
    }

    public void enemiesTurn() {
        tickObservers();
    }

    public void playerAction(char action) {
        player.action(action);
    }

    public void NextLvl() {
        if(curLevel.getLevelNum() == 4){
            status = GameStatus.WON;
            return;
        }
        curLevel = new Level(curLevel.getLevelNum() + 1);
        for(Enemy e : curLevel.getBoard().getEnemies()){
            addObserver(e);
        }
    }

    public int getLevelNum() {
        return curLevel.getLevelNum();
    }

    public void swap(Tile tile1, Tile tile2) {
        curLevel.getBoard().swap(tile1, tile2);
    }

    public MassageCallback getMassageCallback() {
        return messageCallback;
    }
}
