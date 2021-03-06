package com.gjj.igden.utils;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.gjj.igden.model.IWatchListDesc;
import com.google.common.base.Objects;

/**
 * Instrument identifier
 */
//table name = wl_tickers
@Entity
@Table(name="wl_tickers")
@IdClass(InstCId.class)
public class InstId {
  private final static String SEPARATOR = "@";
  private String symbol; // create composite primary key symbol + exch_id
  private String exchId;
  // new parameter WachListDesc 
  private IWatchListDesc iWatchListDesc;
  
  //transient
  private Exchange exchange;

  public InstId(String str) {
    validateCorrectString(str);
    String[] tokens = str.split("@");
    symbol = tokens[0].toUpperCase();
    exchId = tokens[1].toUpperCase();
  }

  public InstId(String symbol, String exchId) {
    this.symbol = symbol;
    this.exchId = exchId;
  }

  @Transient
  public Exchange getExchange() {
    if (java.util.Objects.equals(exchId, "NASDAQ")) {
      return Exchange.NASDAQ;
    }
    if (java.util.Objects.equals(exchId, "NYSE")) {
      return Exchange.NYSE;
    }
    return exchange;
  }

  private void validateCorrectString(String str) {
  }

  public String getExchId() {
    return exchId;
  }

  public String getSymbol() {
    return symbol;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof InstId)) {
      return false;
    }
    InstId altInstId = (InstId) o;
    return Objects.equal(symbol, altInstId.symbol) &&
      Objects.equal(exchId, altInstId.exchId);
  }

  public Exchange getExchange(String exchId) {
    if (exchId.equalsIgnoreCase("NYSE")) {
      return Exchange.NYSE;
    } else if (exchId.equalsIgnoreCase("nasdaq")) {
      return Exchange.NASDAQ;
    } else {
      throw new IllegalArgumentException("your exchange id is not correct");
    }
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(symbol, exchId);
  }

  @Override
  public String toString() {
    return symbol + SEPARATOR + exchId;
  }
}

