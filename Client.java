import java.io.*;
import java.net.*;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Client {
	public static customer myProfile; 
	Client(){
	myProfile= new customer();
	myProfile.ID = 100;
	try {
		myProfile.configureFromFile(
				"C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt");
	} catch (NullPointerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void Order(String sentence) throws FileNotFoundException{
		if (sentence != null) {
			String modifiedSentence = null;
			String forUser = sentence.substring(12);
			//System.out.println("Here is your order:\n " + forUser + "\n Are you sure you want to place order?  Y/N");
			int answer = JOptionPane.showConfirmDialog(null , "Here is your order:\n " + forUser + "\n Are you sure you want to place order?","Confirmation" , 0);
			//BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			if (answer == JOptionPane.YES_OPTION) {
				System.out.println("Your order has been placed\n");
				Socket clientSocket = null;
				try {
					clientSocket = new Socket(InetAddress.getLocalHost().getHostAddress(), 1997);
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				DataOutputStream outToServer = null;
				try {
					outToServer = new DataOutputStream(clientSocket.getOutputStream());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				BufferedReader inFromServer = null;
				try {
					inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// sentence = inFromUser.readLine();
				try {
					outToServer.writeInt((sentence.length()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					outToServer.writeBytes(sentence + '\n');
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					modifiedSentence = inFromServer.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				if (sentence.startsWith("MEMORDER")) // MEMORDER 100 App 3 Wat 4
														// Bvg 6\n
				{
					Scanner reader = new Scanner(sentence);
					String request = reader.findInLine("App");
					if (request == null)
						request = reader.findInLine("Ban");
					if (request == null)
						request = reader.findInLine("Bvg");
					if (request == null)
						request = reader.findInLine("Wat");
					if (request == null) {
						String empty = "No change";
						System.out.println(empty);
					} else {
						Integer count = reader.nextInt();
						count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
						myProfile.addStock(request, count);

						//System.out.println("You now have "
							//	+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
						JOptionPane.showMessageDialog(null, "You now have "
								+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request,"Notification",2);
						request = reader.findInLine("Ban");
						if (request == null)
							request = reader.findInLine("Bvg");
						if (request == null)
							request = reader.findInLine("Wat");
						if (request == null) {
						//	System.out.println("Done");
						} else {
							count = reader.nextInt();
							count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
							myProfile.addStock(request, count);
							//System.out.println(
								//	"You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
									//		+ " " + request + '\n');
							JOptionPane.showMessageDialog(null, "You now have " + 
											myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request,"Notification",2);
							request = reader.findInLine("Bvg");
							if (request == null)
								request = reader.findInLine("Wat");
							if (request == null) {
								System.out.println("Done");
							} else {
								count = reader.nextInt();
								count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
								myProfile.addStock(request, count);
								//System.out.println(
									//	"You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
										//		+ " " + request + '\n');
								JOptionPane.showMessageDialog(null, "You now have " + 
								myProfile.stockCount.get(myProfile.stockName.indexOf(request))+ " " + request,"Notification",2);
								request = reader.findInLine("Wat");
								if (request != null) {
									count = reader.nextInt();
									count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
									myProfile.addStock(request, count);
									//System.out.println("You now have "
										//	+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " "
											//+ request + '\n');
									JOptionPane.showMessageDialog(null, "You now have " + 
											myProfile.stockCount.get(myProfile.stockName.indexOf(request))+ " " + request,"Notification",2);
								}
							}
						}
					}
					//myProfile.TotalCost += Integer.parseInt(modifiedSentence);
					//System.out.println("FROM SERVER: " + modifiedSentence);
					JOptionPane.showMessageDialog(null, "FROM SERVER: " + modifiedSentence, "Notification",2);
				}
				if (sentence.startsWith("CHECK")) {
					//System.out.println("FROM SERVER: " + modifiedSentence);
					JOptionPane.showMessageDialog(null, "FROM SERVER: " + modifiedSentence, "Notification",2);
				}
				if (sentence.startsWith("FIX")) // Command: FIX 897 Fdg
												// CompressorRepair
				{
					String FixName = sentence.substring(12, sentence.length());
					myProfile.addFix(FixName);
					//System.out.println("From Server: " + modifiedSentence);
					JOptionPane.showMessageDialog(null, "FROM SERVER: " + modifiedSentence, "Notification",2);
				}
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Scanner re = new Scanner(new File("C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt")); // Re-write
																													// into
																													// the
																													// file
																													// the
																													// StockCount
				String ret = re.next();
				String half;
				while (!ret.equals("endStockCount")) {
					ret = re.next();
				}
				half = ret + System.getProperty( "line.separator");
				while (re.hasNext()) {
					half += re.next() + System.getProperty( "line.separator");
				}
				PrintStream fileStream = new PrintStream(new File(
						"C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt"));
				fileStream.print("");
				fileStream.println(myProfile.ID);
				for (int j = 0; j < myProfile.stockCount.size(); j++) {

					fileStream.println(myProfile.stockCount.get(j));
				}
				fileStream.append(half);
			} else if (answer == JOptionPane.NO_OPTION) {
				Scanner re = new Scanner(new File(
						"C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt")); // Re-write
																													// into
																													// the
																													// file
																													// the
																													// StockCount
				String ret = re.next();
				String half;
				while (!ret.equals("endStockCount")) {
					ret = re.nextLine();
				}
				half = ret + System.getProperty( "line.separator");
				while (re.hasNext()) {
					half += re.nextLine() + System.getProperty( "line.separator");
				}
				PrintStream fileStream = new PrintStream(new File(
						"C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt"));
				fileStream.print("");
				fileStream.println(myProfile.ID);
				for (int j = 0; j < myProfile.stockCount.size(); j++) {

					fileStream.println(myProfile.stockCount.get(j));
				}
				fileStream.append(half);
				//System.out.println("Your order has been deleted\n");
				JOptionPane.showMessageDialog(null,"Your order has been deleted","Notification" ,2);
			} else {
				System.out.println("Invalid Choice\n");
			}
		}
		
	}
	
	public static String consume(String args)
	{
		String sentence = null;
		//args = new String[1];
BufferedReader UserAction = new BufferedReader(new InputStreamReader(System.in)); // consume
																							// app
																							// 10
																							// 12
																							// ban
		if (args.startsWith("consume")) {
			Scanner reader = new Scanner(args);
			String request = reader.findInLine("App");
			if (request == null)
				request = reader.findInLine("Ban");
			if (request == null)
				request = reader.findInLine("Bvg");
			if (request == null)
				request = reader.findInLine("Wat");
			if (request == null) {
				System.out.println("Invalid command");
			} else {
				Integer count = reader.nextInt();
				myProfile.consumeStock(request, count);
				//System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
					//	+ " " + request);
				JOptionPane.showMessageDialog(null,"You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
				+ " " + request ,"Notification" ,2);
				request = reader.findInLine("Ban");
				if (request == null)
					request = reader.findInLine("Bvg");
				if (request == null)
					request = reader.findInLine("Wat");
				if (request == null) {
					System.out.println("Done");
				} else {
					count = reader.nextInt();
					myProfile.consumeStock(request, count);
					//System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
						//	+ " " + request);
					JOptionPane.showMessageDialog(null,"You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request))
						+ " " + request ,"Notification" ,2);
					request = reader.findInLine("Bvg");
					if (request == null)
						request = reader.findInLine("Wat");
					if (request == null) {
						System.out.println("Done");
					} else {
						count = reader.nextInt();
						myProfile.consumeStock(request, count);
						//System.out.println("You now have "
							//	+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
						JOptionPane.showMessageDialog(null,"You now have "
								+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request ,"Notification" ,2);
						request = reader.findInLine("Wat");
						if (request != null) {
							count = reader.nextInt();
							count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
							myProfile.addStock(request, count);
							//System.out.println("You now have "
								//	+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
							JOptionPane.showMessageDialog(null,"You now have "
									+ myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request ,"Notification" ,2);
						}
					}
				}
			}
			Scanner re = null;
			try {
				re = new Scanner(
						new File("C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // Re-write
																														// into
																														// the
																														// file
																														// the
																														// StockCount
			String ret = re.next();
			String half;
			while (!ret.equals("endStockCount")) {
				ret = re.next();
			}
			half = ret + System.getProperty( "line.separator");
			while (re.hasNext()) {
				half += re.next() + System.getProperty( "line.separator");
			}
			PrintStream fileStream = null;
			try {
				fileStream = new PrintStream(
						new File("C:/Users/toshiba/Desktop/EECE 350 Project/Home-Smart-Appliances-Client/Customer.txt"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fileStream.print("");
			fileStream.println(myProfile.ID);
			for (int j = 0; j < myProfile.stockCount.size(); j++) {

				fileStream.println(myProfile.stockCount.get(j));
			}
			fileStream.append(half);
			String first, second, third, fourth;
			first = null;
			second = null;
			third = null;
			fourth = null;
			Integer toGetFirst, toGetSecond, toGetThird, toGetFourth;
			toGetFirst = null;
			toGetSecond = null;
			toGetThird = null;
			toGetFourth = null;
			// Checking for below Threshold items to place an order
			if (myProfile.stockCount.get(myProfile.stockName.indexOf("App")) < myProfile.stockThreshold
					.get(myProfile.stockName.indexOf("App"))) // Stock <
																// Threshold
			{
				toGetFirst = myProfile.stockThreshold.get(myProfile.stockName.indexOf("App"))
						- myProfile.stockCount.get(myProfile.stockName.indexOf("App")); // Difference
																						// needed
																						// to
																						// reach
																						// Threshold
				first = myProfile.stockName.get(myProfile.stockName.indexOf("App")) + " " + toGetFirst.toString(); // App
																													// 5
			}
			if (myProfile.stockCount.get(myProfile.stockName.indexOf("Ban")) < myProfile.stockThreshold
					.get(myProfile.stockName.indexOf("Ban"))) // Stock <
																// Threshold
			{
				toGetSecond = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Ban"))
						- myProfile.stockCount.get(myProfile.stockName.indexOf("Ban")); // Difference
																						// needed
																						// to
																						// reach
																						// Threshold
				second = myProfile.stockName.get(myProfile.stockName.indexOf("Ban")) + " " + toGetSecond.toString(); // Ban
																														// 5
			}
			if (myProfile.stockCount.get(myProfile.stockName.indexOf("Wat")) < myProfile.stockThreshold
					.get(myProfile.stockName.indexOf("Wat"))) // Stock <
																// Threshold
			{
				toGetThird = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Wat"))
						- myProfile.stockCount.get(myProfile.stockName.indexOf("Wat")); // Difference
																						// needed
																						// to
																						// reach
																						// Threshold
				third = myProfile.stockName.get(myProfile.stockName.indexOf("Wat")) + " " + toGetThird.toString(); // Wat
																													// 5
			}
			if (myProfile.stockCount.get(myProfile.stockName.indexOf("Bvg")) < myProfile.stockThreshold
					.get(myProfile.stockName.indexOf("Bvg"))) // Stock <
																// Threshold
			{
				toGetFourth = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Bvg"))
						- myProfile.stockCount.get(myProfile.stockName.indexOf("Bvg")); // Difference
																						// needed
																						// to
																						// reach
																						// Threshold
				fourth = myProfile.stockName.get(myProfile.stockName.indexOf("Bvg")) + " " + toGetFourth.toString(); // Bvg
																														// 5
			}
			if (first != null) {
				sentence = "MEMORDER " + myProfile.ID.toString() + " " + first;
				if (second != null)
					sentence = sentence + " " + second;
				if (third != null)
					sentence = sentence + " " + third;
				if (fourth != null)
					sentence = sentence + " " + fourth;
			} else if (second != null) {
				sentence = "MEMORDER " + myProfile.ID.toString() + " " + second;
				if (third != null)
					sentence = sentence + " " + third;
				if (fourth != null)
					sentence = sentence + " " + fourth;
			} else if (third != null) {
				sentence = "MEMORDER " + myProfile.ID.toString() + " " + third;
				if (fourth != null)
					sentence = sentence + " " + fourth;
			}
		} // end if for consume
		return sentence;
	}
	public static void main(String[] args) throws Exception {
		//args = new String [1];
		//String temp = UserAction.readLine();
		Client p = new Client();
		p.Order("MEMORDER 100 Ban 10");
		
	}
}
