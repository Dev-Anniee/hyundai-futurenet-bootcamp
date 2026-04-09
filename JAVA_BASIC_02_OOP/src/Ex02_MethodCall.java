import kr.or.kosa.common.Fclass;

public class Ex02_MethodCall {
    //모든 코드는 메모리에 올라와있다는 전제
    public static void main(String[] args) {
        Fclass fclass = new Fclass();
        fclass.m();
        fclass.m2(1234);
        int result = fclass.m3(); //리턴할 땐 받아야 한다
        System.out.println("result value : " + result);

        result = fclass.sum(100,200,300);
        System.out.println("result value : " + result);

        fclass.callSubSum();
        result = fclass.opSum(-500);
        System.out.println("result value :" + result);
    }
}
