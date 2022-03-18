package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.model.ReplyDto;
import com.ssafy.vue.model.mapper.BoardReplyMapper;

@Service
public class BoardReplyServiceImpl implements BoardReplyService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public boolean writeReply(ReplyDto replyDto) throws Exception {
		if(replyDto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(BoardReplyMapper.class).writeReply(replyDto) == 1;
	}

	@Override
	public List<ReplyDto> getReply(int articleno) throws Exception {
		return sqlSession.getMapper(BoardReplyMapper.class).getReply(articleno);
	}

	@Override
	@Transactional
	public boolean modifyReply(ReplyDto replyDto) throws Exception {
		return sqlSession.getMapper(BoardReplyMapper.class).modifyReply(replyDto) == 1;
	}

	@Override
	@Transactional
	public boolean deleteReply(int replyno) throws Exception {
		return sqlSession.getMapper(BoardReplyMapper.class).deleteReply(replyno) == 1;
	}
}
