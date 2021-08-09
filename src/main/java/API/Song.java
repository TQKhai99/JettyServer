package API;

import SongService.Client;
import SongService.Error;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.thrift.transport.TTransportException;
import tool.Tool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Song extends HttpServlet {
    Client client;

    public Song() throws TTransportException {
        client = new Client();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        // get para
        String id = req.getParameter("id");
        String name = req.getParameter("name");

        // do get song
        if(id != null){
            int ID = Integer.parseInt(id);
            out.println(client.performGetSong(ID));
            return;
        }

        // do get list song of artist
        if(name != null) {
            out.println(client.performGetListSongOfArtist(name));
            out.flush();
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        JsonObject jsonObject = Tool.getBodyReq(req);
        String title = jsonObject.get("title").getAsString();
        List<String> singers = Tool.getListFromJson((JsonArray) jsonObject.get("singer"));
        client.performPutSong(title, singers);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id = Tool.getBodyReq(req).get("id").getAsString();
        int ID = Integer.parseInt(id);

        String res = client.performRemoveSong(ID);
        if(res.equals(new Gson().toJson(Error.INVALID_DATA))){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            out.println("NOT OK");
            return;
        }
        resp.setStatus(HttpServletResponse.SC_OK);
        out.println("OK");
        return;

    }
}
