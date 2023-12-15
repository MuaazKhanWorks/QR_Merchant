/*Author Name:ahmad.raza
Project Name: usermanagement
Package Name:com.mfs.usermanagement.dto
Class Name: ProcedureResponse
Date and Time:6/11/2023 4:34 PM
Version:1.0*/
package com.mfs.merchantQR.dto;

public class ProcedureResponse {
    private int status;
    private String statusDescr;
    private int mcApplicability;


    public String getStatusDescr() {
        return statusDescr;
    }

    public void setStatusDescr(String statusDescr) {
        this.statusDescr = statusDescr;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getMcApplicability() {
        return mcApplicability;
    }

    public void setMcApplicability(int mcApplicability) {
        this.mcApplicability = mcApplicability;
    }
}
