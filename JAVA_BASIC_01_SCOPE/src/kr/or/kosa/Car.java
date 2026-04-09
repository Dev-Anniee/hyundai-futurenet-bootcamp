package kr.or.kosa;

// 같은 패키지에서만 사용하겠다라는 의지
class Car { //default 생략 ->  같은 패키지(폴더)에서만 사용할겠다
    int door; //안쓰면 default, 쓰면 오류 난다-> 안 써도 되니까 디폴트
    public String color; //말도 안되는 코드
    private int window; //같은 패키지 안에서 사용하겠지만 노출 안 하겠다, 캡슐화 -> 같은 패키지에서 볼 수는 없다
    // window 자원에 read, write, method를 구현하겠다라는 의지
    // public...
}

