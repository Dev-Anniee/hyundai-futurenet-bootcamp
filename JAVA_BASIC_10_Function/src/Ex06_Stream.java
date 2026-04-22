import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Ex06_Stream {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("a","b","c");
		Stream<String> listStream= list.stream();
		
		Stream<String> stream1 = Stream.of("a","b","c");
		Stream<String> stream2 = Stream.of(new String[] {"a","b","c"});
		Stream<String> stream3 = Arrays.stream(new String[] {"a","b","c"});
		
		stream1.forEach(System.out::println);
		
		//원시 타입별 stream 제공
		
		IntStream stream4 = IntStream.range(4, 10);
		stream4.forEach(System.out::println);
	}
}
