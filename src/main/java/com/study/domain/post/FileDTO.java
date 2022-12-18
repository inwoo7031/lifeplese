package com.study.domain.post;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDTO {
	private long id;
	private Long boardidx;
	private String originalName;
	private LocalDateTime insertTime; // 이거 아니면 inserttime 로 해
	private String saveName;

	}

