/**
 * ExtRef.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 external reference 1
 */
public class ExtRef  implements java.io.Serializable {
    private java.lang.String usage;

    /* A
     *                         label for the external
     *                     reference. */
    private java.lang.String label;

    /* Specifies that users can edit
     *                         this external reference in Browse Details
     * or
     *                         Reconciliation, if their capability
     *                     allows. */
    private boolean modifiable;

    /* This element holds
     *                         information about whether you want to check
     * external references for duplicates and, if so,
     *                     how. */
    private com.coda.www.efinance.schemas.documentmaster.ExtRefChecking checking;

    public ExtRef() {
    }

    public ExtRef(
           java.lang.String usage,
           java.lang.String label,
           boolean modifiable,
           com.coda.www.efinance.schemas.documentmaster.ExtRefChecking checking) {
           this.usage = usage;
           this.label = label;
           this.modifiable = modifiable;
           this.checking = checking;
    }


    /**
     * Gets the usage value for this ExtRef.
     * 
     * @return usage
     */
    public java.lang.String getUsage() {
        return usage;
    }


    /**
     * Sets the usage value for this ExtRef.
     * 
     * @param usage
     */
    public void setUsage(java.lang.String usage) {
        this.usage = usage;
    }


    /**
     * Gets the label value for this ExtRef.
     * 
     * @return label   * A
     *                         label for the external
     *                     reference.
     */
    public java.lang.String getLabel() {
        return label;
    }


    /**
     * Sets the label value for this ExtRef.
     * 
     * @param label   * A
     *                         label for the external
     *                     reference.
     */
    public void setLabel(java.lang.String label) {
        this.label = label;
    }


    /**
     * Gets the modifiable value for this ExtRef.
     * 
     * @return modifiable   * Specifies that users can edit
     *                         this external reference in Browse Details
     * or
     *                         Reconciliation, if their capability
     *                     allows.
     */
    public boolean isModifiable() {
        return modifiable;
    }


    /**
     * Sets the modifiable value for this ExtRef.
     * 
     * @param modifiable   * Specifies that users can edit
     *                         this external reference in Browse Details
     * or
     *                         Reconciliation, if their capability
     *                     allows.
     */
    public void setModifiable(boolean modifiable) {
        this.modifiable = modifiable;
    }


    /**
     * Gets the checking value for this ExtRef.
     * 
     * @return checking   * This element holds
     *                         information about whether you want to check
     * external references for duplicates and, if so,
     *                     how.
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRefChecking getChecking() {
        return checking;
    }


    /**
     * Sets the checking value for this ExtRef.
     * 
     * @param checking   * This element holds
     *                         information about whether you want to check
     * external references for duplicates and, if so,
     *                     how.
     */
    public void setChecking(com.coda.www.efinance.schemas.documentmaster.ExtRefChecking checking) {
        this.checking = checking;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtRef)) return false;
        ExtRef other = (ExtRef) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.usage==null && other.getUsage()==null) || 
             (this.usage!=null &&
              this.usage.equals(other.getUsage()))) &&
            ((this.label==null && other.getLabel()==null) || 
             (this.label!=null &&
              this.label.equals(other.getLabel()))) &&
            this.modifiable == other.isModifiable() &&
            ((this.checking==null && other.getChecking()==null) || 
             (this.checking!=null &&
              this.checking.equals(other.getChecking())));
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
        if (getUsage() != null) {
            _hashCode += getUsage().hashCode();
        }
        if (getLabel() != null) {
            _hashCode += getLabel().hashCode();
        }
        _hashCode += (isModifiable() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getChecking() != null) {
            _hashCode += getChecking().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtRef.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRef"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usage");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Usage"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("label");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Label"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifiable");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Modifiable"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("checking");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Checking"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefChecking"));
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
