import java.util.Scanner;

// Product
interface Payment {
    void processAmount(int amount);
}

// ConcreteProducts
class CardPayment implements Payment {
    public void processAmount(int amount) {
        System.out.println("신용카드 결제 중: " + amount + "원");
    }
}

class NaverPayPayment implements Payment {
    public void processAmount(int amount) {
        System.out.println("네이버페이 포인트 결제 중: " + amount + "원");
    }
}

// Creator
abstract class PaymentFactory {
    // 팩토리 메서드: 자식 클래스가 어떤 객체를 생성할지 결정함
    abstract Payment createPayment();

    public void orderPayment(int amount) {
        Payment payment = createPayment();
        payment.processAmount(amount);
    }
}

// ConcreteCreators
class CardFactory extends PaymentFactory {
    @Override
    Payment createPayment() {
        return new CardPayment();
    }
}

class NaverPayFactory extends PaymentFactory {
    @Override
    Payment createPayment() {
        return new NaverPayPayment();
    }
}

//Abstract Products
interface Currency {
    String getSymbol();
}

interface Delivery {
    void ship();
}

//Concrete Products (KR)
class Won implements Currency {
    public String getSymbol() {
        return "KRW";
    }
}

class KoreaPost implements Delivery {
    public void ship() {
        System.out.println("우체국 택배 배송");
    }
}

//Concrete Products (US)
class Dollar implements Currency {
    public String getSymbol() {
        return "USD";
    }
}

class Fedex implements Delivery {
    public void ship() {
        System.out.println("Fedex Shipping");
    }
}

//Abstract Factory
interface ShopComponentFactory {
    Currency createCurrency();

    Delivery createDelivery();
}

//Concrete Factories
class KoreaShopFactory implements ShopComponentFactory {
    public Currency createCurrency() {
        return new Won();
    }

    public Delivery createDelivery() {
        return new KoreaPost();
    }
}

class USShopFactory implements ShopComponentFactory {
    public Currency createCurrency() {
        return new Dollar();
    }

    public Delivery createDelivery() {
        return new Fedex();
    }
}

abstract class BasePaymentProcessor {
    // Template Method: 순서를 고정하기 위해 final 선언
    public final void processPayment() {
        verifyInfo(); // 1. 정보 검증
        checkBalance(); // 2. 잔액 확인
        executeExternal(); // 3. 외부 통신
        saveLog(); // 4. 로그 저장
    }

    private void verifyInfo() {
        System.out.println("[공통] 사용자 정보를 검증합니다.");
    }

    private void saveLog() {
        System.out.println("[공통] 결제 결과를 DB에 기록합니다.");
    }

    // 자식이 반드시 구현해야 하는 단계
    protected abstract void checkBalance();

    protected abstract void executeExternal();
}

// Concrete Classes
class CreditCardProcessor extends BasePaymentProcessor {
    @Override
    protected void checkBalance() {
        System.out.println("[카드] 카드 한도를 조회합니다.");
    }

    @Override
    protected void executeExternal() {
        System.out.println("[카드] PG사 서버와 API 통신을 수행합니다.");
    }
}

class CryptoPaymentProcessor extends BasePaymentProcessor {
    @Override
    protected void checkBalance() {
        System.out.println("[코인] 지갑 내 잔액 존재 여부를 확인합니다.");
    }

    @Override
    protected void executeExternal() {
        System.out.println("[코인] 블록체인 네트워크에 트랜잭션을 전송합니다.");
    }
}

public class ShoppingMallSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // --- 1. 국가 및 화폐 설정 (추상 팩토리 선택) ---
        System.out.println("쇼핑몰 국가를 선택하세요 (1: 한국, 2: 미국): ");
        int countryChoice = scanner.nextInt();

        ShopComponentFactory shopFactory;
        if (countryChoice == 1) {
            shopFactory = new KoreaShopFactory();
        } else {
            shopFactory = new USShopFactory(); // 기본값 미국
        }

        System.out.println("\n[설정 완료] 화폐 단위: " + shopFactory.createCurrency().getSymbol());
        System.out.println("--------------------------------");

        // --- 2. 결제 수단 선택 (팩토리 메서드 및 템플릿 메서드 전략 선택) ---
        System.out.println("결제 수단을 선택하세요 (1: 신용카드, 2: 네이버페이, 3: 가상화폐): ");
        int paymentChoice = scanner.nextInt();

        PaymentFactory paymentFactory = null;
        BasePaymentProcessor processor = null;

        switch (paymentChoice) {
            case 1:
                // 팩토리 메서드 패턴: Card 객체 생성 결정
                paymentFactory = new CardFactory();
                // 템플릿 메서드 패턴: 카드 결제 알고리즘 선택
                processor = new CreditCardProcessor();
                break;

            case 2:
                paymentFactory = new NaverPayFactory();
                // 네이버페이 전용 프로세서가 없으므로 일반 프로세서나 별도 구현체 연결 가능
                // 여기서는 예시로 CreditCardProcessor를 공유하거나 신규 생성 가능
                processor = new CreditCardProcessor();
                break;

            case 3:
                // 가상화폐 결제 (기존 Factory 구조에 CryptoFactory가 있다면 사용 가능)
                // 여기서는 템플릿 메서드 확인을 위해 프로세서 위주로 할당
                processor = new CryptoPaymentProcessor();
                break;

            default:
                System.out.println("잘못된 선택입니다. 프로그램을 종료합니다.");
                return;
        }

        // --- 3. 실행 프로세스 ---
        System.out.println("\n--- [결제 진행] ---");

        // 팩토리 메서드 실행 (결제 객체 생성 및 간단 처리)
        if (paymentFactory != null) {
            paymentFactory.orderPayment(50000);
        }

        // 템플릿 메서드 실행 (정해진 비즈니스 로직 순서 실행)
        if (processor != null) {
            System.out.println("\n[상세 프로세스 구동]");
            processor.processPayment();
        }

        // --- 4. 배송 시작 ---
        System.out.println("\n--- [물류 시스템] ---");
        shopFactory.createDelivery().ship();

        scanner.close();
    }
}