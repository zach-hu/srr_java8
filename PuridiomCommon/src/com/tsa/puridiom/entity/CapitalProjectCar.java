package com.tsa.puridiom.entity;

import com.tsa.puridiom.common.utility.HiltonUtility;
import java.io.Serializable;


/** @author Hibernate CodeGenerator */
public class CapitalProjectCar implements Serializable {

    /** identifier field */
    private java.math.BigDecimal icProjectCar;

    /** nullable persistent field */
    private String division;

    /** nullable persistent field */
    private String project;

    /** nullable persistent field */
    private String car;

    /** nullable persistent field */
    private String description;

    /** nullable persistent field */
    private String program;

    /** nullable persistent field */
    private java.math.BigDecimal amount;

    /** nullable persistent field */
    private String status;

    /** nullable persistent field */
    private String year;

    /** full constructor */
    public CapitalProjectCar(java.math.BigDecimal icProjectCar, java.lang.String division, java.lang.String project, java.lang.String car, java.lang.String description, java.lang.String program, java.math.BigDecimal amount, java.lang.String status, java.lang.String year ) {
        this.icProjectCar = icProjectCar;
        this.division = division;
        this.project = project;
        this.car = car;
        this.description = description;
        this.program = program;
        this.amount = amount;
        this.status = status;
        this.year = year;
    }

    /** default constructor */
    public CapitalProjectCar() {
    }

    /** minimal constructor */
    public CapitalProjectCar(java.math.BigDecimal icProjectCar) {
        this.icProjectCar = icProjectCar;
    }

    public java.math.BigDecimal getIcProjectCar() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.icProjectCar);
    }

    public void setIcProjectCar(java.math.BigDecimal icProjectCar) {
		this.icProjectCar = icProjectCar;
    }

    public java.lang.String getDivision() {
		return (java.lang.String)HiltonUtility.ckNull(this.division).trim();
    }

    public void setDivision(java.lang.String division) {
		this.division = division;
    }

    public java.lang.String getProject() {
		return (java.lang.String)HiltonUtility.ckNull(this.project).trim();
    }

    public void setProject(java.lang.String project) {
		this.project = project;
    }

    public java.lang.String getCar() {
		return (java.lang.String)HiltonUtility.ckNull(this.car).trim();
    }

    public void setCar(java.lang.String car) {
		this.car = car;
    }

    public java.lang.String getDescription() {
		return (java.lang.String)HiltonUtility.ckNull(this.description).trim();
    }

    public void setDescription(java.lang.String description) {
		this.description = description;
    }

    public java.lang.String getProgram() {
		return (java.lang.String)HiltonUtility.ckNull(this.program).trim();
    }

    public void setProgram(java.lang.String program) {
		this.program = program;
    }

    public java.math.BigDecimal getAmount() {
		return (java.math.BigDecimal)HiltonUtility.ckNull(this.amount);
    }

    public void setAmount(java.math.BigDecimal amount) {
		this.amount = amount;
    }

    public java.lang.String getStatus() {
		return (java.lang.String)HiltonUtility.ckNull(this.status).trim();
    }

    public void setStatus(java.lang.String status) {
		this.status = status;
    }

    public java.lang.String getYear() {
		return (java.lang.String)HiltonUtility.ckNull(this.year).trim();
    }
    public void setYear(java.lang.String year) {
		this.year = year;
    }

}
