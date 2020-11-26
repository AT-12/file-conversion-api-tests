package org.fundacionjala.fc.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.fundacionjala.fc.config.Environment;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

/**
 * Cucumber TestNG runner class.
 */
@CucumberOptions(
        plugin = {"pretty"},
        glue = {"org.fundacionjala.fc"},
        features = {"src/test/resources/features"}
)
public final class Runner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
    /**
     * Executes code before all scenarios.
     */
    @BeforeTest
    public void beforeAllScenarios() {
        System.setProperty("dataproviderthreadcount", Environment.getInstance().getCucumberThreadCount());
    }

    /**
     * Executes code after all scenarios.
     */
    @AfterTest
    public void afterAllScenarios() {
        // Code executed after features execution.
    }
}
