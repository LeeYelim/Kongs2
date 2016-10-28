package kongs.dao;

import java.sql.SQLException;
import java.util.List;

import kongs.model.dto.TeamBoard;

public interface TeamBoardDAO {
	
	/**
	 * 팀 수 구하기
	 * select distinct count(teamid) from member;
	 * */
	int searchTeamCount() throws SQLException;
	
	/**
	 * 팀의 존재 여부 확인
	 */
	int searchTeam(String id) throws SQLException;
	
	/**
	 * 전체 레코드 검색
	 */
	List<TeamBoard> selectAll(String teamId) throws SQLException;

	/**
	 * 레코드 삽입
	 */
	int insert(TeamBoard teamBoard) throws SQLException;
	
	/**
	 * 해당 레코드 삭제
	 */
	int delete(String boardNum, String teamId) throws SQLException;
	
	/**
	 * 해당 레코드 수정
	 */
	int update(TeamBoard teamBoard) throws SQLException;

	
}
