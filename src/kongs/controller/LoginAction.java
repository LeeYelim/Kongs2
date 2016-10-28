package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Member;
import kongs.model.service.SignUpService;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		try {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pass");
			
			Member member = SignUpService.selectById(id);
			// 비밀번호 일치여부 확인
			if(pwd.equals(member.getPwd())) { // 비밀번호가 일치하면
				
				request.getSession().setAttribute("user", member);
				url="/index.jsp";
				
			} else { // 비밀번호가 일치하지 않으면
				throw new Exception("비밀번호가 일치하지 않습니다.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
