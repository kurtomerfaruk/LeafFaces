package com.blogspot.ofarukkurt.leaffaces.component.map;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import javax.faces.application.ResourceDependencies;
import javax.faces.application.ResourceDependency;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.render.FacesRenderer;
import javax.faces.render.Renderer;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 03/09/2018 10:18:43
 */
@ResourceDependencies({
    @ResourceDependency(name = "jsf.js", target = "head", library = "javax.faces"),
    @ResourceDependency(library = "leaffaces", name = "leaflet.css"),
    @ResourceDependency(library = "leaffaces", name = "leaflet.js")})
@FacesRenderer(componentFamily = LeafFaces.COMPONENT_FAMILY, rendererType = "com.blogspot.ofarukkurt.leaffaces.component.map.MapRenderer")
public class MapRenderer extends Renderer {

    @Override
    public void encodeBegin(FacesContext context, UIComponent component) throws IOException {
        Map map = (Map) component;
        ResponseWriter writer = context.getResponseWriter();
        String clientId = map.getClientId(context);

        writer.startElement("div", map);
        writer.writeAttribute("id", map.getClientId(), "id");
        writer.writeAttribute("style", "width:500px;height:500px", null);
        writer.endElement("div");

        writer.startElement("script", map);
        writer.writeAttribute("type", "text/javascript", null);

        String addMap = "var " + map.getWidgetVar() + " = L.map('" + clientId + "', {"
                + "minZoom: " + map.getMinZoom() + ","
                + "maxZoom: " + map.getMaxZoom() + ","
                + "})"
                + ".setView([" + map.getCenter() + "],"
                + map.getZoom() + ");";
        addMap += "L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {\n"
                + "		maxZoom: 18,\n"
                + "		attribution: 'Map data &copy; <a href=\"https://www.openstreetmap.org/\">OpenStreetMap</a> contributors, ' +\n"
                + "			'<a href=\"https://creativecommons.org/licenses/by-sa/2.0/\">CC-BY-SA</a>, ' +\n"
                + "			'Imagery © <a href=\"https://www.mapbox.com/\">Mapbox</a>',\n"
                + "		id: 'mapbox.streets'\n"
                + "	}).addTo(" + map.getWidgetVar() + ");";

        writer.write(addMap);
    }

    @Override
    public void encodeEnd(FacesContext facesContext, UIComponent component) throws IOException {
        ResponseWriter writer = facesContext.getResponseWriter();

        writer.endElement("script");
    }

}
