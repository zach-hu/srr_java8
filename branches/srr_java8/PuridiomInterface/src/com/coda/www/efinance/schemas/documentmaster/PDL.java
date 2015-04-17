/**
 * PDL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This
 *                 element contains information about a predefined
 *             line.
 */
public class PDL  implements java.io.Serializable {
    private java.lang.String accountCode;

    /* This elements specifies
     *                         whether the elements in the account code are
     * protected during Input. */
    private com.coda.www.efinance.schemas.documentmaster.ProtectedElements protectElements;

    /* The
     *                         amount specified in the predefined
     *                     line. */
    private java.math.BigDecimal amount;

    /* Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input. */
    private boolean protectAmount;

    /* The tax value in document
     *                     currency. */
    private java.math.BigDecimal taxValue;

    /* Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input. */
    private boolean protectTaxValue;

    /* The tax
     *                     code. */
    private java.lang.String tax;

    /* Specifies that this code is
     *                         protected and cannot be edited during
     *                     Input. */
    private boolean protectTax;

    /* The document line
     *                     description. */
    private java.lang.String description;

    /* Specifies that this
     *                         description is protected and cannot be edited
     * during Input. */
    private boolean protectDescription;

    /* This
     *                         element holds a list of external
     *                     references. */
    private com.coda.www.efinance.schemas.documentmaster.PDLExtRefs PDLExtRefs;

    /* The line type. The options
     *                         are: Analysis, Summary or
     *                     Tax. */
    private java.lang.String lineType;

    /* The single-character user
     *                     status. */
    private java.lang.String userStatus;

    /* Specifies that the line type
     *                         is protected and cannot be edited during
     *                     Input. */
    private boolean protectLineType;

    /* Specifies that the user
     *                         status is protected at Input
     *                     time. */
    private boolean protectUserStatus;

    /* Specifies that this line must
     *                         be reviewed. */
    private boolean review;

    /* Specifies that zero values
     *                         are allowed. */
    private boolean zeroValues;

    /* The InterCompany destination
     *                     code. */
    private java.lang.String interCmpDest;

    /* Specifies that the
     *                         InterCompany destination code is protected
     * during Input. */
    private boolean protectInterCmpDest;

    /* The Sense of
     *                     line. */
    private java.lang.String lineSense;

    /* Specifies that the line sense
     *                         is protected during Input. */
    private boolean protectLineSense;

    public PDL() {
    }

    public PDL(
           java.lang.String accountCode,
           com.coda.www.efinance.schemas.documentmaster.ProtectedElements protectElements,
           java.math.BigDecimal amount,
           boolean protectAmount,
           java.math.BigDecimal taxValue,
           boolean protectTaxValue,
           java.lang.String tax,
           boolean protectTax,
           java.lang.String description,
           boolean protectDescription,
           com.coda.www.efinance.schemas.documentmaster.PDLExtRefs PDLExtRefs,
           java.lang.String lineType,
           java.lang.String userStatus,
           boolean protectLineType,
           boolean protectUserStatus,
           boolean review,
           boolean zeroValues,
           java.lang.String interCmpDest,
           boolean protectInterCmpDest,
           java.lang.String lineSense,
           boolean protectLineSense) {
           this.accountCode = accountCode;
           this.protectElements = protectElements;
           this.amount = amount;
           this.protectAmount = protectAmount;
           this.taxValue = taxValue;
           this.protectTaxValue = protectTaxValue;
           this.tax = tax;
           this.protectTax = protectTax;
           this.description = description;
           this.protectDescription = protectDescription;
           this.PDLExtRefs = PDLExtRefs;
           this.lineType = lineType;
           this.userStatus = userStatus;
           this.protectLineType = protectLineType;
           this.protectUserStatus = protectUserStatus;
           this.review = review;
           this.zeroValues = zeroValues;
           this.interCmpDest = interCmpDest;
           this.protectInterCmpDest = protectInterCmpDest;
           this.lineSense = lineSense;
           this.protectLineSense = protectLineSense;
    }


    /**
     * Gets the accountCode value for this PDL.
     * 
     * @return accountCode
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this PDL.
     * 
     * @param accountCode
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the protectElements value for this PDL.
     * 
     * @return protectElements   * This elements specifies
     *                         whether the elements in the account code are
     * protected during Input.
     */
    public com.coda.www.efinance.schemas.documentmaster.ProtectedElements getProtectElements() {
        return protectElements;
    }


