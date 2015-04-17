/**
 * ValidateReferenceAttachmentResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.common.schemas.attachment;

public class ValidateReferenceAttachmentResponse  extends com.coda.www.efinance.schemas.common.Response  implements java.io.Serializable {
    /* The URI location of
     *                                 the attachment. */
    private java.lang.String output;

    /* The text validated as
     *                                 a reference to an
     *                             attachment. */
    private java.lang.String filename;

    public ValidateReferenceAttachmentResponse() {
    }

    public ValidateReferenceAttachmentResponse(
           com.coda.www.efinance.schemas.common.TypeResponseStatus status,
           java.lang.String transactioncoordinator,
           com.coda.www.efinance.schemas.common.Reason reason,
           java.lang.String output,
           java.lang.String filename) {
        super();
        this.output = output;
        this.filename = filename;
    }


    /**
     * Gets the output value for this ValidateReferenceAttachmentResponse.
     *
     * @return output   * The URI location of
     *                                 the attachment.
     */
    public java.lang.String getOutput() {
        return output;
    }


    /**
     * Sets the output value for this ValidateReferenceAttachmentResponse.
     *
     * @param output   * The URI location of
     *                                 the attachment.
     */
    public void setOutput(java.lang.String output) {
        this.output = output;
    }


    /**
     * Gets the filename value for this ValidateReferenceAttachmentResponse.
     *
     * @return filename   * The text validated as
     *                                 a reference to an
     *                             attachment.
     */
    public java.lang.String getFilename() {
        return filename;
    }


    /**
     * Sets the filename value for this ValidateReferenceAttachmentResponse.
     *
     * @param filename   * The text validated as
     *                                 a reference to an
     *                             attachment.
     */
    public void setFilename(java.lang.String filename) {
        this.filename = filename;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ValidateReferenceAttachmentResponse)) return false;
        ValidateReferenceAttachmentResponse other = (ValidateReferenceAttachmentResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) &&
            ((this.output==null && other.getOutput()==null) ||
             (this.output!=null &&
              this.output.equals(other.getOutput()))) &&
            ((this.filename==null && other.getFilename()==null) ||
             (this.filename!=null &&
              this.filename.equals(other.getFilename())));
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
        if (getOutput() != null) {
            _hashCode += getOutput().hashCode();
        }
        if (getFilename() != null) {
            _hashCode += getFilename().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ValidateReferenceAttachmentResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "ValidateReferenceAttachmentResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("output");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Output"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("filename");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/common/schemas/attachment", "Filename"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
