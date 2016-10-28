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
			if(pwd.equals(member.getPwd())){//��й�ȣ�� ��ġ�Ѵٸ�
				int result = SignUpService.withdrawal(id);
				if(result>0){
					url="logout.jsp";
				}else{//��й�ȣ�� ��ġ���� �ʴٸ�
					throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
