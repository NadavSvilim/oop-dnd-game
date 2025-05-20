package BusinessLayer.Abilities;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import java.util.List;
import utils.MutableInt;

public class Shoot
implements Ability {
    MutableInt arrowsCount;
    int range;

    public Shoot(MutableInt arrowsCount, int range) {
        this.arrowsCount = arrowsCount;
        this.range = range;
    }

    @Override
    public void cast(Player player) {
        List<Enemy> rangedEnemies;
        if (this.arrowsCount.getValue() > 0 && (rangedEnemies = player.WithinRange(this.range)).size() > 0) {
            Enemy chosenEnemy = rangedEnemies.get(0);
            for (Enemy enemy : rangedEnemies) {
                if (!(player.GetPosition().Range(enemy.GetPosition()) < player.GetPosition().Range(chosenEnemy.GetPosition()))) continue;
                chosenEnemy = enemy;
            }
            int damage = player.battle(chosenEnemy, player.GetAttackPoints());
            player.getMassegeCallback().send(player.getName() + " shot " + chosenEnemy.getName() + " for " + damage + " damage!\n" + chosenEnemy.getName() + " has " + chosenEnemy.getHealth().toString() + " health.");
            this.arrowsCount.decrement();
            if (chosenEnemy.getHealth().getCurrent() == 0) {
                chosenEnemy.onDeath(chosenEnemy);
            }
        }
    }
}

