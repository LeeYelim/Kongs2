package kongs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kongs.model.dto.Board;
import kongs.util.DBUtil;
import kongs.util.FindPageAction;
import kongs.util.FreePageAction;
import kongs.util.PageAction;

public class BoardDAOImpl implements BoardDAO {

	@Override
	public List<Board> searchBoardAll() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from board");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Board(rs.getInt("boardNum"), rs.getString("category"), 
						rs.getString("title"), rs.getString("contents"), rs.getString("writer"), 
						rs.getString("regDate"), rs.getInt("clickCount"), rs.getString("fileName")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Board> searchByCategory(String category) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from board where category=?");
			ps.setString(1, category);
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Board(rs.getInt("boardNum"), rs.getString("category"), 
						rs.getString("title"), rs.getString("contents"), rs.getString("writer"), 
						rs.getString("regDate"), rs.getInt("clickCount"), rs.getString("fileName")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public List<Board> searchTopClickCount() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Board> list = new ArrayList<>();
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from board where rownum<=6 order by clickcount desc ");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(new Board(rs.getInt("boardNum"), rs.getString("category"), 
						rs.getString("title"), rs.getString("contents"), rs.getString("writer"), 
						rs.getString("regDate"), rs.getInt("clickCount"), rs.getString("fileName")));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return list;
	}

	@Override
	public Board searchBoard(int boardNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Board board = null;
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("select * from board where boardnum=?");
			ps.setInt(1, boardNum);
			rs = ps.executeQuery();
			while(rs.next()) {
				board = new Board(rs.getInt("boardNum"), rs.getString("category"), rs.getString("title"), rs.getString("contents"), rs.getString("writer"), 
						rs.getString("regDate"), rs.getInt("clickCount"), rs.getString("fileName"));
			}
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		return board;
	}

	@Override
	public int insertBoard(Board board) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		String sql="";
		try{
			con = DBUtil.getConnection();
			if(board.getCategory().equals("free")) {
				sql = "insert into Board values(?, sysdate, ?, 0, ?, ?, seq_freeBoard.nextval, ?)";
			} else if(board.getCategory().equals("find")) {
				sql = "insert into Board values(?, sysdate, ?, 0, ?, ?, seq_findBoard.nextval, ?)";
			}
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContents());
			ps.setString(4, board.getFileName());
			ps.setString(5, board.getCategory());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateBoard(Board board) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("update board ftitle=?, contents=?, filename=? where boardnum=? and category=?");
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContents());
			ps.setString(3, board.getFileName());
			ps.setInt(4, board.getBoardNum());
			ps.setString(5, board.getCategory());
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int deleteBoard(int boardNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("delete from board where boardnum=?");
			ps.setInt(1, boardNum);
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		return result;
	}

	@Override
	public int updateClickCount(int boardNum) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try{
			con = DBUtil.getConnection();
			ps = con.prepareStatement("update board set clickcount=clickcount+1 where boardnum=?");
			ps.setInt(1, boardNum);
			result = ps.executeUpdate();
		} finally {
			DBUtil.dbClose(con, ps, null);
		}
		return result;
	}
	
	/**
	 * 페이징 활용하여 특정 갯수 만큼 글가져오기 
	 */
	@Override
	public List<Board> getBoardList(String pageNum, String keyField, String keyWord, String category) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Board> list = new ArrayList<>();

		String sql_count = "";
		String sql_Result = "";

		int totalCount = 0;// 전체레코드수
		int absolute = 0;// 게시물의 시작점

		try {
			FreePageAction.keyField = keyField;
			FindPageAction.keyField = keyField;
			FreePageAction.keyWord = keyWord;
			FindPageAction.keyWord = keyWord;

			// 검색일때와 검색아닐때의 쿼리문 구분하기
			if (keyWord.equals("")) {// 검색아닐때
				sql_count = "select count(*) from board where category='"+category+"'";
				sql_Result = "select boardnum,ftitle,writer,to_char(regdate,'YYYY-MM-DD'),clickcount from board where category='"+category+"' order by boardnum desc";
				
			} else {// 검색인 경우
				sql_count = "select count(*) from board where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' and category='"+category+"'";
				sql_Result = "select boardnum,ftitle,writer,to_char(regdate,'YYYY-MM-DD'),clickcount from board where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' and category='"+category+"' order by boardnum desc";
			}

			// 전체레코드수 구하기
			con = DBUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql_count);
			if (rs.next())
				totalCount = rs.getInt(1);
			rs.close();
			st.close();
			
			if("freeboard".equals(category)) {
				// 현재페이지번호를 PageAction의 pageNum에 저장한다.
				FreePageAction.pageNum = Integer.parseInt(pageNum);
	
				// 총페이지수 구하기
				FreePageAction.pageCount = (int) Math.ceil((double) totalCount / FreePageAction.pageSize);
				
				// 페이지번호에 해당하는 게시물의 시작점 구한다.
				absolute = (FreePageAction.pageNum - 1) * FreePageAction.pageSize + 1;

			} else if("findboard".equals(category)) {
				// 현재페이지번호를 PageAction의 pageNum에 저장한다.
				FindPageAction.pageNum = Integer.parseInt(pageNum);

				// 총페이지수 구하기
				FindPageAction.pageCount = (int) Math.ceil((double) totalCount / FindPageAction.pageSize);
				
				// 페이지번호에 해당하는 게시물의 시작점 구한다.
				absolute = (FindPageAction.pageNum - 1) * FindPageAction.pageSize + 1;

			}
			
			// 특정 행에서 부터 레코드 가져오기
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql_Result);
			if (rs.next()) {// 레코드가 존재한다면
				rs.absolute(absolute);// 특정위치의 행으로 이동한다.
				int count = 0;
				int size = 0;
				if("freeboard".equals(category)) {
					size = FreePageAction.pageSize;
				} else if("findboard".equals(category)) {
					size = FindPageAction.pageSize;					
				}
				while (count < size) {
					Board board = new Board();
					board.setBoardNum(rs.getInt(1));
					board.setTitle(rs.getString(2));
					board.setWriter(rs.getString(3));
					board.setRegDate(rs.getString(4));
					board.setClickCount(rs.getInt(5));
							
					list.add(board);
					
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
	 * 글 제목으로 board 내용가져오기
	 * boolean flag : true 이면 조회수 증가, false이면 조회수 증가 x
	 */
	@Override
	public Board selectByTitle(String title, boolean flag) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		Board board = null;
		
		try {
			con = DBUtil.getConnection();
			if(flag) {
				ps = con.prepareStatement("update board set clickcount=clickcount+1 where ftitle=?");
				ps.setString(1, title);
				ps.executeUpdate();
			}
			
			ps = con.prepareStatement("select * from board where ftitle=?");
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new Board(rs.getInt("boardNum"), rs.getString("category"), rs.getString("title"), rs.getString("contents"), rs.getString("writer"), 
						rs.getString("regDate"), rs.getInt("clickCount"), rs.getString("fileName"));
			}
			
		} finally {
			DBUtil.dbClose(con, ps, rs);
		}
		
		return board;
	}

}
