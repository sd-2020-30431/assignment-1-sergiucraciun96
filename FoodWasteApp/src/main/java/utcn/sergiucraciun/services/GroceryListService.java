package utcn.sergiucraciun.services;

import utcn.sergiucraciun.models.GroceryList;
import utcn.sergiucraciun.repositories.GroceryListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroceryListService {
    private final GroceryListRepository groceryListRepository;

    public GroceryList saveGroceryList(GroceryList groceryList) {
        return groceryListRepository.save(groceryList);
    }

    public List<GroceryList> findAllGroceryLists() {
        return groceryListRepository.findAll();
    }
}
