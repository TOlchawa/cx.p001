package pl.cx.p001.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import org.springframework.context.ConfigurableApplicationContext;
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
public class PixelGui extends Application implements ArenaListener, RobotListener {
    @Getter
    private static PixelGui instance;
    private static ConfigurableApplicationContext springContext;
    private static String[] args;
    private final int cellSize;
    private final Canvas canvas;
    private int width;
    private int height;


    public PixelGui() {
        // Set default values, do not use springContext here
        width = 50;
        height = 50;
        cellSize = 3;
        this.canvas = new Canvas(width * cellSize, height * cellSize);
        clearArena();
    }

    public static void launchApp(ConfigurableApplicationContext context, String[] launchArgs) {
        springContext = context;
        args = launchArgs;
        launch(launchArgs);
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this; // set the static reference to the running instance
        canvas.setWidth(width * cellSize);
        canvas.setHeight(height * cellSize);
        clearArena();
        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, width * cellSize + 20, height * cellSize + 20);
        primaryStage.setTitle("Arena Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void clearArena() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, width * cellSize, height * cellSize);
    }

    private void drawArena(Arena arena) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                drawCell(arena, x, y, 0); // Assuming z=0 for 2D view
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
            width = event.getArena().getWidth();
            height = event.getArena().getHeight();
            canvas.setWidth(width * cellSize);
            canvas.setHeight(height * cellSize);
            clearArena();
            drawArena(event.getArena());
            // Adjust window size to fit canvas, but do not exceed max size
            Stage stage = (Stage) canvas.getScene().getWindow();
            double maxWidth = 1200;
            double maxHeight = 900;
            double newWidth = Math.min(width * cellSize + 20, maxWidth);
            double newHeight = Math.min(height * cellSize + 20, maxHeight);
            stage.setWidth(newWidth);
            stage.setHeight(newHeight);
            // Center window on screen, but keep it fully visible
            javafx.geometry.Rectangle2D screenBounds = javafx.stage.Screen.getPrimary().getVisualBounds();
            double x = Math.max(screenBounds.getMinX(), screenBounds.getMinX() + (screenBounds.getWidth() - newWidth) / 2);
            double y = Math.max(screenBounds.getMinY(), screenBounds.getMinY() + (screenBounds.getHeight() - newHeight) / 2);
            // Ensure window is not outside visible area
            if (x + newWidth > screenBounds.getMaxX()) x = screenBounds.getMaxX() - newWidth;
            if (y + newHeight > screenBounds.getMaxY()) y = screenBounds.getMaxY() - newHeight;
            stage.setX(x);
            stage.setY(y);


        } else if (event.getType() == pl.cx.p001.events.ArenaUpdateEvent.Type.CELL_UPDATE) {
            drawCell(event.getArena(), event.getX(), event.getY(), event.getZ());
        }
    }

    @Override
    public void onRobotUpdated(RobotUpdateEvent event) {
        //TODO: Implement robot update handling if needed
    }
}
