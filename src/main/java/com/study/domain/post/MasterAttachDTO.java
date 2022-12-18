package com.study.domain.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterAttachDTO {



	
	private long masterIdx;     // 0
	
	private Long masterBoardIdx; // 1
	
	/** 원본 파일명 */
	private String masterOriginalName;  // 2

	/** 저장 파일명 */
	private String masterSaveName;   // 3

	/** 파일 크기 */
	private long masterSize;   // 4
	
	/** 파일 삭제 여부 **/
	private Boolean masterDeleteYn;   // 5
	
	/** 파일 생성 시간 **/
	private LocalDateTime masterInsertDate;  // 6
	
	/** 파일 삭제 시간 **/
	private LocalDateTime masterDeleteDate;   // 7
	
	
	

	
	//private long masterTotalAttachColums;
	

	
	
}
