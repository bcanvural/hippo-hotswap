package com.bloomreach.components;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.CommonComponent;

//import com.bloomreach.beans.Bookdocument;

public class BookComponent extends CommonComponent {
    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);
//        Bookdocument bookdocument = request.getRequestContext().getContentBean(Bookdocument.class);
//        request.setAttribute("book", bookdocument);

    }
}
