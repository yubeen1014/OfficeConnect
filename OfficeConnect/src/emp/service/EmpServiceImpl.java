package emp.service;

import emp.dao.IEmpDao;
import vo.EmpVO;
import emp.dao.EmpDaoImpl;

public class EmpServiceImpl implements IEmpService{
	
	// 싱글톤패턴
	public static IEmpService instance = null;
	private EmpServiceImpl() {}
	public static IEmpService getInstance() {
		if(instance == null) instance = new EmpServiceImpl();
		return instance;
	}
	
	IEmpDao loginDao = EmpDaoImpl.getInstance();
	
	@Override
	/**
	 * 로그인 체크를 위한 메서드
	 * @param empvo
	 * @return 로그인 성공여부
	 */
	public boolean loginCheck(EmpVO empVO) {
		return loginDao.loginCheck(empVO);
	}
	
	/**
	 * 사원정보 등록을 위한 메서드
	 * @param empVO 등록할 데이터가 담겨진 empVO객체
	 * @return 사원 등록이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환됨.
	 */
	@Override
	public int registEmp(EmpVO empVO) {
		return loginDao.joinEmployee(empVO);
	}
}
