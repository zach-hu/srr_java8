/**
 * BalancingReview.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains the sum of values on the
 *                 specified document for each balancing element and
 * the
 *                 whole document (in document, home and dual
 *             currencies).
 */
public class BalancingReview  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.BalancingCurrReview currReview;

    /* This element contains the
     *                         total value (in document, home and dual
     *                         currencies) for the balancing elements in
     * the
     *                     document. */
    private com.coda.www.efinance.schemas.inputext.ElmReviewItem[] elmReview;

    public BalancingReview() {
    }

    public BalancingReview(
           com.coda.www.efinance.schemas.inputext.BalancingCurrReview currReview,
           com.coda.www.efinance.schemas.inputext.ElmReviewItem[] elmReview) {
           this.currReview = currReview;
           this.elmReview = elmReview;
    }


    /**
     * Gets the currReview value for this BalancingReview.
     * 
     * @return currReview
     */
    public com.coda.www.efinance.schemas.inputext.BalancingCurrReview getCurrReview() {
        return currReview;
    }


    /**
     * Sets the currReview value for this BalancingReview.
     * 
     * @param currReview
     */
    public void setCurrReview(com.coda.www.efinance.schemas.inputext.BalancingCurrReview currReview) {
        this.currReview = currReview;
    }


    /**
     * Gets the elmReview value for this BalancingReview.
     * 
     * @return elmReview   * This element contains the
     *                         total value (in document, home and dual
     *                         currencies) for the balancing elements in
     * the
     *                     document.
     */
    public com.coda.www.efinance.schemas.inputext.ElmReviewItem[] getElmReview() {
        return elmReview;
    }


    /**
     * Sets the elmReview value for this BalancingReview.
     * 
     * @param elmReview   * This element contains the
     *                         total value (in document, home and dual
     *                         currencies) for the balancing elements in
     * the
     *                     document.
     */
    public void setElmReview(com.coda.www.efinance.schemas.inputext.ElmReviewItem[] elmReview) {
        this.elmReview = elmReview;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BalancingReview)) return false;
        BalancingReview other = (BalancingReview) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.currReview==null && other.getCurrReview()==null) || 
             (this.currReview!=null &&
              this.currReview.equals(other.getCurrReview()))) &&
            ((this.elmReview==null && other.getElmReview()==null) || 
             (this.elmReview!=null &&
              java.util.Arrays.equals(this.elmReview, other.getElmReview())));
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
        if (getCurrReview() != null) {
            _hashCode += getCurrReview().hashCode();
        }
        if (getElmReview() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getElmReview());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getElmReview(), i);
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
        new org.apache.axis.description.TypeDesc(BalancingReview.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingReview"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currReview");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReview"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "BalancingCurrReview"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("elmReview");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReview"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ElmReviewItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Item"));
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
