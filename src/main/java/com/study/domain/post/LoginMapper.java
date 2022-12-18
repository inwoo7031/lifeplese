package com.study.domain.post;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	public loginDTO memberlogin(String loginparams);

	public loginDTO memberloginid(String userId);

	public loginDTO memberloginpwd(String pwd);
}
