package com.khaitq2.render;

import com.khaitq2.model.Model;
import com.khaitq2.songservice.SongStruct;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


public class SongRender extends RenderBase {
    public SongRender(){
        model = new Model();
    }

    @Override
    protected void doDoGet(HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String res;
        List<SongStruct> listSong = _getSong();
        try {
            res = rythmEngine.getRythmEngine().render("home.html", listSong);
        } catch (Exception e) {
            res = "<h1>Something wrong when engine render</h1>";
            e.printStackTrace();
        }
        out.print(res);
    }

    private List<SongStruct> _getSong(){
        List<SongStruct> listSong = model.performGetListSongOfArtist("Adele");
        return listSong;
    }

}
