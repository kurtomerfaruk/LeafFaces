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

import com.blogspot.ofarukkurt.leaffaces.model.map.MapModel;
import com.blogspot.ofarukkurt.leaffaces.model.map.Marker;
import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import java.util.Iterator;
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
    @ResourceDependency(library = "leaffaces", name = "loading/loading.css"),
    @ResourceDependency(library = "leaffaces", name = "loading/loading.js"),
    @ResourceDependency(library = "primefaces", name = "jquery/jquery.js")
})
public class Map extends UIInput {

    protected enum PropertyKeys {

        widgetVar,
        center,
        zoom,
        maxZoom,
        minZoom,
        removeDivHeight,
        style,
        model,
        loadingControl

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

        boolean loadingControl = getLoadingControl();

        writer.startElement("div", this);
        writer.writeAttribute("id", id, "id");
        if (getStyle() != null) {
            writer.writeAttribute("style", getStyle(), null);
        }
        writer.endElement("div");

        writer.startElement("script", this);
        writer.writeAttribute("type", "text/javascript", null);

        String addMap = "var " + mapVar + " = L.map('" + id + "', {"
                + "minZoom: " + minZoom + ","
                + "maxZoom: " + maxZoom + ","
                + "loadingControl: " + loadingControl + "})"
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
        if (getModel() != null) {
            if (!getModel().getMarkers().isEmpty()) {
                encodeMarkers(context, this);
            }
        }
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

    public String getStyle() {
        return (String) getStateHelper().eval(PropertyKeys.style, null);
    }

    public void setStyle(final String style) {
        getStateHelper().put(PropertyKeys.style, style);
    }

    public MapModel getModel() {
        return (MapModel) getStateHelper().eval(PropertyKeys.model, null);
    }

    public void setModel(MapModel model) {
        getStateHelper().put(PropertyKeys.model, model);
    }

    public boolean getLoadingControl() {
        return (Boolean) getStateHelper().eval(PropertyKeys.loadingControl, false);
    }

    public void setLoadingControl(boolean loadingControl) {
        getStateHelper().put(PropertyKeys.loadingControl, loadingControl);
    }

    protected void encodeMarkers(FacesContext context, Map map) throws IOException {
        ResponseWriter writer = context.getResponseWriter();

        MapModel model = map.getModel();

        String icon = "var icon = new L.Icon.Default();\n"
                + "icon.options.shadowSize = [0,0];\n";

        for (Iterator<Marker> iterator = model.getMarkers().iterator(); iterator.hasNext();) {
            Marker marker = iterator.next();
            //encodeMarker(context, marker);
            //writer.write("L.marker([" + marker.getLatlng().toString() + "]).addTo(" + getWidgetVar() + ");");
            String markerValue = icon + "L.marker([" + marker.getLatlng().toString() + "],{icon:icon}).addTo(" + getWidgetVar() + ")";
            if (marker.getTitle() != null) {
                writer.write(markerValue + ".bindPopup('" + marker.getTitle() + "');");
            } else {
                writer.write(markerValue + ";");
            }

        }
    }
}
