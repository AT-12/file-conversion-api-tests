package org.fundacionjala.fc.hooks;

import io.cucumber.java.Before;
import org.testng.SkipException;

public class CommonHooks {

    /**
     * Throws exception when a test scenario is skipped.
     */
    @Before(value = "@skipTest", order = 0)
    public void skipTestScenario() {
        throw new SkipException("Exception: Scenario skipped.");
    }
}
