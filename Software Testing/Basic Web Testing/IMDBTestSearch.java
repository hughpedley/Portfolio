import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * As a user,
 * I want to be able to search effectively for a movie or TV show,
 * So that I can efficiently find what I am looking for.
 */

public class IMDBTestSearch {
	
	static WebDriver driver = new FirefoxDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://www.imdb.com");
	}
	
	//Given that I enter nothing in the search bar,
	//When I attempt to search,
	//Then page will inform me to enter a word or phrase to search on.
	@Test
	public void testEmpty() {
		driver.findElement(By.id("navbar-submit-button")).click();
	    assertEquals("Enter a word or phrase to search on.", driver.findElement(By.xpath("/html/body/div[1]/div/div[4]/div[3]/div[1]/div")).getText());
	}
	
	//Given that I am on the main page,
	//When I attempt to search for a nonsense string of characters,
	//Then I will be told "No results found for " the input I entered.
	@Test
	public void testNoResults() {
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("nonsense_nonsense_12431235123451235wad");
	    driver.findElement(By.id("navbar-submit-button")).click();
	    String myInput = "nonsense_nonsense_12431235123451235wad";
	    String no_result = driver.findElement(By.className("findHeader")).getText();
	    assertEquals(no_result, "No results found for \""+ myInput +"\"");
	}
	
	//Given that I am on the main page,
	//When I click advanced search from the dropdown menu next to the search bar,
	//Then I will be redirected to a new page for the advanced search and subsequent options.
	@Test
	public void testAdvancedSearch() {
		new Select(driver.findElement(By.id("quicksearch"))).selectByVisibleText("Advanced Search »");
		try {
			driver.findElement(By.linkText("Advanced Title Search"));
			driver.findElement(By.linkText("Advanced Name Search"));
			driver.findElement(By.linkText("Collaborations and Overlaps"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}
	
	//Given that I am on the main page,
	//When I attempt to search for something,
	//Then the site will bring me to search results informing me of how many results were found for my search.
	@Test
	public void testDisplayingResults() {
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("Star Wars The Force Awakens");
	    driver.findElement(By.id("navbar-submit-button")).click();
	    String search = driver.findElement(By.xpath("//input[@id='navbar-query']")).getAttribute("value");
	    String displayedSearch = driver.findElement(By.className("findSearchTerm")).getText();
	    assertTrue(displayedSearch.contains(search));
	}
	
	//Given that I want to search for a specific movie or TV show,
	//When I attempt to search for my input,
	//Then I should be given the option to filter results to exactly match my query.
	@Test
	public void testExactTitle() {
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("Star Wars");
	    driver.findElement(By.id("navbar-submit-button")).click();
	    try {
	    	driver.findElement(By.linkText("Exact title matches"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}
}