package kongs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Contest;
import kongs.model.service.ContestService;

public class SelectContestAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="errorView/error.jsp";
		
		try{
		 List<Contest> list = ContestService.selectAll();
		 request.setAttribute("list", list);
		 url="/index.jsp";
		 
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
