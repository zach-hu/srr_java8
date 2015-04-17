package com.tsa.puridiom.report.tasks;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.entity.MonthlyActivityBuyer;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportGeMountlyActivityBuyerData extends Task
{
	public Object executeTask(Object object) throws Exception {

		Object result = null;
		Map incomingRequest = (Map) object;
		try
		{

			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			BrowseObject b = (BrowseObject) incomingRequest.get("browseObject");
			List datasourceList = (List) incomingRequest.get("datasource");

			int indexBuyer = 0;
			int indexPoDate = 0;
			int indexTotal = 0;
			BrowseColumn browseColumns[] = b.getBrowseColumns();

			for (int index = 0; index < browseColumns.length; index++)
			{
				String columnName = browseColumns[index].getColumnName().toString();
				if (columnName.indexOf("PoHeader_buyerCode") == 0) {
					indexBuyer = index;
				}
				if (columnName.indexOf("PoHeader_poDate") == 0) {
					indexPoDate = index;
				}
				if (columnName.indexOf("PoHeader_total") == 0) {
					indexTotal = index;
				}
			}

			Calendar currentPoDate = Calendar.getInstance();
			Calendar nextPoDate = Calendar.getInstance();

			String nextBuyer = "";
			int nextPeriod = 0;
			int nextMonth = 0;

			int action1 = 0;
			int action2 = 0;
			int action3 = 0;
			int action4 = 0;
			BigDecimal ammount1 = new BigDecimal(0);
			BigDecimal ammount2 = new BigDecimal(0);
			BigDecimal ammount3 = new BigDecimal(0);
			BigDecimal ammount4 = new BigDecimal(0);

			List lstDataSource = new ArrayList();

			if ( datasourceList != null && datasourceList.size() > 0)
			{
				for (int ii = 0; ii < datasourceList.size(); ii++)
				{
					Object objectCurrent[] = (Object[]) datasourceList.get(ii);
					String currentBuyer = (String) objectCurrent[indexBuyer];
					currentPoDate.setTime((java.util.Date) objectCurrent[indexPoDate]);
					String s_currentAno = (new SimpleDateFormat("yyyy")).format(currentPoDate.getTime());
					String s_currenMonth = (new SimpleDateFormat("MM")).format(currentPoDate.getTime());
					int currentPeriod = (new BigDecimal(s_currentAno)).intValue();
					int currentMonth = (new BigDecimal(s_currenMonth)).intValue();

					if( ii < datasourceList.size()-1 )
					{
						Object objNextDatasource[] = (Object[]) datasourceList.get(ii + 1);
						nextBuyer = (String) objNextDatasource[indexBuyer];
						nextPoDate.setTime((java.util.Date) objNextDatasource[indexPoDate]);
						String s_nextAno = (new SimpleDateFormat("yyyy")).format(nextPoDate.getTime());
						String s_nextMonth = (new SimpleDateFormat("MM")).format(nextPoDate.getTime());
						nextPeriod = (new BigDecimal(s_nextAno)).intValue();
						nextMonth = (new BigDecimal(s_nextMonth)).intValue();

						if (!currentBuyer.equalsIgnoreCase(nextBuyer))
						{
							if (currentPeriod != nextPeriod) {
								if (currentMonth >= 1 && currentMonth <= 3) {
									action1++;
									ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 4 && currentMonth <= 6) {
									action2++;
									ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 7 && currentMonth <= 9) {
									action3++;
									ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 10 && currentMonth <= 12) {
									action4++;
									ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
								}
								MonthlyActivityBuyer mab = new MonthlyActivityBuyer();
								mab.setBuyerCode(currentBuyer);
								mab.setPeriod(String.valueOf(currentPeriod));
								mab.setAction1(new BigDecimal(action1));
								mab.setAmount1(ammount1);
								mab.setAction2(new BigDecimal(action2));
								mab.setAmount2(ammount2);
								mab.setAction3(new BigDecimal(action3));
								mab.setAmount3(ammount3);
								mab.setAction4(new BigDecimal(action4));
								mab.setAmount4(ammount4);

								lstDataSource.add(mab);

								action1 = 0;
								action2 = 0;
								action3 = 0;
								action4 = 0;
								ammount1 = new BigDecimal(0);
								ammount2 = new BigDecimal(0);
								ammount3 = new BigDecimal(0);
								ammount4 = new BigDecimal(0);

							}
							else
							{
								if (currentMonth >= 1 && currentMonth <= 3) {
									action1++;
									ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 4 && currentMonth <= 6) {
									action2++;
									ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 7 && currentMonth <= 9) {
									action3++;
									ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
								}
								if (currentMonth >= 10 && currentMonth <= 12) {
									action4++;
									ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
								}
							}
						}
						else if (currentPeriod != nextPeriod)
						{
							if (currentMonth >= 1 && currentMonth <= 3) {
								action1++;
								ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 4 && currentMonth <= 6) {
								action2++;
								ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 7 && currentMonth <= 9) {
								action3++;
								ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 10 && currentMonth <= 12) {
								action4++;
								ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
							}
							MonthlyActivityBuyer mab = new MonthlyActivityBuyer();
							mab.setBuyerCode(currentBuyer);
							mab.setPeriod(String.valueOf(currentPeriod));
							mab.setAction1(new BigDecimal(action1));
							mab.setAmount1(ammount1);
							mab.setAction2(new BigDecimal(action2));
							mab.setAmount2(ammount2);
							mab.setAction3(new BigDecimal(action3));
							mab.setAmount3(ammount3);
							mab.setAction4(new BigDecimal(action4));
							mab.setAmount4(ammount4);
							lstDataSource.add(mab);
							action1 = 0;
							action2 = 0;
							action3 = 0;
							action4 = 0;
							ammount1 = new BigDecimal(0);
							ammount2 = new BigDecimal(0);
							ammount3 = new BigDecimal(0);
							ammount4 = new BigDecimal(0);
						}
						else
						{
							if (currentMonth >= 1 && currentMonth <= 3) {
								action1++;
								ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 4 && currentMonth <= 6) {
								action2++;
								ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 7 && currentMonth <= 9) {
								action3++;
								ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
							}
							if (currentMonth >= 10 && currentMonth <= 12) {
								action4++;
								ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
							}
						}
					}
					else
					{
						if (currentMonth >= 1 && currentMonth <= 3) {
							action1++;
							ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
						}
						if (currentMonth >= 4 && currentMonth <= 6) {
							action2++;
							ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
						}
						if (currentMonth >= 7 && currentMonth <= 9) {
							action3++;
							ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
						}
						if (currentMonth >= 10 && currentMonth <= 12) {
							action4++;
							ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
						}


						MonthlyActivityBuyer mab = new MonthlyActivityBuyer();
						mab.setBuyerCode(currentBuyer);
						mab.setPeriod(String.valueOf(currentPeriod));
						mab.setAction1(new BigDecimal(action1));
						mab.setAmount1(ammount1);
						mab.setAction2(new BigDecimal(action2));
						mab.setAmount2(ammount2);
						mab.setAction3(new BigDecimal(action3));
						mab.setAmount3(ammount3);
						mab.setAction4(new BigDecimal(action4));
						mab.setAmount4(ammount4);

						lstDataSource.add(mab);

					}
				}
			}

			Log.debug(this, "Begin ReportGeMountlyActivityBuyerData\n");
			Log.debug(this, "\t BC \t PER \t ACT \t AMT \t ACT \t AMT \t ACT \t AMT \t ACT \t AMT ");
			for(int i=0; i<lstDataSource.size();i++){
				MonthlyActivityBuyer mab = (MonthlyActivityBuyer) lstDataSource.get(i);
				//Log.debug(this, "BC \t\t PER \t ACT1 \t AMT1 \t ACT2 \t AMT2 \t ACT3 \t AMT3 \t ACT4 \t AMT4 ");
				Log.debug(this, "\t " + mab.getBuyerCode() + "\t "	+ mab.getPeriod() + "\t " + mab.getAction1() + "\t " + mab.getAmount1() + "\t " + mab.getAction2() + "\t " + mab.getAmount2() + "\t " + mab.getAction3() + "\t " + mab.getAmount3() + "\t " + mab.getAction4() + "\t " + mab.getAmount4());
			}
			Log.debug(this, "End ReportGeMountlyActivityBuyerData\n");

			result = lstDataSource;
			this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			incomingRequest.put("failed", Boolean.TRUE);
			this.setStatus(Status.FAILED);
			Log.error(this, "Error executing ReportGeMountlyActivityBuyerData  - Exception: " + e.getMessage());
			e.printStackTrace();
			throw(e);
		}
		return result;
	}
}
