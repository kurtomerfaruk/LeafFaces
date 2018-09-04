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
import java.util.List;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.component.UIComponentBase;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 04/09/2018 14:46:21
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".HeatMap")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "heatmap/leaflet-heat.js")})
public class HeatMap extends UIComponentBase {

    protected enum PropertyKeys {

        points,
        blur,
        radius,
        maxZoom,
        max
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".HeatMap";
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
        int radius = getRadius();
        int blur = getBlur();
        int maxZoom = getMaxZoom();
        double max = getMax();

        List<String> points = getPoints();
        String data = "var data=" + points + ";"
                + "data = data.map(function (p) { return [p[0], p[1]]; });"
                + "var heat = L.heatLayer(data,{"
                + "        radius: " + radius + ","
                + "        blur: " + blur + ", "
                + "        maxZoom: " + maxZoom + ","
                + "        max: " + max + ","
                + "        gradient: {"
                + "            0.0: 'green',"
                + "            0.5: 'yellow',"
                + "            1.0: 'red'"
                + "        }}).addTo(" + mapVar + ");";
        writer.write(data);
    }

    public List<String> getPoints() {
        return (List<String>) getStateHelper().eval(PropertyKeys.points);
    }

    public void setPoints(final List<String> points) {
        getStateHelper().put(PropertyKeys.points, points);
    }

    public int getRadius() {
        return (Integer) getStateHelper().eval(PropertyKeys.radius, 20);
    }

    public void setRadius(final int radius) {
        getStateHelper().put(PropertyKeys.radius, radius);
    }

    public int getBlur() {
        return (Integer) getStateHelper().eval(PropertyKeys.blur, 15);
    }

    public void setBlur(final int blur) {
        getStateHelper().put(PropertyKeys.blur, blur);
    }

    public int getMaxZoom() {
        return (Integer) getStateHelper().eval(PropertyKeys.maxZoom, 10);
    }

    public void setMaxZoom(final int maxZoom) {
        getStateHelper().put(PropertyKeys.maxZoom, maxZoom);
    }

    public double getMax() {
        return (double) getStateHelper().eval(PropertyKeys.max, 4.0);
    }

    public void setMax(final double max) {
        getStateHelper().put(PropertyKeys.max, max);
    }

}
