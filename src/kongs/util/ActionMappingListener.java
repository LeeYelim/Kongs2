package kongs.util;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import kongs.controller.Action;

/**
 * Application Lifecycle Listener implementation class ActionMappingListener
 *
 */
@WebListener
public class ActionMappingListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent arg0)  { }
    public void contextInitialized(ServletContextEvent e)  { 
    	// 1. proerties 파일 읽어서 map에 저장
    	ServletContext application = e.getServletContext();
    	
    	// 초기 매개변수
    	String fileName = application.getInitParameter("fileName");
    	
    	// properties 파일 읽기
    	ResourceBundle rb = ResourceBundle.getBundle("/kongs/util/"+fileName); // classes폴더를 기준으로 properties의 경로명!-확장자 생략
    	
    	// key에 해당하는 value 객체로 만들기
    	Map<String, Action> map = new HashMap<>();
    	for(String key : rb.keySet()) {
    		String className = rb.getString(key);
    		try {
				Action ac = (Action)Class.forName(className).newInstance();
				map.put(key, ac);
			} catch (Exception ex) {
				ex.printStackTrace();
			} 
    	}
    	// application에 저장
    	application.setAttribute("map", map);
    }
	
}










