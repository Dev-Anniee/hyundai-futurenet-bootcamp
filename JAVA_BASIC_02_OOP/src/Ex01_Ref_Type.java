//연습용

import kr.or.kosa.common.Person;

class Test{
    int i;
    int j;
    void print() {
        System.out.println(i);
    }
}
public class Ex01_Ref_Type {
    public static void main(String[] args) {
        //Person 설계도 구체화 (메모리에 올려라) > new
        Person person = new Person();
        person.name = "홍길동";
        person.age = 100;
        person.power = true;

        person.personPrint();
        System.out.println(person); //kr.or.kosa.common.Person@2a18f23c
        System.out.println(person.toString());

        Person person2 = person; //주소값 할당

        //동거 시작(person 메모리나 person2 같은 메모리)
        if(person == person2)
            System.out.println("같은 집에 살아요");

    }

}

