/**
 * DocNumListItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element contains information
 *                 about one number range.
 */
public class DocNumListItem  implements java.io.Serializable {
    private boolean open;

    /* The first number in the
     *                     range. */
    private java.lang.String first;

    /* The next available
     *                     number. */
    private java.lang.String next;

    /* The
     *                         last number in the range, which must be greater
     * than the first number. */
    private java.lang.String last;

    /* The
     *                         year to which the number range
     *                     applies. */
    private java.lang.String year;

    /* The
     *                         first period in the range to which the number
     * range applies. */
    private short startPeriod;

    /* The
     *                         last period in the range to which the number
     * range applies. */
    private short endPeriod;

    public DocNumListItem() {
    }

    public DocNumListItem(
           boolean open,
           java.lang.String first,
           java.lang.String next,
           java.lang.String last,
           java.lang.String year,
           short startPeriod,
           short endPeriod) {
           this.open = open;
           this.first = first;
           this.next = next;
           this.last = last;
           this.year = year;
           this.startPeriod = startPeriod;
           this.endPeriod = endPeriod;
    }


    /**
     * Gets the open value for this DocNumListItem.
     * 
     * @return open
     */
    public boolean isOpen() {
        return open;
    }


    /**
     * Sets the open value for this DocNumListItem.
     * 
     * @param open
     */
    public void setOpen(boolean open) {
        this.open = open;
    }


    /**
     * Gets the first value for this DocNumListItem.
     * 
     * @return first   * The first number in the
     *                     range.
     */
    public java.lang.String getFirst() {
        return first;
    }


    /**
     * Sets the first value for this DocNumListItem.
     * 
     * @param first   * The first number in the
     *                     range.
     */
    public void setFirst(java.lang.String first) {
        this.first = first;
    }


    /**
     * Gets the next value for this DocNumListItem.
     * 
     * @return next   * The next available
     *                     number.
     */
    public java.lang.String getNext() {
        return next;
    }


    /**
     * Sets the next value for this DocNumListItem.
     * 
     * @param next   * The next available
     *                     number.
     */
    public void setNext(java.lang.String next) {
        this.next = next;
    }


    /**
     * Gets the last value for this DocNumListItem.
     * 
     * @return last   * The
     *                         last number in the range, which must be greater
     * than the first number.
     */
    public java.lang.String getLast() {
        return last;
    }


    /**
     * Sets the last value for this DocNumListItem.
     * 
     * @param last   * The
     *                         last number in the range, which must be greater
     * than the first number.
     */
    public void setLast(java.lang.String last) {
        this.last = last;
    }


    /**
     * Gets the year value for this DocNumListItem.
     * 
     * @return year   * The
     *                         year to which the number range
     *                     applies.
     */
    public java.lang.String getYear() {
        return year;
    }


    /**
     * Sets the year value for this DocNumListItem.
     * 
     * @param year   * The
     *                         year to which the number range
     *                     applies.
     */
    public void setYear(java.lang.String year) {
        this.year = year;
    }


    /**
     * Gets the startPeriod value for this DocNumListItem.
     * 
     * @return startPeriod   * The
     *                         first period in the range to which the number
     * range applies.
     */
    public short getStartPeriod() {
        return startPeriod;
    }


    /**
     * Sets the startPeriod value for this DocNumListItem.
     * 
     * @param startPeriod   * The
     *                         first period in the range to which the number
     * range applies.
     */
    public void setStartPeriod(short startPeriod) {
        this.startPeriod = startPeriod;
    }


    /**
     * Gets the endPeriod value for this DocNumListItem.
     * 
     * @return endPeriod   * The
     *                         last period in the range to which the number
     * range applies.
     */
    public short getEndPeriod() {
        return endPeriod;
    }


    /**
     * Sets the endPeriod value for this DocNumListItem.
     * 
     * @param endPeriod   * The
     *                         last period in the range to which the number
     * range applies.
     */
    public void setEndPeriod(short endPeriod) {
        this.endPeriod = endPeriod;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocNumListItem)) return false;
        DocNumListItem other = (DocNumListItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.open == other.isOpen() &&
            ((this.first==null && other.getFirst()==null) || 
             (this.first!=null &&
              this.first.equals(other.getFirst()))) &&
            ((this.next==null && other.getNext()==null) || 
             (this.next!=null &&
              this.next.equals(other.getNext()))) &&
            ((this.last==null && other.getLast()==null) || 
             (this.last!=null &&
              this.last.equals(other.getLast()))) &&
            ((this.year==null && other.getYear()==null) || 
             (this.year!=null &&
              this.year.equals(other.getYear()))) &&
            this.startPeriod == other.getStartPeriod() &&
            this.endPeriod == other.getEndPeriod();
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
        _hashCode += (isOpen() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFirst() != null) {
            _hashCode += getFirst().hashCode();
        }
        if (getNext() != null) {
            _hashCode += getNext().hashCode();
        }
        if (getLast() != null) {
            _hashCode += getLast().hashCode();
        }
        if (getYear() != null) {
            _hashCode += getYear().hashCode();
        }
        _hashCode += getStartPeriod();
        _hashCode += getEndPeriod();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocNumListItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "DocNumListItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("open");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Open"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("first");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "First"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("next");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Next"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("last");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Last"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("year");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Year"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYear"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("startPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "StartPeriod"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("endPeriod");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "EndPeriod"));
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
