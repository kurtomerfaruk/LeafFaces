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
 * @author Omer Faruk KURT
 * @Created on date 15/03/2018 10:13:34
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".OSMLayer")
public class OSMLayer extends UIComponentBase {

    @Override
    public void encodeAll(FacesContext context) throws IOException {
        ResponseWriter writer = context.getResponseWriter();
        UIComponent parent = this;

        while (!(parent instanceof Map)) {
            parent = parent.getParent();
        }

        Map mapComponent = (Map) parent;
        String mapVar = mapComponent.getWidgetVar();
        System.out.println("mapVar:"+mapVar);

        String layer = "var osmUrl = 'http://{s}.tile.osm.org/{z}/{x}/{y}.png',"
                + "osm = L.tileLayer(osmUrl, {"
                + "maxZoom: 22,"
                + "attribution: '&copy; <a href=\"http://osm.org/copyright\">OpenStreetMap</a> contributors'"
                + "});";
        String addLayer = mapVar + ".addLayer(osm);";

        writer.write(layer + addLayer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".OSMLayer";
    }

}
