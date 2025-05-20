package utils;

public class Resource {
    protected String name;
    private int max;
    private int current;

    public Resource(int max, String name){
        this.name = name;
        this.max = max;
        this.current = max;
    }
    public void Increase(){
        current = max;
    }
    public void Increase(int amount){
        if(amount >= 0)
            current = Math.min(current + amount, max);
    }
    public void Decrease(){
        current = 0;
    }
    public void Decrease(int amount){
        if(amount >= 0)
            current = Math.max(current - amount, 0);
    }
    public String toString(){
        return name + ": " + current + "/" + max;
    }
    public void setMax(int max){
        this.max = max;
    }
    public int getMax(){
        return max;
    }
    public int getCurrent(){
        return current;
    }
}
