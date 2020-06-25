package com.numero.uno.wedify.wedify.security;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "oauth_client_details")
public class OAuth2ClientDetails {

	@Id
	@Column(name = "client_id")
	private String clientId;

	@Column(name = "resource_ids")
	private String resourceIds;

	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "scope")
	private String scopes;

	@Column(name = "authorized_grant_types")
	private String authorizedGrantTypes;

	@Column(name = "web_server_redirect_uri")
	private String webServerRedirectUris;

	@Column(name = "authorities")
	private String getAuthorities;

	@Column(name = "access_token_validity")
	private Integer accessTokenValidity;

	@Column(name = "refresh_token_validity")
	private Integer refreshTokenValidity;

	@Column(name = "additional_information")
	private String additionalInformation;

	@Column(name = "autoapprove")
	private String autoApprove;

}
