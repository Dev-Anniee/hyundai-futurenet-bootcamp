package kr.or.kosa;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

class Bank{
	List<Account> account = new ArrayList<>();
	int totalAccount;
	
	public void addAccount(String account_no,String name) {
		account.add(new Account(account_no, name));
	}
	
	public Account getAccount(String account_no) {
		Account a =null;
		for(int i=0; i<account.size(); i++) {
			if(account_no.equals(account.get(i).getAccount_no())) {
				a = account.get(i);
			}
		}
		return a; 
	}
	
	public List<Account> findAccounts(String name) {
		List<Account> a = new ArrayList<>();
		for(int i=0; i<account.size(); i++) {
			if(name.equals(account.get(i).getName())) {
				a.add(account.get(i));
			}
		}
		return a; 
	}
	
	public List<Account> getAccounts(){
		return account;
	}
	
	public int getTotalAccount() {
		int sum=0;
		for(int i=0; i<account.size(); i++) {
			sum+=account.get(i).getBalance();
		}
		return sum;
	}
}
	

class Account{
	Calendar cal = Calendar.getInstance();
	private String account_no;
	private String name;
	private long balance;
	List<Transaction> transactions;
	
	public String getAccount_no() {
		return account_no;
	}
	
	public String getName() {
		return name;
	}

	public Account(String account_no, String name) {
		this.account_no =account_no;
		this.name = name;
		this.balance = 0;
		transactions = new ArrayList<>();
	}
	
	public void deposit(long amount){
		balance+=amount;
		String transactionDate = String.format("%d년%02d월%02d일", 
                cal.get(Calendar.YEAR), 
                cal.get(Calendar.MONTH) + 1, 
                cal.get(Calendar.DAY_OF_MONTH));

		String transactionTime = String.format("%02d시%02d분", 
		                cal.get(Calendar.HOUR_OF_DAY), 
		                cal.get(Calendar.MINUTE));
		transactions.add(new Transaction(transactionDate, transactionTime,"입급", amount, balance));
		System.out.printf(amount+"원 입금하셨습니다\n");
	}
	
	public void withdraw(long amount){
		if(balance-amount>=0) {
			balance-=amount;
			String transactionDate = String.format("%d년%02d월%02d일", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));

			String transactionTime = String.format("%02d시%02d분", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));
			transactions.add(new Transaction(transactionDate, transactionTime,"출금", amount, balance));
			System.out.printf(amount+"원 출금하셨습니다\n");
		}
		else {
			System.out.println("잔고가 부족합니다");
		}
	}
	
	public long getBalance() {
		return balance; 
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public String toString() {
		return "계좌번호 = " + account_no + ", 이름 = " + name + ", 잔액 = " + balance ;
	}
}

class Transaction{
	String transactionDate;
	String transactionTime;
	String kind;
	long amount;
	long balance;
	
	 public Transaction(String transactionDate, String transactionTime, String kind, long amount, long balance) {
		    this.transactionDate = transactionDate;
		    this.transactionTime = transactionTime;
		    this.kind = kind;
		    this.amount = amount;
		    this.balance = balance;
		  }

		  @Override
		  public String toString() {
		    return "[거래금액: " + this.amount + ", 잔액: " + this.balance + "원 /" + this.transactionDate + this.transactionTime + "]";
		  }
}

public class BankAccount {
	public static void main(String[] args) {
		Bank bank = new Bank();
		bank.addAccount("10071","백");
		bank.addAccount("890113","택");
		bank.addAccount("0113","택");
		bank.addAccount("987654321","두팔");
		
		System.out.println("=전체 계좌 목록=");
		for(Account account : bank.getAccounts())
			System.out.println(account);
		
		System.out.println();
		System.out.println("= 해당 계좌번호의 계좌정보 =");
		Account account1 = bank.getAccount("890113");
		System.out.println(account1);
		
		System.out.println();
		account1.deposit(200000);
		System.out.println("현재 잔액은 : "+account1.getBalance());
		
		System.out.println();
		account1.deposit(200000);
		System.out.println("현재 잔액은 : "+account1.getBalance());
		
		System.out.println();
		List<Account> found1 = bank.findAccounts("택");
		
		System.out.println("= 해당 소유자명의 계좌 목록 =");
		if (found1 != null) { 
		    for (Account acc : found1) {
		        System.out.println(acc); 
		    }
		} else {
		    System.out.println("조회된 결과가 없습니다.");
		}
		
		System.out.println();
		System.out.println("= 해당 계좌번호의 계좌정보 =");
		Account account2 = bank.getAccount("");
		if (account2 != null) {
		    System.out.println(account2);
		} else {
		    System.out.println("해당 계좌번호를 찾을 수 없습니다.");
		}
			
		System.out.println();
		List<Account> found3 = bank.findAccounts("");
		
		System.out.println("= 해당 소유자명의 계좌 목록 =");
		if (found3 != null&& !found3.isEmpty()) {
		    for (Account acc : found3) {
		        System.out.println(acc); 
		    }
		} else {
		    System.out.println("조회된 결과가 없습니다.");
		}
		
		System.out.println();
		account1.withdraw(5500);
		System.out.println("현재 잔액은 : "+account1.getBalance());
			
		System.out.println();
		System.out.println("= 해당 계좌번호의 계좌정보 =");
		System.out.println(account1);
		
		System.out.println();
		System.out.println("= 거래 내역 =");
		for(Transaction transaction :account1.getTransactions())
			System.out.println(transaction);
	}
}
