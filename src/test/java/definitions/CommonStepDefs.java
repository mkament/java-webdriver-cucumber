package definitions;

import cucumber.api.java.en.Given;
import pages.*;
import pages.CareersHeader;

public class CommonStepDefs{

    @Given("I open the {string} page")
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