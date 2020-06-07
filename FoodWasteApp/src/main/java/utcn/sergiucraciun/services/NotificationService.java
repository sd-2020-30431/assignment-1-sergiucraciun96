package utcn.sergiucraciun.services;

import utcn.sergiucraciun.models.ConsumptionItem;
import utcn.sergiucraciun.models.GroceryItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    public List<GroceryItem> getItemsPriorToExpiration(List<GroceryItem> groceryItems) {
        List<GroceryItem> itemsToExpire = new ArrayList<>();

        for (GroceryItem groceryItem : groceryItems) {
            GroceryItem itemToExpire = groceryItem;
            int remainingQuantity = groceryItem.getQuantity();

            for (ConsumptionItem consumptionItem : groceryItem.getConsumptionItems()) {
                if (consumptionItem.getConsumptionDate().isBefore(groceryItem.getExpirationDate().minusDays(1))) {
                    remainingQuantity -= consumptionItem.getQuantity();
                }
            }
            if (remainingQuantity != 0) {
                itemToExpire.setQuantity(remainingQuantity);
                itemsToExpire.add(itemToExpire);
            }
        }
        return itemsToExpire;
    }
}
