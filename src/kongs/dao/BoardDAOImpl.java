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
	 * ����¡ Ȱ���Ͽ� Ư�� ���� ��ŭ �۰������� 
	 */
	@Override
	public List<Board> getBoardList(String pageNum, String keyField, String keyWord, String category) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		List<Board> list = new ArrayList<>();

		String sql_count = "";
		String sql_Result = "";

		int totalCount = 0;// ��ü���ڵ��
		int absolute = 0;// �Խù��� ������

		try {
			FreePageAction.keyField = keyField;
			FindPageAction.keyField = keyField;
			FreePageAction.keyWord = keyWord;
			FindPageAction.keyWord = keyWord;

			// �˻��϶��� �˻��ƴҶ��� ������ �����ϱ�
			if (keyWord.equals("")) {// �˻��ƴҶ�
				sql_count = "select count(*) from board where category='"+category+"'";
				sql_Result = "select boardnum,ftitle,writer,to_char(regdate,'YYYY-MM-DD'),clickcount from board where category='"+category+"' order by boardnum desc";
				
			} else {// �˻��� ���
				sql_count = "select count(*) from board where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' and category='"+category+"'";
				sql_Result = "select boardnum,ftitle,writer,to_char(regdate,'YYYY-MM-DD'),clickcount from board where " + keyField.trim() + " like '%" + keyWord.trim()
						+ "%' and category='"+category+"' order by boardnum desc";
			}

			// ��ü���ڵ�� ���ϱ�
			con = DBUtil.getConnection();
			st = con.createStatement();
			rs = st.executeQuery(sql_count);
			if (rs.next())
				totalCount = rs.getInt(1);
			rs.close();
			st.close();
			
			if("freeboard".equals(category)) {
				// ������������ȣ�� PageAction�� pageNum�� �����Ѵ�.
				FreePageAction.pageNum = Integer.parseInt(pageNum);
	
				// ���������� ���ϱ�
				FreePageAction.pageCount = (int) Math.ceil((double) totalCount / FreePageAction.pageSize);
				
				// ��������ȣ�� �ش��ϴ� �Խù��� ������ ���Ѵ�.
				absolute = (FreePageAction.pageNum - 1) * FreePageAction.pageSize + 1;

			} else if("findboard".equals(category)) {
				// ������������ȣ�� PageAction�� pageNum�� �����Ѵ�.
				FindPageAction.pageNum = Integer.parseInt(pageNum);

				// ���������� ���ϱ�
				FindPageAction.pageCount = (int) Math.ceil((double) totalCount / FindPageAction.pageSize);
				
				// ��������ȣ�� �ش��ϴ� �Խù��� ������ ���Ѵ�.
				absolute = (FindPageAction.pageNum - 1) * FindPageAction.pageSize + 1;

			}
			
			// Ư�� �࿡�� ���� ���ڵ� ��������
			st = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

			rs = st.executeQuery(sql_Result);
			if (rs.next()) {// ���ڵ尡 �����Ѵٸ�
				rs.absolute(absolute);// Ư����ġ�� ������ �̵��Ѵ�.
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
	 * �� �������� board ���밡������
	 * boolean flag : true �̸� ��ȸ�� ����, false�̸� ��ȸ�� ���� x
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
