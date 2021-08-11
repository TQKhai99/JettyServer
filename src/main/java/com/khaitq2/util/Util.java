package com.khaitq2.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.*;

public class Util {
    private Util() {
    }

    public static Map<String, String> getParameter(HttpServletRequest req) {
        Map<String, String> res = new HashMap<>();
        res.put("id", req.getParameter("id"));
        res.put("name", req.getParameter("name"));
        res.put("top", req.getParameter("top"));
        return res;
    }

    public static Map<String, String> getBody(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception ex) {
            sb.setLength(0);
        }

        String para = sb.toString();
        Map<String, String> res = new HashMap<>();
        for (String i : para.split("&")) {
            String[] t = i.split("=");
            res.put(t[0], t[1]);
        }
        return res;
    }

    public static List<String> getList(String a) {
        return Arrays.asList(a.split(", "));
    }
}
