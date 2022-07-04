package com.Item.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;




@Entity

public class Item {
	
public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
public Item(long id, String image, int price, String title, String description) {
		super();
		this.id = id;
		this.image = image;
		this.price = price;
		this.title = title;
		this.description = description;
	}

public Item(String image,int price,String title, String description) {
	this.image=image;
	this.price=price;
	this.title=title;
	this.description=description;
}

@Id
@GeneratedValue

private long id;
private String image;
private int price;
private String title;
private String description;


public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@Override
public String toString() {
	return "Item [id=" + id + ", image=" + image + ", price=" + price + ", title=" + title + ", description="
			+ description + "]";
}
}