    /**
     * Sets the protectElements value for this PDL.
     * 
     * @param protectElements   * This elements specifies
     *                         whether the elements in the account code are
     * protected during Input.
     */
    public void setProtectElements(com.coda.www.efinance.schemas.documentmaster.ProtectedElements protectElements) {
        this.protectElements = protectElements;
    }


    /**
     * Gets the amount value for this PDL.
     * 
     * @return amount   * The
     *                         amount specified in the predefined
     *                     line.
     */
    public java.math.BigDecimal getAmount() {
        return amount;
    }


    /**
     * Sets the amount value for this PDL.
     * 
     * @param amount   * The
     *                         amount specified in the predefined
     *                     line.
     */
    public void setAmount(java.math.BigDecimal amount) {
        this.amount = amount;
    }


    /**
     * Gets the protectAmount value for this PDL.
     * 
     * @return protectAmount   * Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public boolean isProtectAmount() {
        return protectAmount;
    }


    /**
     * Sets the protectAmount value for this PDL.
     * 
     * @param protectAmount   * Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public void setProtectAmount(boolean protectAmount) {
        this.protectAmount = protectAmount;
    }


    /**
     * Gets the taxValue value for this PDL.
     * 
     * @return taxValue   * The tax value in document
     *                     currency.
     */
    public java.math.BigDecimal getTaxValue() {
        return taxValue;
    }


    /**
     * Sets the taxValue value for this PDL.
     * 
     * @param taxValue   * The tax value in document
     *                     currency.
     */
    public void setTaxValue(java.math.BigDecimal taxValue) {
        this.taxValue = taxValue;
    }


    /**
     * Gets the protectTaxValue value for this PDL.
     * 
     * @return protectTaxValue   * Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public boolean isProtectTaxValue() {
        return protectTaxValue;
    }


    /**
     * Sets the protectTaxValue value for this PDL.
     * 
     * @param protectTaxValue   * Specifies that this value is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public void setProtectTaxValue(boolean protectTaxValue) {
        this.protectTaxValue = protectTaxValue;
    }


    /**
     * Gets the tax value for this PDL.
     * 
     * @return tax   * The tax
     *                     code.
     */
    public java.lang.String getTax() {
        return tax;
    }


    /**
     * Sets the tax value for this PDL.
     * 
     * @param tax   * The tax
     *                     code.
     */
    public void setTax(java.lang.String tax) {
        this.tax = tax;
    }


    /**
     * Gets the protectTax value for this PDL.
     * 
     * @return protectTax   * Specifies that this code is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public boolean isProtectTax() {
        return protectTax;
    }


    /**
     * Sets the protectTax value for this PDL.
     * 
     * @param protectTax   * Specifies that this code is
     *                         protected and cannot be edited during
     *                     Input.
     */
    public void setProtectTax(boolean protectTax) {
        this.protectTax = protectTax;
    }


    /**
     * Gets the description value for this PDL.
     * 
     * @return description   * The document line
     *                     description.
     */
    public java.lang.String getDescription() {
        return description;
    }


    /**
     * Sets the description value for this PDL.
     * 
     * @param description   * The document line
     *                     description.
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }


    /**
     * Gets the protectDescription value for this PDL.
     * 
     * @return protectDescription   * Specifies that this
     *                         description is protected and cannot be edited
     * during Input.
     */
    public boolean isProtectDescription() {
        return protectDescription;
    }


    /**
     * Sets the protectDescription value for this PDL.
     * 
     * @param protectDescription   * Specifies that this
     *                         description is protected and cannot be edited
     * during Input.
     */
    public void setProtectDescription(boolean protectDescription) {
        this.protectDescription = protectDescription;
    }


    /**
     * Gets the PDLExtRefs value for this PDL.
     * 
     * @return PDLExtRefs   * This
     *                         element holds a list of external
     *                     references.
     */
    public com.coda.www.efinance.schemas.documentmaster.PDLExtRefs getPDLExtRefs() {
        return PDLExtRefs;
    }


    /**
     * Sets the PDLExtRefs value for this PDL.
     * 
     * @param PDLExtRefs   * This
     *                         element holds a list of external
     *                     references.
     */
    public void setPDLExtRefs(com.coda.www.efinance.schemas.documentmaster.PDLExtRefs PDLExtRefs) {
        this.PDLExtRefs = PDLExtRefs;
    }


    /**
     * Gets the lineType value for this PDL.
     * 
     * @return lineType   * The line type. The options
     *                         are: Analysis, Summary or
     *                     Tax.
     */
    public java.lang.String getLineType() {
        return lineType;
    }


