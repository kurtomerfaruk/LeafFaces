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
 * @Created on date 06/09/2018 14:42:46
 */
@FacesComponent(value = LeafFaces.COMPONENT_FAMILY + ".Scale")
public class Scale extends UIComponentBase {

    protected enum PropertyKeys {

        position,
        maxWidth,
        metric,
        imperial,
        updateWhenIdle
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
        int maxWidth = getMaxWidth();
        boolean metric = isMetric();
        boolean imperial = isImperial();
        boolean updateWhenIdle = isUpdateWhenIdle();

        String layer = "L.control.scale({ "
                + "position: '" + position + "',"
                + "maxWidth:" + maxWidth + ","
                + "metric:" + metric + ","
                + "imperial:" + imperial + ","
                + "updateWhenIdle:" + updateWhenIdle + "})"
                + ".addTo(" + mapVar + ");";

        writer.write(layer);
    }

    @Override
    public String getFamily() {
        return LeafFaces.COMPONENT_FAMILY + ".Scale";
    }

    public String getPosition() {
        return (String) getStateHelper().eval(Logo.PropertyKeys.position, "bottomleft");
    }

    public void setPosition(final String position) {
        getStateHelper().put(Logo.PropertyKeys.position, position);
    }

    public int getMaxWidth() {
        return (Integer) getStateHelper().eval(PropertyKeys.maxWidth, 100);
    }

    public void setMaxWidth(int maxWidth) {
        getStateHelper().put(PropertyKeys.maxWidth, maxWidth);
    }

    public boolean isMetric() {
        return (Boolean) getStateHelper().eval(PropertyKeys.metric, true);
    }

    public void setMetric(boolean metric) {
        getStateHelper().put(PropertyKeys.metric, metric);
    }

    public boolean isImperial() {
        return (Boolean) getStateHelper().eval(PropertyKeys.imperial, true);
    }

    public void setImperial(boolean imperial) {
        getStateHelper().put(PropertyKeys.imperial, imperial);
    }

    public boolean isUpdateWhenIdle() {
        return (Boolean) getStateHelper().eval(PropertyKeys.updateWhenIdle, false);
    }

    public void setUpdateWhenIdle(boolean updateWhenIdle) {
        getStateHelper().put(PropertyKeys.updateWhenIdle, updateWhenIdle);
    }

}
