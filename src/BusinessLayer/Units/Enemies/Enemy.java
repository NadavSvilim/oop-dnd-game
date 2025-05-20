package BusinessLayer.Units.Enemies;

import BusinessLayer.Units.PlayerClasses.Player;
import BusinessLayer.Units.Unit;
import BusinessLayer.Units.UnitVisitor;
import utils.Callbacks.AdjacentTilesCallback;
import utils.Callbacks.EnemyDeathCallback;
import utils.Callbacks.PlayerPositionCallback;

public abstract class Enemy
extends Unit {
    protected int expPoints;
    private EnemyDeathCallback deathCallback;
    protected PlayerPositionCallback playerPositionCallback;

    public Enemy(String name, int maxHP, int attkPoints, int defPoints, int expPoints, char tile) {
        super(name, maxHP, attkPoints, defPoints, tile);
        this.expPoints = expPoints;
    }

    public int getExp() {
        return this.expPoints;
    }

    @Override
    public void visit(Player player) {
        super.Attack(player);
        if (player.getHealth().getCurrent() == 0) {
            player.onDeath();
        }
    }

    @Override
    public void visit(Enemy enemy) {
    }

    public void onDeath(Enemy e) {
        this.deathCallback.onEnemyDeath(this);
    }

    @Override
    public void accept(UnitVisitor v) {
        v.visit(this);
    }

    public void SetDeathCallback(EnemyDeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }

    public void SetPlayerPositionCallback(PlayerPositionCallback playerPositionCallback2) {
        this.playerPositionCallback = playerPositionCallback2;
    }

    @Override
    public void setAdjacentTilesCallback(AdjacentTilesCallback adjacentTilesCallback) {
        this.adjacentTilesCallback = adjacentTilesCallback;
    }
}

