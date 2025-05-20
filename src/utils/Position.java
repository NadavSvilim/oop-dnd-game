package utils;

public class Position implements Comparable<Position>
{
    private int x, y;

    public Position()
    {
        this.x = 0;
        this.y = 0;
    }
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    public double Range(Position other)
    {
        return Math.sqrt(Math.pow((other.x - this.x), 2) + Math.pow(other.y - this.y, 2));
    }
    public int DX(Position other)
    {
        return this.x - other.x;
    }
    public int DY(Position other)
    {
        return this.y - other.y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int compareTo(Position p){
        int xDiff = this.x - p.x;
        int yDiff = this.y - p.y;
        if (yDiff == 0){
            if (xDiff == 0)
                return 0;
            return xDiff;
        }
        return yDiff;  
    }
    public boolean equal(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position objPos = (Position) obj;
        return this.compareTo(objPos) == 0;
    }
}
