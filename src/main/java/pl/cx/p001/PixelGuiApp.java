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
}

class PixelModel {
    public final int width = 300;
    public final int height = 300;

    // Przykładowy model: gradient
    public javafx.scene.paint.Color getColor(int x, int y) {
        return javafx.scene.paint.Color.color((double)x/width, (double)y/height, 0.5);
    }
}
