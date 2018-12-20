package com.bloomreach.components;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;

import com.bloomreach.beans.Cupdocument;

public class TestComponent extends CommonComponent {
    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);
        Cupdocument cupdocument = request.getRequestContext().getContentBean(Cupdocument.class);
        request.setAttribute("cup", cupdocument);
    }
}
