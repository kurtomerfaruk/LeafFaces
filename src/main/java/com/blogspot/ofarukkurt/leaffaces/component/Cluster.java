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
 * @author Omer Faruk Kurt
 * @Created on date 06/09/2018 16:18:22
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Cluster")
@ResourceDependencies({
    @ResourceDependency(library = "leaflet", name = "cluster/MarkerCluster.Default.css"),
    @ResourceDependency(library = "leaflet", name = "cluster/MarkerCluster.css"),
    @ResourceDependency(library = "leaflet", name = "cluster/leaflet.markercluster-src.js")
})
public class Cluster extends UIComponentBase {

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Cluster";
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

        List<String> points = getPoints();
        String data = "var data=" + points + ";"
                + "var markers = L.markerClusterGroup();"
                + " //<![CDATA["
                + "		for (var i = 0; i < data.length; i++) {"
                + "			var a = data[i];"
                + "			var title = a[2];"
                + "			var marker = L.marker(new L.LatLng(a[0], a[1]), { title: title });"
                + "			marker.bindPopup(title);"
                + "			markers.addLayer(marker);"
                + "		}"
                + " //]]>"
                + "		" + mapVar + ".addLayer(markers);";
        writer.write(data);
    }

    public List<String> getPoints() {
        return (List<String>) getStateHelper().eval(HeatMap.PropertyKeys.points);
    }

    public void setPoints(final List<String> points) {
        getStateHelper().put(HeatMap.PropertyKeys.points, points);
    }

}
