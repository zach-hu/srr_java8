/**
 * Asset.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.coda.www.efinance.schemas.transaction;


/**
 * This
 *                 element holds details of the asset that will be created
 * from a document line when the document is
 *             posted.
 */
public class Asset  implements java.io.Serializable {
    private java.lang.String categoryCode;

    /* The
     *                         code of the asset. */
    private java.lang.String assetCode;

    /* The
     *                         code of the parent asset (if
     *                     any). */
    private java.lang.String parentAssetCode;

    /* Indicates whether the asset
     *                         is posted directly to the Asset Register,
     *                         resulting in the asset being capitalised
     *                     immediately. */
    private java.lang.Boolean createOnRegister;

    /* Indicates whether the asset
     *                         inherits the parent asset's remaining asset
     * life. When TRUE, the child asset will share the
     *                         same depreciation end date as the parent
     *                     asset. */
    private java.lang.Boolean inheritRemainingLife;

    /* Indicates whether the asset
     *                         inherits the parent asset's cost
     *                     centre. */
    private java.lang.Boolean inheritCostCentre;

    /* Indicates whether the asset
     *                         inherits the parent asset's
     *                     location. */
    private java.lang.Boolean inheritLocation;

    /* Indicates whether the asset
     *                         is an addition to an asset held on the Asset
     * Intray, or a late adjustment to an asset held on
     *                         the Asset Register. */
    private java.lang.Boolean addition;

    /* When
     *                         creating a late adjustment, the existing asset
     * may have already been depreciation. Set this to
     *                         TRUE to calculate and post the depreciation
     * that
     *                         has accrued for the
     *                     adjustment. */
    private java.lang.Boolean calculateDepreciation;

    /* When TRUE, only one asset is
     *                         created for all the asset lines that are posted
     * in the document. When FALSE, one asset is
     *                         created per line. */
    private java.lang.Boolean documentWide;

    /* The
     *                         date when the asset will be
     *                     capitalised. */
    private java.util.Calendar capitalisationDate;

    /* The
     *                         year and period when the asset will be
     *                     capitalised. */
    private java.lang.String capitalisationPeriodForClientDisplay;

    /* The
     *                         date when depreciation of the asset will start.
     * If the asset category is set up to allow
     *                         depreciation to start before capitalisation,
     * then the depreciation start date can be earlier
     *                         than the asset's capitalisation period.
     *                         DepreciationStartDate is not relevant if
     *                         Addition is TRUE. */
    private java.util.Calendar depreciationStartDate;

    /* The
     *                         year and period when the asset will start
     * to
     *                     depreciate. */
    private java.lang.String depreciationStartPeriodForClientDisplay;

    public Asset() {
    }

    public Asset(
           java.lang.String categoryCode,
           java.lang.String assetCode,
           java.lang.String parentAssetCode,
           java.lang.Boolean createOnRegister,
           java.lang.Boolean inheritRemainingLife,
           java.lang.Boolean inheritCostCentre,
           java.lang.Boolean inheritLocation,
           java.lang.Boolean addition,
           java.lang.Boolean calculateDepreciation,
           java.lang.Boolean documentWide,
           java.util.Calendar capitalisationDate,
           java.lang.String capitalisationPeriodForClientDisplay,
           java.util.Calendar depreciationStartDate,
           java.lang.String depreciationStartPeriodForClientDisplay) {
           this.categoryCode = categoryCode;
           this.assetCode = assetCode;
           this.parentAssetCode = parentAssetCode;
           this.createOnRegister = createOnRegister;
           this.inheritRemainingLife = inheritRemainingLife;
           this.inheritCostCentre = inheritCostCentre;
           this.inheritLocation = inheritLocation;
           this.addition = addition;
           this.calculateDepreciation = calculateDepreciation;
           this.documentWide = documentWide;
           this.capitalisationDate = capitalisationDate;
           this.capitalisationPeriodForClientDisplay = capitalisationPeriodForClientDisplay;
           this.depreciationStartDate = depreciationStartDate;
           this.depreciationStartPeriodForClientDisplay = depreciationStartPeriodForClientDisplay;
    }


    /**
     * Gets the categoryCode value for this Asset.
     * 
     * @return categoryCode
     */
    public java.lang.String getCategoryCode() {
        return categoryCode;
    }


    /**
     * Sets the categoryCode value for this Asset.
     * 
     * @param categoryCode
     */
    public void setCategoryCode(java.lang.String categoryCode) {
        this.categoryCode = categoryCode;
    }


    /**
     * Gets the assetCode value for this Asset.
     * 
     * @return assetCode   * The
     *                         code of the asset.
     */
    public java.lang.String getAssetCode() {
        return assetCode;
    }


