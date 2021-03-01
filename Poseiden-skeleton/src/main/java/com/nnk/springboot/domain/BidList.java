package com.nnk.springboot.domain;

import org.springframework.beans.factory.annotation.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.sql.Timestamp;

/***
 * 
 * @author j.c.
 * POJO class Bidlist
 *
 */

@Entity
@Table(name = "bidlist")
public class BidList {
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer BidListId;
	
	@Column(name = "account")
	@NotBlank(message = "Account is mandatory")
	String account;
	
	@Column(name = "type")
	@NotBlank(message = "Type is mandatory")
	String type;
	
	@Column(name = "bidQuantity")
	@NotBlank(message = "Bid quantity is mandatory")
	Double bidQuantity;
	
	@Column(name = "askQuantity")
	Double askQuantity;
	
	@Column(name = "bid")
	Double bid;
	
	@Column(name = "ask")
	Double ask;
	
	@Column(name = "benchmark")
	String benchmark;
	
	@Column(name = "bidListDate")
	Timestamp bidListDate;
	
	@Column(name = "commentary")
	String commentary;
	
	@Column(name = "security")
	String security;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "trader")
	String trader;
	
	@Column(name = "book")
	String book;
	
	@Column(name = "creationName")
	String creationName;
	
	@Column(name = "creationDate")
	Timestamp creationDate;
	
	@Column(name = "revisionName")
	String revisionName;
	
	@Column(name = "revisionDate")
	Timestamp revisionDate;
	
	@Column(name = "dealName")
	String dealName;
	
	@Column(name = "dealType")
	String dealType;
	
	@Column(name = "sourceListId")
	String sourceListId;
	
	@Column(name = "side")
	String side;

	/***
	 * Constructor for test purposes
	 * @param account
	 * @param type
	 * @param bidQuantity
	 */
	public BidList(String account, String type, double bidQuantity) {
		this.account = account;
		this.dealType = type;
		this.bidQuantity = bidQuantity;
	}

	public Integer getBidListId() {
		return BidListId;
	}

	public void setBidListId(Integer bidListId) {
		BidListId = bidListId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public Timestamp getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(Timestamp bidListDate) {
		this.bidListDate = bidListDate;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public Timestamp getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(Timestamp revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "BidList [BidListId=" + BidListId + ", account=" + account + ", type=" + type + ", bidQuantity="
				+ bidQuantity + ", askQuantity=" + askQuantity + ", bid=" + bid + ", ask=" + ask + ", benchmark="
				+ benchmark + ", bidListDate=" + bidListDate + ", commentary=" + commentary + ", security=" + security
				+ ", status=" + status + ", trader=" + trader + ", book=" + book + ", creationName=" + creationName
				+ ", creationDate=" + creationDate + ", revisionName=" + revisionName + ", revisionDate=" + revisionDate
				+ ", dealName=" + dealName + ", dealType=" + dealType + ", sourceListId=" + sourceListId + ", side="
				+ side + "]";
	}
	
	
}
