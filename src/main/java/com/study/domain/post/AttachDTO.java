package com.study.domain.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttachDTO {
	/** 파일 PK**/ 
	private long idx;     // 0
	
	private Long boardidx; // 1
	
	/** 원본 파일명 */
	private String originalName;  // 2

	/** 저장 파일명 */
	private String saveName;   // 3

	/** 파일 크기 */
	private long size;   // 4
	
	/** 파일 삭제 여부 **/
	private Boolean deleteYn;   // 5
	
	/** 파일 생성 시간 **/
	private LocalDateTime insertDate;  // 6
	
	/** 파일 삭제 시간 **/
	private LocalDateTime deleteDate;   // 7
	

}



	