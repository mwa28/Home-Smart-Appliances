import java.lang.reflect.Array;
import java.util.*;
import java.lang.*;
import java.io.*;

public class customer {

	public ArrayList<String> accountSummary = new ArrayList<>();
	public Integer ID;
	public ArrayList<Integer> stockCount = new ArrayList<>();
	public ArrayList<Integer> stockThreshold = new ArrayList<>();
	public ArrayList<String> stockName = new ArrayList<>();
	public Integer TotalCost = 0;

	public void configureFromFile(String FILENAME) throws NullPointerException, FileNotFoundException {
		Scanner reader = new Scanner(new File(FILENAME));
		String temp;
		temp = reader.next();
		ID = Integer.parseInt(temp);
		temp = reader.next();
		while (!temp.equals("endStockCount")) {
			stockCount.add(Integer.parseInt(temp));
			temp = reader.next();
		}
		temp = reader.next();
		while (!temp.equals("endStockThreshold")) {

			stockThreshold.add(Integer.parseInt(temp));
			temp = reader.next();
		}
		temp = reader.next();
		while (!temp.equals("endStockName")) {
			stockName.add(temp);
			temp = reader.next();
		}
	}

	public void addFix(String FixName) {
		accountSummary.add(FixName);
	}

	public Boolean consumeStock(String app, Integer count) {
		count = stockCount.get(stockName.indexOf(app)) - count;
		stockCount.set(stockName.indexOf(app), count);
		return true;
	}

	public void addStock(String app, Integer count) {
        accountSummary.add(app +" "+ (count-stockCount.get(stockName.indexOf(app))));
		stockCount.set(stockName.indexOf(app), count);
	}
}
