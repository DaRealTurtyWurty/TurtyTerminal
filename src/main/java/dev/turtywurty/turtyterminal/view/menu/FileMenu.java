package dev.turtywurty.turtyterminal.view.menu;

import dev.turtywurty.turtyterminal.view.tab.TerminalTab;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class FileMenu {
    private final Menu menu;
    private final Menu newMenu;

    public FileMenu() {
        this.menu = new Menu("File");
        this.newMenu = new Menu("New");
        this.newMenu.getItems().add(new NewTerminalMenuItem(TerminalTab.TerminalType.CMD));
        this.newMenu.getItems().add(new NewTerminalMenuItem(TerminalTab.TerminalType.POWERSHELL));
        this.newMenu.getItems().add(new NewTerminalMenuItem(TerminalTab.TerminalType.WSL));
        this.menu.getItems().add(this.newMenu);
    }

    public Menu getMenu() {
        return this.menu;
    }

    public Menu getNewMenu() {
        return this.newMenu;
    }

    public static class NewTerminalMenuItem extends MenuItem {
        private final TerminalTab.TerminalType type;

        public NewTerminalMenuItem(TerminalTab.TerminalType type) {
            this.type = type;
            this.setText(type.getName());
        }

        public TerminalTab.TerminalType getType() {
            return this.type;
        }
    }
}
