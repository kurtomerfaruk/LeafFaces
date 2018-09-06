/*
 * Copyright 2018 Omer Faruk Kurt.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.blogspot.ofarukkurt.leaffaces.component;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 05/09/2018 16:05:21
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Logo")
public class Logo extends UIComponentBase {

    protected enum PropertyKeys {

        logoPath,
        width,
        height,
        position
    }

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIComponent parent = this;

        while (!(parent instanceof Map)) {
            parent = parent.getParent();
        }

        Map mapComponent = (Map) parent;
        String mapVar = mapComponent.getWidgetVar();
        String logoPath = getLogoPath();
        String width = getWidth();
        String height = getHeight();
        String position = getPosition();

        String layer = "L.Control.Watermark = L.Control.extend({"
                + "onAdd: function(map) {"
                + "var img = L.DomUtil.create('img');"
                + "img.src = '" + logoPath + "';"
                + "img.style.width = '" + width + "';"
                + "img.style.height = '" + height + "';"
                + "return img;"
                + "},"
                + "onRemove: function(map) {"
                + "}"
                + "});"
                + "L.control.watermark = function(opts) {"
                + "return new L.Control.Watermark(opts); };"
                + "L.control.watermark({ position: '" + position + "' }).addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Logo";
    }

    public String getLogoPath() {
        return (String) getStateHelper().eval(PropertyKeys.logoPath);
    }

    public void setLogoPath(final String logoPath) {
        getStateHelper().put(PropertyKeys.logoPath, logoPath);
    }

    public String getWidth() {
        return (String) getStateHelper().eval(PropertyKeys.width, "64px");
    }

    public void setWidth(final String width) {
        getStateHelper().put(PropertyKeys.width, width);
    }

    public String getHeight() {
        return (String) getStateHelper().eval(PropertyKeys.height, "64px");
    }

    public void setHeight(final String height) {
        getStateHelper().put(PropertyKeys.height, height);
    }

    public String getPosition() {
        return (String) getStateHelper().eval(PropertyKeys.position, "bottomright");
    }

    public void setPosition(final String position) {
        getStateHelper().put(PropertyKeys.position, position);
    }

}
