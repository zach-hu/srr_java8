package com.puridiom.service.budget;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.tsa.puridiom.entity.BudgetCenter;
import com.tsa.puridiom.entity.BudgetDrawer;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.utility.Log;

public class BudgetWrapper
{
	private boolean locked = false;
	private String lockingId;
	private BigDecimal lockingToken;
	private BudgetCenter budgetCenter;
	private List	budgetDrawerList;
	private Object budgetKey;
	private String organizationId;
	private String userId;
	private Calendar lockingStart = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
	private BigDecimal budgetId = new BigDecimal(0);

	public BudgetWrapper(Object _budgetKey)
	{
		this.setBudgetKey(_budgetKey);
	}

	public static void main(String[] args) throws Exception {
		BudgetWrapper bw = new BudgetWrapper(null);
		//bw.setLocked(true, "renzo");
		Thread.sleep(6001);
		System.out.println("expired: " + bw.isLockExpired());
	}
	/**
	 * Returns true is the lock expired.
	 * @return
	 */
	public boolean isLockExpired()
	{
		String sTimeOutPeriod = PropertiesManager.getInstance(this.getOrganizationId()).getProperty("budgetservice", "lockingPeriod", "30");
		long timeOut = 1000L * (Long.parseLong(sTimeOutPeriod));
		Calendar now =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long diff = now.getTimeInMillis() - this.getLockingStart().getTimeInMillis();

		boolean lockMaxExpired = this.isMaxLockExpired();
		if(diff > timeOut)
		{
			if(lockMaxExpired)
			{
				this.setLocked(false, "", new BigDecimal(0));
			}
			else
			{
				Log.info(this, "Lock Expired. Will be Timed out. ");
			}
			return true;
		}

		return false;
	}

	public boolean isMaxLockExpired()
	{
		String sTimeOutPeriod = PropertiesManager.getInstance(this.getOrganizationId()).getProperty("budgetservice", "lockingPeriod", "30");
		long timeOut = 2000L * (Long.parseLong(sTimeOutPeriod));
		Calendar now =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		long diff = now.getTimeInMillis() - this.getLockingStart().getTimeInMillis();

		if(diff > timeOut)
		{
			Log.info(this, "Lock Max Expired. Will be Timed out. ");
			return true;
		}
		return false;
	}

	public boolean isLocked()
	{
		return locked;
	}

	public BigDecimal setLocked(boolean _locked, String userId, BigDecimal token)
	{
		if(_locked)
		{
			this.locked = _locked;
			this.lockingToken = token ;
			this.setLockingId(userId);
			this.setLockingStart(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
			return this.lockingToken;
		}
		else
		{
			Calendar now =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			long diff = now.getTimeInMillis() - this.getLockingStart().getTimeInMillis();
			Log.info(this, "TransactionTotal Time: " + diff);
			this.locked = _locked;
			this.lockingToken = new BigDecimal(0);
			this.setLockingId("");
			this.setLockingStart(now);

			return this.lockingToken;
		}
	}

	public String getLockingId() {
		return lockingId;
	}

	public void setLockingId(String lockingId) {
		this.lockingId = lockingId;
	}

	public Object getBudgetCenter() {
		return this.budgetCenter;
	}

	public void setBudgetCenter(BudgetCenter budget) {
		this.budgetCenter = budget;
	}

	public Object getBudgetKey() {
		return budgetKey;
	}

	private void setBudgetKey(Object budgetKey) {
		this.budgetKey = budgetKey;
	}

	public BudgetWrapper GetBudgetUnlocked()
	{
		BudgetWrapper unlockedBudget = new BudgetWrapper(this.budgetKey);
		unlockedBudget.setLocked(false, "", new BigDecimal(0));

		return unlockedBudget;
	}

	public boolean isUpdatable(BigDecimal token)
	{
		if(token.compareTo(new BigDecimal(0)) == 0) return false;
		return (this.lockingToken.compareTo(token) == 0);
	}

	public int checkAuthority(Object budgetKey, String authority)
	{
		Log.info(this, this.getKeyAsString() +" Checking with: " + authority);
		for(int i = 0; i < this.budgetDrawerList.size(); i++)
		{
			BudgetDrawer drawer = (BudgetDrawer)this.budgetDrawerList.get(i);
			Log.info(this, this.getKeyAsString() +" Authority found: " + drawer.getComp_id().getAuthority());
			if(drawer.getComp_id().getAuthority().trim().equalsIgnoreCase(authority))
			{
				if(drawer.getStatus().equalsIgnoreCase("03"))
				{
					i = this.budgetDrawerList.size();
					Log.info(this, this.getKeyAsString() + "Authority: " + drawer.getComp_id().getAuthority() + " is inactive");
					return BudgetReturn.AUTHORITYNOTACTIVE;
				}
				else
				{
					i = this.budgetDrawerList.size();
					return BudgetReturn.NOEXCEPTION;
				}
			}
		}
		Log.info(this, this.getKeyAsString() + "No matching authority found.");
		return BudgetReturn.NOAUTHORITY;
	}

	public BigDecimal getBudgetBalance()
	{
		BigDecimal balance = budgetCenter.getBudgeted().subtract(budgetCenter.getPreEncumbered().add(budgetCenter.getEncumbered().add(budgetCenter.getExpensed())));
		return balance;
	}

	public BigDecimal getLockingToken() {
		return lockingToken;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List getBudgetDrawerList() {
		return budgetDrawerList;
	}

	public void setBudgetDrawerList(List budgetDrawerList) {
		this.budgetDrawerList = budgetDrawerList;
	}

	public String getKeyAsString()
	{
		StringBuffer _budgetKey = new StringBuffer();
		Object keyArray[] = (Object[])budgetKey;

		for (int i = 0; i < keyArray.length; i++)
		{
			_budgetKey.append(keyArray[i]);
		}
		return _budgetKey.toString();
	}

	public Calendar getLockingStart()
	{
		if(this.lockingStart == null)
		{
			return Calendar.getInstance();
		}

		return lockingStart;
	}

	public void setLockingStart(Calendar lockingStart)
	{
		this.lockingStart = lockingStart;
	}

	public BigDecimal getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(BigDecimal budgetId) {
		this.budgetId = budgetId;
	}

	public BigDecimal getBudgeted()
	{
		BudgetCenter budgetCenter = (BudgetCenter)this.getBudgetCenter();
		return budgetCenter.getBudgeted();
	}
}
