package com.cethik.irmp.util;

//import com.cethik.irmp.domain.UserToken;

import com.cethik.irmp.vmodel.CurrentUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

public class ShiroUtils {

    @Autowired
    private static SessionDAO sessionDAO;

    public static Subject getSubjct() {
        return SecurityUtils.getSubject();
    }

    public static CurrentUser getUser() {
        Object object = getSubjct().getPrincipal();
        return (CurrentUser)object;
    }

    public static Integer getUserId() {
        return getUser().getId();
    }

    public static String getUsername() {
        return getUser().getName();
    }

    public static void logout() {
        getSubjct().logout();
    }

    public static List<Principal> getPrinciples() {
        List<Principal> principals = null;
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        return principals;
    }
}
