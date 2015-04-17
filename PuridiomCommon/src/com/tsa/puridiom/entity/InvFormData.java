package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/** @author Hibernate CodeGenerator */
public class InvFormData implements Serializable {

    /** identifier field */
    private String itemNumber;

    /** nullable persistent field */
    private java.math.BigDecimal icNotes;

    /** nullable persistent field */
    private String stoItemNumber;

    /** nullable persistent field */
    private String sto2ItemNumber;

    /** nullable persistent field */
    private String sto3ItemNumber;

    /** nullable persistent field */
    private String formNumber;

    /** nullable persistent field */
    private String formRevision;

    /** nullable persistent field */
    private String formReplaces;

    /** nullable persistent field */
    private String primeUser;

    /** nullable persistent field */
    private String department;

    /** nullable persistent field */
    private String officeLoc;

    /** nullable persistent field */
    private String budgetCode;

    /** nullable persistent field */
    private String lobCode;

    /** nullable persistent field */
    private String formType;

    /** nullable persistent field */
    private String formClass;

    /** nullable persistent field */
    private String formSize;

    /** nullable persistent field */
    private String coverStock;

    /** nullable persistent field */
    private String coverInk;

    /** nullable persistent field */
    private java.math.BigDecimal pages;

    /** nullable persistent field */
    private String pageStock;

    /** nullable persistent field */
    private String pageInk;

    /** nullable persistent field */
    private String formLogo1;

    /** nullable persistent field */
    private String formLogo2;

    /** nullable persistent field */
    private String formFolding;

    /** nullable persistent field */
    private String formBinding;

    /** nullable persistent field */
    private String formPadding;

    /** nullable persistent field */
    private String formPkging;

    /** nullable persistent field */
    private String formArt;

    /** nullable persistent field */
    private String formSig1;

    /** nullable persistent field */
    private String formSig2;

    /** nullable persistent field */
    private String legalReview;

    /** nullable persistent field */
    private String numberFrom;

    /** nullable persistent field */
    private String numberTo;

    /** nullable persistent field */
    private String noMissing;

    /** nullable persistent field */
    private String listMissing;

    /** nullable persistent field */
    private String numberPrefix;

    /** nullable persistent field */
    private String numberSuffix;

    /** nullable persistent field */
    private String micrCode;
    
    /** nullable persistent field */
    private String micrDesc;

    /** nullable persistent field */
    private String ocrCode;

    /** nullable persistent field */
    private String ocrDesc;

    /** nullable persistent field */
    private String numUdf1;

    /** nullable persistent field */
    private String numUdf2;

    /** nullable persistent field */
    private String numUdf3;

    /** nullable persistent field */
    private String numUdf4;

    /** nullable persistent field */
    private String numUdf5;

    /** nullable persistent field */
    private String fastenPos;

    /** nullable persistent field */
    private String fastenDesc;

    /** nullable persistent field */
    private String fastenType;

    /** nullable persistent field */
    private String binding;

    /** nullable persistent field */
    private java.math.BigDecimal bindingPer;

    /** nullable persistent field */
    private String bindingCover;

    /** nullable persistent field */
    private String bindingBack;

    /** nullable persistent field */
    private String padding;

    /** nullable persistent field */
    private java.math.BigDecimal paddingPer;

    /** nullable persistent field */
    private String paddingCover;

    /** nullable persistent field */
    private String paddingBack;

    /** nullable persistent field */
    private String typewriter;

    /** nullable persistent field */
    private java.math.BigDecimal typewriterVsp;

    /** nullable persistent field */
    private java.math.BigDecimal typewriterHsp;

    /** nullable persistent field */
    private String printer;

    /** nullable persistent field */
    private java.math.BigDecimal printerVsp;

    /** nullable persistent field */
    private java.math.BigDecimal printerHsp;

    /** nullable persistent field */
    private String decollator;

    /** nullable persistent field */
    private String burster;

    /** nullable persistent field */
    private String mailEquip;

    /** nullable persistent field */
    private String envSize;

    /** nullable persistent field */
    private String winSize;

    /** nullable persistent field */
    private java.math.BigDecimal posFromleft;

    /** nullable persistent field */
    private java.math.BigDecimal posFrombot;

    /** nullable persistent field */
    private String flapPos;

    /** nullable persistent field */
    private String envType;

    /** nullable persistent field */
    private String gumType;

    /** nullable persistent field */
    private String otherType;

    /** nullable persistent field */
    private String formColor;

    /** nullable persistent field */
    private String formWeight;

    /** nullable persistent field */
    private String formStock;

    /** nullable persistent field */
    private String formInk;

    /** nullable persistent field */
    private String labelSize;

    /** nullable persistent field */
    private java.math.BigDecimal perfLoc;

    /** nullable persistent field */
    private String appSurface;

    /** nullable persistent field */
    private String adhesive;

    /** nullable persistent field */
    private java.math.BigDecimal qtyPerCtn;

    /** nullable persistent field */
    private java.math.BigDecimal qtyPerPkg;

    /** nullable persistent field */
    private String pkgType;

    /** nullable persistent field */
    private String pkgOption;

    /** nullable persistent field */
    private String compCode;

    /** nullable persistent field */
    private String stockHbu;

    /** nullable persistent field */
    private String sizeFlat;

    /** nullable persistent field */
    private String coverPrints;

    /** nullable persistent field */
    private String bleeds;

    /** nullable persistent field */
    private String proofs;

    /** nullable persistent field */
    private String finishing;

    /** nullable persistent field */
    private String turnaround;

    /** nullable persistent field */
    private String formUdf01;

    /** nullable persistent field */
    private String formUdf02;

    /** nullable persistent field */
    private String specUdf01;

    /** nullable persistent field */
    private String specUdf02;

    /** nullable persistent field */
    private String specUdf03;

    /** nullable persistent field */
    private String specNotes;

    /** nullable persistent field */
    private String formAddress;

    /** nullable persistent field */
    private java.math.BigDecimal maxReqQty;

    /** nullable persistent field */
    private String appointedFlag;

    /** nullable persistent field */
    private String equivalentStock;

    /** nullable persistent field */
    private String autoReprint;

    /** nullable persistent field */
    private String inprintableSpace;

    /** nullable persistent field */
    private String formTraps;

    /** nullable persistent field */
    private String colorSeparation;

    /** nullable persistent field */
    private String supInstruction;

    /** nullable persistent field */
    private String useType;

    /** nullable persistent field */
    private java.util.Date mailDate;

    /** nullable persistent field */
    private String envLabel;

    /** nullable persistent field */
    private String envColor;

    /** nullable persistent field */
    private String envWeight;

    /** nullable persistent field */
    private String envStock;

    /** nullable persistent field */
    private String envInk;

    /** nullable persistent field */
    private String envOther;

    /** nullable persistent field */
    private java.util.Date dateOut;

    /** nullable persistent field */
    private java.util.Date docPrtDate;

    /** nullable persistent field */
    private java.util.Date puAppDate;

    /** nullable persistent field */
    private java.util.Date faAppDate;

    /** nullable persistent field */
    private String automaticReprint;

