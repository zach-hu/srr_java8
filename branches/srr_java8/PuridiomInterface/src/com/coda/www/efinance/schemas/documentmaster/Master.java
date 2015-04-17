/**
 * Master.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds a document
 *             master.
 */
public class Master  implements java.io.Serializable {
    private short timeStamp;

    /* The
     *                         code for the company in which the document
     * master is being maintained. */
    private java.lang.String cmpCode;

    /* The
     *                         document code. */
    private java.lang.String code;

    /* The
     *                         full name for the document
     *                     master. */
    private java.lang.String name;

    /* The abbreviated name for the
     *                         document master. */
    private java.lang.String shortName;

    /* Specifies whether this master
     *                         generates cancelling documents and, if so,
     * whether they cancel single or multiple
     *                     documents. */
    private java.lang.String cancelDoc;

    /* If
     *                         True, specifies that when a cancelling document
     * is posted, all the matchable lines on the
     *                         original document are matched against their
     * counterparts on the cancelling document with the
     *                         payment status of C - Cancel matched. If False,
     * payable lines are matched with the status P -
     *                     Paid. */
    private boolean cancelMatch;

    /* Specifies that postings using
     *                         this document master must be added to the
     * period
     *                         turnover figures for the elements
     *                     affected. */
    private boolean addToTurnovers;

    /* Specifies that documents
     *                         generated from this master will be assigned
     * a
     *                         batch number automatically during
     *                     Input. */
    private boolean batch;

    /* A
     *                         single alphanumeric character which will be
     * added to entries posted to the Books. You can
     *                         use this character as a selection parameter
     * in
     *                     Browse. */
    private java.lang.String userStatus;

    /* The default payment status of
     *                         documents generated from this
     *                     master. */
    private java.lang.String payStatus;

    /* Specifies that a description
     *                         must be supplied during Input for the document
     * header and document lines. */
    private boolean requireDescription;

    /* A code to appear on the bank
     *                         statements for this document code, for example,
     * CHQ (cheque) or DD (direct debit). This is a
     *                         memorandum field that will allow a report
     * to be
     *                         produced for bank reconciliation
     *                     purposes. */
    private java.lang.String bankStatement;

    /* Specifies the numbering
     *                         method on documents generated from this
     *                     master. */
    private java.lang.String numberRule;

    /* Specifies whether document
     *                         numbers are numeric or
     *                     alphanumeric. */
    private java.lang.String numberFormat;

    /* Specifies the action to be
     *                         taken if the entered value for the control
     * total
     *                         does not agree with the total of the debit
     * lines
     *                         on the completed document. */
    private java.lang.String controlTotals;

    /* Specifies whether documents
     *                         are posted only to the Books, only to the
     *                         Intray, or to either. */
    private java.lang.String destination;

    /* Specifies the type of
     *                         checking applied to documents: Books, Intray,
     * or
     *                     either. */
    private java.lang.String checking;

    /* Specifies whether the
     *                         Authoring User input field is used, mandatory,
     * or not used. */
    private java.lang.String authorisingUser;

    /* Specifies that validation of
     *                         individual elements is disabled and that the
     * full account code is checked when the user
     *                         completes an entry during
     *                     Input. */
    private boolean validateOnAccount;

    /* Specifies the type of period
     *                         to which documents generated from this master
     * can be posted. */
    private java.lang.String periodUsage;

    /* Specifies that the document
     *                         master is used to generate invoices for purchase
     * orders, and you want to match them against the
     *                         originating orders in
     *                     Purchasing. */
    private boolean purchaseOrdering;

    /* Specifies that documents
     *                         using this master can hold discount
     *                     information. */
    private boolean allowDiscounts;

    /* Specifies how the Last
     *                         Movements data for the matchable element is
     * updated. */
    private java.lang.String updateTransaction;

    /* Specifies that specific
     *                         document line information is retained when
     * the
     *                         account code is changed during
     *                     Input. */
    private boolean retainValues;

    /* Specifies whether documents
     *                         of this type can be changed in Browse
     *                     Writeback. */
    private boolean amendInBrowse;

    /* The name of the custom
     *                         program called for checking Header information
     * during Input. */
    private java.lang.String headerUserExit;

    /* Specifies whether this
     *                         document master can be used for matching
     *                         documents from Input. */
    private boolean matchingFromInput;

    /* The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Input. */
    private java.lang.String inputMatchingMasterCode;

    /* Specifies whether this
     *                         document master can be used for matching
     *                         documents from Browse. */
    private boolean matchingFromBrowse;

    /* The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Browse. */
    private java.lang.String browseMatchingMasterCode;

    /* Specifies that assets can be
     *                         created with this document
     *                     master. */
    private java.lang.String assetDoc;

    /* Specifies whether this
     *                         document master generates self-proportioning
     * documents. */
    private boolean selfProportioning;

    /* Specifies whether this
     *                         document master generates recurring
     *                     documents. */
    private boolean recurring;

    /* Specifies whether this
     *                         document master generates reversing
     *                     documents. */
    private boolean reversing;

    private com.coda.www.efinance.schemas.documentmaster.DueDate dueDate;

    private com.coda.www.efinance.schemas.documentmaster.ValueDate valueDate;

    private com.coda.www.efinance.schemas.documentmaster.Currency currency;

    /* This
     *                         element holds tax settings. */
    private com.coda.www.efinance.schemas.documentmaster.DocTax tax;

    private com.coda.www.efinance.schemas.documentmaster.Pay pay;

    private com.coda.www.efinance.schemas.documentmaster.Quantities quantities;

    private com.coda.www.efinance.schemas.documentmaster.SummaryLines summaryLines;

    private com.coda.www.efinance.schemas.documentmaster.AnalysisLines analysisLines;

    /* This element holds
     *                         InterCompany settings. */
    private com.coda.www.efinance.schemas.documentmaster.Intercompany intercompany;

    private com.coda.www.efinance.schemas.documentmaster.BalancingElements balancingElements;

    /* This element holds
     *                         information about external
     *                     references. */
    private com.coda.www.efinance.schemas.documentmaster.ExternalReferences externalReferences;

    /* This element holds
     *                         information about user
     *                     references. */
    private com.coda.www.efinance.schemas.documentmaster.UserReferences userReferences;

    /* This
     *                         element holds recurring document
     *                     settings. */
    private com.coda.www.efinance.schemas.documentmaster.Recurring recurringDetails;

    /* This element holds
     *                         pre-defined line settings. */
    private com.coda.www.efinance.schemas.documentmaster.PreDefinedLines preDefinedLines;

    /* This element holds
     *                         information specifying whether certain fields
     * are included in the tab sequence during
     *                     Input. */
    private com.coda.www.efinance.schemas.documentmaster.FieldAccess fieldAccess;

    /* The
     *                         extension master code for the document header
     * validation extension that you want to check the
     *                         headers of documents generated from this
     *                     master. */
    private com.coda.www.efinance.schemas.common.ExtensionRef headerExtension;

    /* The
     *                         extension master code for the document
     *                         validation extension that you want to validate
     * external references for documents generated from
     *                         this master. */
    private com.coda.www.efinance.schemas.common.ExtensionRef extRefExtension;

    /* Specifies that the document
     *                         cannot be deleted once posted to the
     *                     Intray. */
    private boolean prevIntrayDel;

    /* Specifies that the document
     *                         cannot be modified once posted to the
     *                     Intray. */
    private boolean prevIntrayMod;

    /* The date the record was
     *                     created. */
    private java.util.Calendar createDate;

    /* The date when the record was
     *                         last updated. */
    private java.util.Calendar modifyDate;

    /* The user who updated the
     *                     record. */
    private java.lang.String user;

    /* Specifies that a separate
     *                         asset is created from each document
     *                     line. */
    private java.lang.Boolean assetPerLine;

    /* The
     *                         type of workflow authorisation
     *                     required. */
    private java.lang.String workflowRequired;

    /* The browse details presenter
     *                         to use when a user views the document details
     * from their workflow work
     *                     list. */
    private java.lang.String workItemExplodePresenter;

    /* The default position
     *                         hierarchy to use for
     *                     authorisation. */
    private java.lang.String positionHierarchy;

    /* The workflow process
     *                         definition to use when posting this type of
     * document to the Intray. */
    private java.lang.String intrayWorkflow;

    /* If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         Intray cannot be changed. */
    private boolean protectIntrayWorkflow;

    /* If set, a prompt appears at
     *                         Input asking whether the user would like to
     * start a workflow instance. */
    private boolean confirmIntrayWorkflow;

