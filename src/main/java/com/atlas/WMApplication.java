package com.atlas;

import com.atlas.security.OauthIdentitySession;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WMApplication {

    public static void main(String[] args) {
        SpringApplication.run(WMApplication.class, args);
        //  getProcessEngine();
    }

	/*@EnableWebSecurity
    static class SecConfig extends WebSecurityConfigurerAdapter{

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.csrf().disable().authorizeRequests().anyRequest().permitAll();
		}

	}*/


    public static void getProcessEngine() {
        StandaloneProcessEngineConfiguration standaloneProcessEngineConfiguration = (StandaloneProcessEngineConfiguration) StandaloneProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("PB.bpmn20.xml");

        List<SessionFactory> sessionFactories = new ArrayList<SessionFactory>();

        sessionFactories.add(new OauthIdentitySession());

        standaloneProcessEngineConfiguration.setCustomSessionFactories(sessionFactories);

        ProcessEngines.registerProcessEngine(standaloneProcessEngineConfiguration.buildProcessEngine());


    }
}
