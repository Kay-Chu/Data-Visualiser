
public class TestData {
	public static void main(String[] args0) {
		String title[] = {"country_or_erea","year","commodity","flow","trade_usd","weight_kg","quantity"};
		int titlew[] = {140,60,200,60,40,40,40};
		String data[][] = new String[10][7];
		for(int i = 0; i < data.length; i++) {
			for(int j = 0; j < data[i].length; j++) {
				data[i][j] = "Hahaha";
			}
		}
		//DataPage dp = new DataPage(1,title,titlew,data,600,500,1200,600);
	}
}
