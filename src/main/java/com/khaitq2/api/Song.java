package com.khaitq2.api;

import com.khaitq2.handler.Client;
import com.khaitq2.handler.Handler;
import com.khaitq2.tool.Tool;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class Song extends Handler {

    public Song(){
        client = new Client();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        if(para.get("id") != null){
            int id = Integer.parseInt(para.get("id"));
            getPrintWriter(response).println(client.performGetSong(id));
            return;
        }

        if(para.get("name") != null){
            String name = para.get("name");
            getPrintWriter(response).println(client.performGetListSongOfArtist(name));
            return;
        }
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if(!para.containsKey("title") || !para.containsKey("singers")) return;

        String title = para.get("title");
        List<String> singers = Tool.getList(para.get("singers"));
        String res = client.performPutSong(title, singers);
        checkValid(res, response);
    }

    @Override
    protected void doDoDelete(Map<String, String> para, HttpServletResponse response) {
        if(!para.containsKey("id")) return;

        int id = Integer.parseInt(para.get("id"));
        String res = client.performRemoveSong(id);
        checkValid(res, response);
    }
}
