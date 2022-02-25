package com.shop.myapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.shop.myapp.dto.BoardDto;

public class BoardDao {
	DataSource dataSource;
	
	public BoardDao() {
//		db접속
		try {
			Context context=new InitialContext();
			dataSource=(DataSource) context.lookup("java:comp/env/jdbc/springxe");	
			System.out.println("dataSource 생성성공----------");		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("dataSource 생성오류");
		}	
	}
	public ArrayList<BoardDto> list() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardDto> dtos=new ArrayList<BoardDto>();
		
		
		try {
			con=dataSource.getConnection();
			
			String sql="select BOARD_ID, ITEM_CODE, MEMBER_ID,BOARD_TITLE, BOARD_CONTENT, RECOMMEND_ID "
					+ "from SHOP_QNABOARD_TB order by RECOMMEND_ID desc"; 
			
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int BOARD_ID=rs.getInt("BOARD_ID");
				int ITEM_CODE=rs.getInt("ITEM_CODE");
				String MEMBER_ID=rs.getString("MEMBER_ID");
				String BOARD_TITLE=rs.getString("BOARD_TITLE");
				String BOARD_CONTENT=rs.getString("BOARD_CONTENT");
				int RECOMMEND_ID=rs.getInt("RECOMMEND_ID");
								
				//생성자주입
				BoardDto dto=new BoardDto(BOARD_ID, ITEM_CODE, MEMBER_ID,
						BOARD_TITLE, BOARD_CONTENT, RECOMMEND_ID);
				//리스트에 추가
				dtos.add(dto);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dtos;
	}
	public void write(String BOARD_TITLE, String BOARD_CONTENT) {
//		 insert 작업
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=dataSource.getConnection();
			String sql="insert into SHOP_QNABOARD_TB " + 
					"values(SHOP_QNABOARD_TB_seq.nextval,30,'test2',?,?,100)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, BOARD_TITLE);
			pstmt.setString(2, BOARD_CONTENT);
			int rn=pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
		}
		
	}
//	public void upHit(String sbid) {
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		try {
//			con=dataSource.getConnection();
//			String sql="update replyboard " + 
//					"set bhit=bhit+1 where bid=?"; 
//			pstmt=con.prepareStatement(sql);
//			pstmt.setInt(1, Integer.parseInt(sbid));
//			pstmt.executeUpdate();
//			
//		}catch(Exception e) {
//			
//		} finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();	
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		
//	}
//	
	public BoardDto contentView(String sBOARD_ID) {
		
//		upHit(sBOARD_ID);//조회수 증가
		BoardDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			String sql="select BOARD_ID,ITEM_CODE,MEMBER_ID,BOARD_TITLE,BOARD_CONTENT,RECOMMEND_ID "
					+ "from SHOP_QNABOARD_TB where BOARD_ID=?"; 
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sBOARD_ID));
			rs=pstmt.executeQuery();
			
			rs.next();
			int BOARD_ID=rs.getInt("BOARD_ID");
			int ITEM_CODE=rs.getInt("ITEM_CODE");
			String MEMBER_ID=rs.getString("MEMBER_ID");
			String BOARD_TITLE=rs.getString("BOARD_TITLE");
			String BOARD_CONTENT=rs.getString("BOARD_CONTENT");
			int RECOMMEND_ID=rs.getInt("RECOMMEND_ID");
			
			//생성자주입
			dto=new BoardDto(BOARD_ID, ITEM_CODE, MEMBER_ID, BOARD_TITLE,
					BOARD_CONTENT, RECOMMEND_ID);	

			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return dto;

	}
//	public void modify(String BOARD_ID, String ITEM_CODE,
//			String BOARD_TITLE, String BOARD_CONTENT) {
//		
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		
//		try {
//			con=dataSource.getConnection();
//			String sql="update SHOP_QNABOARD_TB " + 
//					"set ITEM_CODE=?,BOARD_TITLE=?,BOARD_CONTENT=? " + 
//					"where BOARD_ID=?"; 
//			pstmt=con.prepareStatement(sql);
//			pstmt.setString(1, ITEM_CODE);
//			pstmt.setString(2, BOARD_TITLE);
//			pstmt.setString(3, BOARD_CONTENT);
//			pstmt.setInt(4, Integer.parseInt(BOARD_ID));
//			
//			pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//		} finally {
//			try {
//				if(pstmt!=null) pstmt.close();
//				if(con!=null) con.close();	
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}	
//	}
	public void delete(String BOARD_ID) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql="delete from SHOP_QNABOARD_TB where BOARD_ID=?"; 
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(BOARD_ID));
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	public BoardDto replyView(String sBOARD_ID) {
		
		BoardDto dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=dataSource.getConnection();
			String sql="select BOARD_ID,ITEM_CODE,MEMBER_ID,BOARD_TITLE,BOARD_CONTENT,RECOMMEND_ID "
					+ "from SHOP_QNABOARD_TB where BOARD_ID=?"; 
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(sBOARD_ID));
			rs=pstmt.executeQuery();
			
			rs.next();
			int BOARD_ID=rs.getInt("BOARD_ID");
			int ITEM_CODE=rs.getInt("ITEM_CODE");
			String MEMBER_ID=rs.getString("MEMBER_ID");
			String BOARD_TITLE=rs.getString("BOARD_TITLE");
			String BOARD_CONTENT=rs.getString("BOARD_CONTENT");
			int RECOMMEND_ID=rs.getInt("RECOMMEND_ID");
						
			dto=new BoardDto(BOARD_ID, ITEM_CODE, MEMBER_ID, BOARD_TITLE,
					BOARD_CONTENT, RECOMMEND_ID);	
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return dto;
	}
	
	public void replyShape(String RECOMMEND_ID) {

		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=dataSource.getConnection();
			String sql=	"where RECOMMEND_ID=?";			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(RECOMMEND_ID));
			pstmt.executeUpdate();	
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}	
	}
	public void reply(String BOARD_ID, String ITEM_CODE,
			String MEMBER_ID, String BOARD_TITLE,
			String BOARD_CONTENT, String RECOMMEND_ID) {
		
		replyShape(RECOMMEND_ID);
				
		Connection con=null;
		PreparedStatement pstmt=null;	
		try {
			con=dataSource.getConnection();
			String sql="insert into SHOP_QNABOARD_TB(BOARD_ID,ITEM_CODE,MEMBER_ID,BOARD_TITLE,BOARD_CONTENT,RECOMMEND_ID) " + 
					"values(SHOP_QNABOARD_TB_seq.nextval,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,ITEM_CODE);
			pstmt.setString(2,MEMBER_ID);
			pstmt.setString(3,BOARD_TITLE);
			pstmt.setString(4,BOARD_CONTENT);
			pstmt.setInt(5,Integer.parseInt(RECOMMEND_ID));
						
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();	
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
	
}
