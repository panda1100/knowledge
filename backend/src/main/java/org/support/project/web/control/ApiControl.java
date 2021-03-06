package org.support.project.web.control;

import org.support.project.common.config.Resources;
import org.support.project.web.bean.ApiParams;
import org.support.project.web.bean.Msg;
import org.support.project.web.boundary.Boundary;
import org.support.project.web.common.HttpStatus;
import org.support.project.web.common.HttpStatusMsg;
import org.support.project.web.common.HttpUtil;
import org.support.project.web.exception.InvalidParamException;

public abstract class ApiControl extends Control {
    protected <T> T parseJson(Class<T> type) throws InvalidParamException {
        return HttpUtil.parseJson(getRequest(), type); 
    }
    protected String getResource(String key) {
        Resources resources = Resources.getInstance(HttpUtil.getLocale(getRequest()));
        return resources.getResource(key);
    }
    protected String getResource(String key, String... params) {
        Resources resources = Resources.getInstance(HttpUtil.getLocale(getRequest()));
        return resources.getResource(key, params);
    }
    
    protected Boundary sendError(int status) {
        return send(status, new Msg(HttpStatusMsg.getMsg(status)));
    }
    protected Boundary sendError(InvalidParamException e) {
        if (e.getMessageResult() != null) {
            return send(e.getMessageResult().getCode(), e.getMessageResult());
        } else {
            return this.sendError(HttpStatus.SC_400_BAD_REQUEST);
        }
    }
    
    protected ApiParams getCommonApiParams() {
        ApiParams param = new ApiParams();
        param.setLimit(getParamInt("limit", 10, 100));
        param.setOffset(getParamInt("offset", 0, -1));
        return param;
    }
}
