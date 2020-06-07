package utcn.sergiucraciun.controllers;

import utcn.sergiucraciun.models.*;
import utcn.sergiucraciun.services.*;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@ToString
@Transactional
@Component
@RequiredArgsConstructor
public class BaseController implements CommandLineRunner {
    private final UserService userService;
    private final GroceryListService groceryListService;
    private final GroceryItemService groceryItemService;
    private final ConsumptionItemService consumptionItemService;
    private final NotificationService notificationService;
    private Scanner scanner = new Scanner(System.in).useDelimiter("\n");
    private User user;
    private Report weeklyReportGenerator;
    private Report monthlyReportGenerator;

    @Override
    public void run(String... args) throws Exception {
        /*GroceryItem groceryItem1 = new GroceryItem("Apple", 25, 100,LocalDate.parse("2020-04-25"), LocalDate.parse("2020-05-10"));
        GroceryItem groceryItem2 = new GroceryItem("Pear", 30, 150, LocalDate.parse("2020-04-20"), LocalDate.parse("2020-05-05"));

        ConsumptionItemService consumptionItem1 = new ConsumptionItemService("Apple", LocalDate.parse("2020-04-30"), 5, 100, "donate");
        ConsumptionItemService consumptionItem2 = new ConsumptionItemService("Apple", LocalDate.parse("2020-05-02"), 10, 100, "sell");

        GroceryList groceryList1 = new GroceryList();
        groceryList1.getGroceryItems().add(groceryItem1);
        groceryList1.getGroceryItems().add(groceryItem2);

        groceryItem1.setGroceryList(groceryList1);
        groceryItem1.getConsumptionItems().add(consumptionItem1);
        groceryItem1.getConsumptionItems().add(consumptionItem2);
        groceryItem2.setGroceryList(groceryList1);

        consumptionItem1.setGroceryItem(groceryItem1);
        consumptionItem2.setGroceryItem(groceryItem1);

        User user1 = new User();
        user1.getGroceryLists().add(groceryList1);

        groceryList1.setUser(user1);

        groceryItem1.wasteLevels();

        groceryItemRepository.save(groceryItem1);
        groceryItemRepository.save(groceryItem2);

        consumptionItemRepository.save(consumptionItem1);
        consumptionItemRepository.save(consumptionItem2);

        groceryListRepository.save(groceryList1);

        userRepository.save(user1);*/

        boolean done = false;
        while (!done) {
            print("Enter a command: ");
            String command = scanner.next().trim();
            done = handleCommand(command);
        }


    }

    private boolean handleCommand(String command) throws ParseException {
        boolean done = false;
        switch (command) {
            case "login":
                print("Username:");
                String username = scanner.next().trim();
                print("Password:");
                String password = scanner.next().trim();
                Optional<User> userOptional = userService.login(username, password);
                if (userOptional.isPresent()) {
                    user = userOptional.get();
                    print("Logged in as:");
                    print(user.toString());
                } else {
                    print("wrong username or password");
                }
                break;

            case "add_list":
                print("Enter name:");

                String listName = scanner.next().trim();
                GroceryList groceryList = new GroceryList(listName);

                user.getGroceryLists().add(groceryList);
                groceryList.setUser(user);

                groceryListService.saveGroceryList(groceryList);
                userService.saveUser(user);
                break;

            case "add_item":
                print("Enter name:");
                String itemName = scanner.next().trim();

                print("Enter quantity:");
                int quantity = scanner.nextInt();

                print("Enter calorie value:");
                long calorieValue = scanner.nextLong();

                print("Enter purchase date:");
                String purchaseDateString = scanner.next().trim();
                LocalDate purchaseDate = LocalDate.parse(purchaseDateString);

                print("Enter expiration date:");
                String expirationDateString = scanner.next().trim();
                LocalDate expirationDate = LocalDate.parse(expirationDateString);

                print("Enter list number:");
                int listNumber = scanner.nextInt();

                GroceryItem groceryItem = new GroceryItem(itemName, quantity, calorieValue, purchaseDate, expirationDate);
                List<GroceryList> groceryLists = groceryListService.findAllGroceryLists();
                groceryLists.get(listNumber).getGroceryItems().add(groceryItem);
                groceryItem.setGroceryList(groceryLists.get(listNumber));

                groceryItemService.saveGroceryItem(groceryItem);
                groceryListService.saveGroceryList(groceryLists.get(listNumber));
                break;

            case "add_consumption_item":
                print("Enter name:");
                String consumptionName = scanner.next().trim();

                print("Enter quantity:");
                int consumptionQuantity = scanner.nextInt();

                print("Enter consumption date:");
                String consumptionDateString = scanner.next().trim();
                LocalDate consumptionDate = LocalDate.parse(consumptionDateString);

                print("Enter operation:");
                String operation = scanner.next().trim();

                print("Enter item number:");
                int itemNumber = scanner.nextInt();

                ConsumptionItem consumptionItem = new ConsumptionItem(consumptionName, consumptionDate, consumptionQuantity, operation);
                List<GroceryItem> groceryItems = groceryItemService.findAllGroceryItems();
                groceryItems.get(itemNumber).getConsumptionItems().add(consumptionItem);
                consumptionItem.setGroceryItem(groceryItems.get(itemNumber));

                consumptionItemService.saveConsumptionItem(consumptionItem);
                groceryItemService.saveGroceryItem(groceryItems.get(itemNumber));
                break;

            case "wasted_levels":
                print("Enter date:");
                String dateString = scanner.next().trim();
                LocalDate date = LocalDate.parse(dateString);

                print("Enter item number:");
                int wastedNumber = scanner.nextInt();

                List<GroceryItem> groceryItemsWasted = groceryItemService.findAllGroceryItems();
                groceryItemsWasted.get(wastedNumber).wasteLevels(date);
                break;

            case "notification":
                List<GroceryItem> groceryItemsNotification = groceryItemService.findAllGroceryItems();
                for (GroceryItem item : notificationService.getItemsPriorToExpiration(groceryItemsNotification)) {
                    System.out.println(item.toString());
                }
                break;

            case "weekly_report":
                Report reportWeekly = weeklyReportGenerator;
                System.out.println(reportWeekly.getWastedGroceryItems().toString() + reportWeekly.getTotalWastedQuantity() + reportWeekly.getTotalWastedCalorieValue());
                break;

            case "monthly_report":
                Report reportMonthly = monthlyReportGenerator;
                System.out.println(reportMonthly.getWastedGroceryItems().toString() + reportMonthly.getTotalWastedQuantity() + reportMonthly.getTotalWastedCalorieValue());
                break;

            case "exit":
                done = true;
                break;

            default:
                print("Unknown command!");
                break;
        }

        return done;
    }

    private void print(String string) {
        System.out.println(string);
    }
}

