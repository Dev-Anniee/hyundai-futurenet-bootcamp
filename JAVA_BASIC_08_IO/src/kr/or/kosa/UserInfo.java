package kr.or.kosa;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfo implements Serializable{ //직렬화 역직렬화 가능 클래스
    private String userId;
    private String userPwd;
    private int age;
}

