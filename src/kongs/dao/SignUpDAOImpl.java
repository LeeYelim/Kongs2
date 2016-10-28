package kongs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kongs.model.dto.Member;
import kongs.util.DBUtil;

public class SignUpDAOImpl implements SignUpDAO {
	/**
	 *  회원가입
	 */
	@Override
	public int insertMember(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0 ;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("insert into member(id,pwd,name,nick) values(?,?,?,?)");
			ps.setString(1, member.getId());
			ps.setString(2, member.getPwd());
			ps.setString(3, member.getName());
			ps.setString(4, member.getNick());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		
		return result;
	}

	
	/**
	 *  회원 탈퇴
	 */
	@Override
	public int deleteMember(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("delete from member where id=?");
			ps.setString(1, id);
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		System.out.println(result);
		return result;
	}

	
	/**
	 *  회원정보수정
	 */
	@Override
	public int updateMember(Member member) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("update member set name=?, pwd=?, nick=? where id=?");
			ps.setString(1, member.getName());
			ps.setString(2, member.getUpwd());
			ps.setString(3, member.getNick());
			ps.setString(4, member.getId());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		
		return result;
	}

	
	/**
	 *  전체 회원 레코드 불러오기
	 */
	@Override
	public List<Member> selectAllMember() throws SQLException {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		List<Member> list = new ArrayList<>();
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from member");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Member(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("nick"), rs.getString("teamId")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return list;
	}

	
	/**
	 *  id로 회원정보 조회하기
	 */
	@Override
	public Member SelectById(String id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Member member = null;
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from member where id=?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				member =new Member(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("nick"), rs.getString("teamId"));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return member;
	}




}
