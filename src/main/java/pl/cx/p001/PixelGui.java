package pl.cx.p001;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;

@NoArgsConstructor
public class PixelGui extends Application {
    private static ConfigurableApplicationContext springContext;
    private static String[] args;

    public static void launchApp(ConfigurableApplicationContext context, String[] launchArgs) {
        springContext = context;
        args = launchArgs;
        launch(launchArgs);
    }

    @Override
    public void start(Stage primaryStage) {
        PixelModel model = springContext.getBean(PixelModel.class);
        Canvas canvas = new Canvas(300, 300);
        drawModel(canvas.getGraphicsContext2D(), model);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, 320, 320);
        primaryStage.setTitle("Pixel Model Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawModel(GraphicsContext gc, PixelModel model) {
        for (int y = 0; y < model.height; y++) {
            for (int x = 0; x < model.width; x++) {
                gc.getPixelWriter().setColor(x, y, model.getColor(x, y));
            }
        }
    }
}
