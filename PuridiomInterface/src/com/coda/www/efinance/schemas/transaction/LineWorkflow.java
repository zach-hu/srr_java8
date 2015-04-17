/**
 * LineWorkflow.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This element holds details of the
 *                 workflow attached to this document
 *             line.
 */
public class LineWorkflow  implements java.io.Serializable {
    private java.lang.String lineStatus;

    /* A
     *                         user-defined authorisation code associated
     * with
     *                         this line. */
    private java.lang.String authCode;

    /* The
     *                         code of the document line
     *                     approver. */
    private java.lang.String lineApprover;

    public LineWorkflow() {
    }

    public LineWorkflow(
           java.lang.String lineStatus,
           java.lang.String authCode,
           java.lang.String lineApprover) {
           this.lineStatus = lineStatus;
           this.authCode = authCode;
           this.lineApprover = lineApprover;
    }


    /**
     * Gets the lineStatus value for this LineWorkflow.
     * 
     * @return lineStatus
     */
    public java.lang.String getLineStatus() {
        return lineStatus;
    }


    /**
     * Sets the lineStatus value for this LineWorkflow.
     * 
     * @param lineStatus
     */
    public void setLineStatus(java.lang.String lineStatus) {
        this.lineStatus = lineStatus;
    }


    /**
     * Gets the authCode value for this LineWorkflow.
     * 
     * @return authCode   * A
     *                         user-defined authorisation code associated
     * with
     *                         this line.
     */
    public java.lang.String getAuthCode() {
        return authCode;
    }


    /**
     * Sets the authCode value for this LineWorkflow.
     * 
     * @param authCode   * A
     *                         user-defined authorisation code associated
     * with
     *                         this line.
     */
    public void setAuthCode(java.lang.String authCode) {
        this.authCode = authCode;
    }


    /**
     * Gets the lineApprover value for this LineWorkflow.
     * 
     * @return lineApprover   * The
     *                         code of the document line
     *                     approver.
     */
    public java.lang.String getLineApprover() {
        return lineApprover;
    }


    /**
     * Sets the lineApprover value for this LineWorkflow.
     * 
     * @param lineApprover   * The
     *                         code of the document line
     *                     approver.
     */
    public void setLineApprover(java.lang.String lineApprover) {
        this.lineApprover = lineApprover;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LineWorkflow)) return false;
        LineWorkflow other = (LineWorkflow) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.lineStatus==null && other.getLineStatus()==null) || 
             (this.lineStatus!=null &&
              this.lineStatus.equals(other.getLineStatus()))) &&
            ((this.authCode==null && other.getAuthCode()==null) || 
             (this.authCode!=null &&
              this.authCode.equals(other.getAuthCode()))) &&
            ((this.lineApprover==null && other.getLineApprover()==null) || 
             (this.lineApprover!=null &&
              this.lineApprover.equals(other.getLineApprover())));
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
        if (getLineStatus() != null) {
            _hashCode += getLineStatus().hashCode();
        }
        if (getAuthCode() != null) {
            _hashCode += getAuthCode().hashCode();
        }
        if (getLineApprover() != null) {
            _hashCode += getLineApprover().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LineWorkflow.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineWorkflow"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AuthCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineApprover");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "LineApprover"));
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
