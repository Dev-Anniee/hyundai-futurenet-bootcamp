package com.model;

/*
* 게시판 글쓰기 + 파일 업로드
* create table photo(
* name
* age
* image > 파일 저장 아니고 > 파일 이름 > 1.jpg
* 크기, 종류, 저장경로
* )
*
* 파일은 웹서버 특정 폴더 저장 > webapp > upload 저장
* AWS s3 서버 저장
*
* 파일 업로드
* 1. 파일서버 (s3) : 파일 write > IO >File, InputStram, OutputStream 구현
* 2. 파일 정상 write >DB > insert > 파일이름, 파일용량, 확장자 저장
*
* 2번의 작업 :IO, DB 작업
*
* Spring에서는 DTo > 파일 객체도 받을 수 있다
* 파일 처리하기 위해서 private CommonsMultipartFile file;
*/

import lombok.Data;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Data
public class Photo {
  private String name;
  private int age;
  private String image;

  private CommonsMultipartFile file;
}
