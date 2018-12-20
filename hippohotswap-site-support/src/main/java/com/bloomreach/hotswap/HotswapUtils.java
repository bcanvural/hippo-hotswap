package com.bloomreach.hotswap;

import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.hippoecm.hst.site.HstServices;

class HotswapUtils {
    private HotswapUtils() {
    }

    static Session getConfigUserSession() {
        try {
            Repository repository = HstServices
                    .getComponentManager()
                    .getComponent(Repository.class.getName() + ".delegating");
            return repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        } catch (RepositoryException ex) {
            return null;
        }
    }
}
