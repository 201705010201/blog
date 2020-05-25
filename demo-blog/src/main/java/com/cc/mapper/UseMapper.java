package com.cc.mapper;

import com.cc.pojo.User;
import org.apache.ibatis.annotations.Param;


public interface UseMapper {

    User queryByUsernameAndPassword(@Param("username") String username,
                                    @Param("password") String password);
}
