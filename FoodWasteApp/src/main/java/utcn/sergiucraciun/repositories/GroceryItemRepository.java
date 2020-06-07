package utcn.sergiucraciun.repositories;

import utcn.sergiucraciun.models.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {
}
