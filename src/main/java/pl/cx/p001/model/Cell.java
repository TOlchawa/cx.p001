package pl.cx.p001.model;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import javafx.scene.paint.Color;

public class Cell {
    private final Map<AssetType, Integer> assets = new EnumMap<>(AssetType.class);

    public Cell() {
        for (AssetType type : AssetType.values()) {
            assets.put(type, 0);
        }
    }

    public int getAssetCount(AssetType type) {
        return assets.getOrDefault(type, 0);
    }

    public Map<AssetType, Integer> getAssets() {
        return Collections.unmodifiableMap(assets);
    }

    public void setAssetCount(AssetType assetType, int count) {
        if (assetType == null) return;
        assets.put(assetType, count);
    }

    public Color getColor() {
        int matter = getAssetCount(AssetType.MATTER);
        int energy = getAssetCount(AssetType.ENERGY);
        if (matter > 0 && energy > 0) {
            return Color.GREEN;
        } else if (energy > 0) {
            return Color.YELLOW;
        } else if (matter > 0) {
            return Color.BLUE;
        } else {
            return Color.BLACK;
        }
    }
}
