package com.study.domain.post;

import org.apache.ibatis.annotations.Mapper;


import com.study.common.dto.SearchDto;




import java.util.List;

@Mapper
public interface PostMapper {

    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * 
     * -코멘트-
     * 특정 값을 받아서 전달이나 받기 떄문에 params 사용
     * @return 
     */
    public long save(PostDTO params);
    

     
//    public int filesave(PostDTO params, MultipartFile[] files); 

    /**
     * 게시글 상세정보 조회
     * @param id - PK
     * @return 게시글 상세정보
     * 
     * -코멘트-
     * 상세정보 확인을 위한 기준인 PK인 long 타입의 id를 받아서 사용
     */
    public PostDTO findById(Long id);
    
    public GubunDTO findByGubun(Long answerGubun); // 여기부분 안되면 지원 
    
    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return 
     */
    public int update(PostDTO params);
    
    
    
    /**
     * 12.09 구분 업테이트 부분 
     * 
     * **/
    public int updategubun(PostDTO params);
    

    /**
     * 게시글 삭제
     * @param id - PK
     */
    public void deleteById(Long id);

    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     * 
     * -코멘트-
     * 특정 조회가 아닌 전체 리스트는 따로 받는 값이 없기 떄문에 빈칸으로 유지
     */
    public List<PostDTO> findAll(SearchDto params);

    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
    int count(SearchDto params);
    
    /** postmapper.xml 에서 파일 특정 게시글에 대한 파일 정보 담은 내용**/
    
    public FileDTO Findspecificfiles(Long id);



	  
    
    
    
    
    /** 이게 특정 파일의 정보 받아오는거 임**/
  //  public List<FileDTO> Findspecificfiles(FileDTO pile);


   
    /** 만약에 여기에서 filedto를 가져오면?
     *   
     *   void savefile(fileDto fileid);
     * 
     */
    
    /** 여기가 파일 첨부하는 곳 (내가 만든 곳)   이게 savefile 이라는 함수를 저장 할껀데 그거는 1번 방법인 다른 요청에 의한것이 아니고 db에 접근해서 써야되서 따로 작성 하지는 않고  db의 pk 값 받아옴 이거에 대한 쿼리는 xml에서 해줄꺼임**/
   /** void savefile(PostRequest params ,MultipartFile files);  **/



    
}