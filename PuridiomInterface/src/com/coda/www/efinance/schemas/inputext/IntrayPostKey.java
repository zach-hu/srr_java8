/**
 * IntrayPostKey.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains key information for a
 *                 document on the Intray that will be posted to the
 *             Books.
 */
public class IntrayPostKey  extends com.coda.www.efinance.schemas.transaction.TxnKey  implements java.io.Serializable {
    private boolean applyCurrentExchangeRate;

    /* The workflow process
     *                                 definition code. */
    private java.lang.String workflowCode;

    /* The position code
     *                                 within the position
     *                             hierarchy. */
    private java.lang.String positionCode;

    public IntrayPostKey() {
    }

    public IntrayPostKey(
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String number,
           boolean applyCurrentExchangeRate,
           java.lang.String workflowCode,
           java.lang.String positionCode) {
        super(
            cmpCode,
            code,
            number);
        this.applyCurrentExchangeRate = applyCurrentExchangeRate;
        this.workflowCode = workflowCode;
        this.positionCode = positionCode;
    }


    /**
     * Gets the applyCurrentExchangeRate value for this IntrayPostKey.
     * 
     * @return applyCurrentExchangeRate
     */
    public boolean isApplyCurrentExchangeRate() {
        return applyCurrentExchangeRate;
    }


    /**
     * Sets the applyCurrentExchangeRate value for this IntrayPostKey.
     * 
     * @param applyCurrentExchangeRate
     */
    public void setApplyCurrentExchangeRate(boolean applyCurrentExchangeRate) {
        this.applyCurrentExchangeRate = applyCurrentExchangeRate;
    }


    /**
     * Gets the workflowCode value for this IntrayPostKey.
     * 
     * @return workflowCode   * The workflow process
     *                                 definition code.
     */
    public java.lang.String getWorkflowCode() {
        return workflowCode;
    }


    /**
     * Sets the workflowCode value for this IntrayPostKey.
     * 
     * @param workflowCode   * The workflow process
     *                                 definition code.
     */
    public void setWorkflowCode(java.lang.String workflowCode) {
        this.workflowCode = workflowCode;
    }


    /**
     * Gets the positionCode value for this IntrayPostKey.
     * 
     * @return positionCode   * The position code
     *                                 within the position
     *                             hierarchy.
     */
    public java.lang.String getPositionCode() {
        return positionCode;
    }


    /**
     * Sets the positionCode value for this IntrayPostKey.
     * 
     * @param positionCode   * The position code
     *                                 within the position
     *                             hierarchy.
     */
    public void setPositionCode(java.lang.String positionCode) {
        this.positionCode = positionCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntrayPostKey)) return false;
        IntrayPostKey other = (IntrayPostKey) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            this.applyCurrentExchangeRate == other.isApplyCurrentExchangeRate() &&
            ((this.workflowCode==null && other.getWorkflowCode()==null) || 
             (this.workflowCode!=null &&
              this.workflowCode.equals(other.getWorkflowCode()))) &&
            ((this.positionCode==null && other.getPositionCode()==null) || 
             (this.positionCode!=null &&
              this.positionCode.equals(other.getPositionCode())));
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
        _hashCode += (isApplyCurrentExchangeRate() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getWorkflowCode() != null) {
            _hashCode += getWorkflowCode().hashCode();
        }
        if (getPositionCode() != null) {
            _hashCode += getPositionCode().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntrayPostKey.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "IntrayPostKey"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("applyCurrentExchangeRate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ApplyCurrentExchangeRate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workflowCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "WorkflowCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("positionCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "PositionCode"));
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
