/**
 * MissingQuantityInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details of a quantity
 *                 missing from the problem document
 *             line.
 */
public class MissingQuantityInfo  implements java.io.Serializable {
    private short level;

    /* The
     *                         quantity, at the specified element level,
     * that
     *                         the data relates to. */
    private short position;

    /* The
     *                         code of the element that requires this
     *                     quantity. */
    private java.lang.String elmCode;

    /* The
     *                         short name of the element that requires this
     * quantity. */
    private java.lang.String elmSName;

    /* The
     *                         title for this quantity. */
    private java.lang.String qtyTitle;

    /* The
     *                         number of decimal places for this
     *                     quantity. */
    private short decimalPlaces;

    public MissingQuantityInfo() {
    }

    public MissingQuantityInfo(
           short level,
           short position,
           java.lang.String elmCode,
           java.lang.String elmSName,
           java.lang.String qtyTitle,
           short decimalPlaces) {
           this.level = level;
           this.position = position;
           this.elmCode = elmCode;
           this.elmSName = elmSName;
           this.qtyTitle = qtyTitle;
           this.decimalPlaces = decimalPlaces;
    }


    /**
     * Gets the level value for this MissingQuantityInfo.
     * 
     * @return level
     */
    public short getLevel() {
        return level;
    }


    /**
     * Sets the level value for this MissingQuantityInfo.
     * 
     * @param level
     */
    public void setLevel(short level) {
        this.level = level;
    }


    /**
     * Gets the position value for this MissingQuantityInfo.
     * 
     * @return position   * The
     *                         quantity, at the specified element level,
     * that
     *                         the data relates to.
     */
    public short getPosition() {
        return position;
    }


    /**
     * Sets the position value for this MissingQuantityInfo.
     * 
     * @param position   * The
     *                         quantity, at the specified element level,
     * that
     *                         the data relates to.
     */
    public void setPosition(short position) {
        this.position = position;
    }


    /**
     * Gets the elmCode value for this MissingQuantityInfo.
     * 
     * @return elmCode   * The
     *                         code of the element that requires this
     *                     quantity.
     */
    public java.lang.String getElmCode() {
        return elmCode;
    }


    /**
     * Sets the elmCode value for this MissingQuantityInfo.
     * 
     * @param elmCode   * The
     *                         code of the element that requires this
     *                     quantity.
     */
    public void setElmCode(java.lang.String elmCode) {
        this.elmCode = elmCode;
    }


    /**
     * Gets the elmSName value for this MissingQuantityInfo.
     * 
     * @return elmSName   * The
     *                         short name of the element that requires this
     * quantity.
     */
    public java.lang.String getElmSName() {
        return elmSName;
    }


    /**
     * Sets the elmSName value for this MissingQuantityInfo.
     * 
     * @param elmSName   * The
     *                         short name of the element that requires this
     * quantity.
     */
    public void setElmSName(java.lang.String elmSName) {
        this.elmSName = elmSName;
    }


    /**
     * Gets the qtyTitle value for this MissingQuantityInfo.
     * 
     * @return qtyTitle   * The
     *                         title for this quantity.
     */
    public java.lang.String getQtyTitle() {
        return qtyTitle;
    }


    /**
     * Sets the qtyTitle value for this MissingQuantityInfo.
     * 
     * @param qtyTitle   * The
     *                         title for this quantity.
     */
    public void setQtyTitle(java.lang.String qtyTitle) {
        this.qtyTitle = qtyTitle;
    }


    /**
     * Gets the decimalPlaces value for this MissingQuantityInfo.
     * 
     * @return decimalPlaces   * The
     *                         number of decimal places for this
     *                     quantity.
     */
    public short getDecimalPlaces() {
        return decimalPlaces;
    }


    /**
     * Sets the decimalPlaces value for this MissingQuantityInfo.
     * 
     * @param decimalPlaces   * The
     *                         number of decimal places for this
     *                     quantity.
     */
    public void setDecimalPlaces(short decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof MissingQuantityInfo)) return false;
        MissingQuantityInfo other = (MissingQuantityInfo) obj;
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
            ((this.elmCode==null && other.getElmCode()==null) || 
             (this.elmCode!=null &&
              this.elmCode.equals(other.getElmCode()))) &&
            ((this.elmSName==null && other.getElmSName()==null) || 
             (this.elmSName!=null &&
              this.elmSName.equals(other.getElmSName()))) &&
            ((this.qtyTitle==null && other.getQtyTitle()==null) || 
             (this.qtyTitle!=null &&
              this.qtyTitle.equals(other.getQtyTitle()))) &&
            this.decimalPlaces == other.getDecimalPlaces();
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
        if (getElmCode() != null) {
            _hashCode += getElmCode().hashCode();
        }
        if (getElmSName() != null) {
            _hashCode += getElmSName().hashCode();
        }
        if (getQtyTitle() != null) {
            _hashCode += getQtyTitle().hashCode();
        }
        _hashCode += getDecimalPlaces();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(MissingQuantityInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQuantityInfo"));
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
        elemField.setFieldName("elmCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmSName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmSName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("qtyTitle");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "QtyTitle"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decimalPlaces");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DecimalPlaces"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
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
