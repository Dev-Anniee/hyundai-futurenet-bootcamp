import java.util.Scanner;
// 음료 종류

// 메뉴 클래스
// 메뉴에 대한 추상 클래스 -> 하위로 메뉴를 만든다.

// 동작 순서 (
// 1. 메뉴를 보여준다. (품절 표시)
// 2. 돈을 넣는다.
// 3. 메뉴를 고른다(버튼을 입력한다).
// 4. 돈을 거슬러준다.
// 5. 음료가 나온다.

class Menu {
	private final int price;
	int cnt; // 자판기가 보유하고 있는 각 음료의 재고 수량

	public Menu(int price) {
		this.price = price;
		this.cnt = 10;
	}

	public int getPrice() {
		return price;
	}

	public int getCnt() {
		return cnt;
	}

	void buyDrink() {
		this.cnt--;
	}
}

class VendingMachine {
	int money; // 받는 돈
	Menu[] menus;

	public VendingMachine() {
		this.menus = new Menu[] { new Americano(1000), new Latte(1500), new Cocoa(2000), new Yulmucha(1800),
				new Milk(800) };
	}

	void process() {
		Scanner scanner = new Scanner(System.in);
		int index=0;

		do {
			display();
			try {
			    index = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
			    System.out.println("숫자를 입력해주세요.");
			    continue;
			}

			switch (index) {
			case 1:
				displayMenu();
				break;
			case 2:
				System.out.println("얼마를 넣으실 건가요? ");
				System.out.print("금액 입력 > ");

				try {
					int input = Integer.parseInt(scanner.nextLine());
					if (input < 0 || input >= 1000000 || this.money + input >= 1000000) {
						throw new IllegalArgumentException("범위 초과");
					}
					this.money += input;

				} catch (IllegalArgumentException e) { 
					System.out.println("정수부터 백만원 미만의 금액만 입력해주세요.");
				} 

				break;
			case 3:
				displayAll();
				System.out.println("무엇을 구매하실 건가요? ");
				System.out.print("번호 입력 > ");
				int input = Integer.parseInt(scanner.nextLine());
				if (!buy(--input))
					index = 4;
				break;
			case 4:
				System.out.println("거스름돈을 " + returnMoney() + "원 받았습니다");
				break;
			default:
				System.out.println("잘못된 번호입니다");
				break;
			}
		} while (index != 4);
	}

	void display() {
		System.out.println("---------------------- ");
		System.out.println("\n 1번 : 자판기 재고 조회 ");
		System.out.println("\n 2번 : 돈 투입  ");
		System.out.println("\n 3번 : 구매  ");
		System.out.println("\n 4번 : 거스름돈 받기");
		System.out.println("---------------------- ");
		System.out.print("번호 입력 > ");
	}

	void displayMenu() {
		System.out.println("\n======== 자판기 재고 조회  =========");
		for (Menu menu : menus) {
			System.out.println(menu.toString() + " : " + menu.getCnt());
		}
	}

	void displayAll() {
		System.out.println("\n======== 자판기 메뉴 조회  =========");
		for (int i = 0; i < menus.length; i++) {
			System.out.println((i + 1) + "번 . " + menus[i].toString() + " : " + menus[i].getPrice() + " 남은 수량 : "
					+ menus[i].getCnt());
		}
	}

	boolean canBuy(int index) {
		if (index < 0 || index >= menus.length) {
			System.out.println("1~5번 사이의 번호를 입력해주세요");
			return false;
		}

		if (menus[index].getCnt() <= 0) {
			System.out.println("품절입니다");
			return false;
		}

		if (menus[index].getPrice() > this.money) {
			System.out.println("금액 부족");
			return false;
		}

		return true;
	}

	boolean buy(int index) {
		if (canBuy(index)) {
			menus[index].buyDrink();
			this.money -= menus[index].getPrice();
			System.out.println(menus[index] + "가 추출되었습니다");
			System.out.println("남은 금액: " + this.money + "원");

			if (this.money == 0)
				return false;
		} else
			System.out.println("투입한 금액: " + this.money + "원");

		return true;
	}

	int returnMoney() {
		int tmp = this.money;
		this.money = 0;
		return tmp;
	}
}

class Americano extends Menu {
	public Americano(int price) {
		super(price);
	}

	@Override
	public String toString() {
		return "Americano";
	}
}

class Latte extends Menu {
	public Latte(int price) {
		super(price);
	}

	@Override
	public String toString() {
		return "Latte";
	}
}

class Cocoa extends Menu {
	public Cocoa(int price) {
		super(price);
	}

	@Override
	public String toString() {
		return "Cocoa";
	}
}

class Yulmucha extends Menu {
	public Yulmucha(int price) {
		super(price);
	}

	@Override
	public String toString() {
		return "Yulmucha";
	}
}

class Milk extends Menu {
	public Milk(int price) {
		super(price);
	}

	@Override
	public String toString() {
		return "Milk";
	}
}

public class Vending_Machine {
	public static void main(String[] args) {
		VendingMachine vendingMachine = new VendingMachine();
		vendingMachine.process();
	}
}
