package BusinessLayer.Units.Enemies;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import utils.Position;

public class Monster
extends Enemy {
    private int visionRange;
    private static final List<Character> DIR_CHARACTERS = Arrays.asList(Character.valueOf('u'), Character.valueOf('d'), Character.valueOf('l'), Character.valueOf('r'));

    public Monster(String name, char tile, int maxHP, int attkPoints, int defPoints, int visionRange, int expPoints) {
        super(name, maxHP, attkPoints, defPoints, expPoints, tile);
        this.visionRange = visionRange;
    }

    public void Move() {
        Position playerPosition = this.playerPositionCallback.getPlayerPosition();
        if (this.position.Range(playerPosition) < (double)this.visionRange) {
            int dx = this.position.DX(playerPosition);
            int dy = this.position.DY(playerPosition);
            if (Math.abs(dx) > Math.abs(dy)) {
                if (dx > 0) {
                    this.interact(this.adjacentTilesCallback.getAdjTile(this.position, 'l'));
                } else {
                    this.interact(this.adjacentTilesCallback.getAdjTile(this.position, 'r'));
                }
            } else if (dy > 0) {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.position, 'u'));
            } else {
                this.interact(this.adjacentTilesCallback.getAdjTile(this.position, 'd'));
            }
        } else {
            Random random = new Random();
            int randomIndex = random.nextInt(DIR_CHARACTERS.size());
            char direction = DIR_CHARACTERS.get(randomIndex).charValue();
            this.interact(this.adjacentTilesCallback.getAdjTile(this.position, direction));
        }
    }

    @Override
    public void Tick() {
        this.Move();
    }
}

