package GameManagers;

import initializers.LevelInitializer;

public class Level {
    protected Board board;
    protected int levelNumber;

    public Level(int levelNumber){
        this.levelNumber = levelNumber;
        initBoard();
    }

    public void initBoard(){
        this.board = LevelInitializer.getInstance().initBoard("levels_dir\\level" + levelNumber + ".txt");
    }

    public Board getBoard(){
        return board;
    }

    public boolean isFinished() {
        return board.getEnemies().isEmpty();
    }

    public int getLevelNum() {
        return levelNumber;
    }
}
