package utcn.sergiucraciun.generators;

import utcn.sergiucraciun.models.GroceryItem;
import utcn.sergiucraciun.models.Report;

import java.time.LocalDate;
import java.util.List;

public class WeeklyReportGenerator extends ReportGenerator {

    @Override
    public Report generateReport(List<GroceryItem> groceryItems, LocalDate date) {
        return createReport(groceryItems, date, 7);
    }
}
