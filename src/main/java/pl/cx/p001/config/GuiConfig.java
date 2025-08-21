package pl.cx.p001.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for GUI map size parameters.
 */
@Getter
@Configuration
public class GuiConfig {
    @Value("${gui.map.width:200}")
    private int guiMapWidth;
    @Value("${gui.map.height:200}")
    private int guiMapHeight;
    @Value("${gui.cell.size:10}")
    private int guiCellSize;
}
