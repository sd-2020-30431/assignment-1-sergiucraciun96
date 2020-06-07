package utcn.sergiucraciun.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "GroceryItem")
public class GroceryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int quantity;
    private long calorieValue;
    private LocalDate purchaseDate;
    private LocalDate expirationDate;;

    public GroceryItem(String name, int quantity, long calorieValue, LocalDate purchaseDate, LocalDate expirationDate) {
        this.name = name;
        this.quantity = quantity;
        this.calorieValue = calorieValue;
        this.purchaseDate = purchaseDate;
        this.expirationDate = expirationDate;
    }

    @ManyToOne
    @JoinColumn(name = "groceryList")
    private GroceryList groceryList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groceryItem")
    private List<ConsumptionItem> consumptionItems = new ArrayList<>();

    public long totalCalories() {
        return this.quantity * this.calorieValue;
    }

    public long idealBurndownRate() {   //calories burndown rate for quantity of items per day
        long rate;

        rate = totalCalories() / DAYS.between(this.purchaseDate, this.expirationDate);
        return rate;
    }

    public long idealCaloriesConsumedToDate(LocalDate date) {  //sum of ideal burnt calories between purchase date and now
        long idealCaloriesToDate = 0;

        for (int i = 0; i < (DAYS.between(this.purchaseDate, date)); i++) {
            idealCaloriesToDate += idealBurndownRate();
        }

        return idealCaloriesToDate;
    }

    public long actualCaloriesConsumedToDate() {   //sum of actual burnt calories between purchase date and now
        long consumedCalories = 0;

        for (ConsumptionItem consumptionItem : this.consumptionItems) {
            if (consumptionItem.getConsumptionDate().isBefore(this.expirationDate) &&
                    consumptionItem.getConsumptionDate().isAfter(this.purchaseDate)) {
                consumedCalories += consumptionItem.burndownRatePerConsumption();
            }
        }
        return consumedCalories;
    }

    public void wasteLevels(LocalDate date) {
        if (idealCaloriesConsumedToDate(date) <= actualCaloriesConsumedToDate()) {
            System.out.println("Waste levels are fine.");
        } else {
            System.out.println("Waste levels are too high.");
        }
    }
}
