package org.redhat.qe.jaeger.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.redhat.qe.jaeger.api.model.TestData;
import org.redhat.qe.jaeger.api.model.test.TestResult;
import org.redhat.qe.jaeger.tests.SimpleSpanTest;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * @author Jeeva Kandasamy (jkandasa)
 * @since 1.0.0
 */
public class TestEngineApi {

    public TestResult executeSimpleSpanTest(TestData testData) {
        HashMap<String, List<String>> tests = new HashMap<String, List<String>>();
        tests.put("Simple Span Test", Arrays.asList(SimpleSpanTest.class.getName()));
        return executeTestSuite(testData, "Jaeger Test Suite - Simple", tests);
    }

    private Map<String, String> getSuiteParameters(TestData testData) {
        Map<String, String> map = new HashMap<String, String>();
        map.put("jaegerServerHost", testData.getConfig().getServerHost());
        map.put("jaegerAgentHost", testData.getConfig().getAgentHost());
        map.put("jaegerZipkinThriftPort", String.valueOf(testData.getConfig().getAgentZipkinThriftPort()));
        map.put("jaegerAgentCompactPort", String.valueOf(testData.getConfig().getAgentCompactPort()));
        map.put("jaegerAgentBinaryPort", String.valueOf(testData.getConfig().getAgentBinaryPort()));
        map.put("jaegerZipkinCollectorPort", String.valueOf(testData.getConfig().getZipkinCollectorPort()));
        map.put("jaegerQueryPort", String.valueOf(testData.getConfig().getQueryPort()));
        map.put("flushInterval", String.valueOf(testData.getConfig().getFlushInterval()));
        map.put("serviceName", String.valueOf(testData.getServiceName()));
        return map;
    }

    private TestResult executeTestSuite(TestData testData, String suiteName, Map<String, List<String>> testsRaw) {
        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite suite = new XmlSuite();
        suite.setName("Jaeger Test Suite");
        suite.setParameters(getSuiteParameters(testData));

        //Create a list of XmlTests and add the Xmltest you created earlier to it.
        List<XmlTest> tests = new ArrayList<XmlTest>();
        for (String testName : testsRaw.keySet()) {
            //Create an instance of XmlTest and assign a name for it.
            XmlTest test = new XmlTest(suite);
            test.setName(testName);

            //Add any parameters that you want to set to the Test.
            test.setParameters(testData.getTestParameters());

            //Create a list which can contain the classes that you want to run.
            List<XmlClass> classes = new ArrayList<XmlClass>();
            for (String clazz : testsRaw.get(testName)) {
                classes.add(new XmlClass(clazz));
            }

            //Assign that to the XmlTest Object created earlier.
            test.setXmlClasses(classes);
            tests.add(test);
        }

        //add the list of tests to your Suite.
        suite.setTests(tests);

        //Add the suite to the list of suites.
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        mySuites.add(suite);

        //add listener
        testng.addListener(tla);

        //Set the list of Suites to the testNG object you created earlier.
        testng.setXmlSuites(mySuites);
        testng.setUseDefaultListeners(useDefaultListeners()); // Enable/Disable default "test-output" directory
        testng.run();
        return TestResult.get(tla);
    }

    private boolean useDefaultListeners() {
        if (System.getProperty("useDefaultListeners") != null) {
            return Boolean.valueOf(System.getProperty("useDefaultListeners"));
        }
        return true;
    }

}
