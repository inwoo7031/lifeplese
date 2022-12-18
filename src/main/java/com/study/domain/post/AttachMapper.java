package com.study.domain.post;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface AttachMapper {
	
	/**파일 저장 **/
	public int insertAttach(List<AttachDTO> attachList);
	
	/** idx 에 해당하는 파일의 상세 정보 조회 (다운로드때 필요?)**/
	public AttachDTO selectAttachDetail(Long idx);
	
	/** 삭제**/
	public int deleteAttach(Long boardidx);
	
	/** 특정 게시글에 포함된 파일 조회 select**/
	public List<AttachDTO> selectAttachList(Long idx );
	
	/**  특정 게시글에 포함된 파일 개수를 조회 하는 select**/
	public int selectAttachTotalCount(Long idx);
	

}
