package pl.cx.p001;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cx.p001.model.Arena;
import pl.cx.p001.model.ArenaProvider;
import pl.cx.p001.model.Cell;
import pl.cx.p001.model.AssetType;

import java.util.Random;

@SpringBootApplication
public class PixelGuiApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PixelGuiApp.class, args);
        PixelGui.launchApp(ctx, args);
    }

    @Bean
    public PixelModel pixelModel() {
        return new PixelModel();
    }

    @Bean
    public ArenaProvider arenaProvider() {
        return new ArenaProvider();
    }

    @Bean
    public Arena arena(ArenaProvider provider) {
        Arena arena = provider.generateDefaultArena();
        Random rand = new Random();
        for (int x = 0; x < arena.getWidth(); x++) {
            for (int y = 0; y < arena.getHeight(); y++) {
                Cell cell = arena.getCell(x, y, 0);
                if (rand.nextDouble() < 0.05) cell.setAssetCount(AssetType.ENERGY, rand.nextInt(10) + 1);
                if (rand.nextDouble() < 0.05) cell.setAssetCount(AssetType.MATTER, rand.nextInt(10) + 1);
            }
        }
        return arena;
    }
}

class PixelModel {
    public final int width = 300;
    public final int height = 300;

    // Przykładowy model: gradient
    public javafx.scene.paint.Color getColor(int x, int y) {
        return javafx.scene.paint.Color.color((double)x/width, (double)y/height, 0.5);
    }
}
