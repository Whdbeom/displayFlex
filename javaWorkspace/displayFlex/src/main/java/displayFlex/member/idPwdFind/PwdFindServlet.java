package displayFlex.member.idPwdFind;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import displayFlex.member.MemberService;
import displayFlex.member.MemberVo;

@WebServlet("/member/pwdFind")
public class PwdFindServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		    MemberService ms = new MemberService();

		    String memberId = req.getParameter("memberId");
		    String memberName = req.getParameter("memberName");
		    String memberPhoneNum = req.getParameter("memberPhoneNum");

		    MemberVo vo = new MemberVo();
		    vo.setMemberId(memberId);
		    vo.setMemberName(memberName);
		    vo.setMemberPhoneNum(memberPhoneNum);

		    MemberVo result = ms.SelectPwd(vo);

		    if (result != null && result.equals(vo)) {
		    	req.setAttribute("vo", result);
		    	req.getRequestDispatcher("/WEB-INF/views/member/pwdFindConfirm.jsp").forward(req, resp);
		    } else {
		    	req.setAttribute("errorMsg", "일치하는 회원 정보가 없습니다.");
		    	req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		    }
		    
		} catch(Exception e) {
		    System.out.println("[ERROR-I002] 비밀번호 찾기 중 에러 발생...!");
		    e.printStackTrace();
		    req.setAttribute("errorMsg", "비밀번호 찾기 중 에러 발생...!");
		    req.getRequestDispatcher("/WEB-INF/views/common/error.jsp").forward(req, resp);
		}
	}
	

}

