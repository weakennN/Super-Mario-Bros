package ECS.SprtieRenderer;

import Game.Common.GlobalVariables;

import java.util.ArrayList;
import java.util.List;

public class SortingLayersContainer {

    public static List<SortingLayer> sortingLayers = new ArrayList<>();

    public static void initContainer() {

        sortingLayers.add(new SortingLayer(GlobalVariables.BACKGROUND_SORTING_LAYER));
        sortingLayers.add(new SortingLayer(GlobalVariables.DEFAULT_SORTING_LAYER));
    }

    public static void addSortingLayer(SortingLayer sortingLayer) {
        sortingLayers.add(sortingLayer);
    }

    public static void removeSortingLayer(SortingLayer sortingLayer) {

        if (sortingLayer.getSortingLayerName().equals(GlobalVariables.DEFAULT_SORTING_LAYER)) {
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
        sortingLayers.set(sortingLayers.size() - 1, sortingLayer);
        sortingLayer.setValue(sortingLayers.size() - 1);
        sortingLayers.set(fistIndex, first);
        first.setValue(fistIndex);

    }
}
