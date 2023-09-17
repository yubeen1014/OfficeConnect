package notice.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import notice.service.INoticeService;
import notice.service.NoticeServiceImpl;


//삭제파일
@WebServlet("/notice/delete.do")
public class DeletenoticeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		int ntcNo = Integer.parseInt(req.getParameter("ntcNo"));
		System.out.println(ntcNo);
		INoticeService noticeService = NoticeServiceImpl.GetInstance();

		int cnt = noticeService.deleteNotice(ntcNo);
		String msg = "";
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		HttpSession session = req.getSession();

		session.setAttribute("msg", msg);
		resp.sendRedirect(req.getContextPath() + "/notice/list.do");

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
