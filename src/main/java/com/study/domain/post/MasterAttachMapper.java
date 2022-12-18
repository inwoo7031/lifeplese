package com.study.domain.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterAttachMapper {
	
	public int masterInsertAttach(List<MasterAttachDTO> masterattachList);
	
	public MasterAttachDTO masterSelectAttachDetail(Long id);
	
	public int masterDeleteAttach(Long masterBoardIdx);
	
	public List<MasterAttachDTO> masterSelectAttachList(Long masterIdx);
	
	public int masterSelectAttachTotalCount(Long masterBoardIdx);
}
