/**
 * BalancingElements.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;

public class BalancingElements  implements java.io.Serializable {
    /* Specifies the action taken
     *                         when a document generated from this master
     * does
     *                         not balance as required. */
    private java.lang.String rule;

    /* The account to which
     *                         balancing entries are
     *                     posted. */
    private java.lang.String accounts;

    public BalancingElements() {
    }

    public BalancingElements(
           java.lang.String rule,
           java.lang.String accounts) {
           this.rule = rule;
           this.accounts = accounts;
    }


    /**
     * Gets the rule value for this BalancingElements.
     * 
     * @return rule   * Specifies the action taken
     *                         when a document generated from this master
     * does
     *                         not balance as required.
     */
    public java.lang.String getRule() {
        return rule;
    }


    /**
     * Sets the rule value for this BalancingElements.
     * 
     * @param rule   * Specifies the action taken
     *                         when a document generated from this master
     * does
     *                         not balance as required.
     */
    public void setRule(java.lang.String rule) {
        this.rule = rule;
    }


    /**
     * Gets the accounts value for this BalancingElements.
     * 
     * @return accounts   * The account to which
     *                         balancing entries are
     *                     posted.
     */
    public java.lang.String getAccounts() {
        return accounts;
    }


    /**
     * Sets the accounts value for this BalancingElements.
     * 
     * @param accounts   * The account to which
     *                         balancing entries are
     *                     posted.
     */
    public void setAccounts(java.lang.String accounts) {
        this.accounts = accounts;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalancingElements)) return false;
        BalancingElements other = (BalancingElements) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rule==null && other.getRule()==null) || 
             (this.rule!=null &&
              this.rule.equals(other.getRule()))) &&
            ((this.accounts==null && other.getAccounts()==null) || 
             (this.accounts!=null &&
              this.accounts.equals(other.getAccounts())));
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
        if (getRule() != null) {
            _hashCode += getRule().hashCode();
        }
        if (getAccounts() != null) {
            _hashCode += getAccounts().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalancingElements.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "BalancingElements"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Rule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Accounts"));
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
