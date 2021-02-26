package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/***
 * POJO class Trade
 * @author j.c.
 *
 */
@Entity
@Table(name = "trade")
public class Trade {
	@Column(name = "tradeId")
	Integer tradeId;
	
	@Column(name = "account")
	String account;
	
	@Column(name = "type")
	String type;
	
	@Column(name = "buyQuantity")
	Double buyQuantity;
	
	@Column(name = "sellQuantity")
	Double sellQuantity;
	
	@Column(name = "buyPrice")
	Double buyPrice;
	
	@Column(name = "sellPrice")
	Double sellPrice;
	
	@Column(name = "benchmark")
	String benchmark;
	
	@Column(name = "tradeDate")
	Timestamp tradeDate;
	
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
	 */
	public Trade(String account, String type) {
		this.account = account;
		this.type = type;
	}

	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
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

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public Timestamp getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Timestamp tradeDate) {
		this.tradeDate = tradeDate;
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
		return "Trade [tradeId=" + tradeId + ", account=" + account + ", type=" + type + ", buyQuantity=" + buyQuantity
				+ ", sellQuantity=" + sellQuantity + ", buyPrice=" + buyPrice + ", sellPrice=" + sellPrice
				+ ", benchmark=" + benchmark + ", tradeDate=" + tradeDate + ", security=" + security + ", status="
				+ status + ", trader=" + trader + ", book=" + book + ", creationName=" + creationName
				+ ", creationDate=" + creationDate + ", revisionName=" + revisionName + ", revisionDate=" + revisionDate
				+ ", dealName=" + dealName + ", dealType=" + dealType + ", sourceListId=" + sourceListId + ", side="
				+ side + "]";
	}
	
	
}
