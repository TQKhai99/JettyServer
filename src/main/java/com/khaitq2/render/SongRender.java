package com.khaitq2.render;

import com.khaitq2.model.Model;
import org.rythmengine.Rythm;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SongRender extends RenderBase {
    public SongRender(){
        model = new Model();
    }

    @Override
    protected void doDoGet(HttpServletResponse response) {
        try {
            response.getWriter().println(Rythm.render("./home.template", "World"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
