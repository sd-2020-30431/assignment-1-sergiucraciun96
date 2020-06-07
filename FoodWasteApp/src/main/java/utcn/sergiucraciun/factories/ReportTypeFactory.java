package utcn.sergiucraciun.factories;

import utcn.sergiucraciun.generators.ReportType;

public class ReportTypeFactory {
    public  ReportFactory getFactory(ReportType report){

        if(report == ReportType.WEEKLY)
            return new WeeklyReportFactory();
        else if(report == ReportType.MONTHLY){
            return new MonthlyReportFactory();
        }
        return null;
    }
}