    /**
     * Sets the assetCode value for this Asset.
     * 
     * @param assetCode   * The
     *                         code of the asset.
     */
    public void setAssetCode(java.lang.String assetCode) {
        this.assetCode = assetCode;
    }


    /**
     * Gets the parentAssetCode value for this Asset.
     * 
     * @return parentAssetCode   * The
     *                         code of the parent asset (if
     *                     any).
     */
    public java.lang.String getParentAssetCode() {
        return parentAssetCode;
    }


    /**
     * Sets the parentAssetCode value for this Asset.
     * 
     * @param parentAssetCode   * The
     *                         code of the parent asset (if
     *                     any).
     */
    public void setParentAssetCode(java.lang.String parentAssetCode) {
        this.parentAssetCode = parentAssetCode;
    }


    /**
     * Gets the createOnRegister value for this Asset.
     * 
     * @return createOnRegister   * Indicates whether the asset
     *                         is posted directly to the Asset Register,
     *                         resulting in the asset being capitalised
     *                     immediately.
     */
    public java.lang.Boolean getCreateOnRegister() {
        return createOnRegister;
    }


    /**
     * Sets the createOnRegister value for this Asset.
     * 
     * @param createOnRegister   * Indicates whether the asset
     *                         is posted directly to the Asset Register,
     *                         resulting in the asset being capitalised
     *                     immediately.
     */
    public void setCreateOnRegister(java.lang.Boolean createOnRegister) {
        this.createOnRegister = createOnRegister;
    }


    /**
     * Gets the inheritRemainingLife value for this Asset.
     * 
     * @return inheritRemainingLife   * Indicates whether the asset
     *                         inherits the parent asset's remaining asset
     * life. When TRUE, the child asset will share the
     *                         same depreciation end date as the parent
     *                     asset.
     */
    public java.lang.Boolean getInheritRemainingLife() {
        return inheritRemainingLife;
    }


    /**
     * Sets the inheritRemainingLife value for this Asset.
     * 
     * @param inheritRemainingLife   * Indicates whether the asset
     *                         inherits the parent asset's remaining asset
     * life. When TRUE, the child asset will share the
     *                         same depreciation end date as the parent
     *                     asset.
     */
    public void setInheritRemainingLife(java.lang.Boolean inheritRemainingLife) {
        this.inheritRemainingLife = inheritRemainingLife;
    }


    /**
     * Gets the inheritCostCentre value for this Asset.
     * 
     * @return inheritCostCentre   * Indicates whether the asset
     *                         inherits the parent asset's cost
     *                     centre.
     */
    public java.lang.Boolean getInheritCostCentre() {
        return inheritCostCentre;
    }


    /**
     * Sets the inheritCostCentre value for this Asset.
     * 
     * @param inheritCostCentre   * Indicates whether the asset
     *                         inherits the parent asset's cost
     *                     centre.
     */
    public void setInheritCostCentre(java.lang.Boolean inheritCostCentre) {
        this.inheritCostCentre = inheritCostCentre;
    }


    /**
     * Gets the inheritLocation value for this Asset.
     * 
     * @return inheritLocation   * Indicates whether the asset
     *                         inherits the parent asset's
     *                     location.
     */
    public java.lang.Boolean getInheritLocation() {
        return inheritLocation;
    }


    /**
     * Sets the inheritLocation value for this Asset.
     * 
     * @param inheritLocation   * Indicates whether the asset
     *                         inherits the parent asset's
     *                     location.
     */
    public void setInheritLocation(java.lang.Boolean inheritLocation) {
        this.inheritLocation = inheritLocation;
    }


    /**
     * Gets the addition value for this Asset.
     * 
     * @return addition   * Indicates whether the asset
     *                         is an addition to an asset held on the Asset
     * Intray, or a late adjustment to an asset held on
     *                         the Asset Register.
     */
    public java.lang.Boolean getAddition() {
        return addition;
    }


    /**
     * Sets the addition value for this Asset.
     * 
     * @param addition   * Indicates whether the asset
     *                         is an addition to an asset held on the Asset
     * Intray, or a late adjustment to an asset held on
     *                         the Asset Register.
     */
    public void setAddition(java.lang.Boolean addition) {
        this.addition = addition;
    }


    /**
     * Gets the calculateDepreciation value for this Asset.
     * 
     * @return calculateDepreciation   * When
     *                         creating a late adjustment, the existing asset
     * may have already been depreciation. Set this to
     *                         TRUE to calculate and post the depreciation
     * that
     *                         has accrued for the
     *                     adjustment.
     */
    public java.lang.Boolean getCalculateDepreciation() {
        return calculateDepreciation;
    }


