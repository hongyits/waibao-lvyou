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
public class LoginController extends BaseController
{
    @Autowired
    private ILvYouService lvYouService;

    //默认上传地址
    private static String defaultBaseDir = RuoYiConfig.getProfile();


    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");
        }

        return "login";
    }


    @GetMapping("/index2")
    public String index2(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        List<LvYou> list = lvYouService.selectAll();
        //把对应的所有文件都搞成缓存
        for (int i = 0; i < list.size(); i++) {
            LvYou lvYou = list.get(i);
            String fengmianUrl = lvYou.getFengmianUrl();
            String pdfUrl = lvYou.getPdfUrl();
            String pdfUrlUs = lvYou.getPdfUrlUs();
//
//            Cache<String, Object> cache = CacheUtils.getCache(fengmianUrl);
//            if (null == cache) {
//                String imgStr = Base64Utils.getImgStr(defaultBaseDir + File.separator + fengmianUrl);
//                cache.put(fengmianUrl,imgStr);
//            }
        }
        mmap.put("lvyouInfo",list);
        return "lvyouIndex/index";
    }


    @GetMapping("/indexLvYouInfo")
    public String indexLvYouInfo(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        return "lvyouIndex/info";
    }

    @GetMapping("/indexAbout")
    public String indexAbout(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {
        return "lvyouIndex/about";
    }


    @GetMapping("/index3")
    public String index3(HttpServletRequest request, HttpServletResponse response, ModelMap mmap)
    {

        return "lvyouIndex/index";
    }
    @PostMapping("/login")
    @ResponseBody
    public AjaxResult ajaxLogin(String username, String password, Boolean rememberMe)
    {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return success();
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }

    @GetMapping("/unauth")
    public String unauth()
    {
        return "error/unauth";
    }
}
