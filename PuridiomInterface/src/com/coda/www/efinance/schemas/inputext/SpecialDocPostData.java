/**
 * SpecialDocPostData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class SpecialDocPostData  implements java.io.Serializable {
    /* Specifies whether the
     *                         generated document is posted using the currency
     * exchange rates as specified on the original
     *                         document (equal), or the currency exchange
     * rates
     *                         in effect on the reversing document date
     *                     (calculated). */
    private java.lang.String rateRule;

    public SpecialDocPostData() {
    }

    public SpecialDocPostData(
           java.lang.String rateRule) {
           this.rateRule = rateRule;
    }


    /**
     * Gets the rateRule value for this SpecialDocPostData.
     * 
     * @return rateRule   * Specifies whether the
     *                         generated document is posted using the currency
     * exchange rates as specified on the original
     *                         document (equal), or the currency exchange
     * rates
     *                         in effect on the reversing document date
     *                     (calculated).
     */
    public java.lang.String getRateRule() {
        return rateRule;
    }


    /**
     * Sets the rateRule value for this SpecialDocPostData.
     * 
     * @param rateRule   * Specifies whether the
     *                         generated document is posted using the currency
     * exchange rates as specified on the original
     *                         document (equal), or the currency exchange
     * rates
     *                         in effect on the reversing document date
     *                     (calculated).
     */
    public void setRateRule(java.lang.String rateRule) {
        this.rateRule = rateRule;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SpecialDocPostData)) return false;
        SpecialDocPostData other = (SpecialDocPostData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rateRule==null && other.getRateRule()==null) || 
             (this.rateRule!=null &&
              this.rateRule.equals(other.getRateRule())));
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
        if (getRateRule() != null) {
            _hashCode += getRateRule().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SpecialDocPostData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "SpecialDocPostData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rateRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "RateRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
