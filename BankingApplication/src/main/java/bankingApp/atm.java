package bankingApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class atm {
	
	Scanner s = new Scanner(System.in);
	
	ArrayList<Customer> custys = new ArrayList<Customer>();
	ArrayList<Application> apply = new ArrayList<Application>();
	ArrayList<JointApp> Japps = new ArrayList<JointApp>();
	String uname;
	String pw;
	String U2;
	String P2;
	String lname;
	String fname;
	int funds;
	
	
	String BAun = "Big Daddy";
	String BApw = "BigPapa";
	
	String Eu = "Big Mama";
	String Ep = "BigMa";
	
	ArrayList<Customer> custList = new ArrayList<Customer>(); 
	ArrayList<Application> appList = new ArrayList<Application>();
	ArrayList<JointApp> JappList = new ArrayList<JointApp>();
	
	public void battery() {
		sys();
	}
	
	public void sys() {
		
		readfile();
		readApps();
		readJA();
				  
		System.out.println("Login as:" + "\n" + "a) Customer" + "\n"
					+ "b) Employee"+"\n" + "c) Bank Admin" + "\n" 
					);//seed												
		String p = s.nextLine(); //stems out

		if (p.equals("a"))  {//branch 1
			//Customer a = new Customer();
			System.out.println("What would you like to do?"+"\n"
					+"a)Register"+ "\n"
					+ "b)Login to account" );
			String c1 = s.nextLine();
			if( c1.equals("a")) {//branch 1a
				System.out.println("Would you like to apply for an : " + "\n" + " a) Account" 
									+"\n" + " b) Joint Account");
				String o1 = s.nextLine();
				if (o1.equals("a")) {
					System.out.println("What is your first name?");
					String fname = s.nextLine();
					System.out.println("What is your last name?");
					String lname = s.nextLine();
					System.out.println("Create New Username:");
					String uname = s.nextLine();
					System.out.println("Create Password:");
					int funds = 10;
					String pw = s.nextLine();
					apply.add(new Application (fname,lname,uname,pw,funds)); 
					writeApps(apply);
					System.out.println("Your Account Application has been submitted!");
					sys();
				} else if (o1.equals("b")) { 
					System.out.println("What is the first account holder's full name?");
					String fl1 = s.nextLine();
					System.out.println("What is the second account holder's full name?");
					String fl2 = s.nextLine();
					System.out.println("Create Username:"); // figure out how to accept application
					String uname1 = s.nextLine();
					System.out.println("Create Password:");
					int funds = 10;
					String pw1 = s.nextLine();
					//System.out.println("Create Second Username:");
					//String uname2 = s.nextLine();
					//System.out.println("Create Password for Second User:");
					//String pw2 = s.nextLine();
					Japps.add(new JointApp (fl1, fl2, uname1, pw1, funds)); 
					writeJA(Japps);
					System.out.println("Your Joint Account Application has been submitted!");
					sys();
				}
			} else if(c1.equals("b")) {//branch 1b
				System.out.println("Input Username: ");
				String checkUname = s.nextLine();
				System.out.println("Input Password: ");
				String checkPwd = s.nextLine();
				for (int i = 0 ; i < custList.size(); i++) {
					if (custList.get(i).getUN().equals(checkUname) && custList.get(i).getPW().equals(checkPwd)) {
						//Customer $4T = custList.get(i);
						//int x = indexOf((custList.get(i));
					 System.out.println("Would you like to:" + "\n" + "a) Withdraw" + "\n" + "b) Deposit" 
											+"\n"+ "c) Transfer");
							String c2b = s.nextLine(); 
							if (c2b.contentEquals("a")) { 
								//Withdrawal
								int $ = custList.get(i).get$(); 
								System.out.println("How much would you like to withdraw?"); 
								int less$ = Integer.parseInt(s.nextLine()); 
								int newF = $ - less$;
								custList.get(i).setFunds(newF);
								System.out.println("New Balance: $"+custList.get(i).get$());
								writefile(custList);
								
							} else if (c2b.equals("b")) { 
								//Deposit
								int $ = custList.get(i).get$(); //change funds to double
								System.out.println("How much would you like to Deposit?"); 
								int plus$ = Integer.parseInt(s.nextLine()); 
								int newF = $ + plus$;
								custList.get(i).setFunds(newF);
								System.out.println("New Balance: $"+custList.get(i).get$());
								writefile(custList);
								
							} else if (c2b.equals("c")){
								//Transfers
								System.out.println("Enter Account Username that you will be transferring money to: ");
								String h = s.nextLine();
								System.out.println("Enter Account Password that you will be transferring money to: ");
								String h1 = s.nextLine();
									for (int t = 0; t < custList.size(); t++ ) {
										if ((custList.get(t).getUN().equals(h) && custList.get(t).getPW().equals(h1)) && t!=i) {
											
											System.out.println("Transfer has begun\n" + "How much money would you like to Transfer?");
											int m$ = Integer.parseInt(s.nextLine());
											int newF = custList.get(i).get$() - m$;
											custList.get(i).setFunds(newF);
											int nf = custList.get(t).get$() + m$;
											custList.get(t).setFunds(nf);
											System.out.println("New Balance for 1st Account: $"+custList.get(i).get$() + "\n"
															+	"New Balance for 2nd Account: $"+custList.get(t).get$());
												
										}
									}
									writefile(custList);
							} 
							sys();
					} else {
						System.out.println("You're a thief!!" + "\n" );
						sys();
					}
				}
			}
		}
		
// Employee branch 2		
		
		else if(p.equals("b")) {
	 		System.out.println("Login in to Employee Account" + "\n" + "What is your Username?");
	 		String eu = s.nextLine();
	 		System.out.println("What is your Password?");
	 		String ep = s.nextLine(); 
	 		
	 		if (eu.equals(Eu) && ep.equals(Ep)) {
	 			System.out.println("Would you like to:" +"\n"+ "a) View Customer Information" + "\n"
									+ "b) Approve or deny normal applications" + "\n" + "c) Approve or deny joint applications");
	 			String e1 = s.nextLine();
	 			if(e1.equals("a")) {
	 					for (int i=0; i< custList.size(); i++){
	 						System.out.println(custList.get(i) + "\n");
	 					} sys();
	 			} else if(e1.equals("b")) {
	 				System.out.println("List of Normal Accounts to be Approved:");
	 				for(int i=0; i<appList.size(); i++) {
						System.out.println(i+1 + ": " + appList.get(i));
					}
					System.out.print('\n');
	 				System.out.println("Would you like to:"+ "\n" + "a) Approve Normal Accounts" + "\n" +
	 						"b) Deny Normal Accounts");
	 				String d2b = s.nextLine();
	 				if (d2b.equals("a")) {
	 					//call approve account
	 					System.out.println("Enter the number of the account you want to approve from the list above.");
	 					int n = Integer.parseInt(s.nextLine());
	 					Employee e = new Employee();
	 					ArrayList<Application> newApproved = new ArrayList<Application>();
	 					newApproved = e.approveApplication(appList, n);
	 					//System.out.println("Account approved!");
	 					writeApps(appList);
	 					if(!newApproved.isEmpty()) {
	 						Customer newUser = new Customer(newApproved.get(0).getF1(), newApproved.get(0).getL1(), newApproved.get(0).getU1(), newApproved.get(0).getP1(), 10);
		 					custList.add(newUser);
		 					writefile(custList);
		 					//System.out.println(appList);
	 					}
	 					
	 				} else if(d2b.equals("b")){
	 					//call deny method
	 					System.out.println("Enter the number of the account you want to deny from the list above.");
	 					int n = Integer.parseInt(s.nextLine());
	 					Employee e = new Employee();
	 					ArrayList<Application> newDenied = new ArrayList<Application>();
	 					newDenied = e.denyApplication(appList, n);
	 					writeApps(appList);
	 					//System.out.println(appList);
	 					//System.out.println("Account denied!");
	 				} 
	 			} else if (e1.equals("c")) {
	 				System.out.println("List of Joint Accounts to be Approved:");
					for(int i=0; i<JappList.size(); i++) {
						System.out.println(i+1 + ": " + JappList.get(i));
					}
					System.out.print('\n');
	 				System.out.println("Would you like to:"+ "\n" + "a) Approve Joint Accounts" + "\n" +
	 						"b) Deny Joint Accounts");
	 				String d2b = s.nextLine();
	 				if (d2b.equals("a")) {
	 					//call approve account
	 					System.out.println("Enter the number of the account you want to approve from the list above.");
	 					int n = Integer.parseInt(s.nextLine());
	 					Employee e = new Employee();
	 					ArrayList<JointApp> newApproved = new ArrayList<JointApp>();
	 					newApproved = e.approveJointApplication(JappList, n);
	 					//System.out.println("Account approved!");
	 					writeJA(JappList);
	 					Customer newUser = new Customer(newApproved.get(0).getfl1(), newApproved.get(0).getfl2(), newApproved.get(0).getUname(), newApproved.get(0).getPW(), 10);
	 					custList.add(newUser);
	 					writefile(custList);
	 					//System.out.println(JappList);
	 					
	 				} else if(d2b.equals("b")){
	 					//call deny method
	 					System.out.println("Enter the number of the account you want to deny from the list above.");
	 					int n = Integer.parseInt(s.nextLine());
	 					Employee e = new Employee();
	 					ArrayList<JointApp> newDenied = new ArrayList<JointApp>();
	 					newDenied = e.denyJointApplication(JappList, n);
	 					writeJA(JappList);
	 					//System.out.println(appList);
	 					//System.out.println("Account denied!");
	 				} 
	 			} else {
	 				System.out.println("Invalid input, try again.");
	 			}
	 			sys();
	 		} else {
	 			System.out.println("You are not Big Mama!!!");
	 			//sys();
	 		}
	 		sys();
		}
		
 // Bank Admin branch 3	
		
		else if(p.equals("c")) {
			System.out.println("Login in to account" + "\n" + "What is your Username?");
			String bu = s.nextLine();
			System.out.println("What is your Password?");
			String bp = s.nextLine(); 
			
			if (bu.equals(BAun) && bp.equals(BApw)) {
			
				System.out.println("a) View Accounts" + "\n" + "b) Edits Accounts" + "\n");
				String d1 = s.nextLine();
				if(d1.equals("a")) {
					for (int x=0; x < custList.size(); x++){
						System.out.println(custList.get(x) + "\n");
						sys();
					}
					for(int i=0; i<custList.size(); i++) {
						System.out.println(i+1 + ": " + custList.get(i));
					}
				} else if(d1.contentEquals("b")){
					System.out.println("Would you like to:"+ "\n" + "a) Approve/Deny Normal Accounts" + "\n" +
									"b) Transaction" + "\n" +"c) Delete Accounts" + "\n" + "d) Approve/Deny Joint Accounts");
					String d2 = s.nextLine();
					if(d2.equals("a")) {
						System.out.println("List of Normal Accounts to be Approved:");
		 				for(int i=0; i<appList.size(); i++) {
							System.out.println(i+1 + ": " + appList.get(i));
						}
						System.out.print('\n');
						System.out.println("Would you like to:"+ "\n" + "a) Approve Account" + "\n" +
							"b) Deny Accounts");
						String d2a = s.nextLine();
						if (d2a.equals("a")) {
							//call approve account
							System.out.println("Enter the number of the account you want to approve from the list above.");
		 					int n = Integer.parseInt(s.nextLine());
		 					Employee e = new Employee();
		 					ArrayList<Application> newApproved = new ArrayList<Application>();
		 					newApproved = e.approveApplication(appList, n);
		 					//System.out.println("Account approved!");
		 					writeApps(appList);
		 					if(!newApproved.isEmpty()) {
		 						Customer newUser = new Customer(newApproved.get(0).getF1(), newApproved.get(0).getL1(), newApproved.get(0).getU1(), newApproved.get(0).getP1(), 10);
			 					custList.add(newUser);
			 					writefile(custList);
			 					//System.out.println(appList);
		 					}
						} else if(d2a.equals("b")){
							System.out.println("Enter the number of the account you want to deny from the list above.");
		 					int n = Integer.parseInt(s.nextLine());
		 					Bankadmin ba = new Bankadmin();
		 					ArrayList<Application> newDenied = new ArrayList<Application>();
		 					newDenied = ba.denyApplication(appList, n);
		 					writeApps(appList);
		 					//System.out.println(appList);
		 					//System.out.println("Account denied!");
							//System.out.println("Account denied!");
						}
						sys();
					} else if (d2.equals("b")) {
						for (int x=0; x < custList.size(); x++){
							System.out.println(custList.get(x) + "\n");
						} 
						System.out.println("Input Username of account you would like to make a transaction for.");
						String ua = s.nextLine();
						for (int n = 0; n < custList.size(); n++) {
							if (custList.get(n).getUN().equals(ua)) {
								System.out.println("Would you like to:" + "\n" + "a) Withdraw" + "\n" + "b) Deposit" 
										+"\n"+ "c) Transfer");
								String d3a = s.nextLine(); 
								if(d3a.contentEquals("a")) {
									//Withdraw method
									int $ = custList.get(n).get$(); 
									System.out.println("How much would you like to withdraw?"); 
									int less$ = Integer.parseInt(s.nextLine()); 
									int newF = $ - less$;
									custList.get(n).setFunds(newF);
									System.out.println("Remaining Balance: $"+custList.get(n).get$());
									writefile(custList);
									sys();
								} else if (d3a.equals("b")) {
									//Deposit method
									int $ = custList.get(n).get$();
									System.out.println("How much would you like to Deposit?"); 
									int plus$ = Integer.parseInt(s.nextLine()); 
									int newF = $ + plus$;
									custList.get(n).setFunds(newF);
									System.out.println("New Balance: $"+custList.get(n).get$());
									writefile(custList);
									sys();
								} else if (d3a.equals("c")){
									//call transfer method
									System.out.println("Enter Account Username that you will be transferring money to: ");
									String h = s.nextLine();
									for (int t = 0; t < custList.size(); t++ ) {
										if (custList.get(t).getUN().equals(h) && n!= t ) {
											System.out.println("Transfer has begun\n" + "How much money would you like to Transfer?");
											int m$ = Integer.parseInt(s.nextLine());
											int newF = custList.get(n).get$() - m$;
											custList.get(n).setFunds(newF);
											int nf = custList.get(t).get$() + m$;
											custList.get(t).setFunds(nf);
											System.out.println("New Balance for 1st Account: $"+custList.get(n).get$() + "\n"
															+	"New Balance for 2nd Account: $"+custList.get(t).get$());
												
										}
									}
									writefile(custList);
									sys();
								}
							}
						}
						
					} else if(d2.equals("c")) {
						System.out.println("List of Customer Accounts:");
						for(int i=0; i<custList.size(); i++) {
							System.out.println(i+1 + ": " + custList.get(i));
						}
						System.out.println("What account would you like to delete?");
						int n = Integer.parseInt(s.nextLine());
	 					Bankadmin ba = new Bankadmin();
	 					ArrayList<Customer> newDeleted = new ArrayList<Customer>();
	 					newDeleted = ba.cancelAccount(custList, n);
	 					writefile(custList);
	 					//System.out.println(appList);
	 					//System.out.println("Account denied!");
						//System.out.println("Account denied!");

						
						//System.out.println("Account has been cancelled");
					} else if(d2.equals("d")) {
						System.out.println("List of Joint Accounts to be Approved:");
						for(int i=0; i<JappList.size(); i++) {
							System.out.println(i+1 + ": " + JappList.get(i));
						}
						System.out.print('\n');
		 				System.out.println("Would you like to:"+ "\n" + "a) Approve Joint Accounts" + "\n" +
		 						"b) Deny Joint Accounts");
		 				String d2b = s.nextLine();
		 				if (d2b.equals("a")) {
		 					//call approve account
		 					System.out.println("Enter the number of the account you want to approve from the list above.");
		 					int n = Integer.parseInt(s.nextLine());
		 					Bankadmin ba = new Bankadmin();
		 					ArrayList<JointApp> newApproved = new ArrayList<JointApp>();
		 					newApproved = ba.approveJointApplication(JappList, n);
		 					//System.out.println("Account approved!");
		 					writeJA(JappList);
		 					Customer newUser = new Customer(newApproved.get(0).getfl1(), newApproved.get(0).getfl2(), newApproved.get(0).getUname(), newApproved.get(0).getPW(), 10);
		 					custList.add(newUser);
		 					writefile(custList);
		 					//System.out.println(JappList);
		 					
		 				} else if(d2b.equals("b")){
		 					//call deny method
		 					System.out.println("Enter the number of the account you want to deny from the list above.");
		 					int n = Integer.parseInt(s.nextLine());
		 					Bankadmin ba = new Bankadmin();
		 					ArrayList<JointApp> newDenied = new ArrayList<JointApp>();
		 					newDenied = ba.denyJointApplication(JappList, n);
		 					writeJA(JappList);
		 					//System.out.println(appList);
		 					//System.out.println("Account denied!");
		 				} 
					}
					sys();
				}	
			} else {
				System.out.println("You are not Big Daddy!");
			}
			sys();
		}
		s.close();
	}

	
	Customer findCusty(String checkPwd) {
		for(Customer customer : custList) {
			if (customer.getPW().equals(checkPwd)){
				return customer;
			}
		}
		return null;
	}
	
//Serialization Methods	
	
	public void writefile(ArrayList<Customer> custys) {
		try {
        	FileOutputStream fos = new FileOutputStream("CustomerData.txt");
        	ObjectOutputStream oos = new ObjectOutputStream(fos);
        	oos.writeObject(custys);
        	System.out.println();
        	oos.close();
        	fos.close();
    	} 
    	catch (IOException ioe) {
        	ioe.printStackTrace();
    	}
	}
	
	public void writeApps(ArrayList<Application> apply) {
		try {
	    	FileOutputStream fos = new FileOutputStream("Applications.txt");
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	oos.writeObject(apply);
	    	System.out.println();
	    	oos.close();
	    	fos.close();
		} 
		catch (IOException ioe) {
	    	ioe.printStackTrace();
		}
	}
	
	public void writeJA(ArrayList<JointApp> Japps) {
		try {
			FileOutputStream fos = new FileOutputStream("Joint.txt");
	    	ObjectOutputStream oos = new ObjectOutputStream(fos);
	    	oos.writeObject(Japps);
	    	System.out.println();
	    	oos.close();
	    	fos.close();
		} 
		catch (IOException ioe) {
	    	ioe.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readfile() {
		try {	
			ArrayList<Customer> deSerializedCustys;
			FileInputStream fis = new FileInputStream(new File ("CustomerData.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
        	deSerializedCustys = ((ArrayList<Customer>)ois.readObject());
			custList = deSerializedCustys;
			ois.close();
			fis.close();
    	} 
    	catch (IOException ioe) {
    		System.out.println("Customer List is empty");
    		//ioe.printStackTrace();
        	//return;
    	} 
		catch(ClassNotFoundException c) {
        	System.out.println("Class not found");
        	c.printStackTrace();
        	//return;
    	}
	}
	
	@SuppressWarnings("unchecked")
	public void readApps() {
		try {	
			ArrayList<Application> deSerializedApps;
			FileInputStream fis = new FileInputStream(new File ("Applications.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
        	deSerializedApps = ((ArrayList<Application>)ois.readObject());
			appList = deSerializedApps;
			ois.close();
			fis.close();
    	} 
    	catch (IOException ioe) {
    		System.out.println("Application List is empty");
    		//ioe.printStackTrace();
        	//return;
    	} 
		catch(ClassNotFoundException c) {
        	System.out.println("Class not found");
        	c.printStackTrace();
        	//return;
    	}
		//return appList;
	}
		
	@SuppressWarnings("unchecked")
	public void readJA() {
		ArrayList<JointApp> deSerializedJA = new ArrayList<JointApp>();
		try {	
			FileInputStream fis = new FileInputStream(new File ("Joint.txt"));
			ObjectInputStream ois = new ObjectInputStream(fis);
	        deSerializedJA = ((ArrayList<JointApp>)ois.readObject());
			JappList = deSerializedJA;
			ois.close();
			fis.close();
			//return JappList;
	    } 
	    catch (IOException ioe) {
	        //ioe.printStackTrace();
	    	System.out.println("Joint Application List is empty");
	    	//return;
	    } 
		catch(ClassNotFoundException c) {
	        	System.out.println("Class not found");
	        	c.printStackTrace();
	        	//return;
	    }
		//return deSerializedJA;
	}
}