package com.ruoyi.project.system.you.service.impl;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.you.mapper.LvYouMapper;
import com.ruoyi.project.system.you.domain.LvYou;
import com.ruoyi.project.system.you.service.ILvYouService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 旅游信息上传Service业务层处理
 *
 * @author ruoyi
 * @date 2020-12-12
 */
@Service
@Slf4j
public class LvYouServiceImpl implements ILvYouService {
    @Autowired
    private LvYouMapper lvYouMapper;

    /**
     * 查询旅游信息上传
     *
     * @param lvyouId 旅游信息上传ID
     * @return 旅游信息上传
     */
    @Override
    public LvYou selectLvYouById(String lvyouId) {
        return lvYouMapper.selectLvYouById(lvyouId);
    }

    /**
     * 查询旅游信息上传列表
     *
     * @param lvYou 旅游信息上传
     * @return 旅游信息上传
     */
    @Override
    public List<LvYou> selectLvYouList(LvYou lvYou) {
        return lvYouMapper.selectLvYouList(lvYou);
    }

    /**
     * 新增旅游信息上传
     *
     * @param lvYou 旅游信息上传
     * @return 结果
     */
    @Override
    public int insertLvYou(LvYou lvYou) {
        return lvYouMapper.insertLvYou(lvYou);
    }

    /**
     * 修改旅游信息上传
     *
     * @param lvYou 旅游信息上传
     * @return 结果
     */
    @Override
    public int updateLvYou(LvYou lvYou) {
        return lvYouMapper.updateLvYou(lvYou);
    }

    /**
     * 删除旅游信息上传对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLvYouByIds(String ids) {
        return lvYouMapper.deleteLvYouByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除旅游信息上传信息
     *
     * @param lvyouId 旅游信息上传ID
     * @return 结果
     */
    @Override
    public int deleteLvYouById(String lvyouId) {
        return lvYouMapper.deleteLvYouById(lvyouId);
    }

    @Override
    public List<LvYou> selectAll(String type) {
        return lvYouMapper.selectAll(type);
    }

    @Override
    public List<LvYou> selectByTitleKey(String searchKey, String type) {
        return lvYouMapper.selectByTitleKey(searchKey, type);
    }

    @Override
    public List<LvYou> selectFive(String type) {
        return lvYouMapper.selectFive(type);
    }

    @Override
    public List<String> getTags(String type) {

        return lvYouMapper.getTags(type);
    }

    @Override
    public List<LvYou> selectByTag(String tagName, String type) {
        return lvYouMapper.selectByTitleKey(tagName, type);
    }

    @Override
    public List<String> getUSTags(String type) {
        return lvYouMapper.getUSTags(type);
    }

    @Override
    public List<LvYou> selectByUSTag(String tagName, String type) {
        return lvYouMapper.selectByUSTitleKey(tagName, type);

    }

    @Override
    public List<LvYou> selectByTag2(String tagName2, String type) {
        return lvYouMapper.selectByTag2(tagName2, type);
    }

    @Override
    public List<LvYou> selectByUSTag2(String tagName2, String type) {
        return lvYouMapper.selectByUSTag2(tagName2, type);
    }

    @Override
    public List<String> getUSTags2(String type) {
        return lvYouMapper.getUSTags2(type);
    }

    @Override
    public List<String> getTags2(String type) {
        return lvYouMapper.getTags2(type);
    }

    @Override
    public List<LvYou> selectallByCategory(String type) {
        return lvYouMapper.selectallByCategory(type);
    }


}
