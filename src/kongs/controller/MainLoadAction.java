package kongs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Contest;
import kongs.model.service.ContestService;
import kongs.model.service.SignUpService;
import kongs.model.service.TeamService;

public class MainLoadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="errorView/error.jsp";
		
		try{
			// 전체 공모전 수
			int size = ContestService.selectAll().size();
			request.setAttribute("size", size);
		 
			// 전체 팀 수
			int teamcount = TeamService.searchTeamCount();
			request.setAttribute("teamcount", teamcount);
			
			// 조회수 높은 4개 공모전
			List<Contest> list  = ContestService.selectTopClickCount();
			request.setAttribute("list", list);
			
			// 회원수
			int membercount = SignUpService.selectAllMember().size();
			request.setAttribute("membercount", membercount);
			url="/main.jsp";
		} catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
