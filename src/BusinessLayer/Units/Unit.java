package BusinessLayer.Units;

import BusinessLayer.Empty;
import BusinessLayer.Observer;
import BusinessLayer.Tile;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import BusinessLayer.Wall;
import utils.Callbacks.AdjacentTilesCallback;
import utils.Callbacks.BoardSwapCallback;
import utils.Callbacks.MassageCallback;
import utils.RNG;
import utils.Resource;

public abstract class Unit
extends Tile
implements UnitVisitor,
Observer {
    protected String name;
    protected Resource health;
    protected Integer attkPoints;
    protected Integer defPoints;
    protected AdjacentTilesCallback adjacentTilesCallback;
    protected BoardSwapCallback boardSwapCallback;
    protected MassageCallback massageCallback;

    public Unit(String name, int maxHP, int attkPoints, int defPoints, char tile) {
        super(tile);
        this.name = name;
        this.health = new Resource(maxHP, "Health");
        this.attkPoints = attkPoints;
        this.defPoints = defPoints;
    }

    public void Attack(Unit defender) {
        int attack = RNG.roll(0, this.attkPoints);
        int damage = this.battle(defender, attack);
        this.massageCallback.send(this.name + " attacked " + defender.name + " for " + damage + " damage!\n" + defender.name + " has " + defender.health.toString() + " health.");
    }

    public int battle(Unit defender, int attack) {
        int def = RNG.roll(0, defender.defPoints);
        int damage = attack - def;
        if (damage < 0) {
            damage = 0;
        }
        defender.health.Decrease(damage);
        return damage;
    }

    public int GetDefPoints() {
        return this.defPoints;
    }

    public int GetAttackPoints() {
        return this.attkPoints;
    }

    public Resource getHealth() {
        return this.health;
    }

    @Override
    public void visit(Wall wall) {
    }

    @Override
    public void visit(Empty empty) {
        this.boardSwapCallback.swap(this, empty);
    }

    @Override
    public abstract void visit(Enemy var1);

    @Override
    public abstract void visit(Player var1);

    @Override
    public abstract void Tick();

    public void interact(Tile tile) {
        tile.accept(this);
    }

    public String getName() {
        return this.name;
    }

    public String description() {
        return "Name: " + this.name + "\n" + this.health.toString() + "\nAttack: " + this.attkPoints + "\nDefense: " + this.defPoints;
    }

    public void setAdjacentTilesCallback(AdjacentTilesCallback adjacentTilesCallback) {
        this.adjacentTilesCallback = adjacentTilesCallback;
    }

    public void setBoardSwapCallback(BoardSwapCallback boardSwapCallback) {
        this.boardSwapCallback = boardSwapCallback;
    }

    public void setMassageCallback(MassageCallback massageCallback) {
        this.massageCallback = massageCallback;
    }

    public MassageCallback getMassegeCallback() {
        return this.massageCallback;
    }
}

