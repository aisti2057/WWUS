package page;

import common.BaseSelenium;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.*;

public class FindAMeetingPage extends BaseSelenium {

    @FindBy(id = "meetingSearch")
    public WebElement searchBar;

    @FindBy(className = "meeting-location")
    public List<WebElement> meetingLocationList;

    @FindBy(className = "meeting-information__left")
    public WebElement meetingInformation;

    @FindBy(className = "hours-list-item")
    public List<WebElement> operatingHoursList;

    @FindBy(className = "schedule-detailed-day-meetings")
    public List<WebElement> dayMeetingsList;

    @FindBy(className = "bx-close-xsvg")
    public WebElement closeAd;

    @FindBy(className = "location__top")
    public WebElement location;

    public void findAStudioWithLocation(String location) {
        this.searchBar.sendKeys(location, Keys.ENTER);
    }

    public Map<String, String> findNearestMeeting() {
        Map<String, String> nearestMeeting = new HashMap<>();
        List<String> nearestMeetingInfo = Arrays.asList(this.meetingLocationList.get(0).getText().split("\\R"));
        nearestMeeting.put(nearestMeetingInfo.get(0), nearestMeetingInfo.get(1));
        System.out.println("Nearest meeting: " + nearestMeetingInfo.get(0));
        System.out.println("Distance to nearest meeting: " + nearestMeetingInfo.get(1));
        return nearestMeeting;
    }

    public void clickOnFirstLocation() {
        this.waitForElementToBeClickable(this.location, 10);
        this.location.click();
    }

    public String getMeetingTitle() {
        String title = Arrays.asList(this.meetingInformation.getText().split("\\R")).get(0);
        return title;
    }

    public List<String> getOperatingHours(Day day) {
        List<String> hours = Arrays.asList(this.operatingHoursList.get(day.getNum()).getText().split("\\R"));
        return hours;
    }

    public Day getCurrentDay() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String currentDay = new SimpleDateFormat("EE", Locale.ENGLISH).format(date.getTime());
        Day day = null;
        switch (currentDay) {
            case "Sun": day = Day.SUNDAY;
                break;
            case "Mon": day = Day.MONDAY;
                break;
            case "Tue": day = Day.TUESDAY;
                break;
            case "Wed": day = Day.WEDNESDAY;
                break;
            case "Thu": day = Day.THURSDAY;
                break;
            case "Fri": day = Day.FRIDAY;
                break;
            case "Sat": day = Day.SATURDAY;
                break;
        }
        return day;
    }

    public void printMeetings(Day day) {
        System.out.println(this.dayMeetingsList.get(day.getNum()).getText());
    }

    public void closeAd(int maxWaitMs) throws Exception{
        int currentWait = 0;
        while (currentWait <= maxWaitMs ){
            try {
                if (this.closeAd.isDisplayed()) {
                    this.closeAd.click();
                    break;
                }
            } catch (NoSuchElementException ex) {
                ex.printStackTrace();
            }

            currentWait += 500;
        }
    }


}
