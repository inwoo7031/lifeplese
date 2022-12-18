package com.study.domain.post;

import lombok.RequiredArgsConstructor;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import com.study.domain.post.PostDTO;
import com.study.common.dto.SearchDto;
import com.study.paging.Pagination;
import com.study.paging.PagingResponse;
import com.study.util.FileUtils;
import com.study.util.MasterFileUtils;


import groovyjarjarantlr4.v4.runtime.atn.SemanticContext.AND;


import javax.transaction.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    
    private final AttachMapper attachMapper;
    
    private final FileUtils fileUtils;
    

    private final MasterPostMapper masterpostMapper;

    private final MasterAttachMapper masterattachMapper;

    private final MasterFileUtils masterfileUtils;
    

    private final LoginMapper loginMapper;

   
    




    /**
     * 게시글 저장
     * @param params - 게시글 정보
     * @param files 
     * @param files 
     * @return Generated PK
     * 
     * -코멘트-
     * DB에 직접적으로 영향을 주는 애들은 Transactional써서 감싸주고
     * 어떠한 값을 전달하거나 전달 받을 값이 존재(여기서는 저장을 위한 파라미터)하기 때문에 final를 사용하여 생성자 주입
     * Postmapper 인터페이스를 가져다 쓰기 때문에 postMapper.save();을 가져옴
     * 이때 저장을 위해서는 값을 가지고 오고 전달해줄 값인 파라미터가 필요하기 때문에 (params)을 넣어서 사용
     * 즉,  postMapper.save(params); 같이 완성된것이고
     * 이후 여기서는 저장을 위해 전달하고 전달받은 파라미터의 값은 return params.getId();를 통해 리턴되어 값이 저장됨
     */
	
//    postMapper.save(params);
//	attachMapper.insertAttach(attachList);
//	return params.getId();
    
    
    
//    @Transactional
//    public int savePost(final PostDTO params) {
//        postMapper.save(params);
//        return params.getId();
//    }

//    @Transactional
//    public boolean savePost(final PostDTO params) {
//    	int queryResult=0;
//    	
 //   	if(params.getId() == null) {
//    			queryResult = postMapper.save(params);
//    	} else {
//    			queryResult = postMapper.update(params);
//    	}
//    	
//    	return (queryResult == 1) ? true : false;
//    }
//    
    //    public boolean savePost(final PostDTO params , final AttachDTO attachList , final MultipartFile[] files) {
    // 	int queryResult =1;
    //
    //if(savePost(params)== false) {
    //	return false;
    //}
    //
    //
    //List<AttachDTO> fileList = fileUtils.uploadFiles(files, params.getId());
    //if (CollectionUtils.isEmpty(fileList) == false) {
    //	queryResult = postMapper.save(params);
    //	queryResult = attachMapper.insertAttach(fileList);
    //	if (queryResult < 1) {
    //		queryResult = 0;
    //	}
    //}
    //
    //return (queryResult > 0);
    //  }
    
