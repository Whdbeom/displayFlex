package displayFlex.screeninginfo.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import displayFlex.movie.vo.MovieVo;
import displayFlex.screeninginfo.dao.ScreenInfoDao;
import displayFlex.screeninginfo.vo.ScreeingInfoVo;
import displayFlex.screeninginfo.vo.ScreenInfoDto;
import displayFlex.screeninginfo.vo.ScreeningTimeVo;
import test.JDBCTemplate;

public class ScreenInfoService {

	private final ScreenInfoDao infoDao;
	
	public ScreenInfoService() {
		infoDao = new ScreenInfoDao();
	}
	/**
	 * 모든 영화 목록 가져오기
	 * @return
	 * @throws SQLException 
	 */
	public List<MovieVo> getAllMovie() throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<MovieVo> movieList = infoDao.getAllMovie(con);
		
		JDBCTemplate.close(con);
		return movieList;
	}
	
	/**
	 * 모든 상영관 가져오기
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getAllTheater() throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		List<String> theaterList = infoDao.getAllTheater(con);
		JDBCTemplate.close(con);
		return theaterList;
	}
	
	/**
	 * 상영 정보 등록
	 * @param infoList
	 * @param timeList
	 * @return
	 * @throws SQLException 
	 */
	public int addScreeningInfo(List<ScreeingInfoVo> infoList, List<ScreeningTimeVo> timeList) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		int result = 0;
		for(int index = 0; index < infoList.size(); index++) {
			String findNo = infoDao.getInfoNoByCondition(infoList.get(index), con);

			//신규로 추가하는 경우
			if(findNo == null) {
				result = infoDao.addScreeningInfo(infoList.get(index), con);
				System.out.println("result1 = " + result);
				if(result == 1) {
					JDBCTemplate.commit(con);
					findNo = infoDao.getInfoNoByCondition(infoList.get(index), con);

				} else {
					JDBCTemplate.rollback(con);
					break;
				}
			}
			
			timeList.get(index).setScreeningInfoNo(findNo);
			if(findNo != null) {
				result = infoDao.addScreeningTime(timeList.get(index), con);
				System.out.println("result2 = " + result);
				if(result == 1) {
					JDBCTemplate.commit(con);
				} else {
					JDBCTemplate.rollback(con);
					int deleteResult = infoDao.deleteScreeningInfo(findNo, con);
					if(deleteResult == 1) JDBCTemplate.commit(con);
					else JDBCTemplate.rollback(con);
				}			
			}
		}
		
		JDBCTemplate.close(con);
		System.out.println("result3 = " + result);
		return result;
	}
	
	/**
	 * 기존의 상영 정보가 있는 지 체크
	 * @param screenInfoDto
	 * @return
	 * @throws SQLException 
	 */
	public int isExistScreeningInfo(ScreenInfoDto screenInfoDto) throws SQLException {
		Connection con = JDBCTemplate.getConnection();
		
		int count = infoDao.isExistScreeningInfo(screenInfoDto, con);
		JDBCTemplate.close(con);
		return count;
	}

}
