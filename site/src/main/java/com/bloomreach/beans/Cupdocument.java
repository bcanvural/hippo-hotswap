package com.bloomreach.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;

@HippoEssentialsGenerated(internalName = "hippohotswap:cupdocument")
@Node(jcrType = "hippohotswap:cupdocument")
public class Cupdocument extends BaseDocument {
    @HippoEssentialsGenerated(internalName = "hippohotswap:title")
    public String getTitle() {
        return getProperty("hippohotswap:title");
    }

    @HippoEssentialsGenerated(internalName = "hippohotswap:color")
    public String getColor() {
        return getProperty("hippohotswap:color");
    }
}
