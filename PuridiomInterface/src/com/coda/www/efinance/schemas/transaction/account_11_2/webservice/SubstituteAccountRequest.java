/**
 * SubstituteAccountRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class SubstituteAccountRequest  implements java.io.Serializable {
    /* This element contains details
     *                             of substitute account parameters. */
    private com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount;

    public SubstituteAccountRequest() {
    }

    public SubstituteAccountRequest(
           com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount) {
           this.substituteAccount = substituteAccount;
    }


    /**
     * Gets the substituteAccount value for this SubstituteAccountRequest.
     * 
     * @return substituteAccount   * This element contains details
     *                             of substitute account parameters.
     */
    public com.coda.www.efinance.schemas.transaction.SubstituteAccountParams getSubstituteAccount() {
        return substituteAccount;
    }


    /**
     * Sets the substituteAccount value for this SubstituteAccountRequest.
     * 
     * @param substituteAccount   * This element contains details
     *                             of substitute account parameters.
     */
    public void setSubstituteAccount(com.coda.www.efinance.schemas.transaction.SubstituteAccountParams substituteAccount) {
        this.substituteAccount = substituteAccount;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SubstituteAccountRequest)) return false;
        SubstituteAccountRequest other = (SubstituteAccountRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.substituteAccount==null && other.getSubstituteAccount()==null) || 
             (this.substituteAccount!=null &&
              this.substituteAccount.equals(other.getSubstituteAccount())));
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
        if (getSubstituteAccount() != null) {
            _hashCode += getSubstituteAccount().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SubstituteAccountRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">SubstituteAccountRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("substituteAccount");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "SubstituteAccount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "SubstituteAccountParams"));
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
