package MFES.gui;

public interface Menu {
    void show();
    Menu action();
    Menu input(String input);
    void destroy();
}