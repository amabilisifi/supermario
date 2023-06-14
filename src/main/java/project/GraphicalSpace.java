package project;

import javafx.stage.Stage;

public class GraphicalSpace {
    private static GraphicalSpace instance;
    private Stage stage;

    public static GraphicalSpace getInstance(){
        if(instance == null){
            instance = new GraphicalSpace();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
