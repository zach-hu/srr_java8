/**
 * GetResponsibleUsersWithToleranceResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction.account_11_2.webservice;

public class GetResponsibleUsersWithToleranceResponse  implements java.io.Serializable {
    /* Details of tolerances
     *                             associated with users. */
    private com.coda.www.efinance.schemas.transaction.UsersAndTolerances responsibleUsersWithTolerance;

    public GetResponsibleUsersWithToleranceResponse() {
    }

    public GetResponsibleUsersWithToleranceResponse(
           com.coda.www.efinance.schemas.transaction.UsersAndTolerances responsibleUsersWithTolerance) {
           this.responsibleUsersWithTolerance = responsibleUsersWithTolerance;
    }


    /**
     * Gets the responsibleUsersWithTolerance value for this GetResponsibleUsersWithToleranceResponse.
     * 
     * @return responsibleUsersWithTolerance   * Details of tolerances
     *                             associated with users.
     */
    public com.coda.www.efinance.schemas.transaction.UsersAndTolerances getResponsibleUsersWithTolerance() {
        return responsibleUsersWithTolerance;
    }


    /**
     * Sets the responsibleUsersWithTolerance value for this GetResponsibleUsersWithToleranceResponse.
     * 
     * @param responsibleUsersWithTolerance   * Details of tolerances
     *                             associated with users.
     */
    public void setResponsibleUsersWithTolerance(com.coda.www.efinance.schemas.transaction.UsersAndTolerances responsibleUsersWithTolerance) {
        this.responsibleUsersWithTolerance = responsibleUsersWithTolerance;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResponsibleUsersWithToleranceResponse)) return false;
        GetResponsibleUsersWithToleranceResponse other = (GetResponsibleUsersWithToleranceResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responsibleUsersWithTolerance==null && other.getResponsibleUsersWithTolerance()==null) || 
             (this.responsibleUsersWithTolerance!=null &&
              this.responsibleUsersWithTolerance.equals(other.getResponsibleUsersWithTolerance())));
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
        if (getResponsibleUsersWithTolerance() != null) {
            _hashCode += getResponsibleUsersWithTolerance().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetResponsibleUsersWithToleranceResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", ">GetResponsibleUsersWithToleranceResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsibleUsersWithTolerance");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction/account-11.2/webservice", "ResponsibleUsersWithTolerance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "UsersAndTolerances"));
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
