package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

/** @author Hibernate CodeGenerator */
public class RfqLine extends Entity implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icRfqLine;

    /** nullable persistent field */
    private java.math.BigDecimal icRfqHeader;

    /** nullable persistent field */
    private java.math.BigDecimal rfqLine;

    /** nullable persistent field */
    private String rfqNumber;

    /** nullable persistent field */
    private String source;

    /** nullable persistent field */
    private String itemNumber;

    /** nullable persistent field */
    private String umCode;

    /** nullable persistent field */
    private java.math.BigDecimal quantity;

    /** nullable persistent field */
    private String taxable;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String commodity;

    /** nullable persistent field */
    private java.math.BigDecimal icSource;

    /** nullable persistent field */
    private String commentFlag;

    /** nullable persistent field */
    private String asset;

    /** nullable persistent field */
    private String splits;

    /** nullable persistent field */
    private String catalogId;

    /** nullable persistent field */
    private java.math.BigDecimal umFactor;

    /** nullable persistent field */
    private java.math.BigDecimal icReqLine;

    /** nullable persistent field */
    private String shiptoFlag;

    /** nullable persistent field */
    private java.math.BigDecimal allocated;

    /** nullable persistent field */
    private String mfgName;

    /** nullable persistent field */
    private String modelNumber;

    /** nullable persistent field */
    private String udf1Code;

    /** nullable persistent field */
    private String udf2Code;

    /** nullable persistent field */
    private String udf3Code;

    /** nullable persistent field */
    private String udf4Code;

    /** nullable persistent field */
    private String udf5Code;

    /** nullable persistent field */
    private String lineRevNo;

    /** nullable persistent field */
    private java.math.BigDecimal icLineHistory;

    /** nullable persistent field */
    private String itemLocation;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String taxCode;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String receiptRequired;

    /** nullable persistent field */
    private String vendorAwarded;

	private List billToList;

	private List docCommentList;

	private List shipToList;

	private List rfqBidList;

	private List rfqBidGroups;

	/* List of documents added to be saved and shown in po_item.jps */
	private List docAttachmentList;

	private List requisitionLineInfoList;

	private List poLineInfoList;

	private List inspectionList;

	private String lowestVendorId;

	private java.math.BigDecimal lowestBid;

	private java.math.BigDecimal averageBid;

	private java.math.BigDecimal icAccount;

	private String rfqNote;

	private java.math.BigDecimal icXls;

    /** nullable persistent field */
    private String udf6Code;

    /** nullable persistent field */
    private String udf7Code;

    /** nullable persistent field */
    private String udf8Code;

    /** nullable persistent field */
    private String udf9Code;

    /** nullable persistent field */
    private String udf10Code;

    /** nullable persistent field */
    private String memoLine;

    /** nullable persistent field */
    private String shelfLifeRqd;


    /** full constructor */
    public RfqLine(java.math.BigDecimal icRfqLine, java.math.BigDecimal icRfqHeader, java.math.BigDecimal rfqLine, java.lang.String rfqNumber, java.lang.String source, java.lang.String itemNumber, java.lang.String umCode, java.math.BigDecimal quantity, java.lang.String taxable, java.lang.String status, java.lang.String commodity, java.math.BigDecimal icSource, java.lang.String commentFlag, java.lang.String asset, java.lang.String splits, java.lang.String catalogId, java.math.BigDecimal umFactor, java.math.BigDecimal icReqLine, java.lang.String shiptoFlag, java.math.BigDecimal allocated, java.lang.String mfgName, java.lang.String modelNumber, java.lang.String udf1Code, java.lang.String udf2Code, java.lang.String udf3Code, java.lang.String udf4Code, java.lang.String udf5Code, java.lang.String lineRevNo, java.math.BigDecimal icLineHistory, java.lang.String itemLocation, java.lang.String description, java.lang.String taxCode, java.lang.String routing, java.lang.String receiptRequired,BigDecimal icAccount, java.lang.String vendorAwarded, java.math.BigDecimal icXls, java.lang.String shelfLifeRqd){

    	this.icRfqLine = icRfqLine;
        this.icRfqHeader = icRfqHeader;
        this.rfqLine = rfqLine;
        this.rfqNumber = rfqNumber;
        this.source = source;
        this.itemNumber = itemNumber;
        this.umCode = umCode;
        this.quantity = quantity;
        this.taxable = taxable;
        this.status = status;
        this.commodity = commodity;
        this.icSource = icSource;
        this.commentFlag = commentFlag;
        this.asset = asset;
        this.splits = splits;
        this.catalogId = catalogId;
        this.umFactor = umFactor;
        this.icReqLine = icReqLine;
        this.shiptoFlag = shiptoFlag;
        this.allocated = allocated;
        this.mfgName = mfgName;
        this.modelNumber = modelNumber;
        this.udf1Code = udf1Code;
        this.udf2Code = udf2Code;
        this.udf3Code = udf3Code;
        this.udf4Code = udf4Code;
        this.udf5Code = udf5Code;
        this.lineRevNo = lineRevNo;
        this.icLineHistory = icLineHistory;
        this.itemLocation = itemLocation;
        this.description = description;
        this.taxCode = taxCode;
        this.routing = routing;
        this.receiptRequired = receiptRequired;
        this.icAccount = icAccount ;
        this.vendorAwarded = vendorAwarded;
        this.icXls = icXls;
        this.shelfLifeRqd = shelfLifeRqd;
    }

    /** default constructor */
    public RfqLine() {
    }

    /** minimal constructor */
    public RfqLine(java.math.BigDecimal icRfqLine) {
        this.icRfqLine = icRfqLine;
    }

    public java.math.BigDecimal getIcRfqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqLine);
    }

    public void setIcRfqLine(java.math.BigDecimal icRfqLine) {
        this.icRfqLine = icRfqLine;
    }

    public java.math.BigDecimal getIcRfqHeader() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icRfqHeader);
    }

    public void setIcRfqHeader(java.math.BigDecimal icRfqHeader) {
        this.icRfqHeader = icRfqHeader;
    }

    public java.math.BigDecimal getRfqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.rfqLine);
    }

    public void setRfqLine(java.math.BigDecimal rfqLine) {
        this.rfqLine = rfqLine;
    }

    public java.lang.String getRfqNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqNumber).trim();
    }

    public void setRfqNumber(java.lang.String rfqNumber) {
		if (!HiltonUtility.isEmpty(rfqNumber) && rfqNumber.length() > 20) {
			rfqNumber = rfqNumber.substring(0, 20);
		}
		this.rfqNumber = rfqNumber;
    }

    public java.lang.String getSource() {
		return (java.lang.String)HiltonUtility.ckNull(this.source).trim();
    }

    public void setSource(java.lang.String source) {
		if (!HiltonUtility.isEmpty(source) && source.length() > 3) {
			source = source.substring(0, 3);
		}
		this.source = source;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber).trim();
    }

    public void setItemNumber(java.lang.String itemNumber) {
		if (!HiltonUtility.isEmpty(itemNumber) && itemNumber.length() > 30) {
			itemNumber = itemNumber.substring(0, 30);
		}
		this.itemNumber = itemNumber;
    }

    public java.lang.String getUmCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.umCode).trim();
    }

    public void setUmCode(java.lang.String umCode) {
		if (!HiltonUtility.isEmpty(umCode) && umCode.length() > 15) {
			umCode = umCode.substring(0, 15);
		}
		this.umCode = umCode;
    }

    public java.math.BigDecimal getQuantity() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.quantity);
    }

    public void setQuantity(java.math.BigDecimal quantity) {
        this.quantity = quantity;
    }

    public java.lang.String getTaxable() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxable).trim();
    }

    public void setTaxable(java.lang.String taxable) {
		if (!HiltonUtility.isEmpty(taxable) && taxable.length() > 1) {
			taxable = taxable.substring(0, 1);
		}
		this.taxable = taxable;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		if (!HiltonUtility.isEmpty(status) && status.length() > 4) {
			status = status.substring(0, 4);
		}
		this.status = status;
    }

    public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
    }

    public void setCommodity(java.lang.String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 15) {
			commodity = commodity.substring(0, 15);
		}
		this.commodity = commodity;
    }

    public java.math.BigDecimal getIcSource() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icSource);
    }

    public void setIcSource(java.math.BigDecimal icSource) {
        this.icSource = icSource;
    }

    public java.lang.String getCommentFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.commentFlag).trim();
    }

    public void setCommentFlag(java.lang.String commentFlag) {
		if (!HiltonUtility.isEmpty(commentFlag) && commentFlag.length() > 1) {
			commentFlag = commentFlag.substring(0, 1);
		}
		this.commentFlag = commentFlag;
    }

    public java.lang.String getAsset() {
		return (java.lang.String)HiltonUtility.ckNull(this.asset).trim();
    }

    public void setAsset(java.lang.String asset) {
		if (!HiltonUtility.isEmpty(asset) && asset.length() > 1) {
			asset = asset.substring(0, 1);
		}
		this.asset = asset;
    }

    public java.lang.String getSplits() {
		return (java.lang.String)HiltonUtility.ckNull(this.splits).trim();
    }

    public void setSplits(java.lang.String splits) {
		if (!HiltonUtility.isEmpty(splits) && splits.length() > 1) {
			splits = splits.substring(0, 1);
		}
		this.splits = splits;
    }

    public java.lang.String getCatalogId() {
		return (java.lang.String)HiltonUtility.ckNull(this.catalogId).trim();
    }

    public void setCatalogId(java.lang.String catalogId) {
		if (!HiltonUtility.isEmpty(catalogId) && catalogId.length() > 15) {
			catalogId = catalogId.substring(0, 15);
		}
		this.catalogId = catalogId;
    }

    public java.math.BigDecimal getUmFactor() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.umFactor);
    }

    public void setUmFactor(java.math.BigDecimal umFactor) {
        this.umFactor = umFactor;
    }

    public java.math.BigDecimal getIcReqLine() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icReqLine);
    }

    public void setIcReqLine(java.math.BigDecimal icReqLine) {
        this.icReqLine = icReqLine;
    }

    public java.lang.String getShiptoFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.shiptoFlag).trim();
    }

    public void setShiptoFlag(java.lang.String shiptoFlag) {
		if (!HiltonUtility.isEmpty(shiptoFlag) && shiptoFlag.length() > 1) {
			shiptoFlag = shiptoFlag.substring(0, 1);
		}
		this.shiptoFlag = shiptoFlag;
    }

    public java.math.BigDecimal getAllocated() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.allocated);
    }

    public void setAllocated(java.math.BigDecimal allocated) {
        this.allocated = allocated;
    }

    public java.lang.String getMfgName() {
		return (java.lang.String)HiltonUtility.ckNull(this.mfgName).trim();
    }

    public void setMfgName(java.lang.String mfgName) {
		if (!HiltonUtility.isEmpty(mfgName) && mfgName.length() > 60) {
			mfgName = mfgName.substring(0, 60);
		}
		this.mfgName = mfgName;
    }

    public java.lang.String getModelNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.modelNumber).trim();
    }

    public void setModelNumber(java.lang.String modelNumber) {
		if (!HiltonUtility.isEmpty(modelNumber) && modelNumber.length() > 20) {
			modelNumber = modelNumber.substring(0, 20);
		}
		this.modelNumber = modelNumber;
    }

    public java.lang.String getUdf1Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf1Code).trim();
    }

    public void setUdf1Code(java.lang.String udf1Code) {
		if (!HiltonUtility.isEmpty(udf1Code) && udf1Code.length() > 30) {
			udf1Code = udf1Code.substring(0, 30);
		}
		this.udf1Code = udf1Code;
    }

    public java.lang.String getUdf2Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf2Code).trim();
    }

    public void setUdf2Code(java.lang.String udf2Code) {
		if (!HiltonUtility.isEmpty(udf2Code) && udf2Code.length() > 15) {
			udf2Code = udf2Code.substring(0, 15);
		}
		this.udf2Code = udf2Code;
    }

    public java.lang.String getUdf3Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf3Code).trim();
    }

    public void setUdf3Code(java.lang.String udf3Code) {
		if (!HiltonUtility.isEmpty(udf3Code) && udf3Code.length() > 15) {
			udf3Code = udf3Code.substring(0, 15);
		}
		this.udf3Code = udf3Code;
    }

    public java.lang.String getUdf4Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf4Code).trim();
    }

    public void setUdf4Code(java.lang.String udf4Code) {
		if (!HiltonUtility.isEmpty(udf4Code) && udf4Code.length() > 15) {
			udf4Code = udf4Code.substring(0, 15);
		}
		this.udf4Code = udf4Code;
    }

    public java.lang.String getUdf5Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf5Code).trim();
    }

    public void setUdf5Code(java.lang.String udf5Code) {
		if (!HiltonUtility.isEmpty(udf5Code) && udf5Code.length() > 15) {
			udf5Code = udf5Code.substring(0, 15);
		}
		this.udf5Code = udf5Code;
    }

    public java.lang.String getLineRevNo() {
		return (java.lang.String)HiltonUtility.ckNull(this.lineRevNo).trim();
    }

    public void setLineRevNo(java.lang.String lineRevNo) {
		if (!HiltonUtility.isEmpty(lineRevNo) && lineRevNo.length() > 20) {
			lineRevNo = lineRevNo.substring(0, 20);
		}
		this.lineRevNo = lineRevNo;
    }

    public java.math.BigDecimal getIcLineHistory() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icLineHistory);
    }

    public void setIcLineHistory(java.math.BigDecimal icLineHistory) {
        this.icLineHistory = icLineHistory;
    }

    public java.lang.String getItemLocation() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemLocation).trim();
    }

    public void setItemLocation(java.lang.String itemLocation) {
		if (!HiltonUtility.isEmpty(itemLocation) && itemLocation.length() > 15) {
			itemLocation = itemLocation.substring(0, 15);
		}
		this.itemLocation = itemLocation;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		if (!HiltonUtility.isEmpty(description) && description.length() > 2000) {
			description = description.substring(0, 2000);
		}
		this.description = description;
    }

    public java.lang.String getTaxCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.taxCode).trim();
    }

    public void setTaxCode(java.lang.String taxCode) {
		if (!HiltonUtility.isEmpty(taxCode) && taxCode.length() > 15) {
			taxCode = taxCode.substring(0, 15);
		}
		this.taxCode = taxCode;
    }

    public java.lang.String getRouting() {
		return (java.lang.String)HiltonUtility.ckNull(this.routing).trim();
    }

    public void setRouting(java.lang.String routing) {
		if (!HiltonUtility.isEmpty(routing) && routing.length() > 25) {
			routing = routing.substring(0, 25);
		}
		this.routing = routing;
    }

	public List getBillToList() {
		return this.billToList;
	}

	public void setBillToList(List billToList) {
		this.billToList = billToList;
	}

	public List getDocCommentList() {



		return this.docCommentList;
	}

	public void setDocCommentList(List docCommentList) {
		this.docCommentList = docCommentList;
	}

	public List getShipToList() {
		return this.shipToList;
	}

	public void setShipToList(List shipToList) {
		this.shipToList = shipToList;
	}

	public List getRfqBidList() {
		return this.rfqBidList;
	}

	public void setRfqBidList(List rfqBidList) {
		this.rfqBidList = rfqBidList;
	}

	public List getRequisitionLineInfoList() {
		return this.requisitionLineInfoList;
	}

	public void setRequisitionLineInfoList(List requisitionLineInfoList) {
		this.requisitionLineInfoList = requisitionLineInfoList;
	}

	public List getPoLineInfoList() {
		return this.poLineInfoList;
	}

	public void setPoLineInfoList(List poLineInfoList) {
		this.poLineInfoList = poLineInfoList;
	}

	public String getLowestVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.lowestVendorId).trim();
	}

	public void setLowestVendorId(String lowestVendorId) {
		this.lowestVendorId = lowestVendorId;
	}

	public java.math.BigDecimal getLowestBid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lowestBid);
	}

	public void setLowestBid(java.math.BigDecimal lowestBid) {
		this.lowestBid = lowestBid;
	}

	public java.math.BigDecimal getAverageBid() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.averageBid);
	}

	public void setAverageBid(java.math.BigDecimal averageBid) {
		this.averageBid = averageBid;
	}
	public String getRfqNote() {
		return (java.lang.String)HiltonUtility.ckNull(this.rfqNote).trim();
	}

	public void setRfqNote(String rfqNote) {
		this.rfqNote = rfqNote;
	}

	public BigDecimal getIcAccount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icAccount);
	}

	public void setIcAccount(BigDecimal icAccount) {
		this.icAccount = icAccount;
	}

    public java.lang.String getReceiptRequired() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiptRequired).trim();
    }

    public void setReceiptRequired(java.lang.String receiptRequired) {
		if (!HiltonUtility.isEmpty(receiptRequired) && receiptRequired.length() > 1) {
			receiptRequired = receiptRequired.substring(0, 1);
		}
		this.receiptRequired = receiptRequired;
    }



    public String getVendorAwarded() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorAwarded).trim();
	}

	public void setVendorAwarded(String vendorAwarded) {
		this.vendorAwarded = vendorAwarded;
	}



     public java.math.BigDecimal getIcXls() {
		return this.icXls;
    }

    public void setIcXls(java.math.BigDecimal icXls) {
        this.icXls = icXls;
    }

    public java.lang.String getUdf6Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf6Code).trim();
    }

    public void setUdf6Code(java.lang.String udf6Code) {
		if (!HiltonUtility.isEmpty(udf6Code) && udf6Code.length() > 15) {
			udf6Code = udf6Code.substring(0, 15);
		}
		this.udf6Code = udf6Code;
    }

    public java.lang.String getUdf7Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf7Code).trim();
    }

    public void setUdf7Code(java.lang.String udf7Code) {
		if (!HiltonUtility.isEmpty(udf7Code) && udf7Code.length() > 15) {
			udf7Code = udf7Code.substring(0, 15);
		}
		this.udf7Code = udf7Code;
    }

    public java.lang.String getUdf8Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf8Code).trim();
    }

    public void setUdf8Code(java.lang.String udf8Code) {
		if (!HiltonUtility.isEmpty(udf8Code) && udf8Code.length() > 15) {
			udf8Code = udf8Code.substring(0, 15);
		}
		this.udf8Code = udf8Code;
    }

    public java.lang.String getUdf9Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf9Code).trim();
    }

    public void setUdf9Code(java.lang.String udf9Code) {
		if (!HiltonUtility.isEmpty(udf9Code) && udf9Code.length() > 15) {
			udf9Code = udf9Code.substring(0, 15);
		}
		this.udf9Code = udf9Code;
    }

    public java.lang.String getUdf10Code() {
		return (java.lang.String)HiltonUtility.ckNull(this.udf10Code).trim();
    }

    public void setUdf10Code(java.lang.String udf10Code) {
		if (!HiltonUtility.isEmpty(udf10Code) && udf10Code.length() > 15) {
			udf10Code = udf10Code.substring(0, 15);
		}
		this.udf10Code = udf10Code;
    }

    public java.lang.String getMemoLine() {
		return (java.lang.String)HiltonUtility.ckNull(this.memoLine).trim();
    }

    public void setMemoLine(java.lang.String memoLine) {
		if (!HiltonUtility.isEmpty(memoLine) && memoLine.length() > 255) {
			memoLine = memoLine.substring(0, 255);
		}
		this.memoLine = memoLine;
    }

    public java.lang.String getShelfLifeRqd() {
		return (java.lang.String)HiltonUtility.ckNull(this.shelfLifeRqd).trim();
    }

    public void setShelfLifeRqd(java.lang.String shelfLifeRqd) {
		if (!HiltonUtility.isEmpty(shelfLifeRqd) && shelfLifeRqd.length() > 1) {
			shelfLifeRqd = shelfLifeRqd.substring(0, 1);
		}
		this.shelfLifeRqd = shelfLifeRqd;
    }


    public List getRfqSelectedBidGroup(int groupElement, int groupBy, List rfqVendorList)
    {

        List rfqBidGroup = new ArrayList();

        if (rfqBidList == null) {
            // no bids
            return rfqBidGroup;
        }

		/*if (this.rfqBidList.size() < groupBy) {
		    // only one group of bids
		    if (groupElement == 0) {
		        // return all bids
		        return rfqBidList;
		    } else {
		        // no bids for this element
		        return rfqBidGroup;
		    }
		}*/

		List	tempBids = new ArrayList();
		for(int  i = 0; i < this.rfqBidList.size(); i++)
		{
			RfqBid rfqBid = (RfqBid)this.rfqBidList.get(i);
			String bidVendorId =rfqBid.getComp_id().getVendorId();
			for(int vendorIndex = 0; vendorIndex < rfqVendorList.size(); vendorIndex++)
			{
				RfqVendor rfqVendor = (RfqVendor)rfqVendorList.get(vendorIndex);
				if(rfqVendor.getVendorId().equalsIgnoreCase(bidVendorId))
				{
					tempBids.add(this.rfqBidList.get(i));
					vendorIndex = rfqVendorList.size();
				}
			}
		}

		return tempBids;

    }
    public java.util.List getRfqBidGroup(int groupElement, int groupBy) {
        List rfqBidGroup = new ArrayList();

        if (rfqBidList == null) {
            // no bids
            return rfqBidGroup;
        }

		if (this.rfqBidList.size() < groupBy) {
		    // only one group of bids
		    if (groupElement == 0) {
		        // return all bids
		        return rfqBidList;
		    } else {
		        // no bids for this element
		        return rfqBidGroup;
		    }
		}

		List	tempBids = new ArrayList();
		int	fromIndex = groupElement * groupBy;
		int	toIndex = (groupElement * groupBy) + groupBy;

		if (toIndex > rfqBidList.size()) {
		    toIndex = rfqBidList.size();
		}

		return rfqBidList.subList(fromIndex, toIndex);
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("icRfqLine", getIcRfqLine())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof RfqLine) ) return false;
        RfqLine castOther = (RfqLine) other;
        return new EqualsBuilder()
            .append(this.getIcRfqLine(), castOther.getIcRfqLine())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getIcRfqLine())
            .toHashCode();
    }

	public List getDocAttachmentList() {
		return docAttachmentList;
	}

	public void setDocAttachmentList(List docAttachmentList) {
		this.docAttachmentList = docAttachmentList;
	}

	public List getInspectionList() {
		return inspectionList;
	}

	public void setInspectionList(List inspectionList) {
		this.inspectionList = inspectionList;
	}

}
