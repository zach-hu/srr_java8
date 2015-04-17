package com.tsa.puridiom.report.tasks;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.MonthlyDetailActivityBuyer;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;

public class ReportGeMountlyDetailActivityBuyerData extends Task
{
	public Object executeTask(Object object) throws Exception	{

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

			for(int index=0 ; index < browseColumns.length ; index ++ )
			{
				String columnName = browseColumns[index].getColumnName().toString();
				if(columnName.indexOf("PoHeader_buyerCode") == 0){
					indexBuyer = index;
				}
				if(columnName.indexOf("PoHeader_poDate") == 0){
					indexPoDate = index;
				}
				if(columnName.indexOf("PoHeader_total") == 0){
					indexTotal = index;
				}
			}

			Calendar currentPoDate=Calendar.getInstance();
			Calendar nextPoDate=Calendar.getInstance();

			String nextBuyer = "";
			int nextAno = 0;
			int nextMonth = 0;
			int numberMonth = 0;

			int action1 = 0;
			int action2 = 0;
			int action3 = 0;
			int action4 = 0;
			BigDecimal ammount1 = new BigDecimal(0);
			BigDecimal ammount2 = new BigDecimal(0);
			BigDecimal ammount3 = new BigDecimal(0);
			BigDecimal ammount4 = new BigDecimal(0);
			String period1 = "";
			String period2 = "";
			String period3 = "";
			String period4 = "";
			String currentPeriod = "";
			String nextPeriod = "";

			List lstDataSource = new ArrayList();

			if ( datasourceList != null && datasourceList.size() > 0 )
			{
				for (int ii = 0; ii < datasourceList.size(); ii++)
				{
					Object[] objectCurrent = (Object[]) datasourceList.get(ii);
					String currentBuyer = (String) objectCurrent[indexBuyer];
					currentPoDate.setTime((java.util.Date) objectCurrent[indexPoDate]);
					String s_currentAno = new SimpleDateFormat("yyyy").format(currentPoDate.getTime());
					String s_currenMonth = new SimpleDateFormat("MM").format(currentPoDate.getTime());
					int currentAno = new BigDecimal(s_currentAno).intValue();
					int currentMonth = new BigDecimal(s_currenMonth).intValue();
					currentPeriod = s_currentAno + "-" + s_currenMonth;

					if( ii < datasourceList.size()-1 )
					{
						Object[] objNextDatasource = (Object[]) datasourceList.get(ii + 1);
						nextBuyer = (String) objNextDatasource[indexBuyer];
						nextPoDate.setTime((java.util.Date) objNextDatasource[indexPoDate]);
						String s_nextAno = new SimpleDateFormat("yyyy").format(nextPoDate.getTime());
						String s_nextMonth = new SimpleDateFormat("MM").format(nextPoDate.getTime());
						nextAno = new BigDecimal(s_nextAno).intValue();
						nextMonth = new BigDecimal(s_nextMonth).intValue();
						nextPeriod = s_nextAno + "-" + s_nextMonth;

						if( !currentBuyer.equalsIgnoreCase(nextBuyer))
						{

							if( currentMonth == 1 || currentMonth == 5 || currentMonth == 9){
								action1+=1;
								ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
								period1=currentPeriod;
							}
							if( currentMonth == 2 || currentMonth == 6 || currentMonth == 10){
								action2+=1;
								ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
								period2=currentPeriod;
							}
							if( currentMonth == 3 || currentMonth == 7 || currentMonth == 11){
								action3+=1;
								ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
								period3=currentPeriod;
							}
							if( currentMonth == 4 || currentMonth == 8 || currentMonth == 12){
								action4+=1;
								ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
								period4=currentPeriod;
							}

								MonthlyDetailActivityBuyer mab = new MonthlyDetailActivityBuyer();
								mab.setBuyerCode(currentBuyer);
								mab.setPeriod1(period1);
								mab.setAction1(new BigDecimal(action1));
								mab.setAmount1(ammount1);
								mab.setPeriod2(period2);
								mab.setAction2(new BigDecimal(action2));
								mab.setAmount2(ammount2);
								mab.setPeriod3(period3);
								mab.setAction3(new BigDecimal(action3));
								mab.setAmount3(ammount3);
								mab.setPeriod4(period4);
								mab.setAction4(new BigDecimal(action4));
								mab.setAmount4(ammount4);								

								fillingPeriod(mab);	
								lstDataSource.add(mab);

								action1 = 0;
								action2 = 0;
								action3 = 0;
								action4 = 0;
								ammount1 = new BigDecimal(0);
								ammount2 = new BigDecimal(0);
								ammount3 = new BigDecimal(0);
								ammount4 = new BigDecimal(0);
								period1 = "";
								period2 = "";
								period3 = "";
								period4 = "";

						}
						else
						{
							if( currentAno != nextAno )
							{
								if( currentMonth == 1 || currentMonth == 5 || currentMonth == 9){
									action1+=1;
									ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
									period1=currentPeriod;
								}
								if( currentMonth == 2 || currentMonth == 6 || currentMonth == 10){
									action2+=1;
									ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
									period2=currentPeriod;
								}
								if( currentMonth == 3 || currentMonth == 7 || currentMonth == 11){
									action3+=1;
									ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
									period3=currentPeriod;
								}
								if( currentMonth == 4 || currentMonth == 8 || currentMonth == 12){
									action4+=1;
									ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
									period4=currentPeriod;
								}

									MonthlyDetailActivityBuyer mab = new MonthlyDetailActivityBuyer();
									mab.setBuyerCode(currentBuyer);
									mab.setPeriod1(period1);
									mab.setAction1(new BigDecimal(action1));
									mab.setAmount1(ammount1);
									mab.setPeriod2(period2);
									mab.setAction2(new BigDecimal(action2));
									mab.setAmount2(ammount2);
									mab.setPeriod3(period3);
									mab.setAction3(new BigDecimal(action3));
									mab.setAmount3(ammount3);
									mab.setPeriod4(period4);
									mab.setAction4(new BigDecimal(action4));
									mab.setAmount4(ammount4);

									fillingPeriod(mab);	
									lstDataSource.add(mab);

									action1 = 0;
									action2 = 0;
									action3 = 0;
									action4 = 0;
									ammount1 = new BigDecimal(0);
									ammount2 = new BigDecimal(0);
									ammount3 = new BigDecimal(0);
									ammount4 = new BigDecimal(0);
									period1 = "";
									period2 = "";
									period3 = "";
									period4 = "";

							}
							else
							{
								if( (currentMonth <= 4 && nextMonth >= 5) ||
									(currentMonth >= 5 && currentMonth <=8 && nextMonth >= 9) )
								{

									if( currentMonth == 1 || currentMonth == 5 || currentMonth == 9){
										action1+=1;
										ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
										period1=currentPeriod;
									}
									if( currentMonth == 2 || currentMonth == 6 || currentMonth == 10){
										action2+=1;
										ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
										period2=currentPeriod;
									}
									if( currentMonth == 3 || currentMonth == 7 || currentMonth == 11){
										action3+=1;
										ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
										period3=currentPeriod;
									}
									if( currentMonth == 4 || currentMonth == 8 || currentMonth == 12){
										action4+=1;
										ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
										period4=currentPeriod;
									}

										MonthlyDetailActivityBuyer mab = new MonthlyDetailActivityBuyer();
										mab.setBuyerCode(currentBuyer);
										mab.setPeriod1(period1);
										mab.setAction1(new BigDecimal(action1));
										mab.setAmount1(ammount1);
										mab.setPeriod2(period2);
										mab.setAction2(new BigDecimal(action2));
										mab.setAmount2(ammount2);
										mab.setPeriod3(period3);
										mab.setAction3(new BigDecimal(action3));
										mab.setAmount3(ammount3);
										mab.setPeriod4(period4);
										mab.setAction4(new BigDecimal(action4));
										mab.setAmount4(ammount4);
										
										fillingPeriod(mab);
										lstDataSource.add(mab);

										action1 = 0;
										action2 = 0;
										action3 = 0;
										action4 = 0;
										ammount1 = new BigDecimal(0);
										ammount2 = new BigDecimal(0);
										ammount3 = new BigDecimal(0);
										ammount4 = new BigDecimal(0);
										period1 = "";
										period2 = "";
										period3 = "";
										period4 = "";

								}
								else
								{
									if( currentMonth == 1 || currentMonth == 5 || currentMonth == 9){
										action1+=1;
										ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
										period1=currentPeriod;
									}
									if( currentMonth == 2 || currentMonth == 6 || currentMonth == 10){
										action2+=1;
										ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
										period2=currentPeriod;
									}
									if( currentMonth == 3 || currentMonth == 7 || currentMonth == 11){
										action3+=1;
										ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
										period3=currentPeriod;
									}
									if( currentMonth == 4 || currentMonth == 8 || currentMonth == 12){
										action4+=1;
										ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
										period4=currentPeriod;
									}
								}
							}
						}
					}
					else
					{
						if( currentMonth == 1 || currentMonth == 5 || currentMonth == 9){
							action1+=1;
							ammount1 = ammount1.add((BigDecimal) objectCurrent[indexTotal]);
							period1=currentPeriod;
						}
						if( currentMonth == 2 || currentMonth == 6 || currentMonth == 10){
							action2+=1;
							ammount2 = ammount2.add((BigDecimal) objectCurrent[indexTotal]);
							period2=currentPeriod;
						}
						if( currentMonth == 3 || currentMonth == 7 || currentMonth == 11){
							action3+=1;
							ammount3 = ammount3.add((BigDecimal) objectCurrent[indexTotal]);
							period3=currentPeriod;
						}
						if( currentMonth == 4 || currentMonth == 8 || currentMonth == 12){
							action4+=1;
							ammount4 = ammount4.add((BigDecimal) objectCurrent[indexTotal]);
							period4=currentPeriod;
						}

						MonthlyDetailActivityBuyer mab = new MonthlyDetailActivityBuyer();
						mab.setBuyerCode(currentBuyer);
						mab.setPeriod1(period1);
						mab.setAction1(new BigDecimal(action1));
						mab.setAmount1(ammount1);
						mab.setPeriod2(period2);
						mab.setAction2(new BigDecimal(action2));
						mab.setAmount2(ammount2);
						mab.setPeriod3(period3);
						mab.setAction3(new BigDecimal(action3));
						mab.setAmount3(ammount3);
						mab.setPeriod4(period4);
						mab.setAction4(new BigDecimal(action4));
						mab.setAmount4(ammount4);
						
						fillingPeriod(mab);
						lstDataSource.add(mab);

				}
			}
		}


		Log.debug(this, "Begin ReportGeMountlyActivityBuyerData\n");
		Log.debug(this, "\t BC \t PER1 \t ACT1 \t AMT1 \t PER2 \t ACT2 \t AMT2 \t PER3 \t ACT3 \t AMT3 \t \t PER4 ACT4 \t AMT4 ");

		for(int i=0; i<lstDataSource.size();i++){
			MonthlyDetailActivityBuyer mab = (MonthlyDetailActivityBuyer)lstDataSource.get(i);
			Log.debug(this, "\t " + mab.getBuyerCode() + "\t " + mab.getPeriod1() + "\t " +  mab.getAction1() + "\t " + mab.getAmount1() + "\t " + mab.getPeriod2() + "\t " + mab.getAction2() + "\t " + mab.getAmount2() + "\t " + mab.getAction3() + "\t " + mab.getPeriod3() + "\t " + mab.getAmount3() + "\t " + "\t " + mab.getPeriod4() + mab.getAction4() + "\t " + mab.getAmount4());
		}
		Log.debug(this, "End ReportGeMountlyDetailActivityBuyerData\n");

		Collections.sort(lstDataSource, new MountlyDetailActivityBuyerComparator());
		result = lstDataSource;
		this.setStatus(Status.SUCCEEDED);

		}
		catch (Exception e)
		{
			incomingRequest.put("failed", Boolean.TRUE);
			this.setStatus(Status.FAILED);
			Log.error(this, "Error executing ReportGeMountlyDetailActivityBuyerData "  + " - Exception: " + e.getMessage());
			e.printStackTrace();
			throw(e);
		}
		return result;
	}
	
	public void fillingPeriod(MonthlyDetailActivityBuyer obj)
	{
		String period1 = obj.getPeriod1();
		String period2 = obj.getPeriod2();
		String period3 = obj.getPeriod3();
		String period4 = obj.getPeriod4();
		String period = "";
		String mes = "";
		//period = HiltonUtility.ckNull(period1).substring(0, 3);
		//mes =  HiltonUtility.ckNull(period1).substring(5, 6);
		if(period.equalsIgnoreCase("") && !period1.equalsIgnoreCase("") ) period = HiltonUtility.ckNull(period1).substring(0, 4);		    
		if(period.equalsIgnoreCase("") && !period2.equalsIgnoreCase("") ) period = HiltonUtility.ckNull(period2).substring(0, 4);
		if(period.equalsIgnoreCase("") && !period3.equalsIgnoreCase("") ) period = HiltonUtility.ckNull(period3).substring(0, 4);		
		if(period.equalsIgnoreCase("") && !period4.equalsIgnoreCase("")) period = HiltonUtility.ckNull(period4).substring(0, 4);
		
		if( mes.equalsIgnoreCase("") && !period1.equalsIgnoreCase("") ) mes = HiltonUtility.ckNull(period1).substring(5, 7);
		if( mes.equalsIgnoreCase("") && !period2.equalsIgnoreCase("") ) mes = HiltonUtility.ckNull(period2).substring(5, 7);
		if( mes.equalsIgnoreCase("") && !period3.equalsIgnoreCase("") )mes = HiltonUtility.ckNull(period3).substring(5, 7);
		if( mes.equalsIgnoreCase("") && !period4.equalsIgnoreCase("") ) mes = HiltonUtility.ckNull(period4).substring(5, 7);
		
		if( mes.equalsIgnoreCase("01") || mes.equalsIgnoreCase("02") || mes.equalsIgnoreCase("03") || mes.equalsIgnoreCase("04") ){
			period1 = period + "-01";
			period2 = period + "-02";
			period3 = period + "-03";
			period4 = period + "-04";
		}
		if( mes.equalsIgnoreCase("05") || mes.equalsIgnoreCase("06") || mes.equalsIgnoreCase("07") || mes.equalsIgnoreCase("08") ){
			period1 = period + "-05";
			period2 = period + "-06";
			period3 = period + "-07";
			period4 = period + "-08";
		}		
		if( mes.equalsIgnoreCase("09") || mes.equalsIgnoreCase("10") || mes.equalsIgnoreCase("11") || mes.equalsIgnoreCase("12") ){
			period1 = period + "-09";
			period2 = period + "-10";
			period3 = period + "-11";
			period4 = period + "-12";
		}
		
		obj.setPeriod1(period1);
		obj.setPeriod2(period2);
		obj.setPeriod3(period3);
		obj.setPeriod4(period4);
			
	}

}