/**
 * Element.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element holds an element
 *             master.
 */
public class Element  extends com.coda.www.efinance.schemas.elementmaster.Master  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses;

    /* Holds details of the
     *                                 element's
     *                             banks. */
    private com.coda.www.efinance.schemas.elementmaster.BankElement[] banks;

    /* Holds the
     *                                 element's comment
     *                             list. */
    private java.lang.String[] comments;

    /* Holds the
     *                                 element's group list, which is a
     *                                 collection of group master codes
     *                                 identifying the groups to which the
     * element belongs. */
    private java.lang.String[] groups;

    /* Holds the
     *                                 element's media list, which is a
     *                                 collection of media master codes
     *                                 identifying the media by which payments
     * are made. */
    private java.lang.String[] medias;

    /* Holds a list of the
     *                                 element's left
     *                             rules. */
    private com.coda.www.efinance.schemas.elementmaster.RuleElement[] leftRules;

    /* Holds a list of the
     *                                 element's right
     *                             rules. */
    private com.coda.www.efinance.schemas.elementmaster.RuleElement[] rightRules;

    /* Holds a list of the
     *                                 element's
     *                             mnemonics. */
    private java.lang.String[] mnemonics;

    /* Holds the advanced
     *                                 punchout parameters for a
     *                             supplier. */
    private com.coda.www.efinance.schemas.elementmaster.PunchoutElement[] punchoutAdvancedParams;

    /* This element contains
     *                                 autput device data. */
    private java.lang.String[] outputDevices;

    public Element() {
    }

    public Element(
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
           com.coda.www.efinance.schemas.elementmaster.SalesInvoiceData salesInvoiceData,
           com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses,
           com.coda.www.efinance.schemas.elementmaster.BankElement[] banks,
           java.lang.String[] comments,
           java.lang.String[] groups,
           java.lang.String[] medias,
           com.coda.www.efinance.schemas.elementmaster.RuleElement[] leftRules,
           com.coda.www.efinance.schemas.elementmaster.RuleElement[] rightRules,
           java.lang.String[] mnemonics,
           com.coda.www.efinance.schemas.elementmaster.PunchoutElement[] punchoutAdvancedParams,
           java.lang.String[] outputDevices) {
        super(
            timeStamp,
            taxesTimeStamp,
            cmpCode,
            code,
            level,
            name,
            shortName,
            currencyCode,
            subAnalyse,
            taxCode,
            accountType,
            dateAccountOpened,
            startPeriod,
            endPeriod,
            compulsoryDescr,
            keepTurnover,
            accountSummary,
            elementStatus,
            externalValidation,
            memoStatus,
            balancingAccount,
            subsLevel,
            subsElement,
            matchable,
            summary,
            split,
            settlement,
            forceDisperse,
            userStatus,
            payStatus,
            recStatus,
            enablePay,
            defaultMedia,
            paperMedia,
            elecMedia,
            payNumber,
            customerSupplier,
            isCustomer,
            isSupplier,
            discountEnable,
            taxMethod,
            terms,
            taxAdjustment,
            taxRepESL,
            taxRepIntra,
            VAT,
            ten99,
            federalTax,
            socialSecurity,
            ten99Code,
            secondTIN,
            SIC,
            creditManager,
            creditRating,
            creditRatingDate,
            creditReference,
            creditAgency,
            paymentIndex,
            forceCreditLimit,
            indirectCode,
            creditLimit,
            creditLimitDate,
            creditLimitCurrency,
            arcRecon,
            arcPaid,
            umbrellaElementDetails,
            shared,
            postedTo,
            promptForAsset,
            assetCategory,
            extension,
            lastTransactions,
            procAllowPrinting,
            procTransmitPDF,
            procTransmitXML,
            procTransmitB2B,
            procOrders,
            procRequisitions,
            billingAllowPrinting,
            billingTransmitPDF,
            billingTransmitXML,
            billingSIProForma,
            billingSIFinal,
            billingCNProForma,
            billingCNFinal,
            createDate,
            modifyDate,
            user,
            reportingCode1,
            reportingCode2,
            reportingCode3,
            punchoutCode,
            punchoutMarketplace,
            punchoutUrl,
            punchoutDomain,
            punchoutEncoding,
            punchoutUser,
            punchoutPassword,
            punchoutItemDetailsCode,
            autoReceipt,
            procStatus,
            toleranceCode,
            matchingOffset,
            procTransLimit,
            procCalloffs,
            procGRNs,
            procReturns,
            procEmailSalutation,
            procEmailSubject,
            billingOutputDevice,
            billingEmailSalutation,
            billingEmailSubject,
            retail,
            quantities,
            discounts,
            salesInvoiceData);
        this.addresses = addresses;
        this.banks = banks;
        this.comments = comments;
        this.groups = groups;
        this.medias = medias;
        this.leftRules = leftRules;
        this.rightRules = rightRules;
        this.mnemonics = mnemonics;
        this.punchoutAdvancedParams = punchoutAdvancedParams;
        this.outputDevices = outputDevices;
    }


    /**
     * Gets the addresses value for this Element.
     * 
     * @return addresses
     */
    public com.coda.www.efinance.schemas.elementmaster.AddressElement[] getAddresses() {
        return addresses;
    }


    /**
     * Sets the addresses value for this Element.
     * 
     * @param addresses
     */
    public void setAddresses(com.coda.www.efinance.schemas.elementmaster.AddressElement[] addresses) {
        this.addresses = addresses;
    }


    /**
     * Gets the banks value for this Element.
     * 
     * @return banks   * Holds details of the
     *                                 element's
     *                             banks.
     */
    public com.coda.www.efinance.schemas.elementmaster.BankElement[] getBanks() {
        return banks;
    }


    /**
     * Sets the banks value for this Element.
     * 
     * @param banks   * Holds details of the
     *                                 element's
     *                             banks.
     */
    public void setBanks(com.coda.www.efinance.schemas.elementmaster.BankElement[] banks) {
        this.banks = banks;
    }


    /**
     * Gets the comments value for this Element.
     * 
     * @return comments   * Holds the
     *                                 element's comment
     *                             list.
     */
    public java.lang.String[] getComments() {
        return comments;
    }


    /**
     * Sets the comments value for this Element.
     * 
     * @param comments   * Holds the
     *                                 element's comment
     *                             list.
     */
    public void setComments(java.lang.String[] comments) {
        this.comments = comments;
    }


    /**
     * Gets the groups value for this Element.
     * 
     * @return groups   * Holds the
     *                                 element's group list, which is a
     *                                 collection of group master codes
     *                                 identifying the groups to which the
     * element belongs.
     */
    public java.lang.String[] getGroups() {
        return groups;
    }


    /**
     * Sets the groups value for this Element.
     * 
     * @param groups   * Holds the
     *                                 element's group list, which is a
     *                                 collection of group master codes
     *                                 identifying the groups to which the
     * element belongs.
     */
    public void setGroups(java.lang.String[] groups) {
        this.groups = groups;
    }


    /**
     * Gets the medias value for this Element.
     * 
     * @return medias   * Holds the
     *                                 element's media list, which is a
     *                                 collection of media master codes
     *                                 identifying the media by which payments
     * are made.
     */
    public java.lang.String[] getMedias() {
        return medias;
    }


    /**
     * Sets the medias value for this Element.
     * 
     * @param medias   * Holds the
     *                                 element's media list, which is a
     *                                 collection of media master codes
     *                                 identifying the media by which payments
     * are made.
     */
    public void setMedias(java.lang.String[] medias) {
        this.medias = medias;
    }


    /**
     * Gets the leftRules value for this Element.
     * 
     * @return leftRules   * Holds a list of the
     *                                 element's left
     *                             rules.
     */
    public com.coda.www.efinance.schemas.elementmaster.RuleElement[] getLeftRules() {
        return leftRules;
    }


    /**
     * Sets the leftRules value for this Element.
     * 
     * @param leftRules   * Holds a list of the
     *                                 element's left
     *                             rules.
     */
    public void setLeftRules(com.coda.www.efinance.schemas.elementmaster.RuleElement[] leftRules) {
        this.leftRules = leftRules;
    }


    /**
     * Gets the rightRules value for this Element.
     * 
     * @return rightRules   * Holds a list of the
     *                                 element's right
     *                             rules.
     */
    public com.coda.www.efinance.schemas.elementmaster.RuleElement[] getRightRules() {
        return rightRules;
    }


    /**
     * Sets the rightRules value for this Element.
     * 
     * @param rightRules   * Holds a list of the
     *                                 element's right
     *                             rules.
     */
    public void setRightRules(com.coda.www.efinance.schemas.elementmaster.RuleElement[] rightRules) {
        this.rightRules = rightRules;
    }


    /**
     * Gets the mnemonics value for this Element.
     * 
     * @return mnemonics   * Holds a list of the
     *                                 element's
     *                             mnemonics.
     */
    public java.lang.String[] getMnemonics() {
        return mnemonics;
    }


    /**
     * Sets the mnemonics value for this Element.
     * 
     * @param mnemonics   * Holds a list of the
     *                                 element's
     *                             mnemonics.
     */
    public void setMnemonics(java.lang.String[] mnemonics) {
        this.mnemonics = mnemonics;
    }


    /**
     * Gets the punchoutAdvancedParams value for this Element.
     * 
     * @return punchoutAdvancedParams   * Holds the advanced
     *                                 punchout parameters for a
     *                             supplier.
     */
    public com.coda.www.efinance.schemas.elementmaster.PunchoutElement[] getPunchoutAdvancedParams() {
        return punchoutAdvancedParams;
    }


    /**
     * Sets the punchoutAdvancedParams value for this Element.
     * 
     * @param punchoutAdvancedParams   * Holds the advanced
     *                                 punchout parameters for a
     *                             supplier.
     */
    public void setPunchoutAdvancedParams(com.coda.www.efinance.schemas.elementmaster.PunchoutElement[] punchoutAdvancedParams) {
        this.punchoutAdvancedParams = punchoutAdvancedParams;
    }


    /**
     * Gets the outputDevices value for this Element.
     * 
     * @return outputDevices   * This element contains
     *                                 autput device data.
     */
    public java.lang.String[] getOutputDevices() {
        return outputDevices;
    }


    /**
     * Sets the outputDevices value for this Element.
     * 
     * @param outputDevices   * This element contains
     *                                 autput device data.
     */
    public void setOutputDevices(java.lang.String[] outputDevices) {
        this.outputDevices = outputDevices;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Element)) return false;
        Element other = (Element) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.addresses==null && other.getAddresses()==null) || 
             (this.addresses!=null &&
              java.util.Arrays.equals(this.addresses, other.getAddresses()))) &&
            ((this.banks==null && other.getBanks()==null) || 
             (this.banks!=null &&
              java.util.Arrays.equals(this.banks, other.getBanks()))) &&
            ((this.comments==null && other.getComments()==null) || 
             (this.comments!=null &&
              java.util.Arrays.equals(this.comments, other.getComments()))) &&
            ((this.groups==null && other.getGroups()==null) || 
             (this.groups!=null &&
              java.util.Arrays.equals(this.groups, other.getGroups()))) &&
            ((this.medias==null && other.getMedias()==null) || 
             (this.medias!=null &&
              java.util.Arrays.equals(this.medias, other.getMedias()))) &&
            ((this.leftRules==null && other.getLeftRules()==null) || 
             (this.leftRules!=null &&
              java.util.Arrays.equals(this.leftRules, other.getLeftRules()))) &&
            ((this.rightRules==null && other.getRightRules()==null) || 
             (this.rightRules!=null &&
              java.util.Arrays.equals(this.rightRules, other.getRightRules()))) &&
            ((this.mnemonics==null && other.getMnemonics()==null) || 
             (this.mnemonics!=null &&
              java.util.Arrays.equals(this.mnemonics, other.getMnemonics()))) &&
            ((this.punchoutAdvancedParams==null && other.getPunchoutAdvancedParams()==null) || 
             (this.punchoutAdvancedParams!=null &&
              java.util.Arrays.equals(this.punchoutAdvancedParams, other.getPunchoutAdvancedParams()))) &&
            ((this.outputDevices==null && other.getOutputDevices()==null) || 
             (this.outputDevices!=null &&
              java.util.Arrays.equals(this.outputDevices, other.getOutputDevices())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getAddresses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAddresses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAddresses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBanks() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBanks());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBanks(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getComments() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getComments());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getComments(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGroups() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGroups());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGroups(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMedias() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMedias());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMedias(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLeftRules() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLeftRules());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLeftRules(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getRightRules() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRightRules());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRightRules(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMnemonics() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMnemonics());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMnemonics(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPunchoutAdvancedParams() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPunchoutAdvancedParams());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPunchoutAdvancedParams(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getOutputDevices() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getOutputDevices());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getOutputDevices(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Element.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Element"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addresses");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Addresses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "AddressElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Address"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("banks");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Banks"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "BankElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Bank"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comments");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Comments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeTextB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Comment"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("groups");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Groups"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "GroupCode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("medias");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Medias"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "MediaCode"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("leftRules");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "LeftRules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Rule"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rightRules");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RightRules"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RuleElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Rule"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mnemonics");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mnemonics"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeText"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Mnemonic"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("punchoutAdvancedParams");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutAdvancedParams"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutElement"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "PunchoutAdvancedParam"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputDevices");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDevices"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "OutputDeviceCode"));
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
