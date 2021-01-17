package com.ruoyi.project.system.you.mapper;

import java.util.List;
import com.ruoyi.project.system.you.domain.LvYou;
import org.apache.ibatis.annotations.Param;

/**
 * 旅游信息上传Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-12
 */
public interface LvYouMapper 
{
    /**
     * 查询旅游信息上传
     * 
     * @param lvyouId 旅游信息上传ID
     * @return 旅游信息上传
     */
    public LvYou selectLvYouById(String lvyouId);

    /**
     * 查询旅游信息上传列表
     * 
     * @param lvYou 旅游信息上传
     * @return 旅游信息上传集合
     */
    public List<LvYou> selectLvYouList(LvYou lvYou);

    /**
     * 新增旅游信息上传
     * 
     * @param lvYou 旅游信息上传
     * @return 结果
     */
    public int insertLvYou(LvYou lvYou);

    /**
     * 修改旅游信息上传
     * 
     * @param lvYou 旅游信息上传
     * @return 结果
     */
    public int updateLvYou(LvYou lvYou);

    /**
     * 删除旅游信息上传
     * 
     * @param lvyouId 旅游信息上传ID
     * @return 结果
     */
    public int deleteLvYouById(String lvyouId);

    /**
     * 批量删除旅游信息上传
     * 
     * @param lvyouIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteLvYouByIds(String[] lvyouIds);

    List<LvYou> selectAll(@Param(value = "type") String type);

    List<LvYou> selectByTitleKey(@Param(value = "searchKey") String searchKey,@Param(value = "type") String type);

    List<LvYou> selectFive(@Param(value = "type") String type);

    List<String> getTags(@Param(value = "type") String type);

    List<String> getUSTags(@Param(value = "type") String type);

    List<LvYou> selectByUSTitleKey(String tagName,@Param(value = "type") String type);

    List<LvYou> selectByTag2(@Param(value = "searchKey") String searchKey,@Param(value = "type") String type);

    List<LvYou> selectByUSTag2(@Param(value = "searchKey") String searchKey,@Param(value = "type") String type);

    List<String> getUSTags2(@Param(value = "type") String type);

    List<String> getTags2(@Param(value = "type") String type);

    List<LvYou> selectallByCategory(@Param(value = "type") String CATEGORY);
}
