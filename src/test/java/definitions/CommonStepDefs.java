package definitions;

import cucumber.api.java.en.Given;
import pages.*;

public class CommonStepDefs {

    @Given("I open {string} page")
    public void iOpenPage(String page) throws InterruptedException {
        switch (page) {
            case "quote":
                new QuoteForm().open();
                break;
            case "usps":
                new UspsHome().open();
                break;
            case "careers":
                new CareersHeader().open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }
}