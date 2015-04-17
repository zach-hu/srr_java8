/**
 * TraderKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This element is a key consisting of a
 *                 company code, element code, element level and trader
 * code which uniquely identifies a
 *             trader.
 */
public class TraderKey  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The code for the umbrella
     *                         element within which the trader is
     *                     held. */
    private java.lang.String elmCode;

    /* The level of the umbrella
     *                         element within which the trader is
     *                     held. */
    private short elmLevel;

    /* A code identifying the
     *                     trader. */
    private java.lang.String code;

    public TraderKey() {
    }

    public TraderKey(
           java.lang.String cmpCode,
           java.lang.String elmCode,
           short elmLevel,
           java.lang.String code) {
           this.cmpCode = cmpCode;
           this.elmCode = elmCode;
           this.elmLevel = elmLevel;
           this.code = code;
    }


    /**
     * Gets the cmpCode value for this TraderKey.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this TraderKey.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the elmCode value for this TraderKey.
     * 
     * @return elmCode   * The code for the umbrella
     *                         element within which the trader is
     *                     held.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this TraderKey.
     * 
     * @param elmCode   * The code for the umbrella
     *                         element within which the trader is
     *                     held.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmLevel value for this TraderKey.
     * 
     * @return elmLevel   * The level of the umbrella
     *                         element within which the trader is
     *                     held.
     */
    public short getElmLevel() {
        return elmLevel;
    }


    /**
     * Sets the elmLevel value for this TraderKey.
     * 
     * @param elmLevel   * The level of the umbrella
     *                         element within which the trader is
     *                     held.
     */
    public void setElmLevel(short elmLevel) {
        this.elmLevel = elmLevel;
    }


    /**
     * Gets the code value for this TraderKey.
     * 
     * @return code   * A code identifying the
     *                     trader.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this TraderKey.
     * 
     * @param code   * A code identifying the
     *                     trader.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TraderKey)) return false;
        TraderKey other = (TraderKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.elmCode==null && other.getElmCode()==null) || 
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            this.elmLevel == other.getElmLevel() &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode())));
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
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        _hashCode += getElmLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TraderKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "TraderKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ElmLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
