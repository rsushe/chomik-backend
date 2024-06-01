package com.chomik.core.gateway.config

import com.chomik.core.gateway.service.AuthorizationUserDetailsService
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.proc.SecurityContext

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource

import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Configuration
@Profile("!test")
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
class WebSecurityConfig(
    @Value("\${rsa.public-key}") private val rsaPublicKey: RSAPublicKey,
    @Value("\${rsa.private-key}") private val rsaPrivateKey: RSAPrivateKey,
) {
    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http {
            authorizeRequests {
                authorize("/api/v1/authentication/register", permitAll)
                authorize("/error", permitAll)
                authorize("/actuator/**", permitAll)
                authorize(anyRequest, authenticated)
            }
            headers { frameOptions { sameOrigin } }
            httpBasic { LoginUrlAuthenticationEntryPoint("/api/v1/authentication/login") }
            oauth2ResourceServer { jwt { } }
            cors {
                configurationSource = CorsConfigurationSource {
                    val configuration = CorsConfiguration().applyPermitDefaultValues()
                    configuration.allowedOrigins = listOf("http://localhost", "http://127.0.0.1")
                    configuration.addAllowedMethod(HttpMethod.PATCH)
                    configuration
                }
            }
            csrf { disable() }
        }
        return http.build()
    }

    @Bean
    fun authenticationManager(authorizationUserDetailsService: AuthorizationUserDetailsService): AuthenticationManager =
        ProviderManager(DaoAuthenticationProvider().also {
            it.setUserDetailsService(authorizationUserDetailsService)
            it.setPasswordEncoder(passwordEncoder())
        })

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun jwtDecoder(): JwtDecoder = NimbusJwtDecoder.withPublicKey(rsaPublicKey).build()

    @Bean
    fun jwtEncoder(): JwtEncoder {
        val jwk = RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build()
        val jwks = ImmutableJWKSet<SecurityContext>(JWKSet(jwk))
        return NimbusJwtEncoder(jwks)
    }
}
