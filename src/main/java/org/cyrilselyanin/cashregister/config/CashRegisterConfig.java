package org.cyrilselyanin.cashregister.config;

import okhttp3.OkHttpClient;
import org.cyrilselyanin.cashregister.service.SbisAuthService;
import org.cyrilselyanin.cashregister.service.SbisRetailService;
import org.cyrilselyanin.cashregister.service.SbisService;
import org.cyrilselyanin.cashregister.service.SbisServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CashRegisterConfig {
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient();
    }

//    @Bean
//    public SbisAuthService sbisAuthService(final OkHttpClient okHttpClient) {
//        return new SbisAuthService(okHttpClient);
//    }
//
//    @Bean
//    public SbisRetailService sbisRetailService(final OkHttpClient okHttpClient) {
//        return new SbisRetailService(okHttpClient);
//    }

//    @Bean
//    public SbisService sbisService(
//            SbisAuthService sbisAuthService,
//            SbisRetailService sbisRetailService
//    ) {
//        return new SbisServiceImpl(sbisAuthService, sbisRetailService);
//    }
}
