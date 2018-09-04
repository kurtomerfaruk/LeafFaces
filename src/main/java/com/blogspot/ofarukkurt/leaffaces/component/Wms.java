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
 * @Created on date 0409/2018 08:12:54
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Wms")
public class Wms extends UIComponentBase {

    protected enum PropertyKeys {

        url,
        layers,
        wmsName
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

        String url = getUrl();
        String layers = getLayers();

        String wmsName = getWmsName();

        String layer = "var " + wmsName + " = L.tileLayer.wms('" + url + "', {"
                + "layers: '" + layers + "',"
                + "format: 'image/png',"
                + "transparent:true"
                + "}).addTo(" + mapVar + ");";

        writer.write(layer);

    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Wms";
    }

    public String getUrl() {
        return (String) getStateHelper().eval(PropertyKeys.url);
    }

    public void setUrl(final String url) {
        getStateHelper().put(PropertyKeys.url, url);
    }

    public String getLayers() {
        return (String) getStateHelper().eval(PropertyKeys.layers);
    }

    public void setLayers(final String layers) {
        getStateHelper().put(PropertyKeys.layers, layers);
    }

    public String getWmsName() {
        return (String) getStateHelper().eval(PropertyKeys.wmsName);
    }

    public void setWmsName(final String wmsName) {
        getStateHelper().put(PropertyKeys.wmsName, wmsName);
    }

}
