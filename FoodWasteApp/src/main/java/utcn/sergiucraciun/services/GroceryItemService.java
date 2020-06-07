package utcn.sergiucraciun.services;

import utcn.sergiucraciun.models.GroceryItem;
import utcn.sergiucraciun.repositories.GroceryItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryItemService {
    private final GroceryItemRepository groceryItemRepository;

    public GroceryItem saveGroceryItem(GroceryItem groceryItem) {
        return groceryItemRepository.save(groceryItem);
    }

    public List<GroceryItem> findAllGroceryItems() {
        return groceryItemRepository.findAll();
    }
}
