package kongs.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kongs.model.dto.Contest;
import kongs.model.service.ContestService;

public class ContestLoadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "errorView/error.jsp";

		try {
			// 넘어오는 값 받기
			String keyField = request.getParameter("keyField");
			String keyWord =  request.getParameter("keyWord");
			String pageNum = request.getParameter("pageNum");
			List<Contest> pageList = new ArrayList<>();
			if (pageNum == null)
				pageNum = "1";

			/*if (request.getParameter("keyWord") != null) {
				keyField = request.getParameter("keyField");
				keyWord = request.getParameter("keyWord");
			} else if(request.getParameter("keyWord")==null && request.getParameter("keyField")==null) {
				// 전체 공모전 리스트
				pageList = ContestService.getContestList(pageNum, keyField, keyWord);
			} else {
				keyField = request.getParameter("keyField");
				pageList = ContestService.getContestList(pageNum, keyField, "");
			}
*/
			pageList = ContestService.getContestList(pageNum, keyField, keyWord);
			// 조회수 높은 8개 공모전
			List<Contest> list = ContestService.selectTopClickCount();
						
			// 디데이
			//List<Contest> ddayList = ContestService.dday(keyField);

			/*System.out.println("ddayList.size" + ddayList.size());
			System.out.println("pageList.size" + pageList.size());
			for (int i = 0; i < ddayList.size(); i++) {
				pageList.get(i).setDday(ddayList.get(i).getDday());
			}*/
			request.setAttribute("list", list);
			request.setAttribute("pageList", pageList);

			url = "Competition/Competition.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

	
}
