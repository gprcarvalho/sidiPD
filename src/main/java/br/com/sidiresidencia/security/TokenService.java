package br.com.sidiresidencia.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import br.com.sidiresidencia.ennum.ProfileEnum;
import br.com.sidiresidencia.model.User;
import br.com.sidiresidencia.service.exception.ProfileException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authentication) {
		
		User logged = (User) authentication.getPrincipal();
		Date currentDate = new Date();
		Date expirationDate = new Date(currentDate.getTime() + expiration);
		
		return Jwts.builder().setIssuer("String API").setSubject(logged.getId().toString())
				.setIssuedAt(currentDate).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public boolean isTokenValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public Long getIdUser(String token) {
	Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
	return Long.parseLong(claims.getSubject());
	}
	
	public String getUserEmail() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		   return ((UserDetails)principal).getUsername();
		} else {
		   return principal.toString();
		}
	}
	
	public Long getUserIdMe() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof User) {
		   return ((User)principal).getId();
		} else {
		   return Long.parseLong(principal.toString());
		}
	}
	
	public ProfileEnum getProfileUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof User) {
		   return ((User)principal).getProfile();
		} else {
		   throw new ProfileException("Profile required") ;
		}
	}
	
}
