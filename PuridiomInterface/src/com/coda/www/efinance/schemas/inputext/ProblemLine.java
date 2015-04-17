/**
 * ProblemLine.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputext;


/**
 * Contains a document line causing a
 *                 problem that invalidates the
 *             document.
 */
public class ProblemLine  implements java.io.Serializable {
    private short number;

    /* The
     *                         document line value. */
    private java.math.BigDecimal docValue;

    /* Contains payment information
     *                         on the problem document line. This information
     * is not returned if the PostRequest or
     *                         CheckPostRequest has ignoresurplusdata set
     * to
     *                     TRUE. */
    private com.coda.www.efinance.schemas.inputext.ExtraPayData extraPayData;

    /* Contains payment information
     *                         that needs to be specified on the problem
     *                         document line. This information can be
     *                         completed, then re-submitted with the original
     * Post or CheckPost request. */
    private com.coda.www.efinance.schemas.inputext.ProblemPay problemPayData;

    /* Contains extra quantity
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE. */
    private com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo[] extraQtys;

    /* Contains details of any
     *                         quantities missing from the problem document
     * line. */
    private com.coda.www.efinance.schemas.inputext.MissingQuantityInfo[] missingQtys;

    /* Contains extra tax
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE. */
    private com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTax;

    /* Contains extra 1099
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE. */
    private com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTen99;

    /* Contains extra discount
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE. */
    private com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo[] extraDiscounts;

    /* This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted. */
    private com.coda.www.efinance.schemas.transaction.Asset asset;

    /* Indicates whether 1099
     *                         information is missing from the document
     *                     line. */
    private java.lang.Boolean missingTen99;

    public ProblemLine() {
    }

    public ProblemLine(
           short number,
           java.math.BigDecimal docValue,
           com.coda.www.efinance.schemas.inputext.ExtraPayData extraPayData,
           com.coda.www.efinance.schemas.inputext.ProblemPay problemPayData,
           com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo[] extraQtys,
           com.coda.www.efinance.schemas.inputext.MissingQuantityInfo[] missingQtys,
           com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTax,
           com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTen99,
           com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo[] extraDiscounts,
           com.coda.www.efinance.schemas.transaction.Asset asset,
           java.lang.Boolean missingTen99) {
           this.number = number;
           this.docValue = docValue;
           this.extraPayData = extraPayData;
           this.problemPayData = problemPayData;
           this.extraQtys = extraQtys;
           this.missingQtys = missingQtys;
           this.extraTax = extraTax;
           this.extraTen99 = extraTen99;
           this.extraDiscounts = extraDiscounts;
           this.asset = asset;
           this.missingTen99 = missingTen99;
    }


    /**
     * Gets the number value for this ProblemLine.
     * 
     * @return number
     */
    public short getNumber() {
        return number;
    }


    /**
     * Sets the number value for this ProblemLine.
     * 
     * @param number
     */
    public void setNumber(short number) {
        this.number = number;
    }


    /**
     * Gets the docValue value for this ProblemLine.
     * 
     * @return docValue   * The
     *                         document line value.
     */
    public java.math.BigDecimal getDocValue() {
        return docValue;
    }


    /**
     * Sets the docValue value for this ProblemLine.
     * 
     * @param docValue   * The
     *                         document line value.
     */
    public void setDocValue(java.math.BigDecimal docValue) {
        this.docValue = docValue;
    }


    /**
     * Gets the extraPayData value for this ProblemLine.
     * 
     * @return extraPayData   * Contains payment information
     *                         on the problem document line. This information
     * is not returned if the PostRequest or
     *                         CheckPostRequest has ignoresurplusdata set
     * to
     *                     TRUE.
     */
    public com.coda.www.efinance.schemas.inputext.ExtraPayData getExtraPayData() {
        return extraPayData;
    }


    /**
     * Sets the extraPayData value for this ProblemLine.
     * 
     * @param extraPayData   * Contains payment information
     *                         on the problem document line. This information
     * is not returned if the PostRequest or
     *                         CheckPostRequest has ignoresurplusdata set
     * to
     *                     TRUE.
     */
    public void setExtraPayData(com.coda.www.efinance.schemas.inputext.ExtraPayData extraPayData) {
        this.extraPayData = extraPayData;
    }


