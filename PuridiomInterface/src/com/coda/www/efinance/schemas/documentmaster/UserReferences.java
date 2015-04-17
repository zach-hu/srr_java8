/**
 * UserReferences.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 user references.
 */
public class UserReferences  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.documentmaster.UserReference reference1;

    /* This element holds
     *                         information about user reference
     *                     2. */
    private com.coda.www.efinance.schemas.documentmaster.UserReference reference2;

    /* This
     *                         element holds information about user reference
     * 3. */
    private com.coda.www.efinance.schemas.documentmaster.UserReference reference3;

    public UserReferences() {
    }

    public UserReferences(
           com.coda.www.efinance.schemas.documentmaster.UserReference reference1,
           com.coda.www.efinance.schemas.documentmaster.UserReference reference2,
           com.coda.www.efinance.schemas.documentmaster.UserReference reference3) {
           this.reference1 = reference1;
           this.reference2 = reference2;
           this.reference3 = reference3;
    }


    /**
     * Gets the reference1 value for this UserReferences.
     * 
     * @return reference1
     */
    public com.coda.www.efinance.schemas.documentmaster.UserReference getReference1() {
        return reference1;
    }


    /**
     * Sets the reference1 value for this UserReferences.
     * 
     * @param reference1
     */
    public void setReference1(com.coda.www.efinance.schemas.documentmaster.UserReference reference1) {
        this.reference1 = reference1;
    }


    /**
     * Gets the reference2 value for this UserReferences.
     * 
     * @return reference2   * This element holds
     *                         information about user reference
     *                     2.
     */
    public com.coda.www.efinance.schemas.documentmaster.UserReference getReference2() {
        return reference2;
    }


    /**
     * Sets the reference2 value for this UserReferences.
     * 
     * @param reference2   * This element holds
     *                         information about user reference
     *                     2.
     */
    public void setReference2(com.coda.www.efinance.schemas.documentmaster.UserReference reference2) {
        this.reference2 = reference2;
    }


    /**
     * Gets the reference3 value for this UserReferences.
     * 
     * @return reference3   * This
     *                         element holds information about user reference
     * 3.
     */
    public com.coda.www.efinance.schemas.documentmaster.UserReference getReference3() {
        return reference3;
    }


    /**
     * Sets the reference3 value for this UserReferences.
     * 
     * @param reference3   * This
     *                         element holds information about user reference
     * 3.
     */
    public void setReference3(com.coda.www.efinance.schemas.documentmaster.UserReference reference3) {
        this.reference3 = reference3;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UserReferences)) return false;
        UserReferences other = (UserReferences) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.reference1==null && other.getReference1()==null) || 
             (this.reference1!=null &&
              this.reference1.equals(other.getReference1()))) &&
            ((this.reference2==null && other.getReference2()==null) || 
             (this.reference2!=null &&
              this.reference2.equals(other.getReference2()))) &&
            ((this.reference3==null && other.getReference3()==null) || 
             (this.reference3!=null &&
              this.reference3.equals(other.getReference3())));
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
        if (getReference1() != null) {
            _hashCode += getReference1().hashCode();
        }
        if (getReference2() != null) {
            _hashCode += getReference2().hashCode();
        }
        if (getReference3() != null) {
            _hashCode += getReference3().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UserReferences.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReferences"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReference"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReference"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("reference3");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "Reference3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "UserReference"));
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
