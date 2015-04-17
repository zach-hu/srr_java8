package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;

/** @author Thelly */
public class PoolApprView implements Serializable {

	/** identifier field */
    private String userId;

    /** persistent field */
    private String entity;
    
    /** persistent field */
    private String dept;
  
    /** persistent field */
    private java.math.BigDecimal amount;
    
    /** persistent field */
    private String name;
   
    /** persistent field */
    private String indPool;
    
    /** persistent field */
    private String cap;
    
    /** persistent field */
    private String fyi;
    
    /** persistent field */
    private String reqApp;
    
    /** persistent field */
    private String callForward;
    
    /** persistent field */
    private String routTo;
    
    /** persistent field */
    private String description;
    
    /** full constructor */
    public PoolApprView(java.lang.String userId, java.lang.String entity, java.lang.String dept, java.math.BigDecimal amount, java.lang.String name, java.lang.String indPool, java.lang.String cap, java.lang.String fyi, java.lang.String reqApp, java.lang.String callForward, java.lang.String routTo, java.lang.String description) {
        this.userId = userId;
        this.entity = entity;
        this.dept = dept;
        this.amount = amount;
        this.name = name;
        this.indPool = indPool;
        this.cap = cap;
        this.callForward = callForward;
        this.fyi = fyi;
        this.reqApp = reqApp;
        this.routTo = routTo;
        this.description = description;
    }

    /** default constructor */
    public PoolApprView() {
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
		if (!HiltonUtility.isEmpty(userId) && userId.length() > 20) {
			userId = userId.substring(0, 20);
		}
		this.userId = userId;
    }
    
    public java.lang.String getEntity() {
		return (java.lang.String)HiltonUtility.ckNull(this.entity).trim();
    }

    public void setEntity(java.lang.String entity) {
		this.entity = entity;
    }
    
    public java.lang.String getDept() {
		return (java.lang.String)HiltonUtility.ckNull(this.dept).trim();
    }

    public void setDept(java.lang.String dept) {
		this.dept = dept;
    }
    
    public java.math.BigDecimal getAmount() {
    	return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount);
    }

    public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
    }
    
    public java.lang.String getName() {
		return (java.lang.String)HiltonUtility.ckNull(this.name).trim();
    }

    public void setName(java.lang.String name) {
		this.name = name;
    }
    
    public java.lang.String getIndPool() {
		return (java.lang.String)HiltonUtility.ckNull(this.indPool).trim();
    }

    public void setIndPool(java.lang.String indPool) {
		this.indPool = indPool;
    }
    
    public java.lang.String getCap() {
		return (java.lang.String)HiltonUtility.ckNull(this.cap).trim();
    }

    public void setCap(java.lang.String cap) {
		this.cap = cap;
    }
    
    public java.lang.String getFyi() {
		return (java.lang.String)HiltonUtility.ckNull(this.fyi).trim();
    }

    public void setFyi(java.lang.String fyi) {
		this.fyi = fyi;
    }
    
    public java.lang.String getReqApp() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqApp).trim();
    }

    public void setReqApp(java.lang.String reqApp) {
		this.reqApp = reqApp;
    }
    
    public java.lang.String getCallForward() {
		return (java.lang.String)HiltonUtility.ckNull(this.callForward).trim();
    }

    public void setCallForward(java.lang.String callForward) {
		this.callForward = callForward;
    }
    
    public java.lang.String getRoutTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.routTo).trim();
    }

    public void setRoutTo(java.lang.String routTo) {
		this.routTo = routTo;
    }
    
    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		this.description = description;
    }
}