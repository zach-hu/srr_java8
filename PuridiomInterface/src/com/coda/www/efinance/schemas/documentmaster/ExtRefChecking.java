/**
 * ExtRefChecking.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.documentmaster;


/**
 * This element holds information about
 *                 whether you want to check external references for
 *                 duplicates and, if so, how.
 */
public class ExtRefChecking  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.documentmaster.ExtRefCompare compareWith;

    /* Allows you to restrict the
     *                         document lines which are checked for duplicate
     * external references by account
     *                     code. */
    private java.lang.String accountRule;

    /* Specifies whether, when
     *                         checking for duplicates, the value of the
     *                         current external reference should be compared
     * with external references on lines from all types
     *                         of document or only on lines from the same
     * document master as the current
     *                     line. */
    private boolean allDocumentTypes;

    /* Specifies whether the current
     *                         document is included when checking for
     *                     duplicates. */
    private boolean excludeCurrent;

    /* Specifies whether only
     *                         summary lines are considered when checking
     * for
     *                     duplicates. */
    private boolean summaryLinesOnly;

    /* Specifies the action to take
     *                         when a duplicate external reference is
     *                     found. */
    private java.lang.String actionOnDuplicate;

    public ExtRefChecking() {
    }

    public ExtRefChecking(
           com.coda.www.efinance.schemas.documentmaster.ExtRefCompare compareWith,
           java.lang.String accountRule,
           boolean allDocumentTypes,
           boolean excludeCurrent,
           boolean summaryLinesOnly,
           java.lang.String actionOnDuplicate) {
           this.compareWith = compareWith;
           this.accountRule = accountRule;
           this.allDocumentTypes = allDocumentTypes;
           this.excludeCurrent = excludeCurrent;
           this.summaryLinesOnly = summaryLinesOnly;
           this.actionOnDuplicate = actionOnDuplicate;
    }


    /**
     * Gets the compareWith value for this ExtRefChecking.
     * 
     * @return compareWith
     */
    public com.coda.www.efinance.schemas.documentmaster.ExtRefCompare getCompareWith() {
        return compareWith;
    }


    /**
     * Sets the compareWith value for this ExtRefChecking.
     * 
     * @param compareWith
     */
    public void setCompareWith(com.coda.www.efinance.schemas.documentmaster.ExtRefCompare compareWith) {
        this.compareWith = compareWith;
    }


    /**
     * Gets the accountRule value for this ExtRefChecking.
     * 
     * @return accountRule   * Allows you to restrict the
     *                         document lines which are checked for duplicate
     * external references by account
     *                     code.
     */
    public java.lang.String getAccountRule() {
        return accountRule;
    }


    /**
     * Sets the accountRule value for this ExtRefChecking.
     * 
     * @param accountRule   * Allows you to restrict the
     *                         document lines which are checked for duplicate
     * external references by account
     *                     code.
     */
    public void setAccountRule(java.lang.String accountRule) {
        this.accountRule = accountRule;
    }


    /**
     * Gets the allDocumentTypes value for this ExtRefChecking.
     * 
     * @return allDocumentTypes   * Specifies whether, when
     *                         checking for duplicates, the value of the
     *                         current external reference should be compared
     * with external references on lines from all types
     *                         of document or only on lines from the same
     * document master as the current
     *                     line.
     */
    public boolean isAllDocumentTypes() {
        return allDocumentTypes;
    }


    /**
     * Sets the allDocumentTypes value for this ExtRefChecking.
     * 
     * @param allDocumentTypes   * Specifies whether, when
     *                         checking for duplicates, the value of the
     *                         current external reference should be compared
     * with external references on lines from all types
     *                         of document or only on lines from the same
     * document master as the current
     *                     line.
     */
    public void setAllDocumentTypes(boolean allDocumentTypes) {
        this.allDocumentTypes = allDocumentTypes;
    }


    /**
     * Gets the excludeCurrent value for this ExtRefChecking.
     * 
     * @return excludeCurrent   * Specifies whether the current
     *                         document is included when checking for
     *                     duplicates.
     */
    public boolean isExcludeCurrent() {
        return excludeCurrent;
    }


    /**
     * Sets the excludeCurrent value for this ExtRefChecking.
     * 
     * @param excludeCurrent   * Specifies whether the current
     *                         document is included when checking for
     *                     duplicates.
     */
    public void setExcludeCurrent(boolean excludeCurrent) {
        this.excludeCurrent = excludeCurrent;
    }


    /**
     * Gets the summaryLinesOnly value for this ExtRefChecking.
     * 
     * @return summaryLinesOnly   * Specifies whether only
     *                         summary lines are considered when checking
     * for
     *                     duplicates.
     */
    public boolean isSummaryLinesOnly() {
        return summaryLinesOnly;
    }


    /**
     * Sets the summaryLinesOnly value for this ExtRefChecking.
     * 
     * @param summaryLinesOnly   * Specifies whether only
     *                         summary lines are considered when checking
     * for
     *                     duplicates.
     */
    public void setSummaryLinesOnly(boolean summaryLinesOnly) {
        this.summaryLinesOnly = summaryLinesOnly;
    }


    /**
     * Gets the actionOnDuplicate value for this ExtRefChecking.
     * 
     * @return actionOnDuplicate   * Specifies the action to take
     *                         when a duplicate external reference is
     *                     found.
     */
    public java.lang.String getActionOnDuplicate() {
        return actionOnDuplicate;
    }


    /**
     * Sets the actionOnDuplicate value for this ExtRefChecking.
     * 
     * @param actionOnDuplicate   * Specifies the action to take
     *                         when a duplicate external reference is
     *                     found.
     */
    public void setActionOnDuplicate(java.lang.String actionOnDuplicate) {
        this.actionOnDuplicate = actionOnDuplicate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtRefChecking)) return false;
        ExtRefChecking other = (ExtRefChecking) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.compareWith==null && other.getCompareWith()==null) || 
             (this.compareWith!=null &&
              this.compareWith.equals(other.getCompareWith()))) &&
            ((this.accountRule==null && other.getAccountRule()==null) || 
             (this.accountRule!=null &&
              this.accountRule.equals(other.getAccountRule()))) &&
            this.allDocumentTypes == other.isAllDocumentTypes() &&
            this.excludeCurrent == other.isExcludeCurrent() &&
            this.summaryLinesOnly == other.isSummaryLinesOnly() &&
            ((this.actionOnDuplicate==null && other.getActionOnDuplicate()==null) || 
             (this.actionOnDuplicate!=null &&
              this.actionOnDuplicate.equals(other.getActionOnDuplicate())));
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
        if (getCompareWith() != null) {
            _hashCode += getCompareWith().hashCode();
        }
        if (getAccountRule() != null) {
            _hashCode += getAccountRule().hashCode();
        }
        _hashCode += (isAllDocumentTypes() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isExcludeCurrent() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isSummaryLinesOnly() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getActionOnDuplicate() != null) {
            _hashCode += getActionOnDuplicate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtRefChecking.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefChecking"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("compareWith");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "CompareWith"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExtRefCompare"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accountRule");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AccountRule"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allDocumentTypes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "AllDocumentTypes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("excludeCurrent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ExcludeCurrent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summaryLinesOnly");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "SummaryLinesOnly"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actionOnDuplicate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/documentmaster", "ActionOnDuplicate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseEnum"));
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
