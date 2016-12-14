package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	public static double getRate(int GivenCreditScore) throws RateException 
	{
		// - RocketBLL RateBLL.getRate - make sure you throw any exception DONE
		
		//		Call RateDAL.getAllRates... this returns an array of rates
		//		write the code that will search the rates to determine the 
		//		interest rate for the given credit score
		//		hints:  you have to sort the rates...  you can do this by using
		//			a comparator... or by using an OrderBy statement in the HQL
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		double tempRate = -1;
		
		if (rates.size()==0){
			throw new RateException();			
		} else if (rates.get(0).getdInterestRate()> GivenCreditScore){
			throw new RateException(rates.get(0));
		} else {
			for (RateDomainModel rate: rates) {
				if (GivenCreditScore >= rate.getiMinCreditScore()){
					tempRate = rate.getdInterestRate();
				}
			}
		}

		
		// - RocketBLL RateBLL.getRate
		//			obviously this should be changed to return the determined rate
		return tempRate;
		
		
	}
	
	
	// - RocketBLL RateBLL.getPayment 
	//		how to use:
	//		https://poi.apache.org/apidocs/org/apache/poi/ss/formula/functions/FinanceLib.html
	
	public static double getPayment(double r, double n, double p, double f, boolean t)
	{		
		return -1*FinanceLib.pmt(r, n, p, f, t);
	}
}