    /* If set, users can modify this
     *                         type of document while it is in the Intray,
     * providing it is not yet
     *                     authorised. */
    private boolean allowWorkflowIntrayEdits;

    /* The workflow process
     *                         definition to use when posting this type of
     * document to the books. */
    private java.lang.String booksWorkflow;

    /* If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         books cannot be changed. */
    private boolean protectBooksWorkflow;

    /* If set, the user is prompted
     *                         for an authorising user when submitting
     *                         documents of this type into
     *                     Workflow. */
    private boolean promptForAuthorisingUser;

    /* If
     *                         set, the document master is
     *                     reserved. */
    private boolean isReserved;

    /* The ID
     *                         of the CODA application which has reserved
     * the
     *                         document master. */
    private java.lang.String reservingApplicationId;

    public Master() {
    }

    public Master(
           short timeStamp,
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           java.lang.String cancelDoc,
           boolean cancelMatch,
           boolean addToTurnovers,
           boolean batch,
           java.lang.String userStatus,
           java.lang.String payStatus,
           boolean requireDescription,
           java.lang.String bankStatement,
           java.lang.String numberRule,
           java.lang.String numberFormat,
           java.lang.String controlTotals,
           java.lang.String destination,
           java.lang.String checking,
           java.lang.String authorisingUser,
           boolean validateOnAccount,
           java.lang.String periodUsage,
           boolean purchaseOrdering,
           boolean allowDiscounts,
           java.lang.String updateTransaction,
           boolean retainValues,
           boolean amendInBrowse,
           java.lang.String headerUserExit,
           boolean matchingFromInput,
           java.lang.String inputMatchingMasterCode,
           boolean matchingFromBrowse,
           java.lang.String browseMatchingMasterCode,
           java.lang.String assetDoc,
           boolean selfProportioning,
           boolean recurring,
           boolean reversing,
           com.coda.www.efinance.schemas.documentmaster.DueDate dueDate,
           com.coda.www.efinance.schemas.documentmaster.ValueDate valueDate,
           com.coda.www.efinance.schemas.documentmaster.Currency currency,
           com.coda.www.efinance.schemas.documentmaster.DocTax tax,
           com.coda.www.efinance.schemas.documentmaster.Pay pay,
           com.coda.www.efinance.schemas.documentmaster.Quantities quantities,
           com.coda.www.efinance.schemas.documentmaster.SummaryLines summaryLines,
           com.coda.www.efinance.schemas.documentmaster.AnalysisLines analysisLines,
           com.coda.www.efinance.schemas.documentmaster.Intercompany intercompany,
           com.coda.www.efinance.schemas.documentmaster.BalancingElements balancingElements,
           com.coda.www.efinance.schemas.documentmaster.ExternalReferences externalReferences,
           com.coda.www.efinance.schemas.documentmaster.UserReferences userReferences,
           com.coda.www.efinance.schemas.documentmaster.Recurring recurringDetails,
           com.coda.www.efinance.schemas.documentmaster.PreDefinedLines preDefinedLines,
           com.coda.www.efinance.schemas.documentmaster.FieldAccess fieldAccess,
           com.coda.www.efinance.schemas.common.ExtensionRef headerExtension,
           com.coda.www.efinance.schemas.common.ExtensionRef extRefExtension,
           boolean prevIntrayDel,
           boolean prevIntrayMod,
           java.util.Calendar createDate,
           java.util.Calendar modifyDate,
           java.lang.String user,
           java.lang.Boolean assetPerLine,
           java.lang.String workflowRequired,
           java.lang.String workItemExplodePresenter,
           java.lang.String positionHierarchy,
           java.lang.String intrayWorkflow,
           boolean protectIntrayWorkflow,
           boolean confirmIntrayWorkflow,
           boolean allowWorkflowIntrayEdits,
           java.lang.String booksWorkflow,
           boolean protectBooksWorkflow,
           boolean promptForAuthorisingUser,
           boolean isReserved,
           java.lang.String reservingApplicationId) {
           this.timeStamp = timeStamp;
           this.cmpCode = cmpCode;
           this.code = code;
           this.name = name;
           this.shortName = shortName;
           this.cancelDoc = cancelDoc;
           this.cancelMatch = cancelMatch;
           this.addToTurnovers = addToTurnovers;
           this.batch = batch;
           this.userStatus = userStatus;
           this.payStatus = payStatus;
           this.requireDescription = requireDescription;
           this.bankStatement = bankStatement;
           this.numberRule = numberRule;
           this.numberFormat = numberFormat;
           this.controlTotals = controlTotals;
           this.destination = destination;
           this.checking = checking;
           this.authorisingUser = authorisingUser;
           this.validateOnAccount = validateOnAccount;
           this.periodUsage = periodUsage;
           this.purchaseOrdering = purchaseOrdering;
           this.allowDiscounts = allowDiscounts;
           this.updateTransaction = updateTransaction;
           this.retainValues = retainValues;
           this.amendInBrowse = amendInBrowse;
           this.headerUserExit = headerUserExit;
           this.matchingFromInput = matchingFromInput;
           this.inputMatchingMasterCode = inputMatchingMasterCode;
           this.matchingFromBrowse = matchingFromBrowse;
           this.browseMatchingMasterCode = browseMatchingMasterCode;
           this.assetDoc = assetDoc;
           this.selfProportioning = selfProportioning;
           this.recurring = recurring;
           this.reversing = reversing;
           this.dueDate = dueDate;
           this.valueDate = valueDate;
           this.currency = currency;
           this.tax = tax;
           this.pay = pay;
           this.quantities = quantities;
           this.summaryLines = summaryLines;
           this.analysisLines = analysisLines;
           this.intercompany = intercompany;
           this.balancingElements = balancingElements;
           this.externalReferences = externalReferences;
           this.userReferences = userReferences;
           this.recurringDetails = recurringDetails;
           this.preDefinedLines = preDefinedLines;
           this.fieldAccess = fieldAccess;
           this.headerExtension = headerExtension;
           this.extRefExtension = extRefExtension;
           this.prevIntrayDel = prevIntrayDel;
           this.prevIntrayMod = prevIntrayMod;
           this.createDate = createDate;
           this.modifyDate = modifyDate;
           this.user = user;
           this.assetPerLine = assetPerLine;
           this.workflowRequired = workflowRequired;
           this.workItemExplodePresenter = workItemExplodePresenter;
           this.positionHierarchy = positionHierarchy;
           this.intrayWorkflow = intrayWorkflow;
           this.protectIntrayWorkflow = protectIntrayWorkflow;
           this.confirmIntrayWorkflow = confirmIntrayWorkflow;
           this.allowWorkflowIntrayEdits = allowWorkflowIntrayEdits;
           this.booksWorkflow = booksWorkflow;
           this.protectBooksWorkflow = protectBooksWorkflow;
           this.promptForAuthorisingUser = promptForAuthorisingUser;
           this.isReserved = isReserved;
           this.reservingApplicationId = reservingApplicationId;
    }


    /**
     * Gets the timeStamp value for this Master.
     * 
     * @return timeStamp
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this Master.
     * 
     * @param timeStamp
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the cmpCode value for this Master.
     * 
     * @return cmpCode   * The
     *                         code for the company in which the document
     * master is being maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this Master.
     * 
     * @param cmpCode   * The
     *                         code for the company in which the document
     * master is being maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this Master.
     * 
     * @return code   * The
     *                         document code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this Master.
     * 
     * @param code   * The
     *                         document code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this Master.
     * 
     * @return name   * The
     *                         full name for the document
     *                     master.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Master.
     * 
     * @param name   * The
     *                         full name for the document
     *                     master.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this Master.
     * 
     * @return shortName   * The abbreviated name for the
     *                         document master.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this Master.
     * 
     * @param shortName   * The abbreviated name for the
     *                         document master.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the cancelDoc value for this Master.
     * 
     * @return cancelDoc   * Specifies whether this master
     *                         generates cancelling documents and, if so,
     * whether they cancel single or multiple
     *                     documents.
     */
    public java.lang.String getCancelDoc() {
        return cancelDoc;
    }


    /**
     * Sets the cancelDoc value for this Master.
     * 
     * @param cancelDoc   * Specifies whether this master
     *                         generates cancelling documents and, if so,
     * whether they cancel single or multiple
     *                     documents.
     */
    public void setCancelDoc(java.lang.String cancelDoc) {
        this.cancelDoc = cancelDoc;
    }


