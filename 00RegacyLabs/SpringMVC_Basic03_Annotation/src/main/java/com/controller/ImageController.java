package com.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.model.Photo;

@Controller
@RequestMapping("/image/upload.do")
public class ImageController {

  //화면
  @GetMapping
  public String form() {
    return "image/image";
  }

  //업로드 처리하기
  @PostMapping
  public String submit(Photo photo , HttpServletRequest request) {

		/*
		1. Photo DTO 타입으로 데이터 받기
		1.1 자동화 : name 속성값이 Photo 타입클래스의 member field 명과 동일
		2. public String submit(Photo photo) 내부적으로 ...
		   >> Photo photo = new Photo();
		   >> photo.setName("홍길동");
		   >> photo.setAge(20);
		   >> photo.setImage() >> 자동 주입 안되요 >> 수동으로 >> 가공 CommonsMultipartFile 추출(이름)
		   >> photo.setFile(CommonsMultipartFile file) 파일 자동으로 들어와요

	     */
    System.out.println(photo.toString());

    //1. photo.setImage() 파일 이름 설정
    //2. 파일 서버 write (IO)
    //3. DB insert

    CommonsMultipartFile imageFile = photo.getFile();
    System.out.println("imagefile getName()" + imageFile.getName() );
    System.out.println("imagefile getContentType()" + imageFile.getContentType() );
    System.out.println("imagefile getOriginalFilename()" + imageFile.getOriginalFilename() );
    System.out.println("imagefile getBytes().length" + imageFile.getBytes().length );

    //필요한 정보가 있다면 추출해서 DB > Table > insert 해야 되요

    //POINT 파일명 추출 image=null
    photo.setImage(imageFile.getOriginalFilename()); //수동 ...

    //upload (서버에 파일쓰기)
    //자동화 : cos.jar (무료) ,  덱스트 업로드(제품 구매)

    //수동으로 코딩( I/O)
    String fileName = imageFile.getOriginalFilename();
    //HttpServletRequest request
    String path = request.getServletContext().getRealPath("/upload"); //실 배포 경로
    File uploadDir = new File(path);
    if (!uploadDir.exists()) {
      uploadDir.mkdirs();
    }

    File uploadFile = new File(uploadDir, fileName);

    System.out.println(uploadFile.getAbsolutePath());

    FileOutputStream fs = null;

    try {
      fs = new FileOutputStream(uploadFile); //파일이 없으면 빈 파일 ( a.jpg) 자동
      fs.write(imageFile.getBytes()); //image생성 .... 업로드한 파일 서버에 write

    } catch (Exception e) {
      e.printStackTrace();
    }finally {
      try {
        if (fs != null) {
          fs.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return "image/image";
  }
}
