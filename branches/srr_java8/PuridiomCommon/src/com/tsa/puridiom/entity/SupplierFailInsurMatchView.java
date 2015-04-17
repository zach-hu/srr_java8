package com.tsa.puridiom.entity;

import java.io.Serializable;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

public class SupplierFailInsurMatchView extends Entity implements Serializable{
	 /** identifier field */
    private String vendorId;

    /** nullable persistent field */
    private String vendorName;

    /** nullable persistent field */
    private String city;

    /** nullable persistent field */
    private String state;

    /** nullable persistent field */
    private String commodity;

    public SupplierFailInsurMatchView(java.lang.String vendorId, java.lang.String vendorName, java.lang.String city, java.lang.String state, java.lang.String commodity){
    	this.vendorId=vendorId;
    	this.vendorName=vendorName;
    	this.city=city;
    	this.state=state;
    	this.commodity=commodity;
    }

    /** default constructor */
    public SupplierFailInsurMatchView() {
    }

    /** minimal constructor */
    public SupplierFailInsurMatchView(java.lang.String vendorId) {
        this.vendorId = vendorId;
    }

    public java.lang.String getVendorId() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorId).trim();
    }

    public void setVendorId(java.lang.String vendorId) {
		if (!HiltonUtility.isEmpty(vendorId) && vendorId.length() > 15) {
			vendorId = vendorId.substring(0, 15);
		}
		this.vendorId = vendorId;
    }

    public java.lang.String getVendorName() {
		return (java.lang.String)HiltonUtility.ckNull(this.vendorName).trim();
    }

    public void setVendorName(java.lang.String vendorName) {
		if (!HiltonUtility.isEmpty(vendorName) && vendorName.length() > 40) {
			vendorName = vendorName.substring(0, 40);
		}
		this.vendorName = vendorName;
    }
   public java.lang.String getCity() {
		return (java.lang.String)HiltonUtility.ckNull(this.city).trim();
   }

   public void setCity(java.lang.String city) {
		if (!HiltonUtility.isEmpty(city) && city.length() > 30) {
				city = city.substring(0, 30);
		}
		this.city = city;
   }

   public java.lang.String getState() {
		return (java.lang.String)HiltonUtility.ckNull(this.state).trim();
   }

   public void setState(java.lang.String state) {
		if (!HiltonUtility.isEmpty(state) && state.length() > 15) {
				state = state.substring(0, 15);
		}
		this.state = state;
	}

	public java.lang.String getCommodity() {
		return (java.lang.String)HiltonUtility.ckNull(this.commodity).trim();
	}

	public void setCommodity(String commodity) {
		if (!HiltonUtility.isEmpty(commodity) && commodity.length() > 255) {
			commodity = commodity.substring(0, 255);
		}
		this.commodity = commodity;
	}
}