    /**
     * Sets the lineType value for this PDL.
     * 
     * @param lineType   * The line type. The options
     *                         are: Analysis, Summary or
     *                     Tax.
     */
    public void setLineType(java.lang.String lineType) {
        this.lineType = lineType;
    }


    /**
     * Gets the userStatus value for this PDL.
     * 
     * @return userStatus   * The single-character user
     *                     status.
     */
    public java.lang.String getUserStatus() {
        return userStatus;
    }


    /**
     * Sets the userStatus value for this PDL.
     * 
     * @param userStatus   * The single-character user
     *                     status.
     */
    public void setUserStatus(java.lang.String userStatus) {
        this.userStatus = userStatus;
    }


    /**
     * Gets the protectLineType value for this PDL.
     * 
     * @return protectLineType   * Specifies that the line type
     *                         is protected and cannot be edited during
     *                     Input.
     */
    public boolean isProtectLineType() {
        return protectLineType;
    }


    /**
     * Sets the protectLineType value for this PDL.
     * 
     * @param protectLineType   * Specifies that the line type
     *                         is protected and cannot be edited during
     *                     Input.
     */
    public void setProtectLineType(boolean protectLineType) {
        this.protectLineType = protectLineType;
    }


    /**
     * Gets the protectUserStatus value for this PDL.
     * 
     * @return protectUserStatus   * Specifies that the user
     *                         status is protected at Input
     *                     time.
     */
    public boolean isProtectUserStatus() {
        return protectUserStatus;
    }


    /**
     * Sets the protectUserStatus value for this PDL.
     * 
     * @param protectUserStatus   * Specifies that the user
     *                         status is protected at Input
     *                     time.
     */
    public void setProtectUserStatus(boolean protectUserStatus) {
        this.protectUserStatus = protectUserStatus;
    }


    /**
     * Gets the review value for this PDL.
     * 
     * @return review   * Specifies that this line must
     *                         be reviewed.
     */
    public boolean isReview() {
        return review;
    }


    /**
     * Sets the review value for this PDL.
     * 
     * @param review   * Specifies that this line must
     *                         be reviewed.
     */
    public void setReview(boolean review) {
        this.review = review;
    }


    /**
     * Gets the zeroValues value for this PDL.
     * 
     * @return zeroValues   * Specifies that zero values
     *                         are allowed.
     */
    public boolean isZeroValues() {
        return zeroValues;
    }


    /**
     * Sets the zeroValues value for this PDL.
     * 
     * @param zeroValues   * Specifies that zero values
     *                         are allowed.
     */
    public void setZeroValues(boolean zeroValues) {
        this.zeroValues = zeroValues;
    }


    /**
     * Gets the interCmpDest value for this PDL.
     * 
     * @return interCmpDest   * The InterCompany destination
     *                     code.
     */
    public java.lang.String getInterCmpDest() {
        return interCmpDest;
    }


    /**
     * Sets the interCmpDest value for this PDL.
     * 
     * @param interCmpDest   * The InterCompany destination
     *                     code.
     */
    public void setInterCmpDest(java.lang.String interCmpDest) {
        this.interCmpDest = interCmpDest;
    }


    /**
     * Gets the protectInterCmpDest value for this PDL.
     * 
     * @return protectInterCmpDest   * Specifies that the
     *                         InterCompany destination code is protected
     * during Input.
     */
    public boolean isProtectInterCmpDest() {
        return protectInterCmpDest;
    }


    /**
     * Sets the protectInterCmpDest value for this PDL.
     * 
     * @param protectInterCmpDest   * Specifies that the
     *                         InterCompany destination code is protected
     * during Input.
     */
    public void setProtectInterCmpDest(boolean protectInterCmpDest) {
        this.protectInterCmpDest = protectInterCmpDest;
    }


    /**
     * Gets the lineSense value for this PDL.
     * 
     * @return lineSense   * The Sense of
     *                     line.
     */
    public java.lang.String getLineSense() {
        return lineSense;
    }


    /**
     * Sets the lineSense value for this PDL.
     * 
     * @param lineSense   * The Sense of
     *                     line.
     */
    public void setLineSense(java.lang.String lineSense) {
        this.lineSense = lineSense;
    }


    /**
     * Gets the protectLineSense value for this PDL.
     * 
     * @return protectLineSense   * Specifies that the line sense
     *                         is protected during Input.
     */
    public boolean isProtectLineSense() {
        return protectLineSense;
    }


