package utcn.sergiucraciun.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "ConsumptionItem")
public class ConsumptionItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private LocalDate consumptionDate;
    private int quantity;
    private String operation;

    public ConsumptionItem(String name, LocalDate consumptionDate, int quantity, String operation) {
        this.name = name;
        this.consumptionDate = consumptionDate;
        this.quantity = quantity;
        this.operation = operation;
    }

    @ManyToOne
    @JoinColumn(name = "groceryItem")
    private GroceryItem groceryItem;

    public long burndownRatePerConsumption() { //calories burndown rate per consumption
        return this.quantity * groceryItem.getCalorieValue();
    }
}
