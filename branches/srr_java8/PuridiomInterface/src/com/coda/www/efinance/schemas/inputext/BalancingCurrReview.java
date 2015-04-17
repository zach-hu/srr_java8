/**
 * BalancingCurrReview.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains the total
 *                 document value in document, home and dual
 *             currencies.
 */
public class BalancingCurrReview  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.common.CurrValue docTotal;

    /* The
     *                         total document value in home
     *                     currency. */
    private com.coda.www.efinance.schemas.common.CurrValue homeTotal;

    /* The total document value in
     *                         dual currency. */
    private com.coda.www.efinance.schemas.common.CurrValue dualTotal;

    public BalancingCurrReview() {
    }

    public BalancingCurrReview(
           com.coda.www.efinance.schemas.common.CurrValue docTotal,
           com.coda.www.efinance.schemas.common.CurrValue homeTotal,
           com.coda.www.efinance.schemas.common.CurrValue dualTotal) {
           this.docTotal = docTotal;
           this.homeTotal = homeTotal;
           this.dualTotal = dualTotal;
    }


    /**
     * Gets the docTotal value for this BalancingCurrReview.
     * 
     * @return docTotal
     */
    public com.coda.www.efinance.schemas.common.CurrValue getDocTotal() {
        return docTotal;
    }


    /**
     * Sets the docTotal value for this BalancingCurrReview.
     * 
     * @param docTotal
     */
    public void setDocTotal(com.coda.www.efinance.schemas.common.CurrValue docTotal) {
        this.docTotal = docTotal;
    }


    /**
     * Gets the homeTotal value for this BalancingCurrReview.
     * 
     * @return homeTotal   * The
     *                         total document value in home
     *                     currency.
     */
    public com.coda.www.efinance.schemas.common.CurrValue getHomeTotal() {
        return homeTotal;
    }


    /**
     * Sets the homeTotal value for this BalancingCurrReview.
     * 
     * @param homeTotal   * The
     *                         total document value in home
     *                     currency.
     */
    public void setHomeTotal(com.coda.www.efinance.schemas.common.CurrValue homeTotal) {
        this.homeTotal = homeTotal;
    }


    /**
     * Gets the dualTotal value for this BalancingCurrReview.
     * 
     * @return dualTotal   * The total document value in
     *                         dual currency.
     */
    public com.coda.www.efinance.schemas.common.CurrValue getDualTotal() {
        return dualTotal;
    }


    /**
     * Sets the dualTotal value for this BalancingCurrReview.
     * 
     * @param dualTotal   * The total document value in
     *                         dual currency.
     */
    public void setDualTotal(com.coda.www.efinance.schemas.common.CurrValue dualTotal) {
        this.dualTotal = dualTotal;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalancingCurrReview)) return false;
        BalancingCurrReview other = (BalancingCurrReview) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.docTotal==null && other.getDocTotal()==null) || 
             (this.docTotal!=null &&
              this.docTotal.equals(other.getDocTotal()))) &&
            ((this.homeTotal==null && other.getHomeTotal()==null) || 
             (this.homeTotal!=null &&
              this.homeTotal.equals(other.getHomeTotal()))) &&
            ((this.dualTotal==null && other.getDualTotal()==null) || 
             (this.dualTotal!=null &&
              this.dualTotal.equals(other.getDualTotal())));
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
        if (getDocTotal() != null) {
            _hashCode += getDocTotal().hashCode();
        }
        if (getHomeTotal() != null) {
            _hashCode += getHomeTotal().hashCode();
        }
        if (getDualTotal() != null) {
            _hashCode += getDualTotal().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BalancingCurrReview.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingCurrReview"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "CurrValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homeTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "HomeTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "CurrValue"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dualTotal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DualTotal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "CurrValue"));
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
