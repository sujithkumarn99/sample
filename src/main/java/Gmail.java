package main.java;

import test.java.com.crossover.e2e.GMailTest;

public class Gmail extends GMailTest {
	
		public static void main(String[] args) throws Exception
		{
			GMailTest gmail = new GMailTest();
			gmail.setUp();
			gmail.sendEmail();
			gmail.verifySentMail();
			gmail.tearDown();
		}
	}


