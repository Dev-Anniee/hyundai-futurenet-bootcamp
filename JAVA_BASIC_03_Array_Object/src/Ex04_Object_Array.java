import java.util.Iterator;

class Person{
    String name;
    int age;
    void print() {
        System.out.println(this.name + " "+ this.age);
    }
}

public class Ex04_Object_Array {
    public static void main(String[] args) {
        int[] intarr = new int[10];
        //default [0][0][0][0] 값
        boolean[] boolarr = new boolean[5];
        //default [false][f][f][f] 값

        Person person = new Person();
        person.name = "홍길동";
        person.age = 100;

        //person타입의 사람 3명
        Person person1 = new Person();
        Person person2 = new Person();
        Person person3 = new Person();

        //같은 타입의 여러개의 ...Array
        Person[] persons = new Person[3];
        persons[0] = new Person();

        persons[1] = new Person();
        persons[1].name = "아무개";
        persons[1].age = 100;

        for (int i = 0; i < persons.length; i++) {
            System.out.println(persons[i].name);

        }

        Person[] parray= new Person[10];
        for (int i = 0; i < parray.length; i++) {
            parray[i] = new Person();
            System.out.println("주소값: "+parray[i]);
        }

        //2.int[] intarr = new int[]{10,20,30}
        Person[] parry2 = new Person[] {new Person(), new Person(), new Person()};

        //3. int[]  arr = {10,20}
        Person[] parry3 = {new Person(), new Person(), new Person()};
        //참조 타입은 메모리에 주소를 갖는다
    }}
