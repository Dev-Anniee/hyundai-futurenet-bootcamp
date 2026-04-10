public class Ex03_Array_for {
    public static void main(String[] args) {
/*		Today Point
		for 문 > 향상된 , 개선된 for문
		java : for문 (Type 변수명 : 배열 or Collection)
		C# : for문 (Type 변수명 in 배열 or Collection)
		javascript : for문 (let index...in 배열){} ...for(let value of 배열) {}
		*/
        int[] arr = {5,6,7,8,9};
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        for(int value : arr) {
            System.out.println(value);
        }
        String[] strarrStrings = {"A","B","C","D","FFF"};
        for (String string : strarrStrings) {
            System.out.println(string);
        }
    }
}

