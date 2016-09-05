package com.hydra.tool.web.ajax;

import com.alibaba.fastjson.JSONObject;
import org.nutz.json.Json;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by osx on 14-3-22.
 */
public class AjaxUtil {
    public static final int SUCCESS_CODE = 0;

    public static void sendAjaxRespond(HttpServletResponse response, Object object, String tip) throws IOException {
        response.getWriter().write(JSONObject.toJSONString(new AjaxMessage(SUCCESS_CODE, object, tip)));
    }

    public static void sendAjaxErrorRespond(HttpServletResponse response, int errorCode, String tip) throws IOException {
        response.getWriter().write(JSONObject.toJSONString(new AjaxMessage(errorCode, null, tip)));
    }

    public static class AjaxMessage {
        private int errorCode;
        private Object content;
        private String tip;

        public AjaxMessage() {

        }

        public AjaxMessage(int errorCode, Object content, String tip) {
            this.errorCode = errorCode;
            this.content = content;
            this.tip = tip;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }

        public Object getContent() {
            return content;
        }

        public void setContent(Object content) {
            this.content = content;
        }

        public String getTip() {
            return tip;
        }

        public void setTip(String tip) {
            this.tip = tip;
        }
    }
}