//    @Transactional         2022.10.28 일자 코딩
//    public boolean savePost(final PostDTO params, final AttachDTO attachList, final MultipartFile[] files) {
//    	int queryResult = 1;
//    	
//    	List<AttachDTO> fileList = fileUtils.uploadFiles(files, attachList.getIdx());
//    	if((params.getId() == null) && (attachList.getIdx() == null)){
//    		queryResult =postMapper.save(params);
//    	} else if((params.getId() == null) && (attachList.getIdx() != null)) {
//    		queryResult = attachMapper.insertAttach(fileList);
//    	}
//    	return (queryResult > 0);
//    }
    /** 이게 사용자의 게시글 + 파일 저장하는 부분 **/
    @Transactional            // 
    public long savePost(final PostDTO params, final AttachDTO attachList ,final MultipartFile[] files) {
    	
    	postMapper.save(params);
    	System.out.println(params);
    	if (files.length != 0) {
    	    		for (int i = 0; i < files.length; i++) {
    	    			MultipartFile[] subFiles = {files[i]};
    	    			List<AttachDTO> fileList = fileUtils.uploadFiles(subFiles, params.getId());
    		
    		if(fileList.isEmpty() == false){
    			attachMapper.insertAttach(fileList);
    			
    			
    	//		System.out.println(fileList.equals(fileList)== true);  			
    			return attachList.getIdx();
    		}
    		
    	}
    	}

    		return params.getId();
    				//return attachList.getIdx();
    		
    }
    
    
    /**여기 부분이 관리자 게시글 + 파일 저장 하는 부분 **/
    @Transactional
    public long mastersavePost(MasterPostDTO masterparams, final MasterAttachDTO masterattachList, final MultipartFile[] files) {
    	masterpostMapper.mastersave(masterparams);
    	
    	
    	if(files.length != 0) {
    			for(int i = 0; i < files.length; i++) {
    				MultipartFile[] mastersubFiles = {files[i]};
    				List<MasterAttachDTO> masterfileList = masterfileUtils.masteruploadFiles(mastersubFiles, masterparams.getMasterId());
    				
    		if(masterfileList.isEmpty() == false) {
    			masterattachMapper.masterInsertAttach(masterfileList);
    			
    			return masterattachList.getMasterIdx();
    		}
    			}
    	}
    	
    	return masterparams.getMasterId();
    }
    
    //이게 12.09에 구분 업데이트 부분 
    @Transactional
    public long updateGubun(final PostDTO params) {
    	postMapper.updategubun(params);
    	return params.getId();
    	//return parmas.getId();
   	
    	
    }
    

    

    

 
	/**
     * 
     * 게시글 상세정보 조회   이게 사용자의 게시글 정보 + 파일 정보를 가져오는 부분 
     * @param id - PK
     * @return 게시글 상세정보
     * 
     * -코멘트-
     * DB에 직접적인 영향을 주지 않기 때문에 따로 Transactional를 사용하지는 않음
     * 하지만 DB안에 있는 PK(id)를 기준으로 게시글의 상세 정보를 조회 하기 때문에 Final를 사용하여 생성자 주입
     * 여기도 역시 마찬가지로 Postmapper 인터페이스를 가져다쓰기 때문에 postMapper.findById();를 가져옴
     * 이후 게시글의 상세 정보 조회를 하기위한 기준 값이 되는 id를 넣어서 다시 postmapper 인터페이스로 리턴함
     */
    
    // 이게 될 지 안될지는 모르겠는데 일단 무조건 가져오고 view에서 이벤트로 처리할꺼임
    public PostDTO findPostById(final Long id) {  

    	return postMapper.findById(id);
    }
    
//    public FileDTO findfileByid(final Long id) {
//
//    	return postMapper.Findspecificfiles(id);
//    }
    
    
    // 11.14 부분 파일의 파라미터로 넘어가는 거 없으면 실행 하지 않도록 
    
    public FileDTO findfileByid(final Long id) {
    	    		
    	return postMapper.Findspecificfiles(id);
    }
    
    
    //12.16 이부분이 아디랑 비번 찾는 부분 ------시작------------
//	public loginDTO mememberlogin(final String loginparams) {
//		
//		return loginMapper.memberlogin(loginparams);
//	}
    
    //이거 안되면 위에 있는거 살리고 이거는 죽여
    //이거는 id 부분 
    public loginDTO mememememberloginid (final String userId) {
    	
    	return loginMapper.memberloginid(userId);
    }
    
    
    //이거는 비번부분
    public loginDTO mememememberloginpwd (final String pwd) {
    	
    	return loginMapper.memberloginpwd(pwd);
    }
    
    // ------끝------------
    
    
    // 관리자가 글을 작성한 후 게시글을 받아오는 부분 
    public MasterPostDTO masterfindPostById(final Long id) {
    	
    	
    	return masterpostMapper.masterfindById(id);
    }
    
    // 관리자가 파일을 저장한 후 파일 정보를 받아오는 부분 
    public MasterFileDTO masterfindfileByid(final Long id) {
    	

    	
    	return masterpostMapper.MasterFindspecificfiles(id);
    }
    
    
    public GubunDTO findPostByGubun(final long answerGubun) {
    	
    	return postMapper.findByGubun(answerGubun);
    }
    
                        

    
    // 이게 12월 5일날 한 거임 일단 지워놓을께
