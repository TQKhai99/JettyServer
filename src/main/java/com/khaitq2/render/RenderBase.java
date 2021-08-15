package com.khaitq2.render;

import com.khaitq2.model.Model;
import com.khaitq2.rythm.Rythm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class RenderBase extends HttpServlet {
    protected Model model;
    protected final Rythm rythmEngine = new Rythm();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doDoGet(resp);
    }

    protected abstract void doDoGet(HttpServletResponse response) throws IOException;

}
