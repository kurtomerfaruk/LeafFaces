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
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

/**
 *
 * @author Omer Faruk KURT
 * @Created on date 03/09/2018 14:38:06
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Map")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "leaflet.css"),
    @ResourceDependency(library = "leaffaces", name = "leaflet.js"),
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
})
public class Map extends UIInput {

    protected enum PropertyKeys {

        widgetVar,
        center,
        zoom,
        maxZoom,
        minZoom,
        removeDivHeight

    }

    @Override
    public void encodeBegin(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        String mapVar = getWidgetVar();
        String id = this.getClientId();
        String center = getCenter();

        int zoom = getZoom() == 1 ? 15 : getZoom();
        int maxZoom = getMaxZoom();
        int minZoom = getMinZoom();
        int removeDivHeight = getRemoveDivHeight();

        writer.startElement("div", this);
        writer.writeAttribute("id", id, "id");
        writer.endElement("div");

        writer.startElement("script", this);
        writer.writeAttribute("type", "text/javascript", null);

        String addMap = "var " + mapVar + " = L.map('" + id + "', {"
                + "minZoom: " + minZoom + ","
                + "maxZoom: " + maxZoom
                + "})"
                + ".setView([" + center + "], "
                + zoom + ");";

        id = id.replace(":", "\\\\:");

        String fitToPageDiv = " $(document).ready(function () {"
                + "var windowH = $(window).height();"
                + "windowH -= " + removeDivHeight + ";"
                + "$('#" + id + "').css({'height': windowH + 'px'});"
                //                + "$('canvas.leaflet-heatmap-layer.leaflet-layer.leaflet-zoom-animated').css({'height': windowH + 'px'});"
                //                + "$('[data-toggle=\"tooltip\"]').tooltip();"
                + "});";

        String updateSize = "setTimeout(function(){ " + mapVar + ".invalidateSize()}, 400);";

        writer.write(addMap + fitToPageDiv + updateSize);
    }

    @Override
    public void encodeEnd(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        writer.endElement("script");
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + "Map";
    }

    public String getWidgetVar() {
        return (String) getStateHelper().eval(PropertyKeys.widgetVar, null);
    }

    public void setWidgetVar(String widgetVar) {
        getStateHelper().put(PropertyKeys.widgetVar, widgetVar);
    }

    public String getCenter() {
        return (String) getStateHelper().eval(PropertyKeys.center);
    }

    public void setCenter(String center) {
        getStateHelper().put(PropertyKeys.center, center);

    }

    public int getZoom() {
        return (Integer) getStateHelper().eval(PropertyKeys.zoom, 10);
    }

    public void setZoom(final int zoom) {
        getStateHelper().put(PropertyKeys.zoom, zoom);
    }

    public int getMaxZoom() {
        return (Integer) getStateHelper().eval(PropertyKeys.maxZoom, 18);
    }

    public void setMaxZoom(final int maxZoom) {
        getStateHelper().put(PropertyKeys.maxZoom, maxZoom);
    }

    public int getMinZoom() {
        return (Integer) getStateHelper().eval(PropertyKeys.minZoom, 1);
    }

    public void setMinZoom(final int minZoom) {
        getStateHelper().put(PropertyKeys.zoom, minZoom);
    }

    public int getRemoveDivHeight() {
        return (Integer) getStateHelper().eval(PropertyKeys.removeDivHeight, 100);
    }

    public void setRemoveDivHeight(final int removeDivHeight) {
        getStateHelper().put(PropertyKeys.removeDivHeight, removeDivHeight);
    }

}
