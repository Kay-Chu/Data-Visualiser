import java.io.Serializable;

public class UserRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6000293301717103824L;
	public static final String[] FIELDS = {"country_or_rea","year","commodity","flow","trade_usd","weight_kg","quantity"};
	public boolean field[];
	public int trade_usd;
	public int trade_equality;
	public int year;
	public String country;
	public int flow;
	public boolean dis_Connect;
	public int page;
	public UserRequest(boolean[] field,int trade_usd,int trade_equality,int year,String country,int flow,int page,boolean dis_Connect) {
		this.field = field;
		this.trade_usd = trade_usd;
		this.trade_equality = trade_equality;
		this.year = year;
		this.country = country;
		this.flow = flow;
		this.page = page;
		this.dis_Connect = dis_Connect;
	}
	
	public UserRequest disConnect(boolean dis_Connect) {
		return new UserRequest(this.field,this.trade_usd,this.trade_equality,this.year,this.country,this.flow,this.page,dis_Connect);
	}
	
	public UserRequest setPage(int x) {
		if(x > 0 && x <= 50) {
			this.page = x;
		}
		return new UserRequest(this.field,this.trade_usd,this.trade_equality,this.year,this.country,this.flow,this.page,this.dis_Connect);
	}
	
	public UserRequest nextPage() {
		if(this.page < 50) {
			this.page++;
		}
		return new UserRequest(this.field,this.trade_usd,this.trade_equality,this.year,this.country,this.flow,this.page,this.dis_Connect);
	}
	
	public UserRequest previousPage() {
		if(this.page > 1) {
			this.page--;
		}
		return new UserRequest(this.field,this.trade_usd,this.trade_equality,this.year,this.country,this.flow,this.page,this.dis_Connect);
	}
}
