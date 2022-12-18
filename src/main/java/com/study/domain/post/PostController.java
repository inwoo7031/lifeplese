package com.study.domain.post;

import lombok.RequiredArgsConstructor;

import org.apache.ibatis.io.ResolverUtil.Test;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;


import com.study.common.dto.MessageDto;
import com.study.common.dto.SearchDto;
import com.study.paging.PagingResponse;
import com.study.util.FileUtils;  // 11.14에 파일 다운로드를 위해 import 한 부분

import groovyjarjarantlr4.v4.parse.ANTLRParser.throwsSpec_return;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;  // 11.14에 파일 다운로드를 위해 import 한 부분
import java.nio.file.Paths; // 11.14에 파일 다운로드를 위해 import 한 부분
import java.time.format.DateTimeFormatter;  // 11.14에 파일 다운로드를 위해 import 한 부분
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;    // 11.14에 파일 다운로드를 위해 import 한 부분 
import javax.swing.filechooser.FileSystemView;



@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // 사용자에게 메시지를 전달하고, 페이지를 리다이렉트 한다.
    private String showMessageAndRedirect(final MessageDto params, Model model) {
        model.addAttribute("params", params);
        return "common/messageRedirect";
    }


    
    @GetMapping("/post/write.do")
    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, 
    							@RequestParam(value = "idx", required = false) final Long idx,
    							Model model) {
		if( id != null) {
			PostDTO post = postService.findPostById(id);
			model.addAttribute("post", post);
		}	
		return "post/write";

    }
    
    
    
    
    @GetMapping("/post/login.do")
    public String findinandpwd(@ModelAttribute("loginparams") final loginDTO loginparams , Model model) {
    	
    	//loginDTO loginpost = postService.Findloginright(loginparams);
    	//model.addAttribute("loginpost", loginpost);
    	return "post/login";
    }
    
    
//    @PostMapping("/post/llogin.do")
//    public String findidandpwd(@RequestParam(value = "userId", required = false) final Long userId ,
//    							@RequestParam(value = "pwd", required = false) final Long pwd ,
//    							final loginDTO loginparams , Model model) {
//    	
//    	
//    	System.out.println(loginparams);
//    	String loginpost = postService.Findloginright(loginparams);
//    	model.addAttribute("loginpost", loginpost);
//    	return "post/list";
//    }
    
    
    
    // 체킹 12.16
    @PostMapping("/post/llogin.do")
    public String bibigoidandpwd(@RequestParam(value = "userId", required = false) final String userId ,
    							@RequestParam(value = "pwd", required = false) final String pwd, loginDTO loginparams
    							) {
    	

    	
    	return null;
		
    }
    
    

    
    
//    @GetMapping("/post/masterwrite.do")   // 11.29 기존에 관리자 게시글 작성하는 부분에서 추가하는 것 + 사용자 정보도 가져오는 부분으로 수정
//    public String masteropenPostwrite(@RequestParam(value = "masterId", required = false)final Long masterId, Model model) {
//    	MasterPostDTO masterpost = postService.masterfindPostById(masterId);
//   	model.addAttribute("masterpost", masterpost);
//		return "post/masterwrite";
//    }
    
    
    @GetMapping("/post/masterwrite.do")   // 11.29 기존에 관리자 게시글 작성하는 부분에서 추가하는 것 + 사용자 정보도 가져오는 부분으로 수정
    public String masteropenPostwrite(@RequestParam(value = "masterId", required = false)final Long masterId, 
    								@RequestParam(value = "id", required = false) final Long id,
    								final MultipartFile[] files, Model model) {
    	
    	
    	
    	
    	
    	PostDTO post = postService.findPostById(id);
    	model.addAttribute("post", post);
    	
    	
    	FileDTO ppile = postService.findfileByid(id);
    	model.addAttribute("ppile", ppile);    	

    	
    	MasterPostDTO masterpost = postService.masterfindPostById(masterId);
    	model.addAttribute("masterpost", masterpost);
    	
    	
    	
    	MasterFileDTO masterfile = postService.masterfindfileByid(masterId);
    	model.addAttribute("masterfile", masterfile);
    	
    	
		return "post/masterwrite";
    }
    
    
    
    
    

    
    
    
    
    
    
    // 게시글 상세 페이지 GetMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 아니면!!! 사용....??
//    @GetMapping("/post/view.do")   
//    public String openPostView(@RequestParam(value = "id", required = false) final Long id ,
//    							@RequestParam(value = "masterId", required = false) final Long masterId,
//    							final MultipartFile[] files,Model model) {
        
    	//여기 부분이  특정 게시글의 상세정보의 제목, 이름, 내용, 등록일에 대한 정보
