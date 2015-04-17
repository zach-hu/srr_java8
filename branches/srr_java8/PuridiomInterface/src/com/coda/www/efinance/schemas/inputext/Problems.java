/**
 * Problems.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains details of any problems that
 *                 invalidate the specified document for posting to the
 * Books or the Intray.
 */
public class Problems  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.ProblemLine[] lines;

    public Problems() {
    }

    public Problems(
           com.coda.www.efinance.schemas.inputext.ProblemLine[] lines) {
           this.lines = lines;
    }


    /**
     * Gets the lines value for this Problems.
     * 
     * @return lines
     */
    public com.coda.www.efinance.schemas.inputext.ProblemLine[] getLines() {
        return lines;
    }


    /**
     * Sets the lines value for this Problems.
     * 
     * @param lines
     */
    public void setLines(com.coda.www.efinance.schemas.inputext.ProblemLine[] lines) {
        this.lines = lines;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Problems)) return false;
        Problems other = (Problems) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lines==null && other.getLines()==null) || 
             (this.lines!=null &&
              java.util.Arrays.equals(this.lines, other.getLines())));
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
        if (getLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLines(), i);
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
        new org.apache.axis.description.TypeDesc(Problems.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Problems"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Lines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemLine"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Line"));
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
