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
 * @Created on date 06/09/2018 10:03:22
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".DrawToolbar")
@ResourceDependencies({
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/draw.css"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/toolbar.css"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/bootstrap.min.css"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/bootstrap-theme.min.css"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/leaflet.draw-toolbar.css"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/bootstrap.min.js"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/toolbar-src.js"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/draw-src.js"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/draw-toolbar.js"),
    @ResourceDependency(library = "leaffaces", name = "drawtoolbar/ColorPicker.js")
})
public class DrawToolbar extends UIComponentBase {

    protected enum PropertyKeys {

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
        
        String position = getPosition();

        String layer = "new LeafletToolbar.DrawToolbar({"
                + "			position: '"+position+"',"
                + "		}).addTo(" + mapVar + ");"
                + mapVar + ".on('draw:created', function(evt) {"
                + "			var	type = evt.layerType,"
                + "				layer = evt.layer;"
                + "			drawnItems.addLayer(layer);"
                + "			layer.on('click', function(event) { "
                + "				new LeafletToolbar.EditToolbar.Popup(event.latlng, { "
                + "					actions: editActions "
                + "				}).addTo(" + mapVar + ", layer);"
                + "			});"
                + "		});";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".DrawToolbar";
    }

    public String getPosition() {
        return (String) getStateHelper().eval(PropertyKeys.position, "topleft");
    }

}
