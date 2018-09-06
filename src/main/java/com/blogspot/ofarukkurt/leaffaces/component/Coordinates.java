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
 * @Created on date 06/09/2018 21:09:44
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Coordinates")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "coordinate/coordinate.css"),
    @ResourceDependency(library = "leaffaces", name = "coordinate/coordinate.js")})
public class Coordinates extends UIComponentBase {

    protected enum PropertyKeys {

        positions,
        decimals,
        decimalSeperator,
        labelTemplateLat,
        labelTemplateLng,
        enableUserInput
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

        String positions = getPositions();
        int decimals = getDecimals();
        String decimalSeperator = getDecimalSeperator();
        String labelTemplateLat = getLabelTemplateLat();
        String labelTemplateLng = getLabelTemplateLng();
        boolean enableUserInput = isEnableUserInput();

        String layer = "L.control.coordinates({"
                + "	position:\"" + positions + "\","
                + "	decimals:" + decimals + ","
                + "	decimalSeperator:'" + decimalSeperator + "',"
                + "	labelTemplateLat:'" + labelTemplateLat + "', "
                + "	labelTemplateLng:'" + labelTemplateLng + "', "
                + "	enableUserInput:" + enableUserInput + ", "
                + "	useDMS:false, "
                + "	useLatLngOrder: true, "
                + "	markerType: L.marker,"
                + "	markerProps: {},"
                + "}).addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Coordinates";
    }

    public String getPositions() {
        return (String) getStateHelper().eval(PropertyKeys.positions, "bottomright");
    }

    public void setPositions(final String positions) {
        getStateHelper().put(PropertyKeys.positions, positions);
    }

    public int getDecimals() {
        return (Integer) getStateHelper().eval(PropertyKeys.decimals, 5);
    }

    public void setDecimals(final int decimals) {
        getStateHelper().put(PropertyKeys.decimals, decimals);
    }

    public String getDecimalSeperator() {
        return (String) getStateHelper().eval(PropertyKeys.decimalSeperator, ".");
    }

    public void setDecimalSeperator(final String decimalSeperator) {
        getStateHelper().put(PropertyKeys.decimalSeperator, decimalSeperator);
    }

    public String getLabelTemplateLat() {
        return (String) getStateHelper().eval(PropertyKeys.labelTemplateLat, "Latitude: {y}");
    }

    public void setLabelTemplateLat(final String labelTemplateLat) {
        getStateHelper().put(PropertyKeys.labelTemplateLat, labelTemplateLat);
    }

    public String getLabelTemplateLng() {
        return (String) getStateHelper().eval(PropertyKeys.labelTemplateLng, "Longitude: {x}");
    }

    public void setLabelTemplateLng(final String labelTemplateLng) {
        getStateHelper().put(PropertyKeys.labelTemplateLng, labelTemplateLng);
    }

    public boolean isEnableUserInput() {
        return (boolean) getStateHelper().eval(PropertyKeys.enableUserInput, true);
    }

    public void setEnableUserInput(boolean enableUserInput) {
        getStateHelper().put(PropertyKeys.enableUserInput, enableUserInput);
    }

}
