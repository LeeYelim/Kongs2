package kongs.model.service;

import java.sql.SQLException;
import java.util.List;
import kongs.dao.SignUpDAO;
import kongs.dao.SignUpDAOImpl;
import kongs.model.dto.Member;

public class SignUpService {
	private static SignUpDAO signupDao = new SignUpDAOImpl();
	
	/**
	 *  회원가입
	 */
	public static int signUp(Member member) throws SQLException {
		return signupDao.insertMember(member);
	}
	
	
	/**
	 * 회원조회(비밀번호확인)
	 */
	public static String searchPwdById(String id) throws SQLException {		
		return signupDao.SelectById(id).getPwd();
	}
	
	
	/**
	 *  전체 회원 정보 불러오기
	 */
	public static List<Member> selectAllMember() throws SQLException {
		return signupDao.selectAllMember();
	}
	
	
	/**
	 * 회원조회(ID중복확인)
	 */
	public static Member selectById(String id) throws SQLException {
		return signupDao.SelectById(id);
	}
	
	
	/**
	 * 회원탈퇴
	 */
	public static int withdrawal(String id) throws SQLException {
		return signupDao.deleteMember(id);
	}
	
	/**
	 * 회원정보수정
	 */
	public static int updateMember(Member member) throws SQLException {
		return signupDao.updateMember(member);
	}
	
	
}
