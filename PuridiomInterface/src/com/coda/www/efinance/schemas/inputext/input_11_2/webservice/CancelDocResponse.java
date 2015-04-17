/**
 * CancelDocResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class CancelDocResponse  implements java.io.Serializable {
    /* Contains results information
     *                             for the CancelDoc request corresponding
     * to
     *                             this response */
    private com.coda.www.efinance.schemas.inputext.CancelDocResults cancelDoc;

    public CancelDocResponse() {
    }

    public CancelDocResponse(
           com.coda.www.efinance.schemas.inputext.CancelDocResults cancelDoc) {
           this.cancelDoc = cancelDoc;
    }


    /**
     * Gets the cancelDoc value for this CancelDocResponse.
     * 
     * @return cancelDoc   * Contains results information
     *                             for the CancelDoc request corresponding
     * to
     *                             this response
     */
    public com.coda.www.efinance.schemas.inputext.CancelDocResults getCancelDoc() {
        return cancelDoc;
    }


    /**
     * Sets the cancelDoc value for this CancelDocResponse.
     * 
     * @param cancelDoc   * Contains results information
     *                             for the CancelDoc request corresponding
     * to
     *                             this response
     */
    public void setCancelDoc(com.coda.www.efinance.schemas.inputext.CancelDocResults cancelDoc) {
        this.cancelDoc = cancelDoc;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CancelDocResponse)) return false;
        CancelDocResponse other = (CancelDocResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cancelDoc==null && other.getCancelDoc()==null) || 
             (this.cancelDoc!=null &&
              this.cancelDoc.equals(other.getCancelDoc())));
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
        if (getCancelDoc() != null) {
            _hashCode += getCancelDoc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CancelDocResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">CancelDocResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cancelDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "CancelDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CancelDocResults"));
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
