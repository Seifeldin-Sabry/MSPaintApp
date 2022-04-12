package mspaint;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mspaint.view.PaintPresenter;
import mspaint.view.PaintView;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        PaintView view = new PaintView();
        new PaintPresenter(view);
        stage.setScene(new Scene(view));
        stage.setTitle("MSPAINT");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
