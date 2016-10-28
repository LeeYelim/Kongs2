package kongs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Board;
import kongs.model.service.BoardService;

public class FreeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="errorView/error.jsp";
		try{
			String keyField="", keyWord="";
		
			if(request.getParameter("keyWord") !=null){
			  keyField = request.getParameter("keyField");
		      keyWord = request.getParameter("keyWord");
			}
			
			//넘어오는 값 받기
			String pageNum = request.getParameter("pageNum");
		    if(pageNum==null) pageNum="1";
		    
			List<Board> boardList = BoardService.getBoardList(pageNum, keyField, keyWord, "freeboard");

			request.setAttribute("boardList", boardList);
			
			url="freeBoard.jsp";
			
		} catch(SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}