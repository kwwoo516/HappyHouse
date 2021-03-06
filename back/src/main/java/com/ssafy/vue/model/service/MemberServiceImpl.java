package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.MemberDto;
import com.ssafy.vue.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int idCheck(String checkId) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).idCheck(checkId); // 0 or 1
	}
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(memberDto);
	}

	@Override
	public boolean registerMember(MemberDto memberDto) throws Exception {
		// validation check
		return sqlSession.getMapper(MemberMapper.class).registerMember(memberDto) == 1;
	}

	@Override
	public List<MemberDto> listMember() throws Exception {
		return sqlSession.getMapper(MemberMapper.class).listMember();
	}

	@Override
	public MemberDto getMember(String userId) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getMember(userId);
	}

	@Override
	public boolean modifyMember(MemberDto memberDto) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).modifyMember(memberDto) == 1;
	}

	@Override
	public boolean deleteMember(String userId) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).deleteMember(userId) == 1;		
	}

}
