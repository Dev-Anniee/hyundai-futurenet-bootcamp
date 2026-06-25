package kr.or.kosa.mapper;

import java.util.List;
import kr.or.kosa.dto.UserAuth;
import kr.or.kosa.dto.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

	Users login(@Param("username") String username);

	int join(Users user) throws Exception;

	int insertAuth(UserAuth userAuth) throws Exception;

	List<Users> findAllUsers();
}
