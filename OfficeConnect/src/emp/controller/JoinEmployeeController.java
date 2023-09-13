package emp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emp.service.EmpServiceImpl;
import emp.service.IEmpService;
import img.service.AtchFileServiceImpl;
import img.service.IAtchFileService;
import img.vo.AtchFileVO;
import util.MailUtil;
import vo.EmpVO;

@MultipartConfig
@WebServlet("/join/join.do")
public class JoinEmployeeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("/views/employee/insertForm.jsp").forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IEmpService empService = EmpServiceImpl.getInstance();
		req.setCharacterEncoding("UTF-8");
		String empPw = req.getParameter("empPw");
		String empAddr = req.getParameter("empAddr");
		String empTel = req.getParameter("empTel");
		String empEmail = req.getParameter("empEmail");
		String empName = req.getParameter("empName");
		String empPosit = req.getParameter("empPosit");
		int adminCode = Integer.parseInt(req.getParameter("adminCode"));
		int empState =Integer.parseInt(req.getParameter("empState"));
		int deptCode = Integer.parseInt(req.getParameter("deptCode"));
		String imgName = req.getParameter("imgName");
		
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO atchFileVO = null;

		try {
			atchFileVO = fileService.saveAtchFileList(req.getParts());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		


		EmpVO empVO = new EmpVO();
		empVO.setEmpPw(empPw);
		empVO.setEmpAddr(empAddr);
		empVO.setEmpTel(empTel);
		empVO.setEmpEmail(empEmail);
		empVO.setEmpName(empName);
		empVO.setEmpPosit(empPosit);
		empVO.setAdminCode(adminCode);
		empVO.setEmpState(empState);
		empVO.setDeptCode(deptCode);
		
		AtchFileVO fileVO = new AtchFileVO();
		fileVO.setImgName(imgName);
		
		if(atchFileVO != null) {
			empVO.setEmpNo(atchFileVO.getEmpNo());
		    fileVO.setImgName(atchFileVO.getImgName()); // 파일명 저장
		}
		
		int cnt = empService.registEmployee(empVO);
		if(atchFileVO != null) {
			atchFileVO.setEmpNo(empVO.getEmpNo());
			int fileResult = empService.insertFile(atchFileVO);
		}
		String msg ="";
		
		if(cnt > 0) {
			msg = "성공";

			MailUtil sendMail = new MailUtil();
			sendMail.sendMail("임시 비밀번호가 발급되었습니다", "사번 : " + empVO.getEmpNo() + "<br>임시 비밀번호 : "+ empPw, empEmail);
		} else {
			msg = "실패";
		}
		HttpSession session = req.getSession();
		session.setAttribute("msg", msg);
		resp.setCharacterEncoding("UTF-8");
		resp.sendRedirect(req.getContextPath() + "/join/list.do");

	}
}