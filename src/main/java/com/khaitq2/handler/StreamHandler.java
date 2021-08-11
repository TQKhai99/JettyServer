package com.khaitq2.handler;

import com.khaitq2.model.Model;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class StreamHandler extends HandlerBase {
    public StreamHandler() {
        model = new Model();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        String top = para.get("top");
        if (top == null) {
            return;
        }

        int topX = Integer.parseInt(para.get("top"));
        getPrintWriter(response).println(model.performGetTopStream(topX));
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if (!para.containsKey("id")) {
            return;
        }

        int id = Integer.parseInt(para.get("id"));
        checkValid(model.performStreamSong(id), response);
    }
}
