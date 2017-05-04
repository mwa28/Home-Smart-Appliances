/**
 * Created by mOh on 29-Apr-17.
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class client {

    public static void main(String[] args) throws Exception {
        customer myProfile = new customer();
        String sentence = null;
        String modifiedSentence;
        myProfile.configureFromFile("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt");
        BufferedReader UserAction = new BufferedReader(new InputStreamReader(System.in));                                // consume app 10 12 ban
        String temp = UserAction.readLine();
        if (temp.startsWith("consume"))
        {
            Scanner reader = new Scanner(temp);
            String request = reader.findInLine("App");
            if (request == null)
                request = reader.findInLine("Ban");
            if (request == null)
                request = reader.findInLine("Bvg");
            if (request == null)
                request = reader.findInLine("Wat");
            if (request == null) {
                System.out.println("Invalid command");
            }
            else
            {
                Integer count = reader.nextInt();
                myProfile.consumeStock(request, count);
                System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
                request = reader.findInLine("Ban");
                if (request == null)
                    request = reader.findInLine("Bvg");
                if (request == null)
                    request = reader.findInLine("Wat");
                if (request == null) {
                    System.out.println("Done");
                }
                else
                {
                    count = reader.nextInt();
                    myProfile.consumeStock(request, count);
                    System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
                    request = reader.findInLine("Bvg");
                    if (request == null)
                        request = reader.findInLine("Wat");
                    if (request == null) {
                        System.out.println("Done");
                    } else {
                        count = reader.nextInt();
                        myProfile.consumeStock(request, count);
                        System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
                        request = reader.findInLine("Wat");
                        if (request != null) {
                            count = reader.nextInt();
                            count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
                            myProfile.addStock(request, count);
                            System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
                        }
                    }
                }
            }
            Scanner re=new Scanner(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));                            // Re-write into the file the StockCount
            String ret=re.nextLine();
            String half;
            while(!ret.equals("endStockCount"))
            {
                ret=re.nextLine();
            }
            half= ret+'\n';
            while(re.hasNext())
            {
                half+=re.nextLine() +"\n";
            }
            PrintStream fileStream = new PrintStream(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));
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
            //Checking for below Threshold items to place an order
            if (myProfile.stockCount.get(myProfile.stockName.indexOf("App"))
                    < myProfile.stockThreshold.get(myProfile.stockName.indexOf("App")))                                 //Stock < Threshold
            {
                toGetFirst = myProfile.stockThreshold.get(myProfile.stockName.indexOf("App"))
                        - myProfile.stockCount.get(myProfile.stockName.indexOf("App"));                                 // Difference needed to reach Threshold
                first = myProfile.stockName.get(myProfile.stockName.indexOf("App")) + " " + toGetFirst.toString();        // App 5
            }
            if (myProfile.stockCount.get(myProfile.stockName.indexOf("Ban"))
                    < myProfile.stockThreshold.get(myProfile.stockName.indexOf("Ban")))                                 //Stock < Threshold
            {
                toGetSecond = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Ban"))
                        - myProfile.stockCount.get(myProfile.stockName.indexOf("Ban"));                                 // Difference needed to reach Threshold
                second = myProfile.stockName.get(myProfile.stockName.indexOf("Ban")) + " " + toGetSecond.toString();      // Ban 5
            }
            if (myProfile.stockCount.get(myProfile.stockName.indexOf("Wat"))
                    < myProfile.stockThreshold.get(myProfile.stockName.indexOf("Wat")))                                 //Stock < Threshold
            {
                toGetThird = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Wat"))
                        - myProfile.stockCount.get(myProfile.stockName.indexOf("Wat"));                                 // Difference needed to reach Threshold
                third = myProfile.stockName.get(myProfile.stockName.indexOf("Wat")) + " " + toGetThird.toString();        // Wat 5
            }
            if (myProfile.stockCount.get(myProfile.stockName.indexOf("Bvg"))
                    < myProfile.stockThreshold.get(myProfile.stockName.indexOf("Bvg")))                                 //Stock < Threshold
            {
                toGetFourth = myProfile.stockThreshold.get(myProfile.stockName.indexOf("Bvg"))
                        - myProfile.stockCount.get(myProfile.stockName.indexOf("Bvg"));                                 // Difference needed to reach Threshold
                fourth = myProfile.stockName.get(myProfile.stockName.indexOf("Bvg")) + " " + toGetFourth.toString();      // Bvg 5
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
        }
        if (sentence != null) {
            String forUser = sentence.substring(12);
            System.out.println("Here is your order:\n " + forUser + "\n Are you sure you want to place order?  Y/N");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String fromUser=inFromUser.readLine();
            if (fromUser.equals("Y"))
            {
                System.out.println("Your order has been placed\n");
                Socket clientSocket = new Socket("192.168.1.64", 1997);

                DataOutputStream outToServer =
                        new DataOutputStream(clientSocket.getOutputStream());

                BufferedReader inFromServer =
                        new BufferedReader(new
                                InputStreamReader(clientSocket.getInputStream()));

                //sentence = inFromUser.readLine();
                outToServer.writeInt((sentence.length()));
                outToServer.writeBytes(sentence + '\n');

                modifiedSentence = inFromServer.readLine();

                if (sentence.startsWith("MEMORDER")) //MEMORDER 100 App 3 Wat 4 Bvg 6\n
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

                        System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request);
                        request = reader.findInLine("Ban");
                        if (request == null)
                            request = reader.findInLine("Bvg");
                        if (request == null)
                            request = reader.findInLine("Wat");
                        if (request == null) {
                            System.out.println("Done");
                        } else {
                            count = reader.nextInt();
                            count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
                            myProfile.addStock(request, count);
                            System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request + '\n');
                            request = reader.findInLine("Bvg");
                            if (request == null)
                                request = reader.findInLine("Wat");
                            if (request == null) {
                                System.out.println("Done");
                            } else {
                                count = reader.nextInt();
                                count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
                                myProfile.addStock(request, count);
                                System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request + '\n');
                                request = reader.findInLine("Wat");
                                if (request != null) {
                                    count = reader.nextInt();
                                    count = count + myProfile.stockCount.get(myProfile.stockName.indexOf(request));
                                    myProfile.addStock(request, count);
                                    System.out.println("You now have " + myProfile.stockCount.get(myProfile.stockName.indexOf(request)) + " " + request + '\n');
                                }
                            }
                        }
                    }
                    myProfile.TotalCost += Integer.parseInt(modifiedSentence);
                    System.out.println("FROM SERVER: " + modifiedSentence);
                }
                if (sentence.startsWith("CHECK")) {
                    System.out.println("FROM SERVER: " + modifiedSentence);
                }
                if (sentence.startsWith("FIX"))                                                                                  // Command: FIX 897 Fdg CompressorRepair
                {
                    String FixName = sentence.substring(12, sentence.length());
                    myProfile.addFix(FixName);
                    System.out.println("From Server: " + modifiedSentence);
                }
                clientSocket.close();
                Scanner re=new Scanner(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));                            // Re-write into the file the StockCount
                String ret=re.nextLine();
                String half;
                while(!ret.equals("endStockCount"))
                {
                    ret=re.nextLine();
                }
                half= ret+'\n';
                while(re.hasNext())
                {
                    half+=re.nextLine() +"\n";
                }
                PrintStream fileStream = new PrintStream(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));
                fileStream.print("");
                fileStream.println(myProfile.ID);
                for (int j = 0; j < myProfile.stockCount.size(); j++) {

                    fileStream.println(myProfile.stockCount.get(j));
                }
                fileStream.append(half);
            }
            else if (fromUser.equals("N"))
            {
                Scanner re=new Scanner(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));                            // Re-write into the file the StockCount
                String ret=re.nextLine();
                String half;
                while(!ret.equals("endStockCount"))
                {
                    ret=re.nextLine();
                }
                half= ret+'\n';
                while(re.hasNext())
                {
                    half+=re.nextLine() +"\n";
                }
                PrintStream fileStream = new PrintStream(new File("C:\\Users\\mOh\\Documents\\IdeaProjects\\Test\\Customer.txt"));
                fileStream.print("");
                fileStream.println(myProfile.ID);
                for (int j = 0; j < myProfile.stockCount.size(); j++) {

                    fileStream.println(myProfile.stockCount.get(j));
                }
                fileStream.append(half);
                System.out.println("Your order has been deleted\n");
            }
            else{ System.out.println("Invalid Choice\n");}
        }
    }
}
