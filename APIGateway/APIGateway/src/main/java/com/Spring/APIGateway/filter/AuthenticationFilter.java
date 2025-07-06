package com.Spring.APIGateway.filter;

import com.Spring.APIGateway.service.JwtService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService service;

//    @Autowired
//    private RestTemplate restTemplate;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                //header contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION))
                    throw new RuntimeException("Missing authorization header");
                String authheaders = exchange.getRequest().getHeaders().get(org.springframework.http.HttpHeaders.AUTHORIZATION).get(0);
                if(authheaders != null && authheaders.startsWith("Bearer ")){
                    authheaders = authheaders.substring(7);
                try{
                    //REST call to Auth service
                    //restTemplate.getForObject("http:/AUTH-SERVICE/validate?token"+authheaders, String.class);
                    service.validateToken(authheaders);
                }catch (Exception e){
                    System.out.println(e);
                }
                }

            }
            return chain.filter(exchange);
        }


        ));
    }

    public static class Config{

    }
}
