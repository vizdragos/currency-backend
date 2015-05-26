package com.currency.model;

import com.currency.util.date.DateTimeInitializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "trades")
public class Trade extends BaseEntity<Long> {

	private String userId;

	@Enumerated
	private Currency currencyFrom;

	@Enumerated
	private Currency currencyTo;

	private BigDecimal amountSell;

	private BigDecimal amountBuy;

	private Double rate;

	//@DateTimeFormat(pattern = DateTimeInitializer.DATE_TIME_FORMAT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeInitializer.DATE_TIME_FORMAT)
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime timePlaced;

	private Country originatingCountry;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Currency getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(Currency currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public Currency getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(Currency currencyTo) {
		this.currencyTo = currencyTo;
	}

	public BigDecimal getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(BigDecimal amountSell) {
		this.amountSell = amountSell;
	}

	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public DateTime getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(DateTime timePlaced) {
		this.timePlaced = timePlaced;
	}

	public Country getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(Country originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static final class Builder {

		private Long id;

		private String userId;

		private Currency currencyFrom;

		private Currency currencyTo;

		private BigDecimal amountSell;

		private BigDecimal amountBuy;

		private Double rate;

		private DateTime timePlaced;

		private Country originatingCountry;

		private Builder() {
		}

		public Builder withId(Long id) {
			this.id = id;
			return this;
		}

		public Builder withUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public Builder withCurrencyFrom(Currency currencyFrom) {
			this.currencyFrom = currencyFrom;
			return this;
		}

		public Builder withCurrencyTo(Currency currencyTo) {
			this.currencyTo = currencyTo;
			return this;
		}

		public Builder withAmountSell(BigDecimal amountSell) {
			this.amountSell = amountSell;
			return this;
		}

		public Builder withAmountBuy(BigDecimal amountBuy) {
			this.amountBuy = amountBuy;
			return this;
		}

		public Builder withRate(Double rate) {
			this.rate = rate;
			return this;
		}

		public Builder withTimePlaced(DateTime timePlaced) {
			this.timePlaced = timePlaced;
			return this;
		}

		public Builder withOriginatingCountry(Country originatingCountry) {
			this.originatingCountry = originatingCountry;
			return this;
		}

		public Trade build() {
			Trade trade = new Trade();
			trade.setId(id);
			trade.setUserId(userId);
			trade.setCurrencyFrom(currencyFrom);
			trade.setCurrencyTo(currencyTo);
			trade.setAmountBuy(amountBuy);
			trade.setAmountSell(amountSell);
			trade.setRate(rate);
			trade.setTimePlaced(timePlaced);
			trade.setOriginatingCountry(originatingCountry);

			return trade;
		}
	}
}
