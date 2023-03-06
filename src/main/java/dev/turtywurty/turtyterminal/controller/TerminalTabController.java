package dev.turtywurty.turtyterminal.controller;

import dev.turtywurty.turtyterminal.view.menu.FileMenu;
import dev.turtywurty.turtyterminal.view.tab.TerminalTab;
import javafx.scene.control.TabPane;

public class TerminalTabController {
    private FileMenu fileMenu;
    private TabPane tabPane;


    public void control(FileMenu fileMenu, TabPane tabPane) {
        if (this.fileMenu != null || this.tabPane != null || fileMenu == null || tabPane == null) return;
        this.fileMenu = fileMenu;
        this.tabPane = tabPane;

        this.fileMenu.getNewMenu().getItems().forEach(item -> {
            if (item instanceof FileMenu.NewTerminalMenuItem menuItem) {
                menuItem.setOnAction(event -> this.tabPane.getTabs().add(new TerminalTab(menuItem.getType()).getTab()));
            }
        });
    }
}
