/*
 * Created on Nov 19, 2003
 */
package com.tsa.puridiom.common.documents.tests;

import com.tsa.puridiom.common.documents.DocumentStatus;
import java.lang.reflect.Field;

/**
 * @author renzo
 */
public class DocumentStatusTest
{
	public static void main(String[] args)
	{
		DocumentStatusTest.printAllValues();
		DocumentStatusTest.toStringTest();
		DocumentStatusTest.toValueTest();
	}
	public static void toStringTest()
	{
		System.out.println("//********** TOSTRINGTEST ***********************/");
		Class status = DocumentStatus.class;
		Field fields[] =  status.getFields();
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				System.out.println(DocumentStatus.toString((String)fields[i].get(status)));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void printAllValues()
	{
		Class status = DocumentStatus.class;
		Field fields[] =  status.getFields();
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
				System.out.println(fields[i].get(status) + " = " + fields[i].getName());
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public static void toValueTest()
	{
		System.out.println("//********** TO VALUE TEST ***********************/");
		Class status = DocumentStatus.class;
		Field fields[] =  status.getFields();
		for (int i = 0; i < fields.length; i++)
		{
			try
			{
			    String	statusText = DocumentStatus.toString((String)fields[i].get(status));
				System.out.println(statusText + " = " + DocumentStatus.toValue(statusText, "PURIDIOM"));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
