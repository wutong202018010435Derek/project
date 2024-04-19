package com.lynn.common.core.domain.entity;

import com.lynn.common.annotation.Excel;
import com.lynn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class SysData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataDeviceid() {
        return dataDeviceid;
    }

    public void setDataDeviceid(String dataDeviceid) {
        this.dataDeviceid = dataDeviceid;
    }

    public float getDataCo() {
        return dataCo;
    }

    public void setDataCo(float dataCo) {
        this.dataCo = dataCo;
    }

    public float getDataWendu() {
        return dataWendu;
    }

    public void setDataWendu(float dataWendu) {
        this.dataWendu = dataWendu;
    }

    public float getDataShidu() {
        return dataShidu;
    }

    public void setDataShidu(float dataShidu) {
        this.dataShidu = dataShidu;
    }

    public float getDataLight() {
        return dataLight;
    }

    public void setDataLight(float dataLight) {
        this.dataLight = dataLight;
    }

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    /** 设备主键 */
    @Excel(name = "设备主键", cellType = Excel.ColumnType.NUMERIC)
    private Long dataId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String dataName;

    /** 负责人 */
    @Excel(name = "设备唯一ID")
    private String dataDeviceid;

    /** 状态（0正常 1停用） */
    @Excel(name = "二氧化碳")
    private float dataCo;

    @Excel(name = "温度")
    private float dataWendu;
    @Excel(name = "湿度")
    private float dataShidu;
    @Excel(name = "光照强度")
    private float dataLight;
    @Excel(name = "记录时间")
    private Date dataTime;

    @Excel(name = "土壤温度")
    private float turangTemp;

    @Excel(name = "土壤湿度")
    private float turangShidu;



    @Excel(name = "ph")
    private float dataPh;

    public float getDataPh() {
        return dataPh;
    }

    public void setDataPh(float dataPh) {
        this.dataPh = dataPh;
    }

    public float getTurangTemp() {
        return turangTemp;
    }

    public void setTurangTemp(float turangTemp) {
        this.turangTemp = turangTemp;
    }

    public float getTurangShidu() {
        return turangShidu;
    }

    public void setTurangShidu(float turangShidu) {
        this.turangShidu = turangShidu;
    }





    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("dataId", getDataId())
                .append("dataName", getDataName())
                .append("dataDeviceid", getDataDeviceid())
                .append("dataCo", getDataCo())
                .append("dataWendu", getDataWendu())
                .append("dataShidu", getDataShidu())
                .append("dataLight", getDataLight())
                .append("dataPh", getDataPh())
                .append("dataTime", getDataTime())
                .toString();
    }
}
