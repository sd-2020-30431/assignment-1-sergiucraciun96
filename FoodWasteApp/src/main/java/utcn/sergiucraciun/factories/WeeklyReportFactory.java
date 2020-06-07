package utcn.sergiucraciun.factories;

import utcn.sergiucraciun.generators.ReportGenerator;
import utcn.sergiucraciun.generators.ReportType;
import utcn.sergiucraciun.generators.WeeklyReportGenerator;
import org.springframework.stereotype.Component;

@Component
public class WeeklyReportFactory extends ReportFactory {

    @Override
    public ReportGenerator getReport(ReportType reportType) {

        return new WeeklyReportGenerator();
    }
}
