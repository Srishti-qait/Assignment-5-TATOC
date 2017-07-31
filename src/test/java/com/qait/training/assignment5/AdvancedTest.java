package com.qait.training.assignment5;

import java.io.IOException;
import java.sql.SQLException;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.training.assignment5.TestSessionInitiator;
import static com.qait.training.assignment5.YamlReader.getYamlValue;

public class AdvancedTest{
	  private TestSessionInitiator testSessionInitiator;

	    @BeforeTest
	    public void initializeVariable(){
	        testSessionInitiator = new TestSessionInitiator(this.getClass().getName());
	    }
	    @Test
	    public void test01_verifyUseLoginOn() throws InterruptedException, SQLException, IOException{
	        testSessionInitiator.launchApplication();
	   
	        testSessionInitiator.advancetest.check();
	        testSessionInitiator.advancetest.check02(getYamlValue("dbUrl"),getYamlValue("Username"), getYamlValue("Password"));
}
}