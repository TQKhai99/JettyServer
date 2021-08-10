package com.khaitq2.handler;

import com.khaitq2.tool.Tool;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public abstract class Handler extends HttpServlet {
    public Client client;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){
        _setResp(resp);
        doDoGet(Tool.getParameter(req), resp);
    }

    protected abstract void doDoGet(Map<String, String> para, HttpServletResponse response);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        _setResp(resp);
        doDoPost(Tool.getBody(req), resp);
    }

    protected abstract void doDoPost(Map<String, String> para, HttpServletResponse response);

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        _setResp(resp);
        doDoDelete(Tool.getBody(req), resp);
    }

    protected void doDoDelete(Map<String, String> para, HttpServletResponse response){
        response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
        getPrintWriter(response).println("NOT SUPPORTED");
    }

    protected PrintWriter getPrintWriter(HttpServletResponse response){
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printWriter;
    }

    protected void checkValid(String res, HttpServletResponse response){
        PrintWriter printWriter = getPrintWriter(response);
        switch (res){
            case "INVALID_DATA":
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                printWriter.println("NOT EXIST");
                break;
            case "SUCCESS":
                response.setStatus(HttpServletResponse.SC_OK);
                printWriter.println("SUCCESS");
                break;
            default:
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                printWriter.println("FAIL");
                break;
        }
    }

    private void _setResp(HttpServletResponse resp){
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
