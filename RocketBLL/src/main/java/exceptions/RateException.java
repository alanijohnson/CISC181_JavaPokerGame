package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {

	//	TODO - RocketBLL RateException - RateDomainModel should be an attribute of RateException
	//	* Add RateRomainModel as an attribute
	//	* Create a constructor, passing in RateDomainModel
	//	* Create a getter (no setter, set value only in Constructor)
	
	RateDomainModel ratedm;
	
	public RateException(){
		super("Rate Credit Score Minimum Schema Not Defined");
	}
	
	public RateException(RateDomainModel rdm){
		super("Credit Score is lower than absolute minimum, " + rdm.getiMinCreditScore()+".");
		ratedm = rdm;
	}

	public RateDomainModel getRatedm() {
		return ratedm;
	}
	
	
	
}
