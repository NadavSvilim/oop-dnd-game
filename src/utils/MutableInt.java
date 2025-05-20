package utils;

public class MutableInt {
    private int value;

    public MutableInt(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }
    
    public void add(int value) {
        this.value += value;
    }

    public void subtract(int value) {
        this.value -= value;
    }

    public String toString() {
        return Integer.toString(value);
    }
}
