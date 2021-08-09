package API;

import SongService.Client;
import SongService.Error;
import com.google.gson.Gson;
import org.apache.thrift.transport.TTransportException;
import tool.Tool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class Stream extends HttpServlet {
    Client client;

    public Stream() throws TTransportException {
        client = new Client();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);

        String top = req.getParameter("top");
        int topX = Integer.parseInt(top);
        out.println(client.performGetTopStream(topX));
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String id = Tool.getBodyReq(req).get("id").toString();
        int ID = Integer.parseInt(id);
        String res = client.performStreamSong(ID);
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
