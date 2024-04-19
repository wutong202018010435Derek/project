package com.lynn.common.core.domain.entity;


import com.lynn.common.annotation.Excel;
import com.lynn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 字典类型表 sys_dict_type
 * 
 * @author ruoyi
 */
public class SysDictType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 设备主键 */
    @Excel(name = "设备主键", cellType = Excel.ColumnType.NUMERIC)
    private Long dictId;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String dictName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String dictType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 设备唯一码 */
    @Excel(name = "设备唯一码")
    private String dictMqtt;


    private String temp;//
    private String tempMax;//
    private String tempMin;//
    private String hum;//
    private String humMax;//
    private String humMin;//
    private String carbon;//
    private String carbonMax;//
    private String carbonMin;//



    private String light;//
    private String lightMax;//
    private String lightMin;//



    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getCarbon() {
        return carbon;
    }

    public void setCarbon(String carbon) {
        this.carbon = carbon;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
    }

    public String getHumMax() {
        return humMax;
    }

    public void setHumMax(String humMax) {
        this.humMax = humMax;
    }

    public String getHumMin() {
        return humMin;
    }

    public void setHumMin(String humMin) {
        this.humMin = humMin;
    }

    public String getCarbonMax() {
        return carbonMax;
    }

    public void setCarbonMax(String carbonMax) {
        this.carbonMax = carbonMax;
    }

    public String getCarbonMin() {
        return carbonMin;
    }

    public void setCarbonMin(String carbonMin) {
        this.carbonMin = carbonMin;
    }

    public String getLightMax() {
        return lightMax;
    }

    public void setLightMax(String lightMax) {
        this.lightMax = lightMax;
    }

    public String getLightMin() {
        return lightMin;
    }

    public void setLightMin(String lightMin) {
        this.lightMin = lightMin;
    }



















    public String getDictMqtt() {
        return dictMqtt;
    }

    public void setDictMqtt(String dictMqtt) {
        this.dictMqtt = dictMqtt;
    }



    public Long getDictId()
    {
        return dictId;
    }

    public void setDictId(Long dictId)
    {
        this.dictId = dictId;
    }

    @NotBlank(message = "名称不能为空")
    @Size(min = 0, max = 100, message = "类型名称长度不能超过100个字符")
    public String getDictName()
    {
        return dictName;
    }

    public void setDictName(String dictName)
    {
        this.dictName = dictName;
    }

//    @NotBlank(message = "负责人")
//    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
//    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    public String getDictType()
    {
        return dictType;
    }

    public void setDictType(String dictType)
    {
        this.dictType = dictType;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictId", getDictId())
            .append("dictName", getDictName())
            .append("dictType", getDictType())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .append("dictMqtt", getDictMqtt())
            .toString();
    }
}
