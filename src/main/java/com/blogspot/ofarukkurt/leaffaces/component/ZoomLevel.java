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
 * @Created on date 04/09/2018 13:56:59
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".ZoomLevel")
public class ZoomLevel extends UIComponentBase {

    protected enum PropertyKeys {

        title
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

        String layer = "var ZoomViewer = L.Control.extend({\n"
                + "		onAdd: function(){\n"
                + "\n"
                + "			var container= L.DomUtil.create('div');\n"
                + "			var gauge = L.DomUtil.create('div');\n"
                + "			container.style.width = '200px';\n"
                + "			container.style.background = 'rgba(255,255,255,0.5)';\n"
                + "			container.style.textAlign = 'left';\n"
                + mapVar + ".on('zoomstart zoom zoomend', function(ev){\n"
                + "				gauge.innerHTML = ' " + title + " ' + " + mapVar + ".getZoom();\n"
                + "			})\n"
                + "			container.appendChild(gauge);\n"
                + "\n"
                + "			return container;\n"
                + "		}\n"
                + "	});\n"
                + "\n"
                + "	(new ZoomViewer).addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".ZoomLevel";
    }

    public String getTitle() {
        return (String) getStateHelper().eval(PropertyKeys.title, "Zoom Level :");
    }

    public void setTitle(final String title) {
        getStateHelper().put(PropertyKeys.title, title);
    }
}
