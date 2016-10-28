package kongs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kongs.model.dto.Contest;
import kongs.util.DBUtil;
import kongs.util.PageAction;

public class ContestDAOimpl implements ContestDAO {

	/**
	 * Contest�� �ִ� ��ü���ڵ� ��ȸ
	 */
	@Override
	public List<Contest> selectAll() throws SQLException {

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<Contest> list = new ArrayList<>();
		try {

			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from contest");
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(new Contest(rs.getInt("contestnum"), rs.getString("title"), rs.getString("sponser"),
						rs.getString("category"), rs.getString("startDay"), rs.getString("endDay"),
						rs.getInt("clickCount"), rs.getString("regDate"), rs.getString("photoName")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}

		return list;
	}

	/**
	 * Contest�� �ִ� ���ڵ� �� ��ȸ���� ���� 8���� ����
	 * */
	@Override
	public List<Contest> selectTopClickCount() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contest> list = new ArrayList<>();
		
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from contest where rownum<=8 order by clickcount");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Contest(rs.getInt("contestnum"), rs.getString("title"), 
						rs.getString("sponser"), rs.getString("category"), rs.getString("startDay"), 
						rs.getString("endDay"), rs.getInt("clickCount"), rs.getString("regDate"), 
						rs.getString("photoName")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	/**
	 * ����¡ Ȱ���Ͽ� Ư�� ���� ��ŭ �� ��������
	 */
	public List<Contest> getContestList(String pageNum, String keyField, String keyWord) throws SQLException  {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Contest> list = new ArrayList<>();

		String sql_count = "";
		String sql_Result = "";

		int totalCount = 0;// ��ü���ڵ��
		int absolute = 0;// �Խù��� ������

		try {
			PageAction.keyField = keyField;
			PageAction.keyWord = keyWord;

			// �˻��϶��� �˻��ƴҶ��� ������ �����ϱ�
			if(keyField!=null) { // ī�װ� �˻�
				sql_count = "select count(*) from contest where category like '%"+keyField.trim() +"%'";
				sql_Result = "select title, sponser, to_char(startday,'YYYY-MM-DD'), to_char(endday,'YYYY-MM-DD'), photoname, clickcount, category, regdate, contestnum from contest where category like '%"+keyField.trim() +"%'";
			} else if (keyWord==null) {// �˻��ƴҶ�
				sql_count = "select count(*) from contest";
				sql_Result = "select title,sponser,to_char(startday,'YYYY-MM-DD'),to_char(endday,'YYYY-MM-DD'),photoname,clickcount,category,regdate,contestnum from contest order by contestnum desc";
				
			} else {// �˻��� ���
				sql_count = "select count(*) from contest where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%'";
				sql_Result = "select * from contest where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' order by contestnum";
			}

			// ��ü���ڵ�� ���ϱ�
			con = DBUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql_count);
			if (rs.next())
				totalCount = rs.getInt(1);
			rs.close();
			st.close();

			// ������������ȣ�� PageAction�� pageNum�� �����Ѵ�.
			PageAction.pageNum = Integer.parseInt(pageNum);

			// ���������� ���ϱ�
			PageAction.pageCount = (int) Math.ceil((double) totalCount / PageAction.pageSize);

			// ��������ȣ�� �ش��ϴ� �Խù��� ������ ���Ѵ�.
			absolute = (PageAction.pageNum - 1) * PageAction.pageSize + 1;

			// Ư�� �࿡�� ���� ���ڵ� ��������
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql_Result);
			if (rs.next()) {// ���ڵ尡 �����Ѵٸ�
				rs.absolute(absolute);// Ư����ġ�� ������ �̵��Ѵ�.
				int count = 0;
				while (count < PageAction.pageSize) {
					Contest contest = new Contest();
					contest.setTitle(rs.getString(1));
					contest.setSponser(rs.getString(2));
					contest.setStartDate(rs.getString(3));
					contest.setEndDate(rs.getString(4));
					contest.setPhotoName(rs.getString(5));
					contest.setClickCount(rs.getInt(6));
					contest.setCategory(rs.getString(7));
					contest.setRegDate(rs.getString(8));
					contest.setNum(rs.getInt(9));
					
					System.out.println(contest.getRegDate());
					list.add(contest);

					if (rs.isLast())
						break;
					else
						rs.next();

					count++;

				} // while����

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e + "=> getBoardList�߻� ");
		} finally {
			DBUtil.dbClose(con,st,rs);
		}

		return list;
	}