    /**
     * Gets the cancelMatch value for this Master.
     * 
     * @return cancelMatch   * If
     *                         True, specifies that when a cancelling document
     * is posted, all the matchable lines on the
     *                         original document are matched against their
     * counterparts on the cancelling document with the
     *                         payment status of C - Cancel matched. If False,
     * payable lines are matched with the status P -
     *                     Paid.
     */
    public boolean isCancelMatch() {
        return cancelMatch;
    }


    /**
     * Sets the cancelMatch value for this Master.
     * 
     * @param cancelMatch   * If
     *                         True, specifies that when a cancelling document
     * is posted, all the matchable lines on the
     *                         original document are matched against their
     * counterparts on the cancelling document with the
     *                         payment status of C - Cancel matched. If False,
     * payable lines are matched with the status P -
     *                     Paid.
     */
    public void setCancelMatch(boolean cancelMatch) {
        this.cancelMatch = cancelMatch;
    }


    /**
     * Gets the addToTurnovers value for this Master.
     * 
     * @return addToTurnovers   * Specifies that postings using
     *                         this document master must be added to the
     * period
     *                         turnover figures for the elements
     *                     affected.
     */
    public boolean isAddToTurnovers() {
        return addToTurnovers;
    }


    /**
     * Sets the addToTurnovers value for this Master.
     * 
     * @param addToTurnovers   * Specifies that postings using
     *                         this document master must be added to the
     * period
     *                         turnover figures for the elements
     *                     affected.
     */
    public void setAddToTurnovers(boolean addToTurnovers) {
        this.addToTurnovers = addToTurnovers;
    }


    /**
     * Gets the batch value for this Master.
     * 
     * @return batch   * Specifies that documents
     *                         generated from this master will be assigned
     * a
     *                         batch number automatically during
     *                     Input.
     */
    public boolean isBatch() {
        return batch;
    }


    /**
     * Sets the batch value for this Master.
     * 
     * @param batch   * Specifies that documents
     *                         generated from this master will be assigned
     * a
     *                         batch number automatically during
     *                     Input.
     */
    public void setBatch(boolean batch) {
        this.batch = batch;
    }


