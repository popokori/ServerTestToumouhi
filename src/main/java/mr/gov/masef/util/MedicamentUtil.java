package mr.gov.masef.util;

import jakarta.persistence.Embeddable;

@Embeddable
public class MedicamentUtil {
	private String name;
	private int quantity;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public MedicamentUtil(String name, int quantity) {
		super();
		this.name = name;
		this.quantity = quantity;
	}
	public MedicamentUtil() {
		super();
	}
	


}