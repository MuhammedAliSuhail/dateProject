package com.example.dateLister.config;

import com.example.dateLister.service.DateService;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

@Configuration
public class DateRoute extends RouteBuilder {
    @Autowired
    private  DateService dateService;

    @Override
    public void configure() throws Exception {

        restConfiguration().component("servlet").bindingMode(RestBindingMode.auto);

        rest()
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .get("/dateLister/service/date")
                .param().name("startDate").type(RestParamType.query).dataType("string").endParam()
                .param().name("endDate").type(RestParamType.query).dataType("string").endParam()
                .route()
                .routeId("dateRoute")
                .setBody(exchange -> {
                    String startDate = exchange.getIn().getHeader("startDate", String.class);
                    String endDate = exchange.getIn().getHeader("endDate", String.class);
                    return dateService.getDates(startDate, endDate);
                });

    }
}
