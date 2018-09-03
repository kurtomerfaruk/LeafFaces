package com.blogspot.ofarukkurt.leaffaces.utils;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;
import javax.faces.application.ResourceWrapper;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 03/09/2018 10:15:00
 */
public class LeafFacesResourceHandler extends ResourceHandlerWrapper {

    private ResourceHandler wrapped;

    public LeafFacesResourceHandler(ResourceHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Resource createResource(String resourceName) {
        return createResource(resourceName, null, null);
    }

    @Override
    public Resource createResource(String resourceName, String libraryName) {
        return createResource(resourceName, libraryName, null);
    }

    @Override
    public Resource createResource(String resourceName, String libraryName, String contentType) {
        final Resource resource = super.createResource(resourceName, libraryName, contentType);

        if (resource == null) {
            return null;
        }

        return new ResourceWrapper() {

            @Override
            public String getRequestPath() {
                if (super.getRequestPath().contains("&v=") || super.getRequestPath().contains("?v=")) {
                    return super.getRequestPath();
                } else {
                    return super.getRequestPath() + "&v=1.0";
                }
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getResourceName() {
                return resource.getResourceName();
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getLibraryName() {
                return resource.getLibraryName();
            }

            @Override // Necessary because this is missing in ResourceWrapper (will be fixed in JSF 2.2).
            public String getContentType() {
                return resource.getContentType();
            }

            @Override
            public Resource getWrapped() {
                return resource;
            }
        };
    }

    @Override
    public ResourceHandler getWrapped() {
        return wrapped;
    }

}
