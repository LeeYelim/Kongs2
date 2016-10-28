package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Member;
import kongs.model.service.SignUpService;

/**
 *  회원가입
 */
public class SignUpAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		try {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pass");
			String name = request.getParameter("name");
			String nick = request.getParameter("nick");
			
			Member member = new Member(id,pwd,name,nick);
			
			int result = SignUpService.signUp(member);
			if(result==0) {
				throw new Exception("회원가입에 실패하였습니다.");
			} else {	// 회원가입에 성공
				// 가입 정보로 로그인 상태 유지
				// 메인페이지로 이동(로그인상태로) => loginAction으로 보내주기
				request.getSession().setAttribute("user", member);
				url="/index.jsp";
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
