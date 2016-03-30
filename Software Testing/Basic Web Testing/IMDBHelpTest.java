import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * As a user,
 * I want to be able to find and use a help page
 * So that I can answer any questions that I may have about using the web site.
 */

public class IMDBHelpTest {
	
static WebDriver driver = new FirefoxDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://www.imdb.com");
	}
	
	//Given that I am on the main page,
	//When I want to find help,
	//Then I should be able to find the help link.
	@Test
	public void testFindHelp() {
		try {
			driver.findElement(By.linkText("Help"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}
	
	//Given that I want to find help,
	//When I click on the help link,
	//Then I should be redirected to the help page.
	@Test
	public void testHelpLinksToHelp() {
		driver.findElement(By.linkText("Help")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("Help"));
	}
	
	//Given that I clicked on help,
	//When I see the help page,
	//Then I should see sections called "Top Frequently Asked Questions", "Help Sections/FAQs", and "Adding Data to IMDb"
	@Test
	public void testHelpSectionsDisplayed() {
		driver.get("http://www.imdb.com/help/?ref_=nb_hlp");
		String searchDiv1 = driver.findElement(By.id("help_center")).getText();
		String searchDiv2 = driver.findElement(By.id("help_rhs")).getText();
		assertTrue(searchDiv1.contains("Top Frequently Asked Questions"));
		assertTrue(searchDiv2.contains("Help Sections & FAQs"));
		assertTrue(searchDiv2.contains("Adding Data to IMDb"));
	}
	
	//Given that I am on the help page,
	//When I want to look for specific help for my problem,
	//Then I should be able to search for it using a search bar.
	@Test
	public void testHelpSearchBar() {
		driver.get("http://www.imdb.com/help/?ref_=nb_hlp");
		try{
			driver.findElement(By.id("help_search_input"));
		}catch(Exception x){
			fail();
		}
	}
	
	//Given that I am on the help page,
	//When I search for my query and want to know how helpful something will be,
	//Then I should see a score informing me of how relevant the result is.
	@Test
	public void testHelpScores() {
		driver.get("http://www.imdb.com/help/?ref_=nb_hlp");
		driver.findElement(By.linkText("Help")).click();
	    driver.findElement(By.id("help_search_input")).clear();
	    driver.findElement(By.id("help_search_input")).sendKeys("help");
	    driver.findElement(By.cssSelector("input.primary.btn")).click();
	    String search = driver.findElement(By.id("pagecontent")).getText();
	    assertTrue(search.contains("(Score:"));
	}
}