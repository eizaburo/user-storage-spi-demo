package com.hoge;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.logging.Logger;
import org.keycloak.Config;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class PropertyFileUserStorageProviderFactory
        implements UserStorageProviderFactory<PropertyFileUserStorageProvider> {

    // logging
    private static final Logger logger = Logger.getLogger(PropertyFileUserStorageProviderFactory.class);
    private static final String PROVIDER_NAME = "spi-demo";
    // Properties
    protected Properties properties = new Properties();

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public void init(Config.Scope config) {
        InputStream is = getClass().getClassLoader().getResourceAsStream("/users.properties");
        if (is == null) {
            logger.warn("プロパティーが読み取れません。");
        } else {
            try {
                properties.load(is);
                logger.warn(properties.getProperty("hoge") + "?????");
            } catch (IOException e) {
                logger.error("プロパティーの読取りに失敗しました。" + e);
            }
        }
    }

    @Override
    public PropertyFileUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new PropertyFileUserStorageProvider(session, model, properties);
    }

}