package kongs.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Member;
import kongs.model.service.SignUpService;

public class LoginIdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try{
		String id = request.getParameter("id");
		Member member = SignUpService.selectById(id); //null : 중복X , nullX : 중복O
		
			if(member==null){
				out.println("사용가능");
			}else{
				out.print("중복");
			}
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
	}

}
