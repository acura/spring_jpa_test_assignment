package com.gjj.igden.model;

import org.apache.commons.collections4.FactoryUtils;
import org.apache.commons.collections4.list.LazyList;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
// table name = datasets
@Entity
@Table(name="data_set")
public class WatchListDesc implements IWatchListDesc {
  private Integer watchListId; //data_set_id
  private int accountId; // account_fk
  private String watchListName; // dataset name
  private String watchListDetails; // data set desc
  private int marketDataFrequency; // market data frequency
  private String dataProviders;  // data providers
  private List<String> stockSymbolsList; // wl_tickers (insta) -- delete
  private List<OperationParameters> operationParameterses = LazyList.lazyList(new ArrayList<>(),
    FactoryUtils.instantiateFactory(OperationParameters.class));

  
  @Transient
  public List<String> getStockSymbolsList() {
    return stockSymbolsList;
  }

  public void setStockSymbolsList(List<String> stockSymbolsList) {
    this.stockSymbolsList = stockSymbolsList;
  }

  public void setStockSymbolsListFromOperationList(List<OperationParameters> stockSymbolsList) {
    List<String> stringList = stockSymbolsList
      .stream()
      .map(OperationParameters::getName)
      .collect(Collectors.toList());
    this.stockSymbolsList = stringList;
  }

  @Transient
  public List<OperationParameters> getOperationParameterses() {
    return operationParameterses;
  }

  public void setOperationParameterses(
    List<OperationParameters> operationParameterses) {
    this.operationParameterses = operationParameterses;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="data_set_id")
  public int getWatchListId() {
    return watchListId;
  }

  public void setWatchListId(int watchListId) {
    this.watchListId = watchListId;
  }

  @Column(name="account_id")
  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(int accountId) {
    this.accountId = accountId;
  }

  @Column(name="data_set_name")
  public String getWatchListName() {
    return watchListName;
  }

  public void setWatchListName(String watchListName) {
    this.watchListName = watchListName;
  }

  @Column(name="data_set_desc")
  public String getWatchListDetails() {
    return watchListDetails;
  }

  public void setWatchListDetails(String watchListDetails) {
    this.watchListDetails = watchListDetails;
  }

  @Column(name="market_data_frequency")
  public int getMarketDataFrequency() {
    return marketDataFrequency;
  }

  public void setMarketDataFrequency(int marketDataFrequency) {
    this.marketDataFrequency = marketDataFrequency;
  }

  public String getDataProviders() {

    // TODO m it should be List<providerID> like phone in social network
    return dataProviders;
  }

  public void setDataProviders(String dataProviders) {
    this.dataProviders = dataProviders;
  }

  public WatchListDesc() {
  }

  public WatchListDesc(int accountId) {
    this.accountId = accountId;
  }

  public WatchListDesc(int watchListId, int accountId, String watchListName,
                       String watchListDetails, int marketDataFrequency, String dataProviders) {
    this.watchListId = watchListId;
    this.accountId = accountId;
    this.watchListName = watchListName;
    this.watchListDetails = watchListDetails;
    this.marketDataFrequency = marketDataFrequency;
    this.dataProviders = dataProviders;
  }

  @Override
  public String toString() {
    return String.valueOf(" account id =  " + this.getAccountId() + "\n " +
      "data set id = " + this.getWatchListId() + "\n " +
      "market data freq = " + this.getMarketDataFrequency() + "\n " +
      "data set name = " + this.getWatchListName() + "\n " +
      "data set description = " + this.getWatchListDetails() + "\n ");
  }
}
