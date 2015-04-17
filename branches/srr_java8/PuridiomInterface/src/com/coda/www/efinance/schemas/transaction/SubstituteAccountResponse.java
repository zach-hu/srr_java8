/**
 * SubstituteAccountResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;

public class SubstituteAccountResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* This element contains
     *                                 details of substitute account
     *                             parameters. */
    private com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount;

    /* An account
     *                             code. */
    private java.lang.String accountCode;

    public SubstituteAccountResponse() {
    }

    public SubstituteAccountResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount,
           java.lang.String accountCode) {
        super();
        this.substituteAccount = substituteAccount;
        this.accountCode = accountCode;
    }


    /**
     * Gets the substituteAccount value for this SubstituteAccountResponse.
     *
     * @return substituteAccount   * This element contains
     *                                 details of substitute account
     *                             parameters.
     */
    public com.coda.www.efinance.schemas.transaction.SubstituteAccountParams getSubstituteAccount() {
        return substituteAccount;
    }


    /**
     * Sets the substituteAccount value for this SubstituteAccountResponse.
     *
     * @param substituteAccount   * This element contains
     *                                 details of substitute account
     *                             parameters.
     */
    public void setSubstituteAccount(com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount) {
        this.substituteAccount = substituteAccount;
    }


    /**
     * Gets the accountCode value for this SubstituteAccountResponse.
     *
     * @return accountCode   * An account
     *                             code.
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this SubstituteAccountResponse.
     *
     * @param accountCode   * An account
     *                             code.
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubstituteAccountResponse)) return false;
        SubstituteAccountResponse other = (SubstituteAccountResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.substituteAccount==null && other.getSubstituteAccount()==null) ||
             (this.substituteAccount!=null &&
              this.substituteAccount.equals(other.getSubstituteAccount()))) &&
            ((this.accountCode==null && other.getAccountCode()==null) ||
             (this.accountCode!=null &&
              this.accountCode.equals(other.getAccountCode())));
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
        if (getSubstituteAccount() != null) {
            _hashCode += getSubstituteAccount().hashCode();
        }
        if (getAccountCode() != null) {
            _hashCode += getAccountCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubstituteAccountResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("substituteAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountParams"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AccountCode"));
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
