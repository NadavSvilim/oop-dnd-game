package View;

import utils.Callbacks.MassageCallback;

public abstract class View {

    public abstract void display(String msg);

    public MassageCallback getCallback(){
        return this::display;
    }
}
