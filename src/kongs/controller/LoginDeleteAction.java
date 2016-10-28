package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Member;
import kongs.model.service.SignUpService;

public class LoginDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		try{
			String id = request.getParameter("id");
			String pwd = request.getParameter("pass").trim();
			Member member = SignUpService.selectById(id);
			System.out.println(member);
			if(pwd.equals(member.getPwd())){//비밀번호가 일치한다면
				int result = SignUpService.withdrawal(id);
				if(result>0){
					url="logout.jsp";
				}else{//비밀번호가 일치하지 않다면
					throw new Exception("비밀번호가 일치하지 않습니다.");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
