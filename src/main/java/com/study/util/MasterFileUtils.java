package com.study.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.study.domain.post.MasterAttachDTO;
import com.study.exception.MasterAttachFileException;


@Component
public class MasterFileUtils {

	private final String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
	
	private final String masteruploadPath = Paths.get("C:", "develop", "masterupload", today).toString();
	
	private final String getRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	
	public List<MasterAttachDTO> masteruploadFiles(MultipartFile[] files, Long masterBoardIdx){
		
		if(files[0].getSize() < 1) {
			return Collections.emptyList();
		}
		
		List<MasterAttachDTO> masterattachList = new ArrayList<>();
		
		File dir = new File(masteruploadPath);
		if(dir.exists() == false) {
			dir.mkdirs();
		}
		
		for (MultipartFile file : files) {
			try {
				final String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				
				final String saveName = getRandomString() + "." + extension;
				
				File target = new File(masteruploadPath, saveName);
				file.transferTo(target);
				
				MasterAttachDTO masterattach = new MasterAttachDTO();
				masterattach.setMasterBoardIdx(masterBoardIdx);
				masterattach.setMasterOriginalName(file.getOriginalFilename());
				masterattach.setMasterSaveName(saveName);
				masterattach.setMasterSize(file.getSize());
				
				masterattachList.add(masterattach);
				
				
				
			} catch (IOException e) {
				throw new MasterAttachFileException("[" + file.getOriginalFilename() + "] fail save file..");
			} catch (Exception e) {
				throw new MasterAttachFileException("[" + file.getOriginalFilename() + "] fail save file..");
			}
		}
		
		return masterattachList;
		
		
		
		
		
		
	}
	
	
	
	
}
