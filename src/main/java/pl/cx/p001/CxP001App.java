package pl.cx.p001;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ConfigurableApplicationContext;
import pl.cx.p001.gui.PixelGui;
import pl.cx.p001.gui.PixelModel;
import pl.cx.p001.manager.SimulatorManager;

import java.util.concurrent.Executors;

@SpringBootApplication
public class CxP001App {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(CxP001App.class, args);
        // Wait for PixelGui initialization before starting the rest of the app

        // start thread for simulation
        Executors.newSingleThreadExecutor().submit(() -> PixelGui.launchApp(ctx, args));

        SimulatorManager simulatorManager = ctx.getBean(SimulatorManager.class); // This ensures PixelGui is initialized first
        simulatorManager.initialize();
    }

}
