package com.hoge;

import java.util.HashMap;
import java.util.Properties;
import java.util.Map;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class PropertyFileUserStorageProvider implements UserStorageProvider, UserLookupProvider {

    protected KeycloakSession session;
    protected Properties properties;
    protected ComponentModel model;

    protected Map<String, UserModel> loadedUsers = new HashMap<>();

    public PropertyFileUserStorageProvider(KeycloakSession session, ComponentModel model, Properties properties) {
        this.session = session;
        this.model = model;
        this.properties = properties;
    }

    // UserStorageProvider
    @Override
    public void close() {

    }

    // UserLookupProvider
    @Override
    public UserModel getUserByUsername(String username, RealmModel realm) {
        return null;
    }

    protected UserModel createAdapter(RealmModel realm, String username) {
        return null;
    }

    @Override
    public UserModel getUserById(String id, RealmModel realm) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();
        return getUserByUsername(username, realm);
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realm) {
        return null;
    }
}