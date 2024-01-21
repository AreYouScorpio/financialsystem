package hu.webuni.spring.financialsystem.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.*;
import org.apache.activemq.artemis.api.core.TransportConfiguration;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyAcceptorFactory;
import org.apache.activemq.artemis.core.remoting.impl.netty.NettyConnectorFactory;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
public class JmsConfig {
    @Bean
    public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) { // springMVC-s objectmappert importaljuk
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setObjectMapper(objectMapper); //es megmondjuk ennek, h ezt hasznalja
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type"); // a jsonben benne lesz, milyen tipusu objektum, deserializalashoz ez az info majd kelleni fog, nem fogja tudni enelkul kitalalni
        return converter;
    }

    //a publikalashoz TCP-re, kell egy brokerService-t legyartani:

    @Configuration
    public class ArtemisConfig implements ArtemisConfigurationCustomizer {
        @Override
        public void customize(org.apache.activemq.artemis.core.config.Configuration configuration) {
            // Allow Artemis to accept tcp connections (Default port localhost:61616)
            configuration.addConnectorConfiguration("nettyConnector", new TransportConfiguration(NettyConnectorFactory.class.getName()));
            configuration.addAcceptorConfiguration(new TransportConfiguration(NettyAcceptorFactory.class.getName()));
        }
    }
}