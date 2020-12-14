package com.ruoyi.project.system.you.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.Base64Utils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.config.RuoYiConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.cache.Cache;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.system.you.domain.LvYou;
import com.ruoyi.project.system.you.service.ILvYouService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 旅游信息上传Controller
 *
 * @author ruoyi
 * @date 2020-12-12
 */
@Controller
@Slf4j
@RequestMapping("/system/you")
public class LvYouController extends BaseController {

    private String prefix = "system/you";

    @Autowired
    private ILvYouService lvYouService;

    //默认上传地址
    private static String defaultBaseDir = RuoYiConfig.getProfile();


    @RequiresPermissions("system:you:view")
    @GetMapping()
    public String you(Model model) {
        List<LvYou> list = lvYouService.selectAll();
        //把对应的所有文件都搞成缓存
        for (int i = 0; i < list.size(); i++) {
            LvYou lvYou = list.get(i);
            String fengmianUrl = lvYou.getFengmianUrl();
            String pdfUrl = lvYou.getPdfUrl();
            String pdfUrlUs = lvYou.getPdfUrlUs();

            Cache<String, Object> cache = CacheUtils.getCache(fengmianUrl);
            if (null == cache) {
                String imgStr = Base64Utils.getImgStr(defaultBaseDir + File.separator + fengmianUrl);
                cache.put(fengmianUrl, imgStr);
            }
        }
        model.addAttribute("lvyouInfo", list);

        return prefix + "/you";
    }


    @RequestMapping(value = "/searchByList", method = RequestMethod.GET)
    public String searchByList(@RequestParam(value = "searchKey") String searchKey, HttpServletRequest request, ModelMap mmap) {
        List<LvYou> list = lvYouService.selectByTitleKey(searchKey);
        mmap.put("lvyouInfo", list);
        return "lvyouIndex/index";

    }

    /**
     * 查询旅游信息上传列表
     */
    @RequiresPermissions("system:you:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LvYou lvYou) {
        startPage();
        List<LvYou> list = lvYouService.selectLvYouList(lvYou);
        return getDataTable(list);
    }

    /**
     * 查询旅游信息上传列表
     */
//    @RequiresPermissions("system:you:list")
    @GetMapping("/listAll")
//    @ResponseBody
    public List<LvYou> listAll(ModelMap mmap) {
        List<LvYou> list = lvYouService.selectAll();
        //把对应的所有文件都搞成缓存
        for (int i = 0; i < list.size(); i++) {
            LvYou lvYou = list.get(i);
            String fengmianUrl = lvYou.getFengmianUrl();
            String pdfUrl = lvYou.getPdfUrl();
            String pdfUrlUs = lvYou.getPdfUrlUs();

            Cache<String, Object> cache = CacheUtils.getCache(fengmianUrl);
            if (null == cache) {
                String imgStr = Base64Utils.getImgStr(defaultBaseDir + File.separator + fengmianUrl);
                cache.put(fengmianUrl, imgStr);
            }
        }

        mmap.put("lvyouInfo", list);
        return list;
    }

    /**
     * 导出旅游信息上传列表
     */
    @RequiresPermissions("system:you:export")
    @Log(title = "旅游信息上传", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LvYou lvYou) {
        List<LvYou> list = lvYouService.selectLvYouList(lvYou);
        ExcelUtil<LvYou> util = new ExcelUtil<LvYou>(LvYou.class);
        return util.exportExcel(list, "you");
    }

    /**
     * 新增旅游信息上传
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存旅游信息上传
     */
    @RequiresPermissions("system:you:add")
    @Log(title = "旅游信息上传", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LvYou lvYou) {
        return toAjax(lvYouService.insertLvYou(lvYou));
    }

    /**
     * 修改旅游信息上传
     */
    @GetMapping("/edit/{lvyouId}")
    public String edit(@PathVariable("lvyouId") String lvyouId, ModelMap mmap) {
        LvYou lvYou = lvYouService.selectLvYouById(lvyouId);
        mmap.put("lvYou", lvYou);
        return prefix + "/edit";
    }

    /**
     * 修改保存旅游信息上传
     */
    @RequiresPermissions("system:you:edit")
    @Log(title = "旅游信息上传", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LvYou lvYou, @RequestParam("fengmianFile") MultipartFile fengmianFile, @RequestParam("pdfFile") MultipartFile pdfFile, @RequestParam("usPdfFile") MultipartFile usPdfFile) {
        try {
            if (!fengmianFile.isEmpty()) {
                String fengmianName = FileUploadUtils.upload(RuoYiConfig.getAvatarPath(), fengmianFile);
                lvYou.setFengmianUrl(fengmianName);
            }
            if (!pdfFile.isEmpty()) {
                String pdfFileName = FileUploadUtils.uploadPdf(RuoYiConfig.getAvatarPath(), pdfFile);
                lvYou.setPdfUrl(pdfFileName);

            }
            if (!usPdfFile.isEmpty()) {
                String usPdfFileName = FileUploadUtils.uploadPdf(RuoYiConfig.getAvatarPath(), usPdfFile);
                lvYou.setPdfUrlUs(usPdfFileName);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return toAjax(lvYouService.updateLvYou(lvYou));
    }

    /**
     * 删除旅游信息上传¡
     */
    @RequiresPermissions("system:you:remove")
    @Log(title = "旅游信息上传", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(lvYouService.deleteLvYouByIds(ids));
    }


    @RequestMapping(value = "/showPdf", method = RequestMethod.GET)
    public void showPdf(HttpServletRequest request, HttpServletResponse response,@RequestParam(value = "fileName") String fileName) {
        //PDF文件地址
        String pdfUrl =defaultBaseDir + File.separator + fileName;

//        File file = new File("/Users/huanghongyuan/IdeaProjects/waibao-lvyou/uploadPath/2.pdf");//test
        File file = new File(pdfUrl);
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input=null;
            try {
                input= new FileInputStream(file);
                data = new byte[input.available()];
                input.read(data);
                response.getOutputStream().write(data);
            } catch (Exception e) {
                System.out.println("pdf文件处理异常：" + e);
            }finally{
                try {
                    if(input!=null){
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}