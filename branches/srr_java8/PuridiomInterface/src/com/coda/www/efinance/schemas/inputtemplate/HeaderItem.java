/**
 * HeaderItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This element holds details of one
 *                 document header field.
 */
public class HeaderItem  extends com.coda.www.efinance.schemas.inputtemplate.VocabEntity  implements java.io.Serializable {
    private java.lang.String value;

    /* Specifies that a
     *                                 value must be provided for this field
     * during Input. This value can either be
     *                                 entered by the user or the fixed,
     *                                 default value for the
     *                             field. */
    private boolean mandatory;

    /* Identifies the
     *                                 position of this field in the sequence
     * of fields on the document
     *                             header. */
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

    public HeaderItem() {
    }

    public HeaderItem(
           short vocabID,
           short index,
           java.lang.String caption,
           java.lang.String value,
           boolean mandatory,
           short lstSeqNo,
           boolean captionHidden,
           boolean valueHidden) {
        super(
            vocabID,
            index,
            caption);
        this.value = value;
        this.mandatory = mandatory;
        this.lstSeqNo = lstSeqNo;
        this.captionHidden = captionHidden;
        this.valueHidden = valueHidden;
    }


    /**
     * Gets the value value for this HeaderItem.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this HeaderItem.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the mandatory value for this HeaderItem.
     * 
     * @return mandatory   * Specifies that a
     *                                 value must be provided for this field
     * during Input. This value can either be
     *                                 entered by the user or the fixed,
     *                                 default value for the
     *                             field.
     */
    public boolean isMandatory() {
        return mandatory;
    }


    /**
     * Sets the mandatory value for this HeaderItem.
     * 
     * @param mandatory   * Specifies that a
     *                                 value must be provided for this field
     * during Input. This value can either be
     *                                 entered by the user or the fixed,
     *                                 default value for the
     *                             field.
     */
    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }


    /**
     * Gets the lstSeqNo value for this HeaderItem.
     * 
     * @return lstSeqNo   * Identifies the
     *                                 position of this field in the sequence
     * of fields on the document
     *                             header.
     */
    public short getLstSeqNo() {
        return lstSeqNo;
    }


    /**
     * Sets the lstSeqNo value for this HeaderItem.
     * 
     * @param lstSeqNo   * Identifies the
     *                                 position of this field in the sequence
     * of fields on the document
     *                             header.
     */
    public void setLstSeqNo(short lstSeqNo) {
        this.lstSeqNo = lstSeqNo;
    }


    /**
     * Gets the captionHidden value for this HeaderItem.
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
     * Sets the captionHidden value for this HeaderItem.
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
     * Gets the valueHidden value for this HeaderItem.
     * 
     * @return valueHidden   * Specifies whether
     *                                 this field is displayed during
     *                             Input.
     */
    public boolean isValueHidden() {
        return valueHidden;
    }


    /**
     * Sets the valueHidden value for this HeaderItem.
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
        if (!(obj instanceof HeaderItem)) return false;
        HeaderItem other = (HeaderItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.value==null && other.getValue()==null) || 
             (this.value!=null &&
              this.value.equals(other.getValue()))) &&
            this.mandatory == other.isMandatory() &&
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
        if (getValue() != null) {
            _hashCode += getValue().hashCode();
        }
        _hashCode += (isMandatory() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += getLstSeqNo();
        _hashCode += (isCaptionHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isValueHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HeaderItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "HeaderItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("value");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Value"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mandatory");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Mandatory"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
