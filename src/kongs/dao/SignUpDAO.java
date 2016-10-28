package kongs.dao;

import java.sql.SQLException;
import java.util.List;

import kongs.model.dto.Member;

public interface SignUpDAO {
	
	/**
	 *  회원가입
	 */
	int insertMember(Member member) throws SQLException;
	
	
	/**
	 *  회원탈퇴
	 */
	int deleteMember(String id) throws SQLException;
	
	
	/**
	 *  회원 정보 수정
	 */
	int updateMember(Member member) throws SQLException;

	
	/**
	 *  전체 회원 정보 불러오기
	 */
	List<Member> selectAllMember() throws SQLException;
	
	
	/**
	 *  id로 회원 정보 가져오기
	 */
	Member SelectById(String id) throws SQLException;


}
