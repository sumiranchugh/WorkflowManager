package com.atlas.security;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;

import javax.servlet.http.HttpSession;

/**
 * Created by Sumiran Chugh on 1/30/2016.
 */
public class OauthIdentitySession implements SessionFactory {
    @Override
    public Class<?> getSessionType() {
        return HttpSession.class;
    }

    @Override
    public Session openSession() {
        return OauthSession();
    }

    private Session OauthSession() {
        return new Session() {
            @Override
            public void flush() {
            }

            @Override
            public void close() {

            }
        };
    }
}
