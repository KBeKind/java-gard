package com.example.javagarden.models.dto;



import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class PlotDTO {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date editDate;

    private Integer editDateType;

    public PlotDTO(Date editDate, Integer editDateType) {
        this.editDate = editDate;
        this.editDateType = editDateType;
    }

    public PlotDTO() {
    }


    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Integer getEditDateType() {
        return editDateType;
    }

    public void setEditDateType(Integer editDateType) {
        this.editDateType = editDateType;
    }
}