    /**
     * Gets the userStatus value for this Master.
     * 
     * @return userStatus   * A
     *                         single alphanumeric character which will be
     * added to entries posted to the Books. You can
     *                         use this character as a selection parameter
     * in
     *                     Browse.
     */
    public java.lang.String getUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this Master.
     * 
     * @param userStatus   * A
     *                         single alphanumeric character which will be
     * added to entries posted to the Books. You can
     *                         use this character as a selection parameter
     * in
     *                     Browse.
     */
    public void setUserStatus(java.lang.String userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the payStatus value for this Master.
     * 
     * @return payStatus   * The default payment status of
     *                         documents generated from this
     *                     master.
     */
    public java.lang.String getPayStatus() {
        return payStatus;
    }


    /**
     * Sets the payStatus value for this Master.
     * 
     * @param payStatus   * The default payment status of
     *                         documents generated from this
     *                     master.
     */
    public void setPayStatus(java.lang.String payStatus) {
        this.payStatus = payStatus;
    }


    /**
     * Gets the requireDescription value for this Master.
     * 
     * @return requireDescription   * Specifies that a description
     *                         must be supplied during Input for the document
     * header and document lines.
     */
    public boolean isRequireDescription() {
        return requireDescription;
    }


    /**
     * Sets the requireDescription value for this Master.
     * 
     * @param requireDescription   * Specifies that a description
     *                         must be supplied during Input for the document
     * header and document lines.
     */
    public void setRequireDescription(boolean requireDescription) {
        this.requireDescription = requireDescription;
    }


    /**
     * Gets the bankStatement value for this Master.
     * 
     * @return bankStatement   * A code to appear on the bank
     *                         statements for this document code, for example,
     * CHQ (cheque) or DD (direct debit). This is a
     *                         memorandum field that will allow a report
     * to be
     *                         produced for bank reconciliation
     *                     purposes.
     */
    public java.lang.String getBankStatement() {
        return bankStatement;
    }


    /**
     * Sets the bankStatement value for this Master.
     * 
     * @param bankStatement   * A code to appear on the bank
     *                         statements for this document code, for example,
     * CHQ (cheque) or DD (direct debit). This is a
     *                         memorandum field that will allow a report
     * to be
     *                         produced for bank reconciliation
     *                     purposes.
     */
    public void setBankStatement(java.lang.String bankStatement) {
        this.bankStatement = bankStatement;
    }


    /**
     * Gets the numberRule value for this Master.
     * 
     * @return numberRule   * Specifies the numbering
     *                         method on documents generated from this
     *                     master.
     */
    public java.lang.String getNumberRule() {
        return numberRule;
    }


    /**
     * Sets the numberRule value for this Master.
     * 
     * @param numberRule   * Specifies the numbering
     *                         method on documents generated from this
     *                     master.
     */
    public void setNumberRule(java.lang.String numberRule) {
        this.numberRule = numberRule;
    }


    /**
     * Gets the numberFormat value for this Master.
     * 
     * @return numberFormat   * Specifies whether document
     *                         numbers are numeric or
     *                     alphanumeric.
     */
    public java.lang.String getNumberFormat() {
        return numberFormat;
    }


    /**
     * Sets the numberFormat value for this Master.
     * 
     * @param numberFormat   * Specifies whether document
     *                         numbers are numeric or
     *                     alphanumeric.
     */
    public void setNumberFormat(java.lang.String numberFormat) {
        this.numberFormat = numberFormat;
    }


    /**
     * Gets the controlTotals value for this Master.
     * 
     * @return controlTotals   * Specifies the action to be
     *                         taken if the entered value for the control
     * total
     *                         does not agree with the total of the debit
     * lines
     *                         on the completed document.
     */
    public java.lang.String getControlTotals() {
        return controlTotals;
    }


    /**
     * Sets the controlTotals value for this Master.
     * 
     * @param controlTotals   * Specifies the action to be
     *                         taken if the entered value for the control
     * total
     *                         does not agree with the total of the debit
     * lines
     *                         on the completed document.
     */
    public void setControlTotals(java.lang.String controlTotals) {
        this.controlTotals = controlTotals;
    }


    /**
     * Gets the destination value for this Master.
     * 
     * @return destination   * Specifies whether documents
     *                         are posted only to the Books, only to the
     *                         Intray, or to either.
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this Master.
     * 
     * @param destination   * Specifies whether documents
     *                         are posted only to the Books, only to the
     *                         Intray, or to either.
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }


    /**
     * Gets the checking value for this Master.
     * 
     * @return checking   * Specifies the type of
     *                         checking applied to documents: Books, Intray,
     * or
     *                     either.
     */
    public java.lang.String getChecking() {
        return checking;
    }


    /**
     * Sets the checking value for this Master.
     * 
     * @param checking   * Specifies the type of
     *                         checking applied to documents: Books, Intray,
     * or
     *                     either.
     */
    public void setChecking(java.lang.String checking) {
        this.checking = checking;
    }


    /**
     * Gets the authorisingUser value for this Master.
     * 
     * @return authorisingUser   * Specifies whether the
     *                         Authoring User input field is used, mandatory,
     * or not used.
     */
    public java.lang.String getAuthorisingUser() {
        return authorisingUser;
    }


    /**
     * Sets the authorisingUser value for this Master.
     * 
     * @param authorisingUser   * Specifies whether the
     *                         Authoring User input field is used, mandatory,
     * or not used.
     */
    public void setAuthorisingUser(java.lang.String authorisingUser) {
        this.authorisingUser = authorisingUser;
    }


    /**
     * Gets the validateOnAccount value for this Master.
     * 
     * @return validateOnAccount   * Specifies that validation of
     *                         individual elements is disabled and that the
     * full account code is checked when the user
     *                         completes an entry during
     *                     Input.
     */
    public boolean isValidateOnAccount() {
        return validateOnAccount;
    }


    /**
     * Sets the validateOnAccount value for this Master.
     * 
     * @param validateOnAccount   * Specifies that validation of
     *                         individual elements is disabled and that the
     * full account code is checked when the user
     *                         completes an entry during
     *                     Input.
     */
    public void setValidateOnAccount(boolean validateOnAccount) {
        this.validateOnAccount = validateOnAccount;
    }


    /**
     * Gets the periodUsage value for this Master.
     * 
     * @return periodUsage   * Specifies the type of period
     *                         to which documents generated from this master
     * can be posted.
     */
    public java.lang.String getPeriodUsage() {
        return periodUsage;
    }


    /**
     * Sets the periodUsage value for this Master.
     * 
     * @param periodUsage   * Specifies the type of period
     *                         to which documents generated from this master
     * can be posted.
     */
    public void setPeriodUsage(java.lang.String periodUsage) {
        this.periodUsage = periodUsage;
    }


    /**
     * Gets the purchaseOrdering value for this Master.
     * 
     * @return purchaseOrdering   * Specifies that the document
     *                         master is used to generate invoices for purchase
     * orders, and you want to match them against the
     *                         originating orders in
     *                     Purchasing.
     */
    public boolean isPurchaseOrdering() {
        return purchaseOrdering;
    }


    /**
     * Sets the purchaseOrdering value for this Master.
     * 
     * @param purchaseOrdering   * Specifies that the document
     *                         master is used to generate invoices for purchase
     * orders, and you want to match them against the
     *                         originating orders in
     *                     Purchasing.
     */
    public void setPurchaseOrdering(boolean purchaseOrdering) {
        this.purchaseOrdering = purchaseOrdering;
    }


    /**
     * Gets the allowDiscounts value for this Master.
     * 
     * @return allowDiscounts   * Specifies that documents
     *                         using this master can hold discount
     *                     information.
     */
    public boolean isAllowDiscounts() {
        return allowDiscounts;
    }


    /**
     * Sets the allowDiscounts value for this Master.
     * 
     * @param allowDiscounts   * Specifies that documents
     *                         using this master can hold discount
     *                     information.
     */
    public void setAllowDiscounts(boolean allowDiscounts) {
        this.allowDiscounts = allowDiscounts;
    }


    /**
     * Gets the updateTransaction value for this Master.
     * 
     * @return updateTransaction   * Specifies how the Last
     *                         Movements data for the matchable element is
     * updated.
     */
    public java.lang.String getUpdateTransaction() {
        return updateTransaction;
    }


    /**
     * Sets the updateTransaction value for this Master.
     * 
     * @param updateTransaction   * Specifies how the Last
     *                         Movements data for the matchable element is
     * updated.
     */
    public void setUpdateTransaction(java.lang.String updateTransaction) {
        this.updateTransaction = updateTransaction;
    }


    /**
     * Gets the retainValues value for this Master.
     * 
     * @return retainValues   * Specifies that specific
     *                         document line information is retained when
     * the
     *                         account code is changed during
     *                     Input.
     */
    public boolean isRetainValues() {
        return retainValues;
    }


    /**
     * Sets the retainValues value for this Master.
     * 
     * @param retainValues   * Specifies that specific
     *                         document line information is retained when
     * the
     *                         account code is changed during
     *                     Input.
     */
    public void setRetainValues(boolean retainValues) {
        this.retainValues = retainValues;
    }


    /**
     * Gets the amendInBrowse value for this Master.
     * 
     * @return amendInBrowse   * Specifies whether documents
     *                         of this type can be changed in Browse
     *                     Writeback.
     */
    public boolean isAmendInBrowse() {
        return amendInBrowse;
    }


    /**
     * Sets the amendInBrowse value for this Master.
     * 
     * @param amendInBrowse   * Specifies whether documents
     *                         of this type can be changed in Browse
     *                     Writeback.
     */
    public void setAmendInBrowse(boolean amendInBrowse) {
        this.amendInBrowse = amendInBrowse;
    }


    /**
     * Gets the headerUserExit value for this Master.
     * 
     * @return headerUserExit   * The name of the custom
     *                         program called for checking Header information
     * during Input.
     */
    public java.lang.String getHeaderUserExit() {
        return headerUserExit;
    }


    /**
     * Sets the headerUserExit value for this Master.
     * 
     * @param headerUserExit   * The name of the custom
     *                         program called for checking Header information
     * during Input.
     */
    public void setHeaderUserExit(java.lang.String headerUserExit) {
        this.headerUserExit = headerUserExit;
    }


    /**
     * Gets the matchingFromInput value for this Master.
     * 
     * @return matchingFromInput   * Specifies whether this
     *                         document master can be used for matching
     *                         documents from Input.
     */
    public boolean isMatchingFromInput() {
        return matchingFromInput;
    }


    /**
     * Sets the matchingFromInput value for this Master.
     * 
     * @param matchingFromInput   * Specifies whether this
     *                         document master can be used for matching
     *                         documents from Input.
     */
    public void setMatchingFromInput(boolean matchingFromInput) {
        this.matchingFromInput = matchingFromInput;
    }


    /**
     * Gets the inputMatchingMasterCode value for this Master.
     * 
     * @return inputMatchingMasterCode   * The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Input.
     */
    public java.lang.String getInputMatchingMasterCode() {
        return inputMatchingMasterCode;
    }


    /**
     * Sets the inputMatchingMasterCode value for this Master.
     * 
     * @param inputMatchingMasterCode   * The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Input.
     */
    public void setInputMatchingMasterCode(java.lang.String inputMatchingMasterCode) {
        this.inputMatchingMasterCode = inputMatchingMasterCode;
    }


    /**
     * Gets the matchingFromBrowse value for this Master.
     * 
     * @return matchingFromBrowse   * Specifies whether this
     *                         document master can be used for matching
     *                         documents from Browse.
     */
    public boolean isMatchingFromBrowse() {
        return matchingFromBrowse;
    }


    /**
     * Sets the matchingFromBrowse value for this Master.
     * 
     * @param matchingFromBrowse   * Specifies whether this
     *                         document master can be used for matching
     *                         documents from Browse.
     */
    public void setMatchingFromBrowse(boolean matchingFromBrowse) {
        this.matchingFromBrowse = matchingFromBrowse;
    }


    /**
     * Gets the browseMatchingMasterCode value for this Master.
     * 
     * @return browseMatchingMasterCode   * The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Browse.
     */
    public java.lang.String getBrowseMatchingMasterCode() {
        return browseMatchingMasterCode;
    }


    /**
     * Sets the browseMatchingMasterCode value for this Master.
     * 
     * @param browseMatchingMasterCode   * The
     *                         code of the matching master to be used for
     * matching documents from
     *                     Browse.
     */
    public void setBrowseMatchingMasterCode(java.lang.String browseMatchingMasterCode) {
        this.browseMatchingMasterCode = browseMatchingMasterCode;
    }


    /**
     * Gets the assetDoc value for this Master.
     * 
     * @return assetDoc   * Specifies that assets can be
     *                         created with this document
     *                     master.
     */
    public java.lang.String getAssetDoc() {
        return assetDoc;
    }


    /**
     * Sets the assetDoc value for this Master.
     * 
     * @param assetDoc   * Specifies that assets can be
     *                         created with this document
     *                     master.
     */
    public void setAssetDoc(java.lang.String assetDoc) {
        this.assetDoc = assetDoc;
    }


    /**
     * Gets the selfProportioning value for this Master.
     * 
     * @return selfProportioning   * Specifies whether this
     *                         document master generates self-proportioning
     * documents.
     */
    public boolean isSelfProportioning() {
        return selfProportioning;
    }


    /**
     * Sets the selfProportioning value for this Master.
     * 
     * @param selfProportioning   * Specifies whether this
     *                         document master generates self-proportioning
     * documents.
     */
    public void setSelfProportioning(boolean selfProportioning) {
        this.selfProportioning = selfProportioning;
    }


    /**
     * Gets the recurring value for this Master.
     * 
     * @return recurring   * Specifies whether this
     *                         document master generates recurring
     *                     documents.
     */
    public boolean isRecurring() {
        return recurring;
    }


    /**
     * Sets the recurring value for this Master.
     * 
     * @param recurring   * Specifies whether this
     *                         document master generates recurring
     *                     documents.
     */
    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }


    /**
     * Gets the reversing value for this Master.
     * 
     * @return reversing   * Specifies whether this
     *                         document master generates reversing
     *                     documents.
     */
    public boolean isReversing() {
        return reversing;
    }


    /**
     * Sets the reversing value for this Master.
     * 
     * @param reversing   * Specifies whether this
     *                         document master generates reversing
     *                     documents.
     */
    public void setReversing(boolean reversing) {
        this.reversing = reversing;
    }


    /**
     * Gets the dueDate value for this Master.
     * 
     * @return dueDate
     */
    public com.coda.www.efinance.schemas.documentmaster.DueDate getDueDate() {
        return dueDate;
    }


    /**
     * Sets the dueDate value for this Master.
     * 
     * @param dueDate
     */
    public void setDueDate(com.coda.www.efinance.schemas.documentmaster.DueDate dueDate) {
        this.dueDate = dueDate;
    }


