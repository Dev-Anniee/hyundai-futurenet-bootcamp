/*
Stream 결과 만들기(최종 연산)

중간 연산을 통해 생성된 Stream을 바탕으로 이제 결과를 만들 차례이다. 
결과를 만들기 위한 최종 연산에는 다음과 같은 것들이 존재한다.

 [ 최댓값/최솟값/총합/평균/갯수 - Max/Min/Sum/Average/Count ]
 Stream의 요소들을 대상으로 최솟값이나 최댓값 또는 총합을 구하기 위한 최종 연산들이 존재한다. 
 최솟값이나 최댓값을 구하기 위해서는 max와 min을 이용해야 하며,
 총합 또는 평균 또는 개수를 구하기 위해서는 sum과 average, count를 이용해야 한다. 
 min이나 max 또는 average는 Stream이 비어있는 경우에 값을 특정할 수 없다. 
 그렇기 때문에 다음과 같이 Optional로 값이 반환된다.
*/ 
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Ex08_Stream {

	public static void main(String[] args) {
		
		OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
		
		int max = IntStream.of().max().orElse(0);
		
		IntStream.of(1, 3, 5, 7, 9).average().ifPresent(System.out::println);

		System.out.println(min.getAsInt());
		System.out.println(min.orElse(0));
		//min.getAsInt();      // 값이 있을 때
		//min.orElse(0);       // 없으면 0
		System.out.println(max);

		/*
		반면에 총합이나 갯수의 경우에는 값이 비어있는 경우 0으로 값을 특정할 수 있다. 
		그렇기 때문에 Stream API는 sum 메소드와 count 메소드에대해 Optional이 아닌 원시
		값을 반환하도록 구현해두었다.
		당연히 Stream이 비어있을 경우에는 0을 반환하게 된다.
		*/

				long count = IntStream.of(1, 3, 5, 7, 9).count();
				long sum = LongStream.of(1, 3, 5, 7, 9).sum();

				List<String> list = Arrays.asList("lee" , "park" , "kim");
				
				Iterator<String> it = list.iterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
				
				//Stream 이용하기
				list.stream().forEach(name -> System.out.println(name));

}

}
