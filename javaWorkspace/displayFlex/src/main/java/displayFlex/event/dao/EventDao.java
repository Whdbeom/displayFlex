package displayFlex.event.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import displayFlex.coupon.vo.CouponVo;
import displayFlex.event.dto.EventDto;
import test.JDBCTemplate;

public class EventDao {
	
	
	//이벤트 등록
	public int createEvent(Connection conn, EventDto dto) throws Exception {
	      
	      //SQL
		
			String sql = "INSERT INTO EVENT\r\n"
					+ "(EVENT_NO, EVENTTYPE_NO, MEMBER_NO, EVENT_TITLE, EVENT_CONTENTS, EVENT_PREPARATIONDATE, EVENT_REPORTINGDATE, EVENT_PROGRESS, EVENT_STARTDATE, EVENT_ENDDATE, EVENT_HIT)\r\n"
					+ "VALUES (SEQ_EVENT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
				PreparedStatement pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, dto.getEventtypeNo());
				pstmt.setString(2, dto.getMemberNo());
				pstmt.setString(3, dto.getEventTitle());
				pstmt.setString(4, dto.getEventContents());
				pstmt.setString(5, dto.getEventPreparationdate());
				pstmt.setString(6, dto.getEventReportingdate());
				pstmt.setString(7, dto.getEventProgress());
				pstmt.setString(8, dto.getEventStartdate());
				pstmt.setString(9, dto.getEventEnddate());
				pstmt.setString(10, dto.getEventHit());
				
				int result = pstmt.executeUpdate();
				
				//close
				JDBCTemplate.close(pstmt);
				return result;
	}

	public List<EventDto> selectEventList(Connection conn) throws Exception{
	      
	      //SQL
	      String sql = "SELECT \r\n"
	      		+ "       A.EVENT_NO\r\n"
	      		+ "       ,A. EVENTTYPE_NO\r\n"
	      		+ "       ,A. MEMBER_NO\r\n"
	      		+ "       ,A. EVENT_TITLE\r\n"
	      		+ "       ,A. EVENT_CONTENTS\r\n"
	      		+ "       ,A. EVENT_PREPARATIONDATE\r\n"
	      		+ "       ,A. EVENT_REPORTINGDATE\r\n"
	      		+ "       ,A. EVENT_PROGRESS\r\n"
	      		+ "       ,A. EVENT_STARTDATE\r\n"
	      		+ "       ,A. EVENT_ENDDATE\r\n"
	      		+ "       ,A. EVENT_HIT\r\n"
	      		+ "       FROM EVENT A\r\n"
	      		+ "        ORDER BY EVENT_NO";
	      
	      
	      
	      
	      PreparedStatement pstmt = conn.prepareStatement(sql);
//	      pstmt.setInt(1, pvo.getStartRow());
//	      pstmt.setInt(2, pvo.getLastRow());
	      
	      ResultSet rs = pstmt.executeQuery();
	   
	      //rs
	      List<EventDto> eventDtoList = new ArrayList<EventDto>();
	      while(rs.next()) {
	         
	         String eventNo = rs.getString("EVENT_NO");
	         String eventTypeNo= rs.getString("EVENTTYPE_NO");
	         String memberNo = rs.getString("MEMBER_NO");
	         String eventTitle = rs.getString("EVENT_TITLE");
	         String eventContents = rs.getString("EVENT_CONTENTS");
	         String eventPreparationDate = rs.getString("EVENT_PREPARATIONDATE");
	         String eventReportingDate = rs.getString("EVENT_REPORTINGDATE");
	         String eventProgress = rs.getString("EVENT_PROGRESS");
	         String eventStartDate = rs.getString("EVENT_STARTDATE");
	         String eventEndDate = rs.getString("EVENT_ENDDATE");
	         String eventHit = rs.getString("EVENT_HIT");
	         
	         
	         EventDto dto = new EventDto();
	         dto.setEventNo(eventNo);
	         dto.setEventtypeNo(eventTypeNo);
	         dto.setMemberNo(memberNo);
	         dto.setEventTitle(eventTitle);
	         dto.setEventContents(eventContents);
	         dto.setEventPreparationdate(eventPreparationDate);
	         dto.setEventReportingdate(eventReportingDate);
	         dto.setEventProgress(eventProgress);
	         dto.setEventStartdate(eventStartDate);
	         dto.setEventEnddate(eventEndDate);
	         dto.setEventHit(eventHit);
	         
	         
	         
	         eventDtoList.add(dto);
	         
	      }
	      
	      
	      //close
	      JDBCTemplate.close(pstmt);
	      JDBCTemplate.close(rs);
	      
	      return eventDtoList;
	   }
}//class