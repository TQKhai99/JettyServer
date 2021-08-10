package com.khaitq2;

import com.khaitq2.api.Like;
import com.khaitq2.api.Song;
import com.khaitq2.api.Stream;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class Jetty {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        try{
            ServletHandler context = new ServletHandler();
            context.addServletWithMapping(Stream.class,"/song/stream");
            context.addServletWithMapping(Like.class,"/song/like");
            context.addServletWithMapping(Song.class,"/song");
            server.setHandler(context);
            server.start();
            System.out.println("server started successful");
        }
        catch(Exception ex){
            System.out.println("server started failed. exception message:"+ex.getMessage());
        }
    }
}