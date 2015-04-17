/**
 * ExtraQuantityInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details of quantities on the
 *                 problem document line.
 */
public class ExtraQuantityInfo  implements java.io.Serializable {
    private short level;

    /* The quantity, at the
     *                         specified element level, that the data relates
     * to. */
    private short position;

    /* The amount of the specified
     *                     quantity. */
    private java.math.BigDecimal value;

    public ExtraQuantityInfo() {
    }

    public ExtraQuantityInfo(
           short level,
           short position,
           java.math.BigDecimal value) {
           this.level = level;
           this.position = position;
           this.value = value;
    }


    /**
     * Gets the level value for this ExtraQuantityInfo.
     * 
     * @return level
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this ExtraQuantityInfo.
     * 
     * @param level
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the position value for this ExtraQuantityInfo.
     * 
     * @return position   * The quantity, at the
     *                         specified element level, that the data relates
     * to.
     */
    public short getPosition() {
        return position;
    }


    /**
     * Sets the position value for this ExtraQuantityInfo.
     * 
     * @param position   * The quantity, at the
     *                         specified element level, that the data relates
     * to.
     */
    public void setPosition(short position) {
        this.position = position;
    }


    /**
     * Gets the value value for this ExtraQuantityInfo.
     * 
     * @return value   * The amount of the specified
     *                     quantity.
     */
    public java.math.BigDecimal getValue() {
        return value;
    }


    /**
     * Sets the value value for this ExtraQuantityInfo.
     * 
     * @param value   * The amount of the specified
     *                     quantity.
     */
    public void setValue(java.math.BigDecimal value) {
        this.value = value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtraQuantityInfo)) return false;
        ExtraQuantityInfo other = (ExtraQuantityInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.level == other.getLevel() &&
            this.position == other.getPosition() &&
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue())));
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
        _hashCode += getLevel();
        _hashCode += getPosition();
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtraQuantityInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQuantityInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("level");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Level"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("position");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Position"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
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
