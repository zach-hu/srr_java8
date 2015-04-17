package com.tsa.puridiom.entity ;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class SrrWorkorderView implements Serializable {

    /** nullable persistent field */
    private String workorderNo;

    /** nullable persistent field */
    private String taskNo;

    /** nullable persistent field */
    private String workorderClosed;

    /** nullable persistent field */
    private String idpNo;

    /** nullable persistent field */
    private String waCode;

    /** nullable persistent field */
    private String equipNo;

    /** nullable persistent field */
    private String facility;

    /** nullable persistent field */
    private String corpType;

    /** nullable persistent field */
    private String unit;

    /** nullable persistent field */
    private String componentNo;

    /** nullable persistent field */
    private String area;

    /** nullable persistent field */
    private String utczNo;

    private String systemType;
    /** nullable persistent field */

    private String manufacturerCode;

    /** nullable persistent field */
    private String equipType;

    /** nullable persistent field */
    private String modelNo;

    /** nullable persistent field */
    private String serialNo ;

    /** full constructor */
    public SrrWorkorderView(java.lang.String workorderNo, java.lang.String  taskNo, java.lang.String workorderClosed,
    		java.lang.String idpNo, java.lang.String waCode, java.lang.String equipNo, java.lang.String facility,
    		java.lang.String corpType, java.lang.String unit, java.lang.String componentNo, java.lang.String area ,
    		java.lang.String utczNo,  java.lang.String systemType, java.lang.String manufacturerCode,
    		java.lang.String equipType, java.lang.String modelNo, java.lang.String serialNo) {
    	this.workorderNo = workorderNo ;
    	this.taskNo = taskNo;
    	this.workorderClosed = workorderClosed ;
    	this.idpNo = idpNo ;
    	this.waCode = waCode ;
    	this.equipNo = equipNo ;
    	this.facility = facility ;
    	this.corpType = corpType ;
    	this.unit = unit ;
    	this.componentNo = componentNo ;
    	this.area = area ;
    	this.utczNo = utczNo ;
    	this.systemType = systemType ;
    	this.manufacturerCode = manufacturerCode ;
    	this.equipType = equipType ;
    	this.modelNo = modelNo ;
    	this.serialNo = serialNo ;
    }

    /** default constructor */
    public SrrWorkorderView() {
    }

    public String getWorkorderNo() {
		return (java.lang.String)HiltonUtility.ckNull(workorderNo) ;
	}

	public void setWorkorderNo(String workorderNo) {
		this.workorderNo = workorderNo;
	}

	public String getTaskNo() {
		return (java.lang.String)HiltonUtility.ckNull(taskNo) ;
	}

	public void setTaskNo(String taskNo) {
		this.taskNo = taskNo;
	}

	public String getWorkorderClosed() {
		return (java.lang.String)HiltonUtility.ckNull(workorderClosed) ;
	}

	public void setWorkorderClosed(String workorderClosed) {
		this.workorderClosed = workorderClosed;
	}

	public String getIdpNo() {
		return (java.lang.String)HiltonUtility.ckNull(idpNo) ;
	}

	public void setIdpNo(String idpNo) {
		this.idpNo = idpNo;
	}

	public String getWaCode() {
		return (java.lang.String)HiltonUtility.ckNull(waCode) ;
	}

	public void setWaCode(String waCode) {
		this.waCode = waCode;
	}

	public String getEquipNo() {
		return (java.lang.String)HiltonUtility.ckNull(equipNo) ;
	}

	public void setEquipNo(String equipNo) {
		this.equipNo = equipNo;
	}

	public String getFacility() {
		return (java.lang.String)HiltonUtility.ckNull(facility) ;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getCorpType() {
		return (java.lang.String)HiltonUtility.ckNull(corpType) ;
	}

	public void setCorpType(String corpType) {
		this.corpType = corpType;
	}

	public String getUnit() {
		return (java.lang.String)HiltonUtility.ckNull(unit) ;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getComponentNo() {
		return (java.lang.String)HiltonUtility.ckNull(componentNo) ;
	}

	public void setComponentNo(String componentNo) {
		this.componentNo = componentNo;
	}

	public String getArea() {
		return (java.lang.String)HiltonUtility.ckNull(area) ;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUtczNo() {
		return (java.lang.String)HiltonUtility.ckNull(utczNo) ;
	}

	public void setUtczNo(String utczNo) {
		this.utczNo = utczNo;
	}


	public String getSystemType() {
		return (java.lang.String)HiltonUtility.ckNull(systemType) ;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getManufacturerCode() {
		return (java.lang.String)HiltonUtility.ckNull(manufacturerCode) ;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public String getEquipType() {
		return (java.lang.String)HiltonUtility.ckNull(equipType) ;
	}

	public void setEquipType(String equipType) {
		this.equipType = equipType;
	}

	public String getModelNo() {
		return (java.lang.String)HiltonUtility.ckNull(modelNo) ;
	}

	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	public String getSerialNo() {
		return (java.lang.String)HiltonUtility.ckNull(serialNo) ;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof SrrWorkorderView) ) return false;
        SrrWorkorderView castOther = (SrrWorkorderView) other;
        return new EqualsBuilder()
            .isEquals();
    }

}