    /**
     * Gets the valueDate value for this Master.
     * 
     * @return valueDate
     */
    public com.coda.www.efinance.schemas.documentmaster.ValueDate getValueDate() {
        return valueDate;
    }


    /**
     * Sets the valueDate value for this Master.
     * 
     * @param valueDate
     */
    public void setValueDate(com.coda.www.efinance.schemas.documentmaster.ValueDate valueDate) {
        this.valueDate = valueDate;
    }


    /**
     * Gets the currency value for this Master.
     * 
     * @return currency
     */
    public com.coda.www.efinance.schemas.documentmaster.Currency getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this Master.
     * 
     * @param currency
     */
    public void setCurrency(com.coda.www.efinance.schemas.documentmaster.Currency currency) {
        this.currency = currency;
    }


    /**
     * Gets the tax value for this Master.
     * 
     * @return tax   * This
     *                         element holds tax settings.
     */
    public com.coda.www.efinance.schemas.documentmaster.DocTax getTax() {
        return tax;
    }


    /**
     * Sets the tax value for this Master.
     * 
     * @param tax   * This
     *                         element holds tax settings.
     */
    public void setTax(com.coda.www.efinance.schemas.documentmaster.DocTax tax) {
        this.tax = tax;
    }


    /**
     * Gets the pay value for this Master.
     * 
     * @return pay
     */
    public com.coda.www.efinance.schemas.documentmaster.Pay getPay() {
        return pay;
    }


    /**
     * Sets the pay value for this Master.
     * 
     * @param pay
     */
    public void setPay(com.coda.www.efinance.schemas.documentmaster.Pay pay) {
        this.pay = pay;
    }


    /**
     * Gets the quantities value for this Master.
     * 
     * @return quantities
     */
    public com.coda.www.efinance.schemas.documentmaster.Quantities getQuantities() {
        return quantities;
    }


    /**
     * Sets the quantities value for this Master.
     * 
     * @param quantities
     */
    public void setQuantities(com.coda.www.efinance.schemas.documentmaster.Quantities quantities) {
        this.quantities = quantities;
    }


    /**
     * Gets the summaryLines value for this Master.
     * 
     * @return summaryLines
     */
    public com.coda.www.efinance.schemas.documentmaster.SummaryLines getSummaryLines() {
        return summaryLines;
    }


    /**
     * Sets the summaryLines value for this Master.
     * 
     * @param summaryLines
     */
    public void setSummaryLines(com.coda.www.efinance.schemas.documentmaster.SummaryLines summaryLines) {
        this.summaryLines = summaryLines;
    }


    /**
     * Gets the analysisLines value for this Master.
     * 
     * @return analysisLines
     */
    public com.coda.www.efinance.schemas.documentmaster.AnalysisLines getAnalysisLines() {
        return analysisLines;
    }


    /**
     * Sets the analysisLines value for this Master.
     * 
     * @param analysisLines
     */
    public void setAnalysisLines(com.coda.www.efinance.schemas.documentmaster.AnalysisLines analysisLines) {
        this.analysisLines = analysisLines;
    }


    /**
     * Gets the intercompany value for this Master.
     * 
     * @return intercompany   * This element holds
     *                         InterCompany settings.
     */
    public com.coda.www.efinance.schemas.documentmaster.Intercompany getIntercompany() {
        return intercompany;
    }


    /**
     * Sets the intercompany value for this Master.
     * 
     * @param intercompany   * This element holds
     *                         InterCompany settings.
     */
    public void setIntercompany(com.coda.www.efinance.schemas.documentmaster.Intercompany intercompany) {
        this.intercompany = intercompany;
    }


    /**
     * Gets the balancingElements value for this Master.
     * 
     * @return balancingElements
     */
    public com.coda.www.efinance.schemas.documentmaster.BalancingElements getBalancingElements() {
        return balancingElements;
    }


    /**
     * Sets the balancingElements value for this Master.
     * 
     * @param balancingElements
     */
    public void setBalancingElements(com.coda.www.efinance.schemas.documentmaster.BalancingElements balancingElements) {
        this.balancingElements = balancingElements;
    }


    /**
     * Gets the externalReferences value for this Master.
     * 
     * @return externalReferences   * This element holds
     *                         information about external
     *                     references.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExternalReferences getExternalReferences() {
        return externalReferences;
    }


    /**
     * Sets the externalReferences value for this Master.
     * 
     * @param externalReferences   * This element holds
     *                         information about external
     *                     references.
     */
    public void setExternalReferences(com.coda.www.efinance.schemas.documentmaster.ExternalReferences externalReferences) {
        this.externalReferences = externalReferences;
    }


    /**
     * Gets the userReferences value for this Master.
     * 
     * @return userReferences   * This element holds
     *                         information about user
     *                     references.
     */
    public com.coda.www.efinance.schemas.documentmaster.UserReferences getUserReferences() {
        return userReferences;
    }


    /**
     * Sets the userReferences value for this Master.
     * 
     * @param userReferences   * This element holds
     *                         information about user
     *                     references.
     */
    public void setUserReferences(com.coda.www.efinance.schemas.documentmaster.UserReferences userReferences) {
        this.userReferences = userReferences;
    }


    /**
     * Gets the recurringDetails value for this Master.
     * 
     * @return recurringDetails   * This
     *                         element holds recurring document
     *                     settings.
     */
    public com.coda.www.efinance.schemas.documentmaster.Recurring getRecurringDetails() {
        return recurringDetails;
    }


    /**
     * Sets the recurringDetails value for this Master.
     * 
     * @param recurringDetails   * This
     *                         element holds recurring document
     *                     settings.
     */
    public void setRecurringDetails(com.coda.www.efinance.schemas.documentmaster.Recurring recurringDetails) {
        this.recurringDetails = recurringDetails;
    }


    /**
     * Gets the preDefinedLines value for this Master.
     * 
     * @return preDefinedLines   * This element holds
     *                         pre-defined line settings.
     */
    public com.coda.www.efinance.schemas.documentmaster.PreDefinedLines getPreDefinedLines() {
        return preDefinedLines;
    }


    /**
     * Sets the preDefinedLines value for this Master.
     * 
     * @param preDefinedLines   * This element holds
     *                         pre-defined line settings.
     */
    public void setPreDefinedLines(com.coda.www.efinance.schemas.documentmaster.PreDefinedLines preDefinedLines) {
        this.preDefinedLines = preDefinedLines;
    }


    /**
     * Gets the fieldAccess value for this Master.
     * 
     * @return fieldAccess   * This element holds
     *                         information specifying whether certain fields
     * are included in the tab sequence during
     *                     Input.
     */
    public com.coda.www.efinance.schemas.documentmaster.FieldAccess getFieldAccess() {
        return fieldAccess;
    }


    /**
     * Sets the fieldAccess value for this Master.
     * 
     * @param fieldAccess   * This element holds
     *                         information specifying whether certain fields
     * are included in the tab sequence during
     *                     Input.
     */
    public void setFieldAccess(com.coda.www.efinance.schemas.documentmaster.FieldAccess fieldAccess) {
        this.fieldAccess = fieldAccess;
    }


    /**
     * Gets the headerExtension value for this Master.
     * 
     * @return headerExtension   * The
     *                         extension master code for the document header
     * validation extension that you want to check the
     *                         headers of documents generated from this
     *                     master.
     */
    public com.coda.www.efinance.schemas.common.ExtensionRef getHeaderExtension() {
        return headerExtension;
    }


    /**
     * Sets the headerExtension value for this Master.
     * 
     * @param headerExtension   * The
     *                         extension master code for the document header
     * validation extension that you want to check the
     *                         headers of documents generated from this
     *                     master.
     */
    public void setHeaderExtension(com.coda.www.efinance.schemas.common.ExtensionRef headerExtension) {
        this.headerExtension = headerExtension;
    }


    /**
     * Gets the extRefExtension value for this Master.
     * 
     * @return extRefExtension   * The
     *                         extension master code for the document
     *                         validation extension that you want to validate
     * external references for documents generated from
     *                         this master.
     */
    public com.coda.www.efinance.schemas.common.ExtensionRef getExtRefExtension() {
        return extRefExtension;
    }


    /**
     * Sets the extRefExtension value for this Master.
     * 
     * @param extRefExtension   * The
     *                         extension master code for the document
     *                         validation extension that you want to validate
     * external references for documents generated from
     *                         this master.
     */
    public void setExtRefExtension(com.coda.www.efinance.schemas.common.ExtensionRef extRefExtension) {
        this.extRefExtension = extRefExtension;
    }


