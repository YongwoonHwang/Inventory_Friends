package com.spring.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.spring.vo.UserVo;
@Mapper
public interface UserMapper {
    // 로그인
    UserVo getUserAccount(String userId);

    // 회원가입
    void saveUser(UserVo userVo);
}
