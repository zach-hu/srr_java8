/**
 * CheckPostResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;

public class CheckPostResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* Contains details of
     *                                 any problems that invalidate the
     *                                 specified document for posting to
     * the
     *                                 Books or the Intray. */
    private com.coda.www.efinance.schemas.inputext.Problems problems;

    public CheckPostResponse() {
    }

    public CheckPostResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.inputext.Problems problems) {
        super();
        this.problems = problems;
    }


    /**
     * Gets the problems value for this CheckPostResponse.
     *
     * @return problems   * Contains details of
     *                                 any problems that invalidate the
     *                                 specified document for posting to
     * the
     *                                 Books or the Intray.
     */
    public com.coda.www.efinance.schemas.inputext.Problems getProblems() {
        return problems;
    }


    /**
     * Sets the problems value for this CheckPostResponse.
     *
     * @param problems   * Contains details of
     *                                 any problems that invalidate the
     *                                 specified document for posting to
     * the
     *                                 Books or the Intray.
     */
    public void setProblems(com.coda.www.efinance.schemas.inputext.Problems problems) {
        this.problems = problems;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CheckPostResponse)) return false;
        CheckPostResponse other = (CheckPostResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.problems==null && other.getProblems()==null) ||
             (this.problems!=null &&
              this.problems.equals(other.getProblems())));
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
        if (getProblems() != null) {
            _hashCode += getProblems().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CheckPostResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CheckPostResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problems");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Problems"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Problems"));
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
