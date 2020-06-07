package utcn.sergiucraciun.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Report {
    private List<GroceryItem> wastedGroceryItems = new ArrayList<>();

    public Report(List<GroceryItem> wastedGroceryItems) {
        this.wastedGroceryItems = wastedGroceryItems;
    }

    public int getTotalWastedQuantity() {
        int totalWastedQuantity = 0;

        for (GroceryItem wastedGroceryItem : this.wastedGroceryItems) {
            totalWastedQuantity += wastedGroceryItem.getQuantity();
        }
        return totalWastedQuantity;
    }

    public long getTotalWastedCalorieValue() {
        long totalWastedCalorieValue = 0;

        for (GroceryItem wastedGroceryItem : this.wastedGroceryItems) {
            totalWastedCalorieValue += wastedGroceryItem.getCalorieValue();
        }
        return totalWastedCalorieValue;
    }
}
