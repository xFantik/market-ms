package ru.pb.market.auth.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//todo: разобраться с видимостью sercet
@PropertySource("secret.properties")
public class AppConfig {

}
