package com.recap.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TokenProcessor{
	
	private static Logger logger = LogManager.getLogger(TokenProcessor.class);

	public TokenProperties validateAndReturnTokenProperties(TokenProperties tokenProperties, 
			OAuth2Client oAuth2Client) throws Exception {
		Date currentDate = new Date();
		int minutes = currentDate.getMinutes();
		currentDate.setMinutes(minutes + 5);
		if (!currentDate.before(tokenProperties.getTokenExpiration())) {
			logger.info("Requesting new nypl token");
			tokenProperties = oAuth2Client.createAndGetTokenAccessProperties();
		}
		logger.info("Going to send bib to API Service at - "
				+ new SimpleDateFormat("yyyy-MM-dd").format(currentDate));
		logger.info("Token expires - " + tokenProperties.getTokenExpiration());
		logger.info(tokenProperties.getTokenValue());
		return tokenProperties;
	}

}
