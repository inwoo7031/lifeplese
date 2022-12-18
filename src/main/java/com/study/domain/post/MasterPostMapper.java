package com.study.domain.post;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MasterPostMapper {

	
	
	public long mastersave(MasterPostDTO masterparams);
	
	public MasterPostDTO masterfindById(Long id);
	

	public MasterFileDTO MasterFindspecificfiles(Long id);
	
	public PostDTO mastergubun();
	
	
	
	
	
	
	
	
	
	
	
	// 12.09 안되면 이거 지워
	public TestMasterPostDTO paratwomasterfindById(TestMasterPostDTO testparami);
	
	
	
}
