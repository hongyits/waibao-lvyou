package com.ruoyi.project.system.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.file.Base64Utils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.project.system.you.domain.LvYou;
import com.ruoyi.project.system.you.service.ILvYouService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;

import java.io.File;
import java.util.List;

/**
 * 登录验证
 *
 * @author ruoyi
 */
@Controller
public class LoginController extends BaseController {
    @Autowired
    private ILvYouService lvYouService;

    //默认上传地址
    private static String defaultBaseDir = RuoYiConfig.getProfile();


    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request)) {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }


    @GetMapping("/index2")
    public String index2(HttpServletRequest request, HttpServletResponse response, ModelMap mmap,@RequestParam(value = "isUS",required = false)  String isUS) {
        List<LvYou> list = lvYouService.selectAll();
        //把对应的所有文件都搞成缓存
        for (int i = 0; i < list.size(); i++) {
            LvYou lvYou = list.get(i);
            String fengmianUrl = lvYou.getFengmianUrl();
            String pdfUrl = lvYou.getPdfUrl();
            String pdfUrlUs = lvYou.getPdfUrlUs();

        }
        mmap.put("lvyouInfo", list);
        if (StringUtils.isNotEmpty(isUS)){
            return "lvyouIndex/ind¡exUS";

        }else {
            return "lvyouIndex/index";

        }
    }



    @GetMapping("/indexLvYouInfo")
    public String indexLvYouInfo(HttpServletRequest request, HttpServletResponse response, ModelMap mmap, @RequestParam(value = "needMore", required = false) String needMore, @RequestParam(value = "tagName", required = false) String tagName,@RequestParam(value = "tagName2", required = false) String tagName2,@RequestParam(value = "isUS",required = false)  String isUS) {
        boolean showMoreFlag = true;
        List<LvYou> list = null;
        if (StringUtils.isEmpty(needMore)) {
            list = lvYouService.selectFive();
        } else {
            showMoreFlag = false;
            list = lvYouService.selectAll();
        }
        if (StringUtils.isNotEmpty(tagName)) {
            showMoreFlag = false;

            if (StringUtils.isNotEmpty(isUS)) {
                list = lvYouService.selectByUSTag(tagName);
            }else {
                list = lvYouService.selectByTag(tagName);
            }

        }

        if (StringUtils.isNotEmpty(tagName2)) {
            showMoreFlag = false;

            if (StringUtils.isNotEmpty(isUS)) {
                list = lvYouService.selectByUSTag2(tagName2);
            }else {
                list = lvYouService.selectByTag2(tagName2);
            }

        }


        List<String> tags  = null;
        List<String> tags2  = null;
        if (StringUtils.isNotEmpty(isUS)) {
            tags= lvYouService.getUSTags();
        }else {
            tags= lvYouService.getTags();

        }

        if (StringUtils.isNotEmpty(isUS)) {
            tags2= lvYouService.getUSTags2();
        }else {
            tags2= lvYouService.getTags2();

        }



        mmap.addAttribute("list", list);
        mmap.addAttribute("tags", tags);
        mmap.addAttribute("tags2", tags2);
        mmap.addAttribute("showMoreFlag", showMoreFlag);

        if (StringUtils.isNotEmpty(isUS)) {
            return "lvyouIndex/infoUS";

        }else {
            return "lvyouIndex/info";

        }
    }

    @GetMapping("/indexAbout")
    public String indexAbout(HttpServletRequest request, HttpServletResponse response, ModelMap mmap,@RequestParam(value = "isUS",required = false)  String isUS) {
        if (StringUtils.isNotEmpty(isUS)) {
            return "lvyouIndex/aboutUS";
        }else {
            return "lvyouIndex/about";

        }
    }


    @GetMapping("/index3")
    public String index3(HttpServletRequest request, HttpServletResponse response, ModelMap mmap) {

        return "lvyouIndex/index";
    }

    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return success();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth() {
        return "error/unauth";
    }
}
