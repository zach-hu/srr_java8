/**
 * Master.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;

public abstract class Master  implements java.io.Serializable {
    /* The TimeStamp value for this
     *                         element master in the
     *                     database. */
    private java.lang.Short timeStamp;

    /* The TimeStamp value for this
     *                         element master in the oas_elmtaxes
     *                     table. */
    private java.lang.Short taxesTimeStamp;

    /* The code for the company in
     *                         which the element master is being
     *                     maintained. */
    private java.lang.String cmpCode;

    /* The
     *                         element code. */
    private java.lang.String code;

    /* The
     *                         level at which this element is defined. A
     * number
     *                         from 1 to 8 inclusive. */
    private short level;

    /* The full name for the
     *                     element. */
    private java.lang.String name;

    /* The abbreviated name for the
     *                     element. */
    private java.lang.String shortName;

    /* A
     *                         currency code identifying the currency
     *                         associated with this
     *                     element. */
    private java.lang.String currencyCode;

    /* Indicates whether the element
     *                         can be subanalysed. */
    private java.lang.String subAnalyse;

    /* The code for the tax master
     *                         for this element. */
    private java.lang.String taxCode;

    /* Identifies the account type
     *                         of accounts which use this
     *                     element. */
    private java.lang.String accountType;

    /* The
     *                         date on which the element was
     *                     created. */
    private java.util.Calendar dateAccountOpened;

    /* The first period in which
     *                         documents may be posted to this
     *                     element. */
    private java.lang.String startPeriod;

    /* The last period in which
     *                         documents may be posted to this
     *                     element. */
    private java.lang.String endPeriod;

    /* Indicates whether users must
     *                         enter a description in Input against any
     *                         document line posted to this
     *                     element. */
    private java.lang.Boolean compulsoryDescr;

    /* Indicates whether turnover
     *                         values are maintained for the element for
     * each
     *                     period. */
    private java.lang.Boolean keepTurnover;

    /* The
     *                         code for the account summary master for this
     * element. */
    private java.lang.String accountSummary;

    /* The
     *                         credit status of this
     *                     element. */
    private java.lang.String elementStatus;

    /* The
     *                         name of the DLL used for extended field
     *                     validation. */
    private java.lang.String externalValidation;

    /* A
     *                         reporting field. */
    private java.lang.String memoStatus;

    /* The
     *                         account to which balancing entries are posted.
     * If you are balancing at element level 1, an
     *                         asterisk represents the first element of the
     * account code. If you are balancing at elements 1
     *                         and 2, both these elements are represented
     * by an
     *                     asterisk. */
    private java.lang.String balancingAccount;

    /* The
     *                         level at which the specified element can be
     * inserted in an account code as part of element
     *                     substitution. */
    private java.lang.Short subsLevel;

    /* The code for the element
     *                         which can be inserted in an account code as
     * part
     *                         of element substitution. */
    private java.lang.String subsElement;

    /* Indicates whether the element
     *                         is a matchable element. */
    private java.lang.Boolean matchable;

    /* The element level at which
     *                         payment postings are
     *                     summarised. */
    private java.lang.String summary;

    /* The element level to the left
     *                         of which wildcards are sorted and payments
     * produced for each of the
     *                     splits. */
    private java.lang.String split;

    /* The settlement account for
     *                         payments for this element. */
    private java.lang.String settlement;

    /* Specifies whether a user must
     *                         select Disperse if this element occurs in
     * a
     *                         detail line during Matching or Background
     *                     Matching. */
    private java.lang.String forceDisperse;

    /* The default user
     *                     status. */
    private java.lang.String userStatus;

    /* The
     *                         default payment status for the
     *                     element. */
    private java.lang.String payStatus;

    /* The reconciliation status of
     *                         the element. */
    private java.lang.String recStatus;

    /* Specifies whether the element
     *                         can be selected and processed by a Payment
     * and
     *                         Collection proposal. */
    private java.lang.Boolean enablePay;

    /* The
     *                         code for the default media master for the
     *                     element. */
    private java.lang.String defaultMedia;

    /* Indicates whether document
     *                         lines using this element can be paid by paper
     * means such as printed
     *                     cheques. */
    private java.lang.Boolean paperMedia;

    /* Indicates whether document
     *                         lines using this element can be paid by
     *                         electronic means. */
    private java.lang.Boolean elecMedia;

    /* The next payment number that
     *                         will be assigned to this element. This number
     * is
     *                     read-only. */
    private java.lang.Integer payNumber;

    /* Identifies elements which
     *                         represent customers or
     *                     suppliers. */
    private java.lang.Boolean customerSupplier;

    /* Indicates that this
     *                         Customer/Supplier element is a customer
     *                     (only). */
    private java.lang.Boolean isCustomer;

    /* Indicates that this
     *                         Customer/Supplier element is a supplier
     *                     (only). */
    private java.lang.Boolean isSupplier;

    /* Indicates whether early
     *                         settlement discount is allowed on document
     * lines
     *                         posted against the element and if so, whether
     * the discount is net of tax. */
    private java.lang.String discountEnable;

    /* Indicates whether tax is
     *                         gross or net of discount. */
    private java.lang.String taxMethod;

    /* The terms, expressed as a
     *                         soft date, used to calculate a document's
     *                         due date. */
    private java.lang.String terms;

    /* Indicates whether tax
     *                         adjustments are calculated for discounts and
     * write-offs during Pay, Matching, and Background
     *                     Matching. */
    private java.lang.Boolean taxAdjustment;

    /* Indicates whether the element
     *                         is included on European Sales List
     *                     reports. */
    private java.lang.Boolean taxRepESL;

    /* Indicates whether the element
     *                         is included on Intrastat
     *                     reports. */
    private java.lang.Boolean taxRepIntra;

    /* The VAT number for this
     *                     element. */
    private java.lang.String VAT;

    /* Specifies whether 1099
     *                         reporting/processing is required for this
     *                     element. */
    private java.lang.Boolean ten99;

    /* The Federal Tax
     *                         identification number (for tax purposes in
     * the
     *                         United States). */
    private java.lang.String federalTax;

    /* The
     *                         Social Security number for the
     *                     element. */
    private java.lang.String socialSecurity;

    /* The
     *                         code for the 1099 master for this
     *                     element. */
    private java.lang.String ten99Code;

    /* Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent. */
    private java.lang.Boolean secondTIN;

    /* The
     *                         Standard Industry Code for this
     *                     element. */
    private java.lang.String SIC;

    /* A code
     *                         identifying the user who is responsible for
     * credit management for the
     *                     element. */
    private java.lang.String creditManager;

    /* The credit rating assigned to
     *                         the element. */
    private java.lang.String creditRating;

    /* The
     *                         date on which the current credit rating was
     * assigned to the element. */
    private java.util.Calendar creditRatingDate;

    /* The credit reference assigned
     *                         to the element. */
    private java.lang.String creditReference;

    /* The credit agency used for
     *                         the element. */
    private java.lang.String creditAgency;

    /* The
     *                         payment index. */
    private java.lang.String paymentIndex;

    /* Specifies whether the credit
     *                         limit is enforced in Input. If True, it is
     * enforced. If False, it is not
     *                     enforced. */
    private java.lang.Boolean forceCreditLimit;

    /* The indirect element
     *                     code. */
    private java.lang.String indirectCode;

    /* The
     *                         element's credit limit (matchable elements
     * only). */
    private java.math.BigDecimal creditLimit;

    /* The
     *                         date on which the credit limit
     *                     expires. */
    private java.util.Calendar creditLimitDate;

    /* The
     *                         currency in which the element's credit
     *                         limit is expressed. */
    private java.lang.String creditLimitCurrency;

    /* Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unreconciled. */
    private java.lang.String arcRecon;

    /* Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unpaid. */
    private java.lang.String arcPaid;

    /* Holds
     *                         details of values that may be provided and
     * values that must be provided for temporary
     *                         suppliers within this umbrella
     *                     element. */
    private com.coda.www.efinance.schemas.elementmaster.UmbrellaData umbrellaElementDetails;

    /* Indicates whether the element
     *                         is shared across more than one
     *                     company. */
    private java.lang.Boolean shared;

    /* Indicates whether any
     *                         transactions have been posted to this
     *                     element. */
    private java.lang.Boolean postedTo;

    /* Set to TRUE if users will
     *                         want to create assets when posting to this
     * element. When TRUE, you can specify a value for
     *                     AssetCategory. */
    private java.lang.Boolean promptForAsset;

    /* The
     *                         default category to use for assets created
     * when
     *                         posting to this element. This value can only
     * be
     *                         set if PromptForAsset is
     *                     TRUE. */
    private java.lang.String assetCategory;

    /* The
     *                         code of the extension master identifying the
     * external routine that will validate the account
     *                         code during Input. */
    private com.coda.www.efinance.schemas.common.ExtensionRef extension;

    /* Holds
     *                         details of the last transactions posted to
     * this
     *                     element. */
    private com.coda.www.efinance.schemas.elementmaster.LastTransactions lastTransactions;

    /* Indicates whether
     *                         e-Procurement documents relating to this element
     * can be printed. */
    private java.lang.Boolean procAllowPrinting;

    /* Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in PDF
     *                     format. */
    private java.lang.Boolean procTransmitPDF;

    /* Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML
     *                     format. */
    private java.lang.Boolean procTransmitXML;

    /* Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML format via the B2B
     *                         server. In effect, the document will be
     *                         transmitted on input rather than in a batch
     * during a document output
     *                     process. */
    private java.lang.Boolean procTransmitB2B;

    /* The
     *                         code of the print format to use when
     *                         transmitting order documents as PDF or printed
     * output. You can specify any of the print formats
     *                         available in e-Procurement. */
    private java.lang.String procOrders;

    /* The
     *                         code of the print format to use when
     *                         transmitting requisition documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     e-Procurement. */
    private java.lang.String procRequisitions;

    /* Indicates whether Billing
     *                         documents relating to this element can be
     *                     printed. */
    private java.lang.Boolean billingAllowPrinting;

    /* Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in PDF format. */
    private java.lang.Boolean billingTransmitPDF;

    /* Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in XML format. */
    private java.lang.Boolean billingTransmitXML;

    /* The
     *                         code of the print format to use when
     *                         transmitting pro-forma invoice documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing. */
    private java.lang.String billingSIProForma;

    /* The
     *                         code of the print format to use when
     *                         transmitting final invoice documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     Billing. */
    private java.lang.String billingSIFinal;

    /* The
     *                         code of the print format to use when
     *                         transmitting pro-forma credit note documents
     * as
     *                         PDF or printed output. You can specify any
     * of
     *                         the print formats available in
     *                     Billing. */
    private java.lang.String billingCNProForma;

    /* The
     *                         code of the print format to use when
     *                         transmitting final credit note documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing. */
    private java.lang.String billingCNFinal;

    /* The date when this element
     *                         master was created. */
    private java.util.Calendar createDate;

    /* The date when this element
     *                         master was last modified. */
    private java.util.Calendar modifyDate;

    /* The user who last modified
     *                         this element master (or the user who created
     * this element master if no modify date
     *                     exists). */
    private java.lang.String user;

    /* A
     *                         reporting code for this element
     *                     master. */
    private java.lang.String reportingCode1;

    /* A
     *                         reporting code for this element
     *                     master. */
    private java.lang.String reportingCode2;

    /* A
     *                         reporting code for this element
     *                     master. */
    private java.lang.String reportingCode3;

    /* The
     *                         code of the user extension master that will
     * invoke the punchout
     *                     function. */
    private java.lang.String punchoutCode;

    /* Specifies whether this
     *                         punchout-enabled element should behave as
     * a
     *                         market place or as a normal punchout supplier.
     * When set to TRUE, the element behaves as a
     *                         market place. */
    private java.lang.Boolean punchoutMarketplace;

    /* The URL for the
     *                     supplier. */
    private java.lang.String punchoutUrl;

    /* The
     *                         domain for the supplier. */
    private java.lang.String punchoutDomain;

    /* The
     *                         encoding of the data that the punchout Web
     * site
     *                         will send back to CODA. */
    private java.lang.String punchoutEncoding;

    /* The user name for accessing
     *                         the supplier's system. */
    private java.lang.String punchoutUser;

    /* The
     *                         password for the user name specified in
     *                     PunchoutUser. */
    private java.lang.String punchoutPassword;

    /* The
     *                         code for the product from which item details
     * should be copied. */
    private java.lang.String punchoutItemDetailsCode;

    /* If set, indicates that
     *                         purchases from this supplier can be receipted
     * automatically. */
    private java.lang.Boolean autoReceipt;

    /* If set, prevents any further
     *                         trading with this supplier. */
    private java.lang.Boolean procStatus;

    /* The
     *                         code of the tolerance master containing the
     * tolerances applicable to this
     *                     supplier. */
    private java.lang.String toleranceCode;

    /* The
     *                         number of elapsed days between the date of
     * a
     *                         receipt and when it can be selected for purchase
     * invoice matching. */
    private java.lang.Short matchingOffset;

    /* The maximum value allowed, in
     *                         home currency, for transactions with this
     *                     supplier. */
    private java.math.BigDecimal procTransLimit;

    /* The
     *                         default print format to use for
     *                     call-offs. */
    private java.lang.String procCalloffs;

    /* The default print format to
     *                         use for GRNs. */
    private java.lang.String procGRNs;

    /* The
     *                         default print format to use for
     *                     returns. */
    private java.lang.String procReturns;

    /* The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     supplier. */
    private java.lang.String procEmailSalutation;

    /* The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions. */
    private java.lang.String procEmailSubject;

    /* The
     *                         output device master to use for preview
     *                         invoices, actual invoices and credit notes
     * sent
     *                         to this customer. */
    private java.lang.String billingOutputDevice;

    /* The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     customer. */
    private java.lang.String billingEmailSalutation;

    /* The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions. */
    private java.lang.String billingEmailSubject;

    /* This element contains details
     *                         for a Retail store. */
    private com.coda.www.efinance.schemas.elementmaster.RetailData retail;

    /* Holds details of the
     *                         element's quantities. */
    private com.coda.www.efinance.schemas.elementmaster.QuantityElement[] quantities;

    /* Holds details of the
     *                         discounts which the element is
     *                     allowed. */
    private com.coda.www.efinance.schemas.elementmaster.DiscountElement[] discounts;

    /* This
     *                         element contains Sales Invoicing
     *                     data. */
    private com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData salesInvoiceData;

    public Master() {
    }

    public Master(
           java.lang.Short timeStamp,
           java.lang.Short taxesTimeStamp,
           java.lang.String cmpCode,
           java.lang.String code,
           short level,
           java.lang.String name,
           java.lang.String shortName,
           java.lang.String currencyCode,
           java.lang.String subAnalyse,
           java.lang.String taxCode,
           java.lang.String accountType,
           java.util.Calendar dateAccountOpened,
           java.lang.String startPeriod,
           java.lang.String endPeriod,
           java.lang.Boolean compulsoryDescr,
           java.lang.Boolean keepTurnover,
           java.lang.String accountSummary,
           java.lang.String elementStatus,
           java.lang.String externalValidation,
           java.lang.String memoStatus,
           java.lang.String balancingAccount,
           java.lang.Short subsLevel,
           java.lang.String subsElement,
           java.lang.Boolean matchable,
           java.lang.String summary,
           java.lang.String split,
           java.lang.String settlement,
           java.lang.String forceDisperse,
           java.lang.String userStatus,
           java.lang.String payStatus,
           java.lang.String recStatus,
           java.lang.Boolean enablePay,
           java.lang.String defaultMedia,
           java.lang.Boolean paperMedia,
           java.lang.Boolean elecMedia,
           java.lang.Integer payNumber,
           java.lang.Boolean customerSupplier,
           java.lang.Boolean isCustomer,
           java.lang.Boolean isSupplier,
           java.lang.String discountEnable,
           java.lang.String taxMethod,
           java.lang.String terms,
           java.lang.Boolean taxAdjustment,
           java.lang.Boolean taxRepESL,
           java.lang.Boolean taxRepIntra,
           java.lang.String VAT,
           java.lang.Boolean ten99,
           java.lang.String federalTax,
           java.lang.String socialSecurity,
           java.lang.String ten99Code,
           java.lang.Boolean secondTIN,
           java.lang.String SIC,
           java.lang.String creditManager,
           java.lang.String creditRating,
           java.util.Calendar creditRatingDate,
           java.lang.String creditReference,
           java.lang.String creditAgency,
           java.lang.String paymentIndex,
           java.lang.Boolean forceCreditLimit,
           java.lang.String indirectCode,
           java.math.BigDecimal creditLimit,
           java.util.Calendar creditLimitDate,
           java.lang.String creditLimitCurrency,
           java.lang.String arcRecon,
           java.lang.String arcPaid,
           com.coda.www.efinance.schemas.elementmaster.UmbrellaData umbrellaElementDetails,
           java.lang.Boolean shared,
           java.lang.Boolean postedTo,
           java.lang.Boolean promptForAsset,
           java.lang.String assetCategory,
           com.coda.www.efinance.schemas.common.ExtensionRef extension,
           com.coda.www.efinance.schemas.elementmaster.LastTransactions lastTransactions,
           java.lang.Boolean procAllowPrinting,
           java.lang.Boolean procTransmitPDF,
           java.lang.Boolean procTransmitXML,
           java.lang.Boolean procTransmitB2B,
           java.lang.String procOrders,
           java.lang.String procRequisitions,
           java.lang.Boolean billingAllowPrinting,
           java.lang.Boolean billingTransmitPDF,
           java.lang.Boolean billingTransmitXML,
           java.lang.String billingSIProForma,
           java.lang.String billingSIFinal,
           java.lang.String billingCNProForma,
           java.lang.String billingCNFinal,
           java.util.Calendar createDate,
           java.util.Calendar modifyDate,
           java.lang.String user,
           java.lang.String reportingCode1,
           java.lang.String reportingCode2,
           java.lang.String reportingCode3,
           java.lang.String punchoutCode,
           java.lang.Boolean punchoutMarketplace,
           java.lang.String punchoutUrl,
           java.lang.String punchoutDomain,
           java.lang.String punchoutEncoding,
           java.lang.String punchoutUser,
           java.lang.String punchoutPassword,
           java.lang.String punchoutItemDetailsCode,
           java.lang.Boolean autoReceipt,
           java.lang.Boolean procStatus,
           java.lang.String toleranceCode,
           java.lang.Short matchingOffset,
           java.math.BigDecimal procTransLimit,
           java.lang.String procCalloffs,
           java.lang.String procGRNs,
           java.lang.String procReturns,
           java.lang.String procEmailSalutation,
           java.lang.String procEmailSubject,
           java.lang.String billingOutputDevice,
           java.lang.String billingEmailSalutation,
           java.lang.String billingEmailSubject,
           com.coda.www.efinance.schemas.elementmaster.RetailData retail,
           com.coda.www.efinance.schemas.elementmaster.QuantityElement[] quantities,
           com.coda.www.efinance.schemas.elementmaster.DiscountElement[] discounts,
           com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData salesInvoiceData) {
           this.timeStamp = timeStamp;
           this.taxesTimeStamp = taxesTimeStamp;
           this.cmpCode = cmpCode;
           this.code = code;
           this.level = level;
           this.name = name;
           this.shortName = shortName;
           this.currencyCode = currencyCode;
           this.subAnalyse = subAnalyse;
           this.taxCode = taxCode;
           this.accountType = accountType;
           this.dateAccountOpened = dateAccountOpened;
           this.startPeriod = startPeriod;
           this.endPeriod = endPeriod;
           this.compulsoryDescr = compulsoryDescr;
           this.keepTurnover = keepTurnover;
           this.accountSummary = accountSummary;
           this.elementStatus = elementStatus;
           this.externalValidation = externalValidation;
           this.memoStatus = memoStatus;
           this.balancingAccount = balancingAccount;
           this.subsLevel = subsLevel;
           this.subsElement = subsElement;
           this.matchable = matchable;
           this.summary = summary;
           this.split = split;
           this.settlement = settlement;
           this.forceDisperse = forceDisperse;
           this.userStatus = userStatus;
           this.payStatus = payStatus;
           this.recStatus = recStatus;
           this.enablePay = enablePay;
           this.defaultMedia = defaultMedia;
           this.paperMedia = paperMedia;
           this.elecMedia = elecMedia;
           this.payNumber = payNumber;
           this.customerSupplier = customerSupplier;
           this.isCustomer = isCustomer;
           this.isSupplier = isSupplier;
           this.discountEnable = discountEnable;
           this.taxMethod = taxMethod;
           this.terms = terms;
           this.taxAdjustment = taxAdjustment;
           this.taxRepESL = taxRepESL;
           this.taxRepIntra = taxRepIntra;
           this.VAT = VAT;
           this.ten99 = ten99;
           this.federalTax = federalTax;
           this.socialSecurity = socialSecurity;
           this.ten99Code = ten99Code;
           this.secondTIN = secondTIN;
           this.SIC = SIC;
           this.creditManager = creditManager;
           this.creditRating = creditRating;
           this.creditRatingDate = creditRatingDate;
           this.creditReference = creditReference;
           this.creditAgency = creditAgency;
           this.paymentIndex = paymentIndex;
           this.forceCreditLimit = forceCreditLimit;
           this.indirectCode = indirectCode;
           this.creditLimit = creditLimit;
           this.creditLimitDate = creditLimitDate;
           this.creditLimitCurrency = creditLimitCurrency;
           this.arcRecon = arcRecon;
           this.arcPaid = arcPaid;
           this.umbrellaElementDetails = umbrellaElementDetails;
           this.shared = shared;
           this.postedTo = postedTo;
           this.promptForAsset = promptForAsset;
           this.assetCategory = assetCategory;
           this.extension = extension;
           this.lastTransactions = lastTransactions;
           this.procAllowPrinting = procAllowPrinting;
           this.procTransmitPDF = procTransmitPDF;
           this.procTransmitXML = procTransmitXML;
           this.procTransmitB2B = procTransmitB2B;
           this.procOrders = procOrders;
           this.procRequisitions = procRequisitions;
           this.billingAllowPrinting = billingAllowPrinting;
           this.billingTransmitPDF = billingTransmitPDF;
           this.billingTransmitXML = billingTransmitXML;
           this.billingSIProForma = billingSIProForma;
           this.billingSIFinal = billingSIFinal;
           this.billingCNProForma = billingCNProForma;
           this.billingCNFinal = billingCNFinal;
           this.createDate = createDate;
           this.modifyDate = modifyDate;
           this.user = user;
           this.reportingCode1 = reportingCode1;
           this.reportingCode2 = reportingCode2;
           this.reportingCode3 = reportingCode3;
           this.punchoutCode = punchoutCode;
           this.punchoutMarketplace = punchoutMarketplace;
           this.punchoutUrl = punchoutUrl;
           this.punchoutDomain = punchoutDomain;
           this.punchoutEncoding = punchoutEncoding;
           this.punchoutUser = punchoutUser;
           this.punchoutPassword = punchoutPassword;
           this.punchoutItemDetailsCode = punchoutItemDetailsCode;
           this.autoReceipt = autoReceipt;
           this.procStatus = procStatus;
           this.toleranceCode = toleranceCode;
           this.matchingOffset = matchingOffset;
           this.procTransLimit = procTransLimit;
           this.procCalloffs = procCalloffs;
           this.procGRNs = procGRNs;
           this.procReturns = procReturns;
           this.procEmailSalutation = procEmailSalutation;
           this.procEmailSubject = procEmailSubject;
           this.billingOutputDevice = billingOutputDevice;
           this.billingEmailSalutation = billingEmailSalutation;
           this.billingEmailSubject = billingEmailSubject;
           this.retail = retail;
           this.quantities = quantities;
           this.discounts = discounts;
           this.salesInvoiceData = salesInvoiceData;
    }


    /**
     * Gets the timeStamp value for this Master.
     * 
     * @return timeStamp   * The TimeStamp value for this
     *                         element master in the
     *                     database.
     */
    public java.lang.Short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this Master.
     * 
     * @param timeStamp   * The TimeStamp value for this
     *                         element master in the
     *                     database.
     */
    public void setTimeStamp(java.lang.Short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the taxesTimeStamp value for this Master.
     * 
     * @return taxesTimeStamp   * The TimeStamp value for this
     *                         element master in the oas_elmtaxes
     *                     table.
     */
    public java.lang.Short getTaxesTimeStamp() {
        return taxesTimeStamp;
    }


    /**
     * Sets the taxesTimeStamp value for this Master.
     * 
     * @param taxesTimeStamp   * The TimeStamp value for this
     *                         element master in the oas_elmtaxes
     *                     table.
     */
    public void setTaxesTimeStamp(java.lang.Short taxesTimeStamp) {
        this.taxesTimeStamp = taxesTimeStamp;
    }


    /**
     * Gets the cmpCode value for this Master.
     * 
     * @return cmpCode   * The code for the company in
     *                         which the element master is being
     *                     maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this Master.
     * 
     * @param cmpCode   * The code for the company in
     *                         which the element master is being
     *                     maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this Master.
     * 
     * @return code   * The
     *                         element code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this Master.
     * 
     * @param code   * The
     *                         element code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the level value for this Master.
     * 
     * @return level   * The
     *                         level at which this element is defined. A
     * number
     *                         from 1 to 8 inclusive.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this Master.
     * 
     * @param level   * The
     *                         level at which this element is defined. A
     * number
     *                         from 1 to 8 inclusive.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the name value for this Master.
     * 
     * @return name   * The full name for the
     *                     element.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Master.
     * 
     * @param name   * The full name for the
     *                     element.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this Master.
     * 
     * @return shortName   * The abbreviated name for the
     *                     element.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this Master.
     * 
     * @param shortName   * The abbreviated name for the
     *                     element.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the currencyCode value for this Master.
     * 
     * @return currencyCode   * A
     *                         currency code identifying the currency
     *                         associated with this
     *                     element.
     */
    public java.lang.String getCurrencyCode() {
        return currencyCode;
    }


    /**
     * Sets the currencyCode value for this Master.
     * 
     * @param currencyCode   * A
     *                         currency code identifying the currency
     *                         associated with this
     *                     element.
     */
    public void setCurrencyCode(java.lang.String currencyCode) {
        this.currencyCode = currencyCode;
    }


    /**
     * Gets the subAnalyse value for this Master.
     * 
     * @return subAnalyse   * Indicates whether the element
     *                         can be subanalysed.
     */
    public java.lang.String getSubAnalyse() {
        return subAnalyse;
    }


    /**
     * Sets the subAnalyse value for this Master.
     * 
     * @param subAnalyse   * Indicates whether the element
     *                         can be subanalysed.
     */
    public void setSubAnalyse(java.lang.String subAnalyse) {
        this.subAnalyse = subAnalyse;
    }


    /**
     * Gets the taxCode value for this Master.
     * 
     * @return taxCode   * The code for the tax master
     *                         for this element.
     */
    public java.lang.String getTaxCode() {
        return taxCode;
    }


    /**
     * Sets the taxCode value for this Master.
     * 
     * @param taxCode   * The code for the tax master
     *                         for this element.
     */
    public void setTaxCode(java.lang.String taxCode) {
        this.taxCode = taxCode;
    }


    /**
     * Gets the accountType value for this Master.
     * 
     * @return accountType   * Identifies the account type
     *                         of accounts which use this
     *                     element.
     */
    public java.lang.String getAccountType() {
        return accountType;
    }


    /**
     * Sets the accountType value for this Master.
     * 
     * @param accountType   * Identifies the account type
     *                         of accounts which use this
     *                     element.
     */
    public void setAccountType(java.lang.String accountType) {
        this.accountType = accountType;
    }


    /**
     * Gets the dateAccountOpened value for this Master.
     * 
     * @return dateAccountOpened   * The
     *                         date on which the element was
     *                     created.
     */
    public java.util.Calendar getDateAccountOpened() {
        return dateAccountOpened;
    }


    /**
     * Sets the dateAccountOpened value for this Master.
     * 
     * @param dateAccountOpened   * The
     *                         date on which the element was
     *                     created.
     */
    public void setDateAccountOpened(java.util.Calendar dateAccountOpened) {
        this.dateAccountOpened = dateAccountOpened;
    }


    /**
     * Gets the startPeriod value for this Master.
     * 
     * @return startPeriod   * The first period in which
     *                         documents may be posted to this
     *                     element.
     */
    public java.lang.String getStartPeriod() {
        return startPeriod;
    }


    /**
     * Sets the startPeriod value for this Master.
     * 
     * @param startPeriod   * The first period in which
     *                         documents may be posted to this
     *                     element.
     */
    public void setStartPeriod(java.lang.String startPeriod) {
        this.startPeriod = startPeriod;
    }


    /**
     * Gets the endPeriod value for this Master.
     * 
     * @return endPeriod   * The last period in which
     *                         documents may be posted to this
     *                     element.
     */
    public java.lang.String getEndPeriod() {
        return endPeriod;
    }


    /**
     * Sets the endPeriod value for this Master.
     * 
     * @param endPeriod   * The last period in which
     *                         documents may be posted to this
     *                     element.
     */
    public void setEndPeriod(java.lang.String endPeriod) {
        this.endPeriod = endPeriod;
    }


    /**
     * Gets the compulsoryDescr value for this Master.
     * 
     * @return compulsoryDescr   * Indicates whether users must
     *                         enter a description in Input against any
     *                         document line posted to this
     *                     element.
     */
    public java.lang.Boolean getCompulsoryDescr() {
        return compulsoryDescr;
    }


    /**
     * Sets the compulsoryDescr value for this Master.
     * 
     * @param compulsoryDescr   * Indicates whether users must
     *                         enter a description in Input against any
     *                         document line posted to this
     *                     element.
     */
    public void setCompulsoryDescr(java.lang.Boolean compulsoryDescr) {
        this.compulsoryDescr = compulsoryDescr;
    }


    /**
     * Gets the keepTurnover value for this Master.
     * 
     * @return keepTurnover   * Indicates whether turnover
     *                         values are maintained for the element for
     * each
     *                     period.
     */
    public java.lang.Boolean getKeepTurnover() {
        return keepTurnover;
    }


    /**
     * Sets the keepTurnover value for this Master.
     * 
     * @param keepTurnover   * Indicates whether turnover
     *                         values are maintained for the element for
     * each
     *                     period.
     */
    public void setKeepTurnover(java.lang.Boolean keepTurnover) {
        this.keepTurnover = keepTurnover;
    }


    /**
     * Gets the accountSummary value for this Master.
     * 
     * @return accountSummary   * The
     *                         code for the account summary master for this
     * element.
     */
    public java.lang.String getAccountSummary() {
        return accountSummary;
    }


    /**
     * Sets the accountSummary value for this Master.
     * 
     * @param accountSummary   * The
     *                         code for the account summary master for this
     * element.
     */
    public void setAccountSummary(java.lang.String accountSummary) {
        this.accountSummary = accountSummary;
    }


    /**
     * Gets the elementStatus value for this Master.
     * 
     * @return elementStatus   * The
     *                         credit status of this
     *                     element.
     */
    public java.lang.String getElementStatus() {
        return elementStatus;
    }


    /**
     * Sets the elementStatus value for this Master.
     * 
     * @param elementStatus   * The
     *                         credit status of this
     *                     element.
     */
    public void setElementStatus(java.lang.String elementStatus) {
        this.elementStatus = elementStatus;
    }


    /**
     * Gets the externalValidation value for this Master.
     * 
     * @return externalValidation   * The
     *                         name of the DLL used for extended field
     *                     validation.
     */
    public java.lang.String getExternalValidation() {
        return externalValidation;
    }


    /**
     * Sets the externalValidation value for this Master.
     * 
     * @param externalValidation   * The
     *                         name of the DLL used for extended field
     *                     validation.
     */
    public void setExternalValidation(java.lang.String externalValidation) {
        this.externalValidation = externalValidation;
    }


    /**
     * Gets the memoStatus value for this Master.
     * 
     * @return memoStatus   * A
     *                         reporting field.
     */
    public java.lang.String getMemoStatus() {
        return memoStatus;
    }


    /**
     * Sets the memoStatus value for this Master.
     * 
     * @param memoStatus   * A
     *                         reporting field.
     */
    public void setMemoStatus(java.lang.String memoStatus) {
        this.memoStatus = memoStatus;
    }


    /**
     * Gets the balancingAccount value for this Master.
     * 
     * @return balancingAccount   * The
     *                         account to which balancing entries are posted.
     * If you are balancing at element level 1, an
     *                         asterisk represents the first element of the
     * account code. If you are balancing at elements 1
     *                         and 2, both these elements are represented
     * by an
     *                     asterisk.
     */
    public java.lang.String getBalancingAccount() {
        return balancingAccount;
    }


    /**
     * Sets the balancingAccount value for this Master.
     * 
     * @param balancingAccount   * The
     *                         account to which balancing entries are posted.
     * If you are balancing at element level 1, an
     *                         asterisk represents the first element of the
     * account code. If you are balancing at elements 1
     *                         and 2, both these elements are represented
     * by an
     *                     asterisk.
     */
    public void setBalancingAccount(java.lang.String balancingAccount) {
        this.balancingAccount = balancingAccount;
    }


    /**
     * Gets the subsLevel value for this Master.
     * 
     * @return subsLevel   * The
     *                         level at which the specified element can be
     * inserted in an account code as part of element
     *                     substitution.
     */
    public java.lang.Short getSubsLevel() {
        return subsLevel;
    }


    /**
     * Sets the subsLevel value for this Master.
     * 
     * @param subsLevel   * The
     *                         level at which the specified element can be
     * inserted in an account code as part of element
     *                     substitution.
     */
    public void setSubsLevel(java.lang.Short subsLevel) {
        this.subsLevel = subsLevel;
    }


    /**
     * Gets the subsElement value for this Master.
     * 
     * @return subsElement   * The code for the element
     *                         which can be inserted in an account code as
     * part
     *                         of element substitution.
     */
    public java.lang.String getSubsElement() {
        return subsElement;
    }


    /**
     * Sets the subsElement value for this Master.
     * 
     * @param subsElement   * The code for the element
     *                         which can be inserted in an account code as
     * part
     *                         of element substitution.
     */
    public void setSubsElement(java.lang.String subsElement) {
        this.subsElement = subsElement;
    }


    /**
     * Gets the matchable value for this Master.
     * 
     * @return matchable   * Indicates whether the element
     *                         is a matchable element.
     */
    public java.lang.Boolean getMatchable() {
        return matchable;
    }


    /**
     * Sets the matchable value for this Master.
     * 
     * @param matchable   * Indicates whether the element
     *                         is a matchable element.
     */
    public void setMatchable(java.lang.Boolean matchable) {
        this.matchable = matchable;
    }


    /**
     * Gets the summary value for this Master.
     * 
     * @return summary   * The element level at which
     *                         payment postings are
     *                     summarised.
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this Master.
     * 
     * @param summary   * The element level at which
     *                         payment postings are
     *                     summarised.
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }


    /**
     * Gets the split value for this Master.
     * 
     * @return split   * The element level to the left
     *                         of which wildcards are sorted and payments
     * produced for each of the
     *                     splits.
     */
    public java.lang.String getSplit() {
        return split;
    }


    /**
     * Sets the split value for this Master.
     * 
     * @param split   * The element level to the left
     *                         of which wildcards are sorted and payments
     * produced for each of the
     *                     splits.
     */
    public void setSplit(java.lang.String split) {
        this.split = split;
    }


    /**
     * Gets the settlement value for this Master.
     * 
     * @return settlement   * The settlement account for
     *                         payments for this element.
     */
    public java.lang.String getSettlement() {
        return settlement;
    }


    /**
     * Sets the settlement value for this Master.
     * 
     * @param settlement   * The settlement account for
     *                         payments for this element.
     */
    public void setSettlement(java.lang.String settlement) {
        this.settlement = settlement;
    }


    /**
     * Gets the forceDisperse value for this Master.
     * 
     * @return forceDisperse   * Specifies whether a user must
     *                         select Disperse if this element occurs in
     * a
     *                         detail line during Matching or Background
     *                     Matching.
     */
    public java.lang.String getForceDisperse() {
        return forceDisperse;
    }


    /**
     * Sets the forceDisperse value for this Master.
     * 
     * @param forceDisperse   * Specifies whether a user must
     *                         select Disperse if this element occurs in
     * a
     *                         detail line during Matching or Background
     *                     Matching.
     */
    public void setForceDisperse(java.lang.String forceDisperse) {
        this.forceDisperse = forceDisperse;
    }


    /**
     * Gets the userStatus value for this Master.
     * 
     * @return userStatus   * The default user
     *                     status.
     */
    public java.lang.String getUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this Master.
     * 
     * @param userStatus   * The default user
     *                     status.
     */
    public void setUserStatus(java.lang.String userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the payStatus value for this Master.
     * 
     * @return payStatus   * The
     *                         default payment status for the
     *                     element.
     */
    public java.lang.String getPayStatus() {
        return payStatus;
    }


    /**
     * Sets the payStatus value for this Master.
     * 
     * @param payStatus   * The
     *                         default payment status for the
     *                     element.
     */
    public void setPayStatus(java.lang.String payStatus) {
        this.payStatus = payStatus;
    }


    /**
     * Gets the recStatus value for this Master.
     * 
     * @return recStatus   * The reconciliation status of
     *                         the element.
     */
    public java.lang.String getRecStatus() {
        return recStatus;
    }


    /**
     * Sets the recStatus value for this Master.
     * 
     * @param recStatus   * The reconciliation status of
     *                         the element.
     */
    public void setRecStatus(java.lang.String recStatus) {
        this.recStatus = recStatus;
    }


    /**
     * Gets the enablePay value for this Master.
     * 
     * @return enablePay   * Specifies whether the element
     *                         can be selected and processed by a Payment
     * and
     *                         Collection proposal.
     */
    public java.lang.Boolean getEnablePay() {
        return enablePay;
    }


    /**
     * Sets the enablePay value for this Master.
     * 
     * @param enablePay   * Specifies whether the element
     *                         can be selected and processed by a Payment
     * and
     *                         Collection proposal.
     */
    public void setEnablePay(java.lang.Boolean enablePay) {
        this.enablePay = enablePay;
    }


    /**
     * Gets the defaultMedia value for this Master.
     * 
     * @return defaultMedia   * The
     *                         code for the default media master for the
     *                     element.
     */
    public java.lang.String getDefaultMedia() {
        return defaultMedia;
    }


    /**
     * Sets the defaultMedia value for this Master.
     * 
     * @param defaultMedia   * The
     *                         code for the default media master for the
     *                     element.
     */
    public void setDefaultMedia(java.lang.String defaultMedia) {
        this.defaultMedia = defaultMedia;
    }


    /**
     * Gets the paperMedia value for this Master.
     * 
     * @return paperMedia   * Indicates whether document
     *                         lines using this element can be paid by paper
     * means such as printed
     *                     cheques.
     */
    public java.lang.Boolean getPaperMedia() {
        return paperMedia;
    }


    /**
     * Sets the paperMedia value for this Master.
     * 
     * @param paperMedia   * Indicates whether document
     *                         lines using this element can be paid by paper
     * means such as printed
     *                     cheques.
     */
    public void setPaperMedia(java.lang.Boolean paperMedia) {
        this.paperMedia = paperMedia;
    }


    /**
     * Gets the elecMedia value for this Master.
     * 
     * @return elecMedia   * Indicates whether document
     *                         lines using this element can be paid by
     *                         electronic means.
     */
    public java.lang.Boolean getElecMedia() {
        return elecMedia;
    }


    /**
     * Sets the elecMedia value for this Master.
     * 
     * @param elecMedia   * Indicates whether document
     *                         lines using this element can be paid by
     *                         electronic means.
     */
    public void setElecMedia(java.lang.Boolean elecMedia) {
        this.elecMedia = elecMedia;
    }


    /**
     * Gets the payNumber value for this Master.
     * 
     * @return payNumber   * The next payment number that
     *                         will be assigned to this element. This number
     * is
     *                     read-only.
     */
    public java.lang.Integer getPayNumber() {
        return payNumber;
    }


    /**
     * Sets the payNumber value for this Master.
     * 
     * @param payNumber   * The next payment number that
     *                         will be assigned to this element. This number
     * is
     *                     read-only.
     */
    public void setPayNumber(java.lang.Integer payNumber) {
        this.payNumber = payNumber;
    }


    /**
     * Gets the customerSupplier value for this Master.
     * 
     * @return customerSupplier   * Identifies elements which
     *                         represent customers or
     *                     suppliers.
     */
    public java.lang.Boolean getCustomerSupplier() {
        return customerSupplier;
    }


    /**
     * Sets the customerSupplier value for this Master.
     * 
     * @param customerSupplier   * Identifies elements which
     *                         represent customers or
     *                     suppliers.
     */
    public void setCustomerSupplier(java.lang.Boolean customerSupplier) {
        this.customerSupplier = customerSupplier;
    }


    /**
     * Gets the isCustomer value for this Master.
     * 
     * @return isCustomer   * Indicates that this
     *                         Customer/Supplier element is a customer
     *                     (only).
     */
    public java.lang.Boolean getIsCustomer() {
        return isCustomer;
    }


    /**
     * Sets the isCustomer value for this Master.
     * 
     * @param isCustomer   * Indicates that this
     *                         Customer/Supplier element is a customer
     *                     (only).
     */
    public void setIsCustomer(java.lang.Boolean isCustomer) {
        this.isCustomer = isCustomer;
    }


    /**
     * Gets the isSupplier value for this Master.
     * 
     * @return isSupplier   * Indicates that this
     *                         Customer/Supplier element is a supplier
     *                     (only).
     */
    public java.lang.Boolean getIsSupplier() {
        return isSupplier;
    }


    /**
     * Sets the isSupplier value for this Master.
     * 
     * @param isSupplier   * Indicates that this
     *                         Customer/Supplier element is a supplier
     *                     (only).
     */
    public void setIsSupplier(java.lang.Boolean isSupplier) {
        this.isSupplier = isSupplier;
    }


    /**
     * Gets the discountEnable value for this Master.
     * 
     * @return discountEnable   * Indicates whether early
     *                         settlement discount is allowed on document
     * lines
     *                         posted against the element and if so, whether
     * the discount is net of tax.
     */
    public java.lang.String getDiscountEnable() {
        return discountEnable;
    }


    /**
     * Sets the discountEnable value for this Master.
     * 
     * @param discountEnable   * Indicates whether early
     *                         settlement discount is allowed on document
     * lines
     *                         posted against the element and if so, whether
     * the discount is net of tax.
     */
    public void setDiscountEnable(java.lang.String discountEnable) {
        this.discountEnable = discountEnable;
    }


    /**
     * Gets the taxMethod value for this Master.
     * 
     * @return taxMethod   * Indicates whether tax is
     *                         gross or net of discount.
     */
    public java.lang.String getTaxMethod() {
        return taxMethod;
    }


    /**
     * Sets the taxMethod value for this Master.
     * 
     * @param taxMethod   * Indicates whether tax is
     *                         gross or net of discount.
     */
    public void setTaxMethod(java.lang.String taxMethod) {
        this.taxMethod = taxMethod;
    }


    /**
     * Gets the terms value for this Master.
     * 
     * @return terms   * The terms, expressed as a
     *                         soft date, used to calculate a document's
     *                         due date.
     */
    public java.lang.String getTerms() {
        return terms;
    }


    /**
     * Sets the terms value for this Master.
     * 
     * @param terms   * The terms, expressed as a
     *                         soft date, used to calculate a document's
     *                         due date.
     */
    public void setTerms(java.lang.String terms) {
        this.terms = terms;
    }


    /**
     * Gets the taxAdjustment value for this Master.
     * 
     * @return taxAdjustment   * Indicates whether tax
     *                         adjustments are calculated for discounts and
     * write-offs during Pay, Matching, and Background
     *                     Matching.
     */
    public java.lang.Boolean getTaxAdjustment() {
        return taxAdjustment;
    }


    /**
     * Sets the taxAdjustment value for this Master.
     * 
     * @param taxAdjustment   * Indicates whether tax
     *                         adjustments are calculated for discounts and
     * write-offs during Pay, Matching, and Background
     *                     Matching.
     */
    public void setTaxAdjustment(java.lang.Boolean taxAdjustment) {
        this.taxAdjustment = taxAdjustment;
    }


    /**
     * Gets the taxRepESL value for this Master.
     * 
     * @return taxRepESL   * Indicates whether the element
     *                         is included on European Sales List
     *                     reports.
     */
    public java.lang.Boolean getTaxRepESL() {
        return taxRepESL;
    }


    /**
     * Sets the taxRepESL value for this Master.
     * 
     * @param taxRepESL   * Indicates whether the element
     *                         is included on European Sales List
     *                     reports.
     */
    public void setTaxRepESL(java.lang.Boolean taxRepESL) {
        this.taxRepESL = taxRepESL;
    }


    /**
     * Gets the taxRepIntra value for this Master.
     * 
     * @return taxRepIntra   * Indicates whether the element
     *                         is included on Intrastat
     *                     reports.
     */
    public java.lang.Boolean getTaxRepIntra() {
        return taxRepIntra;
    }


    /**
     * Sets the taxRepIntra value for this Master.
     * 
     * @param taxRepIntra   * Indicates whether the element
     *                         is included on Intrastat
     *                     reports.
     */
    public void setTaxRepIntra(java.lang.Boolean taxRepIntra) {
        this.taxRepIntra = taxRepIntra;
    }


    /**
     * Gets the VAT value for this Master.
     * 
     * @return VAT   * The VAT number for this
     *                     element.
     */
    public java.lang.String getVAT() {
        return VAT;
    }


    /**
     * Sets the VAT value for this Master.
     * 
     * @param VAT   * The VAT number for this
     *                     element.
     */
    public void setVAT(java.lang.String VAT) {
        this.VAT = VAT;
    }


    /**
     * Gets the ten99 value for this Master.
     * 
     * @return ten99   * Specifies whether 1099
     *                         reporting/processing is required for this
     *                     element.
     */
    public java.lang.Boolean getTen99() {
        return ten99;
    }


    /**
     * Sets the ten99 value for this Master.
     * 
     * @param ten99   * Specifies whether 1099
     *                         reporting/processing is required for this
     *                     element.
     */
    public void setTen99(java.lang.Boolean ten99) {
        this.ten99 = ten99;
    }


    /**
     * Gets the federalTax value for this Master.
     * 
     * @return federalTax   * The Federal Tax
     *                         identification number (for tax purposes in
     * the
     *                         United States).
     */
    public java.lang.String getFederalTax() {
        return federalTax;
    }


    /**
     * Sets the federalTax value for this Master.
     * 
     * @param federalTax   * The Federal Tax
     *                         identification number (for tax purposes in
     * the
     *                         United States).
     */
    public void setFederalTax(java.lang.String federalTax) {
        this.federalTax = federalTax;
    }


    /**
     * Gets the socialSecurity value for this Master.
     * 
     * @return socialSecurity   * The
     *                         Social Security number for the
     *                     element.
     */
    public java.lang.String getSocialSecurity() {
        return socialSecurity;
    }


    /**
     * Sets the socialSecurity value for this Master.
     * 
     * @param socialSecurity   * The
     *                         Social Security number for the
     *                     element.
     */
    public void setSocialSecurity(java.lang.String socialSecurity) {
        this.socialSecurity = socialSecurity;
    }


    /**
     * Gets the ten99Code value for this Master.
     * 
     * @return ten99Code   * The
     *                         code for the 1099 master for this
     *                     element.
     */
    public java.lang.String getTen99Code() {
        return ten99Code;
    }


    /**
     * Sets the ten99Code value for this Master.
     * 
     * @param ten99Code   * The
     *                         code for the 1099 master for this
     *                     element.
     */
    public void setTen99Code(java.lang.String ten99Code) {
        this.ten99Code = ten99Code;
    }


    /**
     * Gets the secondTIN value for this Master.
     * 
     * @return secondTIN   * Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent.
     */
    public java.lang.Boolean getSecondTIN() {
        return secondTIN;
    }


    /**
     * Sets the secondTIN value for this Master.
     * 
     * @param secondTIN   * Indicates whether a second
     *                         tax identification notice (TIN) has been
     *                     sent.
     */
    public void setSecondTIN(java.lang.Boolean secondTIN) {
        this.secondTIN = secondTIN;
    }


    /**
     * Gets the SIC value for this Master.
     * 
     * @return SIC   * The
     *                         Standard Industry Code for this
     *                     element.
     */
    public java.lang.String getSIC() {
        return SIC;
    }


    /**
     * Sets the SIC value for this Master.
     * 
     * @param SIC   * The
     *                         Standard Industry Code for this
     *                     element.
     */
    public void setSIC(java.lang.String SIC) {
        this.SIC = SIC;
    }


    /**
     * Gets the creditManager value for this Master.
     * 
     * @return creditManager   * A code
     *                         identifying the user who is responsible for
     * credit management for the
     *                     element.
     */
    public java.lang.String getCreditManager() {
        return creditManager;
    }


    /**
     * Sets the creditManager value for this Master.
     * 
     * @param creditManager   * A code
     *                         identifying the user who is responsible for
     * credit management for the
     *                     element.
     */
    public void setCreditManager(java.lang.String creditManager) {
        this.creditManager = creditManager;
    }


    /**
     * Gets the creditRating value for this Master.
     * 
     * @return creditRating   * The credit rating assigned to
     *                         the element.
     */
    public java.lang.String getCreditRating() {
        return creditRating;
    }


    /**
     * Sets the creditRating value for this Master.
     * 
     * @param creditRating   * The credit rating assigned to
     *                         the element.
     */
    public void setCreditRating(java.lang.String creditRating) {
        this.creditRating = creditRating;
    }


    /**
     * Gets the creditRatingDate value for this Master.
     * 
     * @return creditRatingDate   * The
     *                         date on which the current credit rating was
     * assigned to the element.
     */
    public java.util.Calendar getCreditRatingDate() {
        return creditRatingDate;
    }


    /**
     * Sets the creditRatingDate value for this Master.
     * 
     * @param creditRatingDate   * The
     *                         date on which the current credit rating was
     * assigned to the element.
     */
    public void setCreditRatingDate(java.util.Calendar creditRatingDate) {
        this.creditRatingDate = creditRatingDate;
    }


    /**
     * Gets the creditReference value for this Master.
     * 
     * @return creditReference   * The credit reference assigned
     *                         to the element.
     */
    public java.lang.String getCreditReference() {
        return creditReference;
    }


    /**
     * Sets the creditReference value for this Master.
     * 
     * @param creditReference   * The credit reference assigned
     *                         to the element.
     */
    public void setCreditReference(java.lang.String creditReference) {
        this.creditReference = creditReference;
    }


    /**
     * Gets the creditAgency value for this Master.
     * 
     * @return creditAgency   * The credit agency used for
     *                         the element.
     */
    public java.lang.String getCreditAgency() {
        return creditAgency;
    }


    /**
     * Sets the creditAgency value for this Master.
     * 
     * @param creditAgency   * The credit agency used for
     *                         the element.
     */
    public void setCreditAgency(java.lang.String creditAgency) {
        this.creditAgency = creditAgency;
    }


    /**
     * Gets the paymentIndex value for this Master.
     * 
     * @return paymentIndex   * The
     *                         payment index.
     */
    public java.lang.String getPaymentIndex() {
        return paymentIndex;
    }


    /**
     * Sets the paymentIndex value for this Master.
     * 
     * @param paymentIndex   * The
     *                         payment index.
     */
    public void setPaymentIndex(java.lang.String paymentIndex) {
        this.paymentIndex = paymentIndex;
    }


    /**
     * Gets the forceCreditLimit value for this Master.
     * 
     * @return forceCreditLimit   * Specifies whether the credit
     *                         limit is enforced in Input. If True, it is
     * enforced. If False, it is not
     *                     enforced.
     */
    public java.lang.Boolean getForceCreditLimit() {
        return forceCreditLimit;
    }


    /**
     * Sets the forceCreditLimit value for this Master.
     * 
     * @param forceCreditLimit   * Specifies whether the credit
     *                         limit is enforced in Input. If True, it is
     * enforced. If False, it is not
     *                     enforced.
     */
    public void setForceCreditLimit(java.lang.Boolean forceCreditLimit) {
        this.forceCreditLimit = forceCreditLimit;
    }


    /**
     * Gets the indirectCode value for this Master.
     * 
     * @return indirectCode   * The indirect element
     *                     code.
     */
    public java.lang.String getIndirectCode() {
        return indirectCode;
    }


    /**
     * Sets the indirectCode value for this Master.
     * 
     * @param indirectCode   * The indirect element
     *                     code.
     */
    public void setIndirectCode(java.lang.String indirectCode) {
        this.indirectCode = indirectCode;
    }


    /**
     * Gets the creditLimit value for this Master.
     * 
     * @return creditLimit   * The
     *                         element's credit limit (matchable elements
     * only).
     */
    public java.math.BigDecimal getCreditLimit() {
        return creditLimit;
    }


    /**
     * Sets the creditLimit value for this Master.
     * 
     * @param creditLimit   * The
     *                         element's credit limit (matchable elements
     * only).
     */
    public void setCreditLimit(java.math.BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }


    /**
     * Gets the creditLimitDate value for this Master.
     * 
     * @return creditLimitDate   * The
     *                         date on which the credit limit
     *                     expires.
     */
    public java.util.Calendar getCreditLimitDate() {
        return creditLimitDate;
    }


    /**
     * Sets the creditLimitDate value for this Master.
     * 
     * @param creditLimitDate   * The
     *                         date on which the credit limit
     *                     expires.
     */
    public void setCreditLimitDate(java.util.Calendar creditLimitDate) {
        this.creditLimitDate = creditLimitDate;
    }


    /**
     * Gets the creditLimitCurrency value for this Master.
     * 
     * @return creditLimitCurrency   * The
     *                         currency in which the element's credit
     *                         limit is expressed.
     */
    public java.lang.String getCreditLimitCurrency() {
        return creditLimitCurrency;
    }


    /**
     * Sets the creditLimitCurrency value for this Master.
     * 
     * @param creditLimitCurrency   * The
     *                         currency in which the element's credit
     *                         limit is expressed.
     */
    public void setCreditLimitCurrency(java.lang.String creditLimitCurrency) {
        this.creditLimitCurrency = creditLimitCurrency;
    }


    /**
     * Gets the arcRecon value for this Master.
     * 
     * @return arcRecon   * Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unreconciled.
     */
    public java.lang.String getArcRecon() {
        return arcRecon;
    }


    /**
     * Sets the arcRecon value for this Master.
     * 
     * @param arcRecon   * Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unreconciled.
     */
    public void setArcRecon(java.lang.String arcRecon) {
        this.arcRecon = arcRecon;
    }


    /**
     * Gets the arcPaid value for this Master.
     * 
     * @return arcPaid   * Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unpaid.
     */
    public java.lang.String getArcPaid() {
        return arcPaid;
    }


    /**
     * Sets the arcPaid value for this Master.
     * 
     * @param arcPaid   * Indicates the element's
     *                         contribution to determining whether an
     *                         account's document lines can be archived
     *                         when unpaid.
     */
    public void setArcPaid(java.lang.String arcPaid) {
        this.arcPaid = arcPaid;
    }


    /**
     * Gets the umbrellaElementDetails value for this Master.
     * 
     * @return umbrellaElementDetails   * Holds
     *                         details of values that may be provided and
     * values that must be provided for temporary
     *                         suppliers within this umbrella
     *                     element.
     */
    public com.coda.www.efinance.schemas.elementmaster.UmbrellaData getUmbrellaElementDetails() {
        return umbrellaElementDetails;
    }


    /**
     * Sets the umbrellaElementDetails value for this Master.
     * 
     * @param umbrellaElementDetails   * Holds
     *                         details of values that may be provided and
     * values that must be provided for temporary
     *                         suppliers within this umbrella
     *                     element.
     */
    public void setUmbrellaElementDetails(com.coda.www.efinance.schemas.elementmaster.UmbrellaData umbrellaElementDetails) {
        this.umbrellaElementDetails = umbrellaElementDetails;
    }


    /**
     * Gets the shared value for this Master.
     * 
     * @return shared   * Indicates whether the element
     *                         is shared across more than one
     *                     company.
     */
    public java.lang.Boolean getShared() {
        return shared;
    }


    /**
     * Sets the shared value for this Master.
     * 
     * @param shared   * Indicates whether the element
     *                         is shared across more than one
     *                     company.
     */
    public void setShared(java.lang.Boolean shared) {
        this.shared = shared;
    }


    /**
     * Gets the postedTo value for this Master.
     * 
     * @return postedTo   * Indicates whether any
     *                         transactions have been posted to this
     *                     element.
     */
    public java.lang.Boolean getPostedTo() {
        return postedTo;
    }


    /**
     * Sets the postedTo value for this Master.
     * 
     * @param postedTo   * Indicates whether any
     *                         transactions have been posted to this
     *                     element.
     */
    public void setPostedTo(java.lang.Boolean postedTo) {
        this.postedTo = postedTo;
    }


    /**
     * Gets the promptForAsset value for this Master.
     * 
     * @return promptForAsset   * Set to TRUE if users will
     *                         want to create assets when posting to this
     * element. When TRUE, you can specify a value for
     *                     AssetCategory.
     */
    public java.lang.Boolean getPromptForAsset() {
        return promptForAsset;
    }


    /**
     * Sets the promptForAsset value for this Master.
     * 
     * @param promptForAsset   * Set to TRUE if users will
     *                         want to create assets when posting to this
     * element. When TRUE, you can specify a value for
     *                     AssetCategory.
     */
    public void setPromptForAsset(java.lang.Boolean promptForAsset) {
        this.promptForAsset = promptForAsset;
    }


    /**
     * Gets the assetCategory value for this Master.
     * 
     * @return assetCategory   * The
     *                         default category to use for assets created
     * when
     *                         posting to this element. This value can only
     * be
     *                         set if PromptForAsset is
     *                     TRUE.
     */
    public java.lang.String getAssetCategory() {
        return assetCategory;
    }


    /**
     * Sets the assetCategory value for this Master.
     * 
     * @param assetCategory   * The
     *                         default category to use for assets created
     * when
     *                         posting to this element. This value can only
     * be
     *                         set if PromptForAsset is
     *                     TRUE.
     */
    public void setAssetCategory(java.lang.String assetCategory) {
        this.assetCategory = assetCategory;
    }


    /**
     * Gets the extension value for this Master.
     * 
     * @return extension   * The
     *                         code of the extension master identifying the
     * external routine that will validate the account
     *                         code during Input.
     */
    public com.coda.www.efinance.schemas.common.ExtensionRef getExtension() {
        return extension;
    }


    /**
     * Sets the extension value for this Master.
     * 
     * @param extension   * The
     *                         code of the extension master identifying the
     * external routine that will validate the account
     *                         code during Input.
     */
    public void setExtension(com.coda.www.efinance.schemas.common.ExtensionRef extension) {
        this.extension = extension;
    }


    /**
     * Gets the lastTransactions value for this Master.
     * 
     * @return lastTransactions   * Holds
     *                         details of the last transactions posted to
     * this
     *                     element.
     */
    public com.coda.www.efinance.schemas.elementmaster.LastTransactions getLastTransactions() {
        return lastTransactions;
    }


    /**
     * Sets the lastTransactions value for this Master.
     * 
     * @param lastTransactions   * Holds
     *                         details of the last transactions posted to
     * this
     *                     element.
     */
    public void setLastTransactions(com.coda.www.efinance.schemas.elementmaster.LastTransactions lastTransactions) {
        this.lastTransactions = lastTransactions;
    }


    /**
     * Gets the procAllowPrinting value for this Master.
     * 
     * @return procAllowPrinting   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be printed.
     */
    public java.lang.Boolean getProcAllowPrinting() {
        return procAllowPrinting;
    }


    /**
     * Sets the procAllowPrinting value for this Master.
     * 
     * @param procAllowPrinting   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be printed.
     */
    public void setProcAllowPrinting(java.lang.Boolean procAllowPrinting) {
        this.procAllowPrinting = procAllowPrinting;
    }


    /**
     * Gets the procTransmitPDF value for this Master.
     * 
     * @return procTransmitPDF   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in PDF
     *                     format.
     */
    public java.lang.Boolean getProcTransmitPDF() {
        return procTransmitPDF;
    }


    /**
     * Sets the procTransmitPDF value for this Master.
     * 
     * @param procTransmitPDF   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in PDF
     *                     format.
     */
    public void setProcTransmitPDF(java.lang.Boolean procTransmitPDF) {
        this.procTransmitPDF = procTransmitPDF;
    }


    /**
     * Gets the procTransmitXML value for this Master.
     * 
     * @return procTransmitXML   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML
     *                     format.
     */
    public java.lang.Boolean getProcTransmitXML() {
        return procTransmitXML;
    }


    /**
     * Sets the procTransmitXML value for this Master.
     * 
     * @param procTransmitXML   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML
     *                     format.
     */
    public void setProcTransmitXML(java.lang.Boolean procTransmitXML) {
        this.procTransmitXML = procTransmitXML;
    }


    /**
     * Gets the procTransmitB2B value for this Master.
     * 
     * @return procTransmitB2B   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML format via the B2B
     *                         server. In effect, the document will be
     *                         transmitted on input rather than in a batch
     * during a document output
     *                     process.
     */
    public java.lang.Boolean getProcTransmitB2B() {
        return procTransmitB2B;
    }


    /**
     * Sets the procTransmitB2B value for this Master.
     * 
     * @param procTransmitB2B   * Indicates whether
     *                         e-Procurement documents relating to this element
     * can be transmitted in XML format via the B2B
     *                         server. In effect, the document will be
     *                         transmitted on input rather than in a batch
     * during a document output
     *                     process.
     */
    public void setProcTransmitB2B(java.lang.Boolean procTransmitB2B) {
        this.procTransmitB2B = procTransmitB2B;
    }


    /**
     * Gets the procOrders value for this Master.
     * 
     * @return procOrders   * The
     *                         code of the print format to use when
     *                         transmitting order documents as PDF or printed
     * output. You can specify any of the print formats
     *                         available in e-Procurement.
     */
    public java.lang.String getProcOrders() {
        return procOrders;
    }


    /**
     * Sets the procOrders value for this Master.
     * 
     * @param procOrders   * The
     *                         code of the print format to use when
     *                         transmitting order documents as PDF or printed
     * output. You can specify any of the print formats
     *                         available in e-Procurement.
     */
    public void setProcOrders(java.lang.String procOrders) {
        this.procOrders = procOrders;
    }


    /**
     * Gets the procRequisitions value for this Master.
     * 
     * @return procRequisitions   * The
     *                         code of the print format to use when
     *                         transmitting requisition documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     e-Procurement.
     */
    public java.lang.String getProcRequisitions() {
        return procRequisitions;
    }


    /**
     * Sets the procRequisitions value for this Master.
     * 
     * @param procRequisitions   * The
     *                         code of the print format to use when
     *                         transmitting requisition documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     e-Procurement.
     */
    public void setProcRequisitions(java.lang.String procRequisitions) {
        this.procRequisitions = procRequisitions;
    }


    /**
     * Gets the billingAllowPrinting value for this Master.
     * 
     * @return billingAllowPrinting   * Indicates whether Billing
     *                         documents relating to this element can be
     *                     printed.
     */
    public java.lang.Boolean getBillingAllowPrinting() {
        return billingAllowPrinting;
    }


    /**
     * Sets the billingAllowPrinting value for this Master.
     * 
     * @param billingAllowPrinting   * Indicates whether Billing
     *                         documents relating to this element can be
     *                     printed.
     */
    public void setBillingAllowPrinting(java.lang.Boolean billingAllowPrinting) {
        this.billingAllowPrinting = billingAllowPrinting;
    }


    /**
     * Gets the billingTransmitPDF value for this Master.
     * 
     * @return billingTransmitPDF   * Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in PDF format.
     */
    public java.lang.Boolean getBillingTransmitPDF() {
        return billingTransmitPDF;
    }


    /**
     * Sets the billingTransmitPDF value for this Master.
     * 
     * @param billingTransmitPDF   * Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in PDF format.
     */
    public void setBillingTransmitPDF(java.lang.Boolean billingTransmitPDF) {
        this.billingTransmitPDF = billingTransmitPDF;
    }


    /**
     * Gets the billingTransmitXML value for this Master.
     * 
     * @return billingTransmitXML   * Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in XML format.
     */
    public java.lang.Boolean getBillingTransmitXML() {
        return billingTransmitXML;
    }


    /**
     * Sets the billingTransmitXML value for this Master.
     * 
     * @param billingTransmitXML   * Indicates whether Billing
     *                         documents relating to this element can be
     *                         transmitted in XML format.
     */
    public void setBillingTransmitXML(java.lang.Boolean billingTransmitXML) {
        this.billingTransmitXML = billingTransmitXML;
    }


    /**
     * Gets the billingSIProForma value for this Master.
     * 
     * @return billingSIProForma   * The
     *                         code of the print format to use when
     *                         transmitting pro-forma invoice documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing.
     */
    public java.lang.String getBillingSIProForma() {
        return billingSIProForma;
    }


    /**
     * Sets the billingSIProForma value for this Master.
     * 
     * @param billingSIProForma   * The
     *                         code of the print format to use when
     *                         transmitting pro-forma invoice documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing.
     */
    public void setBillingSIProForma(java.lang.String billingSIProForma) {
        this.billingSIProForma = billingSIProForma;
    }


    /**
     * Gets the billingSIFinal value for this Master.
     * 
     * @return billingSIFinal   * The
     *                         code of the print format to use when
     *                         transmitting final invoice documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     Billing.
     */
    public java.lang.String getBillingSIFinal() {
        return billingSIFinal;
    }


    /**
     * Sets the billingSIFinal value for this Master.
     * 
     * @param billingSIFinal   * The
     *                         code of the print format to use when
     *                         transmitting final invoice documents as PDF
     * or
     *                         printed output. You can specify any of the
     * print
     *                         formats available in
     *                     Billing.
     */
    public void setBillingSIFinal(java.lang.String billingSIFinal) {
        this.billingSIFinal = billingSIFinal;
    }


    /**
     * Gets the billingCNProForma value for this Master.
     * 
     * @return billingCNProForma   * The
     *                         code of the print format to use when
     *                         transmitting pro-forma credit note documents
     * as
     *                         PDF or printed output. You can specify any
     * of
     *                         the print formats available in
     *                     Billing.
     */
    public java.lang.String getBillingCNProForma() {
        return billingCNProForma;
    }


    /**
     * Sets the billingCNProForma value for this Master.
     * 
     * @param billingCNProForma   * The
     *                         code of the print format to use when
     *                         transmitting pro-forma credit note documents
     * as
     *                         PDF or printed output. You can specify any
     * of
     *                         the print formats available in
     *                     Billing.
     */
    public void setBillingCNProForma(java.lang.String billingCNProForma) {
        this.billingCNProForma = billingCNProForma;
    }


    /**
     * Gets the billingCNFinal value for this Master.
     * 
     * @return billingCNFinal   * The
     *                         code of the print format to use when
     *                         transmitting final credit note documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing.
     */
    public java.lang.String getBillingCNFinal() {
        return billingCNFinal;
    }


    /**
     * Sets the billingCNFinal value for this Master.
     * 
     * @param billingCNFinal   * The
     *                         code of the print format to use when
     *                         transmitting final credit note documents as
     * PDF
     *                         or printed output. You can specify any of
     * the
     *                         print formats available in
     *                     Billing.
     */
    public void setBillingCNFinal(java.lang.String billingCNFinal) {
        this.billingCNFinal = billingCNFinal;
    }


    /**
     * Gets the createDate value for this Master.
     * 
     * @return createDate   * The date when this element
     *                         master was created.
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this Master.
     * 
     * @param createDate   * The date when this element
     *                         master was created.
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the modifyDate value for this Master.
     * 
     * @return modifyDate   * The date when this element
     *                         master was last modified.
     */
    public java.util.Calendar getModifyDate() {
        return modifyDate;
    }


    /**
     * Sets the modifyDate value for this Master.
     * 
     * @param modifyDate   * The date when this element
     *                         master was last modified.
     */
    public void setModifyDate(java.util.Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }


    /**
     * Gets the user value for this Master.
     * 
     * @return user   * The user who last modified
     *                         this element master (or the user who created
     * this element master if no modify date
     *                     exists).
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this Master.
     * 
     * @param user   * The user who last modified
     *                         this element master (or the user who created
     * this element master if no modify date
     *                     exists).
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the reportingCode1 value for this Master.
     * 
     * @return reportingCode1   * A
     *                         reporting code for this element
     *                     master.
     */
    public java.lang.String getReportingCode1() {
        return reportingCode1;
    }


    /**
     * Sets the reportingCode1 value for this Master.
     * 
     * @param reportingCode1   * A
     *                         reporting code for this element
     *                     master.
     */
    public void setReportingCode1(java.lang.String reportingCode1) {
        this.reportingCode1 = reportingCode1;
    }


    /**
     * Gets the reportingCode2 value for this Master.
     * 
     * @return reportingCode2   * A
     *                         reporting code for this element
     *                     master.
     */
    public java.lang.String getReportingCode2() {
        return reportingCode2;
    }


    /**
     * Sets the reportingCode2 value for this Master.
     * 
     * @param reportingCode2   * A
     *                         reporting code for this element
     *                     master.
     */
    public void setReportingCode2(java.lang.String reportingCode2) {
        this.reportingCode2 = reportingCode2;
    }


    /**
     * Gets the reportingCode3 value for this Master.
     * 
     * @return reportingCode3   * A
     *                         reporting code for this element
     *                     master.
     */
    public java.lang.String getReportingCode3() {
        return reportingCode3;
    }


    /**
     * Sets the reportingCode3 value for this Master.
     * 
     * @param reportingCode3   * A
     *                         reporting code for this element
     *                     master.
     */
    public void setReportingCode3(java.lang.String reportingCode3) {
        this.reportingCode3 = reportingCode3;
    }


    /**
     * Gets the punchoutCode value for this Master.
     * 
     * @return punchoutCode   * The
     *                         code of the user extension master that will
     * invoke the punchout
     *                     function.
     */
    public java.lang.String getPunchoutCode() {
        return punchoutCode;
    }


    /**
     * Sets the punchoutCode value for this Master.
     * 
     * @param punchoutCode   * The
     *                         code of the user extension master that will
     * invoke the punchout
     *                     function.
     */
    public void setPunchoutCode(java.lang.String punchoutCode) {
        this.punchoutCode = punchoutCode;
    }


    /**
     * Gets the punchoutMarketplace value for this Master.
     * 
     * @return punchoutMarketplace   * Specifies whether this
     *                         punchout-enabled element should behave as
     * a
     *                         market place or as a normal punchout supplier.
     * When set to TRUE, the element behaves as a
     *                         market place.
     */
    public java.lang.Boolean getPunchoutMarketplace() {
        return punchoutMarketplace;
    }


    /**
     * Sets the punchoutMarketplace value for this Master.
     * 
     * @param punchoutMarketplace   * Specifies whether this
     *                         punchout-enabled element should behave as
     * a
     *                         market place or as a normal punchout supplier.
     * When set to TRUE, the element behaves as a
     *                         market place.
     */
    public void setPunchoutMarketplace(java.lang.Boolean punchoutMarketplace) {
        this.punchoutMarketplace = punchoutMarketplace;
    }


    /**
     * Gets the punchoutUrl value for this Master.
     * 
     * @return punchoutUrl   * The URL for the
     *                     supplier.
     */
    public java.lang.String getPunchoutUrl() {
        return punchoutUrl;
    }


    /**
     * Sets the punchoutUrl value for this Master.
     * 
     * @param punchoutUrl   * The URL for the
     *                     supplier.
     */
    public void setPunchoutUrl(java.lang.String punchoutUrl) {
        this.punchoutUrl = punchoutUrl;
    }


    /**
     * Gets the punchoutDomain value for this Master.
     * 
     * @return punchoutDomain   * The
     *                         domain for the supplier.
     */
    public java.lang.String getPunchoutDomain() {
        return punchoutDomain;
    }


    /**
     * Sets the punchoutDomain value for this Master.
     * 
     * @param punchoutDomain   * The
     *                         domain for the supplier.
     */
    public void setPunchoutDomain(java.lang.String punchoutDomain) {
        this.punchoutDomain = punchoutDomain;
    }


    /**
     * Gets the punchoutEncoding value for this Master.
     * 
     * @return punchoutEncoding   * The
     *                         encoding of the data that the punchout Web
     * site
     *                         will send back to CODA.
     */
    public java.lang.String getPunchoutEncoding() {
        return punchoutEncoding;
    }


    /**
     * Sets the punchoutEncoding value for this Master.
     * 
     * @param punchoutEncoding   * The
     *                         encoding of the data that the punchout Web
     * site
     *                         will send back to CODA.
     */
    public void setPunchoutEncoding(java.lang.String punchoutEncoding) {
        this.punchoutEncoding = punchoutEncoding;
    }


    /**
     * Gets the punchoutUser value for this Master.
     * 
     * @return punchoutUser   * The user name for accessing
     *                         the supplier's system.
     */
    public java.lang.String getPunchoutUser() {
        return punchoutUser;
    }


    /**
     * Sets the punchoutUser value for this Master.
     * 
     * @param punchoutUser   * The user name for accessing
     *                         the supplier's system.
     */
    public void setPunchoutUser(java.lang.String punchoutUser) {
        this.punchoutUser = punchoutUser;
    }


    /**
     * Gets the punchoutPassword value for this Master.
     * 
     * @return punchoutPassword   * The
     *                         password for the user name specified in
     *                     PunchoutUser.
     */
    public java.lang.String getPunchoutPassword() {
        return punchoutPassword;
    }


    /**
     * Sets the punchoutPassword value for this Master.
     * 
     * @param punchoutPassword   * The
     *                         password for the user name specified in
     *                     PunchoutUser.
     */
    public void setPunchoutPassword(java.lang.String punchoutPassword) {
        this.punchoutPassword = punchoutPassword;
    }


    /**
     * Gets the punchoutItemDetailsCode value for this Master.
     * 
     * @return punchoutItemDetailsCode   * The
     *                         code for the product from which item details
     * should be copied.
     */
    public java.lang.String getPunchoutItemDetailsCode() {
        return punchoutItemDetailsCode;
    }


    /**
     * Sets the punchoutItemDetailsCode value for this Master.
     * 
     * @param punchoutItemDetailsCode   * The
     *                         code for the product from which item details
     * should be copied.
     */
    public void setPunchoutItemDetailsCode(java.lang.String punchoutItemDetailsCode) {
        this.punchoutItemDetailsCode = punchoutItemDetailsCode;
    }


    /**
     * Gets the autoReceipt value for this Master.
     * 
     * @return autoReceipt   * If set, indicates that
     *                         purchases from this supplier can be receipted
     * automatically.
     */
    public java.lang.Boolean getAutoReceipt() {
        return autoReceipt;
    }


    /**
     * Sets the autoReceipt value for this Master.
     * 
     * @param autoReceipt   * If set, indicates that
     *                         purchases from this supplier can be receipted
     * automatically.
     */
    public void setAutoReceipt(java.lang.Boolean autoReceipt) {
        this.autoReceipt = autoReceipt;
    }


    /**
     * Gets the procStatus value for this Master.
     * 
     * @return procStatus   * If set, prevents any further
     *                         trading with this supplier.
     */
    public java.lang.Boolean getProcStatus() {
        return procStatus;
    }


    /**
     * Sets the procStatus value for this Master.
     * 
     * @param procStatus   * If set, prevents any further
     *                         trading with this supplier.
     */
    public void setProcStatus(java.lang.Boolean procStatus) {
        this.procStatus = procStatus;
    }


    /**
     * Gets the toleranceCode value for this Master.
     * 
     * @return toleranceCode   * The
     *                         code of the tolerance master containing the
     * tolerances applicable to this
     *                     supplier.
     */
    public java.lang.String getToleranceCode() {
        return toleranceCode;
    }


    /**
     * Sets the toleranceCode value for this Master.
     * 
     * @param toleranceCode   * The
     *                         code of the tolerance master containing the
     * tolerances applicable to this
     *                     supplier.
     */
    public void setToleranceCode(java.lang.String toleranceCode) {
        this.toleranceCode = toleranceCode;
    }


    /**
     * Gets the matchingOffset value for this Master.
     * 
     * @return matchingOffset   * The
     *                         number of elapsed days between the date of
     * a
     *                         receipt and when it can be selected for purchase
     * invoice matching.
     */
    public java.lang.Short getMatchingOffset() {
        return matchingOffset;
    }


    /**
     * Sets the matchingOffset value for this Master.
     * 
     * @param matchingOffset   * The
     *                         number of elapsed days between the date of
     * a
     *                         receipt and when it can be selected for purchase
     * invoice matching.
     */
    public void setMatchingOffset(java.lang.Short matchingOffset) {
        this.matchingOffset = matchingOffset;
    }


    /**
     * Gets the procTransLimit value for this Master.
     * 
     * @return procTransLimit   * The maximum value allowed, in
     *                         home currency, for transactions with this
     *                     supplier.
     */
    public java.math.BigDecimal getProcTransLimit() {
        return procTransLimit;
    }


    /**
     * Sets the procTransLimit value for this Master.
     * 
     * @param procTransLimit   * The maximum value allowed, in
     *                         home currency, for transactions with this
     *                     supplier.
     */
    public void setProcTransLimit(java.math.BigDecimal procTransLimit) {
        this.procTransLimit = procTransLimit;
    }


    /**
     * Gets the procCalloffs value for this Master.
     * 
     * @return procCalloffs   * The
     *                         default print format to use for
     *                     call-offs.
     */
    public java.lang.String getProcCalloffs() {
        return procCalloffs;
    }


    /**
     * Sets the procCalloffs value for this Master.
     * 
     * @param procCalloffs   * The
     *                         default print format to use for
     *                     call-offs.
     */
    public void setProcCalloffs(java.lang.String procCalloffs) {
        this.procCalloffs = procCalloffs;
    }


    /**
     * Gets the procGRNs value for this Master.
     * 
     * @return procGRNs   * The default print format to
     *                         use for GRNs.
     */
    public java.lang.String getProcGRNs() {
        return procGRNs;
    }


    /**
     * Sets the procGRNs value for this Master.
     * 
     * @param procGRNs   * The default print format to
     *                         use for GRNs.
     */
    public void setProcGRNs(java.lang.String procGRNs) {
        this.procGRNs = procGRNs;
    }


    /**
     * Gets the procReturns value for this Master.
     * 
     * @return procReturns   * The
     *                         default print format to use for
     *                     returns.
     */
    public java.lang.String getProcReturns() {
        return procReturns;
    }


    /**
     * Sets the procReturns value for this Master.
     * 
     * @param procReturns   * The
     *                         default print format to use for
     *                     returns.
     */
    public void setProcReturns(java.lang.String procReturns) {
        this.procReturns = procReturns;
    }


    /**
     * Gets the procEmailSalutation value for this Master.
     * 
     * @return procEmailSalutation   * The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     supplier.
     */
    public java.lang.String getProcEmailSalutation() {
        return procEmailSalutation;
    }


    /**
     * Sets the procEmailSalutation value for this Master.
     * 
     * @param procEmailSalutation   * The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     supplier.
     */
    public void setProcEmailSalutation(java.lang.String procEmailSalutation) {
        this.procEmailSalutation = procEmailSalutation;
    }


    /**
     * Gets the procEmailSubject value for this Master.
     * 
     * @return procEmailSubject   * The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions.
     */
    public java.lang.String getProcEmailSubject() {
        return procEmailSubject;
    }


    /**
     * Sets the procEmailSubject value for this Master.
     * 
     * @param procEmailSubject   * The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions.
     */
    public void setProcEmailSubject(java.lang.String procEmailSubject) {
        this.procEmailSubject = procEmailSubject;
    }


    /**
     * Gets the billingOutputDevice value for this Master.
     * 
     * @return billingOutputDevice   * The
     *                         output device master to use for preview
     *                         invoices, actual invoices and credit notes
     * sent
     *                         to this customer.
     */
    public java.lang.String getBillingOutputDevice() {
        return billingOutputDevice;
    }


    /**
     * Sets the billingOutputDevice value for this Master.
     * 
     * @param billingOutputDevice   * The
     *                         output device master to use for preview
     *                         invoices, actual invoices and credit notes
     * sent
     *                         to this customer.
     */
    public void setBillingOutputDevice(java.lang.String billingOutputDevice) {
        this.billingOutputDevice = billingOutputDevice;
    }


    /**
     * Gets the billingEmailSalutation value for this Master.
     * 
     * @return billingEmailSalutation   * The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     customer.
     */
    public java.lang.String getBillingEmailSalutation() {
        return billingEmailSalutation;
    }


    /**
     * Sets the billingEmailSalutation value for this Master.
     * 
     * @param billingEmailSalutation   * The
     *                         message to be included when transmitting
     *                         documents by e-mail to this
     *                     customer.
     */
    public void setBillingEmailSalutation(java.lang.String billingEmailSalutation) {
        this.billingEmailSalutation = billingEmailSalutation;
    }


    /**
     * Gets the billingEmailSubject value for this Master.
     * 
     * @return billingEmailSubject   * The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions.
     */
    public java.lang.String getBillingEmailSubject() {
        return billingEmailSubject;
    }


    /**
     * Sets the billingEmailSubject value for this Master.
     * 
     * @param billingEmailSubject   * The
     *                         text to be displayed in the subject line of
     * all
     *                         e-mail transmissions.
     */
    public void setBillingEmailSubject(java.lang.String billingEmailSubject) {
        this.billingEmailSubject = billingEmailSubject;
    }


    /**
     * Gets the retail value for this Master.
     * 
     * @return retail   * This element contains details
     *                         for a Retail store.
     */
    public com.coda.www.efinance.schemas.elementmaster.RetailData getRetail() {
        return retail;
    }


    /**
     * Sets the retail value for this Master.
     * 
     * @param retail   * This element contains details
     *                         for a Retail store.
     */
    public void setRetail(com.coda.www.efinance.schemas.elementmaster.RetailData retail) {
        this.retail = retail;
    }


    /**
     * Gets the quantities value for this Master.
     * 
     * @return quantities   * Holds details of the
     *                         element's quantities.
     */
    public com.coda.www.efinance.schemas.elementmaster.QuantityElement[] getQuantities() {
        return quantities;
    }


    /**
     * Sets the quantities value for this Master.
     * 
     * @param quantities   * Holds details of the
     *                         element's quantities.
     */
    public void setQuantities(com.coda.www.efinance.schemas.elementmaster.QuantityElement[] quantities) {
        this.quantities = quantities;
    }


    /**
     * Gets the discounts value for this Master.
     * 
     * @return discounts   * Holds details of the
     *                         discounts which the element is
     *                     allowed.
     */
    public com.coda.www.efinance.schemas.elementmaster.DiscountElement[] getDiscounts() {
        return discounts;
    }


    /**
     * Sets the discounts value for this Master.
     * 
     * @param discounts   * Holds details of the
     *                         discounts which the element is
     *                     allowed.
     */
    public void setDiscounts(com.coda.www.efinance.schemas.elementmaster.DiscountElement[] discounts) {
        this.discounts = discounts;
    }


    /**
     * Gets the salesInvoiceData value for this Master.
     * 
     * @return salesInvoiceData   * This
     *                         element contains Sales Invoicing
     *                     data.
     */
    public com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData getSalesInvoiceData() {
        return salesInvoiceData;
    }


    /**
     * Sets the salesInvoiceData value for this Master.
     * 
     * @param salesInvoiceData   * This
     *                         element contains Sales Invoicing
     *                     data.
     */
    public void setSalesInvoiceData(com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData salesInvoiceData) {
        this.salesInvoiceData = salesInvoiceData;
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
            ((this.timeStamp==null && other.getTimeStamp()==null) || 
             (this.timeStamp!=null &&
              this.timeStamp.equals(other.getTimeStamp()))) &&
            ((this.taxesTimeStamp==null && other.getTaxesTimeStamp()==null) || 
             (this.taxesTimeStamp!=null &&
              this.taxesTimeStamp.equals(other.getTaxesTimeStamp()))) &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            this.level == other.getLevel() &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.currencyCode==null && other.getCurrencyCode()==null) || 
             (this.currencyCode!=null &&
              this.currencyCode.equals(other.getCurrencyCode()))) &&
            ((this.subAnalyse==null && other.getSubAnalyse()==null) || 
             (this.subAnalyse!=null &&
              this.subAnalyse.equals(other.getSubAnalyse()))) &&
            ((this.taxCode==null && other.getTaxCode()==null) || 
             (this.taxCode!=null &&
              this.taxCode.equals(other.getTaxCode()))) &&
            ((this.accountType==null && other.getAccountType()==null) || 
             (this.accountType!=null &&
              this.accountType.equals(other.getAccountType()))) &&
            ((this.dateAccountOpened==null && other.getDateAccountOpened()==null) || 
             (this.dateAccountOpened!=null &&
              this.dateAccountOpened.equals(other.getDateAccountOpened()))) &&
            ((this.startPeriod==null && other.getStartPeriod()==null) || 
             (this.startPeriod!=null &&
              this.startPeriod.equals(other.getStartPeriod()))) &&
            ((this.endPeriod==null && other.getEndPeriod()==null) || 
             (this.endPeriod!=null &&
              this.endPeriod.equals(other.getEndPeriod()))) &&
            ((this.compulsoryDescr==null && other.getCompulsoryDescr()==null) || 
             (this.compulsoryDescr!=null &&
              this.compulsoryDescr.equals(other.getCompulsoryDescr()))) &&
            ((this.keepTurnover==null && other.getKeepTurnover()==null) || 
             (this.keepTurnover!=null &&
              this.keepTurnover.equals(other.getKeepTurnover()))) &&
            ((this.accountSummary==null && other.getAccountSummary()==null) || 
             (this.accountSummary!=null &&
              this.accountSummary.equals(other.getAccountSummary()))) &&
            ((this.elementStatus==null && other.getElementStatus()==null) || 
             (this.elementStatus!=null &&
              this.elementStatus.equals(other.getElementStatus()))) &&
            ((this.externalValidation==null && other.getExternalValidation()==null) || 
             (this.externalValidation!=null &&
              this.externalValidation.equals(other.getExternalValidation()))) &&
            ((this.memoStatus==null && other.getMemoStatus()==null) || 
             (this.memoStatus!=null &&
              this.memoStatus.equals(other.getMemoStatus()))) &&
            ((this.balancingAccount==null && other.getBalancingAccount()==null) || 
             (this.balancingAccount!=null &&
              this.balancingAccount.equals(other.getBalancingAccount()))) &&
            ((this.subsLevel==null && other.getSubsLevel()==null) || 
             (this.subsLevel!=null &&
              this.subsLevel.equals(other.getSubsLevel()))) &&
            ((this.subsElement==null && other.getSubsElement()==null) || 
             (this.subsElement!=null &&
              this.subsElement.equals(other.getSubsElement()))) &&
            ((this.matchable==null && other.getMatchable()==null) || 
             (this.matchable!=null &&
              this.matchable.equals(other.getMatchable()))) &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary()))) &&
            ((this.split==null && other.getSplit()==null) || 
             (this.split!=null &&
              this.split.equals(other.getSplit()))) &&
            ((this.settlement==null && other.getSettlement()==null) || 
             (this.settlement!=null &&
              this.settlement.equals(other.getSettlement()))) &&
            ((this.forceDisperse==null && other.getForceDisperse()==null) || 
             (this.forceDisperse!=null &&
              this.forceDisperse.equals(other.getForceDisperse()))) &&
            ((this.userStatus==null && other.getUserStatus()==null) || 
             (this.userStatus!=null &&
              this.userStatus.equals(other.getUserStatus()))) &&
            ((this.payStatus==null && other.getPayStatus()==null) || 
             (this.payStatus!=null &&
              this.payStatus.equals(other.getPayStatus()))) &&
            ((this.recStatus==null && other.getRecStatus()==null) || 
             (this.recStatus!=null &&
              this.recStatus.equals(other.getRecStatus()))) &&
            ((this.enablePay==null && other.getEnablePay()==null) || 
             (this.enablePay!=null &&
              this.enablePay.equals(other.getEnablePay()))) &&
            ((this.defaultMedia==null && other.getDefaultMedia()==null) || 
             (this.defaultMedia!=null &&
              this.defaultMedia.equals(other.getDefaultMedia()))) &&
            ((this.paperMedia==null && other.getPaperMedia()==null) || 
             (this.paperMedia!=null &&
              this.paperMedia.equals(other.getPaperMedia()))) &&
            ((this.elecMedia==null && other.getElecMedia()==null) || 
             (this.elecMedia!=null &&
              this.elecMedia.equals(other.getElecMedia()))) &&
            ((this.payNumber==null && other.getPayNumber()==null) || 
             (this.payNumber!=null &&
              this.payNumber.equals(other.getPayNumber()))) &&
            ((this.customerSupplier==null && other.getCustomerSupplier()==null) || 
             (this.customerSupplier!=null &&
              this.customerSupplier.equals(other.getCustomerSupplier()))) &&
            ((this.isCustomer==null && other.getIsCustomer()==null) || 
             (this.isCustomer!=null &&
              this.isCustomer.equals(other.getIsCustomer()))) &&
            ((this.isSupplier==null && other.getIsSupplier()==null) || 
             (this.isSupplier!=null &&
              this.isSupplier.equals(other.getIsSupplier()))) &&
            ((this.discountEnable==null && other.getDiscountEnable()==null) || 
             (this.discountEnable!=null &&
              this.discountEnable.equals(other.getDiscountEnable()))) &&
            ((this.taxMethod==null && other.getTaxMethod()==null) || 
             (this.taxMethod!=null &&
              this.taxMethod.equals(other.getTaxMethod()))) &&
            ((this.terms==null && other.getTerms()==null) || 
             (this.terms!=null &&
              this.terms.equals(other.getTerms()))) &&
            ((this.taxAdjustment==null && other.getTaxAdjustment()==null) || 
             (this.taxAdjustment!=null &&
              this.taxAdjustment.equals(other.getTaxAdjustment()))) &&
            ((this.taxRepESL==null && other.getTaxRepESL()==null) || 
             (this.taxRepESL!=null &&
              this.taxRepESL.equals(other.getTaxRepESL()))) &&
            ((this.taxRepIntra==null && other.getTaxRepIntra()==null) || 
             (this.taxRepIntra!=null &&
              this.taxRepIntra.equals(other.getTaxRepIntra()))) &&
            ((this.VAT==null && other.getVAT()==null) || 
             (this.VAT!=null &&
              this.VAT.equals(other.getVAT()))) &&
            ((this.ten99==null && other.getTen99()==null) || 
             (this.ten99!=null &&
              this.ten99.equals(other.getTen99()))) &&
            ((this.federalTax==null && other.getFederalTax()==null) || 
             (this.federalTax!=null &&
              this.federalTax.equals(other.getFederalTax()))) &&
            ((this.socialSecurity==null && other.getSocialSecurity()==null) || 
             (this.socialSecurity!=null &&
              this.socialSecurity.equals(other.getSocialSecurity()))) &&
            ((this.ten99Code==null && other.getTen99Code()==null) || 
             (this.ten99Code!=null &&
              this.ten99Code.equals(other.getTen99Code()))) &&
            ((this.secondTIN==null && other.getSecondTIN()==null) || 
             (this.secondTIN!=null &&
              this.secondTIN.equals(other.getSecondTIN()))) &&
            ((this.SIC==null && other.getSIC()==null) || 
             (this.SIC!=null &&
              this.SIC.equals(other.getSIC()))) &&
            ((this.creditManager==null && other.getCreditManager()==null) || 
             (this.creditManager!=null &&
              this.creditManager.equals(other.getCreditManager()))) &&
            ((this.creditRating==null && other.getCreditRating()==null) || 
             (this.creditRating!=null &&
              this.creditRating.equals(other.getCreditRating()))) &&
            ((this.creditRatingDate==null && other.getCreditRatingDate()==null) || 
             (this.creditRatingDate!=null &&
              this.creditRatingDate.equals(other.getCreditRatingDate()))) &&
            ((this.creditReference==null && other.getCreditReference()==null) || 
             (this.creditReference!=null &&
              this.creditReference.equals(other.getCreditReference()))) &&
            ((this.creditAgency==null && other.getCreditAgency()==null) || 
             (this.creditAgency!=null &&
              this.creditAgency.equals(other.getCreditAgency()))) &&
            ((this.paymentIndex==null && other.getPaymentIndex()==null) || 
             (this.paymentIndex!=null &&
              this.paymentIndex.equals(other.getPaymentIndex()))) &&
            ((this.forceCreditLimit==null && other.getForceCreditLimit()==null) || 
             (this.forceCreditLimit!=null &&
              this.forceCreditLimit.equals(other.getForceCreditLimit()))) &&
            ((this.indirectCode==null && other.getIndirectCode()==null) || 
             (this.indirectCode!=null &&
              this.indirectCode.equals(other.getIndirectCode()))) &&
            ((this.creditLimit==null && other.getCreditLimit()==null) || 
             (this.creditLimit!=null &&
              this.creditLimit.equals(other.getCreditLimit()))) &&
            ((this.creditLimitDate==null && other.getCreditLimitDate()==null) || 
             (this.creditLimitDate!=null &&
              this.creditLimitDate.equals(other.getCreditLimitDate()))) &&
            ((this.creditLimitCurrency==null && other.getCreditLimitCurrency()==null) || 
             (this.creditLimitCurrency!=null &&
              this.creditLimitCurrency.equals(other.getCreditLimitCurrency()))) &&
            ((this.arcRecon==null && other.getArcRecon()==null) || 
             (this.arcRecon!=null &&
              this.arcRecon.equals(other.getArcRecon()))) &&
            ((this.arcPaid==null && other.getArcPaid()==null) || 
             (this.arcPaid!=null &&
              this.arcPaid.equals(other.getArcPaid()))) &&
            ((this.umbrellaElementDetails==null && other.getUmbrellaElementDetails()==null) || 
             (this.umbrellaElementDetails!=null &&
              this.umbrellaElementDetails.equals(other.getUmbrellaElementDetails()))) &&
            ((this.shared==null && other.getShared()==null) || 
             (this.shared!=null &&
              this.shared.equals(other.getShared()))) &&
            ((this.postedTo==null && other.getPostedTo()==null) || 
             (this.postedTo!=null &&
              this.postedTo.equals(other.getPostedTo()))) &&
            ((this.promptForAsset==null && other.getPromptForAsset()==null) || 
             (this.promptForAsset!=null &&
              this.promptForAsset.equals(other.getPromptForAsset()))) &&
            ((this.assetCategory==null && other.getAssetCategory()==null) || 
             (this.assetCategory!=null &&
              this.assetCategory.equals(other.getAssetCategory()))) &&
            ((this.extension==null && other.getExtension()==null) || 
             (this.extension!=null &&
              this.extension.equals(other.getExtension()))) &&
            ((this.lastTransactions==null && other.getLastTransactions()==null) || 
             (this.lastTransactions!=null &&
              this.lastTransactions.equals(other.getLastTransactions()))) &&
            ((this.procAllowPrinting==null && other.getProcAllowPrinting()==null) || 
             (this.procAllowPrinting!=null &&
              this.procAllowPrinting.equals(other.getProcAllowPrinting()))) &&
            ((this.procTransmitPDF==null && other.getProcTransmitPDF()==null) || 
             (this.procTransmitPDF!=null &&
              this.procTransmitPDF.equals(other.getProcTransmitPDF()))) &&
            ((this.procTransmitXML==null && other.getProcTransmitXML()==null) || 
             (this.procTransmitXML!=null &&
              this.procTransmitXML.equals(other.getProcTransmitXML()))) &&
            ((this.procTransmitB2B==null && other.getProcTransmitB2B()==null) || 
             (this.procTransmitB2B!=null &&
              this.procTransmitB2B.equals(other.getProcTransmitB2B()))) &&
            ((this.procOrders==null && other.getProcOrders()==null) || 
             (this.procOrders!=null &&
              this.procOrders.equals(other.getProcOrders()))) &&
            ((this.procRequisitions==null && other.getProcRequisitions()==null) || 
             (this.procRequisitions!=null &&
              this.procRequisitions.equals(other.getProcRequisitions()))) &&
            ((this.billingAllowPrinting==null && other.getBillingAllowPrinting()==null) || 
             (this.billingAllowPrinting!=null &&
              this.billingAllowPrinting.equals(other.getBillingAllowPrinting()))) &&
            ((this.billingTransmitPDF==null && other.getBillingTransmitPDF()==null) || 
             (this.billingTransmitPDF!=null &&
              this.billingTransmitPDF.equals(other.getBillingTransmitPDF()))) &&
            ((this.billingTransmitXML==null && other.getBillingTransmitXML()==null) || 
             (this.billingTransmitXML!=null &&
              this.billingTransmitXML.equals(other.getBillingTransmitXML()))) &&
            ((this.billingSIProForma==null && other.getBillingSIProForma()==null) || 
             (this.billingSIProForma!=null &&
              this.billingSIProForma.equals(other.getBillingSIProForma()))) &&
            ((this.billingSIFinal==null && other.getBillingSIFinal()==null) || 
             (this.billingSIFinal!=null &&
              this.billingSIFinal.equals(other.getBillingSIFinal()))) &&
            ((this.billingCNProForma==null && other.getBillingCNProForma()==null) || 
             (this.billingCNProForma!=null &&
              this.billingCNProForma.equals(other.getBillingCNProForma()))) &&
            ((this.billingCNFinal==null && other.getBillingCNFinal()==null) || 
             (this.billingCNFinal!=null &&
              this.billingCNFinal.equals(other.getBillingCNFinal()))) &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.modifyDate==null && other.getModifyDate()==null) || 
             (this.modifyDate!=null &&
              this.modifyDate.equals(other.getModifyDate()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.reportingCode1==null && other.getReportingCode1()==null) || 
             (this.reportingCode1!=null &&
              this.reportingCode1.equals(other.getReportingCode1()))) &&
            ((this.reportingCode2==null && other.getReportingCode2()==null) || 
             (this.reportingCode2!=null &&
              this.reportingCode2.equals(other.getReportingCode2()))) &&
            ((this.reportingCode3==null && other.getReportingCode3()==null) || 
             (this.reportingCode3!=null &&
              this.reportingCode3.equals(other.getReportingCode3()))) &&
            ((this.punchoutCode==null && other.getPunchoutCode()==null) || 
             (this.punchoutCode!=null &&
              this.punchoutCode.equals(other.getPunchoutCode()))) &&
            ((this.punchoutMarketplace==null && other.getPunchoutMarketplace()==null) || 
             (this.punchoutMarketplace!=null &&
              this.punchoutMarketplace.equals(other.getPunchoutMarketplace()))) &&
            ((this.punchoutUrl==null && other.getPunchoutUrl()==null) || 
             (this.punchoutUrl!=null &&
              this.punchoutUrl.equals(other.getPunchoutUrl()))) &&
            ((this.punchoutDomain==null && other.getPunchoutDomain()==null) || 
             (this.punchoutDomain!=null &&
              this.punchoutDomain.equals(other.getPunchoutDomain()))) &&
            ((this.punchoutEncoding==null && other.getPunchoutEncoding()==null) || 
             (this.punchoutEncoding!=null &&
              this.punchoutEncoding.equals(other.getPunchoutEncoding()))) &&
            ((this.punchoutUser==null && other.getPunchoutUser()==null) || 
             (this.punchoutUser!=null &&
              this.punchoutUser.equals(other.getPunchoutUser()))) &&
            ((this.punchoutPassword==null && other.getPunchoutPassword()==null) || 
             (this.punchoutPassword!=null &&
              this.punchoutPassword.equals(other.getPunchoutPassword()))) &&
            ((this.punchoutItemDetailsCode==null && other.getPunchoutItemDetailsCode()==null) || 
             (this.punchoutItemDetailsCode!=null &&
              this.punchoutItemDetailsCode.equals(other.getPunchoutItemDetailsCode()))) &&
            ((this.autoReceipt==null && other.getAutoReceipt()==null) || 
             (this.autoReceipt!=null &&
              this.autoReceipt.equals(other.getAutoReceipt()))) &&
            ((this.procStatus==null && other.getProcStatus()==null) || 
             (this.procStatus!=null &&
              this.procStatus.equals(other.getProcStatus()))) &&
            ((this.toleranceCode==null && other.getToleranceCode()==null) || 
             (this.toleranceCode!=null &&
              this.toleranceCode.equals(other.getToleranceCode()))) &&
            ((this.matchingOffset==null && other.getMatchingOffset()==null) || 
             (this.matchingOffset!=null &&
              this.matchingOffset.equals(other.getMatchingOffset()))) &&
            ((this.procTransLimit==null && other.getProcTransLimit()==null) || 
             (this.procTransLimit!=null &&
              this.procTransLimit.equals(other.getProcTransLimit()))) &&
            ((this.procCalloffs==null && other.getProcCalloffs()==null) || 
             (this.procCalloffs!=null &&
              this.procCalloffs.equals(other.getProcCalloffs()))) &&
            ((this.procGRNs==null && other.getProcGRNs()==null) || 
             (this.procGRNs!=null &&
              this.procGRNs.equals(other.getProcGRNs()))) &&
            ((this.procReturns==null && other.getProcReturns()==null) || 
             (this.procReturns!=null &&
              this.procReturns.equals(other.getProcReturns()))) &&
            ((this.procEmailSalutation==null && other.getProcEmailSalutation()==null) || 
             (this.procEmailSalutation!=null &&
              this.procEmailSalutation.equals(other.getProcEmailSalutation()))) &&
            ((this.procEmailSubject==null && other.getProcEmailSubject()==null) || 
             (this.procEmailSubject!=null &&
              this.procEmailSubject.equals(other.getProcEmailSubject()))) &&
            ((this.billingOutputDevice==null && other.getBillingOutputDevice()==null) || 
             (this.billingOutputDevice!=null &&
              this.billingOutputDevice.equals(other.getBillingOutputDevice()))) &&
            ((this.billingEmailSalutation==null && other.getBillingEmailSalutation()==null) || 
             (this.billingEmailSalutation!=null &&
              this.billingEmailSalutation.equals(other.getBillingEmailSalutation()))) &&
            ((this.billingEmailSubject==null && other.getBillingEmailSubject()==null) || 
             (this.billingEmailSubject!=null &&
              this.billingEmailSubject.equals(other.getBillingEmailSubject()))) &&
            ((this.retail==null && other.getRetail()==null) || 
             (this.retail!=null &&
              this.retail.equals(other.getRetail()))) &&
            ((this.quantities==null && other.getQuantities()==null) || 
             (this.quantities!=null &&
              java.util.Arrays.equals(this.quantities, other.getQuantities()))) &&
            ((this.discounts==null && other.getDiscounts()==null) || 
             (this.discounts!=null &&
              java.util.Arrays.equals(this.discounts, other.getDiscounts()))) &&
            ((this.salesInvoiceData==null && other.getSalesInvoiceData()==null) || 
             (this.salesInvoiceData!=null &&
              this.salesInvoiceData.equals(other.getSalesInvoiceData())));
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
        if (getTimeStamp() != null) {
            _hashCode += getTimeStamp().hashCode();
        }
        if (getTaxesTimeStamp() != null) {
            _hashCode += getTaxesTimeStamp().hashCode();
        }
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        _hashCode += getLevel();
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getCurrencyCode() != null) {
            _hashCode += getCurrencyCode().hashCode();
        }
        if (getSubAnalyse() != null) {
            _hashCode += getSubAnalyse().hashCode();
        }
        if (getTaxCode() != null) {
            _hashCode += getTaxCode().hashCode();
        }
        if (getAccountType() != null) {
            _hashCode += getAccountType().hashCode();
        }
        if (getDateAccountOpened() != null) {
            _hashCode += getDateAccountOpened().hashCode();
        }
        if (getStartPeriod() != null) {
            _hashCode += getStartPeriod().hashCode();
        }
        if (getEndPeriod() != null) {
            _hashCode += getEndPeriod().hashCode();
        }
        if (getCompulsoryDescr() != null) {
            _hashCode += getCompulsoryDescr().hashCode();
        }
        if (getKeepTurnover() != null) {
            _hashCode += getKeepTurnover().hashCode();
        }
        if (getAccountSummary() != null) {
            _hashCode += getAccountSummary().hashCode();
        }
        if (getElementStatus() != null) {
            _hashCode += getElementStatus().hashCode();
        }
        if (getExternalValidation() != null) {
            _hashCode += getExternalValidation().hashCode();
        }
        if (getMemoStatus() != null) {
            _hashCode += getMemoStatus().hashCode();
        }
        if (getBalancingAccount() != null) {
            _hashCode += getBalancingAccount().hashCode();
        }
        if (getSubsLevel() != null) {
            _hashCode += getSubsLevel().hashCode();
        }
        if (getSubsElement() != null) {
            _hashCode += getSubsElement().hashCode();
        }
        if (getMatchable() != null) {
            _hashCode += getMatchable().hashCode();
        }
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        if (getSplit() != null) {
            _hashCode += getSplit().hashCode();
        }
        if (getSettlement() != null) {
            _hashCode += getSettlement().hashCode();
        }
        if (getForceDisperse() != null) {
            _hashCode += getForceDisperse().hashCode();
        }
        if (getUserStatus() != null) {
            _hashCode += getUserStatus().hashCode();
        }
        if (getPayStatus() != null) {
            _hashCode += getPayStatus().hashCode();
        }
        if (getRecStatus() != null) {
            _hashCode += getRecStatus().hashCode();
        }
        if (getEnablePay() != null) {
            _hashCode += getEnablePay().hashCode();
        }
        if (getDefaultMedia() != null) {
            _hashCode += getDefaultMedia().hashCode();
        }
        if (getPaperMedia() != null) {
            _hashCode += getPaperMedia().hashCode();
        }
        if (getElecMedia() != null) {
            _hashCode += getElecMedia().hashCode();
        }
        if (getPayNumber() != null) {
            _hashCode += getPayNumber().hashCode();
        }
        if (getCustomerSupplier() != null) {
            _hashCode += getCustomerSupplier().hashCode();
        }
        if (getIsCustomer() != null) {
            _hashCode += getIsCustomer().hashCode();
        }
        if (getIsSupplier() != null) {
            _hashCode += getIsSupplier().hashCode();
        }
        if (getDiscountEnable() != null) {
            _hashCode += getDiscountEnable().hashCode();
        }
        if (getTaxMethod() != null) {
            _hashCode += getTaxMethod().hashCode();
        }
        if (getTerms() != null) {
            _hashCode += getTerms().hashCode();
        }
        if (getTaxAdjustment() != null) {
            _hashCode += getTaxAdjustment().hashCode();
        }
        if (getTaxRepESL() != null) {
            _hashCode += getTaxRepESL().hashCode();
        }
        if (getTaxRepIntra() != null) {
            _hashCode += getTaxRepIntra().hashCode();
        }
        if (getVAT() != null) {
            _hashCode += getVAT().hashCode();
        }
        if (getTen99() != null) {
            _hashCode += getTen99().hashCode();
        }
        if (getFederalTax() != null) {
            _hashCode += getFederalTax().hashCode();
        }
        if (getSocialSecurity() != null) {
            _hashCode += getSocialSecurity().hashCode();
        }
        if (getTen99Code() != null) {
            _hashCode += getTen99Code().hashCode();
        }
        if (getSecondTIN() != null) {
            _hashCode += getSecondTIN().hashCode();
        }
        if (getSIC() != null) {
            _hashCode += getSIC().hashCode();
        }
        if (getCreditManager() != null) {
            _hashCode += getCreditManager().hashCode();
        }
        if (getCreditRating() != null) {
            _hashCode += getCreditRating().hashCode();
        }
        if (getCreditRatingDate() != null) {
            _hashCode += getCreditRatingDate().hashCode();
        }
        if (getCreditReference() != null) {
            _hashCode += getCreditReference().hashCode();
        }
        if (getCreditAgency() != null) {
            _hashCode += getCreditAgency().hashCode();
        }
        if (getPaymentIndex() != null) {
            _hashCode += getPaymentIndex().hashCode();
        }
        if (getForceCreditLimit() != null) {
            _hashCode += getForceCreditLimit().hashCode();
        }
        if (getIndirectCode() != null) {
            _hashCode += getIndirectCode().hashCode();
        }
        if (getCreditLimit() != null) {
            _hashCode += getCreditLimit().hashCode();
        }
        if (getCreditLimitDate() != null) {
            _hashCode += getCreditLimitDate().hashCode();
        }
        if (getCreditLimitCurrency() != null) {
            _hashCode += getCreditLimitCurrency().hashCode();
        }
        if (getArcRecon() != null) {
            _hashCode += getArcRecon().hashCode();
        }
        if (getArcPaid() != null) {
            _hashCode += getArcPaid().hashCode();
        }
        if (getUmbrellaElementDetails() != null) {
            _hashCode += getUmbrellaElementDetails().hashCode();
        }
        if (getShared() != null) {
            _hashCode += getShared().hashCode();
        }
        if (getPostedTo() != null) {
            _hashCode += getPostedTo().hashCode();
        }
        if (getPromptForAsset() != null) {
            _hashCode += getPromptForAsset().hashCode();
        }
        if (getAssetCategory() != null) {
            _hashCode += getAssetCategory().hashCode();
        }
        if (getExtension() != null) {
            _hashCode += getExtension().hashCode();
        }
        if (getLastTransactions() != null) {
            _hashCode += getLastTransactions().hashCode();
        }
        if (getProcAllowPrinting() != null) {
            _hashCode += getProcAllowPrinting().hashCode();
        }
        if (getProcTransmitPDF() != null) {
            _hashCode += getProcTransmitPDF().hashCode();
        }
        if (getProcTransmitXML() != null) {
            _hashCode += getProcTransmitXML().hashCode();
        }
        if (getProcTransmitB2B() != null) {
            _hashCode += getProcTransmitB2B().hashCode();
        }
        if (getProcOrders() != null) {
            _hashCode += getProcOrders().hashCode();
        }
        if (getProcRequisitions() != null) {
            _hashCode += getProcRequisitions().hashCode();
        }
        if (getBillingAllowPrinting() != null) {
            _hashCode += getBillingAllowPrinting().hashCode();
        }
        if (getBillingTransmitPDF() != null) {
            _hashCode += getBillingTransmitPDF().hashCode();
        }
        if (getBillingTransmitXML() != null) {
            _hashCode += getBillingTransmitXML().hashCode();
        }
        if (getBillingSIProForma() != null) {
            _hashCode += getBillingSIProForma().hashCode();
        }
        if (getBillingSIFinal() != null) {
            _hashCode += getBillingSIFinal().hashCode();
        }
        if (getBillingCNProForma() != null) {
            _hashCode += getBillingCNProForma().hashCode();
        }
        if (getBillingCNFinal() != null) {
            _hashCode += getBillingCNFinal().hashCode();
        }
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getModifyDate() != null) {
            _hashCode += getModifyDate().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getReportingCode1() != null) {
            _hashCode += getReportingCode1().hashCode();
        }
        if (getReportingCode2() != null) {
            _hashCode += getReportingCode2().hashCode();
        }
        if (getReportingCode3() != null) {
            _hashCode += getReportingCode3().hashCode();
        }
        if (getPunchoutCode() != null) {
            _hashCode += getPunchoutCode().hashCode();
        }
        if (getPunchoutMarketplace() != null) {
            _hashCode += getPunchoutMarketplace().hashCode();
        }
        if (getPunchoutUrl() != null) {
            _hashCode += getPunchoutUrl().hashCode();
        }
        if (getPunchoutDomain() != null) {
            _hashCode += getPunchoutDomain().hashCode();
        }
        if (getPunchoutEncoding() != null) {
            _hashCode += getPunchoutEncoding().hashCode();
        }
        if (getPunchoutUser() != null) {
            _hashCode += getPunchoutUser().hashCode();
        }
        if (getPunchoutPassword() != null) {
            _hashCode += getPunchoutPassword().hashCode();
        }
        if (getPunchoutItemDetailsCode() != null) {
            _hashCode += getPunchoutItemDetailsCode().hashCode();
        }
        if (getAutoReceipt() != null) {
            _hashCode += getAutoReceipt().hashCode();
        }
        if (getProcStatus() != null) {
            _hashCode += getProcStatus().hashCode();
        }
        if (getToleranceCode() != null) {
            _hashCode += getToleranceCode().hashCode();
        }
        if (getMatchingOffset() != null) {
            _hashCode += getMatchingOffset().hashCode();
        }
        if (getProcTransLimit() != null) {
            _hashCode += getProcTransLimit().hashCode();
        }
        if (getProcCalloffs() != null) {
            _hashCode += getProcCalloffs().hashCode();
        }
        if (getProcGRNs() != null) {
            _hashCode += getProcGRNs().hashCode();
        }
        if (getProcReturns() != null) {
            _hashCode += getProcReturns().hashCode();
        }
        if (getProcEmailSalutation() != null) {
            _hashCode += getProcEmailSalutation().hashCode();
        }
        if (getProcEmailSubject() != null) {
            _hashCode += getProcEmailSubject().hashCode();
        }
        if (getBillingOutputDevice() != null) {
            _hashCode += getBillingOutputDevice().hashCode();
        }
        if (getBillingEmailSalutation() != null) {
            _hashCode += getBillingEmailSalutation().hashCode();
        }
        if (getBillingEmailSubject() != null) {
            _hashCode += getBillingEmailSubject().hashCode();
        }
        if (getRetail() != null) {
            _hashCode += getRetail().hashCode();
        }
        if (getQuantities() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getQuantities());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getQuantities(), i);
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
        if (getSalesInvoiceData() != null) {
            _hashCode += getSalesInvoiceData().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Master.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Master"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxesTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxesTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CurrencyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subAnalyse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SubAnalyse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dateAccountOpened");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DateAccountOpened"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "StartPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "EndPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compulsoryDescr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CompulsoryDescr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keepTurnover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "KeepTurnover"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountSummary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AccountSummary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElementStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("externalValidation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ExternalValidation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("memoStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MemoStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balancingAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BalancingAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subsLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SubsLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subsElement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SubsElement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Matchable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("split");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Split"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("settlement");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Settlement"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forceDisperse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ForceDisperse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PayStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RecStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enablePay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "EnablePay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultMedia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DefaultMedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paperMedia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PaperMedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elecMedia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElecMedia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("payNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PayNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CustomerSupplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isCustomer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IsCustomer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("isSupplier");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IsSupplier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discountEnable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountEnable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxMethod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxMethod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("terms");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Terms"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxAdjustment");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxAdjustment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxRepESL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxRepESL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxRepIntra");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TaxRepIntra"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VAT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "VAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten99");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Ten99"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("federalTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FederalTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("socialSecurity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SocialSecurity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ten99Code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Ten99Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondTIN");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SecondTIN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SIC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SIC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditManager");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditManager"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditRating");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditRating"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditRatingDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditRatingDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditReference");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditReference"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditAgency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditAgency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PaymentIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("forceCreditLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ForceCreditLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirectCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "IndirectCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditLimitDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditLimitDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("creditLimitCurrency");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreditLimitCurrency"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arcRecon");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ArcRecon"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arcPaid");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ArcPaid"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("umbrellaElementDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UmbrellaElementDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "UmbrellaData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shared");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Shared"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postedTo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PostedTo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("promptForAsset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PromptForAsset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetCategory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AssetCategory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Extension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "ExtensionRef"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastTransactions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransactions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LastTransactions"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procAllowPrinting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcAllowPrinting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procTransmitPDF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcTransmitPDF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procTransmitXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcTransmitXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procTransmitB2B");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcTransmitB2B"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procOrders");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcOrders"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procRequisitions");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcRequisitions"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingAllowPrinting");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingAllowPrinting"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingTransmitPDF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingTransmitPDF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingTransmitXML");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingTransmitXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingSIProForma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingSIProForma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingSIFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingSIFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingCNProForma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingCNProForma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingCNFinal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingCNFinal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CreateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ModifyDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportingCode1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ReportingCode1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportingCode2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ReportingCode2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reportingCode3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ReportingCode3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutMarketplace");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutMarketplace"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutUrl");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutUrl"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutDomain");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutDomain"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutEncoding");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutEncoding"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutUser");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutPassword");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutPassword"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutItemDetailsCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutItemDetailsCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("autoReceipt");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AutoReceipt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toleranceCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ToleranceCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matchingOffset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MatchingOffset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procTransLimit");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcTransLimit"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procCalloffs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcCalloffs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procGRNs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcGRNs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procReturns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcReturns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procEmailSalutation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcEmailSalutation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("procEmailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ProcEmailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingOutputDevice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingOutputDevice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingEmailSalutation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingEmailSalutation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("billingEmailSubject");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BillingEmailSubject"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retail");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Retail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("quantities");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Quantities"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "QuantityElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Quantity"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("discounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Discounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "DiscountElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Discount"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesInvoiceData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "SalesInvoiceData"));
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
