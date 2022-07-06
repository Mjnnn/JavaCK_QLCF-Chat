package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Account {
   String user;
   String pass;
   String name;
   DateFormat date = new SimpleDateFormat("dd/MM/yyyy"); 
   String address;
   String gioitinh;
public Account() {
	super();
	this.user = "";
	this.pass = "";
	this.name = "";
	this.date = null;
	this.address = "";
	this.gioitinh = "";
}

public Account(String user, String pass, String name, DateFormat date, String address, String gioitinh) {
	super();
	this.user = user;
	this.pass = pass;
	this.name = name;
	this.date = date;
	this.address = address;
	this.gioitinh = gioitinh;
}

public String getUser() {
	return user;
}

public void setUser(String user) {
	this.user = user;
}

public String getPass() {
	return pass;
}

public void setPass(String pass) {
	this.pass = pass;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public DateFormat getDate() {
	return date;
}

public void setDate(DateFormat date) {
	this.date = date;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}

public String getGioitinh() {
	return gioitinh;
}

public void setGioitinh(String gioitinh) {
	this.gioitinh = gioitinh;
}

   
}
