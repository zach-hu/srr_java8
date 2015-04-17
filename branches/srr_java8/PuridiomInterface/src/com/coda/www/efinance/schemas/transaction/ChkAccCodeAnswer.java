/**
 * ChkAccCodeAnswer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds the result of the
 *             validation.
 */
public class ChkAccCodeAnswer  implements java.io.Serializable {
    private boolean good;

    /* Holds details of why the
     *                         account code failed the validation. Applies
     * only
     *                         when the account code is
     *                     invalid. */
    private com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed failed;

    /* Holds the short names of the
     *                         elements in the account code. Applies only
     * when
     *                         the account code is valid. */
    private java.lang.String[] passed;

    public ChkAccCodeAnswer() {
    }

    public ChkAccCodeAnswer(
           boolean good,
           com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed failed,
           java.lang.String[] passed) {
           this.good = good;
           this.failed = failed;
           this.passed = passed;
    }


    /**
     * Gets the good value for this ChkAccCodeAnswer.
     * 
     * @return good
     */
    public boolean isGood() {
        return good;
    }


    /**
     * Sets the good value for this ChkAccCodeAnswer.
     * 
     * @param good
     */
    public void setGood(boolean good) {
        this.good = good;
    }


    /**
     * Gets the failed value for this ChkAccCodeAnswer.
     * 
     * @return failed   * Holds details of why the
     *                         account code failed the validation. Applies
     * only
     *                         when the account code is
     *                     invalid.
     */
    public com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed getFailed() {
        return failed;
    }


    /**
     * Sets the failed value for this ChkAccCodeAnswer.
     * 
     * @param failed   * Holds details of why the
     *                         account code failed the validation. Applies
     * only
     *                         when the account code is
     *                     invalid.
     */
    public void setFailed(com.coda.www.efinance.schemas.transaction.ChkAccCodeFailed failed) {
        this.failed = failed;
    }


    /**
     * Gets the passed value for this ChkAccCodeAnswer.
     * 
     * @return passed   * Holds the short names of the
     *                         elements in the account code. Applies only
     * when
     *                         the account code is valid.
     */
    public java.lang.String[] getPassed() {
        return passed;
    }


    /**
     * Sets the passed value for this ChkAccCodeAnswer.
     * 
     * @param passed   * Holds the short names of the
     *                         elements in the account code. Applies only
     * when
     *                         the account code is valid.
     */
    public void setPassed(java.lang.String[] passed) {
        this.passed = passed;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ChkAccCodeAnswer)) return false;
        ChkAccCodeAnswer other = (ChkAccCodeAnswer) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.good == other.isGood() &&
            ((this.failed==null && other.getFailed()==null) || 
             (this.failed!=null &&
              this.failed.equals(other.getFailed()))) &&
            ((this.passed==null && other.getPassed()==null) || 
             (this.passed!=null &&
              java.util.Arrays.equals(this.passed, other.getPassed())));
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
        _hashCode += (isGood() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getFailed() != null) {
            _hashCode += getFailed().hashCode();
        }
        if (getPassed() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPassed());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPassed(), i);
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
        new org.apache.axis.description.TypeDesc(ChkAccCodeAnswer.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeAnswer"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("good");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Good"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("failed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Failed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ChkAccCodeFailed"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Passed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeShortNameB"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ShortName"));
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
