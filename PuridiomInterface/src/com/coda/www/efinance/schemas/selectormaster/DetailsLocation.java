/**
 * DetailsLocation.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.selectormaster;


/**
 * This element contains information
 *                 about the source of the data.
 */
public class DetailsLocation  implements java.io.Serializable {
    private java.lang.Boolean books;

    /* Documents located on the
     *                         Financials Intray will be
     *                     retrieved. */
    private java.lang.Boolean intray;

    /* Documents located in the
     *                         internal archive tables will be
     *                     retrieved. */
    private java.lang.Boolean archive;

    public DetailsLocation() {
    }

    public DetailsLocation(
           java.lang.Boolean books,
           java.lang.Boolean intray,
           java.lang.Boolean archive) {
           this.books = books;
           this.intray = intray;
           this.archive = archive;
    }


    /**
     * Gets the books value for this DetailsLocation.
     * 
     * @return books
     */
    public java.lang.Boolean getBooks() {
        return books;
    }


    /**
     * Sets the books value for this DetailsLocation.
     * 
     * @param books
     */
    public void setBooks(java.lang.Boolean books) {
        this.books = books;
    }


    /**
     * Gets the intray value for this DetailsLocation.
     * 
     * @return intray   * Documents located on the
     *                         Financials Intray will be
     *                     retrieved.
     */
    public java.lang.Boolean getIntray() {
        return intray;
    }


    /**
     * Sets the intray value for this DetailsLocation.
     * 
     * @param intray   * Documents located on the
     *                         Financials Intray will be
     *                     retrieved.
     */
    public void setIntray(java.lang.Boolean intray) {
        this.intray = intray;
    }


    /**
     * Gets the archive value for this DetailsLocation.
     * 
     * @return archive   * Documents located in the
     *                         internal archive tables will be
     *                     retrieved.
     */
    public java.lang.Boolean getArchive() {
        return archive;
    }


    /**
     * Sets the archive value for this DetailsLocation.
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
        if (!(obj instanceof DetailsLocation)) return false;
        DetailsLocation other = (DetailsLocation) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
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
        new org.apache.axis.description.TypeDesc(DetailsLocation.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/selectormaster", "DetailsLocation"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
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
