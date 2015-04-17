package com.tsa.puridiom.sungard.extract;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Account;
import com.tsa.puridiom.entity.Address;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.Vendor;
import com.tsa.puridiom.entity.InvoiceAddress;
import com.tsagate.foundation.processengine.RuleHelper;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.Utility;

public class InvoiceExtractUtility extends Task {

    public static String	generateExtractLine(ExtractColumn extractColumns[], Map incomingRequest) {
        StringBuffer exportText = new StringBuffer();

        try {
	        int currentPosition = 1;

	        InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
	        InvoiceLine invoiceLine = (InvoiceLine) incomingRequest.get("invoiceLine");
	        RequisitionHeader requisitionHeader = (RequisitionHeader) incomingRequest.get("requisitionHeader");
	        RequisitionLine requisitionLine = (RequisitionLine) incomingRequest.get("requisitionLine");
	        Account account = (Account) incomingRequest.get("account");
	        Address address = (Address) incomingRequest.get("address");
	        Vendor vendor = (Vendor) incomingRequest.get("vendor") ;
	        InvoiceAddress invoiceAddress = (InvoiceAddress) incomingRequest.get("invoiceAddress") ;
	        String delimiter = (String) incomingRequest.get("delimiter") ;
	        boolean delimited = false ;

	        if (HiltonUtility.ckNull(delimiter).trim().length() > 0) delimited = true ;
	        for (int ic = 0; ic < extractColumns.length; ic++) {
	            ExtractColumn extractColumn = extractColumns[ic];
	            int	startPosition = extractColumn.getStartPosition();
	            int	size = extractColumn.getSize();
	            String justify = extractColumn.getJustify() ;

	            // ADD SPACES TO MAKE SURE FIELD STARTS ON CORRECT POSITION - IF START POSITION IS SET ( > 0)
	            if (! delimited) {
		            if (startPosition > 0) {
			            while (currentPosition < startPosition) {
			                exportText.append(" ");
			                currentPosition = exportText.length() + 1;
			            }
		            }
	            }

	            String	type = extractColumn.getType();
	            RuleHelper	rule = extractColumn.getRule();
	            String	value = "";

	            if (rule == null || rule.executeRule(incomingRequest)) {
		            if (type.equalsIgnoreCase("CONCATENATE")) {
		                List subColumnList = extractColumn.getConcatenateColumns();
		                StringBuffer sb = new StringBuffer();

		                if (subColumnList != null) {
		                    for (int ix = 0; ix < subColumnList.size(); ix++) {
		                        ExtractColumn subColumn = (ExtractColumn) subColumnList.get(ix);
		                        String	subType = subColumn.getType();
		                        String	tempValue = "";
		                        RuleHelper	subRule = subColumn.getRule();

		        	            if (subRule == null || subRule.executeRule(incomingRequest)) {
			                        if (subType.equalsIgnoreCase("CONSTANT")) {
			                            tempValue = subColumn.getValue().toString();
			                        } else if (subType.equalsIgnoreCase("SPACE")) {
			                        	tempValue = padRight(value, size) ;
			                        }
			                        else if (subType.equalsIgnoreCase("DEFAULT-VALUE"))
			    		            {
			                        	tempValue = subColumn.getColumnName();
			    		            }
			                        else if (subType.equalsIgnoreCase("SYSDATE")) {
			                        	String	extractTimestamp = Dates.today("yyyy-MM-dd hh:mm:ssss", "");
			                    		String	year = extractTimestamp.substring(0, 4);
			                    		String	month = extractTimestamp.substring(5, 7);
			                    		String	day = extractTimestamp.substring(8, 10);
			                    		tempValue = day + month + year;
			                        } else {
			                        	if(address != null)
			                        	{
			                        		tempValue = InvoiceExtractUtility.getValueFromClassObject(subColumn, requisitionHeader, requisitionLine, invoiceHeader, invoiceLine, account, address, vendor, invoiceAddress);
			                        	}
			                        	else
			                        	{
			                        		tempValue = InvoiceExtractUtility.getValueFromClassObject(subColumn, requisitionHeader, requisitionLine, invoiceHeader, invoiceLine, account, vendor, invoiceAddress);
			                        	}
			                        }
		        	            }

		                        sb.append(tempValue);
		                    }
		                    value = sb.toString();
		                }
		            }
		            else if (type.equalsIgnoreCase("DEFAULT-VALUE"))
		            {
		            	value = extractColumn.getColumnName();
		            }
		            else
		            {
		            	if(address != null)
                    	{
		            		value = InvoiceExtractUtility.getValueFromClassObject(extractColumn, requisitionHeader, requisitionLine, invoiceHeader, invoiceLine, account, address, vendor, invoiceAddress);
                    	}
                    	else
                    	{
                    		value = InvoiceExtractUtility.getValueFromClassObject(extractColumn, requisitionHeader, requisitionLine, invoiceHeader, invoiceLine, account, vendor, invoiceAddress);
                    	}

		            }
	        	}

	            // REMOVE CARRIAGE RETUNS
	            value = value.replaceAll("\r\n", "");

	            // TRIM TO SIZE
	            if (value.length() > size) {
	                value = value.substring(0, size);
	            }

	            if (! delimited) {
		            if (justify.equalsIgnoreCase("R")) {
		            	value = padLeft(value, size) ;
		            } else {
		            	value = padRight(value, size) ;
		            }
	            } else {
	            	value = value.trim();
	            }

	            exportText.append(value);
	            if (ic < extractColumns.length - 1 && delimited) exportText.append(delimiter) ;

	            currentPosition = exportText.length() + 1;
	        }
        } catch (Exception e) {
            e.printStackTrace();
            Log.error(InvoiceExtractUtility.class, e.getMessage());
        }
        return exportText.toString();
    }

