package rocketBase;

import static org.junit.Assert.*;

import org.apache.poi.ss.formula.functions.Rate;
import org.junit.Test;

import exceptions.RateException;

public class rate_test {

	//DONE - RocketBLL rate_test
	//		Check to see if a known credit score returns a known interest rate
	
	//DONE - RocketBLL rate_test
	//		Check to see if a RateException is thrown if there are no rates for a given
	//		credit score
	@Test
	public void test_getRate() throws RateException {
		assertEquals(RateBLL.getRate(625), 5.0, 0.001);
		assertEquals(RateBLL.getRate(675), 4.5, 0.001);
		assertEquals(RateBLL.getRate(725), 4.0, 0.001);
		assertEquals(RateBLL.getRate(775), 3.75, 0.001);
		assertEquals(RateBLL.getRate(825), 3.5, 0.001);
		assertEquals(RateBLL.getRate(750), 3.75, 0.001);
	}
	
	//
	@Test
	public void test_getPayment() throws RateException {
		assertEquals(RateBLL.getPayment(4.0/1200, 360, 300000, 0, false), -1432.25, 0.005);
	}
	
	@Test (expected = RateException.class)
	public void test_getRateException() throws RateException {
		RateBLL.getRate(500);
	}

}
