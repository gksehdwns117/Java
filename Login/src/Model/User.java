package Model;

import java.util.Scanner;

public class User {
	private String name;
	private String id;
	private String pw;
	private String email;
	private static final Scanner SC=new Scanner(System.in);
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString() {
		return "User [name=" + name + ", id=" + id + ", pw=" + pw + ", email=" + email + "]";
	}
}
