package cxpro_tests;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;

import java.util.List;

public class ProTestListener implements ConcurrentEventListener {
    private static boolean firstTime = true;
    private int stepIdx;
    private List<TestStep> steps;
    private static final String browser = "chrome";

    public void onTestRunStarted(TestRunStarted event) {
        if(firstTime) {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                ReportHelper.extent.flush();
                Selen.stopService();
            }));

            Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
                System.err.println("Uncaught Exception: " + e.getMessage());
                System.exit(1);
            });

            ReportHelper.init();
            Selen.startService(browser);

            firstTime = false;
        }
    }

    public void onTestCaseStarted(TestCaseStarted event) {
        TestCase testCase = event.getTestCase();
        String scenarioName = testCase.getName();

        stepIdx = 0;
        ReportHelper.CreateScenario(scenarioName);
        steps = testCase.getTestSteps();

        Selen.startBrowser(browser);
    }

    public void onTestCaseFinished(TestCaseFinished event) {
        Selen.stopBrowser();
    }

    public void onTestStepFinished(TestStepFinished event) {
        PickleStepTestStep step = (PickleStepTestStep) steps.get(stepIdx);
        String keyWord = step.getStep().getKeyword();
        String text = step.getStep().getText();
        Result result = event.getResult();
        Status status = result.getStatus();
        Throwable error = result.getError();
        ReportHelper.CreateStep(keyWord, text, status.isOk(), error != null ? error.getMessage() : "");
        stepIdx++;
    }

    public void onTestSourceReadHandler(TestSourceRead event) {
        String src = event.getSource().split("[\\r\\n]+")[0];
        String featureName = src.substring("Feature: ".length()).trim();
        ReportHelper.CreateFeature(featureName);
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestRunStarted.class, this::onTestRunStarted);
        eventPublisher.registerHandlerFor(TestCaseStarted.class, this::onTestCaseStarted);
        eventPublisher.registerHandlerFor(TestStepFinished.class, this::onTestStepFinished);
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::onTestCaseFinished);
        eventPublisher.registerHandlerFor(TestSourceRead.class, this::onTestSourceReadHandler);
    }
}
