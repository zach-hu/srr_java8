/**
 * VocabEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;

public class VocabEntity  implements java.io.Serializable {
    /* The
     *                         system name for the field, used for
     *                         identification. Each field can appear only
     * once
     *                         in the header. */
    private short vocabID;

    /* An
     *                         identifier for the field, used to refer to
     * it in
     *                         derivation formulae. */
    private short index;

    /* The
     *                         label for the field, displayed during
     *                     Input. */
    private java.lang.String caption;

    public VocabEntity() {
    }

    public VocabEntity(
           short vocabID,
           short index,
           java.lang.String caption) {
           this.vocabID = vocabID;
           this.index = index;
           this.caption = caption;
    }


    /**
     * Gets the vocabID value for this VocabEntity.
     * 
     * @return vocabID   * The
     *                         system name for the field, used for
     *                         identification. Each field can appear only
     * once
     *                         in the header.
     */
    public short getVocabID() {
        return vocabID;
    }


    /**
     * Sets the vocabID value for this VocabEntity.
     * 
     * @param vocabID   * The
     *                         system name for the field, used for
     *                         identification. Each field can appear only
     * once
     *                         in the header.
     */
    public void setVocabID(short vocabID) {
        this.vocabID = vocabID;
    }


    /**
     * Gets the index value for this VocabEntity.
     * 
     * @return index   * An
     *                         identifier for the field, used to refer to
     * it in
     *                         derivation formulae.
     */
    public short getIndex() {
        return index;
    }


    /**
     * Sets the index value for this VocabEntity.
     * 
     * @param index   * An
     *                         identifier for the field, used to refer to
     * it in
     *                         derivation formulae.
     */
    public void setIndex(short index) {
        this.index = index;
    }


    /**
     * Gets the caption value for this VocabEntity.
     * 
     * @return caption   * The
     *                         label for the field, displayed during
     *                     Input.
     */
    public java.lang.String getCaption() {
        return caption;
    }


    /**
     * Sets the caption value for this VocabEntity.
     * 
     * @param caption   * The
     *                         label for the field, displayed during
     *                     Input.
     */
    public void setCaption(java.lang.String caption) {
        this.caption = caption;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VocabEntity)) return false;
        VocabEntity other = (VocabEntity) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.vocabID == other.getVocabID() &&
            this.index == other.getIndex() &&
            ((this.caption==null && other.getCaption()==null) || 
             (this.caption!=null &&
              this.caption.equals(other.getCaption())));
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
        _hashCode += getVocabID();
        _hashCode += getIndex();
        if (getCaption() != null) {
            _hashCode += getCaption().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VocabEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "VocabEntity"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("vocabID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "VocabID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("index");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Index"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("caption");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Caption"));
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
