/**
 * CurrReview.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * This element contains the currency
 *                 rates and currency values of all lines in the specified
 * document.
 */
public class CurrReview  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] documentWideData;

    /* This element contains a list
     *                         of the currency details that apply to lines
     * in
     *                         the document. */
    private com.coda.www.efinance.schemas.inputext.CurrReviewLine[] lineData;

    public CurrReview() {
    }

    public CurrReview(
           com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] documentWideData,
           com.coda.www.efinance.schemas.inputext.CurrReviewLine[] lineData) {
           this.documentWideData = documentWideData;
           this.lineData = lineData;
    }


    /**
     * Gets the documentWideData value for this CurrReview.
     * 
     * @return documentWideData
     */
    public com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] getDocumentWideData() {
        return documentWideData;
    }


    /**
     * Sets the documentWideData value for this CurrReview.
     * 
     * @param documentWideData
     */
    public void setDocumentWideData(com.coda.www.efinance.schemas.inputext.CurrReviewCurrRateValue[] documentWideData) {
        this.documentWideData = documentWideData;
    }


    /**
     * Gets the lineData value for this CurrReview.
     * 
     * @return lineData   * This element contains a list
     *                         of the currency details that apply to lines
     * in
     *                         the document.
     */
    public com.coda.www.efinance.schemas.inputext.CurrReviewLine[] getLineData() {
        return lineData;
    }


    /**
     * Sets the lineData value for this CurrReview.
     * 
     * @param lineData   * This element contains a list
     *                         of the currency details that apply to lines
     * in
     *                         the document.
     */
    public void setLineData(com.coda.www.efinance.schemas.inputext.CurrReviewLine[] lineData) {
        this.lineData = lineData;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CurrReview)) return false;
        CurrReview other = (CurrReview) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentWideData==null && other.getDocumentWideData()==null) || 
             (this.documentWideData!=null &&
              java.util.Arrays.equals(this.documentWideData, other.getDocumentWideData()))) &&
            ((this.lineData==null && other.getLineData()==null) || 
             (this.lineData!=null &&
              java.util.Arrays.equals(this.lineData, other.getLineData())));
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
        if (getDocumentWideData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentWideData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentWideData(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getLineData() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLineData());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLineData(), i);
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
        new org.apache.axis.description.TypeDesc(CurrReview.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReview"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentWideData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocumentWideData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewCurrRateValue"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Currency"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "LineData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "CurrReviewLine"));
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
