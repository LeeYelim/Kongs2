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
	 * Contest에 있는 전체레코드 조회
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
	 * Contest에 있는 레코드 중 조회수가 높은 8개만 추출
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
	 * 페이징 활용하여 특정 갯수 만큼 글 가져오기
	 */
	public List<Contest> getContestList(String pageNum, String keyField, String keyWord) throws SQLException  {

		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Contest> list = new ArrayList<>();

		String sql_count = "";
		String sql_Result = "";

		int totalCount = 0;// 전체레코드수
		int absolute = 0;// 게시물의 시작점

		try {
			PageAction.keyField = keyField;
			PageAction.keyWord = keyWord;

			// 검색일때와 검색아닐때의 쿼리문 구분하기
			if(keyField!=null) { // 카테고리 검색
				sql_count = "select count(*) from contest where category like '%"+keyField.trim() +"%'";
				sql_Result = "select title, sponser, to_char(startday,'YYYY-MM-DD'), to_char(endday,'YYYY-MM-DD'), photoname, clickcount, category, regdate, contestnum from contest where category like '%"+keyField.trim() +"%'";
			} else if (keyWord==null) {// 검색아닐때
				sql_count = "select count(*) from contest";
				sql_Result = "select title,sponser,to_char(startday,'YYYY-MM-DD'),to_char(endday,'YYYY-MM-DD'),photoname,clickcount,category,regdate,contestnum from contest order by contestnum desc";
				
			} else {// 검색인 경우
				sql_count = "select count(*) from contest where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%'";
				sql_Result = "select * from contest where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' order by contestnum";
			}

			// 전체레코드수 구하기
			con = DBUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql_count);
			if (rs.next())
				totalCount = rs.getInt(1);
			rs.close();
			st.close();

			// 현재페이지번호를 PageAction의 pageNum에 저장한다.
			PageAction.pageNum = Integer.parseInt(pageNum);

			// 총페이지수 구하기
			PageAction.pageCount = (int) Math.ceil((double) totalCount / PageAction.pageSize);

			// 페이지번호에 해당하는 게시물의 시작점 구한다.
			absolute = (PageAction.pageNum - 1) * PageAction.pageSize + 1;

			// 특정 행에서 부터 레코드 가져오기
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql_Result);
			if (rs.next()) {// 레코드가 존재한다면
				rs.absolute(absolute);// 특정위치의 행으로 이동한다.
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

				} // while문끝

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e + "=> getBoardList발생 ");
		} finally {
			DBUtil.dbClose(con,st,rs);
		}

		return list;
	}

	/**
	 * Contest에 레코드 삽입 공모전 정보 등록
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
	 * Contest에 레코드 수정 공모전 정보 수정
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
	 * Contest에 레코드 삭제 공모전 정보 게시판의 게시글 삭제
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
	 *  공모전 정보 게시판에서 제목으로 글 가져오기
	 *  flag : true면 조회수 증가
	 *  	   false면 조회수 증가하지 않음
	 */
	@Override
	public Contest selectByTitle(String title, boolean flag) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Contest contest = null;
		
		try {
			con = DBUtil.getConnection();
			if(flag) {	//조회수증가
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
