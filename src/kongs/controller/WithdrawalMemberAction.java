package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  È¸¿øÅ»Åð
 */
public class WithdrawalMemberAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		try {
			//TODO È¸¿øÅ»Åð¸¦ À§ÇØ ºñ¹Ð¹øÈ£ È®ÀÎ ÇÑ¹ø´õ
			//TODO È¸¿øÅ»Åð
			//TODO ¼¼¼Ç invalidate() ·Î±×¾Æ¿ô
			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
