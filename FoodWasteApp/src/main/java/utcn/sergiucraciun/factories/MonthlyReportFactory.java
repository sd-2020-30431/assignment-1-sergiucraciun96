package utcn.sergiucraciun.factories;

import utcn.sergiucraciun.generators.MonthlyReportGenerator;
import utcn.sergiucraciun.generators.ReportGenerator;
import utcn.sergiucraciun.generators.ReportType;
import org.springframework.stereotype.Component;

@Component
public class MonthlyReportFactory extends ReportFactory {

    @Override
    public ReportGenerator getReport(ReportType reportType) {

        return new MonthlyReportGenerator();
    }
}
