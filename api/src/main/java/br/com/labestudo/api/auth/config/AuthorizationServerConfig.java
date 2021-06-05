package br.com.labestudo.api.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import br.com.labestudo.api.auth.properties.JwtKeyStoreProperties;
import br.com.labestudo.api.auth.service.JpaUserDetailsService;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JpaUserDetailsService userDetailsService;

	@Autowired
	private JwtKeyStoreProperties jwtKeyStoreProperties;

	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.jdbc(dataSource);
	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).userDetailsService(userDetailsService)
				.reuseRefreshTokens(false).accessTokenConverter(jwtAccessTokenConverter())
				.approvalStore(approvalStore(endpoints.getTokenStore()));
	}

	private ApprovalStore approvalStore(TokenStore tokenStore) {
		var tokenApprovalStore = new TokenApprovalStore();
		tokenApprovalStore.setTokenStore(tokenStore);

		return tokenApprovalStore;
	}

	@Bean
	public AccessTokenConverter jwtAccessTokenConverter() {
		var jksResource = jwtKeyStoreProperties.getPath();
		var keyPairAlias = jwtKeyStoreProperties.getKeyPairAlias();
		var keyStorePass = jwtKeyStoreProperties.getKeyStorePass().toCharArray();

		var keyStoreKeyFactory = new KeyStoreKeyFactory(jksResource, keyStorePass);
		var keyPair = keyStoreKeyFactory.getKeyPair(keyPairAlias);

		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
		jwtAccessTokenConverter.setKeyPair(keyPair);

		return jwtAccessTokenConverter;
	}

}
