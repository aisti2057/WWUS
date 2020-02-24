package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    @FindBy(className = "find-a-meeting")
    public WebElement findAStudioLink;

    public void navigateToFindAStudio() {
        this.findAStudioLink.click();
    }

}
