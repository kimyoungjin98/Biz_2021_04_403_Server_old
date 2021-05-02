package com.callor.book.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.callor.book.model.BookDTO;
import com.callor.book.model.BookVO;
import com.callor.book.service.BookService;
import com.callor.book.service.DBContract;

/*
 * DB�� �뿰�룞�븳 Service
 * DB�뿰寃곗쓣 �븯怨� 
 * SQL�쓣 �옉�꽦�븯怨�
 * SQL Packing�븯怨� 
 * Packing�맂 媛앹껜瑜� �궗�슜�븯�뿬 SQL�쓣 �떎�뻾�븯怨�
 * 
 * 議고쉶�븷 寃쎌슦�뒗 �닔�떊�맂 �뜲�씠�꽣瑜� 泥섎━
 */
public class BookServiceImplV1 implements BookService{
	
	protected Connection dbConn;
	
	public BookServiceImplV1() {
		this.dbConn = DBContract.getDBConnection();
	}
 
	@Override
	public void insert(BookVO bookVO) {
		// TODO �룄�꽌�젙蹂� 異붽�
		
		String sql = " INSERT INTO tbl_books ";
		sql += "(bk_isbn, bk_title, bk_ccode, "
				+ "bk_acode, bk_date, bk_price, bk_pages)";
		sql += " VALUES(?,?,?,?,?,?,?) ";
		
		System.out.println(sql);
		
		// sql = "(" + bookVO.getBk_isbn() + "," + bookVO.getBk_title();
				
		// String type�쓽 SQL 紐낅졊臾몄쓣
		// Oracle�뿉 �쟾�넚�븯湲� �쐞�빐 Packet �쑝濡� 留뚮뱾湲�
		PreparedStatement pStr = null;
		
		try {
			
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bookVO.getBk_isbn());
			pStr.setString(2, bookVO.getBk_title());
			pStr.setString(3, bookVO.getBk_ccode());
			pStr.setString(4, bookVO.getBk_acode());
			pStr.setString(5, bookVO.getBk_date());
			pStr.setInt(6, bookVO.getBk_price());
			pStr.setInt(7, bookVO.getBk_pages());
			
			pStr.executeUpdate();
			
			pStr.close();
			System.out.println("Insert OK !!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<BookDTO> select() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookDTO findById(String bk_isbn) {
		// TODO �룄�꽌 寃��깋
		
		String sql = " SELECT * FROM view_�룄�꽌�젙蹂�";
		sql += " WHERE ISBN = ? ";
		
		PreparedStatement pStr = null;
		
		try {
			pStr = dbConn.prepareStatement(sql);
			pStr.setString(1, bk_isbn.trim());
			ResultSet result = pStr.executeQuery();
			
			if(result.next()) {
				BookDTO bookDTO = new BookDTO();
				bookDTO.setBk_isbn(result.getString("ISBN"));
				bookDTO.setBk_title(result.getString("�룄�꽌紐�"));
				bookDTO.setBk_cname(result.getString("異쒗뙋�궗紐�"));
				bookDTO.setBk_author(result.getString("異쒗뙋�궗���몴"));
				bookDTO.setBk_cceo(result.getString("���옄紐�"));
				bookDTO.setBk_au_tel(result.getString("���옄�뿰�씫泥�"));
				return bookDTO;
				
			} else {
				return null;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	
	@Override
	public void update(BookVO bookVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(String bk_isbn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<BookDTO> findByTitle(String bk_isbn) {
		// TODO Auto-generated method stub
		return null;
	}

}
