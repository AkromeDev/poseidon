package com.nnk.springboot.config;

import java.sql.Timestamp;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.domain.User;

public class EntityTestData {

    public BidList setBid() {
    	
        BidList bidList = new BidList();
        
        bidList.setBidListId(1);
        bidList.setType("type");
        bidList.setAccount("account");
        bidList.setBidQuantity(1.0);
        bidList.setAskQuantity(1.0);
        bidList.setBid(1.0);
        bidList.setAsk(1.0);
        bidList.setBenchmark("benchmark");
        bidList.setBidListDate(new Timestamp(System.currentTimeMillis()));
        bidList.setCommentary("commentary");
        bidList.setSecurity("security");
        bidList.setStatus("status");
        bidList.setTrader("trade");
        bidList.setBook("book");
        bidList.setCreationName("creation name");
        bidList.setCreationDate(new Timestamp(System.currentTimeMillis()));
        bidList.setRevisionName("revision name");
        bidList.setRevisionDate(new Timestamp(System.currentTimeMillis()));
        bidList.setDealName("deal name");
        bidList.setDealType("deal type");
        bidList.setSourceListId("2");
        bidList.setSide("side");
        
        return bidList;
    }

    public CurvePoint setCurve() {
    	
        CurvePoint curvePoint = new CurvePoint();
        
        curvePoint.setCurveId(1);
        curvePoint.setAsOfDate(new Timestamp(System.currentTimeMillis()));
        curvePoint.setTerm(1.0);
        curvePoint.setValue(1.0);
        curvePoint.setCreationDate(new Timestamp(System.currentTimeMillis()));
        
        return curvePoint;
    }


    public Rating setRating() {
    	
        Rating rating = new Rating();
        
        rating.setId(1);
        rating.setMoodysRating("moody");
        rating.setSandPRating("sand");
        rating.setFitchRating("fitch");
        rating.setOrderNumber(1);
        
        return rating;
    }

    public RuleName setRule() {
    	
        RuleName ruleName = new RuleName();
        
        ruleName.setId(1);
        ruleName.setName("name");
        ruleName.setDescription("description");
        ruleName.setJson("json");
        ruleName.setTemplate("template");
        ruleName.setSqlStr("sql str");
        ruleName.setSqlPart("sql part");
        
        return ruleName;
    }

    public Trade setTrade() {
    	
        Trade trade = new Trade();
        
        trade.setId(1);
        trade.setAccount("account");
        trade.setType("type");
        trade.setBuyQuantity(1.0);
        trade.setSellQuantity(1.0);
        trade.setSellPrice(1.0);
        trade.setBenchmark("Tbenchmark");
        trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
        trade.setSecurity("securerity");
        trade.setStatus("status");
        trade.setTrader("trader");
        trade.setBook("book");
        trade.setCreationName("creation name");
        trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
        trade.setRevisionName("revision name");
        trade.setRevisionDate(new Timestamp((System.currentTimeMillis())));
        trade.setDealName("deal name");
        trade.setDealType("deal type");
        trade.setSourceListId("source list id");
        trade.setSide("side");
        
        return trade;
    }

    public User setUser() {
    	
        User user = new User();
        
        user.setId(1);
        user.setUsername("user");
        user.setPassword("userqwertz");
        user.setFullname("user junior");
        user.setRole("USER");
        
        return user;
    }
}
