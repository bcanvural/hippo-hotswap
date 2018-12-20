package com.bloomreach.hotswap;


import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.hippoecm.hst.content.tool.DefaultContentBeansTool;
import org.hippoecm.hst.core.container.ComponentManager;
import org.hippoecm.hst.site.HstServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HotswapWorker {
    private static final Logger logger = LoggerFactory.getLogger(HotswapWorker.class);
    private static final String ENABLE_PROP = "refresh";

    @PostConstruct
    public void init() {
        logger.info("HotswapWorker initialized.");
    }

    public void onScheduledTrigger() {
        logger.info("Triggered in test component!");
        Session session = HotswapUtils.getConfigUserSession();
        if (session != null) {
            try {
                Node rootNode = session.getRootNode();
                if (rootNode.hasProperty(ENABLE_PROP)) {
                    boolean enabled = rootNode.getProperty(ENABLE_PROP).getBoolean();
                    if (enabled) {
                        ComponentManager componentManager = HstServices.getComponentManager();
                        DefaultContentBeansTool beansTool = componentManager.getComponent(DefaultContentBeansTool.class);
                        beansTool.setObjectConverter(null);
                        beansTool.setObjectConverter(beansTool.getObjectConverter());
                        logger.info("Object converter was reset.");
                        rootNode.setProperty(ENABLE_PROP, session.getValueFactory().createValue(false));
                        session.save();
                    } else {
                        logger.debug("Object converter refresher was not enabled, skipping...");
                    }
                }
            } catch (RepositoryException ex) {
                logger.error("{}", ex.getMessage());
            } finally {
                if (session.isLive()) {
                    session.logout();
                }
            }
        }
    }
}
