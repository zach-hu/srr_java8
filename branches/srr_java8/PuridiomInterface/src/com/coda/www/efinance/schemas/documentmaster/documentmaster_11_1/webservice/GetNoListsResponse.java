/**
 * GetNoListsResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster.documentmaster_11_1.webservice;

public class GetNoListsResponse  implements java.io.Serializable {
    /* Contains the number range
     *                             lists for this document master. */
    private com.coda.www.efinance.schemas.documentmaster.Master documentMasterNoLists;

    public GetNoListsResponse() {
    }

    public GetNoListsResponse(
           com.coda.www.efinance.schemas.documentmaster.Master documentMasterNoLists) {
           this.documentMasterNoLists = documentMasterNoLists;
    }


    /**
     * Gets the documentMasterNoLists value for this GetNoListsResponse.
     * 
     * @return documentMasterNoLists   * Contains the number range
     *                             lists for this document master.
     */
    public com.coda.www.efinance.schemas.documentmaster.Master getDocumentMasterNoLists() {
        return documentMasterNoLists;
    }


    /**
     * Sets the documentMasterNoLists value for this GetNoListsResponse.
     * 
     * @param documentMasterNoLists   * Contains the number range
     *                             lists for this document master.
     */
    public void setDocumentMasterNoLists(com.coda.www.efinance.schemas.documentmaster.Master documentMasterNoLists) {
        this.documentMasterNoLists = documentMasterNoLists;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetNoListsResponse)) return false;
        GetNoListsResponse other = (GetNoListsResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentMasterNoLists==null && other.getDocumentMasterNoLists()==null) || 
             (this.documentMasterNoLists!=null &&
              this.documentMasterNoLists.equals(other.getDocumentMasterNoLists())));
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
        if (getDocumentMasterNoLists() != null) {
            _hashCode += getDocumentMasterNoLists().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetNoListsResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", ">GetNoListsResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentMasterNoLists");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster/documentmaster-11.1/webservice", "DocumentMasterNoLists"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Master"));
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