    /**
     * Gets the prevIntrayDel value for this Master.
     * 
     * @return prevIntrayDel   * Specifies that the document
     *                         cannot be deleted once posted to the
     *                     Intray.
     */
    public boolean isPrevIntrayDel() {
        return prevIntrayDel;
    }


    /**
     * Sets the prevIntrayDel value for this Master.
     * 
     * @param prevIntrayDel   * Specifies that the document
     *                         cannot be deleted once posted to the
     *                     Intray.
     */
    public void setPrevIntrayDel(boolean prevIntrayDel) {
        this.prevIntrayDel = prevIntrayDel;
    }


    /**
     * Gets the prevIntrayMod value for this Master.
     * 
     * @return prevIntrayMod   * Specifies that the document
     *                         cannot be modified once posted to the
     *                     Intray.
     */
    public boolean isPrevIntrayMod() {
        return prevIntrayMod;
    }


    /**
     * Sets the prevIntrayMod value for this Master.
     * 
     * @param prevIntrayMod   * Specifies that the document
     *                         cannot be modified once posted to the
     *                     Intray.
     */
    public void setPrevIntrayMod(boolean prevIntrayMod) {
        this.prevIntrayMod = prevIntrayMod;
    }


    /**
     * Gets the createDate value for this Master.
     * 
     * @return createDate   * The date the record was
     *                     created.
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this Master.
     * 
     * @param createDate   * The date the record was
     *                     created.
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the modifyDate value for this Master.
     * 
     * @return modifyDate   * The date when the record was
     *                         last updated.
     */
    public java.util.Calendar getModifyDate() {
        return modifyDate;
    }


    /**
     * Sets the modifyDate value for this Master.
     * 
     * @param modifyDate   * The date when the record was
     *                         last updated.
     */
    public void setModifyDate(java.util.Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }


    /**
     * Gets the user value for this Master.
     * 
     * @return user   * The user who updated the
     *                     record.
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this Master.
     * 
     * @param user   * The user who updated the
     *                     record.
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the assetPerLine value for this Master.
     * 
     * @return assetPerLine   * Specifies that a separate
     *                         asset is created from each document
     *                     line.
     */
    public java.lang.Boolean getAssetPerLine() {
        return assetPerLine;
    }


    /**
     * Sets the assetPerLine value for this Master.
     * 
     * @param assetPerLine   * Specifies that a separate
     *                         asset is created from each document
     *                     line.
     */
    public void setAssetPerLine(java.lang.Boolean assetPerLine) {
        this.assetPerLine = assetPerLine;
    }


    /**
     * Gets the workflowRequired value for this Master.
     * 
     * @return workflowRequired   * The
     *                         type of workflow authorisation
     *                     required.
     */
    public java.lang.String getWorkflowRequired() {
        return workflowRequired;
    }


    /**
     * Sets the workflowRequired value for this Master.
     * 
     * @param workflowRequired   * The
     *                         type of workflow authorisation
     *                     required.
     */
    public void setWorkflowRequired(java.lang.String workflowRequired) {
        this.workflowRequired = workflowRequired;
    }


    /**
     * Gets the workItemExplodePresenter value for this Master.
     * 
     * @return workItemExplodePresenter   * The browse details presenter
     *                         to use when a user views the document details
     * from their workflow work
     *                     list.
     */
    public java.lang.String getWorkItemExplodePresenter() {
        return workItemExplodePresenter;
    }


    /**
     * Sets the workItemExplodePresenter value for this Master.
     * 
     * @param workItemExplodePresenter   * The browse details presenter
     *                         to use when a user views the document details
     * from their workflow work
     *                     list.
     */
    public void setWorkItemExplodePresenter(java.lang.String workItemExplodePresenter) {
        this.workItemExplodePresenter = workItemExplodePresenter;
    }


    /**
     * Gets the positionHierarchy value for this Master.
     * 
     * @return positionHierarchy   * The default position
     *                         hierarchy to use for
     *                     authorisation.
     */
    public java.lang.String getPositionHierarchy() {
        return positionHierarchy;
    }


    /**
     * Sets the positionHierarchy value for this Master.
     * 
     * @param positionHierarchy   * The default position
     *                         hierarchy to use for
     *                     authorisation.
     */
    public void setPositionHierarchy(java.lang.String positionHierarchy) {
        this.positionHierarchy = positionHierarchy;
    }


    /**
     * Gets the intrayWorkflow value for this Master.
     * 
     * @return intrayWorkflow   * The workflow process
     *                         definition to use when posting this type of
     * document to the Intray.
     */
    public java.lang.String getIntrayWorkflow() {
        return intrayWorkflow;
    }


    /**
     * Sets the intrayWorkflow value for this Master.
     * 
     * @param intrayWorkflow   * The workflow process
     *                         definition to use when posting this type of
     * document to the Intray.
     */
    public void setIntrayWorkflow(java.lang.String intrayWorkflow) {
        this.intrayWorkflow = intrayWorkflow;
    }


    /**
     * Gets the protectIntrayWorkflow value for this Master.
     * 
     * @return protectIntrayWorkflow   * If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         Intray cannot be changed.
     */
    public boolean isProtectIntrayWorkflow() {
        return protectIntrayWorkflow;
    }


    /**
     * Sets the protectIntrayWorkflow value for this Master.
     * 
     * @param protectIntrayWorkflow   * If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         Intray cannot be changed.
     */
    public void setProtectIntrayWorkflow(boolean protectIntrayWorkflow) {
        this.protectIntrayWorkflow = protectIntrayWorkflow;
    }


    /**
     * Gets the confirmIntrayWorkflow value for this Master.
     * 
     * @return confirmIntrayWorkflow   * If set, a prompt appears at
     *                         Input asking whether the user would like to
     * start a workflow instance.
     */
    public boolean isConfirmIntrayWorkflow() {
        return confirmIntrayWorkflow;
    }


    /**
     * Sets the confirmIntrayWorkflow value for this Master.
     * 
     * @param confirmIntrayWorkflow   * If set, a prompt appears at
     *                         Input asking whether the user would like to
     * start a workflow instance.
     */
    public void setConfirmIntrayWorkflow(boolean confirmIntrayWorkflow) {
        this.confirmIntrayWorkflow = confirmIntrayWorkflow;
    }


    /**
     * Gets the allowWorkflowIntrayEdits value for this Master.
     * 
     * @return allowWorkflowIntrayEdits   * If set, users can modify this
     *                         type of document while it is in the Intray,
     * providing it is not yet
     *                     authorised.
     */
    public boolean isAllowWorkflowIntrayEdits() {
        return allowWorkflowIntrayEdits;
    }


    /**
     * Sets the allowWorkflowIntrayEdits value for this Master.
     * 
     * @param allowWorkflowIntrayEdits   * If set, users can modify this
     *                         type of document while it is in the Intray,
     * providing it is not yet
     *                     authorised.
     */
    public void setAllowWorkflowIntrayEdits(boolean allowWorkflowIntrayEdits) {
        this.allowWorkflowIntrayEdits = allowWorkflowIntrayEdits;
    }


    /**
     * Gets the booksWorkflow value for this Master.
     * 
     * @return booksWorkflow   * The workflow process
     *                         definition to use when posting this type of
     * document to the books.
     */
    public java.lang.String getBooksWorkflow() {
        return booksWorkflow;
    }


    /**
     * Sets the booksWorkflow value for this Master.
     * 
     * @param booksWorkflow   * The workflow process
     *                         definition to use when posting this type of
     * document to the books.
     */
    public void setBooksWorkflow(java.lang.String booksWorkflow) {
        this.booksWorkflow = booksWorkflow;
    }


    /**
     * Gets the protectBooksWorkflow value for this Master.
     * 
     * @return protectBooksWorkflow   * If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         books cannot be changed.
     */
    public boolean isProtectBooksWorkflow() {
        return protectBooksWorkflow;
    }


    /**
     * Sets the protectBooksWorkflow value for this Master.
     * 
     * @param protectBooksWorkflow   * If set, the workflow process
     *                         definition used when posting a document to
     * the
     *                         books cannot be changed.
     */
    public void setProtectBooksWorkflow(boolean protectBooksWorkflow) {
        this.protectBooksWorkflow = protectBooksWorkflow;
    }


    /**
     * Gets the promptForAuthorisingUser value for this Master.
     * 
     * @return promptForAuthorisingUser   * If set, the user is prompted
     *                         for an authorising user when submitting
     *                         documents of this type into
     *                     Workflow.
     */
    public boolean isPromptForAuthorisingUser() {
        return promptForAuthorisingUser;
    }


