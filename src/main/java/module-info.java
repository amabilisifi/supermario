module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires java.datatransfer;
    requires java.desktop;
    requires earcut4j;
    requires javafx.swing;

    exports project;
    exports project.sceneControllers;
    opens project.sceneControllers to javafx.fxml;
    exports project.gameObjects;
    opens project.gameObjects to com.fasterxml.jackson.databind;
    exports project.characters;
    opens project.characters to com.fasterxml.jackson.databind;
    opens project to javafx.fxml, javafx.graphics;
    exports project.managers;
    opens project.managers to javafx.fxml, javafx.graphics;
    exports project.managers.Page;
    opens project.managers.Page to javafx.fxml, javafx.graphics;
    exports project.gameStuff;
    opens project.gameStuff to javafx.fxml, javafx.graphics;
}