/**
 * Master.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.inputtemplate;

public abstract class Master  implements java.io.Serializable {
    /* The TimeStamp value for this
     *                         input template master in the
     *                     database. */
    private short timeStamp;

    /* The
     *                         code for the company in which the input template
     * master is being maintained. */
    private java.lang.String cmpCode;

    /* The
     *                         code for this input template
     *                     master. */
    private java.lang.String code;

    /* The
     *                         full name for the input
     *                     template. */
    private java.lang.String name;

    /* The abbreviated name for the
     *                         input template. */
    private java.lang.String shortName;

    /* Indicates whether use of the
     *                         Resolve button is mandatory when using this
     * template for Input. */
    private java.lang.Boolean resolveOptional;

    /* Indicates whether account
     *                         codes should be validated using the extension.
     * When TRUE, the extension is invoked when the
     *                         user posts the document. */
    private java.lang.Boolean useElementExtension;

    /* The date when this input
     *                         template master was created. */
    private java.util.Calendar createDate;

    /* The date when this input
     *                         template master was last
     *                     modified. */
    private java.util.Calendar modifyDate;

    /* The user who last modified
     *                         this input template master (or the user who
     * created this input template master if no modify
     *                         date exists). */
    private java.lang.String user;

    /* Indicates whether the
     *                         document description value is copied to the
     * line
     *                         description for any defined and additional
     * lines
     *                         that do not have default line description
     *                     values. */
    private java.lang.Boolean copyHeaderDescription;

    /* The index for the next
     *                         additional line item. */
    private short nextAdditionalLineIndex;

    /* The index for the next
     *                         balancing line item. */
    private short nextBalanceLineIndex;

    /* The
     *                         index for the next footer
     *                     item. */
    private short nextFooterIndex;

    /* The index for the next header
     *                     item. */
    private short nextHeaderIndex;

    /* The
     *                         index for the next generated tax line
     *                     item. */
    private short nextGeneratedLineIndex;

    /* Indicates that this input
     *                         template creates forms with the new tabular
     * layout. */
    private java.lang.Boolean useNeonFormLayout;

    /* The
     *                         maximum number of fields per row on an Input
     * form. */
    private java.lang.Integer generatedFieldsPerRow;

    /* Specifies whether you want to
     *                         validate the account code on tabbing out.
     * This
     *                         is available only if UseNeonFormLayout above
     * is
     *                     TRUE. */
    private boolean validateAccountOnTabOut;

    /* Specifies that the account
     *                         code on the first line of a document provides
     * a
     *                         default for additional
     *                     lines. */
    private boolean defaultAccountFromPrevious;

    public Master() {
    }

    public Master(
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
           boolean defaultAccountFromPrevious) {
           this.timeStamp = timeStamp;
           this.cmpCode = cmpCode;
           this.code = code;
           this.name = name;
           this.shortName = shortName;
           this.resolveOptional = resolveOptional;
           this.useElementExtension = useElementExtension;
           this.createDate = createDate;
           this.modifyDate = modifyDate;
           this.user = user;
           this.copyHeaderDescription = copyHeaderDescription;
           this.nextAdditionalLineIndex = nextAdditionalLineIndex;
           this.nextBalanceLineIndex = nextBalanceLineIndex;
           this.nextFooterIndex = nextFooterIndex;
           this.nextHeaderIndex = nextHeaderIndex;
           this.nextGeneratedLineIndex = nextGeneratedLineIndex;
           this.useNeonFormLayout = useNeonFormLayout;
           this.generatedFieldsPerRow = generatedFieldsPerRow;
           this.validateAccountOnTabOut = validateAccountOnTabOut;
           this.defaultAccountFromPrevious = defaultAccountFromPrevious;
    }


    /**
     * Gets the timeStamp value for this Master.
     * 
     * @return timeStamp   * The TimeStamp value for this
     *                         input template master in the
     *                     database.
     */
    public short getTimeStamp() {
        return timeStamp;
    }


    /**
     * Sets the timeStamp value for this Master.
     * 
     * @param timeStamp   * The TimeStamp value for this
     *                         input template master in the
     *                     database.
     */
    public void setTimeStamp(short timeStamp) {
        this.timeStamp = timeStamp;
    }


    /**
     * Gets the cmpCode value for this Master.
     * 
     * @return cmpCode   * The
     *                         code for the company in which the input template
     * master is being maintained.
     */
    public java.lang.String getCmpCode() {
        return cmpCode;
    }


    /**
     * Sets the cmpCode value for this Master.
     * 
     * @param cmpCode   * The
     *                         code for the company in which the input template
     * master is being maintained.
     */
    public void setCmpCode(java.lang.String cmpCode) {
        this.cmpCode = cmpCode;
    }


    /**
     * Gets the code value for this Master.
     * 
     * @return code   * The
     *                         code for this input template
     *                     master.
     */
    public java.lang.String getCode() {
        return code;
    }


    /**
     * Sets the code value for this Master.
     * 
     * @param code   * The
     *                         code for this input template
     *                     master.
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }


    /**
     * Gets the name value for this Master.
     * 
     * @return name   * The
     *                         full name for the input
     *                     template.
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this Master.
     * 
     * @param name   * The
     *                         full name for the input
     *                     template.
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the shortName value for this Master.
     * 
     * @return shortName   * The abbreviated name for the
     *                         input template.
     */
    public java.lang.String getShortName() {
        return shortName;
    }


    /**
     * Sets the shortName value for this Master.
     * 
     * @param shortName   * The abbreviated name for the
     *                         input template.
     */
    public void setShortName(java.lang.String shortName) {
        this.shortName = shortName;
    }


    /**
     * Gets the resolveOptional value for this Master.
     * 
     * @return resolveOptional   * Indicates whether use of the
     *                         Resolve button is mandatory when using this
     * template for Input.
     */
    public java.lang.Boolean getResolveOptional() {
        return resolveOptional;
    }


    /**
     * Sets the resolveOptional value for this Master.
     * 
     * @param resolveOptional   * Indicates whether use of the
     *                         Resolve button is mandatory when using this
     * template for Input.
     */
    public void setResolveOptional(java.lang.Boolean resolveOptional) {
        this.resolveOptional = resolveOptional;
    }


    /**
     * Gets the useElementExtension value for this Master.
     * 
     * @return useElementExtension   * Indicates whether account
     *                         codes should be validated using the extension.
     * When TRUE, the extension is invoked when the
     *                         user posts the document.
     */
    public java.lang.Boolean getUseElementExtension() {
        return useElementExtension;
    }


    /**
     * Sets the useElementExtension value for this Master.
     * 
     * @param useElementExtension   * Indicates whether account
     *                         codes should be validated using the extension.
     * When TRUE, the extension is invoked when the
     *                         user posts the document.
     */
    public void setUseElementExtension(java.lang.Boolean useElementExtension) {
        this.useElementExtension = useElementExtension;
    }


    /**
     * Gets the createDate value for this Master.
     * 
     * @return createDate   * The date when this input
     *                         template master was created.
     */
    public java.util.Calendar getCreateDate() {
        return createDate;
    }


    /**
     * Sets the createDate value for this Master.
     * 
     * @param createDate   * The date when this input
     *                         template master was created.
     */
    public void setCreateDate(java.util.Calendar createDate) {
        this.createDate = createDate;
    }


    /**
     * Gets the modifyDate value for this Master.
     * 
     * @return modifyDate   * The date when this input
     *                         template master was last
     *                     modified.
     */
    public java.util.Calendar getModifyDate() {
        return modifyDate;
    }


    /**
     * Sets the modifyDate value for this Master.
     * 
     * @param modifyDate   * The date when this input
     *                         template master was last
     *                     modified.
     */
    public void setModifyDate(java.util.Calendar modifyDate) {
        this.modifyDate = modifyDate;
    }


    /**
     * Gets the user value for this Master.
     * 
     * @return user   * The user who last modified
     *                         this input template master (or the user who
     * created this input template master if no modify
     *                         date exists).
     */
    public java.lang.String getUser() {
        return user;
    }


    /**
     * Sets the user value for this Master.
     * 
     * @param user   * The user who last modified
     *                         this input template master (or the user who
     * created this input template master if no modify
     *                         date exists).
     */
    public void setUser(java.lang.String user) {
        this.user = user;
    }


    /**
     * Gets the copyHeaderDescription value for this Master.
     * 
     * @return copyHeaderDescription   * Indicates whether the
     *                         document description value is copied to the
     * line
     *                         description for any defined and additional
     * lines
     *                         that do not have default line description
     *                     values.
     */
    public java.lang.Boolean getCopyHeaderDescription() {
        return copyHeaderDescription;
    }


    /**
     * Sets the copyHeaderDescription value for this Master.
     * 
     * @param copyHeaderDescription   * Indicates whether the
     *                         document description value is copied to the
     * line
     *                         description for any defined and additional
     * lines
     *                         that do not have default line description
     *                     values.
     */
    public void setCopyHeaderDescription(java.lang.Boolean copyHeaderDescription) {
        this.copyHeaderDescription = copyHeaderDescription;
    }


    /**
     * Gets the nextAdditionalLineIndex value for this Master.
     * 
     * @return nextAdditionalLineIndex   * The index for the next
     *                         additional line item.
     */
    public short getNextAdditionalLineIndex() {
        return nextAdditionalLineIndex;
    }


    /**
     * Sets the nextAdditionalLineIndex value for this Master.
     * 
     * @param nextAdditionalLineIndex   * The index for the next
     *                         additional line item.
     */
    public void setNextAdditionalLineIndex(short nextAdditionalLineIndex) {
        this.nextAdditionalLineIndex = nextAdditionalLineIndex;
    }


    /**
     * Gets the nextBalanceLineIndex value for this Master.
     * 
     * @return nextBalanceLineIndex   * The index for the next
     *                         balancing line item.
     */
    public short getNextBalanceLineIndex() {
        return nextBalanceLineIndex;
    }


    /**
     * Sets the nextBalanceLineIndex value for this Master.
     * 
     * @param nextBalanceLineIndex   * The index for the next
     *                         balancing line item.
     */
    public void setNextBalanceLineIndex(short nextBalanceLineIndex) {
        this.nextBalanceLineIndex = nextBalanceLineIndex;
    }


    /**
     * Gets the nextFooterIndex value for this Master.
     * 
     * @return nextFooterIndex   * The
     *                         index for the next footer
     *                     item.
     */
    public short getNextFooterIndex() {
        return nextFooterIndex;
    }


    /**
     * Sets the nextFooterIndex value for this Master.
     * 
     * @param nextFooterIndex   * The
     *                         index for the next footer
     *                     item.
     */
    public void setNextFooterIndex(short nextFooterIndex) {
        this.nextFooterIndex = nextFooterIndex;
    }


    /**
     * Gets the nextHeaderIndex value for this Master.
     * 
     * @return nextHeaderIndex   * The index for the next header
     *                     item.
     */
    public short getNextHeaderIndex() {
        return nextHeaderIndex;
    }


    /**
     * Sets the nextHeaderIndex value for this Master.
     * 
     * @param nextHeaderIndex   * The index for the next header
     *                     item.
     */
    public void setNextHeaderIndex(short nextHeaderIndex) {
        this.nextHeaderIndex = nextHeaderIndex;
    }


    /**
     * Gets the nextGeneratedLineIndex value for this Master.
     * 
     * @return nextGeneratedLineIndex   * The
     *                         index for the next generated tax line
     *                     item.
     */
    public short getNextGeneratedLineIndex() {
        return nextGeneratedLineIndex;
    }


    /**
     * Sets the nextGeneratedLineIndex value for this Master.
     * 
     * @param nextGeneratedLineIndex   * The
     *                         index for the next generated tax line
     *                     item.
     */
    public void setNextGeneratedLineIndex(short nextGeneratedLineIndex) {
        this.nextGeneratedLineIndex = nextGeneratedLineIndex;
    }


    /**
     * Gets the useNeonFormLayout value for this Master.
     * 
     * @return useNeonFormLayout   * Indicates that this input
     *                         template creates forms with the new tabular
     * layout.
     */
    public java.lang.Boolean getUseNeonFormLayout() {
        return useNeonFormLayout;
    }


    /**
     * Sets the useNeonFormLayout value for this Master.
     * 
     * @param useNeonFormLayout   * Indicates that this input
     *                         template creates forms with the new tabular
     * layout.
     */
    public void setUseNeonFormLayout(java.lang.Boolean useNeonFormLayout) {
        this.useNeonFormLayout = useNeonFormLayout;
    }


    /**
     * Gets the generatedFieldsPerRow value for this Master.
     * 
     * @return generatedFieldsPerRow   * The
     *                         maximum number of fields per row on an Input
     * form.
     */
    public java.lang.Integer getGeneratedFieldsPerRow() {
        return generatedFieldsPerRow;
    }


    /**
     * Sets the generatedFieldsPerRow value for this Master.
     * 
     * @param generatedFieldsPerRow   * The
     *                         maximum number of fields per row on an Input
     * form.
     */
    public void setGeneratedFieldsPerRow(java.lang.Integer generatedFieldsPerRow) {
        this.generatedFieldsPerRow = generatedFieldsPerRow;
    }


    /**
     * Gets the validateAccountOnTabOut value for this Master.
     * 
     * @return validateAccountOnTabOut   * Specifies whether you want to
     *                         validate the account code on tabbing out.
     * This
     *                         is available only if UseNeonFormLayout above
     * is
     *                     TRUE.
     */
    public boolean isValidateAccountOnTabOut() {
        return validateAccountOnTabOut;
    }


    /**
     * Sets the validateAccountOnTabOut value for this Master.
     * 
     * @param validateAccountOnTabOut   * Specifies whether you want to
     *                         validate the account code on tabbing out.
     * This
     *                         is available only if UseNeonFormLayout above
     * is
     *                     TRUE.
     */
    public void setValidateAccountOnTabOut(boolean validateAccountOnTabOut) {
        this.validateAccountOnTabOut = validateAccountOnTabOut;
    }


    /**
     * Gets the defaultAccountFromPrevious value for this Master.
     * 
     * @return defaultAccountFromPrevious   * Specifies that the account
     *                         code on the first line of a document provides
     * a
     *                         default for additional
     *                     lines.
     */
    public boolean isDefaultAccountFromPrevious() {
        return defaultAccountFromPrevious;
    }


    /**
     * Sets the defaultAccountFromPrevious value for this Master.
     * 
     * @param defaultAccountFromPrevious   * Specifies that the account
     *                         code on the first line of a document provides
     * a
     *                         default for additional
     *                     lines.
     */
    public void setDefaultAccountFromPrevious(boolean defaultAccountFromPrevious) {
        this.defaultAccountFromPrevious = defaultAccountFromPrevious;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Master)) return false;
        Master other = (Master) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.timeStamp == other.getTimeStamp() &&
            ((this.cmpCode==null && other.getCmpCode()==null) || 
             (this.cmpCode!=null &&
              this.cmpCode.equals(other.getCmpCode()))) &&
            ((this.code==null && other.getCode()==null) || 
             (this.code!=null &&
              this.code.equals(other.getCode()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.shortName==null && other.getShortName()==null) || 
             (this.shortName!=null &&
              this.shortName.equals(other.getShortName()))) &&
            ((this.resolveOptional==null && other.getResolveOptional()==null) || 
             (this.resolveOptional!=null &&
              this.resolveOptional.equals(other.getResolveOptional()))) &&
            ((this.useElementExtension==null && other.getUseElementExtension()==null) || 
             (this.useElementExtension!=null &&
              this.useElementExtension.equals(other.getUseElementExtension()))) &&
            ((this.createDate==null && other.getCreateDate()==null) || 
             (this.createDate!=null &&
              this.createDate.equals(other.getCreateDate()))) &&
            ((this.modifyDate==null && other.getModifyDate()==null) || 
             (this.modifyDate!=null &&
              this.modifyDate.equals(other.getModifyDate()))) &&
            ((this.user==null && other.getUser()==null) || 
             (this.user!=null &&
              this.user.equals(other.getUser()))) &&
            ((this.copyHeaderDescription==null && other.getCopyHeaderDescription()==null) || 
             (this.copyHeaderDescription!=null &&
              this.copyHeaderDescription.equals(other.getCopyHeaderDescription()))) &&
            this.nextAdditionalLineIndex == other.getNextAdditionalLineIndex() &&
            this.nextBalanceLineIndex == other.getNextBalanceLineIndex() &&
            this.nextFooterIndex == other.getNextFooterIndex() &&
            this.nextHeaderIndex == other.getNextHeaderIndex() &&
            this.nextGeneratedLineIndex == other.getNextGeneratedLineIndex() &&
            ((this.useNeonFormLayout==null && other.getUseNeonFormLayout()==null) || 
             (this.useNeonFormLayout!=null &&
              this.useNeonFormLayout.equals(other.getUseNeonFormLayout()))) &&
            ((this.generatedFieldsPerRow==null && other.getGeneratedFieldsPerRow()==null) || 
             (this.generatedFieldsPerRow!=null &&
              this.generatedFieldsPerRow.equals(other.getGeneratedFieldsPerRow()))) &&
            this.validateAccountOnTabOut == other.isValidateAccountOnTabOut() &&
            this.defaultAccountFromPrevious == other.isDefaultAccountFromPrevious();
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
        _hashCode += getTimeStamp();
        if (getCmpCode() != null) {
            _hashCode += getCmpCode().hashCode();
        }
        if (getCode() != null) {
            _hashCode += getCode().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getShortName() != null) {
            _hashCode += getShortName().hashCode();
        }
        if (getResolveOptional() != null) {
            _hashCode += getResolveOptional().hashCode();
        }
        if (getUseElementExtension() != null) {
            _hashCode += getUseElementExtension().hashCode();
        }
        if (getCreateDate() != null) {
            _hashCode += getCreateDate().hashCode();
        }
        if (getModifyDate() != null) {
            _hashCode += getModifyDate().hashCode();
        }
        if (getUser() != null) {
            _hashCode += getUser().hashCode();
        }
        if (getCopyHeaderDescription() != null) {
            _hashCode += getCopyHeaderDescription().hashCode();
        }
        _hashCode += getNextAdditionalLineIndex();
        _hashCode += getNextBalanceLineIndex();
        _hashCode += getNextFooterIndex();
        _hashCode += getNextHeaderIndex();
        _hashCode += getNextGeneratedLineIndex();
        if (getUseNeonFormLayout() != null) {
            _hashCode += getUseNeonFormLayout().hashCode();
        }
        if (getGeneratedFieldsPerRow() != null) {
            _hashCode += getGeneratedFieldsPerRow().hashCode();
        }
        _hashCode += (isValidateAccountOnTabOut() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += (isDefaultAccountFromPrevious() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Master.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Master"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "TimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cmpCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "CmpCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("code");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Code"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shortName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ShortName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("resolveOptional");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ResolveOptional"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useElementExtension");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "UseElementExtension"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "CreateDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modifyDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ModifyDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("user");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "User"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("copyHeaderDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "CopyHeaderDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextAdditionalLineIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextAdditionalLineIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextBalanceLineIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextBalanceLineIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextFooterIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextFooterIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextHeaderIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextHeaderIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nextGeneratedLineIndex");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "NextGeneratedLineIndex"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "short"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("useNeonFormLayout");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "UseNeonFormLayout"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("generatedFieldsPerRow");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "GeneratedFieldsPerRow"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validateAccountOnTabOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "ValidateAccountOnTabOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("defaultAccountFromPrevious");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/inputtemplate", "DefaultAccountFromPrevious"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
