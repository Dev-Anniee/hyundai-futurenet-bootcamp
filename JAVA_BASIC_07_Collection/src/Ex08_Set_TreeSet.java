import java.util.Set;
import java.util.TreeSet;

public class Ex08_Set_TreeSet {
	public static void main(String[] args) {
		//Set : 순서 (x), 중복 (x)
		
		//TreeSet : 순서(x), 중복(x) 내부적으로 정렬된 데이터를 가지고 있어요 - 트리구조
		//로또 6개 중복 안된느데 그 정렬 TreeSet으로 해결
		
		//검색과 정렬에 효과적!
		Set<Integer> lotto = new TreeSet<Integer>();
		while(lotto.size()<6) {
			lotto.add((int)(Math.random()*45+1));
		}System.out.println(lotto.toString());
		
	}
}
