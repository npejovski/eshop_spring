package mk.ukim.finki.emt.ordermanagement.service.xport.client;

import mk.ukim.finki.emt.ordermanagement.domain.valueObjects.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class ProductClient {
    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ProductClient(@Value("${app.product-catalog.url}") String serverUrl){
        this.serverUrl = serverUrl;
        this.restTemplate = new RestTemplate();
        var requestFactory = new SimpleClientHttpRequestFactory();
        this.restTemplate.setRequestFactory(requestFactory);
    }

    public List<Product> findAll(){
        try{
            return restTemplate.exchange(uri().path("/api/product").build().toUri(),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Product>>() {
                    }).getBody();
        } catch (Exception e){
            return Collections.emptyList();
        }
    }



    private UriComponentsBuilder uri(){
        return UriComponentsBuilder.fromUriString(this.serverUrl);
    }
}



// NOTICE: RestTemplate will be depricated soon. Spring has new HTTP client called WebClient!

// findAll() - komunikacijata ne e nadezhna, bidejki mozhi da frli Exception ako servisot ne e dostapen (poradi x prichini). Pa nie toa go hendlame so return empty list, no sepak ne uspeala komunikacijata. Pa treba nie nekako povtorno da go pobarame podatokot.
// Zatoa Message Brokers se podobri, bidejki ovozmozhuvaat nadezhna komunikacija?

// Explanations:
// @Value("${app.product-catalog.url}") finds the value for "app.product-catalog.url" in ApplicationProperties.
// SimpleClientHttpRequestFactory - With this we can configure the communication. We can set how the request should be handled, headers, security (with tokens, jwt tokens...)