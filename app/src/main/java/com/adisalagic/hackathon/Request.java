package com.adisalagic.hackathon;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Request {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("about")
	@Expose
	private String about;
	@SerializedName("about_html")
	@Expose
	private String aboutHtml;
	@SerializedName("authors")
	@Expose
	private List<Author> authors = null;
	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("status_name")
	@Expose
	private String statusName;
	@SerializedName("date_created")
	@Expose
	private String dateCreated;
	@SerializedName("stars")
	@Expose
	private Integer stars;
	@SerializedName("parents")
	@Expose
	private List<Integer> parents = null;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getAboutHtml() {
		return aboutHtml;
	}

	public void setAboutHtml(String aboutHtml) {
		this.aboutHtml = aboutHtml;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getStars() {
		return stars;
	}

	public void setStars(Integer stars) {
		this.stars = stars;
	}

	public List<Integer> getParents() {
		return parents;
	}

	public void setParents(List<Integer> parents) {
		this.parents = parents;
	}

	public int getStatusAsInt() {
		switch (status) {
			case "COO":
				return Microservice.COO;
			case "IDV":
				return Microservice.IDV;
			case "IDE":
				return Microservice.IDE;
			case "DEB":
				return Microservice.DEB;
			default:
				return Microservice.ERR;
		}
	}

}