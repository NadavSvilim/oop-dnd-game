package BusinessLayer.Units.PlayerClasses;

import BusinessLayer.Abilities.Shoot;
import utils.MutableInt;

public class Hunter
extends Player {
    int range;
    MutableInt arrowsCount;
    int tickCount;
    private static final int HUNTER_ATK_MODIFIER = 2;
    private static final int HUNTER_DEF_MODIFIER = 1;
    private static final int HUNTER_ARROWS_MODIFIER = 10;

    public Hunter(String name, int maxHP, int attkPoints, int defPoints, int range) {
        super(name, maxHP, attkPoints, defPoints);
        this.range = range;
        this.arrowsCount = new MutableInt(10 * this.level);
        this.tickCount = 0;
        this.specialAbility = new Shoot(this.arrowsCount, range);
    }

    @Override
    public void Tick() {
        ++this.tickCount;
        if (this.tickCount == 10) {
            this.arrowsCount.increment();
            this.tickCount = 0;
        }
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.arrowsCount.add(HUNTER_ARROWS_MODIFIER * this.level);
        this.attkPoints = this.attkPoints + HUNTER_ATK_MODIFIER * this.level;
        this.defPoints = this.defPoints + HUNTER_DEF_MODIFIER * this.level;
    }

    @Override
    public String description() {
        return super.description() + "\nArrows: " + this.arrowsCount.toString();
    }
}

