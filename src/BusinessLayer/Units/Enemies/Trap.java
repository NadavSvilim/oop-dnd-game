package BusinessLayer.Units.Enemies;

public class Trap
extends Enemy {
    private int visibilityTime;
    private int invisibilityTime;
    private int tickCount;
    private boolean isVisibility;
    private static final int RANGE = 2;

    public Trap(String name, char tile, int maxHP, int attkPoints, int defPoints, int expPoints, int visibilityTime, int invisibilityTime) {
        super(name, maxHP, attkPoints, defPoints, expPoints, tile);
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        this.tickCount = 0;
        this.isVisibility = true;
    }

    @Override
    public void Tick() {
        this.updateVisibility();
        if (this.position.Range(this.playerPositionCallback.getPlayerPosition()) < RANGE) {
            this.interact(this.adjacentTilesCallback.getAdjTile(this.playerPositionCallback.getPlayerPosition(), 's'));
        }
    }

    private void updateVisibility() {
        ++this.tickCount;
        if (this.isVisibility && this.tickCount == this.visibilityTime || !this.isVisibility && this.tickCount == this.invisibilityTime) {
            this.isVisibility = !this.isVisibility;
            this.tickCount = 0;
        }
    }

    @Override
    public String toString() {
        if (this.isVisibility) {
            return super.toString();
        }
        return ".";
    }
}

