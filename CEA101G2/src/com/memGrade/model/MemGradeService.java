package com.memGrade.model;

import java.util.List;

public class MemGradeService {

	private Mem_GradeDAO_interface dao;

	public MemGradeService() {
//		dao = new Mem_GradeJDBCDAO();
		dao = new Mem_GradeDAO();
	}

	public Mem_GradeVO addGrade(Integer grade_no, Integer consume_level, Double discount
		) {
		Mem_GradeVO mem_gradeVO = new Mem_GradeVO();

		mem_gradeVO.setGrade_no(grade_no);
		mem_gradeVO.setConsume_level(consume_level);
		mem_gradeVO.setDiscount(discount);
		
		dao.insert(mem_gradeVO);

		return mem_gradeVO;
	}

	public Mem_GradeVO updateGrade(Integer grade_no, Integer consume_level, Double discount) {
		Mem_GradeVO mem_gradeVO = new Mem_GradeVO();

		mem_gradeVO.setGrade_no(grade_no);
		mem_gradeVO.setConsume_level(consume_level);
		mem_gradeVO.setDiscount(discount);
		
		dao.update(mem_gradeVO);

		return mem_gradeVO;
	}

	public void deleteGrade(Integer grade_no) {
		dao.delete(grade_no);
	}

	public Mem_GradeVO getOneGrade(Integer grade_no) {
		return dao.findByPrimaryKey(grade_no);
	}

	public List<Mem_GradeVO> getAll() {
		return dao.getAll();
	}
}
