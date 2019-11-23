package tap.nforla.tapgateway;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationFilter extends ZuulFilter {

    private final Logger logger = LoggerFactory.getLogger(AuthorizationFilter.class);
    @Value("${TAP_JWT_SECRET}")
    private String jwtSharedSecret;
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        if(!request.getRequestURI().equals("/auth")){

            String token = request.getHeader(TOKEN_HEADER);

            try{

                if(StringUtils.isNotEmpty(token) && token.startsWith(TOKEN_PREFIX)){

                    byte[] signingKey = jwtSharedSecret.getBytes();

                    Jws<Claims> claimsJws =  Jwts.parser()
                            .setSigningKey(signingKey)
                            .parseClaimsJws(token.replace(TOKEN_PREFIX, ""));

                    String username = claimsJws.getBody().getSubject();

                    if(StringUtils.isNotEmpty(username)){
                        logger.info(String.format("Token válido: %s autorizado", username));
                        return null;
                    }
                }

                throw new IllegalArgumentException();

            }catch (JwtException | IllegalArgumentException exc){

                logger.warn("JWT no válido");
                context.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                context.setSendZuulResponse(false);
                context.setResponseBody("{\"mensaje\": \"JWT no válido\"}");

            }
        }
        return null;
    }
}
