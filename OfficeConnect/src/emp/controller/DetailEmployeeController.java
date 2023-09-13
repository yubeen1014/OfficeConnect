package emp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emp.service.EmpServiceImpl;
import emp.service.IEmpService;
import img.service.AtchFileServiceImpl;
import img.service.IAtchFileService;
import img.vo.AtchFileVO;
import vo.EmpVO;

@WebServlet("/join/detail.do")// 1. 컨트롤러 시작 detail.do 호출
public class DetailEmployeeController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String empNo = req.getParameter("empNo"); // 2. 요청객체 파라미터로 가져옴("사번")
		
		IEmpService empService = EmpServiceImpl.getInstance(); // 3. 싱글톤 객체 생성
		
		EmpVO ev = empService.getEmployee(empNo); // 4. db에서 값 가져오는거
		
		req.setAttribute("ev", ev); // 5. db에서 가져온 값 세팅
		
		if(empNo != null && !empNo.isEmpty()) {
			IAtchFileService fileService =
					AtchFileServiceImpl.getInstance();
			
			AtchFileVO atchFileVO = new AtchFileVO();
			atchFileVO.setEmpNo(ev.getEmpNo());
			
			List<AtchFileVO> fileList =
					fileService.getAtchFileList(atchFileVO);
			
			req.setAttribute("fileList", fileList);
		}
		
		req.getRequestDispatcher("/views/employee/detail.jsp").forward(req, resp); // 6. ev에 있는 값 포워드 전달해주기 jsp는 일하는놈 do는 보여지는놈
				
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}