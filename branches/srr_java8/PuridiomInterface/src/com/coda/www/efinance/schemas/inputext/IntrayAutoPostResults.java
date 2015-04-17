/**
 * IntrayAutoPostResults.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains results
 *                 information for the documents posted by the
 *                 IntrayAutoPost request corresponding to this
 *             response.
 */
public class IntrayAutoPostResults  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.IntrayPostResult[] intrayResult;

    public IntrayAutoPostResults() {
    }

    public IntrayAutoPostResults(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           com.coda.www.efinance.schemas.inputext.IntrayPostResult[] intrayResult) {
        super();
        this.intrayResult = intrayResult;
    }


    /**
     * Gets the intrayResult value for this IntrayAutoPostResults.
     *
     * @return intrayResult
     */
    public com.coda.www.efinance.schemas.inputext.IntrayPostResult[] getIntrayResult() {
        return intrayResult;
    }


    /**
     * Sets the intrayResult value for this IntrayAutoPostResults.
     *
     * @param intrayResult
     */
    public void setIntrayResult(com.coda.www.efinance.schemas.inputext.IntrayPostResult[] intrayResult) {
        this.intrayResult = intrayResult;
    }

    public com.coda.www.efinance.schemas.inputext.IntrayPostResult getIntrayResult(int i) {
        return this.intrayResult[i];
    }

    public void setIntrayResult(int i, com.coda.www.efinance.schemas.inputext.IntrayPostResult _value) {
        this.intrayResult[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayAutoPostResults)) return false;
        IntrayAutoPostResults other = (IntrayAutoPostResults) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.intrayResult==null && other.getIntrayResult()==null) ||
             (this.intrayResult!=null &&
              java.util.Arrays.equals(this.intrayResult, other.getIntrayResult())));
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
        if (getIntrayResult() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getIntrayResult());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getIntrayResult(), i);
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
        new org.apache.axis.description.TypeDesc(IntrayAutoPostResults.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayAutoPostResults"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("intrayResult");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayResult"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostResult"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