    /**
     * Sets the protectLineSense value for this PDL.
     * 
     * @param protectLineSense   * Specifies that the line sense
     *                         is protected during Input.
     */
    public void setProtectLineSense(boolean protectLineSense) {
        this.protectLineSense = protectLineSense;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PDL)) return false;
        PDL other = (PDL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accountCode==null && other.getAccountCode()==null) || 
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode()))) &&
            ((this.protectElements==null && other.getProtectElements()==null) || 
             (this.protectElements!=null &&
              this.protectElements.equals(other.getProtectElements()))) &&
            ((this.amount==null && other.getAmount()==null) || 
             (this.amount!=null &&
              this.amount.equals(other.getAmount()))) &&
            this.protectAmount == other.isProtectAmount() &&
            ((this.taxValue==null && other.getTaxValue()==null) || 
             (this.taxValue!=null &&
              this.taxValue.equals(other.getTaxValue()))) &&
            this.protectTaxValue == other.isProtectTaxValue() &&
            ((this.tax==null && other.getTax()==null) || 
             (this.tax!=null &&
              this.tax.equals(other.getTax()))) &&
            this.protectTax == other.isProtectTax() &&
            ((this.description==null && other.getDescription()==null) || 
             (this.description!=null &&
              this.description.equals(other.getDescription()))) &&
            this.protectDescription == other.isProtectDescription() &&
            ((this.PDLExtRefs==null && other.getPDLExtRefs()==null) || 
             (this.PDLExtRefs!=null &&
              this.PDLExtRefs.equals(other.getPDLExtRefs()))) &&
            ((this.lineType==null && other.getLineType()==null) || 
             (this.lineType!=null &&
              this.lineType.equals(other.getLineType()))) &&
            ((this.userStatus==null && other.getUserStatus()==null) || 
             (this.userStatus!=null &&
              this.userStatus.equals(other.getUserStatus()))) &&
            this.protectLineType == other.isProtectLineType() &&
            this.protectUserStatus == other.isProtectUserStatus() &&
            this.review == other.isReview() &&
            this.zeroValues == other.isZeroValues() &&
            ((this.interCmpDest==null && other.getInterCmpDest()==null) || 
             (this.interCmpDest!=null &&
              this.interCmpDest.equals(other.getInterCmpDest()))) &&
            this.protectInterCmpDest == other.isProtectInterCmpDest() &&
            ((this.lineSense==null && other.getLineSense()==null) || 
             (this.lineSense!=null &&
              this.lineSense.equals(other.getLineSense()))) &&
            this.protectLineSense == other.isProtectLineSense();
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
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        if (getProtectElements() != null) {
            _hashCode += getProtectElements().hashCode();
        }
        if (getAmount() != null) {
            _hashCode += getAmount().hashCode();
        }
        _hashCode += (isProtectAmount() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTaxValue() != null) {
            _hashCode += getTaxValue().hashCode();
        }
        _hashCode += (isProtectTaxValue() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getTax() != null) {
            _hashCode += getTax().hashCode();
        }
        _hashCode += (isProtectTax() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getDescription() != null) {
            _hashCode += getDescription().hashCode();
        }
        _hashCode += (isProtectDescription() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getPDLExtRefs() != null) {
            _hashCode += getPDLExtRefs().hashCode();
        }
        if (getLineType() != null) {
            _hashCode += getLineType().hashCode();
        }
        if (getUserStatus() != null) {
            _hashCode += getUserStatus().hashCode();
        }
        _hashCode += (isProtectLineType() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isProtectUserStatus() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isReview() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isZeroValues() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getInterCmpDest() != null) {
            _hashCode += getInterCmpDest().hashCode();
        }
        _hashCode += (isProtectInterCmpDest() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getLineSense() != null) {
            _hashCode += getLineSense().hashCode();
        }
        _hashCode += (isProtectLineSense() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PDL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectElements");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectElements"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectedElements"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Amount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectAmount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectAmount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("taxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "TaxValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectTaxValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectTaxValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Tax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("description");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Description"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PDLExtRefs");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDLExtRefs"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "PDLExtRefs"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "LineType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectLineType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectLineType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectUserStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectUserStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("review");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Review"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zeroValues");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ZeroValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interCmpDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "InterCmpDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectInterCmpDest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectInterCmpDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineSense");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "LineSense"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("protectLineSense");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ProtectLineSense"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
