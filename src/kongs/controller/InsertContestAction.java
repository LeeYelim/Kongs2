package kongs.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kongs.model.dto.Contest;
import kongs.model.service.ContestService;

public class InsertContestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";
		
		// ������
		String saveDir = request.getServletContext().getRealPath("/save/contest");	
		
		// ����ũ��(100mb)
		int maxSize = 1024*1024*100;
		
		// ���ڵ�
		String encoding = "UTF-8";
		
		MultipartRequest mr = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		File f = mr.getFile("fileName");
		
		String title = mr.getParameter("name");
		String category = mr.getParameter("category");
		String sponser = mr.getParameter("company");
		String startDate = mr.getParameter("startDate");
		String endDate = mr.getParameter("endDate");
		
		System.out.println(title + " " + category + " " + sponser + " " + startDate + " " + endDate + " ");
		
		try {
		
			// �Է�����üũ (�ٷ� �����ϴ� ���� ���� ����)
			if(title==null || category==null || sponser==null || startDate==null || endDate==null) {
				throw new Exception("�Է°��� ������� �ʽ��ϴ�.");
			}
			
			Contest contest = new Contest(title, category, sponser, startDate, endDate);
			
			if(mr.getFilesystemName("fileName") != null) {
				String fname = mr.getOriginalFileName("fileName");
				contest.setPhotoName(fname);
			}
			
			if(ContestService.insert(contest)>0) {
				System.out.println("���Լ���");
				response.sendRedirect("front?command=contest");
				return;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}

		request.getRequestDispatcher(url).forward(request, response);

	}

}