	/**
	 * Contest�� ���ڵ� ���� ������ ���� ���
	 */
	@Override
	public int insert(Contest contest) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			con = DBUtil.getConnection();
			ps = con.prepareStatement(
					"insert into contest(title,sponser,startday,endday,photoname,clickcount,category,regdate,contestnum) "
							+ "values(?,?,?,?,?,?,?,sysdate,seq_contestBoard.nextval)");
			ps.setString(1, contest.getTitle());
			ps.setString(2, contest.getSponser());
			ps.setString(3, contest.getStartDate());
			ps.setString(4, contest.getEndDate());
			ps.setString(5, contest.getPhotoName());
			ps.setInt(6, contest.getClickCount());
			ps.setString(7, contest.getCategory());
			result = ps.executeUpdate();

		} finally {
			DBUtil.dbClose(con, ps, null);
		}

		return result;
	}

	/**
	 * Contest�� ���ڵ� ���� ������ ���� ����
	 */
	@Override
	public int update(Contest contest) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			con = DBUtil.getConnection();
			ps = con.prepareStatement(
					"update contest set sponser=?,startday=?,endday=?,photonum=?, category=? where contestnum=?");
			ps.setString(1, contest.getSponser());
			ps.setString(2, contest.getStartDate());
			ps.setString(3, contest.getEndDate());
			ps.setString(4, contest.getPhotoName());
			ps.setString(5, contest.getCategory());
			ps.setInt(6, contest.getNum());
			result = ps.executeUpdate();

		} finally {
			DBUtil.dbClose(con, ps, null);
		}

		return result;
	}

	/**
	 * Contest�� ���ڵ� ���� ������ ���� �Խ����� �Խñ� ����
	 */
	@Override
	public int delete(String contestNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;

		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement("delete from contest where contestnum=?");
			ps.setInt(1, Integer.parseInt(contestNum));
			result = ps.executeUpdate();

		} finally {
			DBUtil.dbClose(con, ps, null);
		}

		return result;
	}
	
	/**
	 * D-day 
	 */
	public List<Contest> dday(String category) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Contest> list = new ArrayList<>();
		String sql="";
		try{
			con = DBUtil.getConnection();
			
			if(category==null) {
				sql = "select contestnum, trunc(endday-sysdate), startday, endday from contest";
				ps = con.prepareStatement(sql);
			} else {
				sql = "select contestnum, trunc(endday-sysdate), startday, endday from contest where category=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, category);
			} 
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new Contest(rs.getInt(1), rs.getString(3), rs.getString(4), rs.getString(2)));
			}
		}finally{
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
		
	}

	/**
	 *  ������ ���� �Խ��ǿ��� �������� �� ��������
	 *  flag : true�� ��ȸ�� ����
	 *  	   false�� ��ȸ�� �������� ����
	 */
	@Override
	public Contest selectByTitle(String title, boolean flag) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Contest contest = null;
		
		try {
			con = DBUtil.getConnection();
			if(flag) {	//��ȸ������
				ps = con.prepareStatement("update contest set clickcount=clickcount+1 where title=?");
				ps.setString(1, title);
				ps.executeUpdate();
			}
				
			ps = con.prepareStatement("select * from contest where title=?");
			ps.setString(1, title);
			rs = ps.executeQuery();
			if(rs.next()) {
				contest = new Contest(rs.getInt("contestnum"), rs.getString("title"), 
						rs.getString("sponser"), rs.getString("category"), rs.getString("startDay"), 
						rs.getString("endDay"), rs.getInt("clickCount"), rs.getString("regDate"), 
						rs.getString("photoName"));
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return contest;
	}
}
