/**
 * SelectorType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selectormaster;


/**
 * This element contains information
 *                 which determines the selector type.
 */
public class SelectorType  implements java.io.Serializable {
    private java.lang.String dataClass;

    /* Data
     *                         located on the same tables as the selected
     * data
     *                         class will be retrieved. */
    private java.lang.Boolean self;

    /* Data located in the Books
     *                         will be retrieved. */
    private java.lang.Boolean books;

    /* Documents located on the
     *                         Financials Intray will be
     *                     retrieved. */
    private java.lang.Boolean intray;

    /* Documents located in the
     *                         internal archive tables will be
     *                     retrieved. */
    private java.lang.Boolean archive;

    public SelectorType() {
    }

    public SelectorType(
           java.lang.String dataClass,
           java.lang.Boolean self,
           java.lang.Boolean books,
           java.lang.Boolean intray,
           java.lang.Boolean archive) {
           this.dataClass = dataClass;
           this.self = self;
           this.books = books;
           this.intray = intray;
           this.archive = archive;
    }


    /**
     * Gets the dataClass value for this SelectorType.
     * 
     * @return dataClass
     */
    public java.lang.String getDataClass() {
        return dataClass;
    }


    /**
     * Sets the dataClass value for this SelectorType.
     * 
     * @param dataClass
     */
    public void setDataClass(java.lang.String dataClass) {
        this.dataClass = dataClass;
    }


    /**
     * Gets the self value for this SelectorType.
     * 
     * @return self   * Data
     *                         located on the same tables as the selected
     * data
     *                         class will be retrieved.
     */
    public java.lang.Boolean getSelf() {
        return self;
    }


    /**
     * Sets the self value for this SelectorType.
     * 
     * @param self   * Data
     *                         located on the same tables as the selected
     * data
     *                         class will be retrieved.
     */
    public void setSelf(java.lang.Boolean self) {
        this.self = self;
    }


    /**
     * Gets the books value for this SelectorType.
     * 
     * @return books   * Data located in the Books
     *                         will be retrieved.
     */
    public java.lang.Boolean getBooks() {
        return books;
    }


    /**
     * Sets the books value for this SelectorType.
     * 
     * @param books   * Data located in the Books
     *                         will be retrieved.
     */
    public void setBooks(java.lang.Boolean books) {
        this.books = books;
    }


    /**
     * Gets the intray value for this SelectorType.
     * 
     * @return intray   * Documents located on the
     *                         Financials Intray will be
     *                     retrieved.
     */
    public java.lang.Boolean getIntray() {
        return intray;
    }


    /**
     * Sets the intray value for this SelectorType.
     * 
     * @param intray   * Documents located on the
     *                         Financials Intray will be
     *                     retrieved.
     */
    public void setIntray(java.lang.Boolean intray) {
        this.intray = intray;
    }


    /**
     * Gets the archive value for this SelectorType.
     * 
     * @return archive   * Documents located in the
     *                         internal archive tables will be
     *                     retrieved.
     */
    public java.lang.Boolean getArchive() {
        return archive;
    }


    /**
     * Sets the archive value for this SelectorType.
     * 
     * @param archive   * Documents located in the
     *                         internal archive tables will be
     *                     retrieved.
     */
    public void setArchive(java.lang.Boolean archive) {
        this.archive = archive;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SelectorType)) return false;
        SelectorType other = (SelectorType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataClass==null && other.getDataClass()==null) || 
             (this.dataClass!=null &&
              this.dataClass.equals(other.getDataClass()))) &&
            ((this.self==null && other.getSelf()==null) || 
             (this.self!=null &&
              this.self.equals(other.getSelf()))) &&
            ((this.books==null && other.getBooks()==null) || 
             (this.books!=null &&
              this.books.equals(other.getBooks()))) &&
            ((this.intray==null && other.getIntray()==null) || 
             (this.intray!=null &&
              this.intray.equals(other.getIntray()))) &&
            ((this.archive==null && other.getArchive()==null) || 
             (this.archive!=null &&
              this.archive.equals(other.getArchive())));
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
        if (getDataClass() != null) {
            _hashCode += getDataClass().hashCode();
        }
        if (getSelf() != null) {
            _hashCode += getSelf().hashCode();
        }
        if (getBooks() != null) {
            _hashCode += getBooks().hashCode();
        }
        if (getIntray() != null) {
            _hashCode += getIntray().hashCode();
        }
        if (getArchive() != null) {
            _hashCode += getArchive().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SelectorType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "SelectorType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataClass");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "DataClass"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("self");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Self"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("books");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Books"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intray");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Intray"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("archive");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "Archive"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