    /**
     * Gets the problemPayData value for this ProblemLine.
     * 
     * @return problemPayData   * Contains payment information
     *                         that needs to be specified on the problem
     *                         document line. This information can be
     *                         completed, then re-submitted with the original
     * Post or CheckPost request.
     */
    public com.coda.www.efinance.schemas.inputext.ProblemPay getProblemPayData() {
        return problemPayData;
    }


    /**
     * Sets the problemPayData value for this ProblemLine.
     * 
     * @param problemPayData   * Contains payment information
     *                         that needs to be specified on the problem
     *                         document line. This information can be
     *                         completed, then re-submitted with the original
     * Post or CheckPost request.
     */
    public void setProblemPayData(com.coda.www.efinance.schemas.inputext.ProblemPay problemPayData) {
        this.problemPayData = problemPayData;
    }


    /**
     * Gets the extraQtys value for this ProblemLine.
     * 
     * @return extraQtys   * Contains extra quantity
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo[] getExtraQtys() {
        return extraQtys;
    }


    /**
     * Sets the extraQtys value for this ProblemLine.
     * 
     * @param extraQtys   * Contains extra quantity
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public void setExtraQtys(com.coda.www.efinance.schemas.inputext.ExtraQuantityInfo[] extraQtys) {
        this.extraQtys = extraQtys;
    }


    /**
     * Gets the missingQtys value for this ProblemLine.
     * 
     * @return missingQtys   * Contains details of any
     *                         quantities missing from the problem document
     * line.
     */
    public com.coda.www.efinance.schemas.inputext.MissingQuantityInfo[] getMissingQtys() {
        return missingQtys;
    }


    /**
     * Sets the missingQtys value for this ProblemLine.
     * 
     * @param missingQtys   * Contains details of any
     *                         quantities missing from the problem document
     * line.
     */
    public void setMissingQtys(com.coda.www.efinance.schemas.inputext.MissingQuantityInfo[] missingQtys) {
        this.missingQtys = missingQtys;
    }


    /**
     * Gets the extraTax value for this ProblemLine.
     * 
     * @return extraTax   * Contains extra tax
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] getExtraTax() {
        return extraTax;
    }


    /**
     * Sets the extraTax value for this ProblemLine.
     * 
     * @param extraTax   * Contains extra tax
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public void setExtraTax(com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTax) {
        this.extraTax = extraTax;
    }


    /**
     * Gets the extraTen99 value for this ProblemLine.
     * 
     * @return extraTen99   * Contains extra 1099
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] getExtraTen99() {
        return extraTen99;
    }


    /**
     * Sets the extraTen99 value for this ProblemLine.
     * 
     * @param extraTen99   * Contains extra 1099
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public void setExtraTen99(com.coda.www.efinance.schemas.inputext.ExtraTaxInfo[] extraTen99) {
        this.extraTen99 = extraTen99;
    }


    /**
     * Gets the extraDiscounts value for this ProblemLine.
     * 
     * @return extraDiscounts   * Contains extra discount
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo[] getExtraDiscounts() {
        return extraDiscounts;
    }


    /**
     * Sets the extraDiscounts value for this ProblemLine.
     * 
     * @param extraDiscounts   * Contains extra discount
     *                         information on the problem document line.
     * This
     *                         information is not returned if the PostRequest
     * or CheckPostRequest has ignoresurplusdata set to
     *                     TRUE.
     */
    public void setExtraDiscounts(com.coda.www.efinance.schemas.inputext.ExtraDiscountInfo[] extraDiscounts) {
        this.extraDiscounts = extraDiscounts;
    }


    /**
     * Gets the asset value for this ProblemLine.
     * 
     * @return asset   * This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted.
     */
    public com.coda.www.efinance.schemas.transaction.Asset getAsset() {
        return asset;
    }


