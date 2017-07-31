package com.qait.training.assignment5;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.training.assignment5.TestSessionInitiator;

public class TestClass {
	  private TestSessionInitiator testSessionInitiator;

	    @BeforeTest
	    public void initializeVariable(){
	        testSessionInitiator = new TestSessionInitiator(this.getClass().getName());
	    }
	    @Test
	    public void test01_verifyUseLoginOn() throws InterruptedException{
	        testSessionInitiator.launchApplication();
	   
	        testSessionInitiator.basictest.check_basic();

}
}