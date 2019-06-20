package com.cise.cms.core.applications.helper;

import com.cise.cms.core.applications.base.BaseControllers;
import com.cise.cms.core.applications.base.BaseModelSignature;
import com.cise.cms.core.modules.auth.models.dao.AppUser;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class SessionHelper {

    public static void signature(BaseModelSignature model, boolean isCreated) {
        HttpSession session = BaseControllers.getHttpSession();
        if (session != null) {
            Object o = session.getAttribute("userLogin");
            if (o instanceof AppUser) {
                AppUser user = (AppUser) o;
                if (isCreated) {
                    model.setCreatedBy(user.getUsername());
                    model.setCreatedDate(new Date());
                } else {
                    model.setModifiedBy(user.getUsername());
                    model.setModifiedDate(new Date());
                }
            }
        }
    }

    public static AppUser getAppUser() {
        HttpSession session = BaseControllers.getHttpSession();
        if (session != null) {
            Object o = session.getAttribute("userLogin");
            if (o instanceof AppUser) {
                return (AppUser) o;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

}
