/**
 *
 */
package com.tsa.puridiom.invoiceheader.tasks;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceHeader;
import com.tsa.puridiom.entity.PaymentTerm;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.paymentterm.tasks.PaymentTermManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;

/**
 * @author Johnny
 */
public class InvoiceHeaderUpdateSetValues extends Task
{

	/*
	 * (non-Javadoc)
	 *
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			InvoiceHeader invoiceHeader = (InvoiceHeader) incomingRequest.get("invoiceHeader");
			String organizationId = (String) incomingRequest.get("organizationId");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            String userDateFormat = (String) incomingRequest.get("userDateFormat");

            PropertiesManager propertiesManager = PropertiesManager.getInstance(organizationId);

            if (HiltonUtility.isEmpty(userDateFormat)) {
                userDateFormat = propertiesManager.getProperty("MISC", "DATEFORMAT", "MM-dd-yyyy");
            }

			String invoiceDateString = "";
			String today = Dates.today(userDateFormat, userTimeZone);
			int offsetDays = 0;
			boolean updateDueDate = false;
			String sTermsCode = "";
			String calculatePaymentDate = propertiesManager.getProperty("VOUCHER DEFAULTS", "CALCULATE PAYMENT DATE", "N");

			try
			{
				offsetDays = Integer.valueOf(propertiesManager.getProperty("VOUCHER DEFAULTS", "DateOffset", "0")).intValue();
			} catch (NumberFormatException e)
			{
				offsetDays = 0;
			}

			if (incomingRequest.containsKey("InvoiceHeader_invoiceDate"))
			{
				invoiceDateString = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceHeader_invoiceDate"));

				if (!HiltonUtility.isEmpty(invoiceDateString))
				{
					if (!invoiceDateString.equals(today))
					{
						Date invoiceDate = Dates.getSqlDate(invoiceDateString, userDateFormat);

						invoiceHeader.setInvoiceDate(invoiceDate);

						updateDueDate = true;
					}
				}

				if (invoiceHeader != null)
					sTermsCode = invoiceHeader.getTermsCode();
			}

			if (incomingRequest.containsKey("InvoiceHeader_termsCode"))
			{
				String termsCode = HiltonUtility.ckNull((String) incomingRequest.get("InvoiceHeader_termsCode"));

				if (!invoiceHeader.getTermsCode().equals(termsCode))
				{
					updateDueDate = true;
				}

				sTermsCode = termsCode;
			}

			Object paymentTerm = (Object)PaymentTermManager.getInstance().getPaymentTerm(organizationId, sTermsCode);

			if (updateDueDate && (invoiceHeader.getInvoiceDate() != null))
			{
				invoiceDateString = HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), organizationId);

				BigDecimal bgTermDays = new BigDecimal(0);
				if (paymentTerm != null)
				{
					bgTermDays = ((PaymentTerm)paymentTerm).getTermDays();
					incomingRequest.put("InvoiceHeader_paymentDueDate", Dates.add(invoiceDateString, bgTermDays.intValue(), userDateFormat));
				}
				else
				{
					incomingRequest.put("InvoiceHeader_paymentDueDate", Dates.add(invoiceDateString, (int) offsetDays, userDateFormat));
				}
			}
			if(calculatePaymentDate.equalsIgnoreCase("Y"))
			{
				offsetDays = Integer.valueOf(propertiesManager.getProperty("VOUCHER DEFAULTS", "DateOffsetPayment", "0")).intValue();
				BigDecimal bgTermDays = new BigDecimal(0);
				if (paymentTerm != null)
				{
					bgTermDays = ((PaymentTerm)paymentTerm).getTermDays();
					bgTermDays = bgTermDays.add(new BigDecimal(offsetDays));
					incomingRequest.put("InvoiceHeader_paymentDate", Dates.add(today, bgTermDays.intValue(), userDateFormat));
				}
			}
			if(invoiceHeader!= null){
				BigDecimal type = ((PaymentTerm)paymentTerm).getTermTypeFlag();
				BigDecimal discountDays = new BigDecimal(0);
				invoiceDateString = HiltonUtility.getFormattedDate(invoiceHeader.getInvoiceDate(), organizationId);
				if (paymentTerm != null && organizationId.equalsIgnoreCase("wpc08p"))
				{
					discountDays = ((PaymentTerm)paymentTerm).getDiscountDays();

					if (discountDays.compareTo(new BigDecimal("0")) == 0)
					{
						incomingRequest.put("InvoiceHeader_discountDate","");
					}
					else if ( type.compareTo(new BigDecimal("0"))== 0 )
					{
						incomingRequest.put("InvoiceHeader_discountDate", Dates.add(invoiceDateString, discountDays.intValue(), userDateFormat));
					}
					else if ( type.compareTo(new BigDecimal("1"))== 0 )
					{
						// just for format MM/dd/yyyy
						String month = invoiceDateString.substring(0,2);
						String day = invoiceDateString.substring(3,5);
						String year =  invoiceDateString.substring(6,10);
						int imonth = Integer.parseInt(month)+ 1;
						month = String.valueOf(imonth);
						if (imonth < 10)
						{
							month = "0" + imonth;
						}
						if (imonth == 13)
						{
							month = "01";
							int iyear = Integer.parseInt(year)+ 1;
							year = String.valueOf(iyear);
						}

						day = String.valueOf(discountDays);
						if (discountDays.intValue() < 10)
						{
							day = "0" + day;
						}

						incomingRequest.put("InvoiceHeader_discountDate", month + "/" + day + "/" + year);
					}

				}
			}
			this.setStatus(Status.SUCCEEDED);

		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);

			Log.error(this, "InvoiceHeaderUpdateSetValues error " + e.getMessage());

			throw e;
		}

		return result;
	}
}
