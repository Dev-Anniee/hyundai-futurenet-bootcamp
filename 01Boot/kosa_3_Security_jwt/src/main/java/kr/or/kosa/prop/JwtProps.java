package kr.or.kosa.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties("kr.or.kosa")
public class JwtProps {
   private String secretkey;
   //getter 
}

//key 제어