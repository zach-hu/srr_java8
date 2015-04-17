/*
 * Created on Jan 26, 2004
 */
package com.tsa.puridiom.common.documents.tests;

import com.tsa.puridiom.common.documents.OrderType;
import java.lang.reflect.Field;

/**
 * @author Kelli
 */
public class OrderTypeTest
{
	public static void main(String[] args)
	{
		OrderTypeTest.printAllValues();
		OrderTypeTest.toStringTest();
	}
	public static void toStringTest()
	{
		System.out.println("//********** TOSTRINGTEST ***********************/");
		Class type = OrderType.class;
		Field fields[] =  type.getFields();
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				System.out.println(OrderType.toString((String)fields[i].get(type)));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void printAllValues()
	{
		Class type = OrderType.class;
		Field fields[] =  type.getFields();
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				System.out.println(fields[i].get(type) + " = " + fields[i].getName());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
