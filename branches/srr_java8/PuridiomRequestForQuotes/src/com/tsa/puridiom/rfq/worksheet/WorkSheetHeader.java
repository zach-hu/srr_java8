package com.tsa.puridiom.rfq.worksheet;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.tsa.puridiom.entity.RfqHeader;
import com.tsa.puridiom.entity.RfqVendor;
import com.tsa.puridiom.vendor.VendorManager;

public class WorkSheetHeader
{
	private String bidNumber;
	private Date date;
	private String facility;
	private RfqHeader rfqHeader;

	private String vendorId1;
	private String vendorName1;
	private String contactId1;
	private String contactName1;
	private Date dateResponseRecv1;
	private String bidResponseCode1;
	private BigDecimal vendorSubTotal1;
	private String fob1;
	private String terms1;
	private Date datePromised1;
	private Date bidValidTo1;
	private BigDecimal tax1;
	private BigDecimal shipping1;
	private BigDecimal others1;
	private BigDecimal totalScore1;
	private String vendorId2;
	private String vendorName2;
	private String contactId2;
	private String contactName2;
	private Date dateResponseRecv2;
	private String bidResponseCode2;
	private BigDecimal vendorSubTotal2;
	private String fob2;
	private String terms2;
	private Date datePromised2;
	private Date bidValidTo2;
	private BigDecimal tax2;
	private BigDecimal shipping2;
	private BigDecimal others2;
	private BigDecimal totalScore2;
	private String vendorId3;
	private String vendorName3;
	private String contactId3;
	private String contactName3;
	private Date dateResponseRecv3;
	private String bidResponseCode3;
	private BigDecimal vendorSubTotal3;
	private String fob3;
	private String terms3;
	private Date datePromised3;
	private Date bidValidTo3;
	private BigDecimal tax3;
	private BigDecimal shipping3;
	private BigDecimal others3;
	private BigDecimal totalScore3;
	private String vendorId4;
	private String vendorName4;
	private String contactId4;
	private String contactName4;
	private Date dateResponseRecv4;
	private String bidResponseCode4;
	private BigDecimal vendorSubTotal4;
	private String fob4;
	private String terms4;
	private Date datePromised4;
	private Date bidValidTo4;
	private BigDecimal tax4;
	private BigDecimal shipping4;
	private BigDecimal others4;
	private BigDecimal totalScore4;
	private String vendorId5;
	private String vendorName5;
	private String contactId5;
	private String contactName5;
	private Date dateResponseRecv5;
	private String bidResponseCode5;
	private BigDecimal vendorSubTotal5;
	private String fob5;
	private String terms5;
	private Date datePromised5;
	private Date bidValidTo5;
	private BigDecimal tax5;
	private BigDecimal shipping5;
	private BigDecimal others5;
	private BigDecimal totalScore5;

	private String buyer;
	private String status;

