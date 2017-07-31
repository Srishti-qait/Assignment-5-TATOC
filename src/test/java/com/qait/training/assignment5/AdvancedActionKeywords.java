package com.qait.training.assignment5;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.given;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AdvancedActionKeywords extends GetPage{
	public AdvancedActionKeywords(WebDriver driver) {
		super(driver, "Advanced");
	
	}

	public void check()   {
		element("advance").click();    
		wait.hardWait(2);
		
		WebElement element = element("mouse");

		Actions action = new Actions(driver);

		action.moveToElement(element).build().perform();
	
		element("Proceed").click();
	}
	public void check02(String dburl,String username,String Password) throws InterruptedException, SQLException, IOException
	{
		wait.hardWait(2);
		String symbol = element("Symbol").getText().toLowerCase();
	String sign = "";
	DataBaseConnecter base = new DataBaseConnecter();
	base.connectToDataBase(dburl, username ,Password);
		String query = "select *  from identity;";
		String query2 = "select *  from credentials;";


		String myName="";
		String myName2="";
		String name="";

		ResultSet rs = base.getResultSetOnExecutingASelectQuery( query);

		while (rs.next()) {

			if (symbol.equals(rs.getString(2)))

			{
				Thread.sleep(2000);
			 name = rs.getString(1);
				System.out.println(name);
			}
		}
	   ResultSet rs2 = base.getResultSetOnExecutingASelectQuery( query2);
				while (rs2.next()) {

					if (name.equals(rs2.getString(1))) {
						myName = rs2.getString(2);/// username
						myName2 = rs2.getString(3); // password

					
			}

		}

	
		element("name").sendKeys(myName);
		element("passkey").sendKeys(myName2);
		element("submit").click();

		wait.hardWait(2);
		driver.get("http://10.0.1.86/tatoc/advanced/rest");
		String session = element("sessionId").getText().split(":")[1].trim();
        RestAssured.baseURI = "http://10.0.1.86/tatoc/advanced/rest";
		Response res = given().get("/service/token/" + session);
     String id = res.getBody().jsonPath().getJsonObject("token");
      given().formParam("id", session).formParam("signature", id).formParam("allow_access", "1")
				.header("Content-Type", "application/x-www-form-urlencoded").request().post("/service/register");
  	wait.hardWait(2);
		element("Proceed").click();

		element("download").click();
		wait.hardWait(2);
		String TestFile = "C://Users//srishtiagarwal//Downloads//file_handle_test.dat";
		File FC = new File(TestFile);
		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";
		while ((Content = BR.readLine()) != null) {
			if (Content.contains("Signature")) {
				sign = Content.split(":")[1].trim();
			}

		}
		element("signature").sendKeys(sign);
		element("sign_btn ").click();

	}
}