    /**
     * Sets the calculateDepreciation value for this Asset.
     * 
     * @param calculateDepreciation   * When
     *                         creating a late adjustment, the existing asset
     * may have already been depreciation. Set this to
     *                         TRUE to calculate and post the depreciation
     * that
     *                         has accrued for the
     *                     adjustment.
     */
    public void setCalculateDepreciation(java.lang.Boolean calculateDepreciation) {
        this.calculateDepreciation = calculateDepreciation;
    }


    /**
     * Gets the documentWide value for this Asset.
     * 
     * @return documentWide   * When TRUE, only one asset is
     *                         created for all the asset lines that are posted
     * in the document. When FALSE, one asset is
     *                         created per line.
     */
    public java.lang.Boolean getDocumentWide() {
        return documentWide;
    }


    /**
     * Sets the documentWide value for this Asset.
     * 
     * @param documentWide   * When TRUE, only one asset is
     *                         created for all the asset lines that are posted
     * in the document. When FALSE, one asset is
     *                         created per line.
     */
    public void setDocumentWide(java.lang.Boolean documentWide) {
        this.documentWide = documentWide;
    }


    /**
     * Gets the capitalisationDate value for this Asset.
     * 
     * @return capitalisationDate   * The
     *                         date when the asset will be
     *                     capitalised.
     */
    public java.util.Calendar getCapitalisationDate() {
        return capitalisationDate;
    }


    /**
     * Sets the capitalisationDate value for this Asset.
     * 
     * @param capitalisationDate   * The
     *                         date when the asset will be
     *                     capitalised.
     */
    public void setCapitalisationDate(java.util.Calendar capitalisationDate) {
        this.capitalisationDate = capitalisationDate;
    }


    /**
     * Gets the capitalisationPeriodForClientDisplay value for this Asset.
     * 
     * @return capitalisationPeriodForClientDisplay   * The
     *                         year and period when the asset will be
     *                     capitalised.
     */
    public java.lang.String getCapitalisationPeriodForClientDisplay() {
        return capitalisationPeriodForClientDisplay;
    }


    /**
     * Sets the capitalisationPeriodForClientDisplay value for this Asset.
     * 
     * @param capitalisationPeriodForClientDisplay   * The
     *                         year and period when the asset will be
     *                     capitalised.
     */
    public void setCapitalisationPeriodForClientDisplay(java.lang.String capitalisationPeriodForClientDisplay) {
        this.capitalisationPeriodForClientDisplay = capitalisationPeriodForClientDisplay;
    }


    /**
     * Gets the depreciationStartDate value for this Asset.
     * 
     * @return depreciationStartDate   * The
     *                         date when depreciation of the asset will start.
     * If the asset category is set up to allow
     *                         depreciation to start before capitalisation,
     * then the depreciation start date can be earlier
     *                         than the asset's capitalisation period.
     *                         DepreciationStartDate is not relevant if
     *                         Addition is TRUE.
     */
    public java.util.Calendar getDepreciationStartDate() {
        return depreciationStartDate;
    }


    /**
     * Sets the depreciationStartDate value for this Asset.
     * 
     * @param depreciationStartDate   * The
     *                         date when depreciation of the asset will start.
     * If the asset category is set up to allow
     *                         depreciation to start before capitalisation,
     * then the depreciation start date can be earlier
     *                         than the asset's capitalisation period.
     *                         DepreciationStartDate is not relevant if
     *                         Addition is TRUE.
     */
    public void setDepreciationStartDate(java.util.Calendar depreciationStartDate) {
        this.depreciationStartDate = depreciationStartDate;
    }


    /**
     * Gets the depreciationStartPeriodForClientDisplay value for this Asset.
     * 
     * @return depreciationStartPeriodForClientDisplay   * The
     *                         year and period when the asset will start
     * to
     *                     depreciate.
     */
    public java.lang.String getDepreciationStartPeriodForClientDisplay() {
        return depreciationStartPeriodForClientDisplay;
    }


