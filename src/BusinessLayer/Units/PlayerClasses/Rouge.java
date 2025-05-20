package BusinessLayer.Units.PlayerClasses;

import BusinessLayer.Abilities.FanOfKnives;
import utils.Resource;

public class Rouge
extends Player {
    private Resource energy = new Resource(BASE_ENERGY, "Energy");
    private static final int ROUGE_ATTK_MODIFIER = 3;
    private static final int BASE_ENERGY = 100;

    public Rouge(String name, int maxHP, int attkPoints, int defPoints, int FOK_cost) {
        super(name, maxHP, attkPoints, defPoints);
        this.specialAbility = new FanOfKnives(this.energy, FOK_cost);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.attkPoints = this.attkPoints + ROUGE_ATTK_MODIFIER * this.level;
        this.energy.Increase();
    }

    @Override
    public void Tick() {
        this.energy.Increase(10);
    }

    @Override
    public String description() {
        return super.description() + "\n" + this.energy.toString();
    }
}

