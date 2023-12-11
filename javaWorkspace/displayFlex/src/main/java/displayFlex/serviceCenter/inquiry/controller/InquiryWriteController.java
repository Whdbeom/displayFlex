package displayFlex.serviceCenter.inquiry.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import displayFlex.member.MemberVo;
import displayFlex.serviceCenter.inquiry.service.InquiryService;
import displayFlex.serviceCenter.inquiry.vo.InquiryVo;

@WebServlet("/serviceCenter/inquiryWrite")
public class InquiryWriteController extends HttpServlet {

	//일대일 문의 작성 화면
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {

			//로그인 안되어있으면 에러페이지로 보내기
			MemberVo loginMember = (MemberVo) req.getSession().getAttribute("loginMember");
			if(loginMember == null) {
				req.setAttribute("errorMsg"	, "잘못된 접근입니다. (로그인 하고 오세요)");
				req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
			}
			
			InquiryService is = new InquiryService();
			req.getRequestDispatcher("/WEB-INF/views/serviceCenter/inquiry/inquiryWrite.jsp").forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
			req.setAttribute("errorMsg", "게시글 작성하기 (화면) 에러 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
		
	}
	
	//일대일 문의 작성 로직
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			
			//인코딩
//			req.setCharacterEncoding("UTF-8");	//필터에서 인코딩 처리 해줌
			
			HttpSession session = req.getSession();
			
			// data
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			MemberVo loginMember = (MemberVo)session.getAttribute("loginMember");
			
			if(loginMember == null) {
				throw new Exception("로그인 안함");
			}
			
			InquiryVo vo = new InquiryVo();
			vo.setTitle(title);
			vo.setContent(content);
//			vo.setMemberNo(loginMember.getMemberNo());
			
			// service
			InquiryService is = new InquiryService();
			int result = is.write(vo);
			
			// result == view
			if(result != 1) {
				throw new Exception("result 가 1이 아님 ,,,,");
			}
			
			req.getSession().setAttribute("alertMsg", "1:1 문의 작성 성공 !");
			resp.sendRedirect("/cinema/serviceCenter/inquiryWrite");
			
		}catch(Exception e) {
			System.out.println("[ERROR-B002] 1:1 문의 작성 실패 ...");
			e.printStackTrace();
			req.setAttribute("errorMsg", "1:1 문의 작성 실패 ...");
			req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	
	}
}
