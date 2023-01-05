package src.main.java.controller;
import src.main.java.view.VueMain;
import src.main.java.model.Bot;

public class ControlBot {
    protected VueMain main;
    protected Bot b;

    public ControlBot(VueMain main, Bot b) {
        this.main=main;
        this.b=b;
    }

    public VueMain getMain() {
        return main;
    }

    public Bot getB() {
        return b;
    }
}
