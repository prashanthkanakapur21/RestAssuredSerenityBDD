package com.assignmentThree.utils;

import java.util.Random;

public class TestUtils 
{
	public static String getRandomValue()
	{
		Random rand=new Random();
		int randomInt=rand.nextInt(10000);
		return Integer.toString(randomInt);
	}

}
