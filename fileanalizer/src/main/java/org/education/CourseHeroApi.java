package org.education;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;

public class CourseHeroApi extends DefaultApi20{

	@Override
	public String getAccessTokenEndpoint() {	
		return "https://www.coursehero.com/api/authorize";
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
		System.out.println("Config is " + config.getApiKey() + " " + config.getApiSecret());
		
		return String.format("https://www.coursehero.com/api/authorize/?api_key=%s&%s", config.getApiKey(), "state=SOME_STATE");
	}

}
