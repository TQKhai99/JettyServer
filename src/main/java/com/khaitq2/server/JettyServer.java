package com.khaitq2.server;

import com.khaitq2.config.Config;
import com.khaitq2.handler.LikeHandler;
import com.khaitq2.handler.SongHandler;
import com.khaitq2.handler.StreamHandler;
import com.khaitq2.render.SongRender;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class JettyServer {
    public static void main(String[] args) {
        Server server = new Server(Integer.parseInt(Config.getInstance().getConfig().get("hPort")));
        try {
            ServletHandler context = new ServletHandler();
            context.addServletWithMapping(StreamHandler.class, "/song/stream");
            context.addServletWithMapping(LikeHandler.class, "/song/like");
            context.addServletWithMapping(SongHandler.class, "/song");
            context.addServletWithMapping(SongRender.class, "/home");
            server.setHandler(context);
            server.start();
            System.out.println("server started successful");
        } catch (Exception ex) {
            System.out.println("server started failed. exception message:" + ex.getMessage());
        }
    }
}