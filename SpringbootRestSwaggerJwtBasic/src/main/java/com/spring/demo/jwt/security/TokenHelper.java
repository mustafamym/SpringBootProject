package com.spring.demo.jwt.security;

import java.util.Date;
import java.util.function.Function;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
/**
 * Created by md Gulam Mustafa
 */
@Component
public class TokenHelper {
 static final Logger LOG = LoggerFactory.getLogger(TokenHelper.class);

 @Value("${app.security.jwt.name}")
    private String APP_NAME;

    @Value("${app.security.jwt.secret}")
    public String SECRET;

    @Value("${app.security.jwt.expires_in}")
    private String EXPIRES_IN;

    @Value("${app.security.jwt.header}")
    private String AUTH_HEADER;

    @Value("${app.security.jwt.cookie}")
    public String AUTH_TOKEN_COOKIE;

    //@Autowired
    //private SessionTokenService sessionTokenService;

    private SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Long getUserIdFromToken(String token) {
        String userId = null;
        try {
          userId=getClaimFromToken(token, Claims::getId);
 
        } catch (Exception ex) {
            LOG.error("Unable extract your id from token::"+ex);
            userId = null;
        }
        return Long.parseLong(userId);
    }

    public Date getIssuedAtDateFromToken(String token) {
        Date issueAt;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            issueAt = claims.getIssuedAt();
        } catch (Exception e) {
            issueAt = null;
        }
        return issueAt;
    }

    public String getAudienceFromToken(String token) {
        String audience;
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            audience = claims.getAudience();
        } catch (Exception e) {
            audience = null;
        }
        return audience;
    }

    public String refreshToken(String token) {
        String refreshedToken;
        Date a = new Date();
        try {
            final Claims claims = this.getAllClaimsFromToken(token);
            claims.setIssuedAt(a);
            refreshedToken = Jwts.builder()
                    .setClaims(claims)
                    .setExpiration(generateExpirationDate())
                    .signWith(SIGNATURE_ALGORITHM, SECRET)
                    .compact();
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public String generateToken(String username, String userId, String fullName, String roleName) {
        String audience = "web";
        String token = Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setId(userId)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .claim("role", roleName)
                .claim("full_name", fullName)
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
        
        //sessionTokenService.save(token, username);
        
        return token;
    }
    public String generateTokenForUserActivation(String username, String userId) {
   
        return Jwts.builder()
                .setIssuer(APP_NAME)
                .setSubject(username)
                .setId(userId)
                .setIssuedAt(new Date())
                .signWith(SIGNATURE_ALGORITHM, SECRET)
                .compact();
    }

     public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    
    private Claims getAllClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        long expiresIn = Integer.parseInt(StringUtils.deleteWhitespace(EXPIRES_IN));
        return new Date(new Date().getTime() + expiresIn * 1000);
    }

    public int getExpiration() {
        return Integer.parseInt(StringUtils.deleteWhitespace(EXPIRES_IN));
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        boolean validateToken;
        AuthUserPrincipal user = (AuthUserPrincipal) userDetails;
        final String username = getUsernameFromToken(token);
        final Date created = getIssuedAtDateFromToken(token);
        validateToken = username != null
                && username.equals(userDetails.getUsername())
                && isInSession(token, username);
        return validateToken;
    }
    
    private Boolean isInSession(String token, String username) {
    	//SessionToken sessionToken = sessionTokenService.findByTokenAndUsername(token, username);
    	//return sessionToken != null ? true : false;
    	return true;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        if (lastPasswordReset == null) {
            return false;
        }

        Date tokenCreatedtime = new Date(created.getTime());
        Date lastPasswordChangeTime = new Date(lastPasswordReset.getTime());
        boolean isvaild = (lastPasswordReset != null && tokenCreatedtime.before(lastPasswordChangeTime));

        return isvaild;
    }

    public String getToken(HttpServletRequest request) {
        /**
         * Getting the token from Cookie store
         */
        Cookie authCookie = getCookieValueByName(request, AUTH_TOKEN_COOKIE);
        if (authCookie != null) {
            return authCookie.getValue();
        }
        /**
         * Getting the token from Authentication header e.g Bearer your_token
         */
        String authHeader = request.getHeader(AUTH_HEADER);
        if (authHeader != null) {
        	if (authHeader.startsWith("Bearer "))
        		return authHeader.substring(7);
        	// Support without Bearer prefix
        	return authHeader;
        }
        return null;
    }

    /**
     * Find a specific HTTP cookie in a request.
     *
     * @param request The HTTP request object.
     * @param name The cookie name to look for.
     * @return The cookie, or <code>null</code> if not found.
     */
    public Cookie getCookieValueByName(HttpServletRequest request, String name) {
        if (request.getCookies() == null) {
            return null;
        }
        for (int i = 0; i < request.getCookies().length; i++) {
            if (request.getCookies()[i].getName().equals(name)) {
                return request.getCookies()[i];
            }
        }
        return null;
    }
}
