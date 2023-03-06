package dev.turtywurty.turtyterminal.view;

import dev.turtywurty.turtyterminal.view.menu.FileMenu;
import dev.turtywurty.turtyterminal.view.tab.TerminalTab;
import javafx.collections.ListChangeListener;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class TerminalView {
    private final BorderPane primaryNode;
    private final MenuBar menuBar;
    private final FileMenu fileMenu;
    private final TabPane tabPane;
    private TerminalTab terminalTab;

    public TerminalView() {
        this.primaryNode = new BorderPane();
        this.menuBar = new MenuBar();
        this.menuBar.prefWidthProperty().bind(this.primaryNode.widthProperty());

        this.fileMenu = new FileMenu();
        this.menuBar.getMenus().addAll(this.fileMenu.getMenu(), new Menu("Edit"), new Menu("View"), new Menu("Help"));
        this.primaryNode.setTop(this.menuBar);

        this.tabPane = new TabPane();
        this.tabPane.getTabs().addListener((ListChangeListener<? super Tab>) changed -> {
            while(changed.next()) {
                changed.getAddedSubList().forEach(tab -> {
                    tab.setOnClosed(event -> {
                        if (this.tabPane.getTabs().size() == 0) {
                            this.terminalTab = new TerminalTab(TerminalTab.TerminalType.CMD);
                            this.tabPane.getTabs().add(this.terminalTab.getTab());
                        }
                    });
                });
            }
        });

        this.terminalTab = new TerminalTab(TerminalTab.TerminalType.CMD);
        this.tabPane.getTabs().add(this.terminalTab.getTab());
        this.primaryNode.setCenter(this.tabPane);
    }

    public Parent getPrimaryNode() {
        return this.primaryNode;
    }

    public FileMenu getFileMenu() {
        return this.fileMenu;
    }

    public TabPane getTerminalTabPane() {
        return this.tabPane;
    }
}