//    	PostDTO post = postService.findPostById(id);
//        model.addAttribute("post", post);
       
        
        // 여기 부분이 특정 게시글의 상세정보의 파일에 대한 이름, 저장이름 등과 같은 정보 담은 부분
//        FileDTO ppile = postService.findfileByid(id);
//        model.addAttribute("pile", ppile);
        
        
 

//        return "post/view";

//    }
        

    
    

    
    /**
     * 지금 필요한거는 
     * @RequestParam(value = "id", required = false) final Long id ----- 이게 기존에 사용자의 정보를 받아오는데 필요 (상세보기 부분)
     * final MultipartFile[] files,Model model ------ 이게 사용자의 파일 정보를 받아오는데 필요한 부분 (상세보기 부분)
     * 
     * 
     * final PostDTO params ----- 이게 관리자 dto  params
     * AttachDTO attachList-------
     * final MultipartFile[] files----------
     * @RequestParam(value = "idx", required = false) final Long idx ,Model model  ----- 
     * **/
    
    @PostMapping("/post/save.do")
    public String savepost(final PostDTO params ,AttachDTO attachList ,final MultipartFile[] files, 
    						@RequestParam(value = "idx", required = false) final Long idx ,Model model) {
    	postService.savePost(params, attachList, files);
    	MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);	
    	return showMessageAndRedirect(message, model);	
    	
    	
    	}
    
    
    @PostMapping("/post/mastersave.do")
    public String mastersavepost(final MasterPostDTO masterparams, MasterAttachDTO masterattachList, final MultipartFile[] files, 
    								@RequestParam(value = "masterIdx", required = false) final Long masterIdx ,Model model,final PostDTO params) {
    	
    	postService.mastersavePost(masterparams, masterattachList, files);
    	
    	postService.updateGubun(params);
    	
    	MessageDto message = new MessageDto("관리자 답변이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
    	return showMessageAndRedirect(message, model);
    }
    



    
    // 컨트롤ㄹ로근 여기서 잠깐 스탐 
//    @PostMapping("/post/mastersave.do")
//    public String mastersavepost(final MasterPostDTO params ,Model model) {
//    	postService.mastersavePost(params);
//    	MessageDto message = new MessageDto("관리자가 작성을 완료하였습니다", "/post/list.do", RequestMethod.GET, null);
//   	
//    	return showMessageAndRedirect(message, model);
//    }
 
    
    // 게시글 리스트 페이지  GetMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 아니면!!! 사용....??
    @GetMapping("/post/list.do")
    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
        PagingResponse<PostDTO> response = postService.findAllPost(params);
        model.addAttribute("response", response);
        return "post/list";
    }
    

    // 게시글 상세 페이지 GetMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 아니면!!! 사용....??
    @GetMapping("/post/view.do")   
    public String openPostView(@RequestParam(value = "id", required = false) final Long id ,
    							@RequestParam(value = "masterId", required = false) final Long masterId,
    							final MultipartFile[] files,Model model,final PostDTO params) {
        
    	
    	//여기 부분이  특정 게시글의 상세정보의 제목, 이름, 내용, 등록일에 대한 정보
    	PostDTO post = postService.findPostById(id);
        model.addAttribute("post", post);
       
        
        // 여기 부분이 특정 게시글의 상세정보의 파일에 대한 이름, 저장이름 등과 같은 정보 담은 부분
        FileDTO ppile = postService.findfileByid(id);
        model.addAttribute("ppile", ppile);
        
        
        

        // 원래대로 할라면 이거를 view로 바꿔주면 됨
        return "post/view";

    }
    
    

    
    
    
    
    
    @GetMapping("/post/masterview.do")
    public String masterPostView(@RequestParam(value="id", required = false)final Long id,
    								@RequestParam(value = "masterId", required = false) final Long masterId,
    								@RequestParam(value = "totalId", required = false) final Long totalId,
    								@RequestParam(value="answerGubun", required = false)final Long answerGubun,
    								final MultipartFile[] files,Model model, final PostDTO params) {
    
    	System.out.println(params.getAnswerGubun()+"이이이아니아니아니아니아니아니아니아니아니아니아니아니아니아");
    	
    	System.out.println(id + "이이이아니아니아니아니아니아니아니아니아니아니아니아니아니아");
    	
    	System.out.println(answerGubun + "qeqwewqeqewqeqwewqeqeqwfqwgvgqwg");
    

    	PostDTO post = postService.findPostById(id);
    	model.addAttribute("post", post);
    	
    	FileDTO ppile = postService.findfileByid(id);
    	model.addAttribute("ppile", ppile);
    	
    	
    	

    	MasterPostDTO masterpost = postService.masterfindPostById(id);
    	model.addAttribute("masterpost", masterpost);

    	MasterFileDTO masterppile = postService.masterfindfileByid(id);
    	model.addAttribute("masterppile", masterppile);
    		
    		

    	
    	
    	return "post/masterview";
    }
    
    
    //이게 12월 5일에 한 총 합친거 가져오는 거
