module project {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;
    requires javafx.media;
    requires java.datatransfer;
    requires java.desktop;
    requires earcut4j;

    exports project;
    exports project.sceneControllers;
    opens project.sceneControllers to javafx.fxml;
    exports project.gameObjects;
    opens project.gameObjects to com.fasterxml.jackson.databind;
    exports project.Characters;
    opens project.Characters to com.fasterxml.jackson.databind;
    opens project to javafx.fxml, javafx.graphics;
    exports tempTrash;
    opens tempTrash to com.fasterxml.jackson.databind;
}