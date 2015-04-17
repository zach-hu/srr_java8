/**
 * BasicLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class BasicLine  implements java.io.Serializable {
    /* The document line's
     *                         position in the document. */
    private int number;

    /* A time stamp
     *                     value. */
    private java.lang.Short timeStamp;

    /* The
     *                         InterCompany destination code for the document
     * line (if any). */
    private java.lang.String destCode;

    /* The
     *                         account code for the document
     *                     line. */
    private java.lang.String accountCode;

    /* The
     *                         code of a temporary trader for the document
     * line. */
    private java.lang.String traderCode;

    /* The value of the line in the
     *                         document currency. */
    private java.math.BigDecimal docValue;

    /* The exchange rate between the
     *                         document currency and either its parent or
     * the
     *                         home currency, whichever
     *                     applies. */
    private java.math.BigDecimal docRate;

    /* The value of the line in the
     *                         home currency. */
    private java.math.BigDecimal homeValue;

    /* The unrounded value of the
     *                         line in home currency (displaying all decimal
     * places). */
    private java.math.BigDecimal homeFullValue;

    /* The value of the line in the
     *                         dual currency. */
    private java.math.BigDecimal dualValue;

    /* The unrounded value of the
     *                         line in dual currency (displaying all decimal
     * places). */
    private java.math.BigDecimal dualFullValue;

    /* The exchange rate between the
     *                         dual currency and either its parent or the
     * home
     *                         currency, whichever applies. */
    private java.math.BigDecimal dualRate;

    /* The
     *                         currency code identifying the parent currency
     * on
     *                         the document line, if any. */
    private java.lang.String parentCurrency;

    /* The exchange rate between the
     *                         parent currency and the home
     *                     currency. */
    private java.math.BigDecimal parentRate;

    /* The value of the document in
     *                         the parent currency. */
    private java.math.BigDecimal parentValue;

    /* The unrounded value of the
     *                         line in parent currency (displaying all decimal
     * places). */
    private java.math.BigDecimal parentFullValue;

    /* The user-defined status value
     *                         for the document line. */
    private java.lang.String userStatus;

    /* The
     *                         type of the document line. */
    private java.lang.String lineType;

    /* The
     *                         sense of the document line. */
    private java.lang.String lineSense;

    /* Specifies the origin of a
     *                         document line in Financials
     *                     Input. */
    private java.lang.String lineOrigin;

    /* User's
     *                         description/comment for the document
     *                     line. */
    private java.lang.String description;

    /* The
     *                         due date for the document
     *                     line. */
    private java.util.Calendar dueDate;

    /* A
     *                         valid soft date determining the due date for
     * recurring documents. */
    private java.lang.String dueTerms;

    /* The value date for the
     *                         document line. */
    private java.util.Calendar valueDate;

    /* A valid soft date determining
     *                         the value date for recurring
     *                     documents. */
    private java.lang.String valueTerms;

    /* External reference
     *                     1. */
    private java.lang.String extRef1;

    /* External reference
     *                     2. */
    private java.lang.String extRef2;

    /* External reference
     *                     3. */
    private java.lang.String extRef3;

    /* External reference
     *                     4. */
    private java.lang.String extRef4;

    /* External reference
     *                     5. */
    private java.lang.String extRef5;

    /* External reference
     *                     6. */
    private java.lang.String extRef6;

    /* The
     *                         total amount of tax on the document. This
     *                         applies only to customer/supplier
     *                     lines. */
    private java.math.BigDecimal docSumTax;

    /* The
     *                         tax code on the document line (applies only
     * to
     *                         tax lines). */
    private java.lang.String taxLineCode;

    /* The tax turnover on a tax
     *                         line (that is, the sum of document currency
     * values of the lines that contribute to the tax
     *                         being paid on the current
     *                     line). */
    private java.math.BigDecimal docTaxTurnover;

    /* Specifies whether the line
     *                         value in the document currency is inclusive
     * of
     *                     tax. */
    private java.lang.Boolean taxInclusive;

    /* The
     *                         code for the media master for the matchable
     * element (if any). */
    private java.lang.String mediaCode;

    /* The bank code identifying
     *                         which of the current company's banks to use
     * for this document line. */
    private java.lang.String bankCode;

    /* Specifies whether the payment
     *                         system can summarise the document
     *                     line. */
    private java.lang.Boolean disableSummaries;

    /* The
     *                         tag identifier for the element bank for the
     * customer/supplier element on this document
     *                     line. */
    private java.lang.Short elmBankTag;

    /* The
     *                         tag identifier for the element address for
     * the
     *                         customer/supplier element on this document
     * line. */
    private java.lang.Short elmAddrTag;

    /* User
     *                         reference 1. */
    private java.lang.String userRef1;

    /* User reference
     *                     2. */
    private java.lang.String userRef2;

    /* User
     *                         reference 3. */
    private java.lang.String userRef3;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 1. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities1;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 2. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities2;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 3. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities3;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 4. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities4;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 5. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities5;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 6. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities6;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 7. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities7;

    /* This
     *                         element holds the quantity information for
     * the
     *                         element at level 8. */
    private com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities8;

    /* The
     *                         payment status of the document
     *                     line. */
    private java.lang.String payStatus;

    /* The
     *                         payment date of the document
     *                     line. */
    private java.util.Calendar payDate;

    /* The payment number assigned
     *                         to the document line. */
    private java.lang.Integer payNumber;

    /* The reconciliation status of
     *                         the document line. */
    private java.lang.String recStatus;

    /* The reconciliation number
     *                         assigned to the document
     *                     line. */
    private java.lang.Integer recNumber;

    /* The
     *                         value of any part payment on this document
     * line. */
    private java.math.BigDecimal partPayment;

    /* The
     *                         total tax on the document line, in home
     *                     currency. */
    private java.math.BigDecimal homeSumTax;

    /* The
     *                         element level in the account code at which
     * the
     *                         matchable element on this document line
     *                     occurs. */
    private java.lang.Short matchableElmLevel;

    /* The method of calculating tax
     *                         when using this document
     *                     master. */
    private java.lang.String taxMethod;

    /* This element holds details of
     *                         the workflow attached to this document
     *                     line. */
    private com.coda.www.efinance.schemas.transaction.LineWorkflow workflow;

    /* The
     *                         account number of the matchable element's
     *                         bank account. */
    private java.lang.String elmBankAccount;

    /* The
     *                         name of the matchable element's
     *                     address. */
    private java.lang.String elmAddrName;

    /* Indicates whether discount
     *                         information can be held for this document
     *                     line. */
    private java.lang.Boolean discsAllowed;

    /* Indicates whether discount
     *                         for this element is calculated gross of tax,
     * net
     *                         of tax or not allowed. */
    private java.lang.String calcDisc;

    /* Indicates whether the
     *                         matchable element is an umbrella element,
     * and
     *                         therefore whether a temporary supplier or
     * trader
     *                         is in use for that element.. */
    private java.lang.Boolean matchableElmTemporary;

    /* Indicates whether the account
     *                         code is valid. */
    private java.lang.Boolean accCodeValid;

    /* Indicates whether the
     *                         matchable element is a customer or supplier
     * element. */
    private java.lang.Boolean customerSupplier;

    /* This element holds the
     *                         element currency information for the document
     * line. */
    private com.coda.www.efinance.schemas.transaction.Currency[] currencies;

    /* This element holds the tax
     *                         information for the document
     *                     line. */
    private com.coda.www.efinance.schemas.transaction.Tax[] taxes;

    /* This
     *                         element holds the 1099 tax information for
     * the
     *                         document line. */
    private com.coda.www.efinance.schemas.transaction.Tax[] ten99Taxes;

    /* This element holds the
     *                         discount information for the document
     *                     line. */
    private com.coda.www.efinance.schemas.transaction.Discount[] discounts;

    /* This
     *                         element holds details of discount rates that
     * are
     *                         available if this document is a recurring
     *                     document. */
    private com.coda.www.efinance.schemas.transaction.RecurringDiscount[] recurringDiscounts;

    /* The value of the line number
     *                         of the document stored in the
     *                     Intray. */
    private java.lang.Integer DBLineNum;

    /* Indicates whether this line
     *                         is matched against an existing line in the
     * database. This line will be made read-only if it
     *                         is involved in a match. */
    private java.lang.Boolean involvedInMatch;

    /* This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted. */
    private com.coda.www.efinance.schemas.transaction.Asset asset;

    /* This element holds the
     *                         withholding tax code used in the Italian
     *                         localisation for Financials. */
    private java.lang.String extensionData;

    /* This element holds
     *                         information about irrecoverable tax passed
     * from
     *                         Invoice Matching to
     *                     Purchasing. */
    private java.lang.String extensionData2;

    public BasicLine() {
    }

    public BasicLine(
           int number,
           java.lang.Short timeStamp,
           java.lang.String destCode,
           java.lang.String accountCode,
           java.lang.String traderCode,
           java.math.BigDecimal docValue,
           java.math.BigDecimal docRate,
           java.math.BigDecimal homeValue,
           java.math.BigDecimal homeFullValue,
           java.math.BigDecimal dualValue,
           java.math.BigDecimal dualFullValue,
           java.math.BigDecimal dualRate,
           java.lang.String parentCurrency,
           java.math.BigDecimal parentRate,
           java.math.BigDecimal parentValue,
           java.math.BigDecimal parentFullValue,
           java.lang.String userStatus,
           java.lang.String lineType,
           java.lang.String lineSense,
           java.lang.String lineOrigin,
           java.lang.String description,
           java.util.Calendar dueDate,
           java.lang.String dueTerms,
           java.util.Calendar valueDate,
           java.lang.String valueTerms,
           java.lang.String extRef1,
           java.lang.String extRef2,
           java.lang.String extRef3,
           java.lang.String extRef4,
           java.lang.String extRef5,
           java.lang.String extRef6,
           java.math.BigDecimal docSumTax,
           java.lang.String taxLineCode,
           java.math.BigDecimal docTaxTurnover,
           java.lang.Boolean taxInclusive,
           java.lang.String mediaCode,
           java.lang.String bankCode,
           java.lang.Boolean disableSummaries,
           java.lang.Short elmBankTag,
           java.lang.Short elmAddrTag,
           java.lang.String userRef1,
           java.lang.String userRef2,
           java.lang.String userRef3,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities1,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities2,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities3,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities4,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities5,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities6,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities7,
           com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities8,
           java.lang.String payStatus,
           java.util.Calendar payDate,
           java.lang.Integer payNumber,
           java.lang.String recStatus,
           java.lang.Integer recNumber,
           java.math.BigDecimal partPayment,
           java.math.BigDecimal homeSumTax,
           java.lang.Short matchableElmLevel,
           java.lang.String taxMethod,
           com.coda.www.efinance.schemas.transaction.LineWorkflow workflow,
           java.lang.String elmBankAccount,
           java.lang.String elmAddrName,
           java.lang.Boolean discsAllowed,
           java.lang.String calcDisc,
           java.lang.Boolean matchableElmTemporary,
           java.lang.Boolean accCodeValid,
           java.lang.Boolean customerSupplier,
           com.coda.www.efinance.schemas.transaction.Currency[] currencies,
           com.coda.www.efinance.schemas.transaction.Tax[] taxes,
           com.coda.www.efinance.schemas.transaction.Tax[] ten99Taxes,
           com.coda.www.efinance.schemas.transaction.Discount[] discounts,
           com.coda.www.efinance.schemas.transaction.RecurringDiscount[] recurringDiscounts,
           java.lang.Integer DBLineNum,
           java.lang.Boolean involvedInMatch,
           com.coda.www.efinance.schemas.transaction.Asset asset,
           java.lang.String extensionData,
           java.lang.String extensionData2) {
           this.number = number;
           this.timeStamp = timeStamp;
           this.destCode = destCode;
           this.accountCode = accountCode;
           this.traderCode = traderCode;
           this.docValue = docValue;
           this.docRate = docRate;
           this.homeValue = homeValue;
           this.homeFullValue = homeFullValue;
           this.dualValue = dualValue;
           this.dualFullValue = dualFullValue;
           this.dualRate = dualRate;
           this.parentCurrency = parentCurrency;
           this.parentRate = parentRate;
           this.parentValue = parentValue;
           this.parentFullValue = parentFullValue;
           this.userStatus = userStatus;
           this.lineType = lineType;
           this.lineSense = lineSense;
           this.lineOrigin = lineOrigin;
           this.description = description;
           this.dueDate = dueDate;
           this.dueTerms = dueTerms;
           this.valueDate = valueDate;
           this.valueTerms = valueTerms;
           this.extRef1 = extRef1;
           this.extRef2 = extRef2;
           this.extRef3 = extRef3;
           this.extRef4 = extRef4;
           this.extRef5 = extRef5;
           this.extRef6 = extRef6;
           this.docSumTax = docSumTax;
           this.taxLineCode = taxLineCode;
           this.docTaxTurnover = docTaxTurnover;
           this.taxInclusive = taxInclusive;
           this.mediaCode = mediaCode;
           this.bankCode = bankCode;
           this.disableSummaries = disableSummaries;
           this.elmBankTag = elmBankTag;
           this.elmAddrTag = elmAddrTag;
           this.userRef1 = userRef1;
           this.userRef2 = userRef2;
           this.userRef3 = userRef3;
           this.elmQuantities1 = elmQuantities1;
           this.elmQuantities2 = elmQuantities2;
           this.elmQuantities3 = elmQuantities3;
           this.elmQuantities4 = elmQuantities4;
           this.elmQuantities5 = elmQuantities5;
           this.elmQuantities6 = elmQuantities6;
           this.elmQuantities7 = elmQuantities7;
           this.elmQuantities8 = elmQuantities8;
           this.payStatus = payStatus;
           this.payDate = payDate;
           this.payNumber = payNumber;
           this.recStatus = recStatus;
           this.recNumber = recNumber;
           this.partPayment = partPayment;
           this.homeSumTax = homeSumTax;
           this.matchableElmLevel = matchableElmLevel;
           this.taxMethod = taxMethod;
           this.workflow = workflow;
           this.elmBankAccount = elmBankAccount;
           this.elmAddrName = elmAddrName;
           this.discsAllowed = discsAllowed;
           this.calcDisc = calcDisc;
           this.matchableElmTemporary = matchableElmTemporary;
           this.accCodeValid = accCodeValid;
           this.customerSupplier = customerSupplier;
           this.currencies = currencies;
           this.taxes = taxes;
           this.ten99Taxes = ten99Taxes;
           this.discounts = discounts;
           this.recurringDiscounts = recurringDiscounts;
           this.DBLineNum = DBLineNum;
           this.involvedInMatch = involvedInMatch;
           this.asset = asset;
           this.extensionData = extensionData;
           this.extensionData2 = extensionData2;
    }


    /**
     * Gets the number value for this BasicLine.
     * 
     * @return number   * The document line's
     *                         position in the document.
     */
    public int getNumber() {
        return number;
    }


    /**
     * Sets the number value for this BasicLine.
     * 
     * @param number   * The document line's
     *                         position in the document.
     */
    public void setNumber(int number) {
        this.number = number;
    }


    /**
     * Gets the timeStamp value for this BasicLine.
     * 
     * @return timeStamp   * A time stamp
     *                     value.
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this BasicLine.
     * 
     * @param timeStamp   * A time stamp
     *                     value.
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the destCode value for this BasicLine.
     * 
     * @return destCode   * The
     *                         InterCompany destination code for the document
     * line (if any).
     */
    public java.lang.String getDestCode() {
        return destCode;
    }


    /**
     * Sets the destCode value for this BasicLine.
     * 
     * @param destCode   * The
     *                         InterCompany destination code for the document
     * line (if any).
     */
    public void setDestCode(java.lang.String destCode) {
        this.destCode = destCode;
    }


    /**
     * Gets the accountCode value for this BasicLine.
     * 
     * @return accountCode   * The
     *                         account code for the document
     *                     line.
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this BasicLine.
     * 
     * @param accountCode   * The
     *                         account code for the document
     *                     line.
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the traderCode value for this BasicLine.
     * 
     * @return traderCode   * The
     *                         code of a temporary trader for the document
     * line.
     */
    public java.lang.String getTraderCode() {
        return traderCode;
    }


    /**
     * Sets the traderCode value for this BasicLine.
     * 
     * @param traderCode   * The
     *                         code of a temporary trader for the document
     * line.
     */
    public void setTraderCode(java.lang.String traderCode) {
        this.traderCode = traderCode;
    }


    /**
     * Gets the docValue value for this BasicLine.
     * 
     * @return docValue   * The value of the line in the
     *                         document currency.
     */
    public java.math.BigDecimal getDocValue() {
        return docValue;
    }


    /**
     * Sets the docValue value for this BasicLine.
     * 
     * @param docValue   * The value of the line in the
     *                         document currency.
     */
    public void setDocValue(java.math.BigDecimal docValue) {
        this.docValue = docValue;
    }


    /**
     * Gets the docRate value for this BasicLine.
     * 
     * @return docRate   * The exchange rate between the
     *                         document currency and either its parent or
     * the
     *                         home currency, whichever
     *                     applies.
     */
    public java.math.BigDecimal getDocRate() {
        return docRate;
    }


    /**
     * Sets the docRate value for this BasicLine.
     * 
     * @param docRate   * The exchange rate between the
     *                         document currency and either its parent or
     * the
     *                         home currency, whichever
     *                     applies.
     */
    public void setDocRate(java.math.BigDecimal docRate) {
        this.docRate = docRate;
    }


    /**
     * Gets the homeValue value for this BasicLine.
     * 
     * @return homeValue   * The value of the line in the
     *                         home currency.
     */
    public java.math.BigDecimal getHomeValue() {
        return homeValue;
    }


    /**
     * Sets the homeValue value for this BasicLine.
     * 
     * @param homeValue   * The value of the line in the
     *                         home currency.
     */
    public void setHomeValue(java.math.BigDecimal homeValue) {
        this.homeValue = homeValue;
    }


    /**
     * Gets the homeFullValue value for this BasicLine.
     * 
     * @return homeFullValue   * The unrounded value of the
     *                         line in home currency (displaying all decimal
     * places).
     */
    public java.math.BigDecimal getHomeFullValue() {
        return homeFullValue;
    }


    /**
     * Sets the homeFullValue value for this BasicLine.
     * 
     * @param homeFullValue   * The unrounded value of the
     *                         line in home currency (displaying all decimal
     * places).
     */
    public void setHomeFullValue(java.math.BigDecimal homeFullValue) {
        this.homeFullValue = homeFullValue;
    }


    /**
     * Gets the dualValue value for this BasicLine.
     * 
     * @return dualValue   * The value of the line in the
     *                         dual currency.
     */
    public java.math.BigDecimal getDualValue() {
        return dualValue;
    }


    /**
     * Sets the dualValue value for this BasicLine.
     * 
     * @param dualValue   * The value of the line in the
     *                         dual currency.
     */
    public void setDualValue(java.math.BigDecimal dualValue) {
        this.dualValue = dualValue;
    }


    /**
     * Gets the dualFullValue value for this BasicLine.
     * 
     * @return dualFullValue   * The unrounded value of the
     *                         line in dual currency (displaying all decimal
     * places).
     */
    public java.math.BigDecimal getDualFullValue() {
        return dualFullValue;
    }


    /**
     * Sets the dualFullValue value for this BasicLine.
     * 
     * @param dualFullValue   * The unrounded value of the
     *                         line in dual currency (displaying all decimal
     * places).
     */
    public void setDualFullValue(java.math.BigDecimal dualFullValue) {
        this.dualFullValue = dualFullValue;
    }


    /**
     * Gets the dualRate value for this BasicLine.
     * 
     * @return dualRate   * The exchange rate between the
     *                         dual currency and either its parent or the
     * home
     *                         currency, whichever applies.
     */
    public java.math.BigDecimal getDualRate() {
        return dualRate;
    }


    /**
     * Sets the dualRate value for this BasicLine.
     * 
     * @param dualRate   * The exchange rate between the
     *                         dual currency and either its parent or the
     * home
     *                         currency, whichever applies.
     */
    public void setDualRate(java.math.BigDecimal dualRate) {
        this.dualRate = dualRate;
    }


    /**
     * Gets the parentCurrency value for this BasicLine.
     * 
     * @return parentCurrency   * The
     *                         currency code identifying the parent currency
     * on
     *                         the document line, if any.
     */
    public java.lang.String getParentCurrency() {
        return parentCurrency;
    }


    /**
     * Sets the parentCurrency value for this BasicLine.
     * 
     * @param parentCurrency   * The
     *                         currency code identifying the parent currency
     * on
     *                         the document line, if any.
     */
    public void setParentCurrency(java.lang.String parentCurrency) {
        this.parentCurrency = parentCurrency;
    }


    /**
     * Gets the parentRate value for this BasicLine.
     * 
     * @return parentRate   * The exchange rate between the
     *                         parent currency and the home
     *                     currency.
     */
    public java.math.BigDecimal getParentRate() {
        return parentRate;
    }


    /**
     * Sets the parentRate value for this BasicLine.
     * 
     * @param parentRate   * The exchange rate between the
     *                         parent currency and the home
     *                     currency.
     */
    public void setParentRate(java.math.BigDecimal parentRate) {
        this.parentRate = parentRate;
    }


    /**
     * Gets the parentValue value for this BasicLine.
     * 
     * @return parentValue   * The value of the document in
     *                         the parent currency.
     */
    public java.math.BigDecimal getParentValue() {
        return parentValue;
    }


    /**
     * Sets the parentValue value for this BasicLine.
     * 
     * @param parentValue   * The value of the document in
     *                         the parent currency.
     */
    public void setParentValue(java.math.BigDecimal parentValue) {
        this.parentValue = parentValue;
    }


    /**
     * Gets the parentFullValue value for this BasicLine.
     * 
     * @return parentFullValue   * The unrounded value of the
     *                         line in parent currency (displaying all decimal
     * places).
     */
    public java.math.BigDecimal getParentFullValue() {
        return parentFullValue;
    }


    /**
     * Sets the parentFullValue value for this BasicLine.
     * 
     * @param parentFullValue   * The unrounded value of the
     *                         line in parent currency (displaying all decimal
     * places).
     */
    public void setParentFullValue(java.math.BigDecimal parentFullValue) {
        this.parentFullValue = parentFullValue;
    }


    /**
     * Gets the userStatus value for this BasicLine.
     * 
     * @return userStatus   * The user-defined status value
     *                         for the document line.
     */
    public java.lang.String getUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this BasicLine.
     * 
     * @param userStatus   * The user-defined status value
     *                         for the document line.
     */
    public void setUserStatus(java.lang.String userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the lineType value for this BasicLine.
     * 
     * @return lineType   * The
     *                         type of the document line.
     */
    public java.lang.String getLineType() {
        return lineType;
    }


    /**
     * Sets the lineType value for this BasicLine.
     * 
     * @param lineType   * The
     *                         type of the document line.
     */
    public void setLineType(java.lang.String lineType) {
        this.lineType = lineType;
    }


    /**
     * Gets the lineSense value for this BasicLine.
     * 
     * @return lineSense   * The
     *                         sense of the document line.
     */
    public java.lang.String getLineSense() {
        return lineSense;
    }


    /**
     * Sets the lineSense value for this BasicLine.
     * 
     * @param lineSense   * The
     *                         sense of the document line.
     */
    public void setLineSense(java.lang.String lineSense) {
        this.lineSense = lineSense;
    }


    /**
     * Gets the lineOrigin value for this BasicLine.
     * 
     * @return lineOrigin   * Specifies the origin of a
     *                         document line in Financials
     *                     Input.
     */
    public java.lang.String getLineOrigin() {
        return lineOrigin;
    }


    /**
     * Sets the lineOrigin value for this BasicLine.
     * 
     * @param lineOrigin   * Specifies the origin of a
     *                         document line in Financials
     *                     Input.
     */
    public void setLineOrigin(java.lang.String lineOrigin) {
        this.lineOrigin = lineOrigin;
    }


    /**
     * Gets the description value for this BasicLine.
     * 
     * @return description   * User's
     *                         description/comment for the document
     *                     line.
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this BasicLine.
     * 
     * @param description   * User's
     *                         description/comment for the document
     *                     line.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the dueDate value for this BasicLine.
     * 
     * @return dueDate   * The
     *                         due date for the document
     *                     line.
     */
    public java.util.Calendar getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this BasicLine.
     * 
     * @param dueDate   * The
     *                         due date for the document
     *                     line.
     */
    public void setDueDate(java.util.Calendar dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the dueTerms value for this BasicLine.
     * 
     * @return dueTerms   * A
     *                         valid soft date determining the due date for
     * recurring documents.
     */
    public java.lang.String getDueTerms() {
        return dueTerms;
    }


    /**
     * Sets the dueTerms value for this BasicLine.
     * 
     * @param dueTerms   * A
     *                         valid soft date determining the due date for
     * recurring documents.
     */
    public void setDueTerms(java.lang.String dueTerms) {
        this.dueTerms = dueTerms;
    }


    /**
     * Gets the valueDate value for this BasicLine.
     * 
     * @return valueDate   * The value date for the
     *                         document line.
     */
    public java.util.Calendar getValueDate() {
        return valueDate;
    }


    /**
     * Sets the valueDate value for this BasicLine.
     * 
     * @param valueDate   * The value date for the
     *                         document line.
     */
    public void setValueDate(java.util.Calendar valueDate) {
        this.valueDate = valueDate;
    }


    /**
     * Gets the valueTerms value for this BasicLine.
     * 
     * @return valueTerms   * A valid soft date determining
     *                         the value date for recurring
     *                     documents.
     */
    public java.lang.String getValueTerms() {
        return valueTerms;
    }


    /**
     * Sets the valueTerms value for this BasicLine.
     * 
     * @param valueTerms   * A valid soft date determining
     *                         the value date for recurring
     *                     documents.
     */
    public void setValueTerms(java.lang.String valueTerms) {
        this.valueTerms = valueTerms;
    }


    /**
     * Gets the extRef1 value for this BasicLine.
     * 
     * @return extRef1   * External reference
     *                     1.
     */
    public java.lang.String getExtRef1() {
        return extRef1;
    }


    /**
     * Sets the extRef1 value for this BasicLine.
     * 
     * @param extRef1   * External reference
     *                     1.
     */
    public void setExtRef1(java.lang.String extRef1) {
        this.extRef1 = extRef1;
    }


    /**
     * Gets the extRef2 value for this BasicLine.
     * 
     * @return extRef2   * External reference
     *                     2.
     */
    public java.lang.String getExtRef2() {
        return extRef2;
    }


    /**
     * Sets the extRef2 value for this BasicLine.
     * 
     * @param extRef2   * External reference
     *                     2.
     */
    public void setExtRef2(java.lang.String extRef2) {
        this.extRef2 = extRef2;
    }


    /**
     * Gets the extRef3 value for this BasicLine.
     * 
     * @return extRef3   * External reference
     *                     3.
     */
    public java.lang.String getExtRef3() {
        return extRef3;
    }


    /**
     * Sets the extRef3 value for this BasicLine.
     * 
     * @param extRef3   * External reference
     *                     3.
     */
    public void setExtRef3(java.lang.String extRef3) {
        this.extRef3 = extRef3;
    }


    /**
     * Gets the extRef4 value for this BasicLine.
     * 
     * @return extRef4   * External reference
     *                     4.
     */
    public java.lang.String getExtRef4() {
        return extRef4;
    }


    /**
     * Sets the extRef4 value for this BasicLine.
     * 
     * @param extRef4   * External reference
     *                     4.
     */
    public void setExtRef4(java.lang.String extRef4) {
        this.extRef4 = extRef4;
    }


    /**
     * Gets the extRef5 value for this BasicLine.
     * 
     * @return extRef5   * External reference
     *                     5.
     */
    public java.lang.String getExtRef5() {
        return extRef5;
    }


    /**
     * Sets the extRef5 value for this BasicLine.
     * 
     * @param extRef5   * External reference
     *                     5.
     */
    public void setExtRef5(java.lang.String extRef5) {
        this.extRef5 = extRef5;
    }


    /**
     * Gets the extRef6 value for this BasicLine.
     * 
     * @return extRef6   * External reference
     *                     6.
     */
    public java.lang.String getExtRef6() {
        return extRef6;
    }


    /**
     * Sets the extRef6 value for this BasicLine.
     * 
     * @param extRef6   * External reference
     *                     6.
     */
    public void setExtRef6(java.lang.String extRef6) {
        this.extRef6 = extRef6;
    }


    /**
     * Gets the docSumTax value for this BasicLine.
     * 
     * @return docSumTax   * The
     *                         total amount of tax on the document. This
     *                         applies only to customer/supplier
     *                     lines.
     */
    public java.math.BigDecimal getDocSumTax() {
        return docSumTax;
    }


    /**
     * Sets the docSumTax value for this BasicLine.
     * 
     * @param docSumTax   * The
     *                         total amount of tax on the document. This
     *                         applies only to customer/supplier
     *                     lines.
     */
    public void setDocSumTax(java.math.BigDecimal docSumTax) {
        this.docSumTax = docSumTax;
    }


    /**
     * Gets the taxLineCode value for this BasicLine.
     * 
     * @return taxLineCode   * The
     *                         tax code on the document line (applies only
     * to
     *                         tax lines).
     */
    public java.lang.String getTaxLineCode() {
        return taxLineCode;
    }


    /**
     * Sets the taxLineCode value for this BasicLine.
     * 
     * @param taxLineCode   * The
     *                         tax code on the document line (applies only
     * to
     *                         tax lines).
     */
    public void setTaxLineCode(java.lang.String taxLineCode) {
        this.taxLineCode = taxLineCode;
    }


    /**
     * Gets the docTaxTurnover value for this BasicLine.
     * 
     * @return docTaxTurnover   * The tax turnover on a tax
     *                         line (that is, the sum of document currency
     * values of the lines that contribute to the tax
     *                         being paid on the current
     *                     line).
     */
    public java.math.BigDecimal getDocTaxTurnover() {
        return docTaxTurnover;
    }


    /**
     * Sets the docTaxTurnover value for this BasicLine.
     * 
     * @param docTaxTurnover   * The tax turnover on a tax
     *                         line (that is, the sum of document currency
     * values of the lines that contribute to the tax
     *                         being paid on the current
     *                     line).
     */
    public void setDocTaxTurnover(java.math.BigDecimal docTaxTurnover) {
        this.docTaxTurnover = docTaxTurnover;
    }


    /**
     * Gets the taxInclusive value for this BasicLine.
     * 
     * @return taxInclusive   * Specifies whether the line
     *                         value in the document currency is inclusive
     * of
     *                     tax.
     */
    public java.lang.Boolean getTaxInclusive() {
        return taxInclusive;
    }


    /**
     * Sets the taxInclusive value for this BasicLine.
     * 
     * @param taxInclusive   * Specifies whether the line
     *                         value in the document currency is inclusive
     * of
     *                     tax.
     */
    public void setTaxInclusive(java.lang.Boolean taxInclusive) {
        this.taxInclusive = taxInclusive;
    }


    /**
     * Gets the mediaCode value for this BasicLine.
     * 
     * @return mediaCode   * The
     *                         code for the media master for the matchable
     * element (if any).
     */
    public java.lang.String getMediaCode() {
        return mediaCode;
    }


    /**
     * Sets the mediaCode value for this BasicLine.
     * 
     * @param mediaCode   * The
     *                         code for the media master for the matchable
     * element (if any).
     */
    public void setMediaCode(java.lang.String mediaCode) {
        this.mediaCode = mediaCode;
    }


    /**
     * Gets the bankCode value for this BasicLine.
     * 
     * @return bankCode   * The bank code identifying
     *                         which of the current company's banks to use
     * for this document line.
     */
    public java.lang.String getBankCode() {
        return bankCode;
    }


    /**
     * Sets the bankCode value for this BasicLine.
     * 
     * @param bankCode   * The bank code identifying
     *                         which of the current company's banks to use
     * for this document line.
     */
    public void setBankCode(java.lang.String bankCode) {
        this.bankCode = bankCode;
    }


    /**
     * Gets the disableSummaries value for this BasicLine.
     * 
     * @return disableSummaries   * Specifies whether the payment
     *                         system can summarise the document
     *                     line.
     */
    public java.lang.Boolean getDisableSummaries() {
        return disableSummaries;
    }


    /**
     * Sets the disableSummaries value for this BasicLine.
     * 
     * @param disableSummaries   * Specifies whether the payment
     *                         system can summarise the document
     *                     line.
     */
    public void setDisableSummaries(java.lang.Boolean disableSummaries) {
        this.disableSummaries = disableSummaries;
    }


    /**
     * Gets the elmBankTag value for this BasicLine.
     * 
     * @return elmBankTag   * The
     *                         tag identifier for the element bank for the
     * customer/supplier element on this document
     *                     line.
     */
    public java.lang.Short getElmBankTag() {
        return elmBankTag;
    }


    /**
     * Sets the elmBankTag value for this BasicLine.
     * 
     * @param elmBankTag   * The
     *                         tag identifier for the element bank for the
     * customer/supplier element on this document
     *                     line.
     */
    public void setElmBankTag(java.lang.Short elmBankTag) {
        this.elmBankTag = elmBankTag;
    }


    /**
     * Gets the elmAddrTag value for this BasicLine.
     * 
     * @return elmAddrTag   * The
     *                         tag identifier for the element address for
     * the
     *                         customer/supplier element on this document
     * line.
     */
    public java.lang.Short getElmAddrTag() {
        return elmAddrTag;
    }


    /**
     * Sets the elmAddrTag value for this BasicLine.
     * 
     * @param elmAddrTag   * The
     *                         tag identifier for the element address for
     * the
     *                         customer/supplier element on this document
     * line.
     */
    public void setElmAddrTag(java.lang.Short elmAddrTag) {
        this.elmAddrTag = elmAddrTag;
    }


    /**
     * Gets the userRef1 value for this BasicLine.
     * 
     * @return userRef1   * User
     *                         reference 1.
     */
    public java.lang.String getUserRef1() {
        return userRef1;
    }


    /**
     * Sets the userRef1 value for this BasicLine.
     * 
     * @param userRef1   * User
     *                         reference 1.
     */
    public void setUserRef1(java.lang.String userRef1) {
        this.userRef1 = userRef1;
    }


    /**
     * Gets the userRef2 value for this BasicLine.
     * 
     * @return userRef2   * User reference
     *                     2.
     */
    public java.lang.String getUserRef2() {
        return userRef2;
    }


    /**
     * Sets the userRef2 value for this BasicLine.
     * 
     * @param userRef2   * User reference
     *                     2.
     */
    public void setUserRef2(java.lang.String userRef2) {
        this.userRef2 = userRef2;
    }


    /**
     * Gets the userRef3 value for this BasicLine.
     * 
     * @return userRef3   * User
     *                         reference 3.
     */
    public java.lang.String getUserRef3() {
        return userRef3;
    }


    /**
     * Sets the userRef3 value for this BasicLine.
     * 
     * @param userRef3   * User
     *                         reference 3.
     */
    public void setUserRef3(java.lang.String userRef3) {
        this.userRef3 = userRef3;
    }


    /**
     * Gets the elmQuantities1 value for this BasicLine.
     * 
     * @return elmQuantities1   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 1.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities1() {
        return elmQuantities1;
    }


    /**
     * Sets the elmQuantities1 value for this BasicLine.
     * 
     * @param elmQuantities1   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 1.
     */
    public void setElmQuantities1(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities1) {
        this.elmQuantities1 = elmQuantities1;
    }


    /**
     * Gets the elmQuantities2 value for this BasicLine.
     * 
     * @return elmQuantities2   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 2.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities2() {
        return elmQuantities2;
    }


    /**
     * Sets the elmQuantities2 value for this BasicLine.
     * 
     * @param elmQuantities2   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 2.
     */
    public void setElmQuantities2(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities2) {
        this.elmQuantities2 = elmQuantities2;
    }


    /**
     * Gets the elmQuantities3 value for this BasicLine.
     * 
     * @return elmQuantities3   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 3.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities3() {
        return elmQuantities3;
    }


    /**
     * Sets the elmQuantities3 value for this BasicLine.
     * 
     * @param elmQuantities3   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 3.
     */
    public void setElmQuantities3(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities3) {
        this.elmQuantities3 = elmQuantities3;
    }


    /**
     * Gets the elmQuantities4 value for this BasicLine.
     * 
     * @return elmQuantities4   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 4.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities4() {
        return elmQuantities4;
    }


    /**
     * Sets the elmQuantities4 value for this BasicLine.
     * 
     * @param elmQuantities4   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 4.
     */
    public void setElmQuantities4(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities4) {
        this.elmQuantities4 = elmQuantities4;
    }


    /**
     * Gets the elmQuantities5 value for this BasicLine.
     * 
     * @return elmQuantities5   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 5.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities5() {
        return elmQuantities5;
    }


    /**
     * Sets the elmQuantities5 value for this BasicLine.
     * 
     * @param elmQuantities5   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 5.
     */
    public void setElmQuantities5(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities5) {
        this.elmQuantities5 = elmQuantities5;
    }


    /**
     * Gets the elmQuantities6 value for this BasicLine.
     * 
     * @return elmQuantities6   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 6.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities6() {
        return elmQuantities6;
    }


    /**
     * Sets the elmQuantities6 value for this BasicLine.
     * 
     * @param elmQuantities6   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 6.
     */
    public void setElmQuantities6(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities6) {
        this.elmQuantities6 = elmQuantities6;
    }


    /**
     * Gets the elmQuantities7 value for this BasicLine.
     * 
     * @return elmQuantities7   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 7.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities7() {
        return elmQuantities7;
    }


    /**
     * Sets the elmQuantities7 value for this BasicLine.
     * 
     * @param elmQuantities7   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 7.
     */
    public void setElmQuantities7(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities7) {
        this.elmQuantities7 = elmQuantities7;
    }


    /**
     * Gets the elmQuantities8 value for this BasicLine.
     * 
     * @return elmQuantities8   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 8.
     */
    public com.coda.www.efinance.schemas.transaction.ElmQuantities getElmQuantities8() {
        return elmQuantities8;
    }


    /**
     * Sets the elmQuantities8 value for this BasicLine.
     * 
     * @param elmQuantities8   * This
     *                         element holds the quantity information for
     * the
     *                         element at level 8.
     */
    public void setElmQuantities8(com.coda.www.efinance.schemas.transaction.ElmQuantities elmQuantities8) {
        this.elmQuantities8 = elmQuantities8;
    }


    /**
     * Gets the payStatus value for this BasicLine.
     * 
     * @return payStatus   * The
     *                         payment status of the document
     *                     line.
     */
    public java.lang.String getPayStatus() {
        return payStatus;
    }


    /**
     * Sets the payStatus value for this BasicLine.
     * 
     * @param payStatus   * The
     *                         payment status of the document
     *                     line.
     */
    public void setPayStatus(java.lang.String payStatus) {
        this.payStatus = payStatus;
    }


    /**
     * Gets the payDate value for this BasicLine.
     * 
     * @return payDate   * The
     *                         payment date of the document
     *                     line.
     */
    public java.util.Calendar getPayDate() {
        return payDate;
    }


    /**
     * Sets the payDate value for this BasicLine.
     * 
     * @param payDate   * The
     *                         payment date of the document
     *                     line.
     */
    public void setPayDate(java.util.Calendar payDate) {
        this.payDate = payDate;
    }


    /**
     * Gets the payNumber value for this BasicLine.
     * 
     * @return payNumber   * The payment number assigned
     *                         to the document line.
     */
    public java.lang.Integer getPayNumber() {
        return payNumber;
    }


    /**
     * Sets the payNumber value for this BasicLine.
     * 
     * @param payNumber   * The payment number assigned
     *                         to the document line.
     */
    public void setPayNumber(java.lang.Integer payNumber) {
        this.payNumber = payNumber;
    }


    /**
     * Gets the recStatus value for this BasicLine.
     * 
     * @return recStatus   * The reconciliation status of
     *                         the document line.
     */
    public java.lang.String getRecStatus() {
        return recStatus;
    }


    /**
     * Sets the recStatus value for this BasicLine.
     * 
     * @param recStatus   * The reconciliation status of
     *                         the document line.
     */
    public void setRecStatus(java.lang.String recStatus) {
        this.recStatus = recStatus;
    }


    /**
     * Gets the recNumber value for this BasicLine.
     * 
     * @return recNumber   * The reconciliation number
     *                         assigned to the document
     *                     line.
     */
    public java.lang.Integer getRecNumber() {
        return recNumber;
    }


    /**
     * Sets the recNumber value for this BasicLine.
     * 
     * @param recNumber   * The reconciliation number
     *                         assigned to the document
     *                     line.
     */
    public void setRecNumber(java.lang.Integer recNumber) {
        this.recNumber = recNumber;
    }


    /**
     * Gets the partPayment value for this BasicLine.
     * 
     * @return partPayment   * The
     *                         value of any part payment on this document
     * line.
     */
    public java.math.BigDecimal getPartPayment() {
        return partPayment;
    }


    /**
     * Sets the partPayment value for this BasicLine.
     * 
     * @param partPayment   * The
     *                         value of any part payment on this document
     * line.
     */
    public void setPartPayment(java.math.BigDecimal partPayment) {
        this.partPayment = partPayment;
    }


    /**
     * Gets the homeSumTax value for this BasicLine.
     * 
     * @return homeSumTax   * The
     *                         total tax on the document line, in home
     *                     currency.
     */
    public java.math.BigDecimal getHomeSumTax() {
        return homeSumTax;
    }


    /**
     * Sets the homeSumTax value for this BasicLine.
     * 
     * @param homeSumTax   * The
     *                         total tax on the document line, in home
     *                     currency.
     */
    public void setHomeSumTax(java.math.BigDecimal homeSumTax) {
        this.homeSumTax = homeSumTax;
    }


    /**
     * Gets the matchableElmLevel value for this BasicLine.
     * 
     * @return matchableElmLevel   * The
     *                         element level in the account code at which
     * the
     *                         matchable element on this document line
     *                     occurs.
     */
    public java.lang.Short getMatchableElmLevel() {
        return matchableElmLevel;
    }


    /**
     * Sets the matchableElmLevel value for this BasicLine.
     * 
     * @param matchableElmLevel   * The
     *                         element level in the account code at which
     * the
     *                         matchable element on this document line
     *                     occurs.
     */
    public void setMatchableElmLevel(java.lang.Short matchableElmLevel) {
        this.matchableElmLevel = matchableElmLevel;
    }


    /**
     * Gets the taxMethod value for this BasicLine.
     * 
     * @return taxMethod   * The method of calculating tax
     *                         when using this document
     *                     master.
     */
    public java.lang.String getTaxMethod() {
        return taxMethod;
    }


    /**
     * Sets the taxMethod value for this BasicLine.
     * 
     * @param taxMethod   * The method of calculating tax
     *                         when using this document
     *                     master.
     */
    public void setTaxMethod(java.lang.String taxMethod) {
        this.taxMethod = taxMethod;
    }


    /**
     * Gets the workflow value for this BasicLine.
     * 
     * @return workflow   * This element holds details of
     *                         the workflow attached to this document
     *                     line.
     */
    public com.coda.www.efinance.schemas.transaction.LineWorkflow getWorkflow() {
        return workflow;
    }


    /**
     * Sets the workflow value for this BasicLine.
     * 
     * @param workflow   * This element holds details of
     *                         the workflow attached to this document
     *                     line.
     */
    public void setWorkflow(com.coda.www.efinance.schemas.transaction.LineWorkflow workflow) {
        this.workflow = workflow;
    }


    /**
     * Gets the elmBankAccount value for this BasicLine.
     * 
     * @return elmBankAccount   * The
     *                         account number of the matchable element's
     *                         bank account.
     */
    public java.lang.String getElmBankAccount() {
        return elmBankAccount;
    }


    /**
     * Sets the elmBankAccount value for this BasicLine.
     * 
     * @param elmBankAccount   * The
     *                         account number of the matchable element's
     *                         bank account.
     */
    public void setElmBankAccount(java.lang.String elmBankAccount) {
        this.elmBankAccount = elmBankAccount;
    }


    /**
     * Gets the elmAddrName value for this BasicLine.
     * 
     * @return elmAddrName   * The
     *                         name of the matchable element's
     *                     address.
     */
    public java.lang.String getElmAddrName() {
        return elmAddrName;
    }


    /**
     * Sets the elmAddrName value for this BasicLine.
     * 
     * @param elmAddrName   * The
     *                         name of the matchable element's
     *                     address.
     */
    public void setElmAddrName(java.lang.String elmAddrName) {
        this.elmAddrName = elmAddrName;
    }


    /**
     * Gets the discsAllowed value for this BasicLine.
     * 
     * @return discsAllowed   * Indicates whether discount
     *                         information can be held for this document
     *                     line.
     */
    public java.lang.Boolean getDiscsAllowed() {
        return discsAllowed;
    }


    /**
     * Sets the discsAllowed value for this BasicLine.
     * 
     * @param discsAllowed   * Indicates whether discount
     *                         information can be held for this document
     *                     line.
     */
    public void setDiscsAllowed(java.lang.Boolean discsAllowed) {
        this.discsAllowed = discsAllowed;
    }


    /**
     * Gets the calcDisc value for this BasicLine.
     * 
     * @return calcDisc   * Indicates whether discount
     *                         for this element is calculated gross of tax,
     * net
     *                         of tax or not allowed.
     */
    public java.lang.String getCalcDisc() {
        return calcDisc;
    }


    /**
     * Sets the calcDisc value for this BasicLine.
     * 
     * @param calcDisc   * Indicates whether discount
     *                         for this element is calculated gross of tax,
     * net
     *                         of tax or not allowed.
     */
    public void setCalcDisc(java.lang.String calcDisc) {
        this.calcDisc = calcDisc;
    }


    /**
     * Gets the matchableElmTemporary value for this BasicLine.
     * 
     * @return matchableElmTemporary   * Indicates whether the
     *                         matchable element is an umbrella element,
     * and
     *                         therefore whether a temporary supplier or
     * trader
     *                         is in use for that element..
     */
    public java.lang.Boolean getMatchableElmTemporary() {
        return matchableElmTemporary;
    }


    /**
     * Sets the matchableElmTemporary value for this BasicLine.
     * 
     * @param matchableElmTemporary   * Indicates whether the
     *                         matchable element is an umbrella element,
     * and
     *                         therefore whether a temporary supplier or
     * trader
     *                         is in use for that element..
     */
    public void setMatchableElmTemporary(java.lang.Boolean matchableElmTemporary) {
        this.matchableElmTemporary = matchableElmTemporary;
    }


    /**
     * Gets the accCodeValid value for this BasicLine.
     * 
     * @return accCodeValid   * Indicates whether the account
     *                         code is valid.
     */
    public java.lang.Boolean getAccCodeValid() {
        return accCodeValid;
    }


    /**
     * Sets the accCodeValid value for this BasicLine.
     * 
     * @param accCodeValid   * Indicates whether the account
     *                         code is valid.
     */
    public void setAccCodeValid(java.lang.Boolean accCodeValid) {
        this.accCodeValid = accCodeValid;
    }


    /**
     * Gets the customerSupplier value for this BasicLine.
     * 
     * @return customerSupplier   * Indicates whether the
     *                         matchable element is a customer or supplier
     * element.
     */
    public java.lang.Boolean getCustomerSupplier() {
        return customerSupplier;
    }


    /**
     * Sets the customerSupplier value for this BasicLine.
     * 
     * @param customerSupplier   * Indicates whether the
     *                         matchable element is a customer or supplier
     * element.
     */
    public void setCustomerSupplier(java.lang.Boolean customerSupplier) {
        this.customerSupplier = customerSupplier;
    }


    /**
     * Gets the currencies value for this BasicLine.
     * 
     * @return currencies   * This element holds the
     *                         element currency information for the document
     * line.
     */
    public com.coda.www.efinance.schemas.transaction.Currency[] getCurrencies() {
        return currencies;
    }


    /**
     * Sets the currencies value for this BasicLine.
     * 
     * @param currencies   * This element holds the
     *                         element currency information for the document
     * line.
     */
    public void setCurrencies(com.coda.www.efinance.schemas.transaction.Currency[] currencies) {
        this.currencies = currencies;
    }


    /**
     * Gets the taxes value for this BasicLine.
     * 
     * @return taxes   * This element holds the tax
     *                         information for the document
     *                     line.
     */
    public com.coda.www.efinance.schemas.transaction.Tax[] getTaxes() {
        return taxes;
    }


    /**
     * Sets the taxes value for this BasicLine.
     * 
     * @param taxes   * This element holds the tax
     *                         information for the document
     *                     line.
     */
    public void setTaxes(com.coda.www.efinance.schemas.transaction.Tax[] taxes) {
        this.taxes = taxes;
    }


    /**
     * Gets the ten99Taxes value for this BasicLine.
     * 
     * @return ten99Taxes   * This
     *                         element holds the 1099 tax information for
     * the
     *                         document line.
     */
    public com.coda.www.efinance.schemas.transaction.Tax[] getTen99Taxes() {
        return ten99Taxes;
    }


    /**
     * Sets the ten99Taxes value for this BasicLine.
     * 
     * @param ten99Taxes   * This
     *                         element holds the 1099 tax information for
     * the
     *                         document line.
     */
    public void setTen99Taxes(com.coda.www.efinance.schemas.transaction.Tax[] ten99Taxes) {
        this.ten99Taxes = ten99Taxes;
    }


    /**
     * Gets the discounts value for this BasicLine.
     * 
     * @return discounts   * This element holds the
     *                         discount information for the document
     *                     line.
     */
    public com.coda.www.efinance.schemas.transaction.Discount[] getDiscounts() {
        return discounts;
    }


    /**
     * Sets the discounts value for this BasicLine.
     * 
     * @param discounts   * This element holds the
     *                         discount information for the document
     *                     line.
     */
    public void setDiscounts(com.coda.www.efinance.schemas.transaction.Discount[] discounts) {
        this.discounts = discounts;
    }


    /**
     * Gets the recurringDiscounts value for this BasicLine.
     * 
     * @return recurringDiscounts   * This
     *                         element holds details of discount rates that
     * are
     *                         available if this document is a recurring
     *                     document.
     */
    public com.coda.www.efinance.schemas.transaction.RecurringDiscount[] getRecurringDiscounts() {
        return recurringDiscounts;
    }


    /**
     * Sets the recurringDiscounts value for this BasicLine.
     * 
     * @param recurringDiscounts   * This
     *                         element holds details of discount rates that
     * are
     *                         available if this document is a recurring
     *                     document.
     */
    public void setRecurringDiscounts(com.coda.www.efinance.schemas.transaction.RecurringDiscount[] recurringDiscounts) {
        this.recurringDiscounts = recurringDiscounts;
    }


    /**
     * Gets the DBLineNum value for this BasicLine.
     * 
     * @return DBLineNum   * The value of the line number
     *                         of the document stored in the
     *                     Intray.
     */
    public java.lang.Integer getDBLineNum() {
        return DBLineNum;
    }


    /**
     * Sets the DBLineNum value for this BasicLine.
     * 
     * @param DBLineNum   * The value of the line number
     *                         of the document stored in the
     *                     Intray.
     */
    public void setDBLineNum(java.lang.Integer DBLineNum) {
        this.DBLineNum = DBLineNum;
    }


    /**
     * Gets the involvedInMatch value for this BasicLine.
     * 
     * @return involvedInMatch   * Indicates whether this line
     *                         is matched against an existing line in the
     * database. This line will be made read-only if it
     *                         is involved in a match.
     */
    public java.lang.Boolean getInvolvedInMatch() {
        return involvedInMatch;
    }


    /**
     * Sets the involvedInMatch value for this BasicLine.
     * 
     * @param involvedInMatch   * Indicates whether this line
     *                         is matched against an existing line in the
     * database. This line will be made read-only if it
     *                         is involved in a match.
     */
    public void setInvolvedInMatch(java.lang.Boolean involvedInMatch) {
        this.involvedInMatch = involvedInMatch;
    }


    /**
     * Gets the asset value for this BasicLine.
     * 
     * @return asset   * This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted.
     */
    public com.coda.www.efinance.schemas.transaction.Asset getAsset() {
        return asset;
    }


    /**
     * Sets the asset value for this BasicLine.
     * 
     * @param asset   * This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted.
     */
    public void setAsset(com.coda.www.efinance.schemas.transaction.Asset asset) {
        this.asset = asset;
    }


    /**
     * Gets the extensionData value for this BasicLine.
     * 
     * @return extensionData   * This element holds the
     *                         withholding tax code used in the Italian
     *                         localisation for Financials.
     */
    public java.lang.String getExtensionData() {
        return extensionData;
    }


    /**
     * Sets the extensionData value for this BasicLine.
     * 
     * @param extensionData   * This element holds the
     *                         withholding tax code used in the Italian
     *                         localisation for Financials.
     */
    public void setExtensionData(java.lang.String extensionData) {
        this.extensionData = extensionData;
    }


    /**
     * Gets the extensionData2 value for this BasicLine.
     * 
     * @return extensionData2   * This element holds
     *                         information about irrecoverable tax passed
     * from
     *                         Invoice Matching to
     *                     Purchasing.
     */
    public java.lang.String getExtensionData2() {
        return extensionData2;
    }


    /**
     * Sets the extensionData2 value for this BasicLine.
     * 
     * @param extensionData2   * This element holds
     *                         information about irrecoverable tax passed
     * from
     *                         Invoice Matching to
     *                     Purchasing.
     */
    public void setExtensionData2(java.lang.String extensionData2) {
        this.extensionData2 = extensionData2;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BasicLine)) return false;
        BasicLine other = (BasicLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.number == other.getNumber() &&
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.destCode==null && other.getDestCode()==null) || 
             (this.destCode!=null &&
              this.destCode.equals(other.getDestCode()))) &&
            ((this.accountCode==null && other.getAccountCode()==null) || 
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode()))) &&
            ((this.traderCode==null && other.getTraderCode()==null) || 
             (this.traderCode!=null &&
              this.traderCode.equals(other.getTraderCode()))) &&
            ((this.docValue==null && other.getDocValue()==null) || 
             (this.docValue!=null &&
              this.docValue.equals(other.getDocValue()))) &&
            ((this.docRate==null && other.getDocRate()==null) || 
             (this.docRate!=null &&
              this.docRate.equals(other.getDocRate()))) &&
            ((this.homeValue==null && other.getHomeValue()==null) || 
             (this.homeValue!=null &&
              this.homeValue.equals(other.getHomeValue()))) &&
            ((this.homeFullValue==null && other.getHomeFullValue()==null) || 
             (this.homeFullValue!=null &&
              this.homeFullValue.equals(other.getHomeFullValue()))) &&
            ((this.dualValue==null && other.getDualValue()==null) || 
             (this.dualValue!=null &&
              this.dualValue.equals(other.getDualValue()))) &&
            ((this.dualFullValue==null && other.getDualFullValue()==null) || 
             (this.dualFullValue!=null &&
              this.dualFullValue.equals(other.getDualFullValue()))) &&
            ((this.dualRate==null && other.getDualRate()==null) || 
             (this.dualRate!=null &&
              this.dualRate.equals(other.getDualRate()))) &&
            ((this.parentCurrency==null && other.getParentCurrency()==null) || 
             (this.parentCurrency!=null &&
              this.parentCurrency.equals(other.getParentCurrency()))) &&
            ((this.parentRate==null && other.getParentRate()==null) || 
             (this.parentRate!=null &&
              this.parentRate.equals(other.getParentRate()))) &&
            ((this.parentValue==null && other.getParentValue()==null) || 
             (this.parentValue!=null &&
              this.parentValue.equals(other.getParentValue()))) &&
            ((this.parentFullValue==null && other.getParentFullValue()==null) || 
             (this.parentFullValue!=null &&
              this.parentFullValue.equals(other.getParentFullValue()))) &&
            ((this.userStatus==null && other.getUserStatus()==null) || 
             (this.userStatus!=null &&
              this.userStatus.equals(other.getUserStatus()))) &&
            ((this.lineType==null && other.getLineType()==null) || 
             (this.lineType!=null &&
              this.lineType.equals(other.getLineType()))) &&
            ((this.lineSense==null && other.getLineSense()==null) || 
             (this.lineSense!=null &&
              this.lineSense.equals(other.getLineSense()))) &&
            ((this.lineOrigin==null && other.getLineOrigin()==null) || 
             (this.lineOrigin!=null &&
              this.lineOrigin.equals(other.getLineOrigin()))) &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            ((this.dueTerms==null && other.getDueTerms()==null) || 
             (this.dueTerms!=null &&
              this.dueTerms.equals(other.getDueTerms()))) &&
            ((this.valueDate==null && other.getValueDate()==null) || 
             (this.valueDate!=null &&
              this.valueDate.equals(other.getValueDate()))) &&
            ((this.valueTerms==null && other.getValueTerms()==null) || 
             (this.valueTerms!=null &&
              this.valueTerms.equals(other.getValueTerms()))) &&
            ((this.extRef1==null && other.getExtRef1()==null) || 
             (this.extRef1!=null &&
              this.extRef1.equals(other.getExtRef1()))) &&
            ((this.extRef2==null && other.getExtRef2()==null) || 
             (this.extRef2!=null &&
              this.extRef2.equals(other.getExtRef2()))) &&
            ((this.extRef3==null && other.getExtRef3()==null) || 
             (this.extRef3!=null &&
              this.extRef3.equals(other.getExtRef3()))) &&
            ((this.extRef4==null && other.getExtRef4()==null) || 
             (this.extRef4!=null &&
              this.extRef4.equals(other.getExtRef4()))) &&
            ((this.extRef5==null && other.getExtRef5()==null) || 
             (this.extRef5!=null &&
              this.extRef5.equals(other.getExtRef5()))) &&
            ((this.extRef6==null && other.getExtRef6()==null) || 
             (this.extRef6!=null &&
              this.extRef6.equals(other.getExtRef6()))) &&
            ((this.docSumTax==null && other.getDocSumTax()==null) || 
             (this.docSumTax!=null &&
              this.docSumTax.equals(other.getDocSumTax()))) &&
            ((this.taxLineCode==null && other.getTaxLineCode()==null) || 
             (this.taxLineCode!=null &&
              this.taxLineCode.equals(other.getTaxLineCode()))) &&
            ((this.docTaxTurnover==null && other.getDocTaxTurnover()==null) || 
             (this.docTaxTurnover!=null &&
              this.docTaxTurnover.equals(other.getDocTaxTurnover()))) &&
            ((this.taxInclusive==null && other.getTaxInclusive()==null) || 
             (this.taxInclusive!=null &&
              this.taxInclusive.equals(other.getTaxInclusive()))) &&
            ((this.mediaCode==null && other.getMediaCode()==null) || 
             (this.mediaCode!=null &&
              this.mediaCode.equals(other.getMediaCode()))) &&
            ((this.bankCode==null && other.getBankCode()==null) || 
             (this.bankCode!=null &&
              this.bankCode.equals(other.getBankCode()))) &&
            ((this.disableSummaries==null && other.getDisableSummaries()==null) || 
             (this.disableSummaries!=null &&
              this.disableSummaries.equals(other.getDisableSummaries()))) &&
            ((this.elmBankTag==null && other.getElmBankTag()==null) || 
             (this.elmBankTag!=null &&
              this.elmBankTag.equals(other.getElmBankTag()))) &&
            ((this.elmAddrTag==null && other.getElmAddrTag()==null) || 
             (this.elmAddrTag!=null &&
              this.elmAddrTag.equals(other.getElmAddrTag()))) &&
            ((this.userRef1==null && other.getUserRef1()==null) || 
             (this.userRef1!=null &&
              this.userRef1.equals(other.getUserRef1()))) &&
            ((this.userRef2==null && other.getUserRef2()==null) || 
             (this.userRef2!=null &&
              this.userRef2.equals(other.getUserRef2()))) &&
            ((this.userRef3==null && other.getUserRef3()==null) || 
             (this.userRef3!=null &&
              this.userRef3.equals(other.getUserRef3()))) &&
            ((this.elmQuantities1==null && other.getElmQuantities1()==null) || 
             (this.elmQuantities1!=null &&
              this.elmQuantities1.equals(other.getElmQuantities1()))) &&
            ((this.elmQuantities2==null && other.getElmQuantities2()==null) || 
             (this.elmQuantities2!=null &&
              this.elmQuantities2.equals(other.getElmQuantities2()))) &&
            ((this.elmQuantities3==null && other.getElmQuantities3()==null) || 
             (this.elmQuantities3!=null &&
              this.elmQuantities3.equals(other.getElmQuantities3()))) &&
            ((this.elmQuantities4==null && other.getElmQuantities4()==null) || 
             (this.elmQuantities4!=null &&
              this.elmQuantities4.equals(other.getElmQuantities4()))) &&
            ((this.elmQuantities5==null && other.getElmQuantities5()==null) || 
             (this.elmQuantities5!=null &&
              this.elmQuantities5.equals(other.getElmQuantities5()))) &&
            ((this.elmQuantities6==null && other.getElmQuantities6()==null) || 
             (this.elmQuantities6!=null &&
              this.elmQuantities6.equals(other.getElmQuantities6()))) &&
            ((this.elmQuantities7==null && other.getElmQuantities7()==null) || 
             (this.elmQuantities7!=null &&
              this.elmQuantities7.equals(other.getElmQuantities7()))) &&
            ((this.elmQuantities8==null && other.getElmQuantities8()==null) || 
             (this.elmQuantities8!=null &&
              this.elmQuantities8.equals(other.getElmQuantities8()))) &&
            ((this.payStatus==null && other.getPayStatus()==null) || 
             (this.payStatus!=null &&
              this.payStatus.equals(other.getPayStatus()))) &&
            ((this.payDate==null && other.getPayDate()==null) || 
             (this.payDate!=null &&
              this.payDate.equals(other.getPayDate()))) &&
            ((this.payNumber==null && other.getPayNumber()==null) || 
             (this.payNumber!=null &&
              this.payNumber.equals(other.getPayNumber()))) &&
            ((this.recStatus==null && other.getRecStatus()==null) || 
             (this.recStatus!=null &&
              this.recStatus.equals(other.getRecStatus()))) &&
            ((this.recNumber==null && other.getRecNumber()==null) || 
             (this.recNumber!=null &&
              this.recNumber.equals(other.getRecNumber()))) &&
            ((this.partPayment==null && other.getPartPayment()==null) || 
             (this.partPayment!=null &&
              this.partPayment.equals(other.getPartPayment()))) &&
            ((this.homeSumTax==null && other.getHomeSumTax()==null) || 
             (this.homeSumTax!=null &&
              this.homeSumTax.equals(other.getHomeSumTax()))) &&
            ((this.matchableElmLevel==null && other.getMatchableElmLevel()==null) || 
             (this.matchableElmLevel!=null &&
              this.matchableElmLevel.equals(other.getMatchableElmLevel()))) &&
            ((this.taxMethod==null && other.getTaxMethod()==null) || 
             (this.taxMethod!=null &&
              this.taxMethod.equals(other.getTaxMethod()))) &&
            ((this.workflow==null && other.getWorkflow()==null) || 
             (this.workflow!=null &&
              this.workflow.equals(other.getWorkflow()))) &&
            ((this.elmBankAccount==null && other.getElmBankAccount()==null) || 
             (this.elmBankAccount!=null &&
              this.elmBankAccount.equals(other.getElmBankAccount()))) &&
            ((this.elmAddrName==null && other.getElmAddrName()==null) || 
             (this.elmAddrName!=null &&
              this.elmAddrName.equals(other.getElmAddrName()))) &&
            ((this.discsAllowed==null && other.getDiscsAllowed()==null) || 
             (this.discsAllowed!=null &&
              this.discsAllowed.equals(other.getDiscsAllowed()))) &&
            ((this.calcDisc==null && other.getCalcDisc()==null) || 
             (this.calcDisc!=null &&
              this.calcDisc.equals(other.getCalcDisc()))) &&
            ((this.matchableElmTemporary==null && other.getMatchableElmTemporary()==null) || 
             (this.matchableElmTemporary!=null &&
              this.matchableElmTemporary.equals(other.getMatchableElmTemporary()))) &&
            ((this.accCodeValid==null && other.getAccCodeValid()==null) || 
             (this.accCodeValid!=null &&
              this.accCodeValid.equals(other.getAccCodeValid()))) &&
            ((this.customerSupplier==null && other.getCustomerSupplier()==null) || 
             (this.customerSupplier!=null &&
              this.customerSupplier.equals(other.getCustomerSupplier()))) &&
            ((this.currencies==null && other.getCurrencies()==null) || 
             (this.currencies!=null &&
              java.util.Arrays.equals(this.currencies, other.getCurrencies()))) &&
            ((this.taxes==null && other.getTaxes()==null) || 
             (this.taxes!=null &&
              java.util.Arrays.equals(this.taxes, other.getTaxes()))) &&
            ((this.ten99Taxes==null && other.getTen99Taxes()==null) || 
             (this.ten99Taxes!=null &&
              java.util.Arrays.equals(this.ten99Taxes, other.getTen99Taxes()))) &&
            ((this.discounts==null && other.getDiscounts()==null) || 
             (this.discounts!=null &&
              java.util.Arrays.equals(this.discounts, other.getDiscounts()))) &&
            ((this.recurringDiscounts==null && other.getRecurringDiscounts()==null) || 
             (this.recurringDiscounts!=null &&
              java.util.Arrays.equals(this.recurringDiscounts, other.getRecurringDiscounts()))) &&
            ((this.DBLineNum==null && other.getDBLineNum()==null) || 
             (this.DBLineNum!=null &&
              this.DBLineNum.equals(other.getDBLineNum()))) &&
            ((this.involvedInMatch==null && other.getInvolvedInMatch()==null) || 
             (this.involvedInMatch!=null &&
              this.involvedInMatch.equals(other.getInvolvedInMatch()))) &&
            ((this.asset==null && other.getAsset()==null) || 
             (this.asset!=null &&
              this.asset.equals(other.getAsset()))) &&
            ((this.extensionData==null && other.getExtensionData()==null) || 
             (this.extensionData!=null &&
              this.extensionData.equals(other.getExtensionData()))) &&
            ((this.extensionData2==null && other.getExtensionData2()==null) || 
             (this.extensionData2!=null &&
              this.extensionData2.equals(other.getExtensionData2())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getNumber();
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getDestCode() != null) {
            _hashCode += getDestCode().hashCode();
        }
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        if (getTraderCode() != null) {
            _hashCode += getTraderCode().hashCode();
        }
        if (getDocValue() != null) {
            _hashCode += getDocValue().hashCode();
        }
        if (getDocRate() != null) {
            _hashCode += getDocRate().hashCode();
        }
        if (getHomeValue() != null) {
            _hashCode += getHomeValue().hashCode();
        }
        if (getHomeFullValue() != null) {
            _hashCode += getHomeFullValue().hashCode();
        }
        if (getDualValue() != null) {
            _hashCode += getDualValue().hashCode();
        }
        if (getDualFullValue() != null) {
            _hashCode += getDualFullValue().hashCode();
        }
        if (getDualRate() != null) {
            _hashCode += getDualRate().hashCode();
        }
        if (getParentCurrency() != null) {
            _hashCode += getParentCurrency().hashCode();
        }
        if (getParentRate() != null) {
            _hashCode += getParentRate().hashCode();
        }
        if (getParentValue() != null) {
            _hashCode += getParentValue().hashCode();
        }
        if (getParentFullValue() != null) {
            _hashCode += getParentFullValue().hashCode();
        }
        if (getUserStatus() != null) {
            _hashCode += getUserStatus().hashCode();
        }
        if (getLineType() != null) {
            _hashCode += getLineType().hashCode();
        }
        if (getLineSense() != null) {
            _hashCode += getLineSense().hashCode();
        }
        if (getLineOrigin() != null) {
            _hashCode += getLineOrigin().hashCode();
        }
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        if (getDueTerms() != null) {
            _hashCode += getDueTerms().hashCode();
        }
        if (getValueDate() != null) {
            _hashCode += getValueDate().hashCode();
        }
        if (getValueTerms() != null) {
            _hashCode += getValueTerms().hashCode();
        }
        if (getExtRef1() != null) {
            _hashCode += getExtRef1().hashCode();
        }
        if (getExtRef2() != null) {
            _hashCode += getExtRef2().hashCode();
        }
        if (getExtRef3() != null) {
            _hashCode += getExtRef3().hashCode();
        }
        if (getExtRef4() != null) {
            _hashCode += getExtRef4().hashCode();
        }
        if (getExtRef5() != null) {
            _hashCode += getExtRef5().hashCode();
        }
        if (getExtRef6() != null) {
            _hashCode += getExtRef6().hashCode();
        }
        if (getDocSumTax() != null) {
            _hashCode += getDocSumTax().hashCode();
        }
        if (getTaxLineCode() != null) {
            _hashCode += getTaxLineCode().hashCode();
        }
        if (getDocTaxTurnover() != null) {
            _hashCode += getDocTaxTurnover().hashCode();
        }
        if (getTaxInclusive() != null) {
            _hashCode += getTaxInclusive().hashCode();
        }
        if (getMediaCode() != null) {
            _hashCode += getMediaCode().hashCode();
        }
        if (getBankCode() != null) {
            _hashCode += getBankCode().hashCode();
        }
        if (getDisableSummaries() != null) {
            _hashCode += getDisableSummaries().hashCode();
        }
        if (getElmBankTag() != null) {
            _hashCode += getElmBankTag().hashCode();
        }
        if (getElmAddrTag() != null) {
            _hashCode += getElmAddrTag().hashCode();
        }
        if (getUserRef1() != null) {
            _hashCode += getUserRef1().hashCode();
        }
        if (getUserRef2() != null) {
            _hashCode += getUserRef2().hashCode();
        }
        if (getUserRef3() != null) {
            _hashCode += getUserRef3().hashCode();
        }
        if (getElmQuantities1() != null) {
            _hashCode += getElmQuantities1().hashCode();
        }
        if (getElmQuantities2() != null) {
            _hashCode += getElmQuantities2().hashCode();
        }
        if (getElmQuantities3() != null) {
            _hashCode += getElmQuantities3().hashCode();
        }
        if (getElmQuantities4() != null) {
            _hashCode += getElmQuantities4().hashCode();
        }
        if (getElmQuantities5() != null) {
            _hashCode += getElmQuantities5().hashCode();
        }
        if (getElmQuantities6() != null) {
            _hashCode += getElmQuantities6().hashCode();
        }
        if (getElmQuantities7() != null) {
            _hashCode += getElmQuantities7().hashCode();
        }
        if (getElmQuantities8() != null) {
            _hashCode += getElmQuantities8().hashCode();
        }
        if (getPayStatus() != null) {
            _hashCode += getPayStatus().hashCode();
        }
        if (getPayDate() != null) {
            _hashCode += getPayDate().hashCode();
        }
        if (getPayNumber() != null) {
            _hashCode += getPayNumber().hashCode();
        }
        if (getRecStatus() != null) {
            _hashCode += getRecStatus().hashCode();
        }
        if (getRecNumber() != null) {
            _hashCode += getRecNumber().hashCode();
        }
        if (getPartPayment() != null) {
            _hashCode += getPartPayment().hashCode();
        }
        if (getHomeSumTax() != null) {
            _hashCode += getHomeSumTax().hashCode();
        }
        if (getMatchableElmLevel() != null) {
            _hashCode += getMatchableElmLevel().hashCode();
        }
        if (getTaxMethod() != null) {
            _hashCode += getTaxMethod().hashCode();
        }
        if (getWorkflow() != null) {
            _hashCode += getWorkflow().hashCode();
        }
        if (getElmBankAccount() != null) {
            _hashCode += getElmBankAccount().hashCode();
        }
        if (getElmAddrName() != null) {
            _hashCode += getElmAddrName().hashCode();
        }
        if (getDiscsAllowed() != null) {
            _hashCode += getDiscsAllowed().hashCode();
        }
        if (getCalcDisc() != null) {
            _hashCode += getCalcDisc().hashCode();
        }
        if (getMatchableElmTemporary() != null) {
            _hashCode += getMatchableElmTemporary().hashCode();
        }
        if (getAccCodeValid() != null) {
            _hashCode += getAccCodeValid().hashCode();
        }
        if (getCustomerSupplier() != null) {
            _hashCode += getCustomerSupplier().hashCode();
        }
        if (getCurrencies() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getCurrencies());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getCurrencies(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTaxes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTaxes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTaxes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTen99Taxes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTen99Taxes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTen99Taxes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDiscounts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDiscounts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDiscounts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRecurringDiscounts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecurringDiscounts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecurringDiscounts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDBLineNum() != null) {
            _hashCode += getDBLineNum().hashCode();
        }
        if (getInvolvedInMatch() != null) {
            _hashCode += getInvolvedInMatch().hashCode();
        }
        if (getAsset() != null) {
            _hashCode += getAsset().hashCode();
        }
        if (getExtensionData() != null) {
            _hashCode += getExtensionData().hashCode();
        }
        if (getExtensionData2() != null) {
            _hashCode += getExtensionData2().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BasicLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "BasicLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("traderCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TraderCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HomeValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeFullValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HomeFullValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DualValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualFullValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DualFullValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DualRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentFullValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentFullValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineSense");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineSense"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineOrigin");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineOrigin"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueTerms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DueTerms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ValueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueTerms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ValueTerms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRef6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtRef6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docSumTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocSumTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxLineCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TaxLineCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docTaxTurnover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocTaxTurnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxInclusive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TaxInclusive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediaCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "MediaCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "BankCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("disableSummaries");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DisableSummaries"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmBankTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmBankTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmAddrTag");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmAddrTag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserRef1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserRef2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userRef3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UserRef3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities4");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities4"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities5");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities5"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities6");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities6"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities7");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities7"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmQuantities8");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities8"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmQuantities"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "PayStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "PayDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "PayNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partPayment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "PartPayment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeSumTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "HomeSumTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchableElmLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "MatchableElmLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TaxMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Workflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineWorkflow"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmBankAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmBankAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmAddrName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ElmAddrName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discsAllowed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DiscsAllowed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calcDisc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CalcDisc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchableElmTemporary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "MatchableElmTemporary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accCodeValid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccCodeValid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CustomerSupplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currencies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currency"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Currency"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Taxes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten99Taxes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Ten99Taxes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Tax"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Ten99Tax"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDiscounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecurringDiscounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "RecurringDiscount"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Discount"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DBLineNum");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DBLineNum"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("involvedInMatch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InvolvedInMatch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Asset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Asset"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtensionData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extensionData2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ExtensionData2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
