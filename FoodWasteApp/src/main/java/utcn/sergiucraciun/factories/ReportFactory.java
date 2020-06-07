package utcn.sergiucraciun.factories;

import utcn.sergiucraciun.generators.ReportGenerator;
import utcn.sergiucraciun.generators.ReportType;

public abstract class ReportFactory {
    public abstract ReportGenerator getReport(ReportType reportType);
}
