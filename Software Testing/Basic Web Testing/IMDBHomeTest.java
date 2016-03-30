import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * As a user,
 * I want to be able to navigate using any links on IMDB,
 * so that I can learn more about movies in an efficient manner.
 */

public class IMDBHomeTest {

	static WebDriver driver = new HtmlUnitDriver();

	@Before
	public void setUp() throws Exception {
		driver.get("www.imdb.com");
	}

	//Given that I am on the main page
	//When I view the Title
	//Then I should see that it contains 'IMDb'
	@Test
	public void testShowsCorrectTitle() {
		String title = driver.getTitle();
		assertTrue(title.contains("IMDb"));
	}

	//Given that I am on the main page
	//When I view the top bar
	//I should see that it contains 'Movies', 'TV', 'Showtimes', 'Celebs', 'Events', 'Photos', 'News', 'Community', and 'Watchlist'
	@Test
	public void testHasCorrectHeaderLinks() {
		try {
			driver.findElement(By.linkText("Movies"));
			driver.findElement(By.linkText("TV"));
			driver.findElement(By.linkText("Showtimes"));
			driver.findElement(By.linkText("Celebs"));
			driver.findElement(By.linkText("Events"));
			driver.findElement(By.linkText("Photos"));
			driver.findElement(By.linkText("News"));
			driver.findElement(By.linkText("Community"));
			driver.findElement(By.linkText("Watchlist"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}

	//Given that I am on the main page
	//When I view the top of the page
	//I should see a search bar and search button
	@Test
	public void testHasSearchBar() {
		try {
			driver.findElement(By.id("navbar-query"));
			driver.findElement(By.id("navbar-submit-button"));
		} catch (NoSuchElementException e) {
			fail();
		}
	}

	//Given that I am on the main page
	//When I click on the "Movies" link
	//Then I should be redirected to the movies page
	@Test
	public void testSeeMovies() {
		driver.findElement(By.linkText("Movies")).click();
		String moviesPageTitle = driver.getTitle();
		assertTrue(moviesPageTitle.contains("New Movies"));
	}

	//Given that I am on the main page
	//When I click on the "TV" link
	//Then I should be redirected to the TV page
	@Test
	public void testSeeTV() {
		driver.findElement(By.linkText("TV")).click();
		String moviesPageTitle = driver.getTitle();
		assertTrue(moviesPageTitle.contains("TV"));
	}

	//Given that I am on the main page
	//When I click on the "Showtimes" link
	//Then I should be redirected to the showtimes page
	@Test
	public void testSeeShowtimes() {
		driver.findElement(By.linkText("Showtimes")).click();
		String moviesPageTitle = driver.getTitle();
		assertTrue(moviesPageTitle.contains("Showtimes"));
	}

	//Given that I am on the main page
	//When I click on the "Celebs" link
	//Then I should be redirected to the Celebs page
	@Test
	public void testSeeCelebs() {
		driver.findElement(By.linkText("Celebs")).click();
		String moviesPageTitle = driver.getTitle();
		assertTrue(moviesPageTitle.contains("Most Popular"));
	}

	//Given that I am on the main page
	//When I click on the "Photos" link
	//Then I should be redirected to the photos page
	@Test
	public void testSeePhotos() {
		driver.findElement(By.linkText("Photos")).click();
		String moviesPageTitle = driver.getTitle();
		assertTrue(moviesPageTitle.contains("Photos"));
	}
}