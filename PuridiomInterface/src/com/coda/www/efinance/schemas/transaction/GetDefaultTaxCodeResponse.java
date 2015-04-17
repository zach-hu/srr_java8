/**
 * GetDefaultTaxCodeResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class GetDefaultTaxCodeResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* A company
     *                             code. */
    private java.lang.String companyCode;

    /* An intercompany
     *                                 destination code. */
    private java.lang.String destinationCode;

    /* An account
     *                             code. */
    private java.lang.String accountCode;

    /* A tax
     *                             code. */
    private java.lang.String taxCode;

    public GetDefaultTaxCodeResponse() {
    }

    public GetDefaultTaxCodeResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String companyCode,
           java.lang.String destinationCode,
           java.lang.String accountCode,
           java.lang.String taxCode) {
        super();
        this.companyCode = companyCode;
        this.destinationCode = destinationCode;
        this.accountCode = accountCode;
        this.taxCode = taxCode;
    }


    /**
     * Gets the companyCode value for this GetDefaultTaxCodeResponse.
     *
     * @return companyCode   * A company
     *                             code.
     */
    public java.lang.String getCompanyCode() {
        return companyCode;
    }


    /**
     * Sets the companyCode value for this GetDefaultTaxCodeResponse.
     *
     * @param companyCode   * A company
     *                             code.
     */
    public void setCompanyCode(java.lang.String companyCode) {
        this.companyCode = companyCode;
    }


    /**
     * Gets the destinationCode value for this GetDefaultTaxCodeResponse.
     *
     * @return destinationCode   * An intercompany
     *                                 destination code.
     */
    public java.lang.String getDestinationCode() {
        return destinationCode;
    }


    /**
     * Sets the destinationCode value for this GetDefaultTaxCodeResponse.
     *
     * @param destinationCode   * An intercompany
     *                                 destination code.
     */
    public void setDestinationCode(java.lang.String destinationCode) {
        this.destinationCode = destinationCode;
    }


    /**
     * Gets the accountCode value for this GetDefaultTaxCodeResponse.
     *
     * @return accountCode   * An account
     *                             code.
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this GetDefaultTaxCodeResponse.
     *
     * @param accountCode   * An account
     *                             code.
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the taxCode value for this GetDefaultTaxCodeResponse.
     *
     * @return taxCode   * A tax
     *                             code.
     */
    public java.lang.String getTaxCode() {
        return taxCode;
    }


    /**
     * Sets the taxCode value for this GetDefaultTaxCodeResponse.
     *
     * @param taxCode   * A tax
     *                             code.
     */
    public void setTaxCode(java.lang.String taxCode) {
        this.taxCode = taxCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDefaultTaxCodeResponse)) return false;
        GetDefaultTaxCodeResponse other = (GetDefaultTaxCodeResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.companyCode==null && other.getCompanyCode()==null) ||
             (this.companyCode!=null &&
              this.companyCode.equals(other.getCompanyCode()))) &&
            ((this.destinationCode==null && other.getDestinationCode()==null) ||
             (this.destinationCode!=null &&
              this.destinationCode.equals(other.getDestinationCode()))) &&
            ((this.accountCode==null && other.getAccountCode()==null) ||
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode()))) &&
            ((this.taxCode==null && other.getTaxCode()==null) ||
             (this.taxCode!=null &&
              this.taxCode.equals(other.getTaxCode())));
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
        if (getCompanyCode() != null) {
            _hashCode += getCompanyCode().hashCode();
        }
        if (getDestinationCode() != null) {
            _hashCode += getDestinationCode().hashCode();
        }
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        if (getTaxCode() != null) {
            _hashCode += getTaxCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDefaultTaxCodeResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "GetDefaultTaxCodeResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("companyCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CompanyCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinationCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DestinationCode"));
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
        elemField.setFieldName("taxCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "TaxCode"));
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
