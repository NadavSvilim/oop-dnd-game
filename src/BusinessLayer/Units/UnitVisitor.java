package BusinessLayer.Units;

import BusinessLayer.Empty;
import BusinessLayer.Units.Enemies.Enemy;
import BusinessLayer.Units.PlayerClasses.Player;
import BusinessLayer.Wall;

public interface UnitVisitor {
    public void visit(Enemy var1);

    public void visit(Player var1);

    public void visit(Wall var1);

    public void visit(Empty var1);
}

