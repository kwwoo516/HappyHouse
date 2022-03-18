package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.model.ReplyDto;

@Mapper
public interface BoardReplyMapper {
	
	public int writeReply(ReplyDto replyDto) throws SQLException;
	public List<ReplyDto> getReply(int articleno) throws SQLException;
	public int modifyReply(ReplyDto replyDto) throws SQLException;
	public int deleteReply(int replyno) throws SQLException;
	
}