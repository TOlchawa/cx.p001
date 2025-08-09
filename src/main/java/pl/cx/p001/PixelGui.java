package pl.cx.p001;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.Cell;

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
        Arena arena = springContext.getBean(Arena.class);
        int cellSize = 4;
        int width = arena.getWidth();
        int height = arena.getHeight();
        Canvas canvas = new Canvas(width * cellSize, height * cellSize);
        drawArena(canvas.getGraphicsContext2D(), arena, cellSize);
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, width * cellSize + 20, height * cellSize + 20);
        primaryStage.setTitle("Arena Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawArena(GraphicsContext gc, Arena arena, int cellSize) {
        for (int y = 0; y < arena.getHeight(); y++) {
            for (int x = 0; x < arena.getWidth(); x++) {
                Cell cell = arena.getCell(x, y, 0);
                gc.setFill(cell.getColor());
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }
}
