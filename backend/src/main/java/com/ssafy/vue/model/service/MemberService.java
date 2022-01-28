package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.MemberDto;

public interface MemberService {
	
	public int idCheck(String checkId) throws Exception;
	public MemberDto login(MemberDto memberDto) throws Exception;
	public boolean registerMember(MemberDto memberDto) throws Exception;
	public List<MemberDto> listMember() throws Exception;
	public MemberDto getMember(String userId) throws Exception;
	public boolean modifyMember(MemberDto memberDto) throws Exception;
	public boolean deleteMember(String userId) throws Exception;
}
