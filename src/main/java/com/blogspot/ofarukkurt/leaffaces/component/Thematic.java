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
 * @Created on date 06/09/2018 15:13:24
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Thematic")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "example/gaziantep-ilceler.js")})
public class Thematic extends UIComponentBase {

    protected enum PropertyKeys {
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

        String layer = "var info = L.control();"
                + "	info.onAdd = function (map) {\n"
                + "		this._div = L.DomUtil.create('div', 'info');\n"
                + "		this.update();\n"
                + "		return this._div;\n"
                + "	};\n"
                + "	info.update = function (props) {\n"
                + "		this._div.innerHTML = '<h4>US Population Density</h4>' +  (props ?\n"
                + "			'<b>' + props.ILCE_ADI + '</b>'"
                + "			: 'Hover over a state');\n"
                + "	};\n"
                + "	info.addTo(" + mapVar + ");\n"
                + "	function getColor(d) {"
                + "		return d > 1000 ? '#800026' :"
                + "				d > 500  ? '#BD0026' :"
                + "				d > 200  ? '#E31A1C' :"
                + "				d > 100  ? '#FC4E2A' :"
                + "				d > 50   ? '#FD8D3C' :"
                + "				d > 20   ? '#FEB24C' :"
                + "				d > 10   ? '#FED976' :"
                + "							'#FFEDA0';"
                + "	}"
                + "	function style(feature) {"
                + "		return {"
                + "			weight: 2,"
                + "			opacity: 1,"
                + "			color: 'white',"
                + "			dashArray: '3',"
                + "			fillOpacity: 0.7,"
                + "			fillColor: getColor(feature.properties.density)\n"
                + "		};"
                + "	}"
                + "	function highlightFeature(e) {"
                + "		var layer = e.target;"
                + "		layer.setStyle({"
                + "			weight: 5,"
                + "			color: '#666',"
                + "			dashArray: '',"
                + "			fillOpacity: 0.7"
                + "		});"
                + "		if (!L.Browser.ie && !L.Browser.opera && !L.Browser.edge) {"
                + "			layer.bringToFront();"
                + "		} "
                + "		info.update(layer.feature.properties);"
                + "	}"
                + "	var geojson;"
                + "	function resetHighlight(e) {"
                + "		geojson.resetStyle(e.target);"
                + "		info.update();"
                + "	}\n"
                + "	function zoomToFeature(e) {"
                + "		" + mapVar + ".fitBounds(e.target.getBounds());"
                + "	}\n"
                + "	function onEachFeature(feature, layer) {"
                + "		layer.on({"
                + "			mouseover: highlightFeature,\n"
                + "			mouseout: resetHighlight,\n"
                + "			click: zoomToFeature\n"
                + "		});\n"
                + "	}\n"
                + "	geojson = L.geoJson(gaziantep_data, {\n"
                + "		style: style,\n"
                + "		onEachFeature: onEachFeature\n"
                + "	}).addTo(" + mapVar + ");\n"
                + "	var legend = L.control({position: 'bottomright'});\n"
                + "	legend.onAdd = function (map) {\n"
                + "		var div = L.DomUtil.create('div', 'info legend'),\n"
                + "			grades = [0, 10, 20, 50, 100, 200, 500, 1000],\n"
                + "			labels = [],\n"
                + "			from, to;\n"
                + "		for (var i = 0; i < grades.length; i++) {\n"
                + "			from = grades[i];\n"
                + "			to = grades[i + 1];\n"
                + "			labels.push(\n"
                + "				'<i style=\"background:' + getColor(from + 1) + '\"></i> ' +\n"
                + "				from + (to ? '&ndash;' + to : '+'));\n"
                + "		}\n"
                + "		div.innerHTML = labels.join('<br>');\n"
                + "		return div;\n"
                + "	};\n"
                + "	legend.addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Thematic";
    }

}
