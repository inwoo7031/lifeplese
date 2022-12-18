package com.study.domain.post;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MasterPostDTO {

    private long masterId;                       
    private String masterTitle;                  // 제목
    private String masterContent;                // 내용
    private String masterWriter;                 // 작성자
    private LocalDateTime masterCreatedDate;     // 생성일시
    private long id;
    private long totalId;
}
