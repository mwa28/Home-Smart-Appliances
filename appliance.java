import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.lang.*;
//This class is used as a database
public class appliance
{
    public String name;
    public ArrayList<String> FixList=new ArrayList<>();
    public ArrayList<Integer> FixCostList=new ArrayList<>();
    public ArrayList<Integer> stockCost=new ArrayList<>();
    public ArrayList<Integer> stockCount=new ArrayList<>();
    public ArrayList<String>  stockName=new ArrayList<>();

    public void configureFromFile(String FILENAME) throws NullPointerException, FileNotFoundException {
        Scanner reader=new Scanner(new File(FILENAME));
        String temp;
        temp=reader.nextLine();
        name=temp;
        int result;
        temp=reader.nextLine();
        int n=0;
        while(!temp.equals("endFixName") )
        {
            FixList.add(n,temp);
            n++;
            temp=reader.nextLine();
        }
        temp=reader.nextLine();
        n=0;
        while(!temp.equals("endFixCost"))
        {

            result = Integer.parseInt(temp);
            FixCostList.add(n,result);
            n++;
            temp=reader.nextLine();
        }
        temp=reader.nextLine();
        n=0;
        while(!temp.equals("endStockName"))
        {
            stockName.add(n,temp);
            n++;
            temp=reader.nextLine();

        }
        temp=reader.nextLine();
        n=0;
        while(!temp.equals("endStockCount"))
        {
            result = Integer.parseInt(temp);
            stockCount.add(n,result);
            n++;
            temp=reader.nextLine();
            
        }
        temp=reader.nextLine();
        n=0;
        while(!temp.equals("endStockCost"))
        {
            result = Integer.parseInt(temp);
            stockCost.add(n,result);
            n++;
            temp=reader.nextLine();

        }
    }

}
