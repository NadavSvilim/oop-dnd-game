package BusinessLayer.Units.PlayerClasses;

import BusinessLayer.Abilities.AvengersShield;
import utils.Resource;

public class Warrior
extends Player {
    private Resource cooldown;
    private static final int WARRIOR_HP_MODIFIER = 5;
    private static final int WARRIOR_ATTK_MODIFIER = 2;
    private static final int WARRIOR_DEF_MODIFIER = 1;

    public Warrior(String name, int maxHP, int attkPoints, int defPoints, int MaxCooldown) {
        super(name, maxHP, attkPoints, defPoints);
        this.cooldown = new Resource(MaxCooldown, "Cooldown");
        this.specialAbility = new AvengersShield(this.cooldown, this.health);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.health.setMax(this.health.getMax() + WARRIOR_HP_MODIFIER * this.level);
        this.attkPoints = this.attkPoints + WARRIOR_ATTK_MODIFIER * this.level;
        this.defPoints = this.defPoints + WARRIOR_DEF_MODIFIER * this.level;
        this.cooldown.Decrease();
    }

    @Override
    public void Tick() {
        this.cooldown.Decrease(1);
    }

    @Override
    public String description() {
        return super.description() + "\n" + this.cooldown.toString();
    }
}

