package com.khaitq2.handler;

import com.khaitq2.model.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LikeHandler extends HandlerBase {
    public LikeHandler() {
        model = new Model();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        String top = para.get("top");
        if (para.get("top") == null) {
            return;
        }

        getPrintWriter(response).println(model.performGetTopLike(Integer.parseInt(top)));
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if (!para.containsKey("id") || !para.containsKey("mode")) {
            return;
        }

        String mode = para.get("mode");
        int id = Integer.parseInt(para.get("id"));
        String res = null;
        if (mode.equals("like")) {
            res = model.performLikeSong(id);
        } else {
            res = model.performUnlikeSong(id);
        }
        checkValid(res, response);
    }
}
