package com.ruoyi.project.system.you.service;

import java.util.List;
import com.ruoyi.project.system.you.domain.LvYou;

/**
 * 旅游信息上传Service接口
 * 
 * @author ruoyi
 * @date 2020-12-12
 */
public interface ILvYouService 
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

    /**uList(lvYou);
     return getDataTable(list);
     * 批量删除旅游信息上传
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLvYouByIds(String ids);

    /**
     * 删除旅游信息上传信息
     * 
     * @param lvyouId 旅游信息上传ID
     * @return 结果
     */
    public int deleteLvYouById(String lvyouId);

    /**
     * 获取所有
     * @return
     */
    List<LvYou> selectAll(String type);

    List<LvYou> selectByTitleKey(String searchKey,String type);

    List<LvYou> selectFive(String type);

    List<String> getTags(String type);

    List<LvYou> selectByTag(String tagName,String type);

    List<String> getUSTags(String type);

    List<LvYou> selectByUSTag(String tagName,String type);

    List<LvYou> selectByTag2(String tagName2,String type);

    List<LvYou> selectByUSTag2(String tagName2,String type);

    List<String> getUSTags2(String type);

    List<String> getTags2(String type);

    List<LvYou> selectallByCategory(String type);
}
