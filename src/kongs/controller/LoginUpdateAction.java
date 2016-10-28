package kongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Member;
import kongs.model.service.SignUpService;
import oracle.net.aso.r;

public class LoginUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		try {
			
			String id = request.getParameter("id");
			String name = request.getParameter("uname");
			String pwd = request.getParameter("pass"); 
			String nick = request.getParameter("nick");
			String upwd = request.getParameter("modipass");
			 
			//System.out.println(id +"  " +pwd +"  " + nick +"  " + upwd);
			
			Member member = SignUpService.selectById(id);
			// ��й�ȣ ��ġ���� Ȯ��
			if(pwd.equals(member.getPwd())) { // ��й�ȣ�� ��ġ�ϸ�
				Member umember = new Member(id,pwd,upwd,name,nick, null);
				int result = SignUpService.updateMember(umember);
				request.getSession().setAttribute("user", umember);
				url="/index.jsp";
			
			} else { // ��й�ȣ�� ��ġ���� ������
				throw new Exception("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
