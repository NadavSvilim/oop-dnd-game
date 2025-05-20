package BusinessLayer.Units.PlayerClasses;

import BusinessLayer.Abilities.Ability;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.Unit;
import BusinessLayer.Units.UnitVisitor;
import java.util.ArrayList;
import java.util.List;
import utils.Callbacks.EnemiesCallback;
import utils.Callbacks.PlayerDeathCallback;

public abstract class Player
extends Unit {
    protected int exp = 0;
    protected int level = 1;
    protected Ability specialAbility;
    protected EnemiesCallback enemies;
    protected PlayerDeathCallback deathCallback;
    private static final int LEVEL_UP_EXP_MODIFIER = 50;
    private static final int LEVEL_UP_HP_MODIFIER = 10;
    private static final int LEVEL_UP_ATTK_MODIFIER = 4;
    private static final int LEVEL_UP_DEF_MODIFIER = 1;
    private static final char PLAYER_TILE = '@';

    public Player(String name, int maxHP, int attkPoints, int defPoints) {
        super(name, maxHP, attkPoints, defPoints, PLAYER_TILE);
    }

    public void levelUp() {
        this.exp -= this.level * 50;
        ++this.level;
        this.health.setMax(this.health.getMax() + LEVEL_UP_HP_MODIFIER * this.level);
        this.health.Increase();
        this.attkPoints = this.attkPoints + LEVEL_UP_ATTK_MODIFIER * this.level;
        this.defPoints = this.defPoints + LEVEL_UP_DEF_MODIFIER * this.level;
        this.massageCallback.send(this.name + " has leveled up! (Level " + this.level + ")");
    }

    public void castSpecialAbility() {
        this.specialAbility.cast(this);
    }

    public void gainExp(int amount) {
        this.exp += amount;
        while (this.exp >= this.level * LEVEL_UP_EXP_MODIFIER) {
            this.levelUp();
        }
    }

    @Override
    public void visit(Enemy enemy) {
        this.Attack(enemy);
        if (enemy.getHealth().getCurrent() == 0) {
            this.boardSwapCallback.swap(this, enemy);
            enemy.onDeath(enemy);
        }
    }

    @Override
    public void visit(Player player) {
    }

    public void onDeath() {
        this.deathCallback.onPlayerDeath();
    }

    public List<Enemy> WithinRange(int range) {
        List<Enemy> enemiesList = this.enemies.getEnemies();
        ArrayList<Enemy> toReturn = new ArrayList<Enemy>();
        for (int i2 = 0; i2 < enemiesList.size(); ++i2) {
            double distance = enemiesList.get(i2).GetPosition().Range(this.GetPosition());
            if (!(distance < (double)range)) continue;
            toReturn.add(enemiesList.get(i2));
        }
        return toReturn;
    }

    @Override
    public void accept(UnitVisitor v) {
        v.visit(this);
    }

    public void SetDeathCallback(PlayerDeathCallback deathCallback) {
        this.deathCallback = deathCallback;
    }

    public void SetEnemiesCallback(EnemiesCallback callback) {
        this.enemies = callback;
    }

    public void action(char action) {
        switch (action) {
            case 'w': {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.GetPosition(), 'u'));
                break;
            }
            case 'a': {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.GetPosition(), 'l'));
                break;
            }
            case 's': {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.GetPosition(), 'd'));
                break;
            }
            case 'd': {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.GetPosition(), 'r'));
                break;
            }
            case 'e': {
                this.castSpecialAbility();
                break;
            }
        }
    }

    @Override
    public String description() {
        return super.description() + "\nLevel: " + this.level + "\nExp: " + this.exp + "/" + this.level * 50;
    }
}

