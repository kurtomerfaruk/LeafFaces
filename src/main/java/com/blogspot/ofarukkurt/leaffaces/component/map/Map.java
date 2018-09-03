package com.blogspot.ofarukkurt.leaffaces.component.map;

import com.blogspot.ofarukkurt.leaffaces.utils.LeafFaces;
import java.io.IOException;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIOutput;
import javax.faces.component.behavior.ClientBehaviorHolder;
import javax.faces.context.FacesContext;

/**
 *
 * @author Omer Faruk Kurt
 * @Created on date 03/09/2018 10:18:16
 */
@FacesComponent(value = "com.blogspot.ofarukkurt.leaffaces.component.map.Map")
public class Map extends UIOutput implements ClientBehaviorHolder {

    protected enum PropertyKeys {

        widgetVar,
        center,
        zoom,
        minZoom,
        maxZoom
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY;
    }

    @Override
    public boolean getRendersChildren() {
        return true;
    }

    @Override
    public void encodeChildren(FacesContext context) throws IOException {

    }

    public String getWidgetVar() {
        return String.class.cast(this.getStateHelper().eval(PropertyKeys.widgetVar, null));
    }

    public void setWidgetVar(String value) {
        this.getStateHelper().put(PropertyKeys.widgetVar, value);
    }

    public String getCenter() {
        return String.class.cast(this.getStateHelper().eval(PropertyKeys.center, null));
    }

    public void setCenter(String value) {
        this.getStateHelper().put(PropertyKeys.center, value);
    }

    public int getZoom() {
        return Integer.class.cast(this.getStateHelper().eval(PropertyKeys.zoom, null));
    }

    public void setZoom(int value) {
        this.getStateHelper().put(PropertyKeys.zoom, value);
    }

    public int getMinZoom() {
        return Integer.class.cast(this.getStateHelper().eval(PropertyKeys.minZoom, null));
    }

    public void setMinZoom(int value) {
        this.getStateHelper().put(PropertyKeys.minZoom, value);
    }

    public int getMaxZoom() {
        return Integer.class.cast(this.getStateHelper().eval(PropertyKeys.maxZoom, null));
    }

    public void setMaxZoom(int value) {
        this.getStateHelper().put(PropertyKeys.maxZoom, value);
    }
}
