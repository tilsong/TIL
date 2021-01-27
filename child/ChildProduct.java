package child;

public class ChildProduct<T,M,C> extends Product<T,M>{
	private C company;
	
	public void setCompany(C company) {this.company=company;}
	public C getCompany() {return this.company;}
}
