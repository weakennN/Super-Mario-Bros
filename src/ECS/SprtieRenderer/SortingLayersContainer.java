package ECS.SprtieRenderer;

import java.util.ArrayList;
import java.util.List;

public class SortingLayersContainer {

    public static List<SortingLayer> sortingLayers = new ArrayList<>();

    public static void initContainer() {
        sortingLayers.add(new SortingLayer("background"));
        sortingLayers.add(new SortingLayer("default"));
    }

    public static void addSortingLayer(SortingLayer sortingLayer) {
        sortingLayers.add(sortingLayer);
    }

    public static void removeSortingLayer(SortingLayer sortingLayer) {
        if (sortingLayer.getSortingLayerName().equals("default")) {
            return;
        }
        sortingLayers.remove(sortingLayer);
    }

    public static SortingLayer getSortingLayerByName(String sortingLayer) {
        for (int i = 0; i < sortingLayers.size(); i++) {
            if (sortingLayers.get(i).getSortingLayerName().equals(sortingLayer)) {
                return sortingLayers.get(i);
            }
        }

        return null;
    }

    public static void setPriority(SortingLayer first, int fistIndex) {
        SortingLayer sortingLayer = sortingLayers.get(fistIndex);
        sortingLayers.set(fistIndex, first);
        sortingLayers.add(sortingLayer);
        sortingLayers.get(sortingLayers.size() - 1).setValue(sortingLayers.size() - 1);
        first.setValue(fistIndex);
    }
}
