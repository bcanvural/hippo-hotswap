package com.bloomreach;

import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.onehippo.cms7.services.HippoServiceRegistry;
import org.onehippo.cms7.services.eventbus.HippoEventBus;
import org.onehippo.cms7.services.eventbus.Subscribe;
import org.onehippo.repository.events.HippoWorkflowEvent;
import org.onehippo.repository.modules.DaemonModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PublicationPostProcessingModule implements DaemonModule {

    private static final Logger logger = LoggerFactory.getLogger(PublicationPostProcessingModule.class);

    public static final String PUBLICATION_INTERACTION = "default:handle:publish";

    @Override
    public void initialize(final Session session) throws RepositoryException {
        HippoServiceRegistry.registerService(this, HippoEventBus.class);
    }

    @Override
    public void shutdown() {
        HippoServiceRegistry.unregisterService(this, HippoEventBus.class);
    }

    @Subscribe
    public void handleEvent(final HippoWorkflowEvent event) {
        if (event.success() && PUBLICATION_INTERACTION.equals(event.interaction())) {
            // post-processing to be implemented here
            logger.info("THIS IS ANOTHER HOTSWAP");
        }
    }

}