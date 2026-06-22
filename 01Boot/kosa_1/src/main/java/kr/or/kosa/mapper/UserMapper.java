package kr.or.kosa.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.or.kosa.model.User;

@Mapper
public interface UserMapper {
  
	//Mapper.xml 
	//1. namespace 동일  namespace="kr.or.kosa.mapper.UserMapper"
	//2. 함수이름과 id 동일 
	
	List<User> selectAll();
	User selectById(Long id);
	void insert(User user);
	void update(User user);
	void delete(Long id);
}
