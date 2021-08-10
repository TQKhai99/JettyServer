package com.khaitq2.api;

import com.khaitq2.handler.Client;
import com.khaitq2.handler.Handler;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Like extends Handler {
    public Like(){
        client = new Client();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        if(para.get("top") == null) return;

        String top = para.get("top");
        if(top == null) return;
        getPrintWriter(response).println(client.performGetTopLike(Integer.parseInt(top)));
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if(!para.containsKey("id") || !para.containsKey("mode")) return;

        String mode = para.get("mode");
        int id = Integer.parseInt(para.get("id"));
        String res = null;
        if(mode.equals("like")) {
            res = client.performLikeSong(id);
        }
        else {
            res = client.performUnlikeSong(id);
        }
        checkValid(res, response);
    }
}
