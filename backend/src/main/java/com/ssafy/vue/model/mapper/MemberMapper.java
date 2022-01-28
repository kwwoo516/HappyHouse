package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.MemberDto;

@Mapper
public interface MemberMapper {
	
	public int idCheck(String checkId) throws SQLException;
	public MemberDto login(MemberDto memberDto) throws SQLException;
	public int registerMember(MemberDto memberDto) throws SQLException;
	public List<MemberDto> listMember() throws SQLException;
	public MemberDto getMember(String userId) throws SQLException;
	public int modifyMember(MemberDto memberDto) throws SQLException;
	public int deleteMember(String userId) throws SQLException;
	//비밀번호 찾기 추가?
}
