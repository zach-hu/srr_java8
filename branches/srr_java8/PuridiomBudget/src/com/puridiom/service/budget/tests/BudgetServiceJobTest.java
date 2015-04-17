package com.puridiom.service.budget.tests;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tsa.puridiom.entity.BudgetCenter;
import com.puridiom.service.budget.BudgetAction;
import com.puridiom.service.budget.BudgetReturn;
import com.puridiom.service.budget.BudgetServiceJob;
import com.puridiom.service.budget.BudgetWrapper;
import com.puridiom.service.budget.exceptions.BudgetLockExpired;
import com.puridiom.service.budget.exceptions.BudgetLockTokenBad;

public class BudgetServiceJobTest
{
	public static String organizationId = "DTN07P";

	public static void testWildcards()
	{
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

		String budget1Array[] = new String[]{"752","617646","695999","2","3", "4", "", "", "", "", "", "", "", "", ""};
		Object budgetArray[] = new Object[1];
		budgetArray[0] = budget1Array;

		System.err.println("get and set budgetA for update");
		BudgetReturn budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(true, budgetArray, "renzo", "test");
		boolean gotBudget = true;
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget Balance: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			int budgetException = budgetReturn.getBudgetException(budgetArray[i]);
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Done with Exceptions");
				return;
			}
		}
		//*******************************************************************
		//Get a second budget. A different budget but belongs to the same wildcard budget
		//*******************************************************************

		/*String budget2Array[] = new String[]{"0145", "0000", "18021", "0001", "101020", "", "", "", "", "", "", "", "", "", ""};
		budgetArray[0] = budget2Array;

		System.err.println("get and set budgetB for update");
		budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(true, budgetArray, "renzo", "dtn07p");
		gotBudget = true;
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget Balance: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			int budgetException = budgetReturn.getBudgetException(budgetArray[i]);
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("if you see this you are good");
				System.err.println("Done with Exceptions");
				return;
			}
		}
		*/


	}

	public static void main(String[] args) {
		//BudgetServiceJobTest.testFindBudgetFromWildcard(null);
		BudgetServiceJobTest.testWildcards();
		//BudgetServiceJobTest.testComplete(null);
		//BudgetServiceJobTest.testBudgetExceptions();

	}
	public static void testFindBudgetFromWildcard(String[] args)
	{
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();
		String budget1Array[] = new String[]{"752","617646","695999","2","3", "4", "", "", "", "", "", "", "", "", ""};

		Object budgetArray[] = new Object[1];
		budgetArray[0] = budget1Array;
		BudgetCenter budgetCenter = budgetServiceJob.getBudgetFromWildcards(budget1Array, organizationId);
		if(budgetCenter == null)
		{
			System.err.println("BUdget not found on wildcards");
		}
		else
		{
			System.out.println("found: " + budgetCenter.getBudgetId());
		}

	}
	public static String getKeyAsString(Object budgetKey[])
	{
		StringBuffer _budgetKey = new StringBuffer();

		for (int i = 0; i < budgetKey.length; i++)
		{
			_budgetKey.append(budgetKey[i]);
		}
		return _budgetKey.toString();
	}

	public static void testBudgetExceptions()
	{
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

		String budget1Array[] = new String[]{"0145", "0000", "18021", "0001", "101010", "", "", "", "", "", "", "", "", "", ""};
		String budget2Array[] = new String[]{"0145", "0000", "18021", "0001", "101020", "", "", "", "", "", "", "", "", "", ""};
		String budget3Array[] = new String[]{"0145", "0000", "18021", "0001", "101030", "", "", "", "", "", "", "", "", "", ""};
		Object budgetArray[] = new Object[3];
		budgetArray[0] = budget1Array;
		budgetArray[1] = budget2Array;
		budgetArray[2] = budget3Array;

		System.err.println("get and set budgetA for update");
		BudgetReturn budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(true, budgetArray, "renzo", "dtn07p");
		boolean gotBudget = true;
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget Balance: " + BudgetServiceJobTest.getKeyAsString((Object[])budgetArray[i]) + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			System.out.println("token: " + budgetReturn.getLockingToken());
			int budgetException = budgetReturn.getBudgetException(budgetArray[i]);
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Get Done with Exceptions. Will exit");
				return;
			}
		}

		//******************** check budget **********************************
		Object budgetStringAction[] = new Object[3];
		budgetStringAction[0] = budget1Array;
		budgetStringAction[1] = budget2Array;
		budgetStringAction[2] = budget3Array;
		BigDecimal amounts[] = new BigDecimal[]{ new BigDecimal(40),
				  new BigDecimal(-45),
				  new BigDecimal(10)
				};
		int checkResults[] = new int[3];
		String authorities[] = new String[]{"1000A",
																	"1000A",
																	"1000Axxx"
																	};

		try
		{
			System.err.println("****************************** checking budget ***********************************************************");

//			checkResults = budgetServiceJob.budgetCheck(budgetStringAction,
//					authorities,
//					amounts,
//					"dtn07p");
			System.err.println("******************************** done checking budget ******************************************************");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*********************** results **************************
		gotBudget = true;
		for (int i = 0; i < checkResults.length; i++)
		{
			System.out.println("Budget Balance: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			int budgetException = checkResults[i];
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Check Done but with Exceptions!!!!!!!!!!");
				return;
			}
		}
		//*********************** end results **********************
		//******************** end check budget ******************************

	}

	public static void testComplete(String[] args)
	{
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

		String budget1Array[] = new String[]{"0601", "0000", "18330", "0001", "101010", "", "", "", "", "", "", "", "", "", ""};
		String budget2Array[] = new String[]{"0145", "0000", "18021", "0001", "101020", "", "", "", "", "", "", "", "", "", ""};
		String budget3Array[] = new String[]{"0145", "0000", "18021", "0001", "101030", "", "", "", "", "", "", "", "", "", ""};
		Object budgetArray[] = new Object[3];
		budgetArray[0] = budget1Array;
		budgetArray[1] = budget2Array;
		budgetArray[2] = budget3Array;

		System.err.println("get and set budgetA for update");
		BudgetReturn budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(true, budgetArray, "renzo", "dtn07p");
		boolean gotBudget = true;
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget Balance: " + BudgetServiceJobTest.getKeyAsString((Object[])budgetArray[i]) + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			System.out.println("token: " + budgetReturn.getLockingToken());
			int budgetException = budgetReturn.getBudgetException(budgetArray[i]);
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException < 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Get Done with Exceptions. Will exit");
				return;
			}
		}

		//******************** check budget **********************************
		Object budgetStringAction[] = new Object[3];
		budgetStringAction[0] = budget1Array;
		budgetStringAction[1] = budget2Array;
		budgetStringAction[2] = budget3Array;
		BigDecimal amounts[] = new BigDecimal[]{ new BigDecimal(30),
				  new BigDecimal(-30),
				  new BigDecimal(10)
				};
		String authorities[] = new String[]{"1000A",
				"1000A",
				"1000A"
				};
		int checkResults[] = new int[3];
		try
		{
			System.err.println("****************************** checking budget ***********************************************************");

//			checkResults = budgetServiceJob.budgetCheck(budgetStringAction,
//					authorities,
//					amounts,
//					"dtn07p");
			System.err.println("******************************** done checking budget ******************************************************");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*********************** results **************************
		gotBudget = true;
		for (int i = 0; i < checkResults.length; i++)
		{
			System.out.println("Budget Balance: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			int budgetException = checkResults[i];
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Check Done but with Exceptions!!!!!!!!!!");
				return;
			}
		}
		//*********************** end results **********************
		//******************** end check budget ******************************

		try
		{
			System.err.println("****************************** update budget ***********************************************************");
			Map updateArgs = new HashMap();
			updateArgs.put("headerIc", new BigDecimal("12860709100029"));
			BigDecimal icLine[] = new BigDecimal[budgetStringAction.length];
			icLine[0] = new BigDecimal("13068884100015");
			icLine[1] = new BigDecimal("13068884100015");
			icLine[2] = new BigDecimal("13068884100015");
			updateArgs.put("lineIc", icLine);
			updateArgs.put("formType", "REQ");

			budgetServiceJob.updateBudget(budgetStringAction, budgetReturn.getLockingToken(),
					new String[]{ String.valueOf(BudgetAction.PREENCUMBRANCE),
											String.valueOf(BudgetAction.ENCUMBRANCE),
											String.valueOf(BudgetAction.EXPENSED)},
											amounts,
				updateArgs, "dtn07p");
			System.err.println("****************************** done update budget ***********************************************************");
		}
		catch (BudgetLockExpired e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BudgetLockTokenBad e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.err.println("****************************** unlock budget ***********************************************************");
		budgetServiceJob.unlockBudget(budgetStringAction, budgetReturn.getLockingToken(), "dtn07p");
		System.err.println("****************************** done unlock budget ***********************************************************");


		budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(false, budgetArray, "renzo", "dtn07p");
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
		}
		//*********************Budget State ***********************
		budgetServiceJob.getState("dtn07p");
		//*********************end Budget State ***********************



		/*if(!budgetReturn.isBudgetLocked(budgetArray))
		{
			System.err.println("balance: " + budgetReturn.getBudgetBalance(budgetArray));
			try
			{
				//budgetServiceJob.updateBudget(budgetArray, budgetReturn.getLockingToken(), BudgetAction.PREENCUMBRANCE, new BigDecimal(10000), "dtn07p");
			}
			catch (BudgetLockExpired e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BudgetLockTokenBad e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*?
		System.err.println("done");
	}

	public static void mainWithReturnToken(String[] args)
	{/*
		System.out.println("start test");
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();
		BigDecimal lockingToken = new BigDecimal(0);
		try
		{
			System.out.println("get and set budgetA for update");
			lockingToken = (BigDecimal)budgetServiceJob.getBudget(true, new String[]{"5566", "7777", "66000", "0001", "1000", "", "", "", "", "", "", "", "", "", ""}, "renzo", "dtn07p");

		}
		finally{}
//		catch (BudgetLockedException e)
//		{
//			// TODO will need to set a retry.
//			e.printStackTrace();
//		}

		try {
			System.out.println("sleeping");
			Thread.sleep(3000);
			System.out.println("waking up");
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try
		{
			budgetServiceJob.updateBudget(new BigDecimal("14545546000024"), lockingToken, BudgetAction.PREENCUMBRANCE, new BigDecimal(10000), "dtn07p");

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	public static void main1(String[] args)
	{
		System.out.println("start test");
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

		BudgetWrapper budgetWrapperGood;
		try
		{
			System.out.println("get and set budgetA for update");

			budgetWrapperGood = (BudgetWrapper)budgetServiceJob.getBudget(true, new String[] {"budgetA"}, "userA", "hilton");
			System.out.println("locked: " + budgetWrapperGood.isLocked());
			//System.out.println("Can I udpate with you? " + budgetServiceJob.updateBudget(budgetWrapperGood, "hilton"));

		}
		finally{}
//		catch (BudgetLockedException e)
//		{
//			// TODO will need to set a retry.
//			e.printStackTrace();
//		}
		System.out.println("can I get it again?");
		try
		{
			BudgetWrapper budgetWrapperBad = (BudgetWrapper)budgetServiceJob.getBudget(true,  new String[] {"budgetA"}, "userA", "hilton");
			System.out.println("wow something is not right!");
		}
		finally{}
//		catch (BudgetLockedException e)
//		{
//			System.out.println("I should be here");
//		}
		System.out.println("can I just look at it?");
		try {
			BudgetWrapper budgetWrapperRead = (BudgetWrapper)budgetServiceJob.getBudget(false,  new String[] {"budgetA"}, "userA", "hilton");
			System.out.println("I should be able to look at it and it should not be locked");
			System.out.println("locked: " + budgetWrapperRead.isLocked());
			//System.out.println("Can I udpate with you? " + budgetServiceJob.updateBudget(budgetWrapperRead, "hilton"));
		}
		finally{}
//		catch (BudgetLockedException e)
//		{
//			System.out.println("wow something is not right! I just wanted to look");
//		}


		System.out.println("end test");
	}

	public static void mainTestLocking(String[] args)
	{
		BudgetServiceJob budgetServiceJob = BudgetServiceJob.getInstance();

		String budget1Array[] = new String[]{"0130", "0000", "18021", "0001", "101010", "", "", "", "", "", "", "", "", "", ""};
		Object budgetArray[] = new Object[1];
		budgetArray[0] = budget1Array;

		System.err.println("get and set budgetA for update");
		BudgetReturn budgetReturn = (BudgetReturn)budgetServiceJob.getBudget(true, budgetArray, "renzo", "dtn07p");
		boolean gotBudget = true;
		for (int i = 0; i < budgetArray.length; i++)
		{
			System.out.println("Budget Balance: " + budgetArray[i] + " - " + budgetReturn.getBudgetBalance(budgetArray[i]));
			int budgetException = budgetReturn.getBudgetException(budgetArray[i]);
			System.out.println("Budget Exception: " + budgetArray[i] + " - " + budgetException);
			if(budgetException != 0)
			{
				gotBudget = false;
			}
			if(!gotBudget)
			{
				System.err.println("Done with Exceptions");
//				return;
			}
		}
		//******************** check budget **********************************
		Object budgetStringAction[] = new Object[3];
		budgetStringAction[0] = budget1Array;
		budgetStringAction[1] = budget1Array;
		budgetStringAction[2] = budget1Array;
		try
		{
			System.err.println("****************************** checking budget ***********************************************************");

//			int results[] = budgetServiceJob.budgetCheck(budgetStringAction, new String[3], new BigDecimal[]{ new BigDecimal(20),  new BigDecimal(10), new BigDecimal(10)}, "dtn07p");
//			for (int i = 0; i < results.length; i++) {
//				System.out.println(budgetStringAction[i] + ": " + results[i]);
//			}
			System.err.println("******************************** done checking budget ******************************************************");
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//******************** end check budget ******************************

	}

}