	private static String getExtractColumnValue(ExtractColumn extractColumn, RequisitionHeader requisitionHeader, RequisitionLine requisitionLine, InvoiceHeader invoiceHeader, InvoiceLine invoiceLine, Account account, Vendor vendor, InvoiceAddress invoiceAddress) {
	    String value = "";

	    try {
	        String	type = extractColumn.getType();
	        int	size = extractColumn.getSize();

		    if (type.equalsIgnoreCase("CONSTANT")) {
	            value = extractColumn.getValue().toString();
	        } else if (type.equalsIgnoreCase("SPACE")) {
	        	value = padRight(value, size) ;
	        } else {
	            value = InvoiceExtractUtility.getValueFromClassObject(extractColumn, requisitionHeader, requisitionLine, invoiceHeader, invoiceLine, account, vendor, invoiceAddress);
	        }
	    } catch (Exception e) {
	        if (extractColumn == null) {
		        Log.error(InvoiceExtractUtility.class, "ExtractColumn is null");
	        } else {
	            Log.error(InvoiceExtractUtility.class, "An error occured trying to get the value for: " + extractColumn.toString());
	        }
	    }
	    return value;
	}
	private static String getValueFromClassObject(ExtractColumn extractColumn, RequisitionHeader requisitionHeader, RequisitionLine requisitionLine, InvoiceHeader invoiceHeader, InvoiceLine invoiceLine, Account account, Vendor vendor, InvoiceAddress invoiceAddress) {
	    String	value = "";

	    try {
	        Object classObject = null;
	        String	className = extractColumn.getClassName();
	        String	type = extractColumn.getType();

	        if (className.equalsIgnoreCase("InvoiceHeader")) {
	            classObject = invoiceHeader;
	        } else if (className.equalsIgnoreCase("InvoiceLine")) {
	            classObject = invoiceLine;
	        } else if (className.equalsIgnoreCase("RequisitionHeader")) {
	            classObject = requisitionLine;
	        } else if (className.equalsIgnoreCase("RequisitionLine")) {
	            classObject = requisitionLine;
	        } else if (className.equalsIgnoreCase("Account")) {
	            classObject = account;
	        } else if (className.equalsIgnoreCase("Vendor")) {
	            classObject = vendor ;
	        } else if (className.equalsIgnoreCase("InvoiceAddress")) {
	            classObject = invoiceAddress ;
	        } else {
	            // INVALID CLASS NAME
	        }

	        if (classObject != null) {

	            Method method = classObject.getClass().getMethod(extractColumn.getMethodName(), null);
	            Object tempValue = method.invoke(classObject, null);

	            if (type.equalsIgnoreCase("DECIMAL")) {
	                BigDecimal bd = (BigDecimal) tempValue;
	                bd = Utility.getBigDecimalFormatted(bd, extractColumn.getDecimals());
	                value = bd.toString();
	                if (! extractColumn.isDecimalPoint()) {
	                	// no decimals
		                value = value.replace(".", "") ;
	                }
	            } else if (type.equalsIgnoreCase("DATE")) {
	                Date date = (Date) tempValue;
	                String	format = extractColumn.getFormat();

	                value = Utility.getDateFormat(date, format);
	            } else {
	                value = tempValue.toString();
	            }
	        } else {
	        	if (extractColumn.getColumnName().equalsIgnoreCase("InvoiceLine_description")) {
	        		if (account == null) {
		        			value = "" ;
		        	} else {
		        		if (account.getAccountType().equals("IVS")) {
		        				value = "Shipping Charges" ;
		        		} else if (account.getAccountType().equals("IVT")) {
		        			value = "Tax Amount" ;
		        		} else if (account.getAccountType().equals("IVO")) {
		        			value = "Other Charges" ;
		        		}
		        	}
	        	}
	        }
	    } catch (Exception e) {
	        if (extractColumn == null) {
		        Log.error(InvoiceExtractUtility.class, "ExtractColumn is null");
	        } else {
	            Log.error(InvoiceExtractUtility.class, "An error occured trying to get the value from the class object for: " + extractColumn.toString());
	        }
	    }
        return value;
	}
	private static String getValueFromClassObject(ExtractColumn extractColumn, RequisitionHeader requisitionHeader, RequisitionLine requisitionLine, InvoiceHeader invoiceHeader, InvoiceLine invoiceLine, Account account, Address address, Vendor vendor, InvoiceAddress invoiceAddress) {
	    String	value = "";

	    try {
	        Object classObject = null;
	        String	className = extractColumn.getClassName();
	        String	type = extractColumn.getType();

	        if (className.equalsIgnoreCase("InvoiceHeader")) {
	            classObject = invoiceHeader;
	        } else if (className.equalsIgnoreCase("InvoiceLine")) {
	            classObject = invoiceLine;
	        } else if (className.equalsIgnoreCase("RequisitionHeader")) {
	            classObject = requisitionHeader;
	        } else if (className.equalsIgnoreCase("RequisitionLine")) {
	            classObject = requisitionLine;
	        } else if (className.equalsIgnoreCase("Account")) {
	            classObject = account;
	        } else if (className.equalsIgnoreCase("Address")) {
	            classObject = address;
	        } else if (className.equalsIgnoreCase("Vendor")) {
	            classObject = vendor ;
	        } else if (className.equalsIgnoreCase("InvoiceAddress")) {
	            classObject = invoiceAddress ;
	        } else {
	            // INVALID CLASS NAME
	        }

	        if (classObject != null) {

	            Method method = classObject.getClass().getMethod(extractColumn.getMethodName(), null);
	            Object tempValue = method.invoke(classObject, null);

	            if (type.equalsIgnoreCase("DECIMAL")) {
	                BigDecimal bd = (BigDecimal) tempValue;
	                bd = Utility.getBigDecimalFormatted(bd, extractColumn.getDecimals());

	                value = bd.toString();
	                if (! extractColumn.isDecimalPoint()) {
	                	// no decimals
		                value = value.replace(".", "") ;
	                }
	            } else if (type.equalsIgnoreCase("DATE")) {
	                Date date = (Date) tempValue;
	                String	format = extractColumn.getFormat();

	                value = Utility.getDateFormat(date, format);
	            } else {
	                value = tempValue.toString();
	                if (extractColumn.getColumnName().equalsIgnoreCase("InvoiceLine_description")) {
	                	if (HiltonUtility.isEmpty(value)  && invoiceLine != null) {
	                		// Force item number into description if description is blank
	                		value = invoiceLine.getItemNumber() ;
	                	}
	                }
	            }
	        } else {
	        	if (extractColumn.getColumnName().equalsIgnoreCase("InvoiceLine_description")) {
	        		if (account == null) {
		        			value = "" ;
		        	} else {
		        		if (account.getAccountType().equals("IVS")) {
		        				value = "Shipping Charges" ;
		        		} else if (account.getAccountType().equals("IVT")) {
		        			value = "Tax Amount" ;
		        		} else if (account.getAccountType().equals("IVO")) {
		        			value = "Other Charges" ;
		        		}
		        	}
	        	}
	        }
	    } catch (Exception e) {
	        if (extractColumn == null) {
		        Log.error(InvoiceExtractUtility.class, "ExtractColumn is null");
	        } else {
	            Log.error(InvoiceExtractUtility.class, "An error occured trying to get the value from the class object for: " + extractColumn.toString());
	        }
	    }
        return value;
	}

	private static String padLeft(String value, int size) {
        while (value.length() < size) {
            value = " " + value ;
        }

        return value ;
	}

	private static String padRight(String value, int size) {
        while (value.length() < size) {
            value = value + " ";
        }

        return value ;
	}

}