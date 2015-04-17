package com.puridiom.service.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.property.PropertiesManager;
import com.puridiom.service.budget.exceptions.BudgetDoesNotExist;
import com.puridiom.service.budget.exceptions.BudgetLockExpired;
import com.puridiom.service.budget.exceptions.BudgetLockTokenBad;
import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.UniqueKeyGenerator;
import com.tsagate.foundation.utility.Utility;

public class BudgetServiceJob
{
	private static BudgetServiceJob instance;
	private Map budgetCache = new HashMap();
	private Map budgetIdCache = new HashMap();
	private Map budgetCacheById = new HashMap();
	private Map wildcardsCache = new HashMap();
	private BigDecimal unlockPassword = new BigDecimal("14846208400080");
	private int countExpiredLocks = 0;
	private Map budgetUdfCache = new HashMap();

	private void setBudgetUdfList(String organizationId)
	{
		List budgetUdfList = new ArrayList();
		String sUdf = "";
		for(int li = 0; li < 15; li++)
		{
			sUdf = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "Budget Udf" + String.valueOf(li + 1),"BUDGET_" + String.valueOf(li + 1) );
			if(Utility.isEmpty(sUdf))	sUdf = "budget_" + String.valueOf(li + 1);
			budgetUdfList.add(sUdf);
		}
		this.budgetUdfCache.put(organizationId, budgetUdfList);
	}

	private void setWildcardsCache(String budgetYear, String organizationId)
	{
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("budgetServiceYear", budgetYear);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budget-service-retrieve-wildcards.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				List wildcardList = (List)incomingRequest.get("wildcardList");
				this.wildcardsCache.put(organizationId, wildcardList);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}//END setWildcardsCache

	private BudgetCenter setWildcardsFromTriger(Object budgetKey, String organizationId)
	{
		BudgetCenter budgetCenter = null;
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budget-service-trigger-retrieve-wildcards.xml");
			incomingRequest.put("budgetId", budgetKey);
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				budgetCenter = (BudgetCenter)incomingRequest.get("wilcardBudget");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return budgetCenter;
	}

	private String getValueFromBudget(BudgetCenter budgetCenter, int fldIndex)
	{
		String value = "";
		switch (fldIndex) {
		case 1:
			value = budgetCenter.getBudget1();
			break;
		case 2:
			value = budgetCenter.getBudget2();
			break;
		case 3:
			value = budgetCenter.getBudget3();
			break;
		case 4:
			value = budgetCenter.getBudget4();
			break;
		case 5:
			value = budgetCenter.getBudget5();
			break;
		case 6:
			value = budgetCenter.getBudget6();
			break;
		case 7:
			value = budgetCenter.getBudget7();
			break;
		case 8:
			value = budgetCenter.getBudget8();
			break;
		case 9:
			value = budgetCenter.getBudget9();
			break;
		case 10:
			value = budgetCenter.getBudget10();
			break;
		case 11:
			value = budgetCenter.getBudget11();
			break;
		case 12:
			value = budgetCenter.getBudget12();
			break;
		case 13:
			value = budgetCenter.getBudget13();
			break;
		case 14:
			value = budgetCenter.getBudget14();
			break;
		case 15:
			value = budgetCenter.getBudget15();
			break;

		default:
			break;
		}
		return value;
	}

	public BudgetCenter getBudgetFromWildcardsTrigger(Object budgetKey, String organizationId)
	{
		return this.setWildcardsFromTriger(budgetKey, organizationId);
	}

	public BudgetCenter getBudgetFromWildcards(Object budgetKey, String organizationId)
	{
		String budgetValues[] = (String[])budgetKey;
		this.setWildcardsCache(budgetValues[0], organizationId);
		List wildcardList = (List)this.wildcardsCache.get(organizationId);
		if(wildcardList == null){		return null;	}
		boolean budgetFound = false;
		boolean valueFound = false;
		BudgetCenter budgetCenter = null;
		for(int i = 0; i < wildcardList.size(); i++ )
		{
			BudgetCenter wildcardBudget = (BudgetCenter)wildcardList.get(i);
			for(int valueIndex = 1; valueIndex < budgetValues.length; valueIndex++)
			{
				String value = budgetValues[valueIndex];
				String field = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "BUDGET UDF" + String.valueOf(valueIndex), "");

				if(!HiltonUtility.isEmpty(field))
				{
					String wildValue = this.getValueFromBudget(wildcardBudget, valueIndex);
					if(wildValue.equalsIgnoreCase("*") || wildValue.equalsIgnoreCase(value))
					{
						valueFound = true;
					}
					else
					{
						valueFound = false;
						valueIndex = budgetValues.length;
					}
				}
			}
			//we found a budget that matches all of the values from the key provided
			if(valueFound)
			{
				budgetFound = true;
				i = wildcardList.size();
				budgetCenter = wildcardBudget;
			}
		}//end wildcardList for

		return budgetCenter;
	}

	private BudgetServiceJob()
	{
		//TODO mark budgetjob as started.
	}

	public static final BudgetServiceJob getInstance()
	{
		if( BudgetServiceJob.instance == null)
		{
			BudgetServiceJob.instance = new BudgetServiceJob();
		}
		return BudgetServiceJob.instance;
	}

	public BudgetWrapper getBudgetLocal(Object budgetKey, String organizationId)
	{
		organizationId = organizationId.toUpperCase();
		Log.info(this, "BudgetLocal: " + organizationId);
		if(this.budgetCache.containsKey(organizationId))
		{
			Map organizationIdMap = (Map)this.budgetCache.get(organizationId);
			Log.info(this, "BudgetLocal: " + organizationIdMap + "---" + budgetKey);
			if(organizationIdMap != null)
	        {
				BudgetWrapper budgetWrapper = (BudgetWrapper)organizationIdMap.get(budgetKey);

				Map budgetCacheByIdMap = (Map)this.budgetCacheById.get(organizationId);
				Log.info(this, "BudgetLocal: " + budgetWrapper);
				Log.info(this, "BudgetLocal: " + budgetCacheByIdMap);
				if(budgetWrapper != null)
				{
					Log.info(this, "BudgetLocal: " + budgetWrapper.getBudgetId());
					if(budgetCacheByIdMap.containsKey(budgetWrapper.getBudgetId()))
					{
						return (BudgetWrapper)budgetCacheByIdMap.get(budgetWrapper.getBudgetId());
					}
				}
	        }
		}
		return null;
	}

	/**
	 * unlockBudget - convenience method to unlock a particular budget from the admin module..
	 * Unlocks the budget for use by other users.
	 * @param budgetKey
	 * @param updateToken
	 * @param organizationId
	 */
	public boolean unlockBudget(String budgetKey, BigDecimal updateToken, String organizationId)
	{
		BudgetWrapper budgetWrapperCache = this.getBudgetLocal(budgetKey, organizationId);
		if (updateToken.compareTo(this.unlockPassword) == 0)
		{
			this.unlockBudget(budgetWrapperCache, organizationId);
			return true;
		}
		return false;
	}
	/**
	 * Unlocks the budget for use by other users.
	 * @param budgetKey
	 * @param updateToken
	 * @param organizationId
	 */
	public void unlockBudget(Object budgetKey[], Object updateToken, String organizationId)
	{
		organizationId = organizationId.toUpperCase();
		for (int i = 0; i < budgetKey.length; i++)
		{
			BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[])budgetKey[i]), organizationId);
			if(budgetWrapperCache != null)
			{
				if (budgetWrapperCache.isUpdatable((BigDecimal)updateToken))
				{
					this.unlockBudget(budgetWrapperCache, organizationId);
				}
			}
		}
	}

	private void unlockBudget(BudgetWrapper budgetWrapper, String organizationId)
	{
		BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[])budgetWrapper.getBudgetKey()), organizationId);
		if( budgetWrapperCache != null )
		{
			budgetWrapperCache.setLocked(false, "", new BigDecimal(0));
		}
	}

	public synchronized Map getBudgetCache(String organizationId)
	{
		return  (Map)this.budgetCache.get(organizationId.toUpperCase());
	}

	private boolean updateBudget(BudgetWrapper budgetWrapper[], String budgetAction[], BigDecimal amount[], Map args, String organizationId)
	{
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("budgetCenter", budgetWrapper);
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("budgetAction", budgetAction);
			incomingRequest.put("budgetAmount", amount);
			incomingRequest.put("updateArgs", args);

			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budgetCenterServiceUpdate.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				budgetWrapper = (BudgetWrapper[])incomingRequest.get("budgetWrapper");
				this.updateBudgetCenterOnCache(budgetWrapper, organizationId);
				//TODO need to update all budgetcenters
				return true;
			}
			else
			{
				return false;
			}
		}
		catch (Exception exception)
		{
			return false;
		}
	}
	/**
	 * Updates a Budget if the token is correct and has not expired.
	 * 				- Does not unlock the Budget.
	 * @param budgetKey
	 * @param updateToken
	 * @param budgetAction
	 * @param amount
	 * @param args:
	 * 		*** BigDecimal icHeader: Document Header IC,
	 *  		*** BigDecimal icLine[]: Document Line ICs.
	 *  	   	*** String formType: REQ/PO/INV
     *         *** Note that this will be the same for some accounts
     *         *** The length of this array should be the same length than BudgetKey/BudgetActions.. etc.
     *         *** Keys should be: headerIc
     *         *** lineIc.
	 * @param organizationId
	 * @return
	 * @throws Exception
	 */
	public boolean updateBudget(Object budgetKey[], Object updateToken, String budgetAction[], BigDecimal amount[], Map args, String organizationId) throws BudgetLockExpired, BudgetLockTokenBad
	{
		organizationId = organizationId.toUpperCase();
		if((budgetKey.length != budgetAction.length) || (budgetKey.length != amount.length)) return false;
		BudgetWrapper budgetWrapperArray[] = new BudgetWrapper[budgetKey.length];
		for (int i = 0; i < budgetKey.length; i++)
		{
			BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[])budgetKey[i]), organizationId);
			Log.info(this, "budgetWrapperCache: " + budgetWrapperCache + ", organization: " + organizationId);
			if(budgetWrapperCache != null)
			{
				Log.info(this, "budgetWrapperCache: " + budgetWrapperCache + ", organization: " + organizationId);
				if(budgetWrapperCache.isLockExpired())
				{
					throw new BudgetLockExpired();
				}
				Log.info(this, "budgetWrapperCache: " + budgetWrapperCache.isLockExpired() + ", organization: " + organizationId);
				if(budgetWrapperCache == null)	return false;
	System.err.println("Passed in token: " + updateToken + "******************************************************");
				if (!budgetWrapperCache.isUpdatable((BigDecimal)updateToken))
				{
					throw new BudgetLockTokenBad((BigDecimal)updateToken);
				}
				else
				{
					budgetWrapperArray[i] = budgetWrapperCache;
				}
			}
			else
			{
				budgetWrapperArray[i] = null;
			}
		}
		return this.updateBudget(budgetWrapperArray, budgetAction, amount, args, organizationId);
	}
	/*public boolean updateBudget(Object budgetKey, Object updateToken, int budgetAction, BigDecimal amount, String organizationId) throws BudgetLockExpired, BudgetLockTokenBad
	{
		BudgetWrapper budgetWrapperCache = this.getBudgetLocal(budgetKey, organizationId);
		if(budgetWrapperCache.isLockExpired())
		{
			throw new BudgetLockExpired();
		}
		if(budgetWrapperCache == null)	return false;

		if (budgetWrapperCache.isUpdatable((BigDecimal)updateToken))
		{
			return this.updateBudget(budgetWrapperCache, budgetAction, amount, organizationId);
//			if(this.updateBudget(budgetWrapperCache, budgetAction, amount, organizationId))
//			{
//				this.unlockBudget(budgetWrapperCache, organizationId);
//			}
		}
		else
		{
			throw new BudgetLockTokenBad((BigDecimal)updateToken);
		}
	}*/

	/**
	 * @param budgetKey
	 * @param budgetCenter
	 * @param organizationId
	 */
	private void updateBudgetCenterOnCache(BudgetWrapper budgetWrapper[], String organizationId)
	{
		Map organizationIdMap = (Map)this.budgetCache.get(organizationId);
		Map organizationIdByBudgetIdMap = (Map)this.budgetCacheById.get(organizationId);
		for (int i = 0; i < budgetWrapper.length; i++)
		{
			organizationIdMap.put(this.getKeyAsString((Object[])budgetWrapper[i].getBudgetKey()), budgetWrapper[i]);
			organizationIdByBudgetIdMap.put(budgetWrapper[i].getBudgetId(), budgetWrapper[i]);
		}
		this.budgetCache.put(organizationId, organizationIdMap);
	}
	private BigDecimal getBudgetBalance(BudgetCenter budgetCenter)
	{
		BigDecimal balance = budgetCenter.getBudgeted().subtract(budgetCenter.getPreEncumbered().add(budgetCenter.getEncumbered().add(budgetCenter.getExpensed())));
		return balance;
	}

	private Map getTotalAmount(Object budgetKey[], BigDecimal amount[], String organizationId)
	{
		Map balanceForBudgetCache = new HashMap();
		for (int i = 0; i < budgetKey.length; i++)
		{
			BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[])budgetKey[i]), organizationId);

			if (budgetWrapperCache != null)
			{
				BigDecimal budgetId = budgetWrapperCache.getBudgetId();
				if (!balanceForBudgetCache.containsKey(budgetId))
				{
					balanceForBudgetCache.put(budgetId, amount[i]);
				} else
				{
					BigDecimal balanceUpToAction = (BigDecimal) balanceForBudgetCache.get(budgetId);
					balanceForBudgetCache.put(budgetId, balanceUpToAction.add(amount[i]));
				}
			}
		}
		return balanceForBudgetCache;
	}//END getTotalAmount

	private int checkBudgetException(String budgetException, int exceptionType, String organizationId)
	{
		String errException = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", budgetException, "F");
		int exceptionCode = BudgetReturn.NOEXCEPTION;
		if(errException.equalsIgnoreCase("F"))
		{
			exceptionCode = exceptionType;
		}
		else if(errException.equalsIgnoreCase("N"))
		{
			exceptionCode = BudgetReturn.NOEXCEPTION;
		}
		else if(errException.equalsIgnoreCase("W"))
		{
			exceptionCode = (exceptionType * -1);
		}
		else if(errException.equalsIgnoreCase("I"))
		{
			exceptionCode = BudgetReturn.NOEXCEPTION;
		}
		Log.info(this, "Exception " + budgetException + " returns[" + exceptionCode + "]" );
		return exceptionCode;
	}//END checkBudgetException

	/**
	 * @param budgetWrapperCache
	 * @param totalAmountByBudget
	 * @param organizationId
	 * @return
	 */
	public int checkTolerance(BudgetWrapper budgetWrapperCache, BigDecimal totalAmountByBudget, String organizationId)
	{
		int toleranceExpcetion = BudgetReturn.NOEXCEPTION;
		String sTol = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "Tolerance", "0");
		BigDecimal bdTolerancePercent = new BigDecimal(sTol);
		/*
		 * budgeted --- 100%
		 * X                ---  Tolerance Percent
		 *
		 * x = (budgeted * Tolerance%)/100
		 */
		BigDecimal bdToleranceAmt = budgetWrapperCache.getBudgeted().multiply(bdTolerancePercent).multiply(new BigDecimal(0.01));
		Log.info(this, budgetWrapperCache.getKeyAsString() + "Tolerance Percent [" + bdTolerancePercent + "] - Amount[" + bdToleranceAmt + "]");
		BigDecimal difference = totalAmountByBudget.subtract(budgetWrapperCache.getBudgetBalance());
		Log.info(this, budgetWrapperCache.getKeyAsString() + "Budgeted [" + budgetWrapperCache.getBudgeted() + "] - difference[" + difference + "]");
		if(difference.compareTo(bdToleranceAmt) > 0)
		{
			toleranceExpcetion = this.checkBudgetException("BUDGET_TOLERR", BudgetReturn.OVERTOLERANCE, organizationId);
		}
		else
		{
			Log.info(this, budgetWrapperCache.getKeyAsString() + "Amount is within Tolerance Amount.");
		}
		return toleranceExpcetion;
	}//END checkTolerance

	public int checkToleranceChangeReq(BudgetWrapper budgetWrapperCache, BigDecimal totalAmountByBudget, BigDecimal differenceAmount, String organizationId)
	{
		int toleranceExpcetion = BudgetReturn.NOEXCEPTION;
		String sTol = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "Tolerance", "0");
		BigDecimal bdTolerancePercent = new BigDecimal(sTol);
		/*
		 * budgeted --- 100%
		 * X                ---  Tolerance Percent
		 *
		 * x = (budgeted * Tolerance%)/100
		 */
		BigDecimal bdToleranceAmt = budgetWrapperCache.getBudgeted().multiply(bdTolerancePercent).multiply(new BigDecimal(0.01));
		Log.info(this, budgetWrapperCache.getKeyAsString() + "Tolerance Percent [" + bdTolerancePercent + "] - Amount[" + bdToleranceAmt + "]");
		BigDecimal difference = differenceAmount.subtract(budgetWrapperCache.getBudgetBalance());
		Log.info(this, budgetWrapperCache.getKeyAsString() + "Budgeted [" + budgetWrapperCache.getBudgeted() + "] - difference[" + difference + "]");
		if(difference.compareTo(bdToleranceAmt) > 0)
		{
			toleranceExpcetion = this.checkBudgetException("BUDGET_TOLERR", BudgetReturn.OVERTOLERANCE, organizationId);
		}
		else
		{
			Log.info(this, budgetWrapperCache.getKeyAsString() + "Amount is within Tolerance Amount.");
		}
		difference = totalAmountByBudget.subtract(budgetWrapperCache.getBudgetBalance());


		return toleranceExpcetion;
	}//END checkTolerance

	/**
	 * @param budgetKey
	 * @param authority
	 * @param amount
	 * @param organizationId
	 * @return int[] length = budgetKey.length, Possible values: 0 = No Exception, Negative Number = Failed, Positive Number = Warning
	 */
	public synchronized int[][] budgetCheck(Object budgetKey[], String authority[], BigDecimal amount[], String organizationId)
	{
		organizationId = organizationId.toUpperCase();
		if(budgetKey.length != amount.length)	return null;
		int results[][] = new int[budgetKey.length][4];
		for (int i = 0; i < budgetKey.length; i++)
		{
			int result = BudgetReturn.NOEXCEPTION;
			BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[]) budgetKey[i]), organizationId);
			Log.info(this, "Checking Budget: " + budgetKey);
			Log.info(this, "Checking Budget: " + budgetKey);
			if (budgetWrapperCache == null)
			{
				Log.info(this, this.getKeyAsString(budgetKey) + " does not exist.");
				results[i][0] = this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOEXISTS, organizationId);
				return results;
			} else
			{
				Log.info(this, budgetWrapperCache.getKeyAsString() + " BUDGET_EXISTSERR NO Exception.");
			}
			// END Budget does NOT Exist

			if (results[i][0] == 0)
			{
				// Check Balance
				Map balanceForBudgetCache = this.getTotalAmount(budgetKey, amount, organizationId.toUpperCase());
				BigDecimal budgetId = budgetWrapperCache.getBudgetId();
				BigDecimal totalAmountByBudget = (BigDecimal) balanceForBudgetCache.get(budgetId);

				// check balances
				// if(amount[i].compareTo(new BigDecimal(0)) < 0)
				if (budgetWrapperCache.getBudgetBalance().compareTo(totalAmountByBudget) < 0)
				{
					//This sentence is commented since there is not necessary to compare Line amount with
					//Budget Balance to allow the check of Budget Tolerance of the total amount
					//if (budgetWrapperCache.getBudgetBalance().compareTo(amount[i]) < 0)
					//{
						Log.info(this, budgetKey + " Not Enough Balance for: " + amount[i]);
						// results[i] = BudgetReturn.NOTENOUGHBALANCE;
						int neb = this.checkBudgetException("BUDGET_OVERERR", BudgetReturn.NOTENOUGHBALANCE, organizationId);
						int tolerance = BudgetReturn.NOEXCEPTION;

						// check tolerance.. if Over Balance Exception is a
						// warning then check tolerance
						// if Over Balance Exception is an ERROR then NO NEED to
						// check Tolerance
						// if Over Balance Exception is an NO Check then NO NEED
						// to check Tolerance
						if (neb > 0)
						{
							tolerance = this.checkTolerance(budgetWrapperCache, totalAmountByBudget, organizationId);
						}
						// END check Tolerance

						if (tolerance == BudgetReturn.NOEXCEPTION)
						{
							result = neb;
						} else
						{
							result = tolerance;
						}
//					} else
//					{
//						Log.info(this, budgetWrapperCache.getKeyAsString() + "Balance/Tolerance returns No Exception.");
//						result = BudgetReturn.NOEXCEPTION;
//					}
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "Balance returns No Exception.");
					result = BudgetReturn.NOEXCEPTION;
				}

				results[i][1] = result;

				// check authority
				result = budgetWrapperCache.checkAuthority(budgetKey[i], authority[i]);
				if (result != 0)
				{
					result = this.checkBudgetException("BUDGET_AUTHERR", BudgetReturn.NOAUTHORITY, organizationId);
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "Authority Check returns No Exception.");
				}

				results[i][2] = result;

				// check budget status
				if (!((BudgetCenter) budgetWrapperCache.getBudgetCenter()).getStatus().equalsIgnoreCase("02"))
				{
					Log.info(this, budgetKey + " is not active.");
					// results[i] = BudgetReturn.BUDGETNOTACTIVE;
					results[i][3] = this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOTACTIVE, organizationId);
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "BUdget Status Check returns No Exception.");
				}
			}
		}

		return results;
	}

	public synchronized int[][] budgetCheckChangeReq(Object budgetKey[], String authority[], BigDecimal amount[], BigDecimal poTotal, String organizationId)
	{
		organizationId = organizationId.toUpperCase();
		if(budgetKey.length != amount.length)	return null;
		int results[][] = new int[budgetKey.length][4];
		for (int i = 0; i < budgetKey.length; i++)
		{
			int result = BudgetReturn.NOEXCEPTION;
			BudgetWrapper budgetWrapperCache = this.getBudgetLocal(this.getKeyAsString((Object[]) budgetKey[i]), organizationId);
			Log.info(this, "Checking Budget: " + budgetKey);
			Log.info(this, "Checking Budget: " + budgetKey);
			if (budgetWrapperCache == null)
			{
				Log.info(this, this.getKeyAsString(budgetKey) + " does not exist.");
				results[i][0] = this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOEXISTS, organizationId);
				return results;
			} else
			{
				Log.info(this, budgetWrapperCache.getKeyAsString() + " BUDGET_EXISTSERR NO Exception.");
			}
			// END Budget does NOT Exist

			if (results[i][0] == 0)
			{
				// Check Balance
				Map balanceForBudgetCache = this.getTotalAmount(budgetKey, amount, organizationId.toUpperCase());
				BigDecimal budgetId = budgetWrapperCache.getBudgetId();
				BigDecimal totalAmountByBudget = (BigDecimal) balanceForBudgetCache.get(budgetId);
				BigDecimal differenceAmount = amount[i].subtract(poTotal);

				// check balances
				// if(amount[i].compareTo(new BigDecimal(0)) < 0)
				if (budgetWrapperCache.getBudgetBalance().compareTo(totalAmountByBudget) < 0)
				{
					if (budgetWrapperCache.getBudgetBalance().compareTo(differenceAmount) < 0)
					{
						Log.info(this, budgetKey + " Not Enough Balance for: " + amount[i]);
						// results[i] = BudgetReturn.NOTENOUGHBALANCE;
						int neb = this.checkBudgetException("BUDGET_OVERERR", BudgetReturn.NOTENOUGHBALANCE, organizationId);
						int tolerance = BudgetReturn.NOEXCEPTION;

						// check tolerance.. if Over Balance Exception is a
						// warning then check tolerance
						// if Over Balance Exception is an ERROR then NO NEED to
						// check Tolerance
						// if Over Balance Exception is an NO Check then NO NEED
						// to check Tolerance
						if (neb > 0)
						{
							tolerance = this.checkToleranceChangeReq(budgetWrapperCache, totalAmountByBudget, differenceAmount, organizationId);
						}
						// END check Tolerance

						if (tolerance == BudgetReturn.NOEXCEPTION)
						{
							result = neb;
						} else
						{
							result = tolerance;
						}
					} else
					{
						Log.info(this, budgetWrapperCache.getKeyAsString() + "Balance/Tolerance returns No Exception.");
						result = BudgetReturn.NOEXCEPTION;
					}
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "Balance returns No Exception.");
					result = BudgetReturn.NOEXCEPTION;
				}

				results[i][1] = result;

				// check authority
				result = budgetWrapperCache.checkAuthority(budgetKey[i], authority[i]);
				if (result != 0)
				{
					result = this.checkBudgetException("BUDGET_AUTHERR", BudgetReturn.NOAUTHORITY, organizationId);
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "Authority Check returns No Exception.");
				}

				results[i][2] = result;

				// check budget status
				if (!((BudgetCenter) budgetWrapperCache.getBudgetCenter()).getStatus().equalsIgnoreCase("02"))
				{
					Log.info(this, budgetKey + " is not active.");
					// results[i] = BudgetReturn.BUDGETNOTACTIVE;
					results[i][3] = this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOTACTIVE, organizationId);
				} else
				{
					Log.info(this, budgetWrapperCache.getKeyAsString() + "BUdget Status Check returns No Exception.");
				}
			}
		}

		return results;
	}

	public String getKeyAsString(Object budgetKey[])
	{
		StringBuffer _budgetKey = new StringBuffer();

		for (int i = 0; i < budgetKey.length; i++)
		{
			_budgetKey.append(budgetKey[i]);
		}
		Log.info(this, "key String: " + _budgetKey.toString());
		return _budgetKey.toString();
	}

	/**
	 *
	 * @param forUpdate - true if obtaining a budget for update it also locks the budget and returns the balance and the lockingToken
	 * 								   - false if you just want to check a balance
	 * @param budgetKey - An array of 15 fields each one corresponds to a BudgetCenter.budgetX field.
	 * @param userId		   - User that will lock the budget.
	 * @param organizationId
	 * @return HashTable with 3 keys.
	 * 				balance			 - Budgeted - (pre + enc + expensed).
	 * 				lockingToken - BigDecimal - If forUpdate true. 0 if false.
	 * 				exception			 - BigDecimal - 0 if no exceptions.
	 * 										 - BigDecimal - -1 BudgetLocked.
	 */
	public synchronized Object getBudget(boolean forUpdate, Object budgetKey[], String userId, String organizationId)
	{
		organizationId = organizationId.toUpperCase();
		BigDecimal token = new BigDecimal(0) ;
		if(forUpdate)
		{
			UniqueKeyGenerator ukg = UniqueKeyGenerator.getInstance();
			token = new BigDecimal(ukg.getUniqueKey().toString()) ;
		}
		BudgetReturn budgetReturn = new BudgetReturn(token);
		for (int i = 0; i < budgetKey.length; i++)
		{
			List budgetReturnData = this.getBudget(forUpdate, budgetKey[i], userId, token, organizationId);
			budgetReturn.addBudget(budgetKey[i], budgetReturnData);
		}
		System.err.println("*************************Update token: " + budgetReturn.getLockingToken() + "******************************");
		int i =0;
		return budgetReturn;
	}

	private boolean checkExpiration(BudgetWrapper budgetWrapper)
	{
		boolean lockExpired = true;
		if(budgetWrapper.isLocked())
		{
			lockExpired = budgetWrapper.isLockExpired();
			if(lockExpired)
			{
				this.countExpiredLocks++;
				Log.info(this, "Expired Locks count: " + this.countExpiredLocks);
			}
		}
		return lockExpired;
	}//END check expiraiton

	private List setBudgetReturnLocked(BudgetWrapper budgetWrapper)
	{
		//budget was locked
		List budgetReturn = new ArrayList();
		//set balance
		budgetReturn.add( this.getBudgetBalance((BudgetCenter)budgetWrapper.getBudgetCenter()) );
		Log.info(this, budgetWrapper.getKeyAsString() + " is locked.");
		//set exception
		budgetReturn.add( new Integer(BudgetReturn.BUDGETLOCKED));
		return budgetReturn;
	}

	private synchronized List getBudget(boolean forUpdate, Object budgetKey, String userId, BigDecimal token, String organizationId)
	{
		String keyString = this.getKeyAsString((Object[])budgetKey);
		Log.info(this, "Getting forUpdate: " + forUpdate + ", " + keyString + ", token: " + token + ", organization: " + organizationId);
		if(budgetCache.containsKey(organizationId))
		{
			Map organizationIdMap = (Map)this.budgetCache.get(organizationId);
			Map organizationIdBudgetIdMap = (Map)this.budgetIdCache.get(organizationId);
			Map organizationIdByBudgetIdMap = (Map)this.budgetCacheById.get(organizationId);
			Log.info(this, "Getting forUpdate: " + organizationIdMap + ", " + organizationIdBudgetIdMap + "," + organizationIdByBudgetIdMap);
			String keyAsString = this.getKeyAsString((Object[])budgetKey);
			if (organizationIdMap != null && organizationIdMap.containsKey(keyAsString))
			{
				BigDecimal budgetId = (BigDecimal)organizationIdBudgetIdMap.get(this.getKeyAsString((Object[])budgetKey));
				BudgetWrapper budgetWrapper = (BudgetWrapper)organizationIdByBudgetIdMap.get(budgetId);
				//BudgetWrapper budgetWrapper = (BudgetWrapper)organizationIdMap.get(keyString);
				Log.info(this, "budgetWrapper: " + budgetWrapper + ", " + budgetId);
				boolean lockExpired = this.checkExpiration(budgetWrapper);

				if(budgetWrapper.isLocked() && !lockExpired && !budgetWrapper.isUpdatable(token))
				{
					//budget was locked
					return this.setBudgetReturnLocked(budgetWrapper);
				}
				else
				{
					budgetWrapper.setLocked(forUpdate, userId, token);
					List budgetReturn = new ArrayList();
					budgetReturn.add( this.getBudgetBalance((BudgetCenter)budgetWrapper.getBudgetCenter()));
					budgetReturn.add( new Integer(BudgetReturn.NOEXCEPTION));
					Log.info(this, "Found " + keyString + " with budget id[" + budgetWrapper.getBudgetId() + "]");
					return budgetReturn;
				}
			}
			else
			{
				BudgetWrapper budgetWrapper = null;
				try
				{
					budgetWrapper = this.getBudgetWrapper(forUpdate, budgetKey, userId,  token, organizationId);
					Log.info(this, "Getting budgetWrapperNull: " + budgetWrapper);

					if(budgetWrapper!=null)
					{
						//now make sure we use the wildcardBudget if one was found
						BigDecimal budgetId = budgetWrapper.getBudgetId();
						if(organizationIdByBudgetIdMap == null)
						{
							organizationIdByBudgetIdMap = new HashMap();
							Log.info(this, "Getting organizationIdByBudgetIdMap Null New:" + organizationIdByBudgetIdMap);
							organizationIdByBudgetIdMap.put(((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId(), budgetWrapper);
						}
						else
						{
							Log.info(this, "Getting organizationIdByBudgetIdMap: " + organizationIdByBudgetIdMap);
							if(organizationIdByBudgetIdMap.containsKey(budgetId))
							{
								budgetWrapper = (BudgetWrapper)organizationIdByBudgetIdMap.get(budgetId);
							}else{
								organizationIdByBudgetIdMap.put(((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId(), budgetWrapper);
							}
						}
						if(organizationIdMap == null)
						{
							organizationIdMap = new HashMap();
							Log.info(this, "Getting organizationIdMap Null New:" + organizationIdMap);
						}
						if(organizationIdBudgetIdMap == null)
						{
							organizationIdBudgetIdMap = new HashMap();
							Log.info(this, "Getting organizationIdBudgetIdMap Null New:" + organizationIdBudgetIdMap);
						}

						organizationIdMap.put(keyString, budgetWrapper);
						organizationIdBudgetIdMap.put(keyString, budgetId);
						Log.info(this, "Getting forUpdateNull: " + organizationIdMap + ", " + organizationIdBudgetIdMap + "," + organizationIdByBudgetIdMap);
						Log.info(this, "budgetWrapperNull: " + budgetWrapper + ", " + budgetId);
						boolean lockExpired = this.checkExpiration(budgetWrapper);

						if(budgetWrapper.isLocked() && !lockExpired)
						{
							if(budgetWrapper.getLockingToken().compareTo(token) != 0)
							{
								//budget was locked
								return this.setBudgetReturnLocked(budgetWrapper);
							}
							else
							{
								budgetWrapper.setLocked(forUpdate, userId, token);
								List budgetReturn = new ArrayList();
								budgetReturn.add( this.getBudgetBalance((BudgetCenter)budgetWrapper.getBudgetCenter()));
								budgetReturn.add( new Integer(BudgetReturn.NOEXCEPTION));
								Log.info(this, "Found " + keyString + " with budget id[" + budgetWrapper.getBudgetId() + "]");
								return budgetReturn;
							}
						}
					}
				}
				catch (BudgetDoesNotExist e)
				{
					List budgetReturn = new ArrayList();
					budgetReturn.add(new BigDecimal(0) );
					budgetReturn.add( new Integer(this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOEXISTS, organizationId)));
					return budgetReturn;
				}

				List budgetReturn = new ArrayList();
				if(budgetWrapper!=null && organizationIdMap != null && organizationIdMap.containsKey(keyAsString))
				{
					Log.info(this, "Adding to cache: " + keyString + " with budget id[" + budgetWrapper.getBudgetId() + "]");
					organizationIdMap.put(keyString, budgetWrapper);
					organizationIdBudgetIdMap.put(keyString, ((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId());
					organizationIdByBudgetIdMap.put(((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId(), budgetWrapper);
					//start bad code segment
					/*this.budgetIdCache.put(organizationId, organizationIdBudgetIdMap);
					this.budgetCache.put(organizationId, organizationIdMap);
					this.budgetCacheById.put(organizationId, budgetWrapper);
					*/
					//end bad code segment
					//good code segment
					this.budgetIdCache.put(organizationId, organizationIdBudgetIdMap);
					this.budgetCache.put(organizationId, organizationIdMap);
					this.budgetCacheById.put(organizationId, organizationIdByBudgetIdMap);
					//end good code segment

					budgetReturn.add( this.getBudgetBalance((BudgetCenter)budgetWrapper.getBudgetCenter()) );
					budgetReturn.add( new Integer(BudgetReturn.NOEXCEPTION));
				}
				return budgetReturn;
			}
		}
		else
		{
			Map organizationIdMap = new HashMap();
			Map organizationIdBudgetIdMap = new HashMap();
			Map organizationIdByBudgetIdMap = new HashMap();

			BudgetWrapper budgetWrapper;
			try
			{
				budgetWrapper = this.getBudgetWrapper(forUpdate, budgetKey, userId, token, organizationId);
			}
			catch (BudgetDoesNotExist e)
			{
				List budgetReturn = new ArrayList();
				budgetReturn.add(new BigDecimal(0) );
				budgetReturn.add( new Integer(this.checkBudgetException("BUDGET_EXISTSERR", BudgetReturn.BUDGETNOEXISTS, organizationId)));
				return budgetReturn;
			}

			List budgetReturn = new ArrayList();
			if(budgetWrapper!=null)
			{
				Log.info(this, "Adding to cache: " + keyString + " with budget id[" + budgetWrapper.getBudgetId() + "]");
				organizationIdMap.put(keyString, budgetWrapper);
				organizationIdBudgetIdMap.put(keyString, ((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId());
				organizationIdByBudgetIdMap.put(((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId(), budgetWrapper);

				this.budgetIdCache.put(organizationId, organizationIdBudgetIdMap);
				this.budgetCache.put(organizationId, organizationIdMap);
				this.budgetCacheById.put(organizationId, organizationIdByBudgetIdMap);

				budgetReturn.add( this.getBudgetBalance((BudgetCenter)budgetWrapper.getBudgetCenter()) );
				budgetReturn.add( new Integer(BudgetReturn.NOEXCEPTION));
		  }
			return budgetReturn;
		}
	}

	public synchronized void resetBudgetInCache(Object budgetKey,  String organizationId)
	{
		String keyString = this.getKeyAsString((Object[])budgetKey);
		Log.info(this, "Getting forUpdate: " + keyString + ", organization: " + organizationId);
		if(budgetCache.containsKey(organizationId))
		{
			Map organizationIdMap = (Map)this.budgetCache.get(organizationId);
			Map organizationIdBudgetIdMap = (Map)this.budgetIdCache.get(organizationId);

			String keyAsString = this.getKeyAsString((Object[])budgetKey);

			if(organizationIdMap.containsKey(keyAsString))
			{

				BudgetWrapper budgetWrapper = (BudgetWrapper) organizationIdMap.get(keyAsString);
				BigDecimal budgetId = ((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId();
				budgetWrapper.setBudgetDrawerList(this.retrieveBudgetDrawer(budgetId , organizationId));
			}

		}
	}


	private BudgetWrapper getBudgetWrapper(boolean forUpdate, Object budgetKey, String userId, BigDecimal token, String organizationId) throws BudgetDoesNotExist
	{
		BudgetWrapper budgetWrapper = null;

		budgetWrapper = new BudgetWrapper(budgetKey);
		BudgetCenter wildBudgetCenter;
		String wildcardsTrigger = PropertiesManager.getInstance(organizationId).getProperty("BUDGET", "wildacardType", "sql");
		if(wildcardsTrigger.equalsIgnoreCase("oracle"))
		{
			wildBudgetCenter = this.getBudgetFromWildcardsTrigger(budgetKey, organizationId);
		}
		else
		{
			wildBudgetCenter = this.getBudgetFromWildcards(budgetKey, organizationId);
		}
		BudgetCenter budgetCenter = null;
		budgetWrapper.setOrganizationId(organizationId);

		try
		{
			budgetCenter = this.retrieveBudgetCenter(budgetKey, organizationId);
			if(budgetCenter!=null)
			{
				budgetWrapper.setBudgetCenter(budgetCenter);
			}
			else
			{
				throw new BudgetDoesNotExist(budgetKey);
			}
		}
		catch (BudgetDoesNotExist bde)
		{
			if(wildBudgetCenter == null)
			{
				throw bde;
			}
			else
			{
				budgetWrapper.setBudgetCenter(wildBudgetCenter);
			}
		}

		if(budgetWrapper.getBudgetCenter()!=null)
		{
			BigDecimal budgetId = ((BudgetCenter)budgetWrapper.getBudgetCenter()).getBudgetId();

			budgetWrapper.setBudgetId(budgetId);
			budgetWrapper.setBudgetDrawerList(this.retrieveBudgetDrawer(budgetId , organizationId));
			budgetWrapper.setLocked(forUpdate, userId, token);
		}
		return budgetWrapper;
	}

	private boolean isBudgetLocked(Object budgetKey)
	{
		return ((BudgetWrapper)this.budgetCache.get(budgetKey)).isLocked();
	}

	public static void mainTestMisc(String[] args)
	{
		Calendar calStart = Calendar.getInstance();
		Calendar calEnd = Calendar.getInstance();
		long diff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();

		while (diff < (3 * 1000L))
		{
			calEnd = Calendar.getInstance();
			diff = calEnd.getTimeInMillis() - calStart.getTimeInMillis();
		}


		System.err.println("time dif: " + ((calEnd.getTimeInMillis() - calStart.getTimeInMillis()) / 1000) );
		Boolean isBudgetUpdated = new Boolean(false);
		System.err.println("bool check: " + isBudgetUpdated.equals(Boolean.TRUE));
	}

	private List retrieveBudgetDrawer(Object budgetId, String organizationId)
	{
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("BudgetCenter_budgetId", budgetId.toString());
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budgetdrawer-retrieve-by-id.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				return (List)incomingRequest.get("budgetDrawerList");
			}
			else
			{
				return null;
			}
		}
		catch (Exception exception)
		{
			return null;
		}
	}//END retrieveBudgetDrawer

	private BudgetCenter retrieveBudgetCenter(Object budgetKey, String organizationId) throws BudgetDoesNotExist
	{
		try
		{
			Map incomingRequest = new HashMap();
			incomingRequest.put("organizationId", organizationId);
			incomingRequest.put("budgetString", budgetKey);
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("budgetcenter-retrieve-from-array.xml");
			process.executeProcess(incomingRequest);
			if (process.getStatus() == Status.SUCCEEDED)
			{
				return (BudgetCenter)incomingRequest.get("budgetCenter");
			}
			else
			{
				throw new BudgetDoesNotExist(budgetKey);
			}
		}
		catch (BudgetDoesNotExist exception)
		{
			throw new BudgetDoesNotExist(budgetKey);
		}
		catch (Exception exception)
		{
			return null;
		}
	}

	public String getState(String organizationId)
	{
		Map budgetCache = this.getBudgetCache(organizationId.toUpperCase());
		if(budgetCache != null)
		{
			Iterator it = budgetCache.entrySet().iterator();
			StringBuffer sb = new StringBuffer();
			while (it.hasNext())
			{
			    Map.Entry pairs = (Map.Entry)it.next();
			    BudgetWrapper wrapper = (BudgetWrapper)pairs.getValue();

			    sb.append("key: " + pairs.getKey());
			    sb.append(", [Locked] " + wrapper.isLocked());
			    BudgetCenter budgetCenter = (BudgetCenter)wrapper.getBudgetCenter();

			    sb.append(", [Pre: ] = " + budgetCenter.getPreEncumbered()  + ", ");
			    sb.append(", [Enc: ] = " + budgetCenter.getEncumbered() + ", ");
			    sb.append(", [Exp: ] = " + budgetCenter.getExpensed());
			}
			return sb.toString();
		}
		return "{}";
	}

	public int getCountExpiredLocks() {
		return countExpiredLocks;
	}
}
