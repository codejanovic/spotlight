package sample;

import com.gluonhq.ignite.guice.GuiceContext;
import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.Arrays;

public class App extends Application implements SpotlightApp {

    class GuiceModule extends AbstractModule {
        @Override protected void configure() {

        }
    }

    private double xOffset = 0;
    private double yOffset = 0;

    @Inject
    private FXMLLoader fxmlLoader;
    private GuiceContext context = new GuiceContext(this, () -> Arrays.asList(new GuiceModule()));

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        context.init();
        fxmlLoader.setLocation(mainViewLocation());
        Parent mainView = fxmlLoader.load();
        makeSceneMoveable(mainView, primaryStage);

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Spotlight");
        primaryStage.setScene(new Scene(mainView, 600, 300));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void makeSceneMoveable(Parent scene, Stage primaryStage) {
        scene.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        scene.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
    }
}
