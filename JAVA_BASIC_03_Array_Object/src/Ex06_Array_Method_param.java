class Human{
    String name;
}

class Test{
    int add(int i , int j) {
        return i + j;
    }

    void add(Human human) {
        System.out.println(human.name);
    }

    int add(int param) {
        return param + 100;
    }

    //POINT
    int[] add(int[] params) { // parameter int[] 주소값을 받는다

        //int[] add  int[] 주소값을 리턴
        int[] target = new int[params.length];
        for (int i = 0; i < target.length; i++) {
            target[i] = params[i]+1;
        }
        return target;
    }
}

public class Ex06_Array_Method_param {

    public static void main(String[] args) {

        Test test = new Test();
        int[] source = {10,20,30,40,50};
        int[] ta = test.add(source);


    }

}
