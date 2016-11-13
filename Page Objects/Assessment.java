/*
 * Page Object for the Assessment Page
 */

package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.PageFactory;

public class Assessment extends Site_Methods
{
	private WebDriverWait wait;
	
	@FindBy(id = "btnAssessment")
	private WebElement btnAssessment;

	@FindBy(id = "imageA")
	private WebElement imageA;
	
	@FindBy(id = "imageB")
	private WebElement imageB;
	
	@FindBy(id = "next")
	private WebElement next;
	
	@FindBy(id = "continue")
	private WebElement continueButton;
	
	public Assessment(WebDriver driver) throws Exception 
	{
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, 30);
	}
	
	/**
	 * Completes the new user Assessment by selecting,
	 * all A's except for question 19.
	 */
	public void completeAssessment() throws Exception
	{
		try 
		{
			wait.until(ExpectedConditions.elementToBeClickable(btnAssessment));
			btnAssessment.click();

			for (int i = 1; i < 35; i++) 
			{
				if (i != 19)
				{
					selectAnswer(imageA, next);
					continue;
				}
			
				selectAnswer(imageB, next);
			}

			wait.until(ExpectedConditions.elementToBeClickable(continueButton));
			continueButton.click();
			wait.until(jQueryAJAXCallsHaveCompleted());
		} 
		catch (Exception exc) 
		{
			exc.printStackTrace();
		}
	}
	
	/**
	 * Select an answer and clicks next to proceed to the next question.
	 *
	 * @param imageA2	the question response
	 * @param next2		button that proceeds to next question
	 */
	public void selectAnswer(WebElement imageA2, WebElement next2) throws Exception 
	{
		wait.until(ExpectedConditions.visibilityOf(imageA2));
		imageA2.click();
		wait.until(ExpectedConditions.elementToBeClickable(next2));
		next2.click();
		wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(imageA2)));
	}
}
