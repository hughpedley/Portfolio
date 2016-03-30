import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * As a user,
 * I want to be able to view full information about any specific movie,
 * So that I can read as much as possible about that specific movie.
 */

public class IMDBSpecificMovieTest {
	
	//I'm going to use Star Wars Episode VII as my 'specific movie' for all of these tests
	
	static WebDriver driver = new FirefoxDriver();
	
	@Before
	public void setUp() throws Exception {
		driver.get("http://www.imdb.com");
	}
	
	//Given that I am on a specific movie's page
	//When I look at the title
	//Then I should see the name of the movie
	@Test
	public void testShowsMovieTitle() {
		driver.findElement(By.id("navbar-query")).clear();
	    driver.findElement(By.id("navbar-query")).sendKeys("Star Wars: Episode VII");
	    driver.findElement(By.cssSelector("div.suggestionlabel")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("Star Wars: Episode VII"));
	}
	
	//Now that we have shown that this goes to the correct page, we will start all tests at the specific movie page
	
	//Given that I am on a movie's page that I want to see,
	//When I click get showtimes and tickets,
	//I should see a list of theaters showing the movie.
	@Test
	public void testGetShowtimes() {
		driver.get("http://www.imdb.com/title/tt2488496/?ref_=nv_sr_1");
		driver.findElement(By.linkText("Get Showtimes & Tickets")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("The Force Awakens Showtimes"));
	}
	
	//Given that I am on a movie's page,
	//When I click on plot summary,
	//I should be brought to the plot summary page.
	@Test
	public void testGetPlotSummary() {
		driver.get("http://www.imdb.com/title/tt2488496/?ref_=nv_sr_1");
		driver.findElement(By.linkText("Plot Summary")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("The Force Awakens (2015) - Plot Summary"));
		//It seriously has patricide under plot keywords? Talk about spoilers...
	}
	
	//Given that I am on a movie's page,
	//When I click to see the full cast of a movie,
	//I should be brought to the full cast page
	@Test
	public void testGetCast() {
		driver.get("http://www.imdb.com/title/tt2488496/?ref_=nv_sr_1");
		driver.findElement(By.linkText("See full cast")).click();
		String title = driver.getTitle();
		assertTrue(title.contains("The Force Awakens (2015) - Full Cast & Crew"));
	}
	
	//Given that I am on a movie's page,
	//When I look at the list of additional photos and videos,
	//Then I should have the ability to navigate to these separate pages specific to the movie
	@Test
	public void testAdditionalMedia() {
		driver.get("http://www.imdb.com/title/tt2488496/?ref_=nv_sr_1");
		WebElement additionalMedia = driver.findElement(By.id("titleMediaStrip"));
		try {
			additionalMedia.findElement(By.partialLinkText("videos"));
	    	additionalMedia.findElement(By.partialLinkText("photos"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}
}