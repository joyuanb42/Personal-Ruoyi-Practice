package com.ruoyi.demo.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 应用声明对象 fmg_base_scene
 * 
 * @author ruoyi
 * @date 2024-06-26
 */
public class BaseScene extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 场景主键 */
    private Long sceneId;

    /** 场景名称 */
    @Excel(name = "场景名称")
    private String sceneName;

    /** 场景说明 */
    @Excel(name = "场景说明")
    private String sceneInfo;

    public void setSceneId(Long sceneId) 
    {
        this.sceneId = sceneId;
    }

    public Long getSceneId() 
    {
        return sceneId;
    }
    public void setSceneName(String sceneName) 
    {
        this.sceneName = sceneName;
    }

    public String getSceneName() 
    {
        return sceneName;
    }
    public void setSceneInfo(String sceneInfo) 
    {
        this.sceneInfo = sceneInfo;
    }

    public String getSceneInfo() 
    {
        return sceneInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sceneId", getSceneId())
            .append("sceneName", getSceneName())
            .append("sceneInfo", getSceneInfo())
            .toString();
    }
}
