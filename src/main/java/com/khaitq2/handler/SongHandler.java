package com.khaitq2.handler;

import com.khaitq2.cache.Cache;
import com.khaitq2.model.Model;
import com.khaitq2.songservice.SongStruct;
import com.khaitq2.util.Util;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class SongHandler extends HandlerBase {

    public SongHandler() {
        model = new Model();
    }

    @Override
    protected void doDoGet(Map<String, String> para, HttpServletResponse response) {
        if (para.get("id") != null) {
            int id = Integer.parseInt(para.get("id"));
            getPrintWriter(response).println(model.performGetSong(id));
            return;
        }

        if (para.get("name") != null) {
            String name = para.get("name");
            getPrintWriter(response).println(model.performGetListSongOfArtist(name));
        }
    }

    @Override
    protected void doDoPost(Map<String, String> para, HttpServletResponse response) {
        if (!para.containsKey("title") || !para.containsKey("singers")) {
            return;
        }

        String title = para.get("title");
        List<String> singers = Util.getList(para.get("singers"));
        String res = model.performPutSong(title, singers);
        checkValid(res, response);
    }

    @Override
    protected void doDoDelete(Map<String, String> para, HttpServletResponse response) {
        if (!para.containsKey("id")) {
            return;
        }

        int id = Integer.parseInt(para.get("id"));
        String res = model.performRemoveSong(id);
        checkValid(res, response);
    }
}
