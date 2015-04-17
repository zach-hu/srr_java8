/**
 * InputTemplate.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;


/**
 * This element holds an input template
 *             master.
 */
public class InputTemplate  extends com.coda.www.efinance.schemas.inputtemplate.Master  implements java.io.Serializable {
    private com.coda.www.efinance.schemas.inputtemplate.HeaderItem[] header;

    /* This element holds
     *                                 details of the defined lines that
     * have
     *                                 been set up on the
     *                             template. */
    private com.coda.www.efinance.schemas.inputtemplate.DefinedLine[] definedLines;

    /* This element holds
     *                                 details of the additional line fields
     * that have been included in the
     *                             template. */
    private com.coda.www.efinance.schemas.inputtemplate.LineItem[] additionalLines;

    /* This element holds
     *                                 details of the generated tax lines
     * that
     *                                 have been included in the
     *                             template. */
    private com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] generatedTaxLines;

    /* This element holds
     *                                 details of the generated balancing
     * lines
     *                                 that have been included in the
     *                             template. */
    private com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] balancingLines;

    /* This element holds
     *                                 details of the document footer fields
     * that have been included in the
     *                             template. */
    private com.coda.www.efinance.schemas.inputtemplate.FooterItem[] footer;

    /* The list of document
     *                                 masters to which this input template
     * master is linked. */
    private java.lang.String[] docCodes;

    public InputTemplate() {
    }

    public InputTemplate(
           short timeStamp,
           java.lang.String cmpCode,
           java.lang.String code,
           java.lang.String name,
           java.lang.String shortName,
           java.lang.Boolean resolveOptional,
           java.lang.Boolean useElementExtension,
           java.util.Calendar createDate,
           java.util.Calendar modifyDate,
           java.lang.String user,
           java.lang.Boolean copyHeaderDescription,
           short nextAdditionalLineIndex,
           short nextBalanceLineIndex,
           short nextFooterIndex,
           short nextHeaderIndex,
           short nextGeneratedLineIndex,
           java.lang.Boolean useNeonFormLayout,
           java.lang.Integer generatedFieldsPerRow,
           boolean validateAccountOnTabOut,
           boolean defaultAccountFromPrevious,
           com.coda.www.efinance.schemas.inputtemplate.HeaderItem[] header,
           com.coda.www.efinance.schemas.inputtemplate.DefinedLine[] definedLines,
           com.coda.www.efinance.schemas.inputtemplate.LineItem[] additionalLines,
           com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] generatedTaxLines,
           com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] balancingLines,
           com.coda.www.efinance.schemas.inputtemplate.FooterItem[] footer,
           java.lang.String[] docCodes) {
        super(
            timeStamp,
            cmpCode,
            code,
            name,
            shortName,
            resolveOptional,
            useElementExtension,
            createDate,
            modifyDate,
            user,
            copyHeaderDescription,
            nextAdditionalLineIndex,
            nextBalanceLineIndex,
            nextFooterIndex,
            nextHeaderIndex,
            nextGeneratedLineIndex,
            useNeonFormLayout,
            generatedFieldsPerRow,
            validateAccountOnTabOut,
            defaultAccountFromPrevious);
        this.header = header;
        this.definedLines = definedLines;
        this.additionalLines = additionalLines;
        this.generatedTaxLines = generatedTaxLines;
        this.balancingLines = balancingLines;
        this.footer = footer;
        this.docCodes = docCodes;
    }


    /**
     * Gets the header value for this InputTemplate.
     * 
     * @return header
     */
    public com.coda.www.efinance.schemas.inputtemplate.HeaderItem[] getHeader() {
        return header;
    }


    /**
     * Sets the header value for this InputTemplate.
     * 
     * @param header
     */
    public void setHeader(com.coda.www.efinance.schemas.inputtemplate.HeaderItem[] header) {
        this.header = header;
    }


    /**
     * Gets the definedLines value for this InputTemplate.
     * 
     * @return definedLines   * This element holds
     *                                 details of the defined lines that
     * have
     *                                 been set up on the
     *                             template.
     */
    public com.coda.www.efinance.schemas.inputtemplate.DefinedLine[] getDefinedLines() {
        return definedLines;
    }


    /**
     * Sets the definedLines value for this InputTemplate.
     * 
     * @param definedLines   * This element holds
     *                                 details of the defined lines that
     * have
     *                                 been set up on the
     *                             template.
     */
    public void setDefinedLines(com.coda.www.efinance.schemas.inputtemplate.DefinedLine[] definedLines) {
        this.definedLines = definedLines;
    }


    /**
     * Gets the additionalLines value for this InputTemplate.
     * 
     * @return additionalLines   * This element holds
     *                                 details of the additional line fields
     * that have been included in the
     *                             template.
     */
    public com.coda.www.efinance.schemas.inputtemplate.LineItem[] getAdditionalLines() {
        return additionalLines;
    }


    /**
     * Sets the additionalLines value for this InputTemplate.
     * 
     * @param additionalLines   * This element holds
     *                                 details of the additional line fields
     * that have been included in the
     *                             template.
     */
    public void setAdditionalLines(com.coda.www.efinance.schemas.inputtemplate.LineItem[] additionalLines) {
        this.additionalLines = additionalLines;
    }


    /**
     * Gets the generatedTaxLines value for this InputTemplate.
     * 
     * @return generatedTaxLines   * This element holds
     *                                 details of the generated tax lines
     * that
     *                                 have been included in the
     *                             template.
     */
    public com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] getGeneratedTaxLines() {
        return generatedTaxLines;
    }


    /**
     * Sets the generatedTaxLines value for this InputTemplate.
     * 
     * @param generatedTaxLines   * This element holds
     *                                 details of the generated tax lines
     * that
     *                                 have been included in the
     *                             template.
     */
    public void setGeneratedTaxLines(com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] generatedTaxLines) {
        this.generatedTaxLines = generatedTaxLines;
    }


    /**
     * Gets the balancingLines value for this InputTemplate.
     * 
     * @return balancingLines   * This element holds
     *                                 details of the generated balancing
     * lines
     *                                 that have been included in the
     *                             template.
     */
    public com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] getBalancingLines() {
        return balancingLines;
    }


    /**
     * Sets the balancingLines value for this InputTemplate.
     * 
     * @param balancingLines   * This element holds
     *                                 details of the generated balancing
     * lines
     *                                 that have been included in the
     *                             template.
     */
    public void setBalancingLines(com.coda.www.efinance.schemas.inputtemplate.GeneratedLineItem[] balancingLines) {
        this.balancingLines = balancingLines;
    }


    /**
     * Gets the footer value for this InputTemplate.
     * 
     * @return footer   * This element holds
     *                                 details of the document footer fields
     * that have been included in the
     *                             template.
     */
    public com.coda.www.efinance.schemas.inputtemplate.FooterItem[] getFooter() {
        return footer;
    }


    /**
     * Sets the footer value for this InputTemplate.
     * 
     * @param footer   * This element holds
     *                                 details of the document footer fields
     * that have been included in the
     *                             template.
     */
    public void setFooter(com.coda.www.efinance.schemas.inputtemplate.FooterItem[] footer) {
        this.footer = footer;
    }


    /**
     * Gets the docCodes value for this InputTemplate.
     * 
     * @return docCodes   * The list of document
     *                                 masters to which this input template
     * master is linked.
     */
    public java.lang.String[] getDocCodes() {
        return docCodes;
    }


    /**
     * Sets the docCodes value for this InputTemplate.
     * 
     * @param docCodes   * The list of document
     *                                 masters to which this input template
     * master is linked.
     */
    public void setDocCodes(java.lang.String[] docCodes) {
        this.docCodes = docCodes;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InputTemplate)) return false;
        InputTemplate other = (InputTemplate) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.header==null && other.getHeader()==null) || 
             (this.header!=null &&
              java.util.Arrays.equals(this.header, other.getHeader()))) &&
            ((this.definedLines==null && other.getDefinedLines()==null) || 
             (this.definedLines!=null &&
              java.util.Arrays.equals(this.definedLines, other.getDefinedLines()))) &&
            ((this.additionalLines==null && other.getAdditionalLines()==null) || 
             (this.additionalLines!=null &&
              java.util.Arrays.equals(this.additionalLines, other.getAdditionalLines()))) &&
            ((this.generatedTaxLines==null && other.getGeneratedTaxLines()==null) || 
             (this.generatedTaxLines!=null &&
              java.util.Arrays.equals(this.generatedTaxLines, other.getGeneratedTaxLines()))) &&
            ((this.balancingLines==null && other.getBalancingLines()==null) || 
             (this.balancingLines!=null &&
              java.util.Arrays.equals(this.balancingLines, other.getBalancingLines()))) &&
            ((this.footer==null && other.getFooter()==null) || 
             (this.footer!=null &&
              java.util.Arrays.equals(this.footer, other.getFooter()))) &&
            ((this.docCodes==null && other.getDocCodes()==null) || 
             (this.docCodes!=null &&
              java.util.Arrays.equals(this.docCodes, other.getDocCodes())));
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
        if (getHeader() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getHeader());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getHeader(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDefinedLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDefinedLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDefinedLines(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditionalLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalLines(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getGeneratedTaxLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGeneratedTaxLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGeneratedTaxLines(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getBalancingLines() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBalancingLines());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBalancingLines(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getFooter() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFooter());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFooter(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocCodes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocCodes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocCodes(), i);
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
        new org.apache.axis.description.TypeDesc(InputTemplate.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "InputTemplate"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("header");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Header"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "HeaderItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("definedLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefinedLine"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Line"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "AdditionalLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "LineItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generatedTaxLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedTaxLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("balancingLines");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "BalancingLines"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedLineItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("footer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Footer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "FooterItem"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Item"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("docCodes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCodes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeGeneralCode"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DocCode"));
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
