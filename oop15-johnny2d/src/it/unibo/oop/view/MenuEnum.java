package it.unibo.oop.view;

public enum MenuEnum {

    LAUNCHER(new Launcher()),
    PAUSE(new PauseMenu()),
    OPTIONS(new OptionsMenu());
    
    private final MenuInterface menu;
    
    private MenuEnum(final MenuInterface menu) {
        this.menu = menu;
    }
    
    public MenuInterface getView() {
        return this.menu;
    }
}
