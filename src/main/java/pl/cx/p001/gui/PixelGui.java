package pl.cx.p001.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cx.p001.config.GuiConfig;
import pl.cx.p001.events.ArenaListener;
import pl.cx.p001.events.ArenaUpdateEvent;
import pl.cx.p001.events.RobotListener;
import pl.cx.p001.events.RobotUpdateEvent;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.Cell;

import java.util.concurrent.CountDownLatch;

/**
 * PixelGui is the main JavaFX application class responsible for rendering the Arena on the GUI.
 * It displays the world map, where each cell is visualized as a colored square based on its assets.
 * Integrates with Spring to obtain the Arena instance.
 */
@Slf4j
public class PixelGui extends Application implements ArenaListener, RobotListener {
    @Getter
    private static PixelGui instance;
    private static ConfigurableApplicationContext springContext;
    private static String[] args;
    private static final CountDownLatch READY_LATCH = new CountDownLatch(1);

    private int cellSize;
    private final Canvas canvas;
    private int width;
    private int height;

    public PixelGui() {
        // Keep constructor parameterless and lightweight (no Spring access)
        this.width = 50;
        this.height = 50;
        this.cellSize = 4;
        this.canvas = new Canvas(width * cellSize, height * cellSize);
        log.debug("ctor canvas: {}", canvas);
        clearArena();
    }

    public static void launchApp(ConfigurableApplicationContext context, String[] launchArgs) {
        springContext = context;
        args = launchArgs;
        launch(launchArgs);
    }

    public static void awaitReady() {
        try {
            READY_LATCH.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this; // set the static reference to the running instance
        // Read GUI config safely now that Spring context is available
        GuiConfig gui = springContext.getBean(GuiConfig.class);
        this.width = gui.getGuiMapWidth();
        this.height = gui.getGuiMapHeight();
        this.cellSize = gui.getGuiCellSize();
        canvas.setWidth(width * cellSize);
        canvas.setHeight(height * cellSize);
        clearArena();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, Math.min(width * cellSize + 20, 1200), Math.min(height * cellSize + 20, 900));
        primaryStage.setTitle("Arena Viewer");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Register this GUI instance as a Spring bean for optional autowiring access
        try {
            springContext.getBeanFactory().registerSingleton("pixelGui", this);
        } catch (Exception ex) {
            log.debug("Skipping bean registration for PixelGui: {}", ex.getMessage());
        }

        // Center window on screen within visible bounds
        centerStage(primaryStage);

        log.debug("start canvas: {}", canvas);
        READY_LATCH.countDown();
    }

    private void centerStage(Stage stage) {
        javafx.geometry.Rectangle2D screen = javafx.stage.Screen.getPrimary().getVisualBounds();
        double newWidth = stage.getWidth();
        double newHeight = stage.getHeight();
        double x = Math.max(screen.getMinX(), screen.getMinX() + (screen.getWidth() - newWidth) / 2);
        double y = Math.max(screen.getMinY(), screen.getMinY() + (screen.getHeight() - newHeight) / 2);
        if (x + newWidth > screen.getMaxX()) x = screen.getMaxX() - newWidth;
        if (y + newHeight > screen.getMaxY()) y = screen.getMaxY() - newHeight;
        stage.setX(x);
        stage.setY(y);
    }

    private void clearArena() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, width * cellSize, height * cellSize);
    }

    private void drawArena(Arena arena) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        log.debug("drawArena canvas: {}", canvas);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                drawCell(arena, x, y, 0); // z=0 for 2D view
            }
        }
    }

    private void drawCell(Arena arena, int x, int y, int z) {
        Cell cell = arena.getCell(x, y, z);
        Color color = cell.getColor();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color == null ? Color.BLACK : color);
        gc.fillRect(x * cellSize, y * cellSize, cellSize, cellSize);
    }

    @Override
    public void onArenaUpdated(ArenaUpdateEvent event) {
        // Always marshal drawing to JavaFX Application Thread
        Platform.runLater(() -> {
            if (event.getType() == ArenaUpdateEvent.Type.FULL_UPDATE) {
                width = event.getArena().getWidth();
                height = event.getArena().getHeight();
                canvas.setWidth(width * cellSize);
                canvas.setHeight(height * cellSize);
                clearArena();
                drawArena(event.getArena());
                // Adjust and center window
                Stage stage = (Stage) canvas.getScene().getWindow();
                double maxWidth = 1200, maxHeight = 900;
                stage.setWidth(Math.min(width * cellSize + 20, maxWidth));
                stage.setHeight(Math.min(height * cellSize + 20, maxHeight));
                centerStage(stage);
            } else if (event.getType() == ArenaUpdateEvent.Type.CELL_UPDATE) {
                drawCell(event.getArena(), event.getX(), event.getY(), event.getZ());
            }
        });
    }

    @Override
    public void onRobotUpdated(RobotUpdateEvent event) {
        // No-op for now; extend to render robots
    }
}
