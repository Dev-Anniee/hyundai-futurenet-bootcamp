/*
입출력 (input/ output) 대상  - 메모리

기본지식
1. Stream (데이터가 흘러가는 통로 : 중간 매개체 : 한방향)
1.1 빨대 (종류 - 일반적인 물, 알갱이 먹을 수 있는) 크기가 다르다  >>2개
1.2 Byte 통과 빨대, Char(문자 2Byte)

Stream 통해서 : byte -이미지, 엑셀
			  char - 문자, 데이터

---------------------------------
자바에서 JAVA API(I/O) 클래스 활용

1.추상 클래스 (완성된 자원 + 미완성된 자원 - 상황에 따라 재정의해서)
InputStream, OutputStream

고슬링 - 입출력 처리시 두 개를 상속해서 재정의 해서 사용해라
입출력 대상 - 클래스 선택
1. memory : Array, Collection (이미 학습했다!) - I/O ByteArrayInputStream (대상 + InputStream)
2. file (텍스트 write, read) - Point - FileInputStream, FileOutputStream (대상 + InputStream/ OutputStream) - 상속
3. 네트워크 (프로세스)

당신이 Byte 데이터 작업
대상  ... File >> FileInputStream, FileOutputStream (
*/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class Ex01_Stream {
    public static void main(String[] args) {
        // byte (-128~127)
        byte[] inSrc = {0,1,2,3,4,5,6,7,8,9}; //10개의 배열
        byte[] outSrc = null; //byte 배열의 주소값 (메모리가 없다 = 주소값이 없다)

        // 빨대 >> MemoryHandler >> read/write
        ByteArrayInputStream inputStream =null; //통로 중간매개체 byte - read
        ByteArrayOutputStream outputStream = null;

        inputStream = new ByteArrayInputStream(inSrc); //input (read) >>byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
        outputStream = new ByteArrayOutputStream(); //write 준비

        System.out.println("outSrc before : "+Arrays.toString(outSrc));

        //공식
        int data = 0;
        while((data = inputStream.read())!= -1){
            System.out.println(data);
            //read 데이터를 담은 Stream을 출력
            outputStream.write(data); //data 값을 read 해서 자신의 메모리에 가지고 있다
            //빨대에 데이터를 담고 있다
        }

        outSrc = outputStream.toByteArray(); //write 대상 Array
        System.out.println("outSrc: "+Arrays.toString(outSrc));

//		data = 0;
//		while (inputStream.read() != -1) {
//			System.out.println(inputStream.read()); // 1 3 5 7 9 read가 next() 기능도 가지고 있다
//		}
    }
}
