import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Lotto {
	Scanner scanner = new Scanner(System.in);
	List<Integer> list; //사용자의 로또번호 생성
	private List<Integer> lastWinNumbers;
	
	public Lotto(){
		list = new ArrayList<>();
		lastWinNumbers = new ArrayList<>();
		int week =10; // 10주차까지 생성
		
		String dirPath = "lotto_results";
		File directory = new File(dirPath);
		
		if (!directory.exists()) {
	        if (directory.mkdir()) {
	            System.out.println("폴더가 생성되었습니다: " + dirPath);
	        }
	    }
		
		for(int i=0; i<week; i++) {
			String fileName = dirPath+ "/" + (i + 1) + "주차.txt";
			
			try (FileOutputStream fileOutputStream = new FileOutputStream(fileName); 
				 PrintWriter pw = new PrintWriter(fileOutputStream)){
				List<Integer> lotto = new ArrayList<>();
                while (lotto.size() < 6) {
                    int num = (int) (Math.random() * 45 + 1);
                    if (!lotto.contains(num)) {
                        lotto.add(num);
                    }
                }
                bubbleSort(lotto);
                pw.println((i + 1) + "회차 로또 번호: " + lotto.toString());
                System.out.println(fileName + " 생성 완료");
                
                if (i == week - 2) {
                    this.lastWinNumbers = new ArrayList<>(lotto);
                }
               }catch (Exception e) {
            	   System.out.println("오류 발생: " + e.getMessage());
			}
		}
	}
	
	public static void bubbleSort(List<Integer> list) {
		int n = list.size();
		for(int i=n-1; i>0; i--) {
			for(int j=0; j<i; j++) {
				if(list.get(j) > list.get(j + 1)) {
					int temp = list.get(j);
                	list.set(j, list.get(j + 1));
                	list.set(j + 1, temp);
				}
			}
		}
	}
	
	public void menu() {
		System.out.println("1. 로또 구매하기");
		System.out.println("2. 로또 번호 확인하기");
		System.out.println("0. 프로그램 종료");
	    System.out.print("선택 > ");
	}
	
	public void buyLotto() {
	    list.clear(); 
	    int choice = 0;
	    while (true) {
	        try {
	            System.out.println("옵션을 선택하세요 1. 자동 2. 수동 : ");
	            choice = Integer.parseInt(scanner.nextLine());
	            if (choice == 1 || choice == 2) break;
	            System.out.println("[오류] 1번(자동) 또는 2번(수동)만 선택 가능합니다.");
	        } catch (NumberFormatException e) {
	            System.out.println("[오류] 숫자만 입력 가능합니다.");
	        }
	    }

	    if (choice == 1) {
	        // 2. 자동 번호 생성
	    	System.out.print("직전회차 당첨번호를 제외하시겠습니까? 1. 예 2. 아니요 : ");
	        int excludeLast = Integer.parseInt(scanner.nextLine());
	        
	        System.out.print("분포 규칙(구간별 최대 3개, 최소 3구간)을 적용하시겠습니까? 1. 예 2. 아니요 : ");
	        int applyDist = Integer.parseInt(scanner.nextLine());
	        
	        boolean isSatisfied = false;
	        while (!isSatisfied) {
	            list.clear();
	            // 6개 번호 생성
	            while (list.size() < 6) {
	                int ran = (int) (Math.random() * 45 + 1);
	                
	                // 중복 체크
	                if (list.contains(ran)) continue;
	                
	                // 직전 회차 제외 옵션 적용
	                if (excludeLast == 1 && lastWinNumbers.contains(ran)) continue;
	                
	                list.add(ran);
	            }

	            // 분포도 검사 옵션 적용
	            if (applyDist == 1) {
	                if (checkDistribution(list)) {
	                    isSatisfied = true; 
	                }
	            } else {
	                isSatisfied = true; 
	            }
	        }

	    } else {
	        // 3. 수동 번호 입력
	        while (list.size() < 6) {
	            System.out.println("1~45 사이의 숫자 6개를 공백으로 구분해서 입력하세요.");
	            System.out.print("번호 입력 > ");
	            String input = scanner.nextLine().trim();
	            
	            if (input.isEmpty()) continue; // 빈 입력 방지

	            String[] parts = input.split("\\s+"); // 공백이 여러 개여도 처리 가능하도록 정규식 사용

	            try {
	                for (String part : parts) {
	                    int val = Integer.parseInt(part);
	                    // 범위 체크 및 중복 체크
	                    if (val >= 1 && val <= 45) {
	                        if (!list.contains(val)) {
	                            list.add(val);
	                        } else {
	                            System.out.println("[알림] 중복된 숫자는 제외되었습니다: " + val);
	                        }
	                    } else {
	                        System.out.println("[알림] 범위를 벗어난 숫자는 제외되었습니다: " + val);
	                    }
	                    
	                    if (list.size() == 6) break; // 6개 채워지면 중단
	                }

	                if (list.size() < 6) {
	                    System.out.println("[오류] 현재 " + list.size() + "개 입력됨. 6개를 정확히 채워주세요.");
	                    list.clear(); // 6개가 안 되면 처음부터 다시 입력
	                }
	            } catch (NumberFormatException e) {
	                System.out.println("[오류] 숫자 형식이 아니거나 잘못된 입력이 포함되어 있습니다.");
	                list.clear();
	            }
	        }
	    }

	    bubbleSort(list);
	    System.out.println("구매 완료: " + list.toString());
	}
	
	private boolean checkDistribution(List<Integer> nums) {
	    int[] sections = new int[5]; // 1-10, 11-20, 21-30, 31-40, 41-45
	    for (int n : nums) {
	        if (n <= 10) sections[0]++;
	        else if (n <= 20) sections[1]++;
	        else if (n <= 30) sections[2]++;
	        else if (n <= 40) sections[3]++;
	        else sections[4]++;
	    }

	    int usedSections = 0;
	    for (int count : sections) {
	        if (count > 3) return false; // 한 구간 최대 3개 초과 금지
	        if (count > 0) usedSections++;
	    }
	    return usedSections >= 3; // 최소 3개 구간 사용 여부
	}
	
	public void checkLotto() {
	    if (list.isEmpty()) {
	        System.out.println("구매한 로또 번호가 없습니다. 먼저 구매해주세요.");
	        return;
	    }

	    System.out.print("확인할 주차를 입력하세요 (1~10): ");
	    int week = Integer.parseInt(scanner.nextLine());
	    String fileName = "lotto_results/" + week + "주차.txt";

	    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
	        String line = br.readLine();
	        if (line != null) {
	            String numbersPart = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
	            String[] tokens = numbersPart.split(", ");
	            
	            List<Integer> winningNums = new ArrayList<>();
	            for (String s : tokens) {
	                winningNums.add(Integer.parseInt(s.trim()));
	            }
	            
	            int matchCount = (int) list.stream().filter(winningNums::contains).count();

	            System.out.println("------------------------------");
	            System.out.println("당첨 결과 확인 (" + week + "주차)");
	            System.out.println("당첨 번호: " + winningNums);
	            System.out.println("나의 번호: " + list);
	            System.out.println("일치 개수: " + matchCount + " -> " + getRank(matchCount));
	            System.out.println("------------------------------");
	        }
	    } catch (Exception e) {
	        System.out.println("[오류] 파일을 읽는 중 문제가 발생했습니다: " + e.getMessage());
	    }
	}
	
	public String getRank(int count) {
	    switch (count) {
	        case 6:  return "1등 (6개 일치!)";
	        case 5:  return "2등 (5개 일치!)";
	        case 4:  return "3등 (4개 일치!)";
	        case 3:  return "4등 (3개 일치!)";
	        default: return "꽝 (낙첨)";
	    }
	}
	
	public void process() {
		int num =0;
		do {
			menu();
            num = Integer.parseInt(scanner.nextLine()); 

            switch (num) {
                case 1:
                    System.out.println("로또 구매 로직을 실행합니다.");
                	buyLotto();
                    break;
                case 2:
                    System.out.println("저장된 로또 번호를 확인합니다.");
                	checkLotto();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 0~2 사이의 숫자를 입력해주세요.");
                    break;
            }
		}while(num!=0);
	}
	
	public static void main(String[] args) {
		Lotto lotto = new Lotto();
		lotto.process();
	}
	
}
