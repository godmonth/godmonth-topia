package com.godmonth.topia.basic.joda.converter;

import java.math.BigDecimal;


import jodd.bean.BeanUtil;
import org.junit.jupiter.api.Test;

public class MoneyAccessorTest {

	@Test
	public void setValue() {
		TH th = new TH();
		BeanUtil.pojo.setProperty(th, "abcUnit", "1");
	}

	public static class TH {
		private String abcUnit;
		private BigDecimal abcAmount;

		public String getAbcUnit() {
			return abcUnit;
		}

		public void setAbcUnit(String abcUnit) {
			this.abcUnit = abcUnit;
		}

		public BigDecimal getAbcAmount() {
			return abcAmount;
		}

		public void setAbcAmount(BigDecimal abcAmount) {
			this.abcAmount = abcAmount;
		}
		
	}
}
