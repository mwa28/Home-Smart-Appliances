import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.*;

public class SkillsServer {
	private ArrayList<Integer> customers = new ArrayList<>();
	private appliance Fridge = new appliance().configureFromFile("C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\Fridge.txt");
	private appliance Oven = new appliance().configureFromFile("C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\Oven.txt");
	private appliance Gas = new appliance().configureFromFile("C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\Gas.txt");
	private ServerSocket serverSocket;

	private SkillsServer() throws ClassNotFoundException, FileNotFoundException {
        Scanner reader = new Scanner(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
        String temp;
        temp = reader.next();
        int n = 100;
        for (int i = 0; i < n; i++)
            customers.add(i, null);
        while (reader.hasNext())
        {
            try
            {
                customers.add(n,Integer.parseInt(temp));
                n++;
                if (reader.hasNext())
                    temp = reader.next();
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        customers.add(n,Integer.parseInt(temp));
	}
	private void acceptConnections() {
		try {
			int port = 1997;
			serverSocket = new	 ServerSocket(port);
		} catch (IOException e) {
			System.err.println("ServerSocket instantiation failure");
			e.printStackTrace();
			System.exit(0);
		}
		// Entering the infinite loop
		while (true) {
			try {
				// wait for a TCP handshake initialization (arrival of a "SYN"
				// packet)
				Socket newConnection = serverSocket.accept();
				System.out.println("accepted connection");
				// Now, pass the connection socket created above to a thread and
				// run it in it
				// First create the thread and pass the connection socket to it
				// This is a non-blocking function: constructor of the class
				// ServerThread
				ServerThread st = new ServerThread(newConnection);
				// Then, start the thread, and go back to waiting for another
				// TCP connection
				// This also is not blocking
				new Thread(st).start();
			} catch (IOException ioe) {
				System.err.println("server accept failed");
			}
		}
	}

	public static void main(String[] args) throws NullPointerException, ClassNotFoundException, FileNotFoundException {
		SkillsServer server= new SkillsServer();
        // call this function, which will start it all...
        server.acceptConnections();
    }

	// Internal class
	class ServerThread implements Runnable
    {
		private Socket socket;
		private DataInputStream datain;
		private DataOutputStream dataout;

		ServerThread(Socket socket) {
			// Inside the constructor: store the passed object in the data
			// member
			this.socket = socket;
		}

		// This is where you place the code you want to run in a thread
		// Every instance of a ServerThread will handle one client (TCP
		// connection)
		public void run() {
			try {
				// Input and output streams, obtained from the member socket
				// object
				datain = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				dataout = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

			} catch (IOException e) {
				return;
			}
			// byte[] ba = new byte[9];
			boolean conversationActive = true;

			while (conversationActive) {

                String skill;
				try {
					int length = datain.readInt();
					byte[] ba = new byte[length];
					datain.readFully(ba, 0, ba.length);
					skill = new String(ba);
					// Exit when a "Q" is received from a client
					if ((skill.length() == 1) && (skill.toUpperCase().charAt(0) == 'Q'))
						conversationActive = false;
					else {
						String customerID;
						if(skill.startsWith("PAY"))
                        {
                            customerID=skill.substring(4,skill.length());
                            customers.set(Integer.parseInt(customerID),0);
                            String temp = "Your payment was successful";
                            PrintStream fileStream = new PrintStream(new File(
                                    "C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
                            fileStream.print("");
                            for (int j = 100; j <= customers.size(); j++) {
                                try{
                                    //if(!customers.get(j).equals(null))
                                        fileStream.println(customers.get(j));
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            dataout.write(temp.getBytes(), 0, temp.length());
                            dataout.write("\n".getBytes(), 0, 1);
                            dataout.flush();
                        }
						if (skill.startsWith("CHECK")) // Command: CHECK 100 ->
														// Account Summary for
														// 897
						{
							customerID = skill.substring(6, skill.length());
							Integer temp2 = customers.get(Integer.parseInt(customerID));
							String temp = "Your current due balance is : " + temp2 + "$";
							dataout.write(temp.getBytes(), 0, temp.length());
							dataout.write("\n".getBytes(), 0, 1);
							dataout.flush();
						}
						if (skill.startsWith("MEMORDER")) // Command: MEMORDER
															// 897 App 5 Wat 5
															// Ban 6
						{
							Scanner reader = new Scanner(skill);
							int ID = Integer.parseInt(skill.substring(9, 12));
							String request = reader.findInLine("App");
							if (request == null)
								request = reader.findInLine("Ban");
							if (request == null)
								request = reader.findInLine("Bvg");
							if (request == null)
								request = reader.findInLine("Wat");
							if (request == null) {
								String empty = "No change";
								dataout.write(empty.getBytes(), 0, empty.length());
								dataout.write("\n".getBytes(), 0, 1);
								dataout.flush();
							} else {
								Integer count = reader.nextInt();
								count = count * Fridge.stockCost.get(Fridge.stockName.indexOf(request));
								count = count + customers.get(ID);
								customers.set(ID, count);

								PrintStream fileStream = new PrintStream(new File(
										"C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
								fileStream.print("");
								for (int j = 100; j <= customers.size(); j++) {
									try{
									//if(!customers.get(j).equals(null))
										fileStream.println(customers.get(j));
									}
									catch (Exception e) {
                                        e.printStackTrace();
									}
								}

								request = reader.findInLine("Ban");
								if (request == null)
									request = reader.findInLine("Bvg");
								if (request == null)
									request = reader.findInLine("Wat");
								if (request == null) {
									customers.set(ID, count);
									dataout.write(customers.get(ID).toString().getBytes(), 0,
											customers.get(ID).toString().length());
									dataout.write("\n".getBytes(), 0, 1);
									dataout.flush();
								} else {
									count = reader.nextInt();
									count = count * Fridge.stockCost.get(Fridge.stockName.indexOf(request));
									count = count + customers.get(ID);
									customers.set(ID, count);
									fileStream = new PrintStream(new File(
											"C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
									fileStream.print("");
									for (int j = 100; j <= customers.size(); j++) {
										//if(!customers.get(j).equals(null))
										fileStream.println(customers.get(j));
									}
									
									request = reader.findInLine("Bvg");
									if (request == null)
										request = reader.findInLine("Wat");
									if (request == null) {
										customers.set(ID, count);
										dataout.write(customers.get(ID).toString().getBytes(), 0,
												customers.get(ID).toString().length());
										dataout.write("\n".getBytes(), 0, 1);
										dataout.flush();
									} else {
										count = reader.nextInt();
										count = count * Fridge.stockCost.get(Fridge.stockName.indexOf(request));
										count = count + customers.get(ID);
										customers.set(ID, count);
                                        fileStream = new PrintStream(new File(
												"C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
										fileStream.print("");
										for (int j = 100; j <= customers.size(); j++) {
											//if(!customers.get(j).equals(null))
											fileStream.println(customers.get(j));
										}
										
										request = reader.findInLine("Wat");
										if (request == null) {
											customers.set(ID, count);
											dataout.write(customers.get(ID).toString().getBytes(), 0,
													customers.get(ID).toString().length());
											dataout.write("\n".getBytes(), 0, 1);
											dataout.flush();
										} else {
											count = reader.nextInt();
											count = count * Fridge.stockCost.get(Fridge.stockName.indexOf(request));
											count = count + customers.get(ID);
											customers.set(ID, count);
											fileStream = new PrintStream(new File(
													"C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
											fileStream.print("");
											for (int j = 100; j <= customers.size(); j++) {
												//if(!customers.get(j).equals(null))
												fileStream.println(customers.get(j));
											}
											//
											dataout.write(customers.get(ID).toString().getBytes(), 0,
													customers.get(ID).toString().length());
											dataout.write("\n".getBytes(), 0, 1);
											dataout.flush();
										}
									}
								}
							}
						}
						if (skill.startsWith("FIX")) // Command: FIX 897 Fdg
														// CompressorRepair
						{
							customerID = skill.substring(4, 7);
							String AppName = skill.substring(8, 11);
							Integer ID = Integer.parseInt(customerID);
							if (AppName.matches("Fdg")) {
								String FixName = skill.substring(12, skill.length());
								Integer temp = Fridge.FixCostList.get(Fridge.FixList.indexOf(FixName))
										+ customers.get(ID);
								customers.set(ID, temp);

							}
							if (AppName.matches("Ovn")) {
								String FixName = skill.substring(12, skill.length());
								Integer temp = Oven.FixCostList.get(Oven.FixList.indexOf(FixName)) + customers.get(ID);
								customers.set(ID, temp);
							}
							if (AppName.matches("Gas")) {
								String FixName = skill.substring(12, skill.length());
								Integer temp = customers.get(ID) + Gas.FixCostList.get(Gas.FixList.indexOf(FixName));
								customers.set(ID, temp);
							}
							PrintStream fileStream = new PrintStream(new File(
									"C:\\Users\\mOh\\Documents\\IdeaProjects\\Home-Smart-Appliances\\src\\StoreCustomers.txt"));
							fileStream.print("");
							for (int j = 100; j <= customers.size(); j++) {
								try{
								//if(!customers.get(j).equals(null))
								fileStream.println(customers.get(j));
								}
								catch (Exception e) {
                                    e.printStackTrace();
								}
							}
							
							dataout.write(customers.get(ID).toString().getBytes(), 0,
									customers.get(ID).toString().length());
							dataout.write("\n".getBytes(), 0, 1);
							dataout.flush();
						}
					}
				} catch (IOException ioe) {
					conversationActive = false;
				}
				try {
					System.out.println("closing socket");
					datain.close();
					dataout.close();
					// When the server receives a "Q", we arrive here
					socket.close();
					 

				} catch (IOException e) {
					System.out.print('.');
				}
			}
		}
	}
}
