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
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 03/09/2018 09:47:42
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Locate")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "locate/locate.css"),
    @ResourceDependency(library = "leaffaces", name = "locate/locate.js")})
public class Locate extends UIComponentBase {

    protected enum PropertyKeys {

        title,
        position,
        flyTo
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

        String title = getTitle();
        String position = getPosition();
        boolean flyTo = getFlyTo();

        String layer = "L.control.locate({"
                + "strings: {"
                + "title: '" + title + "'},"
                + "position:'"+position+"',"
                + "flyTo:"+flyTo
                + "}).addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Locate";
    }

    public String getTitle() {
        return (String) getStateHelper().eval(PropertyKeys.title, "My Location");
    }

    public void setTitle(final String title) {
        getStateHelper().put(PropertyKeys.title, title);
    }

    public String getPosition() {
        return (String) getStateHelper().eval(PropertyKeys.position, "topleft");
    }

    public void setPosition(final String position) {
        getStateHelper().put(PropertyKeys.position, position);
    }

    public boolean getFlyTo() {
        return (Boolean) getStateHelper().eval(PropertyKeys.flyTo, false);
    }

    public void setFlyTo(final boolean flyTo) {
        getStateHelper().put(PropertyKeys.flyTo, flyTo);
    }

}
