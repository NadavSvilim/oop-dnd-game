package BusinessLayer.Abilities;

import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import java.util.List;
import java.util.Random;
import utils.MutableInt;
import utils.Resource;

public class Blizzard
implements Ability {
    private Resource Mana;
    private int Range;
    private int Cost;
    private MutableInt Power;
    private int hitCount;
    private Random rand;

    public Blizzard(Resource Mana, MutableInt spellPower, int hitCount, int Cost, int Range) {
        this.Mana = Mana;
        this.Power = spellPower;
        this.hitCount = hitCount;
        this.Cost = Cost;
        this.Range = Range;
        this.rand = new Random();
    }

    @Override
    public void cast(Player player) {
        if (this.Mana.getCurrent() >= this.Cost) {
            this.Mana.Decrease(this.Cost);
            List<Enemy> rangedEnemies = player.WithinRange(this.Range);
            if (rangedEnemies.size() > 0) {
                for (int i2 = 0; i2 < this.hitCount; ++i2) {
                    Enemy chosenEnemy = rangedEnemies.get(this.rand.nextInt(0, rangedEnemies.size()));
                    int damage = player.battle(chosenEnemy, this.Power.getValue());
                    player.getMassegeCallback().send(player.getName() + "'s' Blizzard has hit " + chosenEnemy.getName() + " for " + damage + " damage!\n" + chosenEnemy.getName() + " has " + chosenEnemy.getHealth().toString() + " health.");
                    if (chosenEnemy.getHealth().getCurrent() != 0) continue;
                    chosenEnemy.onDeath(chosenEnemy);
                }
            }
        }
    }
}

