package com.bloomreach.beans;

import java.util.List;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.onehippo.cms7.essentials.components.model.AuthorEntry;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImage;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;

@HippoEssentialsGenerated(internalName = "hippohotswap:author")
@Node(jcrType = "hippohotswap:author")
public class Author extends HippoDocument implements AuthorEntry {
    public static final String ROLE = "hippohotswap:role";
    public static final String ACCOUNTS = "hippohotswap:accounts";
    public static final String FULL_NAME = "hippohotswap:fullname";
    public static final String IMAGE = "hippohotswap:image";
    public static final String CONTENT = "hippohotswap:content";

    @HippoEssentialsGenerated(internalName = "hippohotswap:fullname")
    public String getFullName() {
        return getProperty(FULL_NAME);
    }

    @HippoEssentialsGenerated(internalName = "hippohotswap:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    @HippoEssentialsGenerated(internalName = "hippohotswap:role")
    public String getRole() {
        return getProperty(ROLE);
    }

    @HippoEssentialsGenerated(internalName = "hippohotswap:accounts")
    public List<Account> getAccounts() {
        return getChildBeansByName(ACCOUNTS, Account.class);
    }

    @HippoEssentialsGenerated(internalName = "hippohotswap:photo")
    public HippoGalleryImageSet getPhoto() {
        return getLinkedBean("hippohotswap:photo", HippoGalleryImageSet.class);
    }
}
