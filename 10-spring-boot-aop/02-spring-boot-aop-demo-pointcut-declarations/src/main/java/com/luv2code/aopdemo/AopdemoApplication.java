package com.luv2code.aopdemo;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;
import com.luv2code.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService){
		return runner->{
			//demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			//demoTheAroundAdvice(theTrafficFortuneService);
			//demoTheAroundAdviceHandleException(theTrafficFortuneService);
			demoTheAroundAdviceRethrowException(theTrafficFortuneService);

			
		};
	}

	private void demoTheAroundAdviceRethrowException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain Program: demoTheAroundAdviceRethrowException");
		System.out.println("Calling getFortune()");

		boolean tripWear = true;

		String data = theTrafficFortuneService.getFortune(tripWear);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}


	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain Program: demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune()");

		boolean tripWear = true;

		String data = theTrafficFortuneService.getFortune(tripWear);
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n\nMain Program: demoTheAroundAdvice");
		System.out.println("Calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("\nMy fortune is: " + data);
		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try {
			//add a boolean flag to simulate exceptions
			boolean tripWar = false;
			theAccounts = theAccountDAO.findAccounts(tripWar);
		}catch (Exception exp){
			System.out.println("\n\nMain Program: ...caught exception: " + exp);
		}

		//display the account
		System.out.println("\n\nMain Program: demoThrAfterThrowingAdvice");
		System.out.println("------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try {
			//add a boolean flag to simulate exceptions
			boolean tripWar = true;
			theAccounts = theAccountDAO.findAccounts(tripWar);
		}catch (Exception exp){
			System.out.println("\n\nMain Program: ...caught exception: " + exp);
		}
		System.out.println("\n\nMain Program: demoThrAfterThrowingAdvice");
		System.out.println("------------");
		System.out.println(theAccounts);
		System.out.println("\n");

	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = theAccountDAO.findAccounts();
		System.out.println("\n\nMain Program: demoThrAfterReturningAdvice");
		System.out.println("------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO,MembershipDAO theMembershipDAO ) {
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		theAccountDAO.addAccount(myAccount,true);
		theAccountDAO.doWork();

		//call the accountDao getter/setter method
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		//call the membership business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();

	}

}
