package pl.cx.p001.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import pl.cx.p001.config.GuiConfig;
import pl.cx.p001.events.ArenaListener;
import pl.cx.p001.events.RobotListener;
import pl.cx.p001.events.RobotUpdateEvent;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.Cell;

/**
 * PixelGui is the main JavaFX application class responsible for rendering the Arena on the GUI.
 * It displays the world map, where each cell is visualized as a colored square based on its assets.
 * Integrates with Spring to obtain the Arena instance.
 */
@Service
public class PixelGui extends Application implements ArenaListener, RobotListener {

    private static ConfigurableApplicationContext springContext;
    private static String[] args;
    private int width;
    private int height;
    private int cellSize;

    public static void launchApp(ConfigurableApplicationContext context, String[] launchArgs) {
        springContext = context;
        args = launchArgs;
        launch(launchArgs);
    }

    private Canvas canvas;

    public PixelGui() {
        // Use default values if GuiConfig is not initialized yet
        this.width = 100;
        this.height = 100;
        this.cellSize = 5;
        this.canvas = new Canvas(width * cellSize, height * cellSize);
        drawBlankArena();
    }

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, width * cellSize + 20, height * cellSize + 20);
        primaryStage.setTitle("Arena Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawBlankArena() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect(0, 0, width * cellSize, height * cellSize);
    }

    private void drawArena(Arena arena) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, width * cellSize, height * cellSize); // clear the whole canvas
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Cell cell = arena.getCell(x, y, 0);
                Color color = cell.getColor();
                if (color == null) {
                    color = Color.BLACK;
                } else if (color.equals(Color.TRANSPARENT)) {
                    color = Color.WHITE;
                }
                gc.setFill(color);
                gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
            }
        }
    }

    private void drawCell(Arena arena, int x, int y, int z) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Cell cell = arena.getCell(x, y, z);
        gc.setFill(cell.getColor());
        gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    @Override
    public void onArenaUpdated(pl.cx.p001.events.ArenaUpdateEvent event) {
        if (event.getType() == pl.cx.p001.events.ArenaUpdateEvent.Type.FULL_UPDATE) {
            drawArena(event.getArena());
        } else if (event.getType() == pl.cx.p001.events.ArenaUpdateEvent.Type.CELL_UPDATE) {
            drawCell(event.getArena(), event.getX(), event.getY(), event.getZ());
        }
    }

    @Override
    public void onRobotUpdated(RobotUpdateEvent event) {
        //TODO: Implement robot update handling if needed
    }
}