    /**
     * Sets the depreciationStartPeriodForClientDisplay value for this Asset.
     * 
     * @param depreciationStartPeriodForClientDisplay   * The
     *                         year and period when the asset will start
     * to
     *                     depreciate.
     */
    public void setDepreciationStartPeriodForClientDisplay(java.lang.String depreciationStartPeriodForClientDisplay) {
        this.depreciationStartPeriodForClientDisplay = depreciationStartPeriodForClientDisplay;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Asset)) return false;
        Asset other = (Asset) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.categoryCode==null && other.getCategoryCode()==null) || 
             (this.categoryCode!=null &&
              this.categoryCode.equals(other.getCategoryCode()))) &&
            ((this.assetCode==null && other.getAssetCode()==null) || 
             (this.assetCode!=null &&
              this.assetCode.equals(other.getAssetCode()))) &&
            ((this.parentAssetCode==null && other.getParentAssetCode()==null) || 
             (this.parentAssetCode!=null &&
              this.parentAssetCode.equals(other.getParentAssetCode()))) &&
            ((this.createOnRegister==null && other.getCreateOnRegister()==null) || 
             (this.createOnRegister!=null &&
              this.createOnRegister.equals(other.getCreateOnRegister()))) &&
            ((this.inheritRemainingLife==null && other.getInheritRemainingLife()==null) || 
             (this.inheritRemainingLife!=null &&
              this.inheritRemainingLife.equals(other.getInheritRemainingLife()))) &&
            ((this.inheritCostCentre==null && other.getInheritCostCentre()==null) || 
             (this.inheritCostCentre!=null &&
              this.inheritCostCentre.equals(other.getInheritCostCentre()))) &&
            ((this.inheritLocation==null && other.getInheritLocation()==null) || 
             (this.inheritLocation!=null &&
              this.inheritLocation.equals(other.getInheritLocation()))) &&
            ((this.addition==null && other.getAddition()==null) || 
             (this.addition!=null &&
              this.addition.equals(other.getAddition()))) &&
            ((this.calculateDepreciation==null && other.getCalculateDepreciation()==null) || 
             (this.calculateDepreciation!=null &&
              this.calculateDepreciation.equals(other.getCalculateDepreciation()))) &&
            ((this.documentWide==null && other.getDocumentWide()==null) || 
             (this.documentWide!=null &&
              this.documentWide.equals(other.getDocumentWide()))) &&
            ((this.capitalisationDate==null && other.getCapitalisationDate()==null) || 
             (this.capitalisationDate!=null &&
              this.capitalisationDate.equals(other.getCapitalisationDate()))) &&
            ((this.capitalisationPeriodForClientDisplay==null && other.getCapitalisationPeriodForClientDisplay()==null) || 
             (this.capitalisationPeriodForClientDisplay!=null &&
              this.capitalisationPeriodForClientDisplay.equals(other.getCapitalisationPeriodForClientDisplay()))) &&
            ((this.depreciationStartDate==null && other.getDepreciationStartDate()==null) || 
             (this.depreciationStartDate!=null &&
              this.depreciationStartDate.equals(other.getDepreciationStartDate()))) &&
            ((this.depreciationStartPeriodForClientDisplay==null && other.getDepreciationStartPeriodForClientDisplay()==null) || 
             (this.depreciationStartPeriodForClientDisplay!=null &&
              this.depreciationStartPeriodForClientDisplay.equals(other.getDepreciationStartPeriodForClientDisplay())));
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
        if (getCategoryCode() != null) {
            _hashCode += getCategoryCode().hashCode();
        }
        if (getAssetCode() != null) {
            _hashCode += getAssetCode().hashCode();
        }
        if (getParentAssetCode() != null) {
            _hashCode += getParentAssetCode().hashCode();
        }
        if (getCreateOnRegister() != null) {
            _hashCode += getCreateOnRegister().hashCode();
        }
        if (getInheritRemainingLife() != null) {
            _hashCode += getInheritRemainingLife().hashCode();
        }
        if (getInheritCostCentre() != null) {
            _hashCode += getInheritCostCentre().hashCode();
        }
        if (getInheritLocation() != null) {
            _hashCode += getInheritLocation().hashCode();
        }
        if (getAddition() != null) {
            _hashCode += getAddition().hashCode();
        }
        if (getCalculateDepreciation() != null) {
            _hashCode += getCalculateDepreciation().hashCode();
        }
        if (getDocumentWide() != null) {
            _hashCode += getDocumentWide().hashCode();
        }
        if (getCapitalisationDate() != null) {
            _hashCode += getCapitalisationDate().hashCode();
        }
        if (getCapitalisationPeriodForClientDisplay() != null) {
            _hashCode += getCapitalisationPeriodForClientDisplay().hashCode();
        }
        if (getDepreciationStartDate() != null) {
            _hashCode += getDepreciationStartDate().hashCode();
        }
        if (getDepreciationStartPeriodForClientDisplay() != null) {
            _hashCode += getDepreciationStartPeriodForClientDisplay().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Asset.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Asset"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("categoryCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CategoryCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "AssetCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parentAssetCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "ParentAssetCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("createOnRegister");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CreateOnRegister"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inheritRemainingLife");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InheritRemainingLife"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inheritCostCentre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InheritCostCentre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("inheritLocation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "InheritLocation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("addition");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "Addition"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("calculateDepreciation");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CalculateDepreciation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentWide");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DocumentWide"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalisationDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CapitalisationDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("capitalisationPeriodForClientDisplay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "CapitalisationPeriodForClientDisplay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depreciationStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DepreciationStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("depreciationStartPeriodForClientDisplay");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/transaction", "DepreciationStartPeriodForClientDisplay"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.coda.com/efinance/schemas/common", "typeBaseYearPeriod"));
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
