package practice;

public class Ex06_Operation {
    public static void main(String[] args) {
        /*
		 if(조건식) {}
		 if(조건식) {} else {}
		 if(조건식) else if(조건식) {} else if(조건식) ... else {}

		 간단한 학점 계산기
		 학점 : A+ , A- , B+ , B- , ..... F

		 판단기준 : 90점 이상 이고 95점 이하이면
		 // 90 A > 다시판단 95 A+ , A-
		 // 80 B > 다시판단 85 B+ , B-

		 90 , 80 , 70   나머지 F

		 if(){
		 	//조건 판단
		 }else if(){

		 }
		 87
		*/

        String grade="";
        int score = 87;

//        if(score>=90) {
//            grade+="A";
//            if(score>=95) grade+="+";
//            else grade+="-"	;
//        }
//        else if(score>=80) {
//            grade+="B";
//            if(score>=85) grade+="B+";
//            else grade+="B-"	;
//        }
//        else if(score>=70) {
//            grade+="C";
//            if(score>=75) grade+="+";
//            else grade+="-";
//        }

//        grade = (score>=95)? (grade+="+"):(grade+="-");
//        else if(score>=80){
//            grade = "B";
//            grade = (score>=85)? (grade+="+"):(grade+="-");
//        }
//        else if(score>=70){
//            grade = "C";
//            grade = (score>=75)? (grade+="+"):(grade+="-");
//        }
//        else
//            grade+="F";
//        System.out.println("당신의 학점은" + grade);

        for (int i=2; i<=9; i++){
            for (int j=1; j<=9; j++){
                if(i==j) break;
//                System.out.printf("%d*%d=%d\t",i,j,i*j);
                System.out.print("*"); //삼각형 모양
            }System.out.println();
        }
    }
}