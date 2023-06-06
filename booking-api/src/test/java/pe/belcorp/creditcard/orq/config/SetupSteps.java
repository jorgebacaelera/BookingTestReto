package pe.belcorp.creditcard.orq.config;

import net.thucydides.core.util.EnvironmentVariables;
import cucumber.api.java.Before;

public class SetupSteps {
    private String theRestApiBaseUrl;
    private EnvironmentVariables environmentVariables;

    @Before
    public void configureBaseUrl() {
        theRestApiBaseUrl = environmentVariables.getProperty("restapi.baseurl");
    }

}
