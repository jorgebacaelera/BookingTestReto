package pe.belcorp.creditcard.orq;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import net.thucydides.core.annotations.Managed;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/features/BookingAPIs.feature",
glue = { "pe.belcorp.creditcard" })
public class PaymentsAPI {
}
