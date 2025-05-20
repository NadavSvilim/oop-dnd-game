package BusinessLayer.Units.PlayerClasses;

import BusinessLayer.Abilities.Blizzard;
import utils.MutableInt;
import utils.Resource;

public class Mage
extends Player {
    private MutableInt spellPower;
    private Resource mana;
    private static final int MAGE_MANA_MODIFIER = 25;
    private static final int MAGE_SPELL_POWER_MODIFIER = 10;
    private static final int MAGE_MANA_PARTIAL_INCREASE = 4;

    public Mage(String name, int maxHP, int attkPoints, int defPoints, int MaxMana, Integer spellPower, int BlizzardHitCount, int BlizzardRange, int BlizzardCost) {
        super(name, maxHP, attkPoints, defPoints);
        this.mana = new Resource(MaxMana, "Mana");
        this.mana.Decrease();
        this.mana.Increase(this.mana.getMax() / MAGE_MANA_PARTIAL_INCREASE);
        this.spellPower = new MutableInt(spellPower);
        this.specialAbility = new Blizzard(this.mana, this.spellPower, BlizzardHitCount, BlizzardCost, BlizzardRange);
    }

    @Override
    public void levelUp() {
        super.levelUp();
        this.mana.setMax(this.mana.getMax() + MAGE_MANA_MODIFIER * this.level);
        this.spellPower.add(MAGE_SPELL_POWER_MODIFIER * this.level);
        this.mana.Increase(this.mana.getMax() / MAGE_MANA_PARTIAL_INCREASE);
    }

    @Override
    public void Tick() {
        this.mana.Increase(this.level);
    }

    @Override
    public String description() {
        return super.description() + "\n" + this.mana.toString() + "\nSpell Power: " + this.spellPower;
    }
}

