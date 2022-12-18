package com.study.domain.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterFileDTO {

	private long id;
	
	private long boardIdx;
	private long totalId;
	
	
	
	
	
	
	private long masterId;
	private Long masterBoardIdx;
	private String masterOriginalName;
	private LocalDateTime masterInsertTime; // 이거 아니면 inserttime 로 해
	private String masterSaveName;
	
}
