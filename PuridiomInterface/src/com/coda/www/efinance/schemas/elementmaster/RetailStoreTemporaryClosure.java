/**
 * RetailStoreTemporaryClosure.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.elementmaster;


/**
 * The details of a store closure
 *             period.
 */
public class RetailStoreTemporaryClosure  implements java.io.Serializable {
    private java.util.Calendar fromDate;

    /* The
     *                         final date the store is
     *                     closed. */
    private java.util.Calendar toDate;

    /* The
     *                         reason for the closure. */
    private java.lang.String storeCloseReason;

    public RetailStoreTemporaryClosure() {
    }

    public RetailStoreTemporaryClosure(
           java.util.Calendar fromDate,
           java.util.Calendar toDate,
           java.lang.String storeCloseReason) {
           this.fromDate = fromDate;
           this.toDate = toDate;
           this.storeCloseReason = storeCloseReason;
    }


    /**
     * Gets the fromDate value for this RetailStoreTemporaryClosure.
     * 
     * @return fromDate
     */
    public java.util.Calendar getFromDate() {
        return fromDate;
    }


    /**
     * Sets the fromDate value for this RetailStoreTemporaryClosure.
     * 
     * @param fromDate
     */
    public void setFromDate(java.util.Calendar fromDate) {
        this.fromDate = fromDate;
    }


    /**
     * Gets the toDate value for this RetailStoreTemporaryClosure.
     * 
     * @return toDate   * The
     *                         final date the store is
     *                     closed.
     */
    public java.util.Calendar getToDate() {
        return toDate;
    }


    /**
     * Sets the toDate value for this RetailStoreTemporaryClosure.
     * 
     * @param toDate   * The
     *                         final date the store is
     *                     closed.
     */
    public void setToDate(java.util.Calendar toDate) {
        this.toDate = toDate;
    }


    /**
     * Gets the storeCloseReason value for this RetailStoreTemporaryClosure.
     * 
     * @return storeCloseReason   * The
     *                         reason for the closure.
     */
    public java.lang.String getStoreCloseReason() {
        return storeCloseReason;
    }


    /**
     * Sets the storeCloseReason value for this RetailStoreTemporaryClosure.
     * 
     * @param storeCloseReason   * The
     *                         reason for the closure.
     */
    public void setStoreCloseReason(java.lang.String storeCloseReason) {
        this.storeCloseReason = storeCloseReason;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RetailStoreTemporaryClosure)) return false;
        RetailStoreTemporaryClosure other = (RetailStoreTemporaryClosure) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fromDate==null && other.getFromDate()==null) || 
             (this.fromDate!=null &&
              this.fromDate.equals(other.getFromDate()))) &&
            ((this.toDate==null && other.getToDate()==null) || 
             (this.toDate!=null &&
              this.toDate.equals(other.getToDate()))) &&
            ((this.storeCloseReason==null && other.getStoreCloseReason()==null) || 
             (this.storeCloseReason!=null &&
              this.storeCloseReason.equals(other.getStoreCloseReason())));
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
        if (getFromDate() != null) {
            _hashCode += getFromDate().hashCode();
        }
        if (getToDate() != null) {
            _hashCode += getToDate().hashCode();
        }
        if (getStoreCloseReason() != null) {
            _hashCode += getStoreCloseReason().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RetailStoreTemporaryClosure.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "RetailStoreTemporaryClosure"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fromDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "FromDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "ToDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("storeCloseReason");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/elementmaster", "StoreCloseReason"));
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
