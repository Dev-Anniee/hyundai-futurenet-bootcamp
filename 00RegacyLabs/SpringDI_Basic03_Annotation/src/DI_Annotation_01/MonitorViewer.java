package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {

	//MonitorViewer 는 recorder  에 의존합니다
	//MonitorViewer 는 recorder 객체의 주소가 필요합니다
	//new recorder() 메모리를 만들고 메모리의 주소를 달라고 ..
	
	//Spring 에서 : DI (constructor,setter)
	//설정 : xml or annotation
	
	private Recorder recorder; //recorder > null

	public Recorder getRecorder() {
		return recorder;
	}

	//컨테이너 안에서 주입 객체를 찿아요 (타입으로) > 있으면 > 그 주소를 자동 주입
	/*
	 @Autowired(required = true) >> default >> 무조건 >> injection
	 @Autowired(required = false) >> 있으면 주입 없으면 말고 ... 
	 */
	
	@Autowired
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
	
	/*
	 <property name="recorder"> 
	 	<ref bean=""
	 </property> 
	 */
	
}
