/**
 * ElmFullKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * This is an element key that
 *                 incorporates the company within which the element
 *             exists.
 */
public class ElmFullKey  implements java.io.Serializable {
    private java.lang.String cmpCode;

    /* The level at which this
     *                         element is defined. A number from 1 to 8
     *                     inclusive. */
    private short level;

    /* The
     *                         element code. */
    private java.lang.String code;

    public ElmFullKey() {
    }

    public ElmFullKey(
           java.lang.String cmpCode,
           short level,
           java.lang.String code) {
           this.cmpCode = cmpCode;
           this.level = level;
           this.code = code;
    }


    /**
     * Gets the cmpCode value for this ElmFullKey.
     * 
     * @return cmpCode
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this ElmFullKey.
     * 
     * @param cmpCode
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the level value for this ElmFullKey.
     * 
     * @return level   * The level at which this
     *                         element is defined. A number from 1 to 8
     *                     inclusive.
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ElmFullKey.
     * 
     * @param level   * The level at which this
     *                         element is defined. A number from 1 to 8
     *                     inclusive.
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the code value for this ElmFullKey.
     * 
     * @return code   * The
     *                         element code.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this ElmFullKey.
     * 
     * @param code   * The
     *                         element code.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ElmFullKey)) return false;
        ElmFullKey other = (ElmFullKey) obj;
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
            this.level == other.getLevel() &&
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
        _hashCode += getLevel();
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ElmFullKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "elmFullKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "CmpCode"));
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
