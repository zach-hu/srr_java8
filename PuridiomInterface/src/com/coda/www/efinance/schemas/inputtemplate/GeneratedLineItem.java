/**
 * GeneratedLineItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This element holds details of one
 *                 generated tax line field.
 */
public class GeneratedLineItem  extends com.coda.www.efinance.schemas.inputtemplate.VocabEntity  implements java.io.Serializable {
    private short lstSeqNo;

    /* Specifies whether the
     *                                 caption for this field is displayed
     * during Input. This can only be set when
     *                                 ValueHidden is FALSE. That is, you
     * can
     *                                 only choose whether or not to display
     * the caption when the value itself is
     *                                 displayed. If the value is hidden,
     * by
     *                                 default the caption is also
     *                             hidden. */
    private boolean captionHidden;

    /* Specifies whether
     *                                 this field is displayed during
     *                             Input. */
    private boolean valueHidden;

    public GeneratedLineItem() {
    }

    public GeneratedLineItem(
           short vocabID,
           short index,
           java.lang.String caption,
           short lstSeqNo,
           boolean captionHidden,
           boolean valueHidden) {
        super(
            vocabID,
            index,
            caption);
        this.lstSeqNo = lstSeqNo;
        this.captionHidden = captionHidden;
        this.valueHidden = valueHidden;
    }


    /**
     * Gets the lstSeqNo value for this GeneratedLineItem.
     * 
     * @return lstSeqNo
     */
    public short getLstSeqNo() {
        return lstSeqNo;
    }


    /**
     * Sets the lstSeqNo value for this GeneratedLineItem.
     * 
     * @param lstSeqNo
     */
    public void setLstSeqNo(short lstSeqNo) {
        this.lstSeqNo = lstSeqNo;
    }


    /**
     * Gets the captionHidden value for this GeneratedLineItem.
     * 
     * @return captionHidden   * Specifies whether the
     *                                 caption for this field is displayed
     * during Input. This can only be set when
     *                                 ValueHidden is FALSE. That is, you
     * can
     *                                 only choose whether or not to display
     * the caption when the value itself is
     *                                 displayed. If the value is hidden,
     * by
     *                                 default the caption is also
     *                             hidden.
     */
    public boolean isCaptionHidden() {
        return captionHidden;
    }


    /**
     * Sets the captionHidden value for this GeneratedLineItem.
     * 
     * @param captionHidden   * Specifies whether the
     *                                 caption for this field is displayed
     * during Input. This can only be set when
     *                                 ValueHidden is FALSE. That is, you
     * can
     *                                 only choose whether or not to display
     * the caption when the value itself is
     *                                 displayed. If the value is hidden,
     * by
     *                                 default the caption is also
     *                             hidden.
     */
    public void setCaptionHidden(boolean captionHidden) {
        this.captionHidden = captionHidden;
    }


    /**
     * Gets the valueHidden value for this GeneratedLineItem.
     * 
     * @return valueHidden   * Specifies whether
     *                                 this field is displayed during
     *                             Input.
     */
    public boolean isValueHidden() {
        return valueHidden;
    }


    /**
     * Sets the valueHidden value for this GeneratedLineItem.
     * 
     * @param valueHidden   * Specifies whether
     *                                 this field is displayed during
     *                             Input.
     */
    public void setValueHidden(boolean valueHidden) {
        this.valueHidden = valueHidden;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GeneratedLineItem)) return false;
        GeneratedLineItem other = (GeneratedLineItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.lstSeqNo == other.getLstSeqNo() &&
            this.captionHidden == other.isCaptionHidden() &&
            this.valueHidden == other.isValueHidden();
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
        _hashCode += getLstSeqNo();
        _hashCode += (isCaptionHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isValueHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GeneratedLineItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lstSeqNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LstSeqNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("captionHidden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "CaptionHidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valueHidden");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ValueHidden"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