//    public FinalDTO fianltotalfindbyid(final Long id) {
//    	
//    	return finaltotalMapper.finalTotal(id);
//    }
    
    
    // 11.21 이게 서비스 영역의 관리자 게시글 작성 부분 
    //public MasterPostDTO masterFindPostById(final Long masterId) {
    //	
    //	return MasterpostMapper.masterfindById(masterId);
    //	
   // }
    
    //이게 서비스 영역의 관리자 파일 작성 부분
  //  public MasterFileDTO master
    
    

     
    
    /**
     * AttachMapper의 selectAttachTotalCount, selectAttachList 메서드를 이용해서
     * 파일 개수를 조회하고, 파일 개수가 1개 이상이면
     * boardIdx에 해당하는 게시글에 포함된 파일 리스트를 리턴합니다.
     * 
     * 
     * 
     * 이 밑에밑에 있는게 내가 만든거임 안되면 삭제하삼
     * 
     */
    


    
    
 
    

    /**
     * 게시글 수정
     * @param params - 게시글 정보
     * @return PK
     * 
     * -코멘트-
     * DB에 직접적인 영향을 주기 떄문에 @Transactional를 사용하여 감싸주고
     * 게시글 수정 할때 필요한 정보를 전달하기위한 파라미터 사용을 해야하기 떄문에 final를 통해 생성자 주입하고 params를 사용
     * 역시 마찬가지로 postmapper 인터페이스를 사용하고, 수정된 값인 파라미터(params)이 있기 때문에 postMapper.update(params); 사용
     * 이후 리턴 해줄떄는 params의 값을 getId를 통해 변경된 값을 넣어서 다시 리턴함 
     */
    @Transactional
    public long updatePost(final PostDTO params) {
        postMapper.update(params);
        return params.getId();
    }

    /**
     * 게시글 삭제
     * @param id - PK
     * @return PK
     * 
     * -코멘트-
     * DB 새로운 생성이나 수정이 아닌 삭제 기능은 @Transactional 사용 안하는듯...?
     * 게시글 삭제를 하기 위한 기준이 되는 PK인 id값을 전달 받아야 하기 때문에 final로 생성자 받음
     * 이후 역시 Postmappper를 사용하고 PK인 id값을 받아야 하기 떄문에 postMapper.deleteById(id); 작성
     * 이후 다른 파라미터를 받은 것 없이!! 단순히 PK인 id값를 리턴 해줌
     */
    public Long deletePost(final Long id) {
        postMapper.deleteById(id);
        return id;

    }

    /**
     * 게시글 리스트 조회
     * @return 
     * 
     * -코멘트-
     * 단순한 조회 기능이기 떄문에 DB에 영향을 주지 않아서 @Transactional 사용 않함
     * 또한 파라미터(params)나 PK(id)를 사용하지 않고 단순히 view에 보여 주기 위함이기 떄문에 다른 것 사용 안함
     * 그래도 Postmapper 인터페이스를 사용하기 
     * 리턴은 postmapper로 지정하고 findAll();에는 값을 담아서 보낼 것은 없기 떄문에 괄호 안에는 빈칸으로 리턴함
     */
    public PagingResponse<PostDTO> findAllPost(final SearchDto params) {
        int count = postMapper.count(params);
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        List<PostDTO> list = postMapper.findAll(params);
        return new PagingResponse<>(list, pagination);
           
    }



    
    
    

    
    
    
    
    
//    @Transactional
//    public long updatePost(final PostDTO params) {
//        postMapper.update(params);
//        return params.getId();
//    }





    
    /**여기 기준으로 위는 사용자 에 대한 서비스 부분 아래는 관리자에 대한 부분  **/
    
    /** 이게 사용자의 게시글 + 파일 저장하는 부분 
    @Transactional            // 
    public long savePost(final PostDTO params, final AttachDTO attachList ,final MultipartFile[] files) {
    	
    	postMapper.save(params);

    	if (files.length != 0) {
    	    		for (int i = 0; i < files.length; i++) {
    	    			MultipartFile[] subFiles = {files[i]};
    	    			List<AttachDTO> fileList = fileUtils.uploadFiles(subFiles, params.getId());
    		
    		if(fileList.isEmpty() == false){
    			attachMapper.insertAttach(fileList);
    			
    			
    	//		System.out.println(fileList.equals(fileList)== true);  			
    			return attachList.getIdx();
    		}
    		
    	}
    	}
    		return params.getId();
    				//return attachList.getIdx();
    }
    **/
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    
    
    
    
    
    
    
    
    
    /**
    @Transactional            // 
    public long savePost(final PostDTO params, final AttachDTO attachList ,final MultipartFile[] files) {
    	
    	postMapper.save(params);

    	if (files.length != 0) {
    	    		for (int i = 0; i < files.length; i++) {
    	    			MultipartFile[] subFiles = {files[i]};
    	    			List<AttachDTO> fileList = fileUtils.uploadFiles(subFiles, params.getId());
    		
    		if(fileList.isEmpty() == false){
    			attachMapper.insertAttach(fileList);
    			
    			
    	//		System.out.println(fileList.equals(fileList)== true);  			
    			return attachList.getIdx();
    		}
    		
    	}
    	}
    		return params.getId();
    				//return attachList.getIdx();
    }
    **/
    
   
    
    
    
    
    
    

}