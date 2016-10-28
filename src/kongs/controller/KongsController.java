package kongs.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/front")
public class KongsController extends HttpServlet {
	
	// map
	Map<String, Action> map;
	
	@Override
	public void init() throws ServletException {
		map = (Map<String, Action>)super.getServletContext().getAttribute("map");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//parameter로 넘어오는 command 값 받기
		String key = request.getParameter("command");//encType있을경우 null
		if(key==null) key="main";
		Action action = map.get(key);
		action.execute(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
