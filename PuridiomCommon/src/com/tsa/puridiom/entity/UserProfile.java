package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.Entity;
import com.tsagate.properties.DictionaryManager;

import java.io.Serializable;
import java.util.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class UserProfile extends Entity implements Serializable {

    /** identifier field */
    private String userId;

    /** nullable persistent field */
    private String mailId;

	/** nullable persistent field */
	private String organizationId;

	 /** nullable persistent field */
    private String firstName;

    /** nullable persistent field */
    private String middleInit;

    /** nullable persistent field */
    private String lastName;

    /** nullable persistent field */
    private String title;

    /** nullable persistent field */
    private String phoneNumber;

    /** nullable persistent field */
    private String faxNumber;

    /** nullable persistent field */
    private String phoneFormat;

    /** nullable persistent field */
    private String faxFormat;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String owner;

    /** nullable persistent field */
    private java.util.Date dateEntered;

    /** nullable persistent field */
    private java.util.Date dateExpires;

    /** nullable persistent field */
    private String signature;

    /** nullable persistent field */
    private String signaturePassword;

    /** nullable persistent field */
    private String functionFlags;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String userPassword;

    /** nullable persistent field */
    private String adminBuyer;

    /** nullable persistent field */
    private String buyer;

    /** nullable persistent field */
    private String requisitioner;

    /** nullable persistent field */
    private String authorizedBy;

    /** nullable persistent field */
    private String receiver;

    /** nullable persistent field */
    private String administeredBy;

    /** nullable persistent field */
    private String approver;

    /** nullable persistent field */
    private String nameUdf1;

    /** nullable persistent field */
    private String nameUdf2;

    /** nullable persistent field */
    private String nameUdf3;

    /** nullable persistent field */
    private String nameUdf4;

    /** nullable persistent field */
    private String nameUdf5;

    private String nameUdf6;

    /** nullable persistent field */
    private java.math.BigDecimal approvalAmount;

    /** nullable persistent field */
    private String approveByLine;

    /** nullable persistent field */
    private String mailPassword;

    /** nullable persistent field */
    private String callForward;

    /** nullable persistent field */
    private java.math.BigDecimal excludeLess;

    /** nullable persistent field */
    private java.math.BigDecimal warrantAmount;

    /** nullable persistent field */
    private String mailStop;

    /** nullable persistent field */
    private String remoteUser;

    /** nullable persistent field */
    private String passChanged;

    /** nullable persistent field */
    private String lockLogin;

    /** nullable persistent field */
    private String empid;

    /** nullable persistent field */
    private String viewReqs;

    /** nullable persistent field */
    private String vchApp;

    /** nullable persistent field */
    private String reqClass;

    /** nullable persistent field */
    private String userType;

    /** nullable persistent field */
    private String locale;

    /** nullable persistent field */
    private String apBatchid;

    /** nullable persistent field */
    private String appointedFlag;

    /** nullable persistent field */
    private String formValidate;

    /** nullable persistent field */
    private String shipToCode;

    /** nullable persistent field */
    private String routing;

    /** nullable persistent field */
    private String overrider;

    /** nullable persistent field */
    private String securityQuestion;

    /** nullable persistent field */
    private String securityAnswer;

    /** nullable persistent field */
    private String costCenter;

    /** nullable persistent field */
    private String reviewProfile;

    /** nullable persistent field */
    private java.util.Date forwardOffDate;

    /** nullable persistent field */
    private java.math.BigDecimal contractAmount;

    /** nullable persistent field */
    private String priorPassword;

    /** nullable persistent field */
    private String backupApprover;

    /** nullable persistent field */
    private boolean tempPassword;

    /** nullable persistent field */
    private String emailVersion;

    /** nullable persistent field */
    private String externalId;

    /** nullable persistent field */
    private String language;

    /** nullable persistent field */
    private String currencyCode;

    /** nullable persistent field */
    private String timeZone;

    /** nullable persistent field */
    private String dateFormat;

    /** nullable persistent field */
    private String lastChangeBy;

    /** nullable persistent field */
    private java.util.Date lastChangeDate;

    /** nullable persistent field */
    private java.util.Date lastLoggedOn;

    /** nullable persistent field */
    private String barChart;

    private String emailsActive;

    private String termsAccepted;

    private String dateFormatKey;

    private java.util.Date loggedOn;

    private boolean registered = false;
    private int loginAttempts = 0;
    private List userRoles;
    private final Address shipToAddress = null;
    private String expandBrowse ;

    private String pCardType;
    private String pCardHolder;
    private String pCardNumber;
    private String pCardExpires;

    // MSR fields
    private String inspector ;
    private String engineer ;
    private String fpe ;
    private String marker ;

    /** nullable persistent field */
    private java.util.Date lockedOutTime;

    /** nullable persistent field */
    private java.math.BigDecimal lockOutCounter;
    
    
    private String targetCenter;

	
	/** full constructor */
    public UserProfile( final java.lang.String userId, final java.lang.String mailId, final java.lang.String organizationId, final java.lang.String firstName, final java.lang.String middleInit, final java.lang.String lastName, final java.lang.String title, final java.lang.String phoneNumber, final java.lang.String faxNumber, final java.lang.String phoneFormat, final java.lang.String faxFormat, final java.lang.String status, final java.lang.String owner, final java.util.Date dateEntered, final java.util.Date dateExpires, final java.lang.String signature, final java.lang.String signaturePassword, final java.lang.String functionFlags, final java.lang.String department, final java.lang.String userPassword, final java.lang.String adminBuyer, final java.lang.String buyer, final java.lang.String requisitioner, final java.lang.String authorizedBy, final java.lang.String receiver, final java.lang.String administeredBy, final java.lang.String approver, final java.lang.String nameUdf1, final java.lang.String nameUdf2, final java.lang.String nameUdf3, final java.lang.String nameUdf4, final java.lang.String nameUdf5, final java.math.BigDecimal approvalAmount, final java.lang.String approveByLine, final java.lang.String mailPassword, final java.lang.String callForward, final java.math.BigDecimal excludeLess, final java.math.BigDecimal warrantAmount, final java.lang.String mailStop, final java.lang.String remoteUser, final java.lang.String passChanged, final java.lang.String lockLogin, final java.lang.String empid, final java.lang.String viewReqs, final java.lang.String vchApp, final java.lang.String reqClass, final java.lang.String userType, final java.lang.String locale, final java.lang.String apBatchid, final java.lang.String appointedFlag, final java.lang.String formValidate, final java.lang.String shipToCode, final java.lang.String routing, final String overrider, final String securityQuestion, final String securityAnswer, final String costCenter, final String reviewProfile, final java.util.Date forwardOffDate, final java.math.BigDecimal contractAmount, final String priorPassword, final String backupApprover, final String emailVersion, final String externalId, final String expandBrowse, final String language, final String currencyCode, final String timeZone, final String dateFormat, final String lastChangeBy, final java.util.Date lastChangeDate, final java.util.Date lastLoggedOn, final String barChart, final String emailsActive, final String termsAccepted, final java.util.Date loggedOn, final String dateFormatKey, final String inspector, final String engineer, final String fpe, final String marker, final java.util.Date lockedOutTime, final java.math.BigDecimal lockOutCounter, 
    		String targetCenter) {
    	this.userId = userId;
        this.mailId = mailId;
    	this.organizationId = organizationId;
        this.firstName = firstName;
        this.middleInit = middleInit;
        this.lastName = lastName;
        this.title = title;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.phoneFormat = phoneFormat;
        this.faxFormat = faxFormat;
        this.status = status;
        this.owner = owner;
        this.dateEntered = dateEntered;
        this.dateExpires = dateExpires;
        this.signature = signature;
        this.signaturePassword = signaturePassword;
        this.functionFlags = functionFlags;
        this.department = department;
        this.userPassword = userPassword;
        this.adminBuyer = adminBuyer;
        this.buyer = buyer;
        this.requisitioner = requisitioner;
        this.authorizedBy = authorizedBy;
        this.receiver = receiver;
        this.administeredBy = administeredBy;
        this.approver = approver;
        this.nameUdf1 = nameUdf1;
        this.nameUdf2 = nameUdf2;
        this.nameUdf3 = nameUdf3;
        this.nameUdf4 = nameUdf4;
        this.nameUdf5 = nameUdf5;
        this.approvalAmount = approvalAmount;
        this.approveByLine = approveByLine;
        this.mailPassword = mailPassword;
        this.callForward = callForward;
        this.excludeLess = excludeLess;
        this.warrantAmount = warrantAmount;
        this.mailStop = mailStop;
        this.remoteUser = remoteUser;
        this.passChanged = passChanged;
        this.lockLogin = lockLogin;
        this.empid = empid;
        this.viewReqs = viewReqs;
        this.vchApp = vchApp;
        this.reqClass = reqClass;
        this.userType = userType;
        this.locale = locale;
        this.apBatchid = apBatchid;
        this.appointedFlag = appointedFlag;
        this.formValidate = formValidate;
        this.shipToCode = shipToCode;
        this.routing = routing;
        this.overrider = overrider;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
        this.costCenter = costCenter;
        this.reviewProfile = reviewProfile;
        this.forwardOffDate = forwardOffDate;
        this.priorPassword = priorPassword;
        this.contractAmount = contractAmount;
        this.backupApprover = backupApprover;
        this.emailVersion = emailVersion ;
        this.externalId = externalId ;
        this.expandBrowse = expandBrowse ;
        this.language = language;
        this.currencyCode = currencyCode;
        this.timeZone = timeZone;
        this.dateFormat = dateFormat;
        this.lastChangeBy = lastChangeBy;
        this.lastChangeDate = lastChangeDate;
        this.lastLoggedOn = lastLoggedOn;
        this.barChart = barChart;
        this.emailsActive = emailsActive;
        this.termsAccepted = termsAccepted;
        this.loggedOn = loggedOn;
        this.dateFormatKey = dateFormatKey;
        this.inspector = inspector ;
        this.engineer = engineer ;
        this.fpe = fpe ;
        this.marker = marker ;
        this.lockedOutTime = lockedOutTime;
        this.lockOutCounter = lockOutCounter;
        this.targetCenter = targetCenter;
    }

    /** default constructor */
    public UserProfile() {
    }

    /** minimal constructor */
    public UserProfile(final java.lang.String userId) {
        this.userId = userId;
    }

    public java.lang.String getUserId() {
		return (java.lang.String)HiltonUtility.ckNull(this.userId).trim();
    }

    public void setUserId(java.lang.String userId) {
        if (!HiltonUtility.isEmpty(userId)) {
            userId = userId.toUpperCase();
        }
        this.userId = userId;
    }

    public java.lang.String getMailId() {
		return this.mailId;
    }

    public void setMailId(java.lang.String mailId) {
        if (!HiltonUtility.isEmpty(mailId)) {
            mailId = mailId.toLowerCase();
        }
    	this.mailId = mailId;
    }

    public java.lang.String getOrganizationId() {
		return (java.lang.String)HiltonUtility.ckNull(this.organizationId).trim();
    }

    public void setOrganizationId(java.lang.String organizationId) {
        if (!HiltonUtility.isEmpty(organizationId)) {
            organizationId = organizationId.toUpperCase();
        }
    	this.organizationId = organizationId;
    }

    public java.lang.String getFirstName() {
		return (java.lang.String)HiltonUtility.ckNull(this.firstName).trim();
    }

    public void setFirstName(java.lang.String firstName) {
		if (!HiltonUtility.isEmpty(firstName) && firstName.length() > 20) {
			firstName = firstName.substring(0, 20);
		}
		this.firstName = firstName;
    }

    public java.lang.String getMiddleInit() {
		return (java.lang.String)HiltonUtility.ckNull(this.middleInit).trim();
    }

    public void setMiddleInit(java.lang.String middleInit) {
		if (!HiltonUtility.isEmpty(middleInit) && middleInit.length() > 1) {
			middleInit = middleInit.substring(0, 1);
		}
		this.middleInit = middleInit;
    }

    public java.lang.String getLastName() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastName).trim();
    }

    public void setLastName(java.lang.String lastName) {
		if (!HiltonUtility.isEmpty(lastName) && lastName.length() > 20) {
			lastName = lastName.substring(0, 20);
		}
		this.lastName = lastName;
    }

    public java.lang.String getTitle() {
		return (java.lang.String)HiltonUtility.ckNull(this.title).trim();
    }

    public void setTitle(java.lang.String title) {
		if (!HiltonUtility.isEmpty(title) && title.length() > 40) {
			title = title.substring(0, 40);
		}
		this.title = title;
    }

    public java.lang.String getPhoneNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.phoneNumber).trim();
    }

    public void setPhoneNumber(java.lang.String phoneNumber) {
		if (!HiltonUtility.isEmpty(phoneNumber) && phoneNumber.length() > 40) {
			phoneNumber = phoneNumber.substring(0, 40);
		}
		this.phoneNumber = phoneNumber;
    }

    public java.lang.String getFaxNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.faxNumber).trim();
    }

    public void setFaxNumber(java.lang.String faxNumber) {
		if (!HiltonUtility.isEmpty(faxNumber) && faxNumber.length() > 40) {
			faxNumber = faxNumber.substring(0, 40);
		}
		this.faxNumber = faxNumber;
    }

    public java.lang.String getPhoneFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.phoneFormat).trim();
    }

    public void setPhoneFormat(java.lang.String phoneFormat) {
		if (!HiltonUtility.isEmpty(phoneFormat) && phoneFormat.length() > 2) {
			phoneFormat = phoneFormat.substring(0, 2);
		}
		this.phoneFormat = phoneFormat;
    }

    public java.lang.String getFaxFormat() {
		return (java.lang.String)HiltonUtility.ckNull(this.faxFormat).trim();
    }

    public void setFaxFormat(java.lang.String faxFormat) {
		if (!HiltonUtility.isEmpty(faxFormat) && faxFormat.length() > 2) {
			faxFormat = faxFormat.substring(0, 2);
		}
		this.faxFormat = faxFormat;
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

    public java.lang.String getOwner() {
		return (java.lang.String)HiltonUtility.ckNull(this.owner).trim();
    }

    public void setOwner(java.lang.String owner) {
        if (!HiltonUtility.isEmpty(owner)) {
            owner = owner.toUpperCase();
        }
        this.owner = owner;
    }

    public java.util.Date getDateEntered() {
		return this.dateEntered;
    }

    public void setDateEntered(final java.util.Date dateEntered) {
        this.dateEntered = dateEntered;
    }

    public java.util.Date getSqlDateExpires() {
		return this.dateExpires;
    }

    public java.util.Date getDateExpires() {
		return this.dateExpires;
    }

    public void setDateExpires(final java.util.Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public java.lang.String getSignature() {
		return (java.lang.String)HiltonUtility.ckNull(this.signature).trim();
    }

    public void setSignature(java.lang.String signature) {
		if (!HiltonUtility.isEmpty(signature) && signature.length() > 50) {
			signature = signature.substring(0, 50);
		}
		this.signature = signature;
    }

    public java.lang.String getSignaturePassword() {
		return (java.lang.String)HiltonUtility.ckNull(this.signaturePassword).trim();
    }

    public void setSignaturePassword(java.lang.String signaturePassword) {
		if (!HiltonUtility.isEmpty(signaturePassword) && signaturePassword.length() > 12) {
			signaturePassword = signaturePassword.substring(0, 12);
		}
		this.signaturePassword = signaturePassword;
    }

    public java.lang.String getFunctionFlags() {
		return (java.lang.String)HiltonUtility.ckNull(this.functionFlags).trim();
    }

    public void setFunctionFlags(java.lang.String functionFlags) {
		if (!HiltonUtility.isEmpty(functionFlags) && functionFlags.length() > 20) {
			functionFlags = functionFlags.substring(0, 20);
		}
		this.functionFlags = functionFlags;
    }

    public java.lang.String getDepartment() {
		return (java.lang.String)HiltonUtility.ckNull(this.department).trim();
    }

    public void setDepartment(java.lang.String department) {
		if (!HiltonUtility.isEmpty(department) && department.length() > 15) {
			department = department.substring(0, 15);
		}
		this.department = department;
    }

    public java.lang.String getUserPassword() {
		return this.userPassword;
    }

    public void setUserPassword(java.lang.String userPassword) {
//		if (!HiltonUtility.isEmpty(userPassword) && userPassword.length() > 12) {
//			userPassword = userPassword.substring(0, 12);
//		}
		this.userPassword = userPassword;
    }

    public java.lang.String getAdminBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.adminBuyer).trim();
    }

    public void setAdminBuyer(java.lang.String adminBuyer) {
		if (!HiltonUtility.isEmpty(adminBuyer) && adminBuyer.length() > 1) {
			adminBuyer = adminBuyer.substring(0, 1);
		}
		this.adminBuyer = adminBuyer;
    }

    public java.lang.String getBuyer() {
		return (java.lang.String)HiltonUtility.ckNull(this.buyer).trim();
    }

    public void setBuyer(java.lang.String buyer) {
		if (!HiltonUtility.isEmpty(buyer) && buyer.length() > 1) {
			buyer = buyer.substring(0, 1);
		}
		this.buyer = buyer;
    }

    public java.lang.String getRequisitioner() {
		return (java.lang.String)HiltonUtility.ckNull(this.requisitioner).trim();
    }

    public void setRequisitioner(java.lang.String requisitioner) {
		if (!HiltonUtility.isEmpty(requisitioner) && requisitioner.length() > 1) {
			requisitioner = requisitioner.substring(0, 1);
		}
		this.requisitioner = requisitioner;
    }

    public java.lang.String getAuthorizedBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.authorizedBy).trim();
    }

    public void setAuthorizedBy(java.lang.String authorizedBy) {
		if (!HiltonUtility.isEmpty(authorizedBy) && authorizedBy.length() > 1) {
			authorizedBy = authorizedBy.substring(0, 1);
		}
		this.authorizedBy = authorizedBy;
    }

    public java.lang.String getReceiver() {
		return (java.lang.String)HiltonUtility.ckNull(this.receiver).trim();
    }

    public void setReceiver(java.lang.String receiver) {
		if (!HiltonUtility.isEmpty(receiver) && receiver.length() > 1) {
			receiver = receiver.substring(0, 1);
		}
		this.receiver = receiver;
    }

    public java.lang.String getAdministeredBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.administeredBy).trim();
    }

    public void setAdministeredBy(java.lang.String administeredBy) {
		if (!HiltonUtility.isEmpty(administeredBy) && administeredBy.length() > 1) {
			administeredBy = administeredBy.substring(0, 1);
		}
		this.administeredBy = administeredBy;
    }

    public java.lang.String getApprover() {
		return (java.lang.String)HiltonUtility.ckNull(this.approver).trim();
    }

    public void setApprover(java.lang.String approver) {
		if (!HiltonUtility.isEmpty(approver) && approver.length() > 1) {
			approver = approver.substring(0, 1);
		}
		this.approver = approver;
    }

    public java.lang.String getNameUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf1).trim();
    }

    public void setNameUdf1(java.lang.String nameUdf1) {
		if (!HiltonUtility.isEmpty(nameUdf1) && nameUdf1.length() > 15) {
			nameUdf1 = nameUdf1.substring(0, 15);
		}
		this.nameUdf1 = nameUdf1;
    }

    public java.lang.String getNameUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf2).trim();
    }

    public void setNameUdf2(java.lang.String nameUdf2) {
		if (!HiltonUtility.isEmpty(nameUdf2) && nameUdf2.length() > 15) {
			nameUdf2 = nameUdf2.substring(0, 15);
		}
		this.nameUdf2 = nameUdf2;
    }

    public java.lang.String getNameUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf3).trim();
    }

    public void setNameUdf3(java.lang.String nameUdf3) {
		if (!HiltonUtility.isEmpty(nameUdf3) && nameUdf3.length() > 15) {
			nameUdf3 = nameUdf3.substring(0, 15);
		}
		this.nameUdf3 = nameUdf3;
    }

    public java.lang.String getNameUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf4).trim();
    }

    public void setNameUdf4(java.lang.String nameUdf4) {
		if (!HiltonUtility.isEmpty(nameUdf4) && nameUdf4.length() > 15) {
			nameUdf4 = nameUdf4.substring(0, 15);
		}
		this.nameUdf4 = nameUdf4;
    }

    public java.lang.String getNameUdf6() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf6).trim();
    }

    public void setNameUdf6(java.lang.String nameUdf6) {
		if (!HiltonUtility.isEmpty(nameUdf6) && nameUdf6.length() > 15) {
			nameUdf6 = nameUdf6.substring(0, 15);
		}
		this.nameUdf6 = nameUdf6;
    }

    public java.lang.String getNameUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.nameUdf5).trim();
    }

    public void setNameUdf5(java.lang.String nameUdf5) {
		if (!HiltonUtility.isEmpty(nameUdf5) && nameUdf5.length() > 15) {
			nameUdf5 = nameUdf5.substring(0, 15);
		}
		this.nameUdf5 = nameUdf5;
    }

    public java.math.BigDecimal getApprovalAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.approvalAmount);
    }

    public void setApprovalAmount(final java.math.BigDecimal approvalAmount) {
        this.approvalAmount = approvalAmount;
    }

    public java.lang.String getApproveByLine() {
		return (java.lang.String)HiltonUtility.ckNull(this.approveByLine).trim();
    }

    public void setApproveByLine(java.lang.String approveByLine) {
		if (!HiltonUtility.isEmpty(approveByLine) && approveByLine.length() > 1) {
			approveByLine = approveByLine.substring(0, 1);
		}
		this.approveByLine = approveByLine;
    }

    public java.lang.String getMailPassword() {
		return (java.lang.String)HiltonUtility.ckNull(this.mailPassword).trim();
    }

    public void setMailPassword(java.lang.String mailPassword) {
		if (!HiltonUtility.isEmpty(mailPassword) && mailPassword.length() > 50) {
			mailPassword = mailPassword.substring(0, 50);
		}
		this.mailPassword = mailPassword;
    }

    public java.lang.String getCallForward() {
		return (java.lang.String)HiltonUtility.ckNull(this.callForward).trim();
    }

    public void setCallForward(java.lang.String callForward) {
        if (!HiltonUtility.isEmpty(callForward)) {
            callForward = callForward.toUpperCase();
        }
        this.callForward = callForward;
    }

    public java.math.BigDecimal getExcludeLess() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.excludeLess);
    }

    public void setExcludeLess(final java.math.BigDecimal excludeLess) {
        this.excludeLess = excludeLess;
    }

    public java.math.BigDecimal getWarrantAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.warrantAmount);
    }

    public void setWarrantAmount(final java.math.BigDecimal warrantAmount) {
        this.warrantAmount = warrantAmount;
    }

    public java.math.BigDecimal getContractAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.contractAmount);
    }

    public void setContractAmount(final java.math.BigDecimal contractAmount) {
        this.contractAmount = contractAmount;
    }

    public java.lang.String getMailStop() {
		return (java.lang.String)HiltonUtility.ckNull(this.mailStop).trim();
    }

    public void setMailStop(java.lang.String mailStop) {
		if (!HiltonUtility.isEmpty(mailStop) && mailStop.length() > 15) {
			mailStop = mailStop.substring(0, 15);
		}
		this.mailStop = mailStop;
    }

    public java.lang.String getRemoteUser() {
		return (java.lang.String)HiltonUtility.ckNull(this.remoteUser).trim();
    }

    public void setRemoteUser(java.lang.String remoteUser) {
		if (!HiltonUtility.isEmpty(remoteUser) && remoteUser.length() > 1) {
			remoteUser = remoteUser.substring(0, 1);
		}
		this.remoteUser = remoteUser;
    }

    public java.lang.String getPassChanged() {
		return (java.lang.String)HiltonUtility.ckNull(this.passChanged).trim();
    }

    public void setPassChanged(java.lang.String passChanged) {
		if (!HiltonUtility.isEmpty(passChanged) && passChanged.length() > 10) {
			passChanged = passChanged.substring(0, 10);
		}
		this.passChanged = passChanged;
    }

    public java.lang.String getLockLogin() {
		return (java.lang.String)HiltonUtility.ckNull(this.lockLogin).trim();
    }

    public void setLockLogin(java.lang.String lockLogin) {
		if (!HiltonUtility.isEmpty(lockLogin) && lockLogin.length() > 1) {
			lockLogin = lockLogin.substring(0, 1);
		}
		this.lockLogin = lockLogin;
    }

    public java.lang.String getEmpid() {
		return (java.lang.String)HiltonUtility.ckNull(this.empid).trim();
    }

    public void setEmpid(java.lang.String empid) {
		if (!HiltonUtility.isEmpty(empid) && empid.length() > 15) {
			empid = empid.substring(0, 15);
		}
		this.empid = empid;
    }

    public java.lang.String getViewReqs() {
		return (java.lang.String)HiltonUtility.ckNull(this.viewReqs).trim();
    }

    public void setViewReqs(java.lang.String viewReqs) {
		if (!HiltonUtility.isEmpty(viewReqs) && viewReqs.length() > 1) {
			viewReqs = viewReqs.substring(0, 1);
		}
		this.viewReqs = viewReqs;
    }

    public java.lang.String getVchApp() {
		return (java.lang.String)HiltonUtility.ckNull(this.vchApp).trim();
    }

    public void setVchApp(java.lang.String vchApp) {
		if (!HiltonUtility.isEmpty(vchApp) && vchApp.length() > 1) {
			vchApp = vchApp.substring(0, 1);
		}
		this.vchApp = vchApp;
    }

    public java.lang.String getReqClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.reqClass).trim();
    }

    public void setReqClass(java.lang.String reqClass) {
		if (!HiltonUtility.isEmpty(reqClass) && reqClass.length() > 30) {
			reqClass = reqClass.substring(0, 30);
		}
		this.reqClass = reqClass;
    }

    public java.lang.String getUserType() {
		return (java.lang.String)HiltonUtility.ckNull(this.userType).trim();
    }

    public void setUserType(java.lang.String userType) {
		if (!HiltonUtility.isEmpty(userType) && userType.length() > 30) {
			userType = userType.substring(0, 30);
		}
		this.userType = userType;
    }

    public java.lang.String getLocale() {
		return (java.lang.String)HiltonUtility.ckNull(this.locale).trim();
    }

    public void setLocale(java.lang.String locale) {
		if (!HiltonUtility.isEmpty(locale) && locale.length() > 15) {
			locale = locale.substring(0, 15);
		}
		this.locale = locale;
    }

    public java.lang.String getApBatchid() {
		return (java.lang.String)HiltonUtility.ckNull(this.apBatchid).trim();
    }

    public void setApBatchid(java.lang.String apBatchid) {
		if (!HiltonUtility.isEmpty(apBatchid) && apBatchid.length() > 6) {
			apBatchid = apBatchid.substring(0, 6);
		}
		this.apBatchid = apBatchid;
    }

    public java.lang.String getAppointedFlag() {
		return (java.lang.String)HiltonUtility.ckNull(this.appointedFlag).trim();
    }

    public void setAppointedFlag(java.lang.String appointedFlag) {
		if (!HiltonUtility.isEmpty(appointedFlag) && appointedFlag.length() > 1) {
			appointedFlag = appointedFlag.substring(0, 1);
		}
		this.appointedFlag = appointedFlag;
    }

    public java.lang.String getFormValidate() {
		return (java.lang.String)HiltonUtility.ckNull(this.formValidate).trim();
    }

    public void setFormValidate(java.lang.String formValidate) {
		if (!HiltonUtility.isEmpty(formValidate) && formValidate.length() > 1) {
			formValidate = formValidate.substring(0, 1);
		}
		this.formValidate = formValidate;
    }

    public java.lang.String getShipToCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.shipToCode).trim();
    }

    public void setShipToCode(java.lang.String shipToCode) {
		if (!HiltonUtility.isEmpty(shipToCode) && shipToCode.length() > 15) {
			shipToCode = shipToCode.substring(0, 15);
		}
		this.shipToCode = shipToCode;
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

    public java.lang.String getOverrider() {
		return (java.lang.String)HiltonUtility.ckNull(this.overrider).trim();
    }

    public void setOverrider(java.lang.String overrider) {
		if (!HiltonUtility.isEmpty(overrider) && overrider.length() > 1) {
			overrider = overrider.substring(0, 1);
		}
		this.overrider = overrider;
    }

    public java.lang.String getSecurityQuestion() {
		return (java.lang.String)HiltonUtility.ckNull(this.securityQuestion).trim();
    }

    public void setSecurityQuestion(java.lang.String securityQuestion) {
		if (!HiltonUtility.isEmpty(securityQuestion) && securityQuestion.length() > 40) {
			securityQuestion = securityQuestion.substring(0, 40);
		}
		this.securityQuestion = securityQuestion;
    }

    public java.lang.String getSecurityAnswer() {
		return (java.lang.String)HiltonUtility.ckNull(this.securityAnswer).trim();
    }

    public void setSecurityAnswer(java.lang.String securityAnswer) {
		if (!HiltonUtility.isEmpty(securityAnswer) && securityAnswer.length() > 40) {
			securityAnswer = securityAnswer.substring(0, 40);
		}
		this.securityAnswer = securityAnswer;
    }

    public java.lang.String getCostCenter() {
		return (java.lang.String)HiltonUtility.ckNull(this.costCenter).trim();
    }

    public void setCostCenter(java.lang.String costCenter) {
		if (!HiltonUtility.isEmpty(costCenter) && costCenter.length() > 15) {
			costCenter = costCenter.substring(0, 15);
		}
		this.costCenter = costCenter;
    }

    public java.lang.String getReviewProfile() {
		return (java.lang.String)HiltonUtility.ckNull(this.reviewProfile).trim();
    }

    public void setReviewProfile(java.lang.String reviewProfile) {
		if (!HiltonUtility.isEmpty(reviewProfile) && reviewProfile.length() > 1) {
			reviewProfile = reviewProfile.substring(0, 1);
		}
		this.reviewProfile = reviewProfile;
    }

    public java.util.Date getForwardOffDate() {
		return this.forwardOffDate;
    }

    public void setForwardOffDate(final java.util.Date forwardOffDate) {
        this.forwardOffDate = forwardOffDate;
    }

    public java.lang.String getPriorPassword() {
		return (java.lang.String)HiltonUtility.ckNull(this.priorPassword).trim();
    }

    public void setPriorPassword(java.lang.String priorPassword) {
		if (!HiltonUtility.isEmpty(priorPassword) && priorPassword.length() > 255) {
		    priorPassword = priorPassword.substring(0, 255);
		}
		this.priorPassword = priorPassword;
    }

    public java.lang.String getBackupApprover() {
		return (java.lang.String)HiltonUtility.ckNull(this.backupApprover).trim();
    }

    public void setBackupApprover(java.lang.String backupApprover) {
        if (!HiltonUtility.isEmpty(backupApprover)) {
            backupApprover = backupApprover.toUpperCase();
        }
        this.backupApprover = backupApprover;
    }

    public java.lang.String getLanguage() {
        return (java.lang.String)HiltonUtility.ckNull(this.language).trim();
    }

    public void setLanguage(java.lang.String language) {
        if (!HiltonUtility.isEmpty(language) && language.length() > 15) {
            language = language.substring(0, 15);
        }
        this.language = language;
    }

    public java.lang.String getCurrencyCode() {
        return (java.lang.String)HiltonUtility.ckNull(this.currencyCode).trim();
    }

    public void setCurrencyCode(java.lang.String currencyCode) {
        if (!HiltonUtility.isEmpty(currencyCode) && currencyCode.length() > 15) {
            currencyCode = currencyCode.substring(0, 15);
        }
        this.currencyCode = currencyCode;
    }

    public java.lang.String getTimeZone() {
        return (java.lang.String)HiltonUtility.ckNull(this.timeZone).trim();
    }

    public void setTimeZone(java.lang.String timeZone) {
        if (!HiltonUtility.isEmpty(timeZone) && timeZone.length() > 30) {
            timeZone = timeZone.substring(0, 30);
        }
        this.timeZone = timeZone;
    }

    public java.lang.String getDateFormat() {
        return (java.lang.String)HiltonUtility.ckNull(this.dateFormat).trim();
    }

    public void setDateFormat(java.lang.String dateFormat) {
        if (!HiltonUtility.isEmpty(dateFormat) && dateFormat.length() > 10) {
            dateFormat = dateFormat.substring(0, 10);
        }
        this.dateFormat = dateFormat;
    }

    public boolean isAnAdministeredBy() {
    	boolean badministeredBy = false;

    	if (this.getAdministeredBy() != null && this.getAdministeredBy().equalsIgnoreCase("Y")) {
    	    badministeredBy = true;
    	}
    	return badministeredBy;
    }

    public boolean isAnAuthorizedBy() {
    	boolean bauthorizedBy = false;

    	if (this.getAuthorizedBy() != null && this.getAuthorizedBy().equalsIgnoreCase("Y")) {
    		bauthorizedBy = true;
    	}
    	return bauthorizedBy;
    }

    public boolean isAppointed() {
    	boolean bappointed = false;

    	if (this.getAppointedFlag() != null && this.getAppointedFlag().equalsIgnoreCase("Y")) {
    		bappointed = true;
    	}
    	return bappointed;
    }

    public boolean isAnApprover() {
    	boolean bapprover = false;

    	if (this.getApprover() != null && this.getApprover().equalsIgnoreCase("Y")) {
    		bapprover = true;
    	}
    	return bapprover;
    }

    public boolean isVchApprover() {
    	boolean bvchapprover = false;

    	if (this.getVchApp() != null && this.getVchApp().equalsIgnoreCase("Y")) {
    		bvchapprover = true;
    	}
    	return bvchapprover;
    }

    public boolean isAnAdminBuyer() {
    	boolean badminbuyer = false;

    	if (adminBuyer != null && adminBuyer.equalsIgnoreCase("Y")) {
    		badminbuyer = true;
    	}
    	return badminbuyer;
    }

    public boolean isABuyer() {
    	boolean bbuyer = false;

    	if (buyer != null && buyer.equalsIgnoreCase("Y")) {
    		bbuyer = true;
    	}
    	return bbuyer;
    }

    public boolean isAFormValidate() {
    	boolean bformValidate = false;

    	if (this.formValidate != null && this.formValidate.equalsIgnoreCase("Y")) {
    		bformValidate = true;
    	}
    	return bformValidate;
    }

    public boolean isAnOverrider() {
    	boolean boverrider = false;

    	if (overrider != null && overrider.equalsIgnoreCase("Y")) {
    		boverrider = true;
    	}
    	return boverrider;
    }

    public boolean isAReceiverValue() {
    	boolean breceiver = false;

    	if (receiver != null && receiver.equalsIgnoreCase("Y")) {
    		breceiver = true;
    	}
    	return breceiver;
    }

    public boolean isARequisitioner() {
    	boolean brequisitioner = false;

    	if (requisitioner != null && requisitioner.equalsIgnoreCase("Y")) {
    		brequisitioner = true;
    	}
    	return brequisitioner;
    }

    public void setRegistered(final boolean newBoolean) {
    	registered = newBoolean;
    }

    public boolean isAInspector() {
    	boolean bInspector = false;

    	if (inspector != null && inspector.equalsIgnoreCase("Y")) {
    		bInspector = true;
    	}
    	return bInspector;
    }

    public boolean isAEngineer() {
    	boolean bEngineer = false;

    	if (engineer != null && engineer.equalsIgnoreCase("Y")) {
    		bEngineer = true;
    	}
    	return bEngineer;
    }

    public boolean isAFpe() {
    	boolean bFpe = false;

    	if (fpe != null && fpe.equalsIgnoreCase("Y")) {
    		bFpe = true;
    	}
    	return bFpe;
    }

    public boolean isAMarker() {
    	boolean bMarker = false;

    	if (marker != null && marker.equalsIgnoreCase("Y")) {
    		bMarker = true;
    	}
    	return bMarker;
    }

    public boolean isRegistered() {
    	return registered;
    }

    public void setTempPassword(final boolean newBoolean) {
        tempPassword = newBoolean;
    }

    public boolean isTempPassword() {
    	return tempPassword;
    }

    public void setLoginAttempts(final int newInt) {
    	loginAttempts = newInt;
    }

    public int getLoginAttempts() {
    	return loginAttempts;
    }

    public void setUserRoles(final List newList) {
    	userRoles = newList;
    }

    public List getUserRoles() {
    	return userRoles;
    }

    public String getDisplayName() {
    	final StringBuffer name = new StringBuffer();
    	if (!HiltonUtility.isEmpty(this.firstName)) {
    		name.append(this.firstName.trim());
    	}
    	/*
    	if (!HiltonUtility.isEmpty(this.middleInit)) {
    		if (name.length() > 0) {
    			name.append(" " + this.middleInit.trim());
    		} else {
    			name.append(this.middleInit.trim());
    		}
    	}*/
    	if (!HiltonUtility.isEmpty(this.lastName)) {
    		if (name.length() > 0) {
    			name.append(" " + this.lastName.trim());
    		} else {
    			name.append(this.lastName.trim());
    		}
    	}
    	if (HiltonUtility.isEmpty(name.toString())) {
    		return this.getUserId();
    	}
    	return name.toString();
   	}

    public String getDisplayName(final String	reqType) {
    	String	uName = this.getDisplayName() ;

    	if (uName.equalsIgnoreCase("UNASSIGNED")) {
    		if (! HiltonUtility.isEmpty(reqType)) {
    			final String	iTypes = "RSTI";
	    		if (iTypes.indexOf(reqType) == -1) {
	    			uName = "Purchasing Department" ;
	    		} else {
	    			uName = DictionaryManager.getLabelsInstance(organizationId, language).getLabel(organizationId, "supplyCoordinator", "Supply Coordinator", reqType);
	    		}
    		} else {
    			uName = "Purchasing Department" ;
	    	}
    	}
    	return uName ;
    }

    public Address getShipToAddress() {
        if (this.shipToAddress == null) {
            return new Address();
        }
        return this.shipToAddress;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("userId", getUserId())
            .toString();
    }

    public boolean equals(final Object other) {
        if ( !(other instanceof UserProfile) ) return false;
        final UserProfile castOther = (UserProfile) other;
        return new EqualsBuilder()
            .append(this.getUserId(), castOther.getUserId())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getUserId())
            .toHashCode();
    }

	/**
	 * @return the emailVersion
	 */
	public String getEmailVersion()
	{
		return (String) HiltonUtility.ckNull(this.emailVersion).trim();
	}

	/**
	 * @param emailVersion the emailVersion to set
	 */
	public void setEmailVersion(String emailVersion)
	{
		if (!HiltonUtility.isEmpty(emailVersion) && emailVersion.length() > 1)
		{
			emailVersion = emailVersion.substring(0, 1);
		}

		this.emailVersion = emailVersion;
	}

	/**
	 * @return the externalId
	 */
	public String getExternalId() {
		return externalId;
	}

	/**
	 * @param externalId the externalId to set
	 */
	public void setExternalId(final String externalId) {
		this.externalId = externalId;
	}

	public String getExpandBrowse() {
		return HiltonUtility.ckNull(expandBrowse);
	}

	public void setExpandBrowse(final String expandBrowse) {
		this.expandBrowse = expandBrowse;
	}

	public java.lang.String getLastChangeBy() {
		return (java.lang.String)HiltonUtility.ckNull(this.lastChangeBy).trim();
    }

    public void setLastChangeBy(java.lang.String lastChangeBy) {
		if (!HiltonUtility.isEmpty(lastChangeBy) && lastChangeBy.length() > 20) {
			lastChangeBy = lastChangeBy.substring(0, 20);
		}
		this.lastChangeBy = lastChangeBy;
    }

    public java.util.Date getLastChangeDate() {
        return this.lastChangeDate;
    }

    public void setLastChangeDate(java.util.Date lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public java.util.Date getLastLoggedOn() {
        return this.lastLoggedOn;
    }

    public void setLastLoggedOn(java.util.Date lastLoggedOn) {
        this.lastLoggedOn = lastLoggedOn;
    }

    public java.lang.String getEmailsActive() {
		return (java.lang.String)HiltonUtility.ckNull(this.emailsActive).trim();
    }

    public void setEmailsActive(java.lang.String emailsActive) {
		if (!HiltonUtility.isEmpty(emailsActive) && emailsActive.length() > 1) {
			emailsActive = emailsActive.substring(0, 1);
		}
		this.emailsActive = emailsActive;
    }

    public java.lang.String getBarChart() {
		return (java.lang.String)HiltonUtility.ckNull(this.barChart).trim();
    }

    public void setBarChart(java.lang.String barChart) {
		if (!HiltonUtility.isEmpty(barChart) && barChart.length() > 10) {
			barChart = barChart.substring(0, 10);
		}
		this.barChart = barChart;
    }

    public java.lang.String getTermsAccepted() {
		return (java.lang.String)HiltonUtility.ckNull(this.termsAccepted).trim();
    }

    public void setTermsAccepted(java.lang.String termsAccepted) {
		if (!HiltonUtility.isEmpty(barChart) && !HiltonUtility.isEmpty(termsAccepted) && termsAccepted.length() > 10) {
			termsAccepted = termsAccepted.substring(0, 10);
		}
		this.termsAccepted = termsAccepted;
    }

    public java.util.Date getLoggedOn() {
        return this.loggedOn;
    }

    public void setLoggedOn(java.util.Date loggedOn) {
        this.loggedOn = loggedOn;
    }

    public java.lang.String getDateFormatKey() {
        return (java.lang.String)HiltonUtility.ckNull(this.dateFormatKey).trim();
    }

    public void setDateFormatKey(java.lang.String dateFormatKey) {
        if (!HiltonUtility.isEmpty(dateFormatKey) && dateFormatKey.length() > 10) {
        	dateFormatKey = dateFormatKey.substring(0, 10);
        }
        this.dateFormatKey = dateFormatKey;
    }

    public String getpCardType() {
		return pCardType;
	}

	public void setpCardType(String pCardType) {
		this.pCardType = pCardType;
	}

	public String getpCardHolder() {
		return pCardHolder;
	}

	public void setpCardHolder(String pCardHolder) {
		this.pCardHolder = pCardHolder;
	}

	public String getpCardNumber() {
		return pCardNumber;
	}

	public void setpCardNumber(String pCardNumber) {
		this.pCardNumber = pCardNumber;
	}

	public String getpCardExpires() {
		return pCardExpires;
	}

	public void setpCardExpires(String pCardExpires) {
		this.pCardExpires = pCardExpires;
	}

	public String getInspector() {
		return (java.lang.String)HiltonUtility.ckNull(this.inspector).trim();
	}

	public void setInspector(String inspector) {
		if (!HiltonUtility.isEmpty(inspector) && inspector.length() > 1) {
			inspector = inspector.substring(0, 1);
		}
		this.inspector = inspector;
	}

	public String getEngineer() {
		return (java.lang.String)HiltonUtility.ckNull(this.engineer).trim();
	}

	public void setEngineer(String engineer) {
		if (!HiltonUtility.isEmpty(engineer) && engineer.length() > 1) {
			engineer = engineer.substring(0, 1);
		}
		this.engineer = engineer;
	}

	public String getFpe() {
		return (java.lang.String)HiltonUtility.ckNull(this.fpe).trim();
	}

	public void setFpe(String fpe) {
		if (!HiltonUtility.isEmpty(fpe) && fpe.length() > 1) {
			fpe = fpe.substring(0, 1);
		}
		this.fpe = fpe;
	}

	public String getMarker() {
		return (java.lang.String)HiltonUtility.ckNull(this.marker).trim();
	}

	public void setMarker(String marker) {
		if (!HiltonUtility.isEmpty(marker) && marker.length() > 1) {
			marker = marker.substring(0, 1);
		}
		this.marker = marker;
	}

	public java.util.Date getLockedOutTime() {
        return this.lockedOutTime;
    }

    public void setLockedOutTime(java.util.Date lockedOutTime) {
        this.lockedOutTime = lockedOutTime;
    }

    public java.math.BigDecimal getLockOutCounter() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.lockOutCounter);
    }

    public void setLockOutCounter(final java.math.BigDecimal lockOutCounter) {
        this.lockOutCounter = lockOutCounter;
    }

    public String getTargetCenter() {		
		return (java.lang.String)HiltonUtility.ckNull(this.targetCenter).trim();
	}

	public void setTargetCenter(String targetCenter) {
		if (!HiltonUtility.isEmpty(targetCenter) && targetCenter.length() > 30) {
			targetCenter = targetCenter.substring(0, 30);
		}		
		this.targetCenter = targetCenter;
	}

}