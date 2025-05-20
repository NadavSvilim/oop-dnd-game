package BusinessLayer.Abilities;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import java.util.List;
import java.util.Random;
import utils.Resource;

public class AvengersShield
implements Ability {
    private final int RANGE = 3;
    private Resource Cooldown;
    private Resource Health;
    private Random rand;

    public AvengersShield(Resource Cooldown, Resource Health) {
        this.Cooldown = Cooldown;
        this.Health = Health;
        this.rand = new Random();
    }

    @Override
    public void cast(Player player) {
        if (this.Cooldown.getCurrent() == 0) {
            this.Cooldown.Increase();
            List<Enemy> rangedEnemies = player.WithinRange(RANGE);
            if (rangedEnemies.size() == 0) {
                return;
            }
            Enemy chosenEnemy = rangedEnemies.get(this.rand.nextInt(0, rangedEnemies.size()));
            chosenEnemy.getHealth().Decrease(this.Health.getMax() / 10);
            player.getMassegeCallback().send(player.getName() + "'s Avengers Shield has hit " + chosenEnemy.getName() + " for " + this.Health.getMax() / 10 + " damage!\n" + chosenEnemy.getName() + " has " + chosenEnemy.getHealth().toString() + " health.");
            player.getMassegeCallback().send(player.getName() + " has gained " + 10 * player.GetDefPoints() + " health from Avengers Shield!");
            if (chosenEnemy.getHealth().getCurrent() == 0) {
                chosenEnemy.onDeath(chosenEnemy);
            }
            this.Health.Increase(10 * player.GetDefPoints());
        }
    }
}

