/**
 * IntrayAutoPostRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext.input_11_2.webservice;

public class IntrayAutoPostRequest  implements java.io.Serializable {
    /* Contains information used for
     *                             posting the documents on the Intray, to
     * the Books. */
    private com.coda.www.efinance.schemas.inputext.IntrayAutoPostInfo intrayAutoPostInfo;

    /* Contains key information
     *                             identifying which document(s) on the Intray
     * will be posted to the Books. */
    private com.coda.www.efinance.schemas.inputext.IntrayPostKey[] keys;

    public IntrayAutoPostRequest() {
    }

    public IntrayAutoPostRequest(
           com.coda.www.efinance.schemas.inputext.IntrayAutoPostInfo intrayAutoPostInfo,
           com.coda.www.efinance.schemas.inputext.IntrayPostKey[] keys) {
           this.intrayAutoPostInfo = intrayAutoPostInfo;
           this.keys = keys;
    }


    /**
     * Gets the intrayAutoPostInfo value for this IntrayAutoPostRequest.
     * 
     * @return intrayAutoPostInfo   * Contains information used for
     *                             posting the documents on the Intray, to
     * the Books.
     */
    public com.coda.www.efinance.schemas.inputext.IntrayAutoPostInfo getIntrayAutoPostInfo() {
        return intrayAutoPostInfo;
    }


    /**
     * Sets the intrayAutoPostInfo value for this IntrayAutoPostRequest.
     * 
     * @param intrayAutoPostInfo   * Contains information used for
     *                             posting the documents on the Intray, to
     * the Books.
     */
    public void setIntrayAutoPostInfo(com.coda.www.efinance.schemas.inputext.IntrayAutoPostInfo intrayAutoPostInfo) {
        this.intrayAutoPostInfo = intrayAutoPostInfo;
    }


    /**
     * Gets the keys value for this IntrayAutoPostRequest.
     * 
     * @return keys   * Contains key information
     *                             identifying which document(s) on the Intray
     * will be posted to the Books.
     */
    public com.coda.www.efinance.schemas.inputext.IntrayPostKey[] getKeys() {
        return keys;
    }


    /**
     * Sets the keys value for this IntrayAutoPostRequest.
     * 
     * @param keys   * Contains key information
     *                             identifying which document(s) on the Intray
     * will be posted to the Books.
     */
    public void setKeys(com.coda.www.efinance.schemas.inputext.IntrayPostKey[] keys) {
        this.keys = keys;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayAutoPostRequest)) return false;
        IntrayAutoPostRequest other = (IntrayAutoPostRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.intrayAutoPostInfo==null && other.getIntrayAutoPostInfo()==null) || 
             (this.intrayAutoPostInfo!=null &&
              this.intrayAutoPostInfo.equals(other.getIntrayAutoPostInfo()))) &&
            ((this.keys==null && other.getKeys()==null) || 
             (this.keys!=null &&
              java.util.Arrays.equals(this.keys, other.getKeys())));
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
        if (getIntrayAutoPostInfo() != null) {
            _hashCode += getIntrayAutoPostInfo().hashCode();
        }
        if (getKeys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getKeys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getKeys(), i);
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
        new org.apache.axis.description.TypeDesc(IntrayAutoPostRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", ">IntrayAutoPostRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayAutoPostInfo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "IntrayAutoPostInfo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostInfo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("keys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext/input-11.2/webservice", "Keys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostKey"));
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
