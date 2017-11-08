package com.project;

import com.project.Wicket.HomePage;
import com.project.Wicket.MainPage;
import com.project.Wicket.WicketApplication;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(MainPage.class);

		//assert rendered page class
		tester.assertRenderedPage(MainPage.class);
	}
}