//    @GetMapping("/post/totalview.do")
//    public String totalPostView(@RequestParam(value="id", required = false)final Long id,final MultipartFile[] files, Model model) {
//    	
//    	FinalDTO totalpost = postService.fianltotalfindbyid(id);
//    	model.addAttribute("totalpost", totalpost);
//    	
//    	
//    	return "post/totalview";
//    }
    
    
    
    
    
    
//    @GetMapping("/post/masterwrite.do")
//    public String masterPostWrite(@RequestParam(value = "id", required = false) final Long id ,final MultipartFile[] files,Model model) {
//    	
//    	//여기 부분이  특정 게시글의 상세정보의 제목, 이름, 내용, 등록일에 대한 정보
 //   	PostDTO post = postService.findPostById(id);
//        model.addAttribute("post", post);
//
//        return "post/masterwrite";    	
//    }
    
    

    
    
    

    
    
    // 게시글 리스트 페이지  GetMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 아니면!!! 사용....??
//    @GetMapping("/post/list.do")
//    public String openPostList(@ModelAttribute("params") final SearchDto params, Model model) {
//        PagingResponse<PostDTO> response = postService.findAllPost(params);
//        model.addAttribute("response", response);
//        return "post/list";
//    }
    
    
    
    
    
    
    
    
    @GetMapping("/post/download.do")
    public void downloadAttachFile(@RequestParam(value = "id", required = false) final Long id, 
    								@RequestParam(value = "idx", required = false) final Long idx,
    								final MultipartFile[] files, Model model , HttpServletResponse response) {
    	
    	if(id == null) throw new RuntimeException("잘못된 접근");
    	
    	FileDTO fileInfo = postService.findfileByid(id);
    	
    	if (fileInfo == null) {
    		throw new RuntimeException("파일 못찾음");
    	}
    	
    	String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
    	System.out.println(uploadDate);
    	
    	
    	String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();
    	
    	
    	System.out.println(uploadPath);
    	
    	
    	String filename = fileInfo.getOriginalName();
    	System.out.println("wqeqewqeqe" + filename);
    	//File file = new File(uploadPath, fileInfo.getSaveName());
    	//System.out.println(file);
    	
    	
    	//File fffile = new File(uploadPath, fileInfo.getSaveName());                         
    	//System.out.println("나와주세요"+fffile);
    	
    	
    	File qile = new File(uploadPath, fileInfo.getSaveName());
    	
    	
    	
    	
    	try {
    		// 파일유형 설정
			byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(qile);
			//파일 길이 설정
			response.setContentType("application/octet-stream");
			// 데이터 형
			response.setContentLength(data.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
			
			
			
			response.getOutputStream().write(data);
			response.getOutputStream().flush();
			response.getOutputStream().close();
    		
			
			System.out.println(data);
    		
    		
    		
		} catch (IOException e) {
			throw new RuntimeException("파일 다운 실패");
		} catch (Exception e) {
			throw new RuntimeException("시스템 문제");
		}
    	
    	
    	
    	
    }
    
    
    
    
    
    
    @GetMapping("/post/masterdownload.do")
    public void masterdownloadAttachFile(@RequestParam(value = "id", required = false) final Long id, 
    								@RequestParam(value = "idx", required = false) final Long idx,
    								final MultipartFile[] files, Model model , HttpServletResponse response) {
    	
    	if(id == null) throw new RuntimeException("잘못된 접근");
    	
    	//FileDTO fileInfo = postService.findfileByid(id);
    	MasterFileDTO masterfileInfo = postService.masterfindfileByid(id);
    	
    	//if (fileInfo == null) {
    	if (masterfileInfo == null) {
    		throw new RuntimeException("파일 못찾음");
    	}
    	
    	//String uploadDate = fileInfo.getInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
    	//System.out.println(uploadDate);
    	
    	String masteruploadDate = masterfileInfo.getMasterInsertTime().format(DateTimeFormatter.ofPattern("yyMMdd"));
    	
    	//String uploadPath = Paths.get("C:", "develop", "upload", uploadDate).toString();
    	String masteruploadPath = Paths.get("C:", "develop", "masterupload", masteruploadDate).toString();
    	
    	
    	
    	//System.out.println(uploadPath);
    	
    	
    	//String filename = fileInfo.getOriginalName();
    	//System.out.println("wqeqewqeqe" + filename);
    	//File file = new File(uploadPath, fileInfo.getSaveName());
    	//System.out.println(file);
    	
    	String masterfilename = masterfileInfo.getMasterOriginalName();
    	
    	
    	
    	//File fffile = new File(uploadPath, fileInfo.getSaveName());                         
    	//System.out.println("나와주세요"+fffile);
    	
    	
    	//File qile = new File(uploadPath, fileInfo.getSaveName());
    	File masterqile = new File(masteruploadPath, masterfileInfo.getMasterSaveName());
    	
    	
    	
//    	try {
//    		// 파일유형 설정
//			byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(qile);
//			//파일 길이 설정
//			response.setContentType("application/octet-stream");
//			// 데이터 형
//			response.setContentLength(data.length);
//			response.setHeader("Content-Transfer-Encoding", "binary");
//			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(filename, "UTF-8") + "\";");
//			
//			
//			
//			response.getOutputStream().write(data);
//			response.getOutputStream().flush();
//			response.getOutputStream().close();
//   		
//			
//			System.out.println(data);
//   		
//    		
//    		
//		} catch (IOException e) {
//			throw new RuntimeException("파일 다운 실패");
//		} catch (Exception e) {
//			throw new RuntimeException("시스템 문제");
//		}
  
    	
    	try {
    		byte[] data = org.apache.commons.io.FileUtils.readFileToByteArray(masterqile);
    		
    		response.setContentType("application/octet-stream");
    		
    		response.setContentLength(data.length);
    		response.setHeader("Content-Transfer-Encoding", "binary");
    		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(masterfilename, "UTF-8") + "\";");
    		
    		response.getOutputStream().write(data);
    		response.getOutputStream().flush();
    		response.getOutputStream().close();
    		
    		} catch (IOException e) {
				throw new RuntimeException("관리자 첨부파일 다운 실패");
			} catch (Exception e) {
				throw new RuntimeException("시스템 문제");
			}
    	
    	
    	
    }    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    


 	//	신규 게시글 생성     PostMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 할때 사용하는거 같은데....??
