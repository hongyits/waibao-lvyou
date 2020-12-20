package com.ruoyi.project.system.you.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 旅游信息上传对象 lv_you
 * 
 * @author ruoyi
 * @date 2020-12-12
 */

@Data
public class LvYou
{
    private static final long serialVersionUID = 1L;

    /**  */
    private String lvyouId;

    /**  */
    @Excel(name = "")
    private String lvyouDesc;

    /**  */
    @Excel(name = "")
    private String PRICE;

    /**  */
    @Excel(name = "")
    private String fengmianUrl;

    /**  */
    @Excel(name = "")
    private String pdfUrl;

    /**  */
    @Excel(name = "")
    private String crtDatetime;

    /**  */
    @Excel(name = "")
    private String TITLE;

    /**  */
    @Excel(name = "")
    private String locationName;

    /**  */
    @Excel(name = "")
    private String lvyouDescUs;

    /**  */
    @Excel(name = "")
    private String pdfUrlUs;

    /**  */
    @Excel(name = "")
    private String titleUs;

    /**  */
    @Excel(name = "")
    private String locationNameUs;


}
