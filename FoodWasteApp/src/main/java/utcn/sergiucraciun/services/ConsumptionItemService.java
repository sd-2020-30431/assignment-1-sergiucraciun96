package utcn.sergiucraciun.services;

import utcn.sergiucraciun.models.ConsumptionItem;
import utcn.sergiucraciun.repositories.ConsumptionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsumptionItemService {
    private final ConsumptionItemRepository consumptionItemRepository;

    public ConsumptionItem saveConsumptionItem(ConsumptionItem consumptionItem) {
        return consumptionItemRepository.save(consumptionItem);
    }
}
