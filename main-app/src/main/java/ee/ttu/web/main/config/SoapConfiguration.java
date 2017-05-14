package ee.ttu.web.main.config;

import ee.ttu.web.main.engine.OfferClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("ee.ttu.web.main.soap");
        return marshaller;
    }

    @Bean
    public OfferClient offerClient(Jaxb2Marshaller marshaller) {
        OfferClient client = new OfferClient();
        client.setDefaultUri("http://ttu.ee/web/delivery-info");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
