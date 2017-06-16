package org.redhat.qe.jaeger.api.model.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestResult {
    private Count count;
    private List<Test> configurationFailures;
    private List<Test> configurationSkips;
    private List<Test> passed;
    private List<Test> falied;
    private List<Test> skipped;

    public static TestResult get(TestListenerAdapter tla) {
        return TestResult.builder()
                .count(Count.builder()
                        .passed(tla.getPassedTests().size())
                        .failed(tla.getFailedTests().size())
                        .skipped(tla.getSkippedTests().size())
                        .configurationFailures(tla.getConfigurationFailures().size())
                        .configurationSkips(tla.getConfigurationSkips().size())
                        .build())
                        .configurationFailures(getTestList(tla.getConfigurationFailures()))
                        .configurationSkips(getTestList(tla.getConfigurationSkips()))
                        .passed(getTestList(tla.getPassedTests()))
                        .falied(getTestList(tla.getFailedTests()))
                        .skipped(getTestList(tla.getSkippedTests()))
                        .build();
    }

    private static List<Test> getTestList(List<ITestResult> iTestResult) {
        List<Test> testResult = new ArrayList<Test>();
        for (ITestResult iResult : iTestResult) {
            testResult.add(
                    Test.builder()
                    .method(iResult.getMethod().getMethodName())
                    .clazz(iResult.getTestClass().getRealClass().getCanonicalName())
                    .startMillis(iResult.getStartMillis())
                    .endMillis(iResult.getEndMillis())
                    .success(iResult.isSuccess())
                    .parameters(iResult.getParameters())
                    .throwable(iResult.getThrowable())
                    .status(iResult.getStatus())
                    .build()
                    );
        }
        return testResult;
    }
}
