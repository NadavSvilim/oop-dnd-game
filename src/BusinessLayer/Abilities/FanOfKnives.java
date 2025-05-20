package BusinessLayer.Abilities;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import java.util.List;
import utils.Resource;

public class FanOfKnives
implements Ability {
    private Resource Energy;
    private final int RANGE = 2;
    private int Cost;

    public FanOfKnives(Resource Energy, int Cost) {
        this.Energy = Energy;
        this.Cost = Cost;
    }

    @Override
    public void cast(Player player) {
        if (this.Energy.getCurrent() >= this.Cost) {
            List<Enemy> rangedEnemies = player.WithinRange(RANGE);
            for (int i2 = 0; i2 < rangedEnemies.size(); ++i2) {
                int damage = player.battle(rangedEnemies.get(i2), player.GetAttackPoints());
                player.getMassegeCallback().send(player.getName() + "'s Fan Of Knives has hit " + rangedEnemies.get(i2).getName() + " for " + damage + " damage!\n" + rangedEnemies.get(i2).getName() + " has " + rangedEnemies.get(i2).getHealth().toString() + " health.");
                if (rangedEnemies.get(i2).getHealth().getCurrent() != 0) continue;
                rangedEnemies.get(i2).onDeath(rangedEnemies.get(i2));
            }
            this.Energy.Decrease(this.Cost);
        }
    }
}

