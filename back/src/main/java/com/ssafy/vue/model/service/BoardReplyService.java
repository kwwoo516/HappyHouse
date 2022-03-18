package com.ssafy.vue.model.service;

import java.util.List;

import com.ssafy.vue.model.ReplyDto;

public interface BoardReplyService {
	
	public boolean writeReply(ReplyDto replyDto) throws Exception;
	public List<ReplyDto> getReply(int articleno) throws Exception;
	public boolean modifyReply(ReplyDto replyDto) throws Exception;
	public boolean deleteReply(int replyno) throws Exception;
}
