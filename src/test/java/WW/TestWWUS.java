package WW;

import common.BaseSelenium;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.Day;
import page.FindAMeetingPage;
import page.HomePage;

import java.util.List;
import java.util.Map;

public class TestWWUS extends BaseSelenium {
    @Test
    public void testScenario() throws Exception {
        HomePage hp = PageFactory.initElements(driver, HomePage.class);
        FindAMeetingPage famp = PageFactory.initElements(driver, FindAMeetingPage.class);
        int maxWaitMs = 15000;

        // 1. Navigate to https://www.weightwatchers.com/us/
        String url = "https://www.weightwatchers.com/us/";
        driver.navigate().to(url);

        // 2. Verify loaded page title matches “WW (Weight Watchers): Weight Loss & Wellness Help”
        String actualTitle = driver.getTitle();
        String expectedTitle = "WW (Weight Watchers): Weight Loss & Wellness Help";
        Assert.assertEquals(actualTitle, expectedTitle);

        // 3. On the right corner of the page, click on “Find a Studio”
        hp.navigateToFindAStudio();
        this.waitForElementToBeClickable(famp.searchBar, 15);
        famp.closeAd(maxWaitMs);

        // 4. Verify loaded page title contains “Find WW Studios & Meetings Near You | WW USA”
        actualTitle = driver.getTitle();
        expectedTitle = "Find WW Studios & Meetings Near You | WW USA";
        Assert.assertEquals(actualTitle, expectedTitle);

        // 5. In the search field, search for meetings for zip code: 10011
        famp.findAStudioWithLocation("10011");

        // 6. Print the title of the first result and the distance (located on the right of location title/name)
        Map<String, String> nearestMeeting = famp.findNearestMeeting();
        String nearestMeetingName = "";
        for (String title : nearestMeeting.keySet()) {
            nearestMeetingName = title;
        }

        // 7. Click on the first search result and then, verify displayed location name/title matches
        // with the name of the first searched result that was clicked.
        famp.clickOnFirstLocation();
        Assert.assertEquals(nearestMeetingName, famp.getMeetingTitle());

        // 8. From this location page, print TODAY’s hours of operation (located towards the bottom of the page)
        Day currentDay = famp.getCurrentDay();
        List<String> hours = famp.getOperatingHours(currentDay);
        System.out.println("Opening hours for today:");
        for (String info : hours) {
            System.out.println(info);
        }

        // 9. Create a method to print the number of meeting the each person(under the scheduled time)
        // has a particular day of the week
        //e.g. printMeetings("Sun")
        famp.printMeetings(currentDay);
    }

}

