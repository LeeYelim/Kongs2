package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		try {
			//TODO 회원정보수정
			//TODO 세션 리뉴얼
			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
