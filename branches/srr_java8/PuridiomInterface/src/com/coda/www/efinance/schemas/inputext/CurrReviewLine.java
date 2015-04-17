/**
 * CurrReviewLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains a list of the
 *                 currency details that apply to a line in the
 *             document.
 */
public class CurrReviewLine  implements java.io.Serializable {
    private java.lang.String accountCode;

    /* This
     *                         element contains a list of the currencies
     * used
     *                         on this document line. */
    private com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] currencies;

    public CurrReviewLine() {
    }

    public CurrReviewLine(
           java.lang.String accountCode,
           com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] currencies) {
           this.accountCode = accountCode;
           this.currencies = currencies;
    }


    /**
     * Gets the accountCode value for this CurrReviewLine.
     * 
     * @return accountCode
     */
    public java.lang.String getAccountCode() {
        return accountCode;
    }


    /**
     * Sets the accountCode value for this CurrReviewLine.
     * 
     * @param accountCode
     */
    public void setAccountCode(java.lang.String accountCode) {
        this.accountCode = accountCode;
    }


    /**
     * Gets the currencies value for this CurrReviewLine.
     * 
     * @return currencies   * This
     *                         element contains a list of the currencies
     * used
     *                         on this document line.
     */
    public com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] getCurrencies() {
        return currencies;
    }


    /**
     * Sets the currencies value for this CurrReviewLine.
     * 
     * @param currencies   * This
     *                         element contains a list of the currencies
     * used
     *                         on this document line.
     */
    public void setCurrencies(com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] currencies) {
        this.currencies = currencies;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CurrReviewLine)) return false;
        CurrReviewLine other = (CurrReviewLine) obj;
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
            ((this.currencies==null && other.getCurrencies()==null) || 
             (this.currencies!=null &&
              java.util.Arrays.equals(this.currencies, other.getCurrencies())));
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CurrReviewLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "AccountCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currencies");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Currencies"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValue"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Currency"));
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
