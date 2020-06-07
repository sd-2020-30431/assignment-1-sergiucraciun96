package utcn.sergiucraciun.repositories;

import utcn.sergiucraciun.models.ConsumptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionItemRepository extends JpaRepository<ConsumptionItem, Integer> {
}
