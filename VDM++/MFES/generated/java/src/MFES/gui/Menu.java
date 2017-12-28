package MFES.gui;

import java.util.Scanner;

public abstract class Menu {
    protected Scanner reader;

    public Menu(Scanner reader) {
        this.reader = reader;
    }

    public abstract void show();
    public abstract Menu action();
    public abstract Menu input(String input);
    public abstract void destroy();
}