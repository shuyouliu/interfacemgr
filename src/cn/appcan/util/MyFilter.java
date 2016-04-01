package cn.appcan.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

/**
 * Servlet Filter implementation class MyFilter
 */
public class MyFilter implements Filter {

    /**
     * Default constructor. 
     */
    public MyFilter() {
        // TODO Auto-generated constructor stub 
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	static String path = "c:\\restful";
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res =  (HttpServletResponse)response;
		HttpServletRequest req = (HttpServletRequest)request;
		String url = req.getRequestURI();
		System.out.println(url+"----------------------------------------");
		if (url.indexOf("index.htm") != -1 || url.indexOf("readme.files") != -1){
			
			chain.doFilter(request, response);
			return;
		}
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();

		if (url.indexOf("path_linux") != -1){
			path = "/home/restful";
			out.println("file save "+path);
			out.flush();
			return;
		}
		if (url.indexOf("path_window") != -1){
			path = "c:\\restful";
			out.println("file save "+path);
			out.flush();
			return;
		}
		
		
		res.setCharacterEncoding("utf-8");
		//String path = "/home/restful";
		File f = new File(path);
		if (!f.exists()){
			f.mkdir();
		}
		
		
		int end = url.lastIndexOf("?");
		int start = url.lastIndexOf("/");
		if (end != -1){
			url = url.substring(start,end);
		}else{
			url = url.substring(start);
		}

		try {
			File x = new File(path+"/"+url+".txt");
			FileInputStream fis = new FileInputStream(x);
			BufferedReader br = new BufferedReader(new InputStreamReader(fis,"GBK"));
			String str = "";
			StringBuffer ss = new StringBuffer();
			str = br.readLine();
			while (str != null && str != ""){
				ss.append(str);
				str = br.readLine();
			}
			out.println(ss.toString());
			out.flush();	
			fis.close();
			
		} catch (Exception e) {
			out.println(" 使用说明：windows 下 需要 c:\\restful 目录存在！<br/>");
			out.println(" 使用说明：linux 下 需要 /home/restful 目录存在！");
			out.println(" tomcat 运行在linux 上，需要通过浏览器运行  http://localhost:8080/xxx/path_linux");
			out.println(" tomcat 运行在window 上，需要通过浏览器运行  http://localhost:8080/xxx/path_window");
			out.flush();	
			e.printStackTrace();
		}
		//	chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		path = fConfig.getInitParameter("path");
	}

}
