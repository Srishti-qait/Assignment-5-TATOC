package com.qait.training.assignment5;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.qait.training.assignment5.GetPage;

public class BasicActionKeywords extends GetPage {
	public BasicActionKeywords(WebDriver driver) {
		super(driver, "Basic");
	}

	public void check_basic() {
		element("basic").click();
		isElementDisplayed("txt_box ");
		element("txt_box ").click();
		wait.hardWait(1);
		switchToFrame("main");
		String color1 = element("btn_box1").getAttribute("class");
		System.out.println(color1);
		switchToFrame("child");
		while (!color1.equals(element("btn_box2 ").getAttribute("class"))) {
			switchToDefaultContent();
			switchToFrame("main");
			element("btn_reload ").click();
			switchToFrame("child");
		}
		switchToDefaultContent();
		switchToFrame("main");
		element("btn_proceed ").click();
		WebElement To = element("btn_dropbox");
		WebElement From = element("btn_dragme");
		dragAndDrop(From, To);
		element("btn_proceed ").click();
		element("btn_launch").click();
		String Mainwindow = switchToMainWindow();
		for (String ChildWindow : switchToMainWindows()) {
			switchToWindow(ChildWindow);
		}
		element("name ").sendKeys("Srishti");
		element("btn_submit").click();
		switchToWindow(Mainwindow);
		element("btn_proceed ").click();
		element("btn_token ").click();
		String token = element("token").getText();
		String token1 = token.split(":")[1];
		Cookie ck = new Cookie("Token", token1.trim());
		cookie(ck);
		element("btn_proceed ").click();

	}
}