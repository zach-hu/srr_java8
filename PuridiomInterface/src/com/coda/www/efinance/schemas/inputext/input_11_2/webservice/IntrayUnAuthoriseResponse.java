/**
 * IntrayUnAuthoriseResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class IntrayUnAuthoriseResponse  implements java.io.Serializable {
    /* Contains the keys information
     *                             you supplied in the request corresponding
     * to
     *                             this response. */
    private com.coda.www.efinance.schemas.transaction.TxnKey[] unAuthorisedKeys;

    public IntrayUnAuthoriseResponse() {
    }

    public IntrayUnAuthoriseResponse(
           com.coda.www.efinance.schemas.transaction.TxnKey[] unAuthorisedKeys) {
           this.unAuthorisedKeys = unAuthorisedKeys;
    }


    /**
     * Gets the unAuthorisedKeys value for this IntrayUnAuthoriseResponse.
     * 
     * @return unAuthorisedKeys   * Contains the keys information
     *                             you supplied in the request corresponding
     * to
     *                             this response.
     */
    public com.coda.www.efinance.schemas.transaction.TxnKey[] getUnAuthorisedKeys() {
        return unAuthorisedKeys;
    }


    /**
     * Sets the unAuthorisedKeys value for this IntrayUnAuthoriseResponse.
     * 
     * @param unAuthorisedKeys   * Contains the keys information
     *                             you supplied in the request corresponding
     * to
     *                             this response.
     */
    public void setUnAuthorisedKeys(com.coda.www.efinance.schemas.transaction.TxnKey[] unAuthorisedKeys) {
        this.unAuthorisedKeys = unAuthorisedKeys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayUnAuthoriseResponse)) return false;
        IntrayUnAuthoriseResponse other = (IntrayUnAuthoriseResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.unAuthorisedKeys==null && other.getUnAuthorisedKeys()==null) || 
             (this.unAuthorisedKeys!=null &&
              java.util.Arrays.equals(this.unAuthorisedKeys, other.getUnAuthorisedKeys())));
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
        if (getUnAuthorisedKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUnAuthorisedKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUnAuthorisedKeys(), i);
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
        new org.apache.axis.description.TypeDesc(IntrayUnAuthoriseResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayUnAuthoriseResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("unAuthorisedKeys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "UnAuthorisedKeys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "txnKey"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Key"));
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
