package com.khaitq2.api;

import com.khaitq2.handler.Client;
import com.khaitq2.handler.Handler;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class Stream extends Handler {
    public Stream(){
        client = new Client();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        if(para.get("top") == null) return;

        int topX = Integer.parseInt(para.get("top"));
        getPrintWriter(response).println(client.performGetTopStream(topX));
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if(!para.containsKey("id")) return;

        int id = Integer.parseInt(para.get("id"));
        checkValid(client.performStreamSong(id), response);
    }
}
