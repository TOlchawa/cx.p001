package pl.cx.p001.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for GUI map size parameters.
 */
@Getter
@Configuration
public class GuiConfig {
    private final int guiMapWidth = 100;
    private final int guiMapHeight = 100;
    private final int guiCellSize = 5;
}
