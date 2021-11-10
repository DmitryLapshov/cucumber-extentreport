package cxpro_tests;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReportHelper {
    public static ExtentReports extent;
    public static ExtentSparkReporter spark;
    public static ExtentTest feature, scenario, step;

    public static void init() {
        if (extent == null) {
            extent = new ExtentReports();
            spark = new ExtentSparkReporter("AutomatedReports/report/index.html");
            extent.attachReporter(spark);
        }
    }

    public static void CreateFeature(String name) {
        try {
            feature = extent.createTest(new GherkinKeyword("Feature"), name);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void CreateScenario(String name) {
        try {
            scenario = feature.createNode(new GherkinKeyword("Scenario"), name);
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void CreateStep(String keyWord, String name, Boolean isOK, String error) {
        try {
            if (isOK) {
                step = scenario.createNode(new GherkinKeyword(keyWord), name).pass("");
            }
            else {
                step = scenario.createNode(new GherkinKeyword(keyWord), name).fail(error);
            }
        }
        catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