    /**
     * Sets the promptForAuthorisingUser value for this Master.
     * 
     * @param promptForAuthorisingUser   * If set, the user is prompted
     *                         for an authorising user when submitting
     *                         documents of this type into
     *                     Workflow.
     */
    public void setPromptForAuthorisingUser(boolean promptForAuthorisingUser) {
        this.promptForAuthorisingUser = promptForAuthorisingUser;
    }


    /**
     * Gets the isReserved value for this Master.
     * 
     * @return isReserved   * If
     *                         set, the document master is
     *                     reserved.
     */
    public boolean isIsReserved() {
        return isReserved;
    }


    /**
     * Sets the isReserved value for this Master.
     * 
     * @param isReserved   * If
     *                         set, the document master is
     *                     reserved.
     */
    public void setIsReserved(boolean isReserved) {
        this.isReserved = isReserved;
    }


    /**
     * Gets the reservingApplicationId value for this Master.
     * 
     * @return reservingApplicationId   * The ID
     *                         of the CODA application which has reserved
     * the
     *                         document master.
     */
    public java.lang.String getReservingApplicationId() {
        return reservingApplicationId;
    }


    /**
     * Sets the reservingApplicationId value for this Master.
     * 
     * @param reservingApplicationId   * The ID
     *                         of the CODA application which has reserved
     * the
     *                         document master.
     */
    public void setReservingApplicationId(java.lang.String reservingApplicationId) {
        this.reservingApplicationId = reservingApplicationId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Master)) return false;
        Master other = (Master) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.timeStamp == other.getTimeStamp() &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.cancelDoc==null && other.getCancelDoc()==null) || 
             (this.cancelDoc!=null &&
              this.cancelDoc.equals(other.getCancelDoc()))) &&
            this.cancelMatch == other.isCancelMatch() &&
            this.addToTurnovers == other.isAddToTurnovers() &&
            this.batch == other.isBatch() &&
            ((this.userStatus==null && other.getUserStatus()==null) || 
             (this.userStatus!=null &&
              this.userStatus.equals(other.getUserStatus()))) &&
            ((this.payStatus==null && other.getPayStatus()==null) || 
             (this.payStatus!=null &&
              this.payStatus.equals(other.getPayStatus()))) &&
            this.requireDescription == other.isRequireDescription() &&
            ((this.bankStatement==null && other.getBankStatement()==null) || 
             (this.bankStatement!=null &&
              this.bankStatement.equals(other.getBankStatement()))) &&
            ((this.numberRule==null && other.getNumberRule()==null) || 
             (this.numberRule!=null &&
              this.numberRule.equals(other.getNumberRule()))) &&
            ((this.numberFormat==null && other.getNumberFormat()==null) || 
             (this.numberFormat!=null &&
              this.numberFormat.equals(other.getNumberFormat()))) &&
            ((this.controlTotals==null && other.getControlTotals()==null) || 
             (this.controlTotals!=null &&
              this.controlTotals.equals(other.getControlTotals()))) &&
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination()))) &&
            ((this.checking==null && other.getChecking()==null) || 
             (this.checking!=null &&
              this.checking.equals(other.getChecking()))) &&
            ((this.authorisingUser==null && other.getAuthorisingUser()==null) || 
             (this.authorisingUser!=null &&
              this.authorisingUser.equals(other.getAuthorisingUser()))) &&
            this.validateOnAccount == other.isValidateOnAccount() &&
            ((this.periodUsage==null && other.getPeriodUsage()==null) || 
             (this.periodUsage!=null &&
              this.periodUsage.equals(other.getPeriodUsage()))) &&
            this.purchaseOrdering == other.isPurchaseOrdering() &&
            this.allowDiscounts == other.isAllowDiscounts() &&
            ((this.updateTransaction==null && other.getUpdateTransaction()==null) || 
             (this.updateTransaction!=null &&
              this.updateTransaction.equals(other.getUpdateTransaction()))) &&
            this.retainValues == other.isRetainValues() &&
            this.amendInBrowse == other.isAmendInBrowse() &&
            ((this.headerUserExit==null && other.getHeaderUserExit()==null) || 
             (this.headerUserExit!=null &&
              this.headerUserExit.equals(other.getHeaderUserExit()))) &&
            this.matchingFromInput == other.isMatchingFromInput() &&
            ((this.inputMatchingMasterCode==null && other.getInputMatchingMasterCode()==null) || 
             (this.inputMatchingMasterCode!=null &&
              this.inputMatchingMasterCode.equals(other.getInputMatchingMasterCode()))) &&
            this.matchingFromBrowse == other.isMatchingFromBrowse() &&
            ((this.browseMatchingMasterCode==null && other.getBrowseMatchingMasterCode()==null) || 
             (this.browseMatchingMasterCode!=null &&
              this.browseMatchingMasterCode.equals(other.getBrowseMatchingMasterCode()))) &&
            ((this.assetDoc==null && other.getAssetDoc()==null) || 
             (this.assetDoc!=null &&
              this.assetDoc.equals(other.getAssetDoc()))) &&
            this.selfProportioning == other.isSelfProportioning() &&
            this.recurring == other.isRecurring() &&
            this.reversing == other.isReversing() &&
            ((this.dueDate==null && other.getDueDate()==null) || 
             (this.dueDate!=null &&
              this.dueDate.equals(other.getDueDate()))) &&
            ((this.valueDate==null && other.getValueDate()==null) || 
             (this.valueDate!=null &&
              this.valueDate.equals(other.getValueDate()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency()))) &&
            ((this.tax==null && other.getTax()==null) || 
             (this.tax!=null &&
              this.tax.equals(other.getTax()))) &&
            ((this.pay==null && other.getPay()==null) || 
             (this.pay!=null &&
              this.pay.equals(other.getPay()))) &&
            ((this.quantities==null && other.getQuantities()==null) || 
             (this.quantities!=null &&
              this.quantities.equals(other.getQuantities()))) &&
            ((this.summaryLines==null && other.getSummaryLines()==null) || 
             (this.summaryLines!=null &&
              this.summaryLines.equals(other.getSummaryLines()))) &&
            ((this.analysisLines==null && other.getAnalysisLines()==null) || 
             (this.analysisLines!=null &&
              this.analysisLines.equals(other.getAnalysisLines()))) &&
            ((this.intercompany==null && other.getIntercompany()==null) || 
             (this.intercompany!=null &&
              this.intercompany.equals(other.getIntercompany()))) &&
            ((this.balancingElements==null && other.getBalancingElements()==null) || 
             (this.balancingElements!=null &&
              this.balancingElements.equals(other.getBalancingElements()))) &&
            ((this.externalReferences==null && other.getExternalReferences()==null) || 
             (this.externalReferences!=null &&
              this.externalReferences.equals(other.getExternalReferences()))) &&
            ((this.userReferences==null && other.getUserReferences()==null) || 
             (this.userReferences!=null &&
              this.userReferences.equals(other.getUserReferences()))) &&
            ((this.recurringDetails==null && other.getRecurringDetails()==null) || 
             (this.recurringDetails!=null &&
              this.recurringDetails.equals(other.getRecurringDetails()))) &&
            ((this.preDefinedLines==null && other.getPreDefinedLines()==null) || 
             (this.preDefinedLines!=null &&
              this.preDefinedLines.equals(other.getPreDefinedLines()))) &&
            ((this.fieldAccess==null && other.getFieldAccess()==null) || 
             (this.fieldAccess!=null &&
              this.fieldAccess.equals(other.getFieldAccess()))) &&
            ((this.headerExtension==null && other.getHeaderExtension()==null) || 
             (this.headerExtension!=null &&
              this.headerExtension.equals(other.getHeaderExtension()))) &&
            ((this.extRefExtension==null && other.getExtRefExtension()==null) || 
             (this.extRefExtension!=null &&
              this.extRefExtension.equals(other.getExtRefExtension()))) &&
            this.prevIntrayDel == other.isPrevIntrayDel() &&
            this.prevIntrayMod == other.isPrevIntrayMod() &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.modifyDate==null && other.getModifyDate()==null) || 
             (this.modifyDate!=null &&
              this.modifyDate.equals(other.getModifyDate()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.assetPerLine==null && other.getAssetPerLine()==null) || 
             (this.assetPerLine!=null &&
              this.assetPerLine.equals(other.getAssetPerLine()))) &&
            ((this.workflowRequired==null && other.getWorkflowRequired()==null) || 
             (this.workflowRequired!=null &&
              this.workflowRequired.equals(other.getWorkflowRequired()))) &&
            ((this.workItemExplodePresenter==null && other.getWorkItemExplodePresenter()==null) || 
             (this.workItemExplodePresenter!=null &&
              this.workItemExplodePresenter.equals(other.getWorkItemExplodePresenter()))) &&
            ((this.positionHierarchy==null && other.getPositionHierarchy()==null) || 
             (this.positionHierarchy!=null &&
              this.positionHierarchy.equals(other.getPositionHierarchy()))) &&
            ((this.intrayWorkflow==null && other.getIntrayWorkflow()==null) || 
             (this.intrayWorkflow!=null &&
              this.intrayWorkflow.equals(other.getIntrayWorkflow()))) &&
            this.protectIntrayWorkflow == other.isProtectIntrayWorkflow() &&
            this.confirmIntrayWorkflow == other.isConfirmIntrayWorkflow() &&
            this.allowWorkflowIntrayEdits == other.isAllowWorkflowIntrayEdits() &&
            ((this.booksWorkflow==null && other.getBooksWorkflow()==null) || 
             (this.booksWorkflow!=null &&
              this.booksWorkflow.equals(other.getBooksWorkflow()))) &&
            this.protectBooksWorkflow == other.isProtectBooksWorkflow() &&
            this.promptForAuthorisingUser == other.isPromptForAuthorisingUser() &&
            this.isReserved == other.isIsReserved() &&
            ((this.reservingApplicationId==null && other.getReservingApplicationId()==null) || 
             (this.reservingApplicationId!=null &&
              this.reservingApplicationId.equals(other.getReservingApplicationId())));
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
        _hashCode += getTimeStamp();
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getCancelDoc() != null) {
            _hashCode += getCancelDoc().hashCode();
        }
        _hashCode += (isCancelMatch() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAddToTurnovers() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isBatch() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUserStatus() != null) {
            _hashCode += getUserStatus().hashCode();
        }
        if (getPayStatus() != null) {
            _hashCode += getPayStatus().hashCode();
        }
        _hashCode += (isRequireDescription() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBankStatement() != null) {
            _hashCode += getBankStatement().hashCode();
        }
        if (getNumberRule() != null) {
            _hashCode += getNumberRule().hashCode();
        }
        if (getNumberFormat() != null) {
            _hashCode += getNumberFormat().hashCode();
        }
        if (getControlTotals() != null) {
            _hashCode += getControlTotals().hashCode();
        }
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        if (getChecking() != null) {
            _hashCode += getChecking().hashCode();
        }
        if (getAuthorisingUser() != null) {
            _hashCode += getAuthorisingUser().hashCode();
        }
        _hashCode += (isValidateOnAccount() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPeriodUsage() != null) {
            _hashCode += getPeriodUsage().hashCode();
        }
        _hashCode += (isPurchaseOrdering() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAllowDiscounts() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getUpdateTransaction() != null) {
            _hashCode += getUpdateTransaction().hashCode();
        }
        _hashCode += (isRetainValues() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAmendInBrowse() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getHeaderUserExit() != null) {
            _hashCode += getHeaderUserExit().hashCode();
        }
        _hashCode += (isMatchingFromInput() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInputMatchingMasterCode() != null) {
            _hashCode += getInputMatchingMasterCode().hashCode();
        }
        _hashCode += (isMatchingFromBrowse() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBrowseMatchingMasterCode() != null) {
            _hashCode += getBrowseMatchingMasterCode().hashCode();
        }
        if (getAssetDoc() != null) {
            _hashCode += getAssetDoc().hashCode();
        }
        _hashCode += (isSelfProportioning() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isRecurring() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReversing() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDueDate() != null) {
            _hashCode += getDueDate().hashCode();
        }
        if (getValueDate() != null) {
            _hashCode += getValueDate().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        if (getTax() != null) {
            _hashCode += getTax().hashCode();
        }
        if (getPay() != null) {
            _hashCode += getPay().hashCode();
        }
        if (getQuantities() != null) {
            _hashCode += getQuantities().hashCode();
        }
        if (getSummaryLines() != null) {
            _hashCode += getSummaryLines().hashCode();
        }
        if (getAnalysisLines() != null) {
            _hashCode += getAnalysisLines().hashCode();
        }
        if (getIntercompany() != null) {
            _hashCode += getIntercompany().hashCode();
        }
        if (getBalancingElements() != null) {
            _hashCode += getBalancingElements().hashCode();
        }
        if (getExternalReferences() != null) {
            _hashCode += getExternalReferences().hashCode();
        }
        if (getUserReferences() != null) {
            _hashCode += getUserReferences().hashCode();
        }
        if (getRecurringDetails() != null) {
            _hashCode += getRecurringDetails().hashCode();
        }
        if (getPreDefinedLines() != null) {
            _hashCode += getPreDefinedLines().hashCode();
        }
        if (getFieldAccess() != null) {
            _hashCode += getFieldAccess().hashCode();
        }
        if (getHeaderExtension() != null) {
            _hashCode += getHeaderExtension().hashCode();
        }
        if (getExtRefExtension() != null) {
            _hashCode += getExtRefExtension().hashCode();
        }
        _hashCode += (isPrevIntrayDel() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isPrevIntrayMod() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getModifyDate() != null) {
            _hashCode += getModifyDate().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getAssetPerLine() != null) {
            _hashCode += getAssetPerLine().hashCode();
        }
        if (getWorkflowRequired() != null) {
            _hashCode += getWorkflowRequired().hashCode();
        }
        if (getWorkItemExplodePresenter() != null) {
            _hashCode += getWorkItemExplodePresenter().hashCode();
        }
        if (getPositionHierarchy() != null) {
            _hashCode += getPositionHierarchy().hashCode();
        }
        if (getIntrayWorkflow() != null) {
            _hashCode += getIntrayWorkflow().hashCode();
        }
        _hashCode += (isProtectIntrayWorkflow() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isConfirmIntrayWorkflow() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isAllowWorkflowIntrayEdits() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getBooksWorkflow() != null) {
            _hashCode += getBooksWorkflow().hashCode();
        }
        _hashCode += (isProtectBooksWorkflow() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isPromptForAuthorisingUser() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isIsReserved() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReservingApplicationId() != null) {
            _hashCode += getReservingApplicationId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Master.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Master"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CancelDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelMatch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CancelMatch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addToTurnovers");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AddToTurnovers"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("batch");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Batch"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PayStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requireDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "RequireDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("bankStatement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BankStatement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "NumberRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numberFormat");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "NumberFormat"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("controlTotals");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ControlTotals"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Checking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authorisingUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AuthorisingUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validateOnAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ValidateOnAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodUsage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PeriodUsage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("purchaseOrdering");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PurchaseOrdering"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowDiscounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AllowDiscounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateTransaction");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UpdateTransaction"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retainValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "RetainValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amendInBrowse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AmendInBrowse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerUserExit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "HeaderUserExit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchingFromInput");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "MatchingFromInput"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inputMatchingMasterCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "InputMatchingMasterCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchingFromBrowse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "MatchingFromBrowse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("browseMatchingMasterCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BrowseMatchingMasterCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AssetDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selfProportioning");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "SelfProportioning"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurring");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Recurring"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reversing");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reversing"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DueDate"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ValueDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ValueDate"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Currency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Currency"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocTax"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Pay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Pay"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Quantities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Quantities"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summaryLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "SummaryLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "SummaryLines"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("analysisLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AnalysisLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AnalysisLines"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intercompany");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Intercompany"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Intercompany"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balancingElements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BalancingElements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BalancingElements"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalReferences");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExternalReferences"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExternalReferences"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userReferences");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReferences"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReferences"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recurringDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "RecurringDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Recurring"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("preDefinedLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PreDefinedLines"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fieldAccess");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "FieldAccess"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "FieldAccess"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("headerExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "HeaderExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extRefExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prevIntrayDel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PrevIntrayDel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("prevIntrayMod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PrevIntrayMod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CreateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ModifyDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetPerLine");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AssetPerLine"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowRequired");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "WorkflowRequired"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workItemExplodePresenter");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "WorkItemExplodePresenter"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionHierarchy");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PositionHierarchy"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "IntrayWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectIntrayWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectIntrayWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("confirmIntrayWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ConfirmIntrayWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allowWorkflowIntrayEdits");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AllowWorkflowIntrayEdits"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("booksWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BooksWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectBooksWorkflow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectBooksWorkflow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promptForAuthorisingUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PromptForAuthorisingUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isReserved");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "IsReserved"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reservingApplicationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ReservingApplicationId"));
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
