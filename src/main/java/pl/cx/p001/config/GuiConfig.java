package pl.cx.p001.config;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for GUI map size parameters.
 */
@Getter
@Configuration
public class GuiConfig {
    private final int guiMapWidth = 200;
    private final int guiMapHeight = 200;
    private final int guiCellSize = 10;
}
