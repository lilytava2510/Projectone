package com.revature.models;

import java.sql.Date;

public class Reimburse {

  private int reimburse_id;

  private double amount;
  private Date submission;
  private String start;
  private String end;
  private Date resolution;
  private String description;
  private int author;
  private int manager = 2;
  private int status;
  private int type;


  public Reimburse() {

  }

  public Reimburse(Date resolution, int manager, int status) {
    this.resolution = resolution;
    this.manager = manager;
    this.status = status;
  }

  public Reimburse(int reimburse_id, Date resolution, int manager, int status) {
    this.reimburse_id = reimburse_id;
    this.resolution = resolution;
    this.manager = manager;
    this.status = status;
  }

  public Reimburse(double amount, Date submission, String description, int author, int type) {
    this.amount=amount;
    this.submission = submission;
    this.description =description;
    this.author=author;
    this.type=type;
  }

  public Reimburse(int reimburse_id, double amount, Date submission, Date resolution, String description, int author, int manager, int status, int type) {
    this.amount=amount;
    this.reimburse_id = reimburse_id;
    this.submission = submission;
    this.description =description;
    this.author=author;
    this.manager=manager;
    this.status=status;
    this.type=type;
    this.resolution=resolution;
  }

  public Reimburse(double amount, Date submission, String description, int author, int status, int type) {
    this.amount = amount;
    this.submission = submission;
    this.description = description;
    this.author = author;
    this.status = status;
    this.type = type;
  }

  public int getReimburse_id() {
    return reimburse_id;
  }

  public void setReimburse_id(int reimburse_id) {
    this.reimburse_id = reimburse_id;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Date getSubmission() {
    return submission;
  }

  public void setSubmission(Date submission) {
    this.submission = submission;
  }

  public Date getResolution() {
    return resolution;
  }

  public void setResolution(Date resolution) {
    this.resolution = resolution;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getAuthor() {
    return author;
  }

  public void setAuthor(int author) {
    this.author = author;
  }

  public int getManager() {
    return manager;
  }

  public void setManager(int manager) {
    this.manager = manager;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public String getStart() {
    return start;
  }

  public void setStart(String start) {
    this.start = start;
  }

  public String getEnd() {
    return end;
  }

  public void setEnd(String end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return "reim{" +
            "Id='" + reimburse_id + '\'' +
            "amount='" + amount + '\'' +
            ", date='" + submission + '\'' +
            ", date='" + resolution + '\'' +
            ", des='" + description + '\'' +
            "au='" + author + '\'' +
            ", ma='" + manager + '\'' +
            ", st='" + status + '\'' +
            ", ty='" + type + '\'' +


            '}';
  }
}