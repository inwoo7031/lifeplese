package com.study.domain.post;

import java.time.LocalDateTime;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private long id;                       // PK
    private String title;                  // 제목
    private String content;                // 내용
    private String writer;                 // 작성자
    private int viewCnt;                   // 조회 수
    private Boolean noticeYn;              // 공지글 여부
    private Boolean deleteYn;              // 삭제 여부
    private LocalDateTime createdDate;     // 생성일시
    private LocalDateTime modifiedDate;    // 최종 수정일시
    
    private Boolean answerGubun; // 답변 유뮤 판단


}
