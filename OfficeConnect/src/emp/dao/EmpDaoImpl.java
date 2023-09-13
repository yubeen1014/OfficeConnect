package emp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import util.MyBatisUtil;
import vo.ImageVO;
import vo.EmpVO;

public class EmpDaoImpl implements IEmpDao {

	// 싱글톤패턴
	public static IEmpDao instance = null;
	private EmpDaoImpl() {}
	public static IEmpDao getInstance() {
		if(instance == null) instance = new EmpDaoImpl();
		return instance;
	}
	
	/**
	 * 로그인 체크를 위한 메서드, 파라미터로 사번과 관리자로그인 실행여부를 받는다
	 * @param empVO
	 * @param isAdminLogin
	 * @return
	 */
	@Override
	public boolean loginCheck(EmpVO empVO, boolean isAdminLogin) {
		
		boolean isSuccess = false;
		SqlSession session = MyBatisUtil.getInstance();
		EmpVO result = null;
		
		try {
			
			if(isAdminLogin) {
				result = session.selectOne("employee.adminCheck", empVO);
			} else if(isAdminLogin == false) {
				result = session.selectOne("employee.loginCheck", empVO);
			}
			
			if(result != null) {
				isSuccess = true;
			}
			
		} catch (PersistenceException e) {
			e.printStackTrace();
			session.rollback();
		} finally {
			session.close();
		}
		
		return isSuccess;
	}
	
  	/**
	 * 회원가입 시켜주는 메서드
	 * @param empVO
	 * @return 성공여부
	 */
	@Override
	public int joinEmployee(EmpVO empVO) {
		
		SqlSession session = MyBatisUtil.getInstance();
		
		int cnt = 0;
		
		try {
			cnt = session.insert("employee.joinEmployee", empVO);
			if (cnt > 0) {
				session.commit();
			}
			
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;

	}

	
	/**
	 * 사원정보 수정을 위한 메서드
	 * @param empVO에 등록할 데이터가 담겨진 EmpVO의 객체
	 * @return 사원정보 수정에 성공하면 1이상의 값 반환, 실패하면 0 반환
	 */
	@Override
	public int updateEmployee(EmpVO empVO) {
		int cnt = 0;
		
		SqlSession session = MyBatisUtil.getInstance();
		
		try {
			
			cnt = session.update("employee.updateEmployee", empVO);
			
			if(cnt > 0) {
				session.commit();
			}
			
		} catch(PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return cnt;
	}
	
	
	/**
	 * 사원정보 삭제를 위한 메서드
	 * @param empNo 삭제할 사원의 사번
	 * @return 삭제에 성공하면 1 반환, 실패하면 0 반환
	 */
	@Override
	public int deleteEmployee(String empNo) {
		int cnt = 0;

		SqlSession session = MyBatisUtil.getInstance();

		try {
			cnt = session.delete("employee.deleteEmployee", empNo);

			if (cnt > 0) {
				session.commit();
			}

		} catch (PersistenceException ex) {
			ex.printStackTrace();
			session.rollback();

		} finally {
			session.close();
		}
		return cnt;
	}
	
	
	/**
	 * 사원정보가 존재하는지 확인하는 메서드
	 * @param empNo 체크할 사원의 사번
	 * @return 사번이 존재하면 true, 없으면 false 리턴
	 */
	@Override
	public boolean checkEmployee(String empNo) {
		
		boolean isExist = false;
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			int cnt = session.selectOne("employee.checkEmployee", empNo);
			
			if(cnt > 0) {
				isExist = true;
			}
			
		} catch (PersistenceException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		
		return isExist;
		
	}
	
	
	/**
	 * 해당 사번에 해당하는 사원정보를 가져오기 위한 메서드
	 * @param empNo 가져올 사번
	 * @return 해당 사원의 정보를 담은 empVO 객체
	 */
	@Override
	public EmpVO getEmployee(String empNo) {
		
		SqlSession session = MyBatisUtil.getInstance();

		EmpVO ev = null;
		try {
			ev = session.selectOne("employee.getEmployee", empNo);
			if(ev!=null) {
				 session.commit();
			 }
		} catch (PersistenceException ex) {
			session.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return ev;
	}
	
	
	/**
	 * 전체 사원정보(리스트)를 가져오는 메서드
	 */
	@Override
	public List<EmpVO> selectAll() {
		List<EmpVO> empList = new ArrayList<EmpVO>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			empList = session.selectList("employee.selectAll");
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return empList;
	}
	
	
	/**
	 * 사원 정보를 검색하기 위한 메서드
	 * @param 검색된 회원정보를 담은 ev 객체
	 * @return 검색한 사원의 정보를 담은 ev를 return
	 */
	@Override
	public List<EmpVO> searchEmployee(EmpVO ev){
		List<EmpVO> empList = new ArrayList<EmpVO>();
		
		SqlSession session = MyBatisUtil.getInstance(true);
		
		try {
			
			empList = session.selectList("employee.searchEmployee", ev);
			
		}catch(PersistenceException ex) {
			ex.printStackTrace();
		}finally {
			session.close();
		}
		return empList;
	}
}
