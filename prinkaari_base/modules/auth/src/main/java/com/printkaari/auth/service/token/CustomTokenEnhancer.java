package com.printkaari.auth.service.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import com.printkaari.data.dao.entity.Role;
import com.printkaari.data.dao.entity.User;
import com.printkaari.auth.service.UserDetailsImpl;

public class CustomTokenEnhancer implements TokenEnhancer {

	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
	        OAuth2Authentication authentication) {
		User user = ((UserDetailsImpl) authentication.getPrincipal()).getUser();
		final Map<String, Object> additionalInfo = new HashMap<>();

		additionalInfo.put("full_name", user.getFirstName() + " " + user.getLastName());
		//additionalInfo.put("company_id", user.getCompany().getId());
		additionalInfo.put("username", user.getEmailId());
		List<String> roles = new ArrayList<>();
		for (Role role : user.getRoles()) {
			roles.add(role.getName());
		}
		additionalInfo.put("authorities", roles);

		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

		return accessToken;
	}

}