//    @PostMapping("/post/save.do")
//    public String savepost(final PostDTO params ,AttachDTO attachList ,final MultipartFile[] files, 
//    						@RequestParam(value = "idx", required = false) final Long idx ,Model model) {
//    	postService.savePost(params, attachList, files);
//    	MessageDto message = new MessageDto("게시글 생성이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);	
//    	return showMessageAndRedirect(message, model);	
//    	
//    	}
    

    // 기존 게시글 수정   PostMapping은 약간 클라이언트가 뭔가를 생성, 수정 삭제 같은거 할때 사용하는거 같은데....??
    @PostMapping("/post/update.do") 
    public String updatePost(final PostDTO params, Model model) {
        postService.updatePost(params);
        MessageDto message = new MessageDto("게시글 수정이 완료되었습니다.", "/post/list.do", RequestMethod.GET, null);
        return showMessageAndRedirect(message, model);
    }

 // 게시글 삭제
    @PostMapping("/post/delete.do")
    public String deletePost(@RequestParam final Long id,
                             @RequestParam final Map<String, Object> queryParams,
                             Model model) {

        postService.deletePost(id);
        
        System.out.println("----------------------------------------------------------------------------------------------"+model);
        
        MessageDto message = new MessageDto("게시글 삭제가 완료되었습니다.", "/post/list.do", RequestMethod.GET, queryParams);
        return showMessageAndRedirect(message, model);
    }
    
    
    
  
    
    
   

    
//    @GetMapping("/post/write.do")
//    public String openPostWrite(@RequestParam(value = "id", required = false) final Long id, 
//    							@RequestParam(value = "idx", required = false) final Long idx,
//    							Model model) {
//		if( id != null) {
//			PostDTO post = postService.findPostById(id);
//			model.addAttribute("post", post);
//		}	
//		return "post/write";
//    }    
    
    

        
}

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
 
    

    