    /** full constructor */
    public InvFormData(java.lang.String itemNumber, java.math.BigDecimal icNotes, java.lang.String stoItemNumber, java.lang.String sto2ItemNumber, java.lang.String sto3ItemNumber, java.lang.String formNumber, java.lang.String formRevision, java.lang.String formReplaces, java.lang.String primeUser, java.lang.String department, java.lang.String officeLoc, java.lang.String budgetCode, java.lang.String lobCode, java.lang.String formType, java.lang.String formClass, java.lang.String formSize, java.lang.String coverStock, java.lang.String coverInk, java.math.BigDecimal pages, java.lang.String pageStock, java.lang.String pageInk, java.lang.String formLogo1, java.lang.String formLogo2, java.lang.String formFolding, java.lang.String formBinding, java.lang.String formPadding, java.lang.String formPkging, java.lang.String formArt, java.lang.String formSig1, java.lang.String formSig2, java.lang.String legalReview, java.lang.String numberFrom, java.lang.String numberTo, java.lang.String noMissing, java.lang.String listMissing, java.lang.String numberPrefix, java.lang.String numberSuffix, java.lang.String micrCode, java.lang.String micrDesc, java.lang.String ocrCode, java.lang.String ocrDesc, java.lang.String numUdf1, java.lang.String numUdf2, java.lang.String numUdf3, java.lang.String numUdf4, java.lang.String numUdf5, java.lang.String fastenPos, java.lang.String fastenDesc, java.lang.String fastenType, java.lang.String binding, java.math.BigDecimal bindingPer, java.lang.String bindingCover, java.lang.String bindingBack, java.lang.String padding, java.math.BigDecimal paddingPer, java.lang.String paddingCover, java.lang.String paddingBack, java.lang.String typewriter, java.math.BigDecimal typewriterVsp, java.math.BigDecimal typewriterHsp, java.lang.String printer, java.math.BigDecimal printerVsp, java.math.BigDecimal printerHsp, java.lang.String decollator, java.lang.String burster, java.lang.String mailEquip, java.lang.String envSize, java.lang.String winSize, java.math.BigDecimal posFromleft, java.math.BigDecimal posFrombot, java.lang.String flapPos, java.lang.String envType, java.lang.String gumType, java.lang.String otherType, java.lang.String formColor, java.lang.String formWeight, java.lang.String formStock, java.lang.String formInk, java.lang.String labelSize, java.math.BigDecimal perfLoc, java.lang.String appSurface, java.lang.String adhesive, java.math.BigDecimal qtyPerCtn, java.math.BigDecimal qtyPerPkg, java.lang.String pkgType, java.lang.String pkgOption, java.lang.String compCode, java.lang.String stockHbu, java.lang.String sizeFlat, java.lang.String coverPrints, java.lang.String bleeds, java.lang.String proofs, java.lang.String finishing, java.lang.String turnaround, java.lang.String formUdf01, java.lang.String formUdf02, java.lang.String specUdf01, java.lang.String specUdf02, java.lang.String specUdf03, java.lang.String specNotes, java.lang.String formAddress, java.math.BigDecimal maxReqQty, java.lang.String appointedFlag, java.lang.String equivalentStock, java.lang.String autoReprint, java.lang.String inprintableSpace, java.lang.String formTraps, java.lang.String colorSeparation, java.lang.String supInstruction, java.lang.String useType, java.util.Date mailDate, java.lang.String envLabel, java.lang.String envColor, java.lang.String envWeight, java.lang.String envStock, java.lang.String envInk, java.lang.String envOther, java.util.Date dateOut, java.util.Date docPrtDate, java.util.Date puAppDate, java.util.Date faAppDate, java.lang.String automaticReprint) {
        this.itemNumber = itemNumber;
        this.icNotes = icNotes;
        this.stoItemNumber = stoItemNumber;
        this.sto2ItemNumber = sto2ItemNumber;
        this.sto3ItemNumber = sto3ItemNumber;
        this.formNumber = formNumber;
        this.formRevision = formRevision;
        this.formReplaces = formReplaces;
        this.primeUser = primeUser;
        this.department = department;
        this.officeLoc = officeLoc;
        this.budgetCode = budgetCode;
        this.lobCode = lobCode;
        this.formType = formType;
        this.formClass = formClass;
        this.formSize = formSize;
        this.coverStock = coverStock;
        this.coverInk = coverInk;
        this.pages = pages;
        this.pageStock = pageStock;
        this.pageInk = pageInk;
        this.formLogo1 = formLogo1;
        this.formLogo2 = formLogo2;
        this.formFolding = formFolding;
        this.formBinding = formBinding;
        this.formPadding = formPadding;
        this.formPkging = formPkging;
        this.formArt = formArt;
        this.formSig1 = formSig1;
        this.formSig2 = formSig2;
        this.legalReview = legalReview;
        this.numberFrom = numberFrom;
        this.numberTo = numberTo;
        this.noMissing = noMissing;
        this.listMissing = listMissing;
        this.numberPrefix = numberPrefix;
        this.numberSuffix = numberSuffix;
        this.micrCode = micrCode;
        this.micrDesc = micrDesc;
        this.ocrCode = ocrCode;
        this.ocrDesc = ocrDesc;
        this.numUdf1 = numUdf1;
        this.numUdf2 = numUdf2;
        this.numUdf3 = numUdf3;
        this.numUdf4 = numUdf4;
        this.numUdf5 = numUdf5;
        this.fastenPos = fastenPos;
        this.fastenDesc = fastenDesc;
        this.fastenType = fastenType;
        this.binding = binding;
        this.bindingPer = bindingPer;
        this.bindingCover = bindingCover;
        this.bindingBack = bindingBack;
        this.padding = padding;
        this.paddingPer = paddingPer;
        this.paddingCover = paddingCover;
        this.paddingBack = paddingBack;
        this.typewriter = typewriter;
        this.typewriterVsp = typewriterVsp;
        this.typewriterHsp = typewriterHsp;
        this.printer = printer;
        this.printerVsp = printerVsp;
        this.printerHsp = printerHsp;
        this.decollator = decollator;
        this.burster = burster;
        this.mailEquip = mailEquip;
        this.envSize = envSize;
        this.winSize = winSize;
        this.posFromleft = posFromleft;
        this.posFrombot = posFrombot;
        this.flapPos = flapPos;
        this.envType = envType;
        this.gumType = gumType;
        this.otherType = otherType;
        this.formColor = formColor;
        this.formWeight = formWeight;
        this.formStock = formStock;
        this.formInk = formInk;
        this.labelSize = labelSize;
        this.perfLoc = perfLoc;
        this.appSurface = appSurface;
        this.adhesive = adhesive;
        this.qtyPerCtn = qtyPerCtn;
        this.qtyPerPkg = qtyPerPkg;
        this.pkgType = pkgType;
        this.pkgOption = pkgOption;
        this.compCode = compCode;
        this.stockHbu = stockHbu;
        this.sizeFlat = sizeFlat;
        this.coverPrints = coverPrints;
        this.bleeds = bleeds;
        this.proofs = proofs;
        this.finishing = finishing;
        this.turnaround = turnaround;
        this.formUdf01 = formUdf01;
        this.formUdf02 = formUdf02;
        this.specUdf01 = specUdf01;
        this.specUdf02 = specUdf02;
        this.specUdf03 = specUdf03;
        this.specNotes = specNotes;
        this.formAddress = formAddress;
        this.maxReqQty = maxReqQty;
        this.appointedFlag = appointedFlag;
        this.equivalentStock = equivalentStock;
        this.autoReprint = autoReprint;
        this.inprintableSpace = inprintableSpace;
        this.formTraps = formTraps;
        this.colorSeparation = colorSeparation;
        this.supInstruction = supInstruction;
        this.useType = useType;
        this.mailDate = mailDate;
        this.envLabel = envLabel;
        this.envColor = envColor;
        this.envWeight = envWeight;
        this.envStock = envStock;
        this.envInk = envInk;
        this.envOther = envOther;
        this.dateOut = dateOut;
        this.docPrtDate = docPrtDate;
        this.puAppDate = puAppDate;
        this.faAppDate = faAppDate;
        this.automaticReprint = automaticReprint;
    }

