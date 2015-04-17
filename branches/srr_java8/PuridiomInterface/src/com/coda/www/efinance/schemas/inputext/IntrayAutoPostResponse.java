/**
 * IntrayAutoPostResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class IntrayAutoPostResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains results
     *                                 information for the documents posted
     * by
     *                                 the IntrayAutoPost request corresponding
     * to this response. */
    private com.coda.www.efinance.schemas.inputext.IntrayAutoPostResults intrayResults;

    public IntrayAutoPostResponse() {
    }

    public IntrayAutoPostResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.inputext.IntrayAutoPostResults intrayResults) {
        super();
        this.intrayResults = intrayResults;
    }


    /**
     * Gets the intrayResults value for this IntrayAutoPostResponse.
     *
     * @return intrayResults   * Contains results
     *                                 information for the documents posted
     * by
     *                                 the IntrayAutoPost request corresponding
     * to this response.
     */
    public com.coda.www.efinance.schemas.inputext.IntrayAutoPostResults getIntrayResults() {
        return intrayResults;
    }


    /**
     * Sets the intrayResults value for this IntrayAutoPostResponse.
     *
     * @param intrayResults   * Contains results
     *                                 information for the documents posted
     * by
     *                                 the IntrayAutoPost request corresponding
     * to this response.
     */
    public void setIntrayResults(com.coda.www.efinance.schemas.inputext.IntrayAutoPostResults intrayResults) {
        this.intrayResults = intrayResults;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayAutoPostResponse)) return false;
        IntrayAutoPostResponse other = (IntrayAutoPostResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.intrayResults==null && other.getIntrayResults()==null) ||
             (this.intrayResults!=null &&
              this.intrayResults.equals(other.getIntrayResults())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getIntrayResults() != null) {
            _hashCode += getIntrayResults().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayAutoPostResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayResults");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayResults"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostResults"));
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
