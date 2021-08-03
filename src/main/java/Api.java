import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Api extends HttpServlet {
    private Map<Integer, String> dictionary = new HashMap<>();
    public Api(){
        dictionary.put(1, "Java");
        dictionary.put(2, "Python");
        dictionary.put(3, "C++");
    }
    public static boolean isEmpty(String a){
        return a == null || a.length() == 0;
    }
    public static Map<String, String> getAllParameter(HttpServletRequest request){
        Map<String, String> parameters = new HashMap<String, String>();
        try{
            Enumeration<String> propertyNames = request.getParameterNames();
            if(propertyNames!=null){
                while (propertyNames.hasMoreElements()){
                    String parameterName = propertyNames.nextElement();
                    if(!isEmpty(parameterName)){
                        parameters.put(parameterName, request.getParameter(parameterName));
                    }
                }
            }
        }
        catch (Exception e){
        }
        return parameters;
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Map<String, String> para = getAllParameter(request);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Get method");
        if(para.containsKey("key") && para.containsKey("value")){
            //CREATE
            int key = Integer.parseInt(para.get("key"));
            response.getWriter().println("CREAT");
            if(dictionary.containsKey(key)){
                response.getWriter().println("key EXIST");
            } else {
                dictionary.put(key, para.get("value"));
            }
            Set set = dictionary.keySet();
            for(Object i:  set)
                response.getWriter().println("<h1>"+ i + " " + dictionary.get(i)+"</h1>");
        } else {
            //READ
            response.getWriter().println("READ");
            Set set = dictionary.keySet();
            for(Object i:  set)
                response.getWriter().println("<h1>"+ i + " " + dictionary.get(i)+"</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Map<String, String> para = getAllParameter(request);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("Post method");
        if(para.containsKey("key") && para.containsKey("value")){
            //UPDATE
            int key = Integer.parseInt(para.get("key"));
            if(dictionary.containsKey(key)){
                dictionary.put(key, para.get("value"));
                response.getWriter().println("UPDATE OK");
            }
            else{
                response.getWriter().println("key not EXIST");
            }
        } else {
            // READ
            response.getWriter().println("READ");
        }
        Set set = dictionary.keySet();
        for(Object i:  set)
            response.getWriter().println("<h1>"+ i + " " + dictionary.get(i)+"</h1>");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException
    {
        Map<String, String> para = getAllParameter(request);
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        if(para.containsKey("key")){
            int key = Integer.parseInt(para.get("key"));
            dictionary.remove(key);
            response.getWriter().println("<h1>"+"Delete OK!"+"</h1>");
            Set set = dictionary.keySet();
            for(Object i:  set)
                response.getWriter().println("<h1>"+ i + " " + dictionary.get(i)+"</h1>");
        } else {
            response.getWriter().println("<h1>NOT FOUND PARAMETER</h1>");
        }

    }
}