    /** default constructor */
    public InvFormData() {
    }

    /** minimal constructor */
    public InvFormData(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.lang.String getItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.itemNumber).trim();
    }

    public void setItemNumber(java.lang.String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public java.math.BigDecimal getIcNotes() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icNotes);
    }

    public void setIcNotes(java.math.BigDecimal icNotes) {
        this.icNotes = icNotes;
    }

    public java.lang.String getStoItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.stoItemNumber).trim();
    }

    public void setStoItemNumber(java.lang.String stoItemNumber) {
		if (!HiltonUtility.isEmpty(stoItemNumber) && stoItemNumber.length() > 30) {
			stoItemNumber = stoItemNumber.substring(0, 30);
		}
		this.stoItemNumber = stoItemNumber;
    }

    public java.lang.String getSto2ItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.sto2ItemNumber).trim();
    }

    public void setSto2ItemNumber(java.lang.String sto2ItemNumber) {
		if (!HiltonUtility.isEmpty(sto2ItemNumber) && sto2ItemNumber.length() > 30) {
			sto2ItemNumber = sto2ItemNumber.substring(0, 30);
		}
		this.sto2ItemNumber = sto2ItemNumber;
    }

    public java.lang.String getSto3ItemNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.sto3ItemNumber).trim();
    }

    public void setSto3ItemNumber(java.lang.String sto3ItemNumber) {
		if (!HiltonUtility.isEmpty(sto3ItemNumber) && sto3ItemNumber.length() > 30) {
			sto3ItemNumber = sto3ItemNumber.substring(0, 30);
		}
		this.sto3ItemNumber = sto3ItemNumber;
    }

    public java.lang.String getFormNumber() {
		return (java.lang.String)HiltonUtility.ckNull(this.formNumber).trim();
    }

    public void setFormNumber(java.lang.String formNumber) {
		if (!HiltonUtility.isEmpty(formNumber) && formNumber.length() > 20) {
			formNumber = formNumber.substring(0, 20);
		}
		this.formNumber = formNumber;
    }

    public java.lang.String getFormRevision() {
		return (java.lang.String)HiltonUtility.ckNull(this.formRevision).trim();
    }

    public void setFormRevision(java.lang.String formRevision) {
		if (!HiltonUtility.isEmpty(formRevision) && formRevision.length() > 10) {
			formRevision = formRevision.substring(0, 10);
		}
		this.formRevision = formRevision;
    }

    public java.lang.String getFormReplaces() {
		return (java.lang.String)HiltonUtility.ckNull(this.formReplaces).trim();
    }

    public void setFormReplaces(java.lang.String formReplaces) {
		if (!HiltonUtility.isEmpty(formReplaces) && formReplaces.length() > 20) {
			formReplaces = formReplaces.substring(0, 20);
		}
		this.formReplaces = formReplaces;
    }

    public java.lang.String getPrimeUser() {
		return (java.lang.String)HiltonUtility.ckNull(this.primeUser).trim();
    }

    public void setPrimeUser(java.lang.String primeUser) {
		if (!HiltonUtility.isEmpty(primeUser) && primeUser.length() > 15) {
			primeUser = primeUser.substring(0, 15);
		}
		this.primeUser = primeUser;
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

    public java.lang.String getOfficeLoc() {
		return (java.lang.String)HiltonUtility.ckNull(this.officeLoc).trim();
    }

    public void setOfficeLoc(java.lang.String officeLoc) {
		if (!HiltonUtility.isEmpty(officeLoc) && officeLoc.length() > 40) {
			officeLoc = officeLoc.substring(0, 40);
		}
		this.officeLoc = officeLoc;
    }

    public java.lang.String getBudgetCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.budgetCode).trim();
    }

    public void setBudgetCode(java.lang.String budgetCode) {
		if (!HiltonUtility.isEmpty(budgetCode) && budgetCode.length() > 17) {
			budgetCode = budgetCode.substring(0, 17);
		}
		this.budgetCode = budgetCode;
    }

    public java.lang.String getLobCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.lobCode).trim();
    }

    public void setLobCode(java.lang.String lobCode) {
		if (!HiltonUtility.isEmpty(lobCode) && lobCode.length() > 15) {
			lobCode = lobCode.substring(0, 15);
		}
		this.lobCode = lobCode;
    }

    public java.lang.String getFormType() {
		return (java.lang.String)HiltonUtility.ckNull(this.formType).trim();
    }

    public void setFormType(java.lang.String formType) {
		if (!HiltonUtility.isEmpty(formType) && formType.length() > 15) {
			formType = formType.substring(0, 15);
		}
		this.formType = formType;
    }

    public java.lang.String getFormClass() {
		return (java.lang.String)HiltonUtility.ckNull(this.formClass).trim();
    }

    public void setFormClass(java.lang.String formClass) {
		if (!HiltonUtility.isEmpty(formClass) && formClass.length() > 15) {
			formClass = formClass.substring(0, 15);
		}
		this.formClass = formClass;
    }

    public java.lang.String getFormSize() {
		return (java.lang.String)HiltonUtility.ckNull(this.formSize).trim();
    }

    public void setFormSize(java.lang.String formSize) {
		if (!HiltonUtility.isEmpty(formSize) && formSize.length() > 15) {
			formSize = formSize.substring(0, 15);
		}
		this.formSize = formSize;
    }

    public java.lang.String getCoverStock() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverStock).trim();
    }

    public void setCoverStock(java.lang.String coverStock) {
		if (!HiltonUtility.isEmpty(coverStock) && coverStock.length() > 15) {
			coverStock = coverStock.substring(0, 15);
		}
		this.coverStock = coverStock;
    }

    public java.lang.String getCoverInk() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverInk).trim();
    }

    public void setCoverInk(java.lang.String coverInk) {
		if (!HiltonUtility.isEmpty(coverInk) && coverInk.length() > 15) {
			coverInk = coverInk.substring(0, 15);
		}
		this.coverInk = coverInk;
    }

    public java.math.BigDecimal getPages() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.pages);
    }

    public void setPages(java.math.BigDecimal pages) {
        this.pages = pages;
    }

    public java.lang.String getPageStock() {
		return (java.lang.String)HiltonUtility.ckNull(this.pageStock).trim();
    }

    public void setPageStock(java.lang.String pageStock) {
		if (!HiltonUtility.isEmpty(pageStock) && pageStock.length() > 15) {
			pageStock = pageStock.substring(0, 15);
		}
		this.pageStock = pageStock;
    }

    public java.lang.String getPageInk() {
		return (java.lang.String)HiltonUtility.ckNull(this.pageInk).trim();
    }

    public void setPageInk(java.lang.String pageInk) {
		if (!HiltonUtility.isEmpty(pageInk) && pageInk.length() > 15) {
			pageInk = pageInk.substring(0, 15);
		}
		this.pageInk = pageInk;
    }

    public java.lang.String getFormLogo1() {
		return (java.lang.String)HiltonUtility.ckNull(this.formLogo1).trim();
    }

    public void setFormLogo1(java.lang.String formLogo1) {
		if (!HiltonUtility.isEmpty(formLogo1) && formLogo1.length() > 15) {
			formLogo1 = formLogo1.substring(0, 15);
		}
		this.formLogo1 = formLogo1;
    }

    public java.lang.String getFormLogo2() {
		return (java.lang.String)HiltonUtility.ckNull(this.formLogo2).trim();
    }

    public void setFormLogo2(java.lang.String formLogo2) {
		if (!HiltonUtility.isEmpty(formLogo2) && formLogo2.length() > 15) {
			formLogo2 = formLogo2.substring(0, 15);
		}
		this.formLogo2 = formLogo2;
    }

    public java.lang.String getFormFolding() {
		return (java.lang.String)HiltonUtility.ckNull(this.formFolding).trim();
    }

    public void setFormFolding(java.lang.String formFolding) {
		if (!HiltonUtility.isEmpty(formFolding) && formFolding.length() > 15) {
			formFolding = formFolding.substring(0, 15);
		}
		this.formFolding = formFolding;
    }

    public java.lang.String getFormBinding() {
		return (java.lang.String)HiltonUtility.ckNull(this.formBinding).trim();
    }

    public void setFormBinding(java.lang.String formBinding) {
		if (!HiltonUtility.isEmpty(formBinding) && formBinding.length() > 15) {
			formBinding = formBinding.substring(0, 15);
		}
		this.formBinding = formBinding;
    }

    public java.lang.String getFormPadding() {
		return (java.lang.String)HiltonUtility.ckNull(this.formPadding).trim();
    }

    public void setFormPadding(java.lang.String formPadding) {
		if (!HiltonUtility.isEmpty(formPadding) && formPadding.length() > 15) {
			formPadding = formPadding.substring(0, 15);
		}
		this.formPadding = formPadding;
    }

    public java.lang.String getFormPkging() {
		return (java.lang.String)HiltonUtility.ckNull(this.formPkging).trim();
    }

    public void setFormPkging(java.lang.String formPkging) {
		if (!HiltonUtility.isEmpty(formPkging) && formPkging.length() > 15) {
			formPkging = formPkging.substring(0, 15);
		}
		this.formPkging = formPkging;
    }

    public java.lang.String getFormArt() {
		return (java.lang.String)HiltonUtility.ckNull(this.formArt).trim();
    }

    public void setFormArt(java.lang.String formArt) {
		if (!HiltonUtility.isEmpty(formArt) && formArt.length() > 15) {
			formArt = formArt.substring(0, 15);
		}
		this.formArt = formArt;
    }

    public java.lang.String getFormSig1() {
		return (java.lang.String)HiltonUtility.ckNull(this.formSig1).trim();
    }

    public void setFormSig1(java.lang.String formSig1) {
		if (!HiltonUtility.isEmpty(formSig1) && formSig1.length() > 12) {
			formSig1 = formSig1.substring(0, 12);
		}
		this.formSig1 = formSig1;
    }

    public java.lang.String getFormSig2() {
		return (java.lang.String)HiltonUtility.ckNull(this.formSig2).trim();
    }

    public void setFormSig2(java.lang.String formSig2) {
		if (!HiltonUtility.isEmpty(formSig2) && formSig2.length() > 12) {
			formSig2 = formSig2.substring(0, 12);
		}
		this.formSig2 = formSig2;
    }

    public java.lang.String getLegalReview() {
		return (java.lang.String)HiltonUtility.ckNull(this.legalReview).trim();
    }

    public void setLegalReview(java.lang.String legalReview) {
		if (!HiltonUtility.isEmpty(legalReview) && legalReview.length() > 1) {
			legalReview = legalReview.substring(0, 1);
		}
		this.legalReview = legalReview;
    }

    public java.lang.String getNumberFrom() {
		return (java.lang.String)HiltonUtility.ckNull(this.numberFrom).trim();
    }

    public void setNumberFrom(java.lang.String numberFrom) {
		if (!HiltonUtility.isEmpty(numberFrom) && numberFrom.length() > 20) {
			numberFrom = numberFrom.substring(0, 20);
		}
		this.numberFrom = numberFrom;
    }

    public java.lang.String getNumberTo() {
		return (java.lang.String)HiltonUtility.ckNull(this.numberTo).trim();
    }

    public void setNumberTo(java.lang.String numberTo) {
		if (!HiltonUtility.isEmpty(numberTo) && numberTo.length() > 20) {
			numberTo = numberTo.substring(0, 20);
		}
		this.numberTo = numberTo;
    }

    public java.lang.String getNoMissing() {
		return (java.lang.String)HiltonUtility.ckNull(this.noMissing).trim();
    }

    public void setNoMissing(java.lang.String noMissing) {
		if (!HiltonUtility.isEmpty(noMissing) && noMissing.length() > 1) {
			noMissing = noMissing.substring(0, 1);
		}
		this.noMissing = noMissing;
    }

    public java.lang.String getListMissing() {
		return (java.lang.String)HiltonUtility.ckNull(this.listMissing).trim();
    }

    public void setListMissing(java.lang.String listMissing) {
		if (!HiltonUtility.isEmpty(listMissing) && listMissing.length() > 1) {
			listMissing = listMissing.substring(0, 1);
		}
		this.listMissing = listMissing;
    }

    public java.lang.String getNumberPrefix() {
		return (java.lang.String)HiltonUtility.ckNull(this.numberPrefix).trim();
    }

    public void setNumberPrefix(java.lang.String numberPrefix) {
		if (!HiltonUtility.isEmpty(numberPrefix) && numberPrefix.length() > 10) {
			numberPrefix = numberPrefix.substring(0, 10);
		}
		this.numberPrefix = numberPrefix;
    }

    public java.lang.String getNumberSuffix() {
		return (java.lang.String)HiltonUtility.ckNull(this.numberSuffix).trim();
    }

    public void setNumberSuffix(java.lang.String numberSuffix) {
		if (!HiltonUtility.isEmpty(numberSuffix) && numberSuffix.length() > 10) {
			numberSuffix = numberSuffix.substring(0, 10);
		}
		this.numberSuffix = numberSuffix;
    }

    public java.lang.String getMicrCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.micrCode).trim();
    }

    public void setMicrCode(java.lang.String micrCode) {
		if (!HiltonUtility.isEmpty(micrCode) && micrCode.length() > 15) {
			micrCode = micrCode.substring(0, 15);
		}
		this.micrCode = micrCode;
    }
    
    public java.lang.String getMicrDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.micrDesc).trim();
    }

    public void setMicrDesc(java.lang.String micrDesc) {
		if (!HiltonUtility.isEmpty(micrDesc) && micrDesc.length() > 50) {
			micrDesc = micrDesc.substring(0, 50);
		}
		this.micrDesc = micrDesc;
    }

    public java.lang.String getOcrCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.ocrCode).trim();
    }

    public void setOcrCode(java.lang.String ocrCode) {
		if (!HiltonUtility.isEmpty(ocrCode) && ocrCode.length() > 15) {
			ocrCode = ocrCode.substring(0, 15);
		}
		this.ocrCode = ocrCode;
    }

    public java.lang.String getOcrDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.ocrDesc).trim();
    }

    public void setOcrDesc(java.lang.String ocrDesc) {
		if (!HiltonUtility.isEmpty(ocrDesc) && ocrDesc.length() > 15) {
			ocrDesc = ocrDesc.substring(0, 15);
		}
		this.ocrDesc = ocrDesc;
    }

    public java.lang.String getNumUdf1() {
		return (java.lang.String)HiltonUtility.ckNull(this.numUdf1).trim();
    }

    public void setNumUdf1(java.lang.String numUdf1) {
		if (!HiltonUtility.isEmpty(numUdf1) && numUdf1.length() > 20) {
			numUdf1 = numUdf1.substring(0, 20);
		}
		this.numUdf1 = numUdf1;
    }

    public java.lang.String getNumUdf2() {
		return (java.lang.String)HiltonUtility.ckNull(this.numUdf2).trim();
    }

    public void setNumUdf2(java.lang.String numUdf2) {
		if (!HiltonUtility.isEmpty(numUdf2) && numUdf2.length() > 20) {
			numUdf2 = numUdf2.substring(0, 20);
		}
		this.numUdf2 = numUdf2;
    }

    public java.lang.String getNumUdf3() {
		return (java.lang.String)HiltonUtility.ckNull(this.numUdf3).trim();
    }

    public void setNumUdf3(java.lang.String numUdf3) {
		if (!HiltonUtility.isEmpty(numUdf3) && numUdf3.length() > 20) {
			numUdf3 = numUdf3.substring(0, 20);
		}
		this.numUdf3 = numUdf3;
    }

    public java.lang.String getNumUdf4() {
		return (java.lang.String)HiltonUtility.ckNull(this.numUdf4).trim();
    }

    public void setNumUdf4(java.lang.String numUdf4) {
		if (!HiltonUtility.isEmpty(numUdf4) && numUdf4.length() > 20) {
			numUdf4 = numUdf4.substring(0, 20);
		}
		this.numUdf4 = numUdf4;
    }

    public java.lang.String getNumUdf5() {
		return (java.lang.String)HiltonUtility.ckNull(this.numUdf5).trim();
    }

    public void setNumUdf5(java.lang.String numUdf5) {
		if (!HiltonUtility.isEmpty(numUdf5) && numUdf5.length() > 20) {
			numUdf5 = numUdf5.substring(0, 20);
		}
		this.numUdf5 = numUdf5;
    }

    public java.lang.String getFastenPos() {
		return (java.lang.String)HiltonUtility.ckNull(this.fastenPos).trim();
    }

    public void setFastenPos(java.lang.String fastenPos) {
		if (!HiltonUtility.isEmpty(fastenPos) && fastenPos.length() > 20) {
			fastenPos = fastenPos.substring(0, 20);
		}
		this.fastenPos = fastenPos;
    }

    public java.lang.String getFastenDesc() {
		return (java.lang.String)HiltonUtility.ckNull(this.fastenDesc).trim();
    }

    public void setFastenDesc(java.lang.String fastenDesc) {
		if (!HiltonUtility.isEmpty(fastenDesc) && fastenDesc.length() > 60) {
			fastenDesc = fastenDesc.substring(0, 60);
		}
		this.fastenDesc = fastenDesc;
    }

    public java.lang.String getFastenType() {
		return (java.lang.String)HiltonUtility.ckNull(this.fastenType).trim();
    }

    public void setFastenType(java.lang.String fastenType) {
		if (!HiltonUtility.isEmpty(fastenType) && fastenType.length() > 20) {
			fastenType = fastenType.substring(0, 20);
		}
		this.fastenType = fastenType;
    }

    public java.lang.String getBinding() {
		return (java.lang.String)HiltonUtility.ckNull(this.binding).trim();
    }

    public void setBinding(java.lang.String binding) {
		if (!HiltonUtility.isEmpty(binding) && binding.length() > 15) {
			binding = binding.substring(0, 15);
		}
		this.binding = binding;
    }

    public java.math.BigDecimal getBindingPer() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.bindingPer);
    }

    public void setBindingPer(java.math.BigDecimal bindingPer) {
        this.bindingPer = bindingPer;
    }

    public java.lang.String getBindingCover() {
		return (java.lang.String)HiltonUtility.ckNull(this.bindingCover).trim();
    }

    public void setBindingCover(java.lang.String bindingCover) {
		if (!HiltonUtility.isEmpty(bindingCover) && bindingCover.length() > 15) {
			bindingCover = bindingCover.substring(0, 15);
		}
		this.bindingCover = bindingCover;
    }

    public java.lang.String getBindingBack() {
		return (java.lang.String)HiltonUtility.ckNull(this.bindingBack).trim();
    }

    public void setBindingBack(java.lang.String bindingBack) {
		if (!HiltonUtility.isEmpty(bindingBack) && bindingBack.length() > 15) {
			bindingBack = bindingBack.substring(0, 15);
		}
		this.bindingBack = bindingBack;
    }

    public java.lang.String getPadding() {
		return (java.lang.String)HiltonUtility.ckNull(this.padding).trim();
    }

    public void setPadding(java.lang.String padding) {
		if (!HiltonUtility.isEmpty(padding) && padding.length() > 15) {
			padding = padding.substring(0, 15);
		}
		this.padding = padding;
    }

    public java.math.BigDecimal getPaddingPer() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.paddingPer);
    }

    public void setPaddingPer(java.math.BigDecimal paddingPer) {
        this.paddingPer = paddingPer;
    }

    public java.lang.String getPaddingCover() {
		return (java.lang.String)HiltonUtility.ckNull(this.paddingCover).trim();
    }

    public void setPaddingCover(java.lang.String paddingCover) {
		if (!HiltonUtility.isEmpty(paddingCover) && paddingCover.length() > 15) {
			paddingCover = paddingCover.substring(0, 15);
		}
		this.paddingCover = paddingCover;
    }

    public java.lang.String getPaddingBack() {
		return (java.lang.String)HiltonUtility.ckNull(this.paddingBack).trim();
    }

    public void setPaddingBack(java.lang.String paddingBack) {
		if (!HiltonUtility.isEmpty(paddingBack) && paddingBack.length() > 15) {
			paddingBack = paddingBack.substring(0, 15);
		}
		this.paddingBack = paddingBack;
    }

    public java.lang.String getTypewriter() {
		return (java.lang.String)HiltonUtility.ckNull(this.typewriter).trim();
    }

    public void setTypewriter(java.lang.String typewriter) {
		if (!HiltonUtility.isEmpty(typewriter) && typewriter.length() > 10) {
			typewriter = typewriter.substring(0, 10);
		}
		this.typewriter = typewriter;
    }

    public java.math.BigDecimal getTypewriterVsp() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.typewriterVsp);
    }

    public void setTypewriterVsp(java.math.BigDecimal typewriterVsp) {
        this.typewriterVsp = typewriterVsp;
    }

    public java.math.BigDecimal getTypewriterHsp() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.typewriterHsp);
    }

    public void setTypewriterHsp(java.math.BigDecimal typewriterHsp) {
        this.typewriterHsp = typewriterHsp;
    }

    public java.lang.String getPrinter() {
		return (java.lang.String)HiltonUtility.ckNull(this.printer).trim();
    }

    public void setPrinter(java.lang.String printer) {
		if (!HiltonUtility.isEmpty(printer) && printer.length() > 10) {
			printer = printer.substring(0, 10);
		}
		this.printer = printer;
    }

    public java.math.BigDecimal getPrinterVsp() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.printerVsp);
    }

    public void setPrinterVsp(java.math.BigDecimal printerVsp) {
        this.printerVsp = printerVsp;
    }

    public java.math.BigDecimal getPrinterHsp() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.printerHsp);
    }

    public void setPrinterHsp(java.math.BigDecimal printerHsp) {
        this.printerHsp = printerHsp;
    }

    public java.lang.String getDecollator() {
		return (java.lang.String)HiltonUtility.ckNull(this.decollator).trim();
    }

    public void setDecollator(java.lang.String decollator) {
		if (!HiltonUtility.isEmpty(decollator) && decollator.length() > 60) {
			decollator = decollator.substring(0, 60);
		}
		this.decollator = decollator;
    }

    public java.lang.String getBurster() {
		return (java.lang.String)HiltonUtility.ckNull(this.burster).trim();
    }

    public void setBurster(java.lang.String burster) {
		if (!HiltonUtility.isEmpty(burster) && burster.length() > 60) {
			burster = burster.substring(0, 60);
		}
		this.burster = burster;
    }

    public java.lang.String getMailEquip() {
		return (java.lang.String)HiltonUtility.ckNull(this.mailEquip).trim();
    }

    public void setMailEquip(java.lang.String mailEquip) {
		if (!HiltonUtility.isEmpty(mailEquip) && mailEquip.length() > 60) {
			mailEquip = mailEquip.substring(0, 60);
		}
		this.mailEquip = mailEquip;
    }

    public java.lang.String getEnvSize() {
		return (java.lang.String)HiltonUtility.ckNull(this.envSize).trim();
    }

    public void setEnvSize(java.lang.String envSize) {
		if (!HiltonUtility.isEmpty(envSize) && envSize.length() > 15) {
			envSize = envSize.substring(0, 15);
		}
		this.envSize = envSize;
    }

    public java.lang.String getWinSize() {
		return (java.lang.String)HiltonUtility.ckNull(this.winSize).trim();
    }

    public void setWinSize(java.lang.String winSize) {
		if (!HiltonUtility.isEmpty(winSize) && winSize.length() > 20) {
			winSize = winSize.substring(0, 20);
		}
		this.winSize = winSize;
    }

    public java.math.BigDecimal getPosFromleft() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.posFromleft);
    }

    public void setPosFromleft(java.math.BigDecimal posFromleft) {
        this.posFromleft = posFromleft;
    }

    public java.math.BigDecimal getPosFrombot() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.posFrombot);
    }

    public void setPosFrombot(java.math.BigDecimal posFrombot) {
        this.posFrombot = posFrombot;
    }

    public java.lang.String getFlapPos() {
		return (java.lang.String)HiltonUtility.ckNull(this.flapPos).trim();
    }

    public void setFlapPos(java.lang.String flapPos) {
		if (!HiltonUtility.isEmpty(flapPos) && flapPos.length() > 20) {
			flapPos = flapPos.substring(0, 20);
		}
		this.flapPos = flapPos;
    }

    public java.lang.String getEnvType() {
		return (java.lang.String)HiltonUtility.ckNull(this.envType).trim();
    }

    public void setEnvType(java.lang.String envType) {
		if (!HiltonUtility.isEmpty(envType) && envType.length() > 15) {
			envType = envType.substring(0, 15);
		}
		this.envType = envType;
    }

    public java.lang.String getGumType() {
		return (java.lang.String)HiltonUtility.ckNull(this.gumType).trim();
    }

    public void setGumType(java.lang.String gumType) {
		if (!HiltonUtility.isEmpty(gumType) && gumType.length() > 20) {
			gumType = gumType.substring(0, 20);
		}
		this.gumType = gumType;
    }

    public java.lang.String getOtherType() {
		return (java.lang.String)HiltonUtility.ckNull(this.otherType).trim();
    }

    public void setOtherType(java.lang.String otherType) {
		if (!HiltonUtility.isEmpty(otherType) && otherType.length() > 20) {
			otherType = otherType.substring(0, 20);
		}
		this.otherType = otherType;
    }

    public java.lang.String getFormColor() {
		return (java.lang.String)HiltonUtility.ckNull(this.formColor).trim();
    }

    public void setFormColor(java.lang.String formColor) {
		if (!HiltonUtility.isEmpty(formColor) && formColor.length() > 15) {
			formColor = formColor.substring(0, 15);
		}
		this.formColor = formColor;
    }

    public java.lang.String getFormWeight() {
		return (java.lang.String)HiltonUtility.ckNull(this.formWeight).trim();
    }

    public void setFormWeight(java.lang.String formWeight) {
		if (!HiltonUtility.isEmpty(formWeight) && formWeight.length() > 15) {
			formWeight = formWeight.substring(0, 15);
		}
		this.formWeight = formWeight;
    }

    public java.lang.String getFormStock() {
		return (java.lang.String)HiltonUtility.ckNull(this.formStock).trim();
    }

    public void setFormStock(java.lang.String formStock) {
		if (!HiltonUtility.isEmpty(formStock) && formStock.length() > 15) {
			formStock = formStock.substring(0, 15);
		}
		this.formStock = formStock;
    }

    public java.lang.String getFormInk() {
		return (java.lang.String)HiltonUtility.ckNull(this.formInk).trim();
    }

    public void setFormInk(java.lang.String formInk) {
		if (!HiltonUtility.isEmpty(formInk) && formInk.length() > 15) {
			formInk = formInk.substring(0, 15);
		}
		this.formInk = formInk;
    }

    public java.lang.String getLabelSize() {
		return (java.lang.String)HiltonUtility.ckNull(this.labelSize).trim();
    }

    public void setLabelSize(java.lang.String labelSize) {
		if (!HiltonUtility.isEmpty(labelSize) && labelSize.length() > 20) {
			labelSize = labelSize.substring(0, 20);
		}
		this.labelSize = labelSize;
    }

    public java.math.BigDecimal getPerfLoc() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.perfLoc);
    }

    public void setPerfLoc(java.math.BigDecimal perfLoc) {
        this.perfLoc = perfLoc;
    }

    public java.lang.String getAppSurface() {
		return (java.lang.String)HiltonUtility.ckNull(this.appSurface).trim();
    }

    public void setAppSurface(java.lang.String appSurface) {
		if (!HiltonUtility.isEmpty(appSurface) && appSurface.length() > 20) {
			appSurface = appSurface.substring(0, 20);
		}
		this.appSurface = appSurface;
    }

    public java.lang.String getAdhesive() {
		return (java.lang.String)HiltonUtility.ckNull(this.adhesive).trim();
    }

    public void setAdhesive(java.lang.String adhesive) {
		if (!HiltonUtility.isEmpty(adhesive) && adhesive.length() > 20) {
			adhesive = adhesive.substring(0, 20);
		}
		this.adhesive = adhesive;
    }

    public java.math.BigDecimal getQtyPerCtn() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyPerCtn);
    }

    public void setQtyPerCtn(java.math.BigDecimal qtyPerCtn) {
        this.qtyPerCtn = qtyPerCtn;
    }

    public java.math.BigDecimal getQtyPerPkg() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.qtyPerPkg);
    }

    public void setQtyPerPkg(java.math.BigDecimal qtyPerPkg) {
        this.qtyPerPkg = qtyPerPkg;
    }

    public java.lang.String getPkgType() {
		return (java.lang.String)HiltonUtility.ckNull(this.pkgType).trim();
    }

    public void setPkgType(java.lang.String pkgType) {
		if (!HiltonUtility.isEmpty(pkgType) && pkgType.length() > 15) {
			pkgType = pkgType.substring(0, 15);
		}
		this.pkgType = pkgType;
    }

    public java.lang.String getPkgOption() {
		return (java.lang.String)HiltonUtility.ckNull(this.pkgOption).trim();
    }

    public void setPkgOption(java.lang.String pkgOption) {
		if (!HiltonUtility.isEmpty(pkgOption) && pkgOption.length() > 20) {
			pkgOption = pkgOption.substring(0, 20);
		}
		this.pkgOption = pkgOption;
    }

    public java.lang.String getCompCode() {
		return (java.lang.String)HiltonUtility.ckNull(this.compCode).trim();
    }

    public void setCompCode(java.lang.String compCode) {
		if (!HiltonUtility.isEmpty(compCode) && compCode.length() > 15) {
			compCode = compCode.substring(0, 15);
		}
		this.compCode = compCode;
    }

    public java.lang.String getStockHbu() {
		return (java.lang.String)HiltonUtility.ckNull(this.stockHbu).trim();
    }

    public void setStockHbu(java.lang.String stockHbu) {
		if (!HiltonUtility.isEmpty(stockHbu) && stockHbu.length() > 15) {
			stockHbu = stockHbu.substring(0, 15);
		}
		this.stockHbu = stockHbu;
    }

    public java.lang.String getSizeFlat() {
		return (java.lang.String)HiltonUtility.ckNull(this.sizeFlat).trim();
    }

    public void setSizeFlat(java.lang.String sizeFlat) {
		if (!HiltonUtility.isEmpty(sizeFlat) && sizeFlat.length() > 15) {
			sizeFlat = sizeFlat.substring(0, 15);
		}
		this.sizeFlat = sizeFlat;
    }

    public java.lang.String getCoverPrints() {
		return (java.lang.String)HiltonUtility.ckNull(this.coverPrints).trim();
    }

    public void setCoverPrints(java.lang.String coverPrints) {
		if (!HiltonUtility.isEmpty(coverPrints) && coverPrints.length() > 15) {
			coverPrints = coverPrints.substring(0, 15);
		}
		this.coverPrints = coverPrints;
    }

    public java.lang.String getBleeds() {
		return (java.lang.String)HiltonUtility.ckNull(this.bleeds).trim();
    }

    public void setBleeds(java.lang.String bleeds) {
		if (!HiltonUtility.isEmpty(bleeds) && bleeds.length() > 15) {
			bleeds = bleeds.substring(0, 15);
		}
		this.bleeds = bleeds;
    }

    public java.lang.String getProofs() {
		return (java.lang.String)HiltonUtility.ckNull(this.proofs).trim();
    }

    public void setProofs(java.lang.String proofs) {
		if (!HiltonUtility.isEmpty(proofs) && proofs.length() > 15) {
			proofs = proofs.substring(0, 15);
		}
		this.proofs = proofs;
    }

    public java.lang.String getFinishing() {
		return (java.lang.String)HiltonUtility.ckNull(this.finishing).trim();
    }

    public void setFinishing(java.lang.String finishing) {
		if (!HiltonUtility.isEmpty(finishing) && finishing.length() > 15) {
			finishing = finishing.substring(0, 15);
		}
		this.finishing = finishing;
    }

    public java.lang.String getTurnaround() {
		return (java.lang.String)HiltonUtility.ckNull(this.turnaround).trim();
    }

    public void setTurnaround(java.lang.String turnaround) {
		if (!HiltonUtility.isEmpty(turnaround) && turnaround.length() > 15) {
			turnaround = turnaround.substring(0, 15);
		}
		this.turnaround = turnaround;
    }

    public java.lang.String getFormUdf01() {
		return (java.lang.String)HiltonUtility.ckNull(this.formUdf01).trim();
    }

    public void setFormUdf01(java.lang.String formUdf01) {
		if (!HiltonUtility.isEmpty(formUdf01) && formUdf01.length() > 20) {
			formUdf01 = formUdf01.substring(0, 20);
		}
		this.formUdf01 = formUdf01;
    }

    public java.lang.String getFormUdf02() {
		return (java.lang.String)HiltonUtility.ckNull(this.formUdf02).trim();
    }

    public void setFormUdf02(java.lang.String formUdf02) {
		if (!HiltonUtility.isEmpty(formUdf02) && formUdf02.length() > 20) {
			formUdf02 = formUdf02.substring(0, 20);
		}
		this.formUdf02 = formUdf02;
    }

    public java.lang.String getSpecUdf01() {
		return (java.lang.String)HiltonUtility.ckNull(this.specUdf01).trim();
    }

    public void setSpecUdf01(java.lang.String specUdf01) {
		if (!HiltonUtility.isEmpty(specUdf01) && specUdf01.length() > 20) {
			specUdf01 = specUdf01.substring(0, 20);
		}
		this.specUdf01 = specUdf01;
    }

    public java.lang.String getSpecUdf02() {
		return (java.lang.String)HiltonUtility.ckNull(this.specUdf02).trim();
    }

    public void setSpecUdf02(java.lang.String specUdf02) {
		if (!HiltonUtility.isEmpty(specUdf02) && specUdf02.length() > 20) {
			specUdf02 = specUdf02.substring(0, 20);
		}
		this.specUdf02 = specUdf02;
    }

    public java.lang.String getSpecUdf03() {
		return (java.lang.String)HiltonUtility.ckNull(this.specUdf03).trim();
    }

    public void setSpecUdf03(java.lang.String specUdf03) {
		if (!HiltonUtility.isEmpty(specUdf03) && specUdf03.length() > 20) {
			specUdf03 = specUdf03.substring(0, 20);
		}
		this.specUdf03 = specUdf03;
    }

    public java.lang.String getSpecNotes() {
		return (java.lang.String)HiltonUtility.ckNull(this.specNotes).trim();
    }

    public void setSpecNotes(java.lang.String specNotes) {
		if (!HiltonUtility.isEmpty(specNotes) && specNotes.length() > 1000) {
			specNotes = specNotes.substring(0, 1000);
		}
		this.specNotes = specNotes;
    }

    public java.lang.String getFormAddress() {
		return (java.lang.String)HiltonUtility.ckNull(this.formAddress).trim();
    }

    public void setFormAddress(java.lang.String formAddress) {
		if (!HiltonUtility.isEmpty(formAddress) && formAddress.length() > 40) {
			formAddress = formAddress.substring(0, 40);
		}
		this.formAddress = formAddress;
    }

    public java.math.BigDecimal getMaxReqQty() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.maxReqQty);
    }

    public void setMaxReqQty(java.math.BigDecimal maxReqQty) {
        this.maxReqQty = maxReqQty;
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

    public java.lang.String getEquivalentStock() {
		return (java.lang.String)HiltonUtility.ckNull(this.equivalentStock).trim();
    }

    public void setEquivalentStock(java.lang.String equivalentStock) {
		if (!HiltonUtility.isEmpty(equivalentStock) && equivalentStock.length() > 1) {
			equivalentStock = equivalentStock.substring(0, 1);
		}
		this.equivalentStock = equivalentStock;
    }

    public java.lang.String getAutoReprint() {
		return (java.lang.String)HiltonUtility.ckNull(this.autoReprint).trim();
    }

    public void setAutoReprint(java.lang.String autoReprint) {
		if (!HiltonUtility.isEmpty(autoReprint) && autoReprint.length() > 1) {
			autoReprint = autoReprint.substring(0, 1);
		}
		this.autoReprint = autoReprint;
    }

    public java.lang.String getInprintableSpace() {
		return (java.lang.String)HiltonUtility.ckNull(this.inprintableSpace).trim();
    }

    public void setInprintableSpace(java.lang.String inprintableSpace) {
		if (!HiltonUtility.isEmpty(inprintableSpace) && inprintableSpace.length() > 1) {
			inprintableSpace = inprintableSpace.substring(0, 1);
		}
		this.inprintableSpace = inprintableSpace;
    }

    public java.lang.String getFormTraps() {
		return (java.lang.String)HiltonUtility.ckNull(this.formTraps).trim();
    }

    public void setFormTraps(java.lang.String formTraps) {
		if (!HiltonUtility.isEmpty(formTraps) && formTraps.length() > 1) {
			formTraps = formTraps.substring(0, 1);
		}
		this.formTraps = formTraps;
    }

    public java.lang.String getColorSeparation() {
		return (java.lang.String)HiltonUtility.ckNull(this.colorSeparation).trim();
    }

    public void setColorSeparation(java.lang.String colorSeparation) {
		if (!HiltonUtility.isEmpty(colorSeparation) && colorSeparation.length() > 15) {
			colorSeparation = colorSeparation.substring(0, 15);
		}
		this.colorSeparation = colorSeparation;
    }

    public java.lang.String getSupInstruction() {
		return (java.lang.String)HiltonUtility.ckNull(this.supInstruction).trim();
    }

    public void setSupInstruction(java.lang.String supInstruction) {
		if (!HiltonUtility.isEmpty(supInstruction) && supInstruction.length() > 255) {
			supInstruction = supInstruction.substring(0, 255);
		}
		this.supInstruction = supInstruction;
    }

    public java.lang.String getUseType() {
		return (java.lang.String)HiltonUtility.ckNull(this.useType).trim();
    }

    public void setUseType(java.lang.String useType) {
		if (!HiltonUtility.isEmpty(useType) && useType.length() > 15) {
			useType = useType.substring(0, 15);
		}
		this.useType = useType;
    }

    public java.util.Date getMailDate() {
		return this.mailDate;
//		return HiltonUtility.ckNull(this.mailDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.mailDate);
    }

    public void setMailDate(java.util.Date mailDate) {
        this.mailDate = mailDate;
    }

    public java.lang.String getEnvLabel() {
		return (java.lang.String)HiltonUtility.ckNull(this.envLabel).trim();
    }

    public void setEnvLabel(java.lang.String envLabel) {
		if (!HiltonUtility.isEmpty(envLabel) && envLabel.length() > 15) {
			envLabel = envLabel.substring(0, 15);
		}
		this.envLabel = envLabel;
    }

    public java.lang.String getEnvColor() {
		return (java.lang.String)HiltonUtility.ckNull(this.envColor).trim();
    }

    public void setEnvColor(java.lang.String envColor) {
		if (!HiltonUtility.isEmpty(envColor) && envColor.length() > 15) {
			envColor = envColor.substring(0, 15);
		}
		this.envColor = envColor;
    }

    public java.lang.String getEnvWeight() {
		return (java.lang.String)HiltonUtility.ckNull(this.envWeight).trim();
    }

    public void setEnvWeight(java.lang.String envWeight) {
		if (!HiltonUtility.isEmpty(envWeight) && envWeight.length() > 15) {
			envWeight = envWeight.substring(0, 15);
		}
		this.envWeight = envWeight;
    }

    public java.lang.String getEnvStock() {
		return (java.lang.String)HiltonUtility.ckNull(this.envStock).trim();
    }

    public void setEnvStock(java.lang.String envStock) {
		if (!HiltonUtility.isEmpty(envStock) && envStock.length() > 15) {
			envStock = envStock.substring(0, 15);
		}
		this.envStock = envStock;
    }

    public java.lang.String getEnvInk() {
		return (java.lang.String)HiltonUtility.ckNull(this.envInk).trim();
    }

    public void setEnvInk(java.lang.String envInk) {
		if (!HiltonUtility.isEmpty(envInk) && envInk.length() > 15) {
			envInk = envInk.substring(0, 15);
		}
		this.envInk = envInk;
    }

    public java.lang.String getEnvOther() {
		return (java.lang.String)HiltonUtility.ckNull(this.envOther).trim();
    }

    public void setEnvOther(java.lang.String envOther) {
		if (!HiltonUtility.isEmpty(envOther) && envOther.length() > 15) {
			envOther = envOther.substring(0, 15);
		}
		this.envOther = envOther;
    }

    public java.util.Date getDateOut() {
		return this.dateOut;
//		return HiltonUtility.ckNull(this.dateOut);
//		return (java.sql.Date)HiltonUtility.ckNull(this.dateOut);
    }

    public void setDateOut(java.util.Date dateOut) {
        this.dateOut = dateOut;
    }

    public java.util.Date getDocPrtDate() {
		return this.docPrtDate;
//		return HiltonUtility.ckNull(this.docPrtDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.docPrtDate);
    }

    public void setDocPrtDate(java.util.Date docPrtDate) {
        this.docPrtDate = docPrtDate;
    }

    public java.util.Date getPuAppDate() {
		return this.puAppDate;
//		return HiltonUtility.ckNull(this.puAppDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.puAppDate);
    }

    public void setPuAppDate(java.util.Date puAppDate) {
        this.puAppDate = puAppDate;
    }

    public java.util.Date getFaAppDate() {
		return this.faAppDate;
//		return HiltonUtility.ckNull(this.faAppDate);
//		return (java.sql.Date)HiltonUtility.ckNull(this.faAppDate);
    }

    public void setFaAppDate(java.util.Date faAppDate) {
        this.faAppDate = faAppDate;
    }

    public java.lang.String getAutomaticReprint() {
		return (java.lang.String)HiltonUtility.ckNull(this.automaticReprint).trim();
    }

    public void setAutomaticReprint(java.lang.String automaticReprint) {
		if (!HiltonUtility.isEmpty(automaticReprint) && automaticReprint.length() > 1) {
			automaticReprint = automaticReprint.substring(0, 1);
		}
		this.automaticReprint = automaticReprint;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("itemNumber", getItemNumber())
            .toString();
    }

    public boolean equals(Object other) {
        if ( !(other instanceof InvFormData) ) return false;
        InvFormData castOther = (InvFormData) other;
        return new EqualsBuilder()
            .append(this.getItemNumber(), castOther.getItemNumber())
            .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder()
            .append(getItemNumber())
            .toHashCode();
    }

}