    /**
     * Sets the asset value for this ProblemLine.
     * 
     * @param asset   * This element holds details of
     *                         the asset that will be created from this
     *                         document line when the document is
     *                     posted.
     */
    public void setAsset(com.coda.www.efinance.schemas.transaction.Asset asset) {
        this.asset = asset;
    }


    /**
     * Gets the missingTen99 value for this ProblemLine.
     * 
     * @return missingTen99   * Indicates whether 1099
     *                         information is missing from the document
     *                     line.
     */
    public java.lang.Boolean getMissingTen99() {
        return missingTen99;
    }


    /**
     * Sets the missingTen99 value for this ProblemLine.
     * 
     * @param missingTen99   * Indicates whether 1099
     *                         information is missing from the document
     *                     line.
     */
    public void setMissingTen99(java.lang.Boolean missingTen99) {
        this.missingTen99 = missingTen99;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ProblemLine)) return false;
        ProblemLine other = (ProblemLine) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.number == other.getNumber() &&
            ((this.docValue==null && other.getDocValue()==null) || 
             (this.docValue!=null &&
              this.docValue.equals(other.getDocValue()))) &&
            ((this.extraPayData==null && other.getExtraPayData()==null) || 
             (this.extraPayData!=null &&
              this.extraPayData.equals(other.getExtraPayData()))) &&
            ((this.problemPayData==null && other.getProblemPayData()==null) || 
             (this.problemPayData!=null &&
              this.problemPayData.equals(other.getProblemPayData()))) &&
            ((this.extraQtys==null && other.getExtraQtys()==null) || 
             (this.extraQtys!=null &&
              java.util.Arrays.equals(this.extraQtys, other.getExtraQtys()))) &&
            ((this.missingQtys==null && other.getMissingQtys()==null) || 
             (this.missingQtys!=null &&
              java.util.Arrays.equals(this.missingQtys, other.getMissingQtys()))) &&
            ((this.extraTax==null && other.getExtraTax()==null) || 
             (this.extraTax!=null &&
              java.util.Arrays.equals(this.extraTax, other.getExtraTax()))) &&
            ((this.extraTen99==null && other.getExtraTen99()==null) || 
             (this.extraTen99!=null &&
              java.util.Arrays.equals(this.extraTen99, other.getExtraTen99()))) &&
            ((this.extraDiscounts==null && other.getExtraDiscounts()==null) || 
             (this.extraDiscounts!=null &&
              java.util.Arrays.equals(this.extraDiscounts, other.getExtraDiscounts()))) &&
            ((this.asset==null && other.getAsset()==null) || 
             (this.asset!=null &&
              this.asset.equals(other.getAsset()))) &&
            ((this.missingTen99==null && other.getMissingTen99()==null) || 
             (this.missingTen99!=null &&
              this.missingTen99.equals(other.getMissingTen99())));
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
        _hashCode += getNumber();
        if (getDocValue() != null) {
            _hashCode += getDocValue().hashCode();
        }
        if (getExtraPayData() != null) {
            _hashCode += getExtraPayData().hashCode();
        }
        if (getProblemPayData() != null) {
            _hashCode += getProblemPayData().hashCode();
        }
        if (getExtraQtys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtraQtys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtraQtys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getMissingQtys() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMissingQtys());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMissingQtys(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtraTax() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtraTax());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtraTax(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtraTen99() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtraTen99());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtraTen99(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtraDiscounts() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getExtraDiscounts());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getExtraDiscounts(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAsset() != null) {
            _hashCode += getAsset().hashCode();
        }
        if (getMissingTen99() != null) {
            _hashCode += getMissingTen99().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ProblemLine.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemLine"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("number");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Number"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "DocValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraPayData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraPayData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraPayData"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemPayData");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemPayData"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ProblemPay"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraQtys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQtys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraQuantityInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Qty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingQtys");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQtys"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingQuantityInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Qty"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraTax");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTax"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Tax"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraTen99");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTen99"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraTaxInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Ten99"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraDiscounts");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraDiscounts"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "ExtraDiscountInfo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Discount"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("asset");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "Asset"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Asset"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("missingTen99");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputext", "MissingTen99"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
