package com.kakaolove.doit.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kakaolove.doit.vo.projectVO;

@Repository("projectDAO")
public class projectDAOImpl implements projectDAO {
	
	@Inject
	private SqlSession sqlSession;

    
	private static String namespace = "com.kakaolove.doit.mappers.projectMapper";
	
	@Override
	public void create(projectVO vo) throws Exception {
		sqlSession.insert(namespace+".insertProject", vo);
	}

	@Override
	public List<projectVO> listAll() throws Exception {
		return sqlSession.selectList(namespace+".listProject");
	}

	@Override
	public projectVO read(Integer no) throws Exception {
		sqlSession.selectOne(namespace+".readProject", no);
		return null;
	}

	@Override
	public void delete(Integer no) throws Exception {
		sqlSession.delete(namespace+".deleteProject", no);
	}

	@Override
	public void update(projectVO vo) throws Exception {
		sqlSession.update(namespace+".updateProject", vo);
	}

}
