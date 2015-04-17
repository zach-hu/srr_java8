/*
 * Created on Jan 29, 2004
 */
package com.tsa.puridiom.browse;

import com.tsa.puridiom.common.documents.*;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.currcode.CurrencyManager;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;

import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author Kelli
 */
public class BrowseUtility {
	public static BrowseUtility browseUtility = new BrowseUtility();

	public static List populateValues(List list, BrowseObject b, String o, String userId) throws Exception{
		List rows = new ArrayList();

		try {
			//Log.debug(browseUtility, b.getBrowseName() + " executing BrowseUtility.populateValues().");

			if (list != null) {
				BrowseColumn[] browseColumns = b.getBrowseColumns();
				BrowseUtility bu = new BrowseUtility();

                UserProfile userProfile = UserManager.getInstance().getUser(o, userId);
                String userDateFormat = userProfile.getDateFormat();

                PropertiesManager props = PropertiesManager.getInstance(o);
                if (Utility.isEmpty(userDateFormat)) {
                    userDateFormat = props.getProperty("MISC", "DATEFORMAT", "yyyy-MM-dd");
                }

				//Log.debug(browseUtility, b.getBrowseName() + " loop through " + list.size() + " records to get values.");

				for (int il = 0; il < list.size(); il++) {
					Object object = list.get(il);
					List valueList[] = bu.getRowValues(object, browseColumns, o, userDateFormat);

					rows.add(valueList);
				}
				b.setRowCount(list.size());

				if (b.getPageSize() > 0) {
					b.setPageCount( ((b.getRowCount() - 1) / b.getPageSize()) + 1);
				} else {
					b.setPageCount(1);
				}
			}

			//Log.debug(browseUtility, b.getBrowseName() + "populateValues returning " + rows.size());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return rows;
	}
	public static Object getInputColumnValue(BrowseColumn column)
	{
	    Object obj = null;
	    int	size = 0;
	    if (column.getInputSize() <= 0)
	    {
	        size = column.getSize();
	    }
	    else  if (column.getSize() <= 0)
	    {
	        size = column.getSize();
	    }
	    obj = "<input type=text name=\"" + column.getColumnName() + "\" value=\"\" size=" + size + ">";
	    return obj;
	}
	public static Object getInputColumnValue(BrowseColumn column, Object row, String organizationId, BrowseColumn columns[], String browseId, String userDateFormat) throws Exception
	{
	    Object obj = null;
	    Browse browse = BrowseManager.getInstance().getBrowse(browseId);
	    BrowseObject browseObject = browse.getBrowseObject();
	    Map	keyMap = BrowseUtility.getColumnKeyValue(browseObject.getBrowseKeys(), row, organizationId, columns, browseId, userDateFormat);
	    String inputValue = browse.getInputValue(keyMap);

	    int	size = 0;
	    if (column.getInputSize() <= 0)
	    {
	        size = column.getSize();
	    }
	    else  if (column.getSize() <= 0)
	    {
	        size = column.getSize();
	    }
	    obj = "<input type=text name=\"" + column.getColumnName() + "\" value=\"" + inputValue +  "\" size=" + size + ">";
	    return obj;
	}
	public static Object getCheckboxColumnValue(BrowseColumn column, Object row, String organizationId, BrowseColumn columns[], String browseId, String userDateFormat) throws Exception
	{
	    Object obj = null;
	    Browse browse = BrowseManager.getInstance().getBrowse(browseId);
	    BrowseObject browseObject = browse.getBrowseObject();
	    Map	keyMap = BrowseUtility.getColumnKeyValue(browseObject.getBrowseKeys(), row, organizationId, columns, browseId, userDateFormat, column.getColumnName());
	    String inputValue = browse.getInputValue(keyMap);

	    if (inputValue.equals("Y")) {
	        obj = "<input type=checkbox name=\"" + column.getColumnName() + column.getIdName() + "\" value=\"Y\" checked>";
	    } else {
	        obj = "<input type=checkbox name=\"" + column.getColumnName() + column.getIdName() + "\" value=\"Y\">";
	    }
	    obj = obj + "<input type=hidden name=\"Input_" + column.getColumnName().substring(2) + column.getIdName() + "\" value=\"" + inputValue + "\">";
	    return obj;
	}
	public static String getSelectColumnValue(BrowseColumn column, Object row, BrowseColumn columns[], int rowIndex) throws Exception
	{
	    StringBuffer selectInput = new StringBuffer();

	    if (column.isSelectInput())
		{
	    	selectInput.append("<select name=\"" + column.getColumnName() + column.getIdName() + "\" onchange=\"setRowStatus(" + rowIndex + ", 'U');\">");

	    	List selectOptions = column.getArgumentColumns();
			for (int colsIndex = 0; colsIndex < selectOptions.size(); colsIndex++)
			{
				ComputedColumn computedColumn = (ComputedColumn) selectOptions.get(colsIndex);
				String optionValue = computedColumn.getColumnType();
				String optionText = (String) computedColumn.getValue();
				Object columnValueObj = column.getValue();
				String columnValue = "";

				if (columnValueObj instanceof String) {
					columnValue = (String) columnValueObj;
					if(columnValue.indexOf("#") != -1){
						columnValue = (String)BrowseUtility.getColumnValue(columnValue.substring(1), row, columns);
					}
				}

				if (columnValueObj == null) {
					if (row instanceof Object[]) {
						Object rowArray[] = (Object[]) row;
						columnValueObj = rowArray[column.getIndex()];
					}
				}

			    selectInput.append("<option value=\"" + optionValue + "\"");
			    if (optionValue.equals(columnValue)) {
			    	selectInput.append(" selected");
			    }
			    selectInput.append(">");
			    selectInput.append(optionText);
			    selectInput.append("</option>");
			}
			selectInput.append("</select>");
		}

		return selectInput.toString();
	}
	public static Object getRadioColumnValue(BrowseColumn column, Object row, String organizationId, BrowseColumn columns[], String browseId, String userDateFormat) throws Exception
	{
		Object obj = null;
		Browse browse = BrowseManager.getInstance().getBrowse(browseId);
		BrowseObject browseObject = browse.getBrowseObject();
		Map keyMap = BrowseUtility.getColumnKeyValue(browseObject.getBrowseKeys(), row, organizationId, columns, browseId, userDateFormat);
		String radioValue = (String)keyMap.get("RadioValue");

		obj = "<input type=radio name='c_radio' value='" + radioValue + "'>";

		return obj;
	}
	public static Object getTestColumnValueObject(BrowseColumn column, Object row, String organizationId, BrowseColumn columns[], String browseId, String userDateFormat) throws Exception
	{
	    Object obj = null;
	    try {
		    if(column.isSqlPart())
		    {
		    	if (row instanceof Object[])
		    	{
		    		Object currentRow[] = (Object[])row;
				    obj = currentRow[column.getIndex()];
		    	}
		    	else
		    	{
		    		Object currentRow = (Object)row;
				    obj = currentRow;
		    	}
		    }
			else
			{
				if (column.getClassName().equals("Input"))
			    {
					obj = BrowseUtility.getInputColumnValue(column, row, organizationId, columns, browseId, userDateFormat);
				}
				else if (column.getType().equals("Checkbox"))
			    {
				    obj = BrowseUtility.getCheckboxColumnValue(column, row, organizationId, columns, browseId, userDateFormat);
				}
				else if (column.getType().equals("Dropdown"))
				{
					obj = BrowseUtility.getSelectColumnValue(column, row, columns, 0);
				}
				else if (column.getType().equals("Radio"))
			    {
				    obj = BrowseUtility.getRadioColumnValue(column, row, organizationId, columns, browseId, userDateFormat);
				}
				else if(column.getColumnName().indexOf("view") > -1)
				{
					obj = column.getLink();
				}
				else if(column.isComputed())
				{
				    if(column.getComputeType().equalsIgnoreCase("userName"))
				    {
				        column.setType("USER-ID");

				        StringBuffer tempObj = new StringBuffer("");
				        List columnsToUse = column.getConcatenateColumns();
			            ComputedColumn computedColumn = (ComputedColumn) columnsToUse.get(0);
				        String columnValue = (String) computedColumn.getValue();
				        if (!Utility.isEmpty(columnValue))
				        {
					        columnValue = (String)BrowseUtility.getColumnValue(columnValue, row, columns);
					        if (!Utility.isEmpty(columnValue)) {
					            tempObj.append(columnValue);
					        }
				        }
				        obj = tempObj.toString();
				    }
				    else
				    {
						StringBuffer tempObj = new StringBuffer();
						List columnsToUse = column.getConcatenateColumns();
						for(int colsIndex = 0; colsIndex < columnsToUse.size(); colsIndex++)
						{
				            ComputedColumn computedColumn = (ComputedColumn) columnsToUse.get(colsIndex);
				            String	columnType = computedColumn.getColumnType();
					        String columnValue = (String) computedColumn.getValue();
					        if (!Utility.isEmpty(columnValue))
					        {
					            if (!columnType.equalsIgnoreCase("constant")) {
							        Object columnValueObj = BrowseUtility.getColumnValue(columnValue, row, columns);

							        if (column.getType().length() >= 0 && !column.getType().equalsIgnoreCase("String") && !column.getType().equalsIgnoreCase("constant"))
								    {
								        obj = BrowseUtility.formatBrowseColumnValue(columnValueObj, computedColumn.getColumnType(), organizationId, userDateFormat);
									}
							        columnValue = String.valueOf(obj);

						            tempObj.append(columnValue);
					            } else {
					                tempObj.append(columnValue);
				                }
					        }
						}
						obj = tempObj;
				    }
				}
				else
				{

				}
			}
		    if (obj == null) {
		        obj = new String("");
		    }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }

	    return obj;
	}
	public static Object getTestColumnValue(BrowseColumn column, Object row, String organizationId, BrowseColumn browseColumns[], String browseId, String userId)  throws Exception
	{
	    Object obj = null;

	    try {
            UserProfile userProfile = UserManager.getInstance().getUser(organizationId, userId);
            String userDateFormat = userProfile.getDateFormat();

            PropertiesManager props = PropertiesManager.getInstance(organizationId);
            if (Utility.isEmpty(userDateFormat)) {
                userDateFormat = props.getProperty("MISC", "DATEFORMAT", "yyyy-MM-dd");
            }
	        obj = BrowseUtility.getTestColumnValueObject(column, row, organizationId, browseColumns, browseId, userDateFormat);

		    if (column.getType().length() >= 0 && !column.getType().equalsIgnoreCase("String"))
		    {
		    	List arguments = BrowseUtility.getArgumentValuesFromColumn(column, row, browseColumns, organizationId, userDateFormat);
		    	obj = BrowseUtility.formatBrowseColumnValue(obj, column, arguments, organizationId, userDateFormat) ;
			}
	    } catch (Exception e) {
	        throw e;
	    }

	    return obj;
	}

	public static List getArgumentValuesFromColumn(BrowseColumn column, Object row, BrowseColumn columns[], String organizationId, String userDateFormat)
	{
		List argumentValues = new ArrayList();
		List columnsToUse = column.getArgumentColumns();
		Object obj = null;

		if (column.isComputed() || column.isSelectInput())
		{
			for (int colsIndex = 0; colsIndex < columnsToUse.size(); colsIndex++)
			{
				ComputedColumn computedColumn = (ComputedColumn) columnsToUse.get(colsIndex);
				String columnType = computedColumn.getColumnType();
				String columnValue = (String) computedColumn.getValue();
				String argument = "";

				if (!HiltonUtility.isEmpty(columnValue))
				{
					if (!columnType.equalsIgnoreCase("constant") && !column.isSelectInput())
					{
						Object columnValueObj = BrowseUtility.getColumnValue(columnValue, row, columns);

						if (column.getType().length() >= 0 && !column.getType().equalsIgnoreCase("String") && !column.getType().equalsIgnoreCase("constant"))
						{
							obj = BrowseUtility.formatBrowseColumnValue(columnValueObj, computedColumn.getColumnType(), organizationId, userDateFormat);
						}

						columnValue = String.valueOf(obj);
					}
				}

				argument = columnValue;

				if (!HiltonUtility.isEmpty(argument))
				{
					argumentValues.add(argument);
				}
			}
		}

		return argumentValues;
	}


	public static Object getColumnValue(BrowseColumn column, Object object, String organizationId, BrowseColumn[] columns, String browseId, String userDateFormat) {
		Object result;

		if (column.getClassName().equals("Input")) {
		    try {
		        result = BrowseUtility.getInputColumnValue(column, object, organizationId, columns, browseId, userDateFormat);
		    } catch (Exception e) {
		        result = BrowseUtility.getInputColumnValue(column);
		    }
		}
		else {
			String	methodName = column.getMethodName();
			if (object == null) {
				result = null;
			}
			else {
				Class c = object.getClass();
				Method method;
				try {
					method = c.getMethod(methodName, null);
					result = method.invoke(object, new Class[]{});
				}
				catch (Exception e) {
					try {
						Method pkMethod = c.getMethod("getComp_id", null);
						Object pkObj = pkMethod.invoke(object, new Class[]{});
						method = pkObj.getClass().getMethod(methodName, null);
						result = method.invoke(pkObj, new Class[]{});
					}
					catch (Exception ex) {
						method = null;
						result = null;
					}
				}
			}
		}
		return result;
	}

	public static Object getArgumentValue(String methodName, Object object) {
		Object result;

		if (object == null) {
			result = null;
		}
		else {
			Class c = object.getClass();
			Method method;
			try {
				method = c.getMethod(methodName, null);
				result = method.invoke(object, new Class[]{});
			}
			catch (Exception e) {
				try {
					Method pkMethod = c.getMethod("getComp_id", null);
					Object pkObj = pkMethod.invoke(object, new Class[]{});
					method = pkObj.getClass().getMethod(methodName, null);
					result = method.invoke(pkObj, new Class[]{});
				}
				catch (Exception ex) {
					method = null;
					result = null;
				}
			}
		}
		return result;
	}

	public static Object getColumnValue(BrowseColumn column, Object object) {
		Object result;

		if (column.getClassName().equals("Input")) {
		   result = BrowseUtility.getInputColumnValue(column);
		}
		else {
			String	methodName = column.getMethodName();
			if (object == null) {
				result = null;
			}
			else {
				Class c = object.getClass();
				Method method;
				try {
					method = c.getMethod(methodName, null);
					result = method.invoke(object, new Class[]{});
				}
				catch (Exception e) {
					try {
						Method pkMethod = c.getMethod("getComp_id", null);
						Object pkObj = pkMethod.invoke(object, new Class[]{});
						method = pkObj.getClass().getMethod(methodName, null);
						result = method.invoke(pkObj, new Class[]{});
					}
					catch (Exception ex) {
						method = null;
						result = null;
					}
				}
			}
		}
		return result;
	}

	public static Object getColumnValue(String columnName, Object object) {
		int	ind = columnName.indexOf("_");
		String className = columnName.substring(0, ind);
		String methodName = "get" + columnName.substring(ind + 1, ind+2).toUpperCase() + columnName.substring(ind + 2);
		Class c = object.getClass();
		Method method;
		Object result;

		try {
			method = c.getMethod(methodName, null);
			result = method.invoke(object, new Class[]{});
		}
		catch (Exception e) {
			try {
				Method pkMethod = c.getMethod("getComp_id", null);
				Object pkObj = pkMethod.invoke(object, new Class[]{});
				method = pkObj.getClass().getMethod(methodName, null);
				result = method.invoke(pkObj, new Class[]{});
			}
			catch (Exception ex) {
				method = null;
				result = null;
			}
		}
		return result;
	}

	public static Object getColumnValue(String ColumnName, Object row, BrowseColumn columns[])
	{
	    int	index = -1;
	    int	currentIndex = 0;
	    for (int i = 0; i < columns.length; i++)
        {
	        BrowseColumn currentColumn = columns[i];
	        if (currentColumn.isSqlPart()) {
	            if (currentColumn.getColumnName().equalsIgnoreCase(ColumnName))
		        {
		            index = currentIndex;
		            i = columns.length;
		        }
	            currentIndex++;
	        }
        }
	    Object returnObject;
	    try
	    {
	    	if (row instanceof Object[])
			{
				Object currentRow[] = (Object[])row;
				returnObject = currentRow[index];
			}
			else
			{
				Object currentRow = row;
				returnObject = currentRow;
			}
	    }
	    catch (Exception e)
	    {
	        returnObject = new String("");
	    }
	    return HiltonUtility.ckNull(returnObject);
	}
	public static String testPopulateLinkParameters(BrowseColumn column, Object currentRow, BrowseColumn columns[])
	{
	    String linkString = column.getLink();
	    StringBuffer linkSB = new StringBuffer(linkString);
	    ArrayList columnNames = column.getLinkedColumns();
	    for (int index = 0; index < columnNames.size(); index++)
        {
            Object value = BrowseUtility.getColumnValue((String)columnNames.get(index), currentRow, columns);
            try
            {
            	int poundIndex = linkSB.indexOf("#");
            	linkSB.replace(poundIndex, linkSB.indexOf("'", poundIndex), String.valueOf(value));
            	//linkString = linkString.replaceAll("#" + index, String.valueOf(value));
            }
            catch (Exception e) {
				e.printStackTrace();
			}
        }
	    return linkSB.toString();
	}

	public static String populateLinkParameters(BrowseColumn column, Object object)
	{
		Log.debug(browseUtility, column.getClassName() + " " + " BrowseUtility.populateLinkParameters().");

		String clink = column.getLink();

		if (clink.length() > 0)
		{
			/* Build Link Parameters */
			int p = clink.indexOf("?") ;

			if (p != -1)
			{
				StringBuffer sresult = new StringBuffer(clink.substring(0,p)) ;
				String params = clink.substring(p + 1) ;
				StringTokenizer parser = new StringTokenizer(params, "&");
				int numTokens = parser.countTokens();
				String snamevalue = null;
				String sname = null;
				String sdata = null ;
				String svalue = null;

				for (int bx = 0; bx < numTokens; bx++)
				{
					snamevalue = parser.nextToken();
					p = snamevalue.indexOf("=");

					if (p != -1)
					{
						sname = snamevalue.substring(0, p);
						svalue = snamevalue.substring(p + 1).trim();

						if (bx > 0)
						{
							sresult.append("&") ;
						}
						else
						{
							sresult.append("?") ;
						}

						if (svalue.substring(0,1).equals("#"))
						{
							svalue = svalue.substring(1) ;
							sresult.append(sname + "=" ) ;
							sdata = String.valueOf(BrowseUtility.getColumnValue(column, object)).trim() ;
							int ip = sdata.indexOf(" ") ;

							while (ip >= 0) {
								sresult.append(sdata.substring(0,ip) + "%20") ;
								sdata = sdata.substring(ip + 1) ;
								ip = sdata.indexOf(" ") ;
							}

							sresult.append(sdata) ;
						}
						else {
							sresult.append(sname + "=" + svalue) ;
						}
					}
				}
				clink = sresult.toString() ;
			}
			p = clink.indexOf("javascript");
			if (p != -1)
			{
				int pos = clink.indexOf("#");
				int subEnd = 0;
				String sdata = "";
				StringBuffer sb = new StringBuffer();

				while(pos > -1)
				{
					sb.append(clink.substring(subEnd, pos));
					subEnd = clink.indexOf("^", pos);
					String tmpName = clink.substring(pos + 1, subEnd);
					sdata = String.valueOf(BrowseUtility.getColumnValue(tmpName, object)).trim() ;
					sb.append(sdata);
					subEnd++;
					pos = clink.indexOf("#", subEnd);
				}
				sb.append(clink.substring(subEnd));
				clink = sb.toString();
			}
		}

		Log.debug(browseUtility, column.getClassName() + " " + " BrowseUtility.populateLinkParameters() COMPLETE.");

		return clink;
	}

	private List[] getRowValues(Object object, BrowseColumn[] browseColumns, String organizationId, String dateFormat) {
		List row[] = new ArrayList[3];
		List displayValues = new ArrayList();
		List hiddenValues = new ArrayList();
		List detailValues = new ArrayList();
		//BrowseUtility utility = new BrowseUtility();

		Log.debug(browseUtility, " BrowseUtility.getRowValues()");

		for (int i = 0; i < browseColumns.length; i++) {
			BrowseColumn column = BrowseUtility.getBrowseColumnCopy(browseColumns[i]);
			Object entity = BrowseUtility.getEntityObject(object, column);
			Object result = null;

			if (entity != null) {
				result = BrowseUtility.getColumnValue(column, entity);
			}

			if (Utility.isEmpty(column.getType()) && result instanceof BigDecimal) {
				column.setType("BigDecimal");
			}

			result = BrowseUtility.formatBrowseColumnValue(result, column, organizationId, dateFormat);

			column.setValue(result);
			column.setLink(BrowseUtility.populateLinkParameters(column, entity));

			if (column.isHidden()) {
				hiddenValues.add(column);
			}
			else if (column.isDetail()) {
				detailValues.add(column);
			}
			else {
				displayValues.add(column);
			}
		}

		row[0] = displayValues;
		row[1] = hiddenValues;
		row[2] = detailValues;

		Log.debug(browseUtility, " BrowseUtility.getRowValues() COMPLETE.");

		return row;
	}

	public static BrowseColumn getBrowseColumnCopy(BrowseColumn originalBC) {
		BrowseColumn column = new BrowseColumn(originalBC.getOid(), originalBC.getLanguage());
		column.setAlias(originalBC.getAlias());
		column.setAlignment(originalBC.getAlignment());
		column.setAllowFilter(originalBC.getAllowFilter());
		column.setArgumentColumns(originalBC.getArgumentColumns());
		column.setCheckbox(originalBC.isCheckbox());
		column.setClassName(originalBC.getClassName());
		column.setColumnName(originalBC.getColumnName());
		column.setComputed(originalBC.isComputed());
		column.setConcatenateColumns(originalBC.getConcatenateColumns());
		column.setDetail(originalBC.isDetail());
		column.setHidden(originalBC.isHidden());
		column.setHiddenInput(originalBC.isHiddenInput());
		column.setIndex(originalBC.getIndex());
		column.setInputSize(originalBC.getInputSize());
		column.setLabel(originalBC.getLabel());
		column.setLanguage(originalBC.getLanguage());
		column.setLink(originalBC.getLink());
		column.setLinkImage(originalBC.getLinkImage()) ;
		column.setLinkedColumns(originalBC.getLinkedColumns());
		column.setMethodName(originalBC.getMethodName());
		column.setOid(originalBC.getOid());
		column.setSelectInput(originalBC.isSelectInput());
		column.setSize(originalBC.getSize());
		column.setSort(originalBC.getSort());
		column.setSqlPart(originalBC.isSqlPart());
		column.setStoreRequestValue(originalBC.storeRequestValue());
		column.setTextInput(originalBC.isTextInput());
		column.setTrim(originalBC.getTrim());
		column.setType(originalBC.getType());
		column.setValue(originalBC.getValue());

		return column;
	}

	public static Object formatBrowseColumnValue(Object value, String type, String organizationId, String dateFormat)
	{
		return BrowseUtility.formatBrowseColumnValue(value, type, new ArrayList(), organizationId, dateFormat);
	}

	public static Object formatBrowseColumnValue(Object value, String type, List arguments, String organizationId, String dateFormat) {
		Object result = value;

        PropertiesManager props = PropertiesManager.getInstance(organizationId);

		if (result != null && !HiltonUtility.isEmpty(type) ) {
			if (type.equalsIgnoreCase("Date") || result instanceof java.sql.Date || result instanceof java.util.Date) {
				if (result instanceof String) {
					if (result != null && ! HiltonUtility.isEmpty((String) result)) result = Utility.getDateFormat(result, dateFormat);
				} else {
					if (result != null ) result = Utility.getDateFormat(result, dateFormat);
				}
			} else if (type.equalsIgnoreCase("QtyDecimal")) {
				result = Utility.getBigDecimalFormatted(result, Integer.valueOf(props.getProperty("MISC", "QTYDECIMAL", "2")).intValue());
			} else if (type.equalsIgnoreCase("QtyDecimal3")) {
				result = Utility.getBigDecimalFormatted(result, Integer.valueOf(props.getProperty("MISC", "QTYDECIMAL3", "3")).intValue());
			} else if (type.equalsIgnoreCase("CurrencyDecimal")) {
				String currencyCode = (!arguments.isEmpty()) ? (String) arguments.get(0) : props.getProperty("MISC", "BASECURRENCY", "USD");
				result = HiltonUtility.getFormattedCurrency(result, currencyCode, organizationId);
			} else if (type.equalsIgnoreCase("CurrencyFormat")) {
				String currencyCode = (!arguments.isEmpty()) ? (String) arguments.get(0) : props.getProperty("MISC", "BASECURRENCY", "USD");
				result = HiltonUtility.getCurrency(result, currencyCode, organizationId);
			} else if (type.equalsIgnoreCase("DollarDecimal")) {
				if (result == null || result instanceof String) {
					result = "";
				} else {
					result = HiltonUtility.getFormattedCurrency(result, props.getProperty("MISC", "BASECURRENCY", "USD"), organizationId);
				}
			} else if (type.equalsIgnoreCase("PriceDecimal")) {
				result = HiltonUtility.getFormattedPriceCurrency(result, props.getProperty("MISC", "BASECURRENCY", "USD"), organizationId);
			} else if (type.equalsIgnoreCase("PriceQuantity")) {
				result = HiltonUtility.getFormattedCurrency(result, props.getProperty("MISC", "QTYDECIMAL", "2"), organizationId, false);
			}else if (type.equalsIgnoreCase("BigDecimal")) {
				result = Utility.getBigDecimalFormatted(result, 2);
			} else if (type.equalsIgnoreCase("NoDecimal")) {
				result = Utility.getBigDecimalFormatted(result, 0);
			} else if (type.equalsIgnoreCase("STATUS")) {
				result = DocumentStatus.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("REQUISITION-TYPE")) {
				result = RequisitionType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("RFQ-TYPE")) {
				result = RfqType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("ORDER-TYPE")) {
				result = OrderType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("DISBURSEMENT-TYPE")) {
				result = DisbursementType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("STDDOCUMENT-TYPE")) {
				result = StdDocumentType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("STDQUESTION-RESPONSE-TYPE")) {
				result = StdQuestionResponseType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("AUTOGEN-TYPE")) {
				result = AutoGenType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("RECEIPT-TYPE")) {
				result = ReceiptType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("ITEM-ACTION-CODE")) {
				result = ItemActionCode.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("PERF-RATING")) {
				result = SupplierPerformanceRatings.getText((BigDecimal)result);
			} else if (type.equalsIgnoreCase("EMAIL")) {
				String s_result = (String) result;
				if (s_result.indexOf(";")>0)
					result = s_result.substring(0,s_result.indexOf(";"))+";...";
			} else if (type.equalsIgnoreCase("REPORTQUEUE-FREQUENCY")) {
				result = ReportQueueFrequency.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("USER-ID")) {
				try {
					String name = UserManager.getInstance().getUser(organizationId,String.valueOf(result)).getDisplayName();
					if (!Utility.isEmpty(name)) {
						result = name;
					}
				}
				catch (Exception e) {
					// use user id
				}
			} else if (type.equalsIgnoreCase("COMMODITY")) {
				try {
					String commodity = CommodityManager.getInstance().getCommodityDescription(organizationId, String.valueOf(result));
					if (!Utility.isEmpty(commodity)) {
						result = commodity;
					}
				}
				catch (Exception e) {
					// use commodity code
				}
			} else if (type.equalsIgnoreCase("CHECKLISTENTRY-RESPONSE-TYPE")) {
				result = ChecklistEntryResponseType.toString(String.valueOf(result), organizationId);
			} else if (type.equalsIgnoreCase("COUNTRY-CURRENCY-CODE")) {
			    result = CurrencyManager.getInstance(organizationId).getCurrencyCodeForCountry(String.valueOf(result));
			} else if (type.equalsIgnoreCase("PAYMENT-TYPE")) {
				result = PaymentType.toString(String.valueOf(result), organizationId);
            } else if (type.equalsIgnoreCase("EVALUATION-IMG")) {
                try {
                    BigDecimal bdRating = (BigDecimal) result;
                    String img = SupplierPerformanceRatings.getRatingImage(organizationId, bdRating, true);
                    if (!Utility.isEmpty(img)) {
                        result = img;
                    } else if (bdRating.compareTo(new BigDecimal(0)) == 0) {
                        result = "N/A";
                    } else {
                        result = bdRating;
                    }
                }
                catch (Exception e) {
                    result = "N/A";
                }
			} else if (type.equalsIgnoreCase("TIMEZONE")) {
                result = HiltonUtility.getFormattedTimeZone(String.valueOf(result));
            } else if (type.equalsIgnoreCase("UPPERCASE")) {
                result = HiltonUtility.ckNull(String.valueOf(result)).toUpperCase();
            } else if (type.equalsIgnoreCase("LOWERCASE")) {
                result = HiltonUtility.ckNull(String.valueOf(result)).toLowerCase();
            }
		}
		return result;
	}

	public static Object formatBrowseColumnValue(Object value, BrowseColumn column, List arguments, String organizationId, String dateFormat)
	{
		Object result = BrowseUtility.formatBrowseColumnValue(value, column.getType(), arguments, organizationId, dateFormat);

		return BrowseUtility.cleanObjectResult(result, column);
	}

	public static Object formatBrowseColumnValue(Object value, BrowseColumn column, String organizationId, String dateFormat)
	{
		Object result = BrowseUtility.formatBrowseColumnValue(value, column.getType(), organizationId, dateFormat);

		return BrowseUtility.cleanObjectResult(result, column);
	}

	public static Object cleanObjectResult(Object result, BrowseColumn column)
	{

		if (result == null)
		{
			if (result instanceof BigDecimal)
			{
				result = new BigDecimal("0");
			} else
			{
				result = "";
			}
		}

		if (result instanceof String)
		{
			String temp = String.valueOf(result);
			if (column.getTrim() > 0 && !column.isHidden())
			{
				if (temp.length() > column.getTrim())
				{
					temp = temp.substring(0, column.getTrim());
				}
			}

			if (!column.getClassName().equalsIgnoreCase("Input") && !column.getType().equals("Checkbox"))
			{
				temp = temp.replaceAll(String.valueOf('"'), "&quot;");
			}

			result = temp;
		}

		return result;
	}

	public static Object getEntityObject(Object object, BrowseColumn column) {
		Log.debug(browseUtility, column.getClassName() + " " + column.getColumnName() + " BrowseUtility.getEntityObject()");
		try {
			if (object instanceof Object[]) {
				Object objects[] = (Object[]) object;
				if (column.getClassName().equalsIgnoreCase("Input")) {
					return objects[0];
				}
				String className = "com.tsa.puridiom.entity." + column.getClassName();
				Object entity = null;
				for (int i = 0; i < objects.length; i++) {
					Object entityObj = objects[i];
					if (entityObj != null) {
						String objName = entityObj.getClass().getName();
						if (objName.equals(className)) {
							entity = entityObj;
						}
					}
				}
				return entity;
			}
			else {
				return object;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return object;
		}
	}

    /**
     * @param BrowseColumn column
     * @return ArrayList
     */
    public static ArrayList getColumnNamesFromLink(BrowseColumn column)
    {

        String linkString = column.getLink();
        ArrayList links = new ArrayList();

        if (linkString.length() > 0)
		{
			/* Build Link Parameters */
			int p = linkString.indexOf("?") ;

			if (p != -1)
			{
				StringBuffer sresult = new StringBuffer(linkString.substring(0,p)) ;
				String params = linkString.substring(p + 1) ;
				StringTokenizer parser = new StringTokenizer(params, "&");
				int numTokens = parser.countTokens();
				String snamevalue = null;
				String sname = null;
				String sdata = null ;
				String svalue = null;

				for (int bx = 0; bx < numTokens; bx++)
				{
					snamevalue = parser.nextToken();
					p = snamevalue.indexOf("=");

					if (p != -1)
					{
						sname = snamevalue.substring(0, p);
						svalue = snamevalue.substring(p + 1).trim();

						if (bx > 0)
						{
							sresult.append("&") ;
						}
						else
						{
							sresult.append("?") ;
						}

						if (svalue.substring(0,1).equals("#"))
						{
							svalue = svalue.substring(1) ;
							sresult.append(sname + "=" ) ;
							links.add(svalue);

							sresult.append("#" + bx) ;
						}
						else
						{
							sresult.append(sname + "=" + svalue) ;
						}
					}
				}
				linkString = sresult.toString() ;
			}
			p = linkString.indexOf("javascript");
			if (p != -1)
			{
				int pos = linkString.indexOf("#");
				int subEnd = 0;
				String sdata = "";
				StringBuffer sb = new StringBuffer();
				int bx = 0;
				while(pos > -1)
				{
					sb.append(linkString.substring(subEnd, pos));
					subEnd = linkString.indexOf("^", pos);
					String tmpName = linkString.substring(pos + 1, subEnd);
					//sdata = String.valueOf(BrowseUtility.getTestColumnValue(column, object)).trim() ;
					links.add(tmpName);
					sb.append("#" + bx);
					subEnd++;
					bx++;
					pos = linkString.indexOf("#", subEnd);
				}
				sb.append(linkString.substring(subEnd));
				linkString = sb.toString();
			}
		}
        column.setLinkedColumns(links);
        column.setLink(linkString);
        return links;
    }

    /**
     * @param BrowseColumn column
     * @return ArrayList
     */
    public static String getValueOption(BrowseColumn column)
    {

    	String value = (String) column.getValue();
    	int pos = value.indexOf("#");

    	if (pos != -1)
    	{
    		StringBuffer sb = new StringBuffer();
			int subEnd = 0;
			int bx = 0;

			sb.append(value.substring(subEnd, pos));
			subEnd = value.indexOf("^", pos);
			String tmpName = value.substring(pos + 1, subEnd);
			sb.append("#" + bx);
			subEnd++;
			sb.append(value.substring(subEnd));

			value = "#"+tmpName;
    	}
    	column.setValue(value);
    	return value;
    }

	public static Map getColumnKeyValue(List browseKeys, Object resultObj, String organizationId, BrowseColumn[] browseColumns, String browseId, String userDateFormat) throws Exception {
	    Map	keyMap = new HashMap();

	    try {
	        for (int ik = 0; ik < browseKeys.size(); ik++) {
	            BrowseColumn keyColumn = (BrowseColumn) browseKeys.get(ik);
	            Object keyObj = BrowseUtility.getTestColumnValueObject(keyColumn, resultObj, organizationId, browseColumns, browseId, userDateFormat);
	            String	thisKey = "";
	            if (keyObj instanceof String) {
	                thisKey = (String) keyObj;
	            } else if (keyObj instanceof BigDecimal) {
	                thisKey = ((BigDecimal) keyObj).toString();
	            } else if (keyObj instanceof StringBuffer) {
	                thisKey = keyObj.toString();
	            }
	            keyMap.put(keyColumn.getAlias(), thisKey);
	        }
	    } catch (Exception e) {
	        throw e;
	    }
        return keyMap;
	}

	public static Map getColumnKeyValue(List browseKeys, Object resultObj, String organizationId, BrowseColumn[] browseColumns, String browseId, String userDateFormat, String columnName) throws Exception {
	    Map	keyMap = new HashMap();

	    try {
	        for (int ik = 0; ik < browseKeys.size(); ik++) {
	            BrowseColumn keyColumn = (BrowseColumn) browseKeys.get(ik);
	            Object keyObj = BrowseUtility.getTestColumnValueObject(keyColumn, resultObj, organizationId, browseColumns, browseId, userDateFormat);
	            String	thisKey = "";
	            if (keyObj instanceof String) {
	                thisKey = (String) keyObj;
	            } else if (keyObj instanceof BigDecimal) {
	                thisKey = ((BigDecimal) keyObj).toString();
	            }
	            keyMap.put(keyColumn.getAlias()+ "-" + "Input_" + columnName.substring(2), thisKey);
	        }
	    } catch (Exception e) {
	        throw e;
	    }
        return keyMap;
	}
}