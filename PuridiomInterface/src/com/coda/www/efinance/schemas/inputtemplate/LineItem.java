/**
 * LineItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This element holds details of one
 *                 field on this defined line.
 */
public class LineItem  extends com.coda.www.efinance.schemas.inputtemplate.VocabEntity  implements java.io.Serializable {
    private java.lang.String value;

    /* Specifies that a
     *                                 value must be provided for this field
     * during Input. This value can either be
     *                                 entered by the user or the fixed,
     *                                 default value for the
     *                             field. */
    private boolean mandatory;

    /* The derivation
     *                                 formula used to calculate the value
     * of
     *                                 this field. If a formula is entered
     * here, the field becomes a derived
     *                             field. */
    private java.lang.String derived;

    /* Identifies the
     *                                 position of this field in the sequence
     * of fields on the defined
     *                             line. */
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

    /* Contains element
     *                                 filters for the account code on this
     * document line. */
    private com.coda.www.efinance.schemas.inputtemplate.ElementFilter[] elementFilters;

    public LineItem() {
    }

    public LineItem(
           short vocabID,
           short index,
           java.lang.String caption,
           java.lang.String value,
           boolean mandatory,
           java.lang.String derived,
           short lstSeqNo,
           boolean captionHidden,
           boolean valueHidden,
           com.coda.www.efinance.schemas.inputtemplate.ElementFilter[] elementFilters) {
        super(
            vocabID,
            index,
            caption);
        this.value = value;
        this.mandatory = mandatory;
        this.derived = derived;
        this.lstSeqNo = lstSeqNo;
        this.captionHidden = captionHidden;
        this.valueHidden = valueHidden;
        this.elementFilters = elementFilters;
    }


    /**
     * Gets the value value for this LineItem.
     * 
     * @return value
     */
    public java.lang.String getValue() {
        return value;
    }


    /**
     * Sets the value value for this LineItem.
     * 
     * @param value
     */
    public void setValue(java.lang.String value) {
        this.value = value;
    }


    /**
     * Gets the mandatory value for this LineItem.
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
     * Sets the mandatory value for this LineItem.
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
     * Gets the derived value for this LineItem.
     * 
     * @return derived   * The derivation
     *                                 formula used to calculate the value
     * of
     *                                 this field. If a formula is entered
     * here, the field becomes a derived
     *                             field.
     */
    public java.lang.String getDerived() {
        return derived;
    }


    /**
     * Sets the derived value for this LineItem.
     * 
     * @param derived   * The derivation
     *                                 formula used to calculate the value
     * of
     *                                 this field. If a formula is entered
     * here, the field becomes a derived
     *                             field.
     */
    public void setDerived(java.lang.String derived) {
        this.derived = derived;
    }


    /**
     * Gets the lstSeqNo value for this LineItem.
     * 
     * @return lstSeqNo   * Identifies the
     *                                 position of this field in the sequence
     * of fields on the defined
     *                             line.
     */
    public short getLstSeqNo() {
        return lstSeqNo;
    }


    /**
     * Sets the lstSeqNo value for this LineItem.
     * 
     * @param lstSeqNo   * Identifies the
     *                                 position of this field in the sequence
     * of fields on the defined
     *                             line.
     */
    public void setLstSeqNo(short lstSeqNo) {
        this.lstSeqNo = lstSeqNo;
    }


    /**
     * Gets the captionHidden value for this LineItem.
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
     * Sets the captionHidden value for this LineItem.
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
     * Gets the valueHidden value for this LineItem.
     * 
     * @return valueHidden   * Specifies whether
     *                                 this field is displayed during
     *                             Input.
     */
    public boolean isValueHidden() {
        return valueHidden;
    }


    /**
     * Sets the valueHidden value for this LineItem.
     * 
     * @param valueHidden   * Specifies whether
     *                                 this field is displayed during
     *                             Input.
     */
    public void setValueHidden(boolean valueHidden) {
        this.valueHidden = valueHidden;
    }


    /**
     * Gets the elementFilters value for this LineItem.
     * 
     * @return elementFilters   * Contains element
     *                                 filters for the account code on this
     * document line.
     */
    public com.coda.www.efinance.schemas.inputtemplate.ElementFilter[] getElementFilters() {
        return elementFilters;
    }


    /**
     * Sets the elementFilters value for this LineItem.
     * 
     * @param elementFilters   * Contains element
     *                                 filters for the account code on this
     * document line.
     */
    public void setElementFilters(com.coda.www.efinance.schemas.inputtemplate.ElementFilter[] elementFilters) {
        this.elementFilters = elementFilters;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LineItem)) return false;
        LineItem other = (LineItem) obj;
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
            ((this.derived==null && other.getDerived()==null) || 
             (this.derived!=null &&
              this.derived.equals(other.getDerived()))) &&
            this.lstSeqNo == other.getLstSeqNo() &&
            this.captionHidden == other.isCaptionHidden() &&
            this.valueHidden == other.isValueHidden() &&
            ((this.elementFilters==null && other.getElementFilters()==null) || 
             (this.elementFilters!=null &&
              java.util.Arrays.equals(this.elementFilters, other.getElementFilters())));
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
        if (getDerived() != null) {
            _hashCode += getDerived().hashCode();
        }
        _hashCode += getLstSeqNo();
        _hashCode += (isCaptionHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isValueHidden() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getElementFilters() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElementFilters());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElementFilters(), i);
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
        new org.apache.axis.description.TypeDesc(LineItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItem"));
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
        elemField.setFieldName("derived");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Derived"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elementFilters");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilters"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ElementFilter"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Filter"));
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
