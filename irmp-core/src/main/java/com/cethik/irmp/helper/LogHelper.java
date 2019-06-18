package com.cethik.irmp.helper;

import com.cethik.irmp.util.DateUtils;
import com.cethik.irmp.model.Systemlog;
import com.cethik.irmp.service.SystemlogService;
import com.cethik.irmp.util.ShiroUtils;
import com.cethik.irmp.vmodel.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * the description of class or interface
 *
 * @author zhongping@hikchina.com
 * @date 2018-08-31
 */
@Component
public class LogHelper {

    private static SystemlogService logService;

    @Autowired
    public LogHelper(SystemlogService logService) {
        LogHelper.logService = logService;
    }

    public static void operate(String id)
    {
        logHelper("操作", "", "");
    }

    public static void log(Exception ex)
    {
        String strErrorMsg = String.format("Exception:%n异常信息:{0}%n异常对象:{1}%n调用堆栈:{2}%n",
                ex.getMessage(),ex.getLocalizedMessage(), ex.getStackTrace());

        String msg = strErrorMsg;// ex.Message;
        String description = "";        //获得详细出错信息
//        Exception exx = ex.;
//        while (exx != null)
//        {
//            if (!String.(exx.getStackTrace()))
//            {
//                msg += exx.Message + "\r\n\r\n";
//                description += exx.StackTrace.ToString() + "\r\n\r\n";
//            }
//
//            exx = exx.InnerException;
//        }

        logHelper("异常", strErrorMsg, msg + description);
    }

    public static void logPage()
    {

        CurrentUser cUser=ShiroUtils.getUser();
        String  description = cUser!=null?"【" +cUser.getName():"";
        description+="】在【" + DateUtils.getDateTime() + "】打开了系统";
        logHelper("登录界面", "打开登录界面", description);
    }

    public static void logOn()
    {
        String description = "【" + ShiroUtils.getUser().getName() + "】在【" + DateUtils.getDateTime() + "】登录了系统";
        logHelper("登录", "登录", description);
    }

    public static void logOff()
    {
        String description = "【" + ShiroUtils.getUser().getName() + "】在【" + DateUtils.getDateTime() + "】退出了系统";
        logHelper("退出", "退出", description);
    }

    private static void logHelper(String logType, String message, String description)
    {
        try
        {
//            var current = HttpContext.Current;
//            var hostIP = string.Empty;
//            if (current != null && current.Request != null)
//            {
//                hostIP = current.Request.UserHostAddress;
//            }

//            String controller = ((MvcHandler)(HttpContext.Current.Handler)).RequestContext.RouteData.Values["Controller"] as string;
//            String action = ((MvcHandler)(HttpContext.Current.Handler)).RequestContext.RouteData.Values["Action"] as string;

            Systemlog log = new Systemlog();

            log.setLogtype(logType);
//            log.setController(controller);
//            log.Action = action;
//            log.RelationID = relationID;
            log.setMessage(message);
            log.setDescription(description);
//            log.setHostip(hostIP);
            log.setUpdatetime(new Date());
            log.setOperator(ShiroUtils.getUser().getId());
            log.setOperatorname(ShiroUtils.getUser().getName());
            log.setCreator(ShiroUtils.getUser().getId());
            log.setModifier(ShiroUtils.getUser().getId());

            logService.create(log);
        }
        catch (Exception ex)
        {
            String e = ex.getMessage();
            //by pass
        }
    }
}