	public WorkSheetHeader(RfqHeader rfqHeader)
	{
		this.setBidNumber(rfqHeader.getRfqNumber());
		this.setDate(rfqHeader.getRfqDate());
		this.setStatus(rfqHeader.getStatus());
		this.setRfqHeader(rfqHeader);
	}
	private void setVendors(int vendIndex, RfqVendor rfqVendor, String organizationId)
	{
		String vendorId = rfqVendor.getComp_id().getVendorId();
		String vendorName = "";
		String contactName = rfqVendor.getContactName();
		String contactId = rfqVendor.getContactId();
		Date dateResponseRecv = rfqVendor.getDateResponseRecv();
		String bidResponseCode = rfqVendor.getBidResponseCode();
		String fob = rfqVendor.getFob();
		String terms = rfqVendor.getPaymentTerms();
		Date datePromised = rfqVendor.getDatePromised();
		Date bidValidTo = rfqVendor.getBidValidTo();
		BigDecimal tax = rfqVendor.getTaxAmount();
		BigDecimal shipping = rfqVendor.getShippingCharges();
		BigDecimal shippingTax = rfqVendor.getShippingTaxAmt();
		BigDecimal otherCharges = rfqVendor.getOtherCharges();
		BigDecimal totalScore = rfqVendor.getTotalScore();

		try {
			vendorName = VendorManager.getInstance().getVendorName(organizationId, vendorId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BigDecimal subTotal = rfqVendor.getBidSubtotal();

		switch (vendIndex)
		{
			case 0:
				this.setVendorId1(vendorId);
				this.setVendorName1(vendorName);
				this.setVendorSubTotal1(subTotal);
				this.setContactName1(contactName);
				this.setContactId1(contactId);
				this.setDateResponseRecv1(dateResponseRecv);
				this.setBidResponseCode1(bidResponseCode);
				this.setFob1(fob);
				this.setTerms1(terms);
				this.setDatePromised1(datePromised);
				this.setBidValidTo1(bidValidTo);
				this.setTax1(tax);
				this.setShipping1(shipping);
				this.setOthers1(otherCharges);
				this.setTotalScore1(totalScore);
				break;
			case 1:
				this.setVendorId2(vendorId);
				this.setVendorName2(vendorName);
				this.setVendorSubTotal2(subTotal);
				this.setContactName2(contactName);
				this.setContactId2(contactId);
				this.setDateResponseRecv2(dateResponseRecv);
				this.setBidResponseCode2(bidResponseCode);
				this.setFob2(fob);
				this.setTerms2(terms);
				this.setDatePromised2(datePromised);
				this.setBidValidTo2(bidValidTo);
				this.setTax2(tax);
				this.setShipping2(shipping);
				this.setOthers2(otherCharges);
				this.setTotalScore2(totalScore);
				break;
			case 2:
				this.setVendorId3(vendorId);
				this.setVendorName3(vendorName);
				this.setVendorSubTotal3(subTotal);
				this.setContactName3(contactName);
				this.setContactId3(contactId);
				this.setDateResponseRecv3(dateResponseRecv);
				this.setBidResponseCode3(bidResponseCode);
				this.setFob3(fob);
				this.setTerms3(terms);
				this.setDatePromised3(datePromised);
				this.setBidValidTo3(bidValidTo);
				this.setTax3(tax);
				this.setShipping3(shipping);
				this.setOthers3(otherCharges);
				this.setTotalScore3(totalScore);
				break;
			case 3:
				this.setVendorId4(vendorId);
				this.setVendorName4(vendorName);
				this.setVendorSubTotal4(subTotal);
				this.setContactName4(contactName);
				this.setContactId4(contactId);
				this.setDateResponseRecv4(dateResponseRecv);
				this.setBidResponseCode4(bidResponseCode);
				this.setFob4(fob);
				this.setTerms4(terms);
				this.setDatePromised4(datePromised);
				this.setBidValidTo4(bidValidTo);
				this.setTax4(tax);
				this.setShipping4(shipping);
				this.setOthers4(otherCharges);
				this.setTotalScore4(totalScore);
				break;
			case 4:
				this.setVendorId5(vendorId);
				this.setVendorName5(vendorName);
				this.setVendorSubTotal5(subTotal);
				this.setContactName5(contactName);
				this.setContactId5(contactId);
				this.setDateResponseRecv5(dateResponseRecv);
				this.setBidResponseCode5(bidResponseCode);
				this.setFob5(fob);
				this.setTerms5(terms);
				this.setDatePromised5(datePromised);
				this.setBidValidTo5(bidValidTo);
				this.setTax5(tax);
				this.setShipping5(shipping);
				this.setOthers5(otherCharges);
				this.setTotalScore5(totalScore);
				break;
			default:
				break;
		}

	}
	public WorkSheetHeader(RfqHeader rfqHeader, List vendors, String organizationId)
	{
		this(rfqHeader);

		if (vendors != null)
		{
			for (int ib = 0; ib < vendors.size(); ib++)
			{
				RfqVendor vendor = (RfqVendor) vendors.get(ib);
				this.setVendors(ib, vendor, organizationId);
			}
		}
	}
	public String getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getVendorId1() {
		return vendorId1;
	}
	public void setVendorId1(String vendorId1) {
		this.vendorId1 = vendorId1;
	}
	public String getVendorId2() {
		return vendorId2;
	}
	public void setVendorId2(String vendorId2) {
		this.vendorId2 = vendorId2;
	}
	public String getVendorId3() {
		return vendorId3;
	}
	public void setVendorId3(String vendorId3) {
		this.vendorId3 = vendorId3;
	}
	public String getVendorId4() {
		return vendorId4;
	}
	public void setVendorId4(String vendorId4) {
		this.vendorId4 = vendorId4;
	}
	public String getVendorId5() {
		return vendorId5;
	}
	public void setVendorId5(String vendorId5) {
		this.vendorId5 = vendorId5;
	}
	public String getVendorName1() {
		return vendorName1;
	}
	public void setVendorName1(String vendorName1) {
		this.vendorName1 = vendorName1;
	}
	public String getVendorName2() {
		return vendorName2;
	}
	public void setVendorName2(String vendorName2) {
		this.vendorName2 = vendorName2;
	}
	public String getVendorName3() {
		return vendorName3;
	}
	public void setVendorName3(String vendorName3) {
		this.vendorName3 = vendorName3;
	}
	public String getVendorName4() {
		return vendorName4;
	}
	public void setVendorName4(String vendorName4) {
		this.vendorName4 = vendorName4;
	}
	public String getVendorName5() {
		return vendorName5;
	}
	public void setVendorName5(String vendorName5) {
		this.vendorName5 = vendorName5;
	}

	public BigDecimal getVendorSubTotal1() {
		return vendorSubTotal1;
	}

	public void setVendorSubTotal1(BigDecimal vendorSubTotal1) {
		this.vendorSubTotal1 = vendorSubTotal1;
	}

	public BigDecimal getVendorSubTotal2() {
		return vendorSubTotal2;
	}

	public void setVendorSubTotal2(BigDecimal vendorSubTotal2) {
		this.vendorSubTotal2 = vendorSubTotal2;
	}

	public BigDecimal getVendorSubTotal3() {
		return vendorSubTotal3;
	}

	public void setVendorSubTotal3(BigDecimal vendorSubTotal3) {
		this.vendorSubTotal3 = vendorSubTotal3;
	}

	public BigDecimal getVendorSubTotal4() {
		return vendorSubTotal4;
	}

	public void setVendorSubTotal4(BigDecimal vendorSubTotal4) {
		this.vendorSubTotal4 = vendorSubTotal4;
	}

	public BigDecimal getVendorSubTotal5() {
		return vendorSubTotal5;
	}

	public void setVendorSubTotal5(BigDecimal vendorSubTotal5) {
		this.vendorSubTotal5 = vendorSubTotal5;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getContactName1() {
		return contactName1;
	}
	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}
	public String getContactName2() {
		return contactName2;
	}
	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}
	public String getContactName3() {
		return contactName3;
	}
	public void setContactName3(String contactName3) {
		this.contactName3 = contactName3;
	}
	public String getContactName4() {
		return contactName4;
	}
	public void setContactName4(String contactName4) {
		this.contactName4 = contactName4;
	}
	public String getContactName5() {
		return contactName5;
	}
	public void setContactName5(String contactName5) {
		this.contactName5 = contactName5;
	}
	public String getContactId1() {
		return contactId1;
	}
	public void setContactId1(String contactId1) {
		this.contactId1 = contactId1;
	}
	public String getContactId2() {
		return contactId2;
	}
	public void setContactId2(String contactId2) {
		this.contactId2 = contactId2;
	}
	public String getContactId3() {
		return contactId3;
	}
	public void setContactId3(String contactId3) {
		this.contactId3 = contactId3;
	}
	public String getContactId4() {
		return contactId4;
	}
	public void setContactId4(String contactId4) {
		this.contactId4 = contactId4;
	}
	public String getContactId5() {
		return contactId5;
	}
	public void setContactId5(String contactId5) {
		this.contactId5 = contactId5;
	}
	public Date getDateResponseRecv1() {
		return dateResponseRecv1;
	}
	public void setDateResponseRecv1(Date dateResponseRecv1) {
		this.dateResponseRecv1 = dateResponseRecv1;
	}
	public Date getDateResponseRecv2() {
		return dateResponseRecv2;
	}
	public void setDateResponseRecv2(Date dateResponseRecv2) {
		this.dateResponseRecv2 = dateResponseRecv2;
	}
	public Date getDateResponseRecv3() {
		return dateResponseRecv3;
	}
	public void setDateResponseRecv3(Date dateResponseRecv3) {
		this.dateResponseRecv3 = dateResponseRecv3;
	}
	public Date getDateResponseRecv4() {
		return dateResponseRecv4;
	}
	public void setDateResponseRecv4(Date dateResponseRecv4) {
		this.dateResponseRecv4 = dateResponseRecv4;
	}
	public Date getDateResponseRecv5() {
		return dateResponseRecv5;
	}
	public void setDateResponseRecv5(Date dateResponseRecv5) {
		this.dateResponseRecv5 = dateResponseRecv5;
	}
	public String getBidResponseCode1() {
		return bidResponseCode1;
	}
	public void setBidResponseCode1(String bidResponseCode1) {
		this.bidResponseCode1 = bidResponseCode1;
	}
	public String getBidResponseCode2() {
		return bidResponseCode2;
	}
	public void setBidResponseCode2(String bidResponseCode2) {
		this.bidResponseCode2 = bidResponseCode2;
	}
	public String getBidResponseCode3() {
		return bidResponseCode3;
	}
	public void setBidResponseCode3(String bidResponseCode3) {
		this.bidResponseCode3 = bidResponseCode3;
	}
	public String getBidResponseCode4() {
		return bidResponseCode4;
	}
	public void setBidResponseCode4(String bidResponseCode4) {
		this.bidResponseCode4 = bidResponseCode4;
	}
	public String getBidResponseCode5() {
		return bidResponseCode5;
	}
	public void setBidResponseCode5(String bidResponseCode5) {
		this.bidResponseCode5 = bidResponseCode5;
	}
	public Date getBidValidTo1() {
		return bidValidTo1;
	}
	public void setBidValidTo1(Date bidValidTo1) {
		this.bidValidTo1 = bidValidTo1;
	}
	public Date getBidValidTo2() {
		return bidValidTo2;
	}
	public void setBidValidTo2(Date bidValidTo2) {
		this.bidValidTo2 = bidValidTo2;
	}
	public Date getBidValidTo3() {
		return bidValidTo3;
	}
	public void setBidValidTo3(Date bidValidTo3) {
		this.bidValidTo3 = bidValidTo3;
	}
	public Date getBidValidTo4() {
		return bidValidTo4;
	}
	public void setBidValidTo4(Date bidValidTo4) {
		this.bidValidTo4 = bidValidTo4;
	}
	public Date getBidValidTo5() {
		return bidValidTo5;
	}
	public void setBidValidTo5(Date bidValidTo5) {
		this.bidValidTo5 = bidValidTo5;
	}
	public Date getDatePromised1() {
		return datePromised1;
	}
	public void setDatePromised1(Date datePromised1) {
		this.datePromised1 = datePromised1;
	}
	public Date getDatePromised2() {
		return datePromised2;
	}
	public void setDatePromised2(Date datePromised2) {
		this.datePromised2 = datePromised2;
	}
	public Date getDatePromised3() {
		return datePromised3;
	}
	public void setDatePromised3(Date datePromised3) {
		this.datePromised3 = datePromised3;
	}
	public Date getDatePromised4() {
		return datePromised4;
	}
	public void setDatePromised4(Date datePromised4) {
		this.datePromised4 = datePromised4;
	}
	public Date getDatePromised5() {
		return datePromised5;
	}
	public void setDatePromised5(Date datePromised5) {
		this.datePromised5 = datePromised5;
	}
	public String getFob1() {
		return fob1;
	}
	public void setFob1(String fob1) {
		this.fob1 = fob1;
	}
	public String getFob2() {
		return fob2;
	}
	public void setFob2(String fob2) {
		this.fob2 = fob2;
	}
	public String getFob3() {
		return fob3;
	}
	public void setFob3(String fob3) {
		this.fob3 = fob3;
	}
	public String getFob4() {
		return fob4;
	}
	public void setFob4(String fob4) {
		this.fob4 = fob4;
	}
	public String getFob5() {
		return fob5;
	}
	public void setFob5(String fob5) {
		this.fob5 = fob5;
	}
	public BigDecimal getOthers1() {
		return others1;
	}
	public void setOthers1(BigDecimal others1) {
		this.others1 = others1;
	}
	public BigDecimal getOthers2() {
		return others2;
	}
	public void setOthers2(BigDecimal others2) {
		this.others2 = others2;
	}
	public BigDecimal getOthers3() {
		return others3;
	}
	public void setOthers3(BigDecimal others3) {
		this.others3 = others3;
	}
	public BigDecimal getOthers4() {
		return others4;
	}
	public void setOthers4(BigDecimal others4) {
		this.others4 = others4;
	}
	public BigDecimal getOthers5() {
		return others5;
	}
	public void setOthers5(BigDecimal others5) {
		this.others5 = others5;
	}
	public BigDecimal getShipping1() {
		return shipping1;
	}
	public void setShipping1(BigDecimal shipping1) {
		this.shipping1 = shipping1;
	}
	public BigDecimal getShipping2() {
		return shipping2;
	}
	public void setShipping2(BigDecimal shipping2) {
		this.shipping2 = shipping2;
	}
	public BigDecimal getShipping3() {
		return shipping3;
	}
	public void setShipping3(BigDecimal shipping3) {
		this.shipping3 = shipping3;
	}
	public BigDecimal getShipping4() {
		return shipping4;
	}
	public void setShipping4(BigDecimal shipping4) {
		this.shipping4 = shipping4;
	}
	public BigDecimal getShipping5() {
		return shipping5;
	}
	public void setShipping5(BigDecimal shipping5) {
		this.shipping5 = shipping5;
	}
	public BigDecimal getTax1() {
		return tax1;
	}
	public void setTax1(BigDecimal tax1) {
		this.tax1 = tax1;
	}
	public BigDecimal getTax2() {
		return tax2;
	}
	public void setTax2(BigDecimal tax2) {
		this.tax2 = tax2;
	}
	public BigDecimal getTax3() {
		return tax3;
	}
	public void setTax3(BigDecimal tax3) {
		this.tax3 = tax3;
	}
	public BigDecimal getTax4() {
		return tax4;
	}
	public void setTax4(BigDecimal tax4) {
		this.tax4 = tax4;
	}
	public BigDecimal getTax5() {
		return tax5;
	}
	public void setTax5(BigDecimal tax5) {
		this.tax5 = tax5;
	}
	public String getTerms1() {
		return terms1;
	}
	public void setTerms1(String terms1) {
		this.terms1 = terms1;
	}
	public String getTerms2() {
		return terms2;
	}
	public void setTerms2(String terms2) {
		this.terms2 = terms2;
	}
	public String getTerms3() {
		return terms3;
	}
	public void setTerms3(String terms3) {
		this.terms3 = terms3;
	}
	public String getTerms4() {
		return terms4;
	}
	public void setTerms4(String terms4) {
		this.terms4 = terms4;
	}
	public String getTerms5() {
		return terms5;
	}
	public void setTerms5(String terms5) {
		this.terms5 = terms5;
	}
	public RfqHeader getRfqHeader()
	{
		return rfqHeader;
	}
	public void setRfqHeader(RfqHeader rfqHeader)
	{
		this.rfqHeader = rfqHeader;
	}
	/**
	 * @return the totalScore1
	 */
	public BigDecimal getTotalScore1()
	{
		return totalScore1;
	}
	/**
	 * @param totalScore1 the totalScore1 to set
	 */
	public void setTotalScore1(BigDecimal totalScore1)
	{
		this.totalScore1 = totalScore1;
	}
	/**
	 * @return the totalScore2
	 */
	public BigDecimal getTotalScore2()
	{
		return totalScore2;
	}
	/**
	 * @param totalScore2 the totalScore2 to set
	 */
	public void setTotalScore2(BigDecimal totalScore2)
	{
		this.totalScore2 = totalScore2;
	}
	/**
	 * @return the totalScore3
	 */
	public BigDecimal getTotalScore3()
	{
		return totalScore3;
	}
	/**
	 * @param totalScore3 the totalScore3 to set
	 */
	public void setTotalScore3(BigDecimal totalScore3)
	{
		this.totalScore3 = totalScore3;
	}
	/**
	 * @return the totalScore4
	 */
	public BigDecimal getTotalScore4()
	{
		return totalScore4;
	}
	/**
	 * @param totalScore4 the totalScore4 to set
	 */
	public void setTotalScore4(BigDecimal totalScore4)
	{
		this.totalScore4 = totalScore4;
	}
	/**
	 * @return the totalScore5
	 */
	public BigDecimal getTotalScore5()
	{
		return totalScore5;
	}
	/**
	 * @param totalScore5 the totalScore5 to set
	 */
	public void setTotalScore5(BigDecimal totalScore5)
	{
		this.totalScore5 = totalScore5;
	}
}
