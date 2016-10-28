package kongs.model.service;

import java.sql.SQLException;
import java.util.List;

import kongs.dao.ContestDAOimpl;
import kongs.model.dto.Contest;

public class ContestService {
	private static ContestDAOimpl contestDao = new ContestDAOimpl();
	
	/**
	 * Contest�� �ִ� ��ü���ڵ� ��ȸ
	 */
	public static List<Contest> selectAll() throws SQLException {
		return contestDao.selectAll();
	}
	
	/**
	 * Contest�� �ִ� ���ڵ� �� ��ȸ�� ���� 4�� ����
	 * */
	public static List<Contest> selectTopClickCount() throws SQLException {
		return contestDao.selectTopClickCount();
	}
	
	/**
	 *  Contest�� �ִ� ��ü ���ڵ� ����¡���� ��ȸ
	 */
	public static List<Contest> getContestList(String pageNum, String keyField, String keyWord) throws SQLException {
		return contestDao.getContestList(pageNum, keyField, keyWord);
	}
	
	
	/**
	 * Contest�� ���ڵ� ����
	 * ������ ���� �Խ����� �Խñ� ���
	 */
	public static int insert(Contest contest) throws SQLException {
		return contestDao.insert(contest);
	}
	
	
	/**
	 * Contest�� ���ڵ� ����
	 * ������ ���� �Խ����� �Խñ� ����
	 */
	public static int update(Contest contest) throws SQLException {
		return contestDao.update(contest);
	}
	
	
	/**
	 * Contest�� ���ڵ� ����
	 * ������ ���� �Խ����� �Խñ� ����
	 */
	public static int delete(String contestNum) throws SQLException {
		return contestDao.delete(contestNum);
	}
	
	/**
	 *  ������ ���� �Խ��ǿ��� �������� �� ��������
	 */
	public static Contest selectByTitle(String title, boolean flag) throws SQLException {
		return contestDao.selectByTitle(title, flag);
	}
	
	/**
	 * D-day 
	 */
	public static List<Contest> dday(String category) throws SQLException{
		return contestDao.dday(category);
	}
}
