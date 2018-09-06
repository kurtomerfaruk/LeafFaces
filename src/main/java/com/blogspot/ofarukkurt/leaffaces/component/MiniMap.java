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
 * @Created on date 06/09/2018 13:18:29
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".MiniMap")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "minimap/minimap.css"),
    @ResourceDependency(library = "leaffaces", name = "minimap/minimap.js")})
public class MiniMap extends UIComponentBase {

    protected enum PropertyKeys {

        toggleDisplay,
        position,
        hideLabel,
        showLabel,
        width,
        height
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

        String position = getPosition();
        String hideLabel = getHideLabel();
        String showLabel = getShowLabel();
        int width = getWidth();
        int height = getHeight();
        boolean toggleDisplay = getToggleDisplay();

        String layer = "var miniMap = new L.Control.MiniMap( new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', "
                + "{minZoom: 0, maxZoom: 13, attribution: 'Map data &copy; OpenStreetMap contributors'}), {"
                + "toggleDisplay: " + toggleDisplay + ","
                + "position:'" + position + "',"
                + "hideText:'" + hideLabel + "',"
                + "showText:'" + showLabel + "'})"
                + ".addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".MiniMap";
    }

    public boolean getToggleDisplay() {
        return (Boolean) getStateHelper().eval(PropertyKeys.toggleDisplay, false);
    }

    public void setToggleDisplay(final boolean toggleDisplay) {
        getStateHelper().put(PropertyKeys.toggleDisplay, toggleDisplay);
    }

    public String getPosition() {
        return (String) getStateHelper().eval(PropertyKeys.position, "topleft");
    }

    public void setPosition(final String position) {
        getStateHelper().put(PropertyKeys.position, position);
    }

    public String getHideLabel() {
        return (String) getStateHelper().eval(PropertyKeys.hideLabel, "Hide MiniMap");
    }

    public void setHideLabel(final String hideLabel) {
        getStateHelper().put(PropertyKeys.hideLabel, hideLabel);
    }

    public String getShowLabel() {
        return (String) getStateHelper().eval(PropertyKeys.showLabel, "Show MiniMap");
    }

    public void setShowLabel(final String showLabel) {
        getStateHelper().put(PropertyKeys.showLabel, showLabel);
    }

    public int getWidth() {
        return (Integer) getStateHelper().eval(PropertyKeys.width, 150);
    }

    public void setWidth(final int width) {
        getStateHelper().put(PropertyKeys.width, width);
    }

    public int getHeight() {
        return (Integer) getStateHelper().eval(PropertyKeys.height, 150);
    }

    public void setHeight(final int height) {
        getStateHelper().put(PropertyKeys.height, height);
    }

}
