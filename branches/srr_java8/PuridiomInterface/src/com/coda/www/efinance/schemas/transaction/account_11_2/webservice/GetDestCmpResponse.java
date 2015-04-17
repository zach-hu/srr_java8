/**
 * GetDestCmpResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class GetDestCmpResponse  implements java.io.Serializable {
    /* The company code retrieved
     *                             from the database. */
    private java.lang.String company;

    /* The InterCompany destination
     *                             code retrieved from the database. */
    private java.lang.String destination;

    public GetDestCmpResponse() {
    }

    public GetDestCmpResponse(
           java.lang.String company,
           java.lang.String destination) {
           this.company = company;
           this.destination = destination;
    }


    /**
     * Gets the company value for this GetDestCmpResponse.
     * 
     * @return company   * The company code retrieved
     *                             from the database.
     */
    public java.lang.String getCompany() {
        return company;
    }


    /**
     * Sets the company value for this GetDestCmpResponse.
     * 
     * @param company   * The company code retrieved
     *                             from the database.
     */
    public void setCompany(java.lang.String company) {
        this.company = company;
    }


    /**
     * Gets the destination value for this GetDestCmpResponse.
     * 
     * @return destination   * The InterCompany destination
     *                             code retrieved from the database.
     */
    public java.lang.String getDestination() {
        return destination;
    }


    /**
     * Sets the destination value for this GetDestCmpResponse.
     * 
     * @param destination   * The InterCompany destination
     *                             code retrieved from the database.
     */
    public void setDestination(java.lang.String destination) {
        this.destination = destination;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetDestCmpResponse)) return false;
        GetDestCmpResponse other = (GetDestCmpResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.company==null && other.getCompany()==null) || 
             (this.company!=null &&
              this.company.equals(other.getCompany()))) &&
            ((this.destination==null && other.getDestination()==null) || 
             (this.destination!=null &&
              this.destination.equals(other.getDestination())));
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
        if (getCompany() != null) {
            _hashCode += getCompany().hashCode();
        }
        if (getDestination() != null) {
            _hashCode += getDestination().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetDestCmpResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetDestCmpResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("company");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Company"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destination");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "Destination"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
