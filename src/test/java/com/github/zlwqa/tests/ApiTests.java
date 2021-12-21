package com.github.zlwqa.tests;

import com.github.zlwqa.config.ApiConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiTests {

    @Test
    public void apiTests() {
        ApiConfig apiConfig = ConfigFactory.create(ApiConfig.class, System.getProperties());
        assertThat(apiConfig.baseURL()).isEqualTo("https://www.mvideo.ru/");
        assertThat(apiConfig.token()).isEqualTo("tokenMvideo");
    }
}